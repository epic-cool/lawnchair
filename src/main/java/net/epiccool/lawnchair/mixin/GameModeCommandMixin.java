package net.epiccool.lawnchair.mixin;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.command.CommandSource;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.GameModeCommand;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.world.GameMode;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Mixin(GameModeCommand.class)
public abstract class GameModeCommandMixin {
    private static final List<String> GAMEMODE_SUGGESTIONS = Arrays.asList(
            "survival", "creative", "adventure", "spectator"
    );

    @Inject(method = "register", at = @At("TAIL"))
    private static void otherNames(CommandDispatcher<ServerCommandSource> dispatcher, CallbackInfo ci) {
        registerCommand(dispatcher, "gamemode");
        registerCommand(dispatcher, "gm");
    }

    private static void registerCommand(CommandDispatcher<ServerCommandSource> dispatcher, String literal) {
        dispatcher.register(CommandManager.literal(literal)
                .requires(source -> source.hasPermissionLevel(2))
                .then(CommandManager.argument("mode", StringArgumentType.word())
                        .suggests((context, builder) ->
                                CommandSource.suggestMatching(GAMEMODE_SUGGESTIONS, builder))
                        .executes(context -> {
                            ServerPlayerEntity player = context.getSource().getPlayerOrThrow();
                            GameMode mode = parseGamemode(StringArgumentType.getString(context, "mode"));

                            if (player.changeGameMode(mode)) {
                                sendFeedback(context.getSource(), player, mode);
                            }

                            return 1;
                        })
                        .then(CommandManager.argument("target", EntityArgumentType.players())
                                .executes(context -> {
                                    GameMode mode = parseGamemode(StringArgumentType.getString(context, "mode"));
                                    Collection<ServerPlayerEntity> targets = EntityArgumentType.getPlayers(context, "target");
                                    int count = 0;
                                    for (ServerPlayerEntity target : targets) {
                                        if (target.changeGameMode(mode)) {
                                            count++;
                                            sendFeedback(context.getSource(), target, mode);
                                        }
                                    }
                                    return count;
                                }))));
    }

    private static GameMode parseGamemode(String input) throws CommandSyntaxException {
        return switch (input.toLowerCase()) {
            case "0", "s", "survival" -> GameMode.SURVIVAL;
            case "1", "c", "creative" -> GameMode.CREATIVE;
            case "2", "a", "adventure" -> GameMode.ADVENTURE;
            case "3", "sp", "spectator" -> GameMode.SPECTATOR;
            default -> throw new SimpleCommandExceptionType(Text.translatable("commands.gamemode.invalid", input.toLowerCase())).create();
        };
    }

    @Unique
    private static void sendFeedback(ServerCommandSource source, ServerPlayerEntity player, GameMode mode) {
        MutableText modeText = Text.translatable("gameMode." + mode.getId());
        if (source.getEntity() == player) {
            source.sendFeedback(() -> Text.translatable("commands.gamemode.success.self", modeText), true);
        } else {
            if (source.getWorld().getGameRules().getBoolean(GameRules.SEND_COMMAND_FEEDBACK)) {
                player.sendMessage(Text.translatable("gameMode.changed", modeText));
            }
            source.sendFeedback(() -> Text.translatable("commands.gamemode.success.other", player.getDisplayName(), modeText), true);
        }
    }
}

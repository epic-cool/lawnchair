package net.epiccool.lawnchair.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CommandUtil {
    private static final Map<String, BlockPos> PLAYER_HOMES = new HashMap<>();

    public static final Map<String, String> EMOJIS = Map.<String, String>ofEntries(
            Map.entry("amazed", "( ﾟдﾟ)"),
            Map.entry("angel", "☜(⌒▽⌒)☞"),
            Map.entry("angry", "(＃ﾟДﾟ)"),
            Map.entry("angry1", "ヽ(o`皿′o)ﾉ"),
            Map.entry("bad", "（・Ａ・）"),
            Map.entry("bear", "ʕ •ᴥ•ʔ"),
            Map.entry("bellySlide", "⊂（ﾟДﾟ⊂⌒｀つ≡≡≡(´⌒;;;≡≡≡"),
            Map.entry("blush", "(,,> ᴗ <,,)"),
            Map.entry("bored", "(ΘεΘ;)"),
            Map.entry("carefree", "（´∀｀）"),
            Map.entry("carefree1", "⊂二二二（＾ω＾）二⊃"),
            Map.entry("carryMoney", "（･∀･)つ⑩"),
            Map.entry("cat", "(=^ェ^=)"),
            Map.entry("cat1", "=＾● ⋏ ●＾="),
            Map.entry("cheer", "\\(^o^)/"),
            Map.entry("comeOn", "щ(ﾟДﾟщ)"),
            Map.entry("comeOn1", "(屮ﾟДﾟ)屮"),
            Map.entry("disapprove", "ಠ_ಠ"),
            Map.entry("disapprove1", "ಠ__ಠ"),
            Map.entry("disapprove2", "ಠ益ಠ"),
            Map.entry("discombobulated", "┌(；`～,)┐"),
            Map.entry("dog", "(ᵔᴥᵔ)"),
            Map.entry("doIt", "(☞ﾟヮﾟ)☞"),
            Map.entry("doIt1", "☜(ﾟヮﾟ☜)"),
            Map.entry("flowerGirl", "(◕‿◕✿)"),
            Map.entry("friendly", "ヽ(´ー`)人(´∇｀)人(`Д´)ノ"),
            Map.entry("good", "（・∀・）"),
            Map.entry("grimace", "(╬ ಠ益ಠ)"),
            Map.entry("happy", "( ﾟヮﾟ)"),
            Map.entry("happy1", "♪┏(・o･)┛♪"),
            Map.entry("happy2", "♪┗ (･o･) ┓♪"),
            Map.entry("happy3", "♪┏(・o･)┛♪┗ ( ･o･) ┓"),
            Map.entry("happy4", "d(*⌒▽⌒*)b"),
            Map.entry("happy5", "ヽ(´▽`)/"),
            Map.entry("happy6", "^ㅂ^"),
            Map.entry("impatient", "(ﾟДﾟ;≡;ﾟДﾟ)"),
            Map.entry("indifferent", "（　´_ゝ`）"),
            Map.entry("intuition", "m9(・∀・)"),
            Map.entry("irritable", "ヽ(`Д´)ﾉ"),
            Map.entry("kick", "＼ ￣ヘ￣"),
            Map.entry("kowtow", "m(_ _)m"),
            Map.entry("lenny", "( ͡° ͜ʖ ͡°)"),
            Map.entry("lenny1", "(ง ͠ ° ͟ʖ ͡ °) ง"),
            Map.entry("lonely", "('A`)"),
            Map.entry("peaceful", "ヽ(´ー｀)ﾉ"),
            Map.entry("peek", "┬┴┬┴┤(･_├┬┴┬┴"),
            Map.entry("perky", "(`･ω･´)"),
            Map.entry("poke", "( ´∀｀)σ)∀`)"),
            Map.entry("run", "ε=ε=ε=┌(;*´Д`)ﾉ"),
            Map.entry("sad", "(´；ω；`)"),
            Map.entry("sad1", "（ ´,_ゝ`)"),
            Map.entry("sad2", "（つ Д ｀）"),
            Map.entry("salute", "(｀-´)>"),
            Map.entry("shock", "(l'o'l)"),
            Map.entry("shock1", "Σ(゜д゜;)"),
            Map.entry("shout", "(≧ロ≦)"),
            Map.entry("shrug", "¯\\_(ツ)_/¯"),
            Map.entry("snubbed", "(´･ω･`)"),
            Map.entry("snorlax", "(￣ー￣)"),
            Map.entry("smoking", "(´ー`)y-~~"),
            Map.entry("spook", "(((( ；ﾟДﾟ)))"),
            Map.entry("surprise", "Σ(ﾟДﾟ)"),
            Map.entry("surprise1", "（　ﾟДﾟ）"),
            Map.entry("tableFlip", "(╯°□°）╯︵ ┻━┻"),
            Map.entry("tableFlip1", "┻━┻ ︵ ヽ(°□°ヽ)"),
            Map.entry("tableFlip2", "┻━┻ ︵ ＼( °□° )／ ︵ ┻━┻"),
            Map.entry("tableFlip3", "┬─┬ノ( º _ ºノ)"),
            Map.entry("tableFlip4", "(ﾉಥ益ಥ）ﾉ ┻━┻"),
            Map.entry("tableFlip5", "┬──┬ ¯\\_(ツ)"),
            Map.entry("tableFlip6", "┻━┻ ︵ヽ(`Д´)ﾉ︵ ┻━┻"),
            Map.entry("tableFlip7", "┻━┻ ︵ ¯\\(ツ)/¯ ︵ ┻━┻"),
            Map.entry("tableFlip8", "(╯°Д°）╯︵ /(.□ . \\)"),
            Map.entry("tableFlip9", "ʕノ•ᴥ•ʔノ ︵ ┻━┻"),
            Map.entry("thinking", "(ಠ_ಠ)つ"),
            Map.entry("thinking1", "（´-`）.｡oO( ... )"),
            Map.entry("toast", "（ ^_^）o自自o（^_^ ）"),
            Map.entry("unconvinced", "エェェ(´д｀)ェェエ"),
            Map.entry("unsure", "┐('～`；)┌"),
            Map.entry("whisper", "( ´д)ﾋｿ(´Д｀)ﾋｿ(Д｀)"),
            Map.entry("wink", "(>ω^)"),
            Map.entry("wink1", "(^ω<)"),
            Map.entry("yell", "（ ´Д｀）")
    );

    public static final Set<Text> EMOJI_MESSAGES = new HashSet<>();

    public static int executeHome(CommandContext<ServerCommandSource> ctx, ServerPlayerEntity target) {
        ServerPlayerEntity executor = ctx.getSource().getPlayer();
        if (target == null) target = executor;

        assert target != null;
        ServerWorld world = target.getEntityWorld();
        BlockPos home = PLAYER_HOMES.getOrDefault(target.getUuidAsString(), world.getSpawnPoint().getPos());

        if (home == null) {
            ctx.getSource().sendFeedback(() -> Text.translatable("commands.lawnchair.home.not_set"), false);
            return 0;
        }

        assert executor != null;
        executor.teleport(world, home.getX(), home.getY(), home.getZ(), Set.of(), executor.getYaw(), executor.getPitch(), true);
        ServerPlayerEntity finalTarget = target;
        ctx.getSource().sendFeedback(() -> Text.translatable("commands.lawnchair.home.teleported", finalTarget.getName()), true);
        return Command.SINGLE_SUCCESS;
    }

    public static int resetHome(CommandContext<ServerCommandSource> ctx, ServerPlayerEntity target) {
        ServerPlayerEntity executor = ctx.getSource().getPlayer();
        if (target == null) target = executor;

        assert executor != null;
        ServerWorld world = executor.getEntityWorld();
        BlockPos spawn = world.getSpawnPoint().getPos();
        PLAYER_HOMES.put(target.getUuidAsString(), spawn);

        ServerPlayerEntity finalTarget = target;
        ctx.getSource().sendFeedback(() -> Text.translatable("commands.lawnchair.home.reset", finalTarget.getName()), true);
        return Command.SINGLE_SUCCESS;
    }

    public static int setHome(CommandContext<ServerCommandSource> ctx, ServerPlayerEntity target, Vec3d position) {
        if (position == null) return 0;
        ServerPlayerEntity executor = ctx.getSource().getPlayer();
        if (target == null) target = executor;

        BlockPos blockPos = new BlockPos((int) position.x, (int) position.y, (int) position.z);
        assert target != null;
        PLAYER_HOMES.put(target.getUuidAsString(), blockPos);

        ServerPlayerEntity finalTarget = target;
        ctx.getSource().sendFeedback(() -> Text.translatable("commands.lawnchair.home.set", finalTarget.getName(), blockPos.toShortString()), true);
        return Command.SINGLE_SUCCESS;
    }
    //todo: make this better, i.e. not change the day. Use a mixin
    public static int setPhase(CommandContext<ServerCommandSource> ctx, int phase) {
        ServerWorld world = ctx.getSource().getWorld();
        phase = Math.floorMod(phase, 8);

        long currentTime = world.getTimeOfDay();
        long days = currentTime / 24000L;
        long newTime = (days - (days % 8) + phase) * 24000L + (currentTime % 24000L);

        world.setTimeOfDay(newTime);
        int finalPhase = phase;
        ctx.getSource().sendFeedback(() -> Text.translatable("commands.lawnchair.phase.set", finalPhase, getPhase(finalPhase)), true);
        return Command.SINGLE_SUCCESS;
    }

    public static String getPhase(int phase) {
        return switch (phase) {
            case 0 -> "Full Moon";
            case 1 -> "Waning Gibbous";
            case 2 -> "Last Quarter";
            case 3 -> "Waning Crescent";
            case 4 -> "New Moon";
            case 5 -> "Waxing Crescent";
            case 6 -> "First Quarter";
            case 7 -> "Waxing Gibbous";
            default -> throw new IllegalStateException("Unexpected value: " + phase);
        };
    }

    public static int sendEmoji(CommandContext<ServerCommandSource> ctx, String name) {
        ServerPlayerEntity player = ctx.getSource().getPlayer();

        if (player == null) return 0;

        String emoji = EMOJIS.get(name);
        if (emoji == null) {
            //todo: fix error msg
            player.sendMessage(Text.translatable("commands.lawnchair.emoji.invalid", name).formatted(Formatting.RED));
            return 0;
        }

        Text message = Text.literal("<" + player.getName().getString() + "> " + emoji);

        EMOJI_MESSAGES.add(Text.literal(emoji));

        player.getEntityWorld().getServer().getPlayerManager().broadcast(message, false);

        return Command.SINGLE_SUCCESS;
    }

    public static int emojiError(CommandContext<ServerCommandSource> ctx, String name) {
        ServerPlayerEntity player = ctx.getSource().getPlayer();

        player.sendMessage(Text.translatable("commands.lawnchair.emoji.invalid", name).formatted(Formatting.RED));
        return 0;
    }

    public static int toggleGravity(CommandContext<ServerCommandSource> context) {
        ServerPlayerEntity player = context.getSource().getPlayer();

        assert player != null;
        boolean newGravityState = !player.hasNoGravity();
        player.setNoGravity(newGravityState);

        String message = newGravityState ? "commands.lawnchair.grav.disabled" : "commands.lawnchair.grav.enabled";
        context.getSource().sendFeedback(() -> Text.translatable(message), true);

        return Command.SINGLE_SUCCESS;
    }
}

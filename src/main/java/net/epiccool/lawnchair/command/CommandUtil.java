package net.epiccool.lawnchair.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CommandUtil {
    private static final Map<String, BlockPos> PLAYER_HOMES = new HashMap<>();

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

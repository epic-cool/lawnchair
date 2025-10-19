package net.epiccool.lawnchair.block.custom;

import net.epiccool.lawnchair.Lawnchair;
import net.epiccool.lawnchair.block.ModBlocks;
import net.epiccool.lawnchair.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.block.WireOrientation;
import org.jetbrains.annotations.Nullable;

public class EvilGoopBlock extends Block {
    public static final BooleanProperty DORMANT = BooleanProperty.of("dormant");

    private static final double SPREAD_CHANCE = 0.05F;
    private static final int PARTICLE_COUNT = 6;

    private static final TagKey<Block> IMMUNE_TAG = TagKey.of(RegistryKeys.BLOCK, Identifier.of(Lawnchair.MODID, "evil_goop_immune"));

    public EvilGoopBlock(Settings settings) {
        super(settings.ticksRandomly().allowsSpawning((state, world, pos, type) -> false));
        this.setDefaultState(this.stateManager.getDefaultState().with(DORMANT, false));
    }

    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient() && stack.getItem() == Items.GLASS_BOTTLE) {
            ItemStack fullBottle = new ItemStack(ModItems.EVIL_GOOP_FRAGMENT); //todo: Evil goop effect

            stack.decrementUnlessCreative(1, player);

            if (!player.getInventory().insertStack(fullBottle)) {
                player.dropItem(fullBottle, false);
            }

            if (!state.get(DORMANT)) {
                world.setBlockState(pos, state.with(DORMANT, true));
            }

            world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_FILL, net.minecraft.sound.SoundCategory.BLOCKS, 1.0F, 1.0F);

            return ActionResult.SUCCESS;
        }
        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(DORMANT);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(DORMANT)) return;

        boolean hasValidNeighbor = false;

        for (Direction dir : Direction.values()) {
            BlockPos neighborPos = pos.offset(dir);
            BlockState neighborState = world.getBlockState(neighborPos);

            if (neighborState.isIn(IMMUNE_TAG)) continue;

            hasValidNeighbor = true;
            if (random.nextDouble() < SPREAD_CHANCE) {
                world.setBlockState(neighborPos, this.getDefaultState(), Block.NOTIFY_ALL);
            }
        }

        if (!hasValidNeighbor) {
            world.setBlockState(pos, state.with(DORMANT, true), Block.NOTIFY_ALL);
        }
    }

    @Override
    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, @Nullable WireOrientation wireOrientation, boolean notify) {
        if (world.isClient() || !state.get(DORMANT)) return;

        for (Direction dir : Direction.values()) {
            BlockPos neighborPos = pos.offset(dir);
            BlockState neighborState = world.getBlockState(neighborPos);

            if (neighborState.isAir() ||
                    neighborState.getBlock() == Blocks.OBSIDIAN ||
                    neighborState.getBlock() == ModBlocks.STEEL_BLOCK ||
                    neighborState.getBlock() == ModBlocks.EVIL_GOOP ||
                    neighborState.getBlock() == Blocks.BARRIER ||
                    neighborState.getBlock() == Blocks.BEDROCK ||
                    neighborState.getBlock() == Blocks.CHAIN_COMMAND_BLOCK ||
                    neighborState.getBlock() == Blocks.COMMAND_BLOCK ||
                    neighborState.getBlock() == Blocks.DRAGON_EGG ||
                    neighborState.getBlock() == Blocks.JIGSAW ||
                    neighborState.getBlock() == Blocks.LIGHT ||
                    neighborState.getBlock() == Blocks.REPEATING_COMMAND_BLOCK ||
                    neighborState.getBlock() == Blocks.STRUCTURE_BLOCK ||
                    neighborState.getBlock() == Blocks.STRUCTURE_VOID ||
                    neighborState.getBlock() == Blocks.TEST_BLOCK ||
                    neighborState.getBlock() == Blocks.TEST_INSTANCE_BLOCK) {
                continue;
            }

            world.setBlockState(pos, state.with(DORMANT, false), Block.NOTIFY_ALL);

            for (int x = -5; x <= 5; x++) {
                for (int y = -5; y <= 5; y++) {
                    for (int z = -5; z <= 5; z++) {
                        BlockPos targetPos = pos.add(x, y, z);
                        BlockState targetState = world.getBlockState(targetPos);

                        if (targetState.getBlock() == Blocks.WATER || targetState.getBlock() == Blocks.LAVA) {
                            world.setBlockState(targetPos, Blocks.BEDROCK.getDefaultState(), Block.NOTIFY_ALL);
                        }
                    }
                }
            }
            break;
        }
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(DORMANT)) return;

        for (int i = 0; i < PARTICLE_COUNT; i++) {
            double x = pos.getX() + 0.5 + (random.nextDouble() - 0.5);
            double y = pos.getY() + 0.5 + (random.nextDouble() - 0.5);
            double z = pos.getZ() + 0.5 + (random.nextDouble() - 0.5);
            world.addParticleClient(ParticleTypes.LARGE_SMOKE, x, y, z, 0.0, 0.0, 0.0);
        }
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(DORMANT, false);
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (!world.isClient()) {
            world.scheduleBlockTick(pos, this, 20);
        }
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        randomTick(state, world, pos, random);

        if (!state.get(DORMANT)) {
            double maxRadius = 5.0;
            Box area = new Box(
                    pos.getX() - maxRadius, pos.getY() - maxRadius, pos.getZ() - maxRadius,
                    pos.getX() + maxRadius, pos.getY() + maxRadius, pos.getZ() + maxRadius
            );

            for (PlayerEntity player : world.getEntitiesByClass(PlayerEntity.class, area, p -> true)) {
                if (player.getEquippedStack(EquipmentSlot.HEAD).getItem() == ModItems.GAS_MASK) continue;

                double distance = player.squaredDistanceTo(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
                double normalized = Math.max(0, 1.0 - (Math.sqrt(distance) / maxRadius));
                int amplifier = (int) (normalized * 3);

                if (amplifier > 0) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 40, amplifier)); //todo: Evil goop effect
                }
            }

            world.scheduleBlockTick(pos, this, 20);
        }
    }
}

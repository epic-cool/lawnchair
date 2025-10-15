//package net.epiccool.lawnchair.block.custom;
//
//import com.mojang.serialization.MapCodec;
//import net.epiccool.lawnchair.block.ModBlockEntities;
//import net.epiccool.lawnchair.block.entity.AlloyMixerBlockEntity;
//import net.epiccool.lawnchair.screen.AlloyMixerScreenHandler;
//import net.epiccool.lawnchair.stat.ModStats;
//import net.minecraft.block.*;
//import net.minecraft.block.entity.BlockEntity;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.item.ItemPlacementContext;
//import net.minecraft.item.ItemStack;
//import net.minecraft.screen.CraftingScreenHandler;
//import net.minecraft.screen.NamedScreenHandlerFactory;
//import net.minecraft.screen.ScreenHandlerContext;
//import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
//import net.minecraft.state.StateManager;
//import net.minecraft.state.property.EnumProperty;
//import net.minecraft.text.Text;
//import net.minecraft.util.ActionResult;
//import net.minecraft.util.collection.DefaultedList;
//import net.minecraft.util.hit.BlockHitResult;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.Direction;
//import net.minecraft.world.World;
//import org.jetbrains.annotations.Nullable;
//
//
//public class AlloyMixerBlock extends BlockWithEntity {
//    public static final MapCodec<AlloyMixerBlock> CODEC = createCodec(AlloyMixerBlock::new);
//    private static final Text TITLE = Text.translatable("container.lawnchair.alloy_mixer");
//    public static final EnumProperty<Direction> FACING = EnumProperty.of("facing", Direction.class, dir -> dir.getAxis().isHorizontal());
//
//    @Override
//    public MapCodec<? extends AlloyMixerBlock> getCodec() {
//        return CODEC;
//    }
//
//    public AlloyMixerBlock(AbstractBlock.Settings settings) {
//        super(settings);
//        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
//    }
//
//    @Override
//    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
//        if (!world.isClient()) {
//            player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
//            player.incrementStat(ModStats.INTERACT_WITH_ALLOY_MIXER);
//        }
//
//        return ActionResult.SUCCESS;
//    }
//
//    @Override
//    protected NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
//        BlockEntity be = world.getBlockEntity(pos);
//        if (be instanceof AlloyMixerBlockEntity) {
//            return new SimpleNamedScreenHandlerFactory(
//                    (syncId, inventory, player) -> new AlloyMixerScreenHandler(syncId, inventory),
//                    TITLE
//            );
//        }
//        return null;
//    }
//
//    @Override
//    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
//        builder.add(FACING);
//    }
//
//    @Override
//    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
//        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
//    }
//
//    @Override
//    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
//        return new AlloyMixerBlockEntity(pos, state);
//    }
//}

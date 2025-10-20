////package net.epiccool.lawnchair.fluid;
////
////import net.epiccool.lawnchair.Lawnchair;
////import net.minecraft.block.BlockState;
////import net.minecraft.fluid.Fluid;
////import net.minecraft.fluid.FluidState;
////import net.minecraft.item.Item;
////import net.minecraft.state.StateManager;
////import net.minecraft.state.property.Properties;
////
////public abstract class EvilFluid extends LawnchairFluid {
////    @Override
////    public Fluid getFlowing() {
////        return Lawnchair.EVIL_FLUID_FLOWING;
////    }
////
////    @Override
////    public Fluid getStill() {
////        return Lawnchair.EVIL_FLUID_STILL;
////    }
////
////    @Override
////    public Item getBucketItem() {
////        return Lawnchair.EVIL_FLUID_BUCKET;
////    }
////
////    public static class Flowing extends EvilFluid {
////        @Override
////        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
////            super.appendProperties(builder);
////            builder.add(LEVEL);
////        }
////
////        @Override
////        protected BlockState toBlockState(FluidState fluidState) {
////            return Lawnchair.EVIL_FLUID.getDefaultState().with(Properties.LEVEL_15, getBlockStateLevel(fluidState));
////        }
////
////        @Override
////        public int getLevel(FluidState fluidState) {
////            return fluidState.get(LEVEL);
////        }
////
////        @Override
////        public boolean isStill(FluidState fluidState) {
////            return false;
////        }
////    }
////
////    public static class Still extends EvilFluid {
////        @Override
////        public int getLevel(FluidState fluidState) {
////            return 8;
////        }
////
////        @Override
////        protected BlockState toBlockState(FluidState fluidState) {
////            return Lawnchair.EVIL_FLUID.getDefaultState().with(Properties.LEVEL_15, getBlockStateLevel(fluidState));
////        }
////
////        @Override
////        public boolean isStill(FluidState fluidState) {
////            return true;
////        }
////    }
////}
//
//
//package net.epiccool.lawnchair.fluid;
//
//import net.epiccool.lawnchair.Lawnchair;
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockState;
//import net.minecraft.fluid.Fluid;
//import net.minecraft.fluid.FluidState;
//import net.minecraft.item.Item;
//import net.minecraft.state.StateManager;
//import net.minecraft.state.property.Properties;
//import net.minecraft.util.Identifier;
//
//public abstract class EvilFluid extends LawnchairFluid {
//    @Override
//    public Fluid getFlowing() {
//        return Lawnchair.EVIL_FLUID_FLOWING;
//    }
//
//    @Override
//    public Fluid getStill() {
//        return Lawnchair.EVIL_FLUID_STILL;
//    }
//
//    @Override
//    public Item getBucketItem() {
//        return Lawnchair.EVIL_FLUID_BUCKET;
//    }
//
//    public static class Flowing extends EvilFluid {
//        @Override
//        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
//            super.appendProperties(builder);
//            builder.add(LEVEL);
//        }
//
//        @Override
//        protected BlockState toBlockState(FluidState fluidState) {
//            Block block = net.minecraft.registry.Registries.BLOCK.get(Identifier.of(Lawnchair.MODID, "evil_fluid"));
//            return block.getDefaultState().with(Properties.LEVEL_15, getBlockStateLevel(fluidState));
//        }
//
//        @Override
//        public int getLevel(FluidState fluidState) {
//            return fluidState.get(LEVEL);
//        }
//
//        @Override
//        public boolean isStill(FluidState fluidState) {
//            return false;
//        }
//    }
//
//    public static class Still extends EvilFluid {
//        @Override
//        public int getLevel(FluidState fluidState) {
//            return 8;
//        }
//
//        @Override
//        protected BlockState toBlockState(FluidState fluidState) {
//            return Lawnchair.EVIL_FLUID == null
//                    ? null
//                    : Lawnchair.EVIL_FLUID.getDefaultState().with(Properties.LEVEL_15, getBlockStateLevel(fluidState));
//        }
//
//        @Override
//        public boolean isStill(FluidState fluidState) {
//            return true;
//        }
//    }
//}

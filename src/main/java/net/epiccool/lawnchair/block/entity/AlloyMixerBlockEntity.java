//package net.epiccool.lawnchair.block.entity;
//
//import net.epiccool.lawnchair.block.ModBlockEntities;
//import net.epiccool.lawnchair.recipe.AlloyMixerRecipe;
//import net.epiccool.lawnchair.recipe.AlloyMixerRecipeInput;
//import net.epiccool.lawnchair.recipe.ModRecipes;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.entity.BlockEntity;
//import net.minecraft.block.entity.BlockEntityType;
//import net.minecraft.component.ComponentsAccess;
//import net.minecraft.inventory.Inventories;
//import net.minecraft.inventory.SimpleInventory;
//import net.minecraft.item.ItemStack;
//import net.minecraft.nbt.NbtCompound;
//import net.minecraft.recipe.RecipeEntry;
//import net.minecraft.server.world.ServerWorld;
//import net.minecraft.storage.ReadView;
//import net.minecraft.storage.WriteView;
//import net.minecraft.util.collection.DefaultedList;
//import net.minecraft.util.math.BlockPos;
//
//import java.util.Optional;
//
//public class AlloyMixerBlockEntity extends BlockEntity {
//    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);
//
//    private static final int IRON_SLOT = 0;
//    private static final int COAL_SLOT = 1;
//    private static final int OUTPUT_SLOT = 2;
//
//    public AlloyMixerBlockEntity(BlockPos pos, BlockState state) {
//        super(ModBlockEntities.ALLOY_MIXER_ENTITY, pos, state);
//    }
//
//    public DefaultedList<ItemStack> getItems() {
//        return inventory;
//    }
//
//    public ItemStack getStack(int slot) {
//        return inventory.get(slot);
//    }
//
//    public void setStack(int slot, ItemStack stack) {
//        inventory.set(slot, stack);
//        markDirty();
//    }
//
//    public void removeStack(int slot, int amount) {
//        inventory.set(slot, ItemStack.EMPTY.split(amount));
//        markDirty();
//    }
//
//    public int size() {
//        return inventory.size();
//    }
//
//    @Override
//    protected void readData(ReadView view) {
//        super.readData(view);
//        Inventories.readData(view, inventory);
//    }
//
//    @Override
//    protected void writeData(WriteView view) {
//        super.writeData(view);
//        Inventories.writeData(view, inventory);
//    }
//
////    private void craftItem() {
////      Optional<RecipeEntry<AlloyMixerRecipe>> recipe = getCurrentRecipe();
////
////        ItemStack output = recipe.get().value().output();
////        this.removeStack(IRON_SLOT, 1);
////        this.removeStack(COAL_SLOT, 1);
////        this.setStack(OUTPUT_SLOT, new ItemStack(output.getItem(),
////                this.getStack(OUTPUT_SLOT).getCount() + output.getCount()));
////    }
//
////    private boolean hasRecipe() {
////        Optional<RecipeEntry<AlloyMixerRecipe>> recipe = getCurrentRecipe();
////        if(recipe.isEmpty()) {
////            return false;
////        }
////
////        ItemStack output = recipe.get().value().output();
////        return canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output);
////    }
////
////    private Optional<RecipeEntry<AlloyMixerRecipe>> getCurrentRecipe() {
////        return ((ServerWorld) this.getWorld()).getRecipeManager()
////                .getFirstMatch(ModRecipes.ALLOY_MIXER_TYPE, new AlloyMixerRecipeInput(inventory.get(IRON_SLOT)), this.getWorld());
////    }
//}

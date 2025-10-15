//package net.epiccool.lawnchair.screen;
//
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.entity.player.PlayerInventory;
//import net.minecraft.inventory.SimpleInventory;
//import net.minecraft.item.ItemStack;
//import net.minecraft.screen.ScreenHandler;
//import net.minecraft.screen.ScreenHandlerType;
//import net.minecraft.screen.slot.Slot;
//import org.jetbrains.annotations.Nullable;
//
//public class AlloyMixerScreenHandler extends ScreenHandler {
//    private final SimpleInventory inventory;
//
//    public AlloyMixerScreenHandler(int syncId, PlayerInventory playerInventory) {
//        super(ModScreenHandlers.ALLOY_MIXER_SCREEN_HANDLER, syncId);
//        this.inventory = new SimpleInventory(3);
//
//        int startX = 34;
//        int startY = 19;
//        for (int i = 0; i < 3; i++) {
//            this.addSlot(new Slot(inventory, i, startX + i * 18, startY));
//        }
//
//        int m;
//        int l;
//        for (m = 0; m < 3; ++m) {
//            for (l = 0; l < 9; ++l) {
//                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
//            }
//        }
//
//        for (m = 0; m < 9; ++m) {
//            this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 142));
//        }
//    }
//
//    @Override
//    public ItemStack quickMove(PlayerEntity player, int slot) {
//        return null;
//    }
//
//    @Override
//    public boolean canUse(PlayerEntity player) {
//        return true;
//    }
//
//}

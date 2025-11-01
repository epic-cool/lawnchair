package net.epiccool.lawnchair.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class ColoredItemFrameEntity extends ItemFrameEntity {
    private DyeColor color = DyeColor.WHITE;

    public ColoredItemFrameEntity(EntityType<? extends ColoredItemFrameEntity> type, World world) {
        super(type, world);
    }

    public ColoredItemFrameEntity(EntityType<? extends ColoredItemFrameEntity> type, World world, BlockPos pos, Direction facing, DyeColor color) {
        super(type, world, pos, facing);
        this.color = color == null ? DyeColor.WHITE : color;
    }

    public DyeColor getColor() {
        return color;
    }

    public void setColor(DyeColor color) {
        this.color = color == null ? DyeColor.WHITE : color;
    }

    @Override
    protected void writeCustomData(WriteView view) {
        super.writeCustomData(view);
        view.putString("Color", this.color.getId());
    }

    @Override
    protected void readCustomData(ReadView view) {
        super.readCustomData(view);
        if (view.contains("Color")) {
            this.color = DyeColor.byId(view.getString("Color", this.color.getId()), DyeColor.WHITE);
        }
    }
}

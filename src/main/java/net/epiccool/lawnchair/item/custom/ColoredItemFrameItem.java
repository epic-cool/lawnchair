package net.epiccool.lawnchair.item.custom;

import net.epiccool.lawnchair.entity.custom.ColoredItemFrameEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class ColoredItemFrameItem extends Item {
    private final DyeColor color;
    private final EntityType<ColoredItemFrameEntity> entityType;

    public ColoredItemFrameItem(DyeColor color, EntityType<ColoredItemFrameEntity> entityType, Settings settings) {
        super(settings);
        this.color = color;
        this.entityType = entityType;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos().offset(context.getSide());
        Direction facing = context.getSide();

        if (!world.isClient()) {
            ColoredItemFrameEntity frame = new ColoredItemFrameEntity(entityType, world, pos, facing, color);
            world.spawnEntity(frame);
        }

        return ActionResult.SUCCESS;
    }
}

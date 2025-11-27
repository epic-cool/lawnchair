package net.epiccool.lawnchair.entity.goal;

import net.minecraft.entity.ai.goal.Goal;

//todo: animation (it'll be shoving its head underwater, bc yk... its fishing. [for ducks]
public class FishGoal extends Goal {
    @Override
    public boolean canStart() {
        return false;
    }
}

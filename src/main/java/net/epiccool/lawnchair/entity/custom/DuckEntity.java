package net.epiccool.lawnchair.entity.custom;

import net.epiccool.lawnchair.entity.ModEntities;
import net.epiccool.lawnchair.sound.ModSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class DuckEntity extends AnimalEntity {
    private static final EntityDimensions BABY_BASE_DIMENSIONS = ModEntities.DUCK.getDimensions().scaled(0.5f).withEyeHeight(0.2975f);
    private static final TrackedData<Integer> GENDER = DataTracker.registerData(DuckEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public float flapProgress;
    public float maxWingDeviation;
    public float lastMaxWingDeviation;
    public float lastFlapProgress;
    public float flapSpeed = 1.0f;
    private float field_28639 = 1.0f;
    public int eggLayTime = this.random.nextInt(6000) + 6000;

    public DuckEntity(EntityType<? extends DuckEntity> entityType, World world) {
        super(entityType, world);
        this.setPathfindingPenalty(PathNodeType.WATER, -0.2f);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.4));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.0));
        this.goalSelector.add(3, new TemptGoal(this, 1.0, stack -> stack.isIn(ItemTags.CHICKEN_FOOD), false));
        this.goalSelector.add(4, new FollowParentGoal(this, 1.1));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 6.0f));
        this.goalSelector.add(7, new LookAroundGoal(this));
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(GENDER, this.random.nextInt(3) == 0 ? 1 : 0);
    }

    public boolean isMale() {
        return this.dataTracker.get(GENDER) == 1;
    }

    public void setGender(boolean male) {
        this.dataTracker.set(GENDER, male ? 1 : 0);
    }

    @Override
    protected EntityDimensions getBaseDimensions(EntityPose pose) {
        return this.isBaby() ? BABY_BASE_DIMENSIONS : super.getBaseDimensions(pose);
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return AnimalEntity.createAnimalAttributes()
                .add(EntityAttributes.MAX_HEALTH, 4.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.25);
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        this.lastFlapProgress = this.flapProgress;
        this.lastMaxWingDeviation = this.maxWingDeviation;
        this.maxWingDeviation += (this.isOnGround() ? -1.0f : 4.0f) * 0.3f;
        this.maxWingDeviation = MathHelper.clamp(this.maxWingDeviation, 0.0f, 1.0f);
        if (!this.isOnGround() && this.flapSpeed < 1.0f) {
            this.flapSpeed = 1.0f;
        }
        this.flapSpeed *= 0.9f;
        Vec3d vec3d = this.getVelocity();
        if (!this.isOnGround() && vec3d.y < 0.0) {
            this.setVelocity(vec3d.multiply(1.0, 0.6, 1.0));
        }
        this.flapProgress += this.flapSpeed * 2.0f;

        World world = this.getEntityWorld();
        if (world instanceof ServerWorld serverWorld) {
            if (this.isAlive() && !this.isBaby() && --this.eggLayTime <= 0) {
                dropStack(serverWorld, new ItemStack(Items.EGG));
                this.playSound(ModSoundEvents.ENTITY_DUCK_EGG, 1.0f, 1.0f);
                this.emitGameEvent(GameEvent.ENTITY_PLACE);
                this.eggLayTime = this.random.nextInt(6000) + 6000;
            }
        }
    }

    @Override
    protected boolean isFlappingWings() {
        return this.speed > this.field_28639;
    }

    @Override
    protected void addFlapEffects() {
        this.field_28639 = this.speed + this.maxWingDeviation / 2.0f;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSoundEvents.ENTITY_DUCK_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSoundEvents.ENTITY_DUCK_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSoundEvents.ENTITY_DUCK_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(ModSoundEvents.ENTITY_DUCK_STEP, 0.15f, 1.0f);
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        DuckEntity child = ModEntities.DUCK.create(world, SpawnReason.BREEDING);
        if (child != null) {
            child.setBaby(true);
            child.setGender(world.random.nextInt(3) == 0);
        }
        return child;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isIn(ItemTags.CHICKEN_FOOD);
    }

    @Override
    protected void readCustomData(ReadView view) {
        super.readCustomData(view);
        view.getOptionalInt("EggLayTime").ifPresent(time -> this.eggLayTime = time);
        if (view.contains("Gender")) {
            this.dataTracker.set(GENDER, view.getInt("Gender", 0));
        }
    }

    @Override
    protected void writeCustomData(WriteView view) {
        super.writeCustomData(view);
        view.putInt("EggLayTime", this.eggLayTime);
        view.putInt("Gender", this.dataTracker.get(GENDER));
    }
}

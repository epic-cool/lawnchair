package net.epiccool.lawnchair.block.custom.leaves;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.ParticleUtil;
import net.minecraft.particle.TintedParticleEffect;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class TintedParticleLeavesSlabBlock extends LeavesSlabBlock {
    public static final MapCodec<TintedParticleLeavesSlabBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(Codecs.rangedInclusiveFloat(0.0f, 1.0f).fieldOf("leaf_particle_chance").forGetter(block -> block.leafParticleChance), createSettingsCodec()).apply(instance, TintedParticleLeavesSlabBlock::new));

    public TintedParticleLeavesSlabBlock(float f, AbstractBlock.Settings settings) {
        super(f, settings);
    }

    @Override
    protected void spawnLeafParticle(World world, BlockPos pos, Random random) {
        TintedParticleEffect tintedParticleEffect = TintedParticleEffect.create(ParticleTypes.TINTED_LEAVES, world.getBlockColor(pos));
        ParticleUtil.spawnParticle(world, pos, random, tintedParticleEffect);
    }

    public MapCodec<? extends TintedParticleLeavesSlabBlock> getCodec() {
        return CODEC;
    }
}

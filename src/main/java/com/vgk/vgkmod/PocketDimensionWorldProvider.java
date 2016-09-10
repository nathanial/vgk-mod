package com.vgk.vgkmod;

import javax.annotation.Nonnull;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkGenerator;

public class PocketDimensionWorldProvider extends WorldProvider {
	
	public static int dimensionID = 71727;

	@Override
	public DimensionType getDimensionType() {
		return DimensionType.getById(dimensionID);
	}
	
	@Override
	public IChunkGenerator createChunkGenerator() {
		return new FireChunkGenerator(worldObj);
	}
	
    @Override
    @Nonnull
    public String getSaveFolder() {
        return "ELDIM_FIRE";
    }

    @Override
    public float calculateCelestialAngle(long worldTime, float partialTicks) {
        return 0.73F;
    }
}

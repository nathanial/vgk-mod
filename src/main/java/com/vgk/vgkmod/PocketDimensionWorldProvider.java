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
		return new PocketChunkGenerator(worldObj);
	}
	
    @Override
    @Nonnull
    public String getSaveFolder() {
        return "ELDIM_FIRE";
    }

    @Override
    public float calculateCelestialAngle(long worldTime, float partialTicks) {
        return 1.0f;
    }
    
    @Override
    public void calculateInitialWeather() {
    	this.worldObj.weatherEffects.clear();
    	this.worldObj.updateWeatherBody();
    }
    
    @Override
    protected void generateLightBrightnessTable() {
		float f = 0.3f;
		for(int i = 0; i <= 15; ++i) {
			float f1 = 1.0F - (float) i / 15.0F;
			this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
		}
    }

}

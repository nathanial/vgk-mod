package com.vgk.vgkmod;

import javax.annotation.Nonnull;

import net.minecraft.util.math.BlockPos;
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
    	// TODO Auto-generated method stub
    	this.worldObj.weatherEffects.clear();
//    	this.worldObj.setThunderStrength(2.0f);
//    	this.worldObj.setRainStrength(10.0f);
    	this.worldObj.updateWeatherBody();
    }

}

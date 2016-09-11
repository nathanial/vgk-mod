package pocketdimension;

import javax.annotation.Nonnull;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkGenerator;

public class PDWorldProvider extends WorldProvider {
	
	public static int dimensionID = 71727;

	@Override
	public DimensionType getDimensionType() {
		return DimensionType.getById(dimensionID);
	}
	
	@Override
	public IChunkGenerator createChunkGenerator() {
		return new PDChunkGenerator(worldObj);
	}
	
    @Override
    @Nonnull
    public String getSaveFolder() {
        return "DIM_POCKET_DIMENSION";
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

}

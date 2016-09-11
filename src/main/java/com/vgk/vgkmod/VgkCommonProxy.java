package com.vgk.vgkmod;

import net.minecraft.item.ItemBlock;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.GameRegistry;
import pocketdimension.PDWorldProvider;

public abstract class VgkCommonProxy {
    
    public static SubstanceBlock substanceBlock;
    public static ItemBlock itemSubstanceBlock;
	
	public void init(){

	}

	public void preInit() {
        substanceBlock = new SubstanceBlock();
        substanceBlock.setUnlocalizedName("vgk_substance_block");
        substanceBlock.setRegistryName("vgk_substance_block");
        GameRegistry.register(substanceBlock);
        
        itemSubstanceBlock = new ItemBlock(substanceBlock);
        itemSubstanceBlock.setRegistryName(substanceBlock.getRegistryName());
        GameRegistry.register(itemSubstanceBlock);        	
        
        loadDimension();
	}
	
	private void loadDimension(){
		DimensionType dimType = DimensionType.register("Pocket Dimension", "pocket-dimension", PDWorldProvider.dimensionID, PDWorldProvider.class, false);
        DimensionManager.registerDimension(PDWorldProvider.dimensionID, dimType);
	}
}

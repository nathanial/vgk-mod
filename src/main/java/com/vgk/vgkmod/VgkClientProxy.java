package com.vgk.vgkmod;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import pocketdimension.PDRightClickBlock;

public class VgkClientProxy extends VgkCommonProxy {

	@Override
	public void preInit() {
		super.preInit();
        ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation("vgkmod:vgk_substance_block", "inventory");
        final int DEFAULT_ITEM_SUBTYPE = 0;
        ModelLoader.setCustomModelResourceLocation(itemSubstanceBlock, DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);
        MinecraftForge.EVENT_BUS.register(new PDRightClickBlock());
	}

}

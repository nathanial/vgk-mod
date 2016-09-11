package com.vgk.vgkmod

import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.ItemBlock
import net.minecraft.world.DimensionType
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.common.{DimensionManager, MinecraftForge}
import com.vgk.vgkmod.pocketdimension.{PocketDimensionWorldProvider, PDRightClickBlock}
import net.minecraftforge.fml.common.registry.GameRegistry

object VgkCommonProxy {
  var substanceBlock: SubstanceBlock = null
  var itemSubstanceBlock: ItemBlock = null
}

abstract class VgkCommonProxy {
  def init {
  }

  def preInit {
    VgkCommonProxy.substanceBlock = new SubstanceBlock
    VgkCommonProxy.substanceBlock.setUnlocalizedName("vgk_substance_block")
    VgkCommonProxy.substanceBlock.setRegistryName("vgk_substance_block")
    GameRegistry.register(VgkCommonProxy.substanceBlock)
    VgkCommonProxy.itemSubstanceBlock = new ItemBlock(VgkCommonProxy.substanceBlock)
    VgkCommonProxy.itemSubstanceBlock.setRegistryName(VgkCommonProxy.substanceBlock.getRegistryName)
    GameRegistry.register(VgkCommonProxy.itemSubstanceBlock)
    loadDimension
  }

  private def loadDimension {
    val dimType: DimensionType = DimensionType.register("Pocket Dimension", "pocket-dimension", PocketDimensionWorldProvider.dimensionID, classOf[PocketDimensionWorldProvider], false)
    DimensionManager.registerDimension(PocketDimensionWorldProvider.dimensionID, dimType)
  }
}

class VgkClientProxy extends VgkCommonProxy {
  override def preInit {
    super.preInit
    val itemModelResourceLocation: ModelResourceLocation = new ModelResourceLocation("vgkmod:vgk_substance_block", "inventory")
    val DEFAULT_ITEM_SUBTYPE: Int = 0
    ModelLoader.setCustomModelResourceLocation(VgkCommonProxy.itemSubstanceBlock, DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation)
    MinecraftForge.EVENT_BUS.register(new PDRightClickBlock)
  }
}

class VgkServerProxy extends VgkCommonProxy {
}

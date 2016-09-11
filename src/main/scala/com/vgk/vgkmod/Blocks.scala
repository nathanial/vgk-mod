package com.vgk.vgkmod
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.util.{EnumBlockRenderType, BlockRenderLayer}

class SubstanceBlock extends Block(Material.ROCK) {
  setCreativeTab(CreativeTabs.BUILDING_BLOCKS)

  override def getBlockLayer: BlockRenderLayer = BlockRenderLayer.SOLID
  override def isOpaqueCube(iBlockState: IBlockState) = true
  override def isFullCube(iBlockState: IBlockState) = true
  override def getRenderType(iBlockState: IBlockState) = EnumBlockRenderType.MODEL
}

package com.vgk.vgkmod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;

public class SubstanceBlock extends Block {

	public SubstanceBlock(){
		super(Material.ROCK);
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	}
	
	@Override
	public BlockRenderLayer getBlockLayer(){
		return BlockRenderLayer.SOLID;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState iBlockState){
		return true;
	}
	
	@Override
	public boolean isFullCube(IBlockState iBlockState){
		return true;
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState iBlockState){
		return EnumBlockRenderType.MODEL;
	}
}

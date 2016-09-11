package com.vgk.vgkmod.pocketdimension;

import java.util.List;

import com.google.common.collect.ImmutableList;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkGenerator;

public class PDChunkGenerator implements IChunkGenerator {
	
	private final World worldObj;
	
	public PDChunkGenerator(World worldObj) {
		this.worldObj = worldObj;
	}

	@Override
	public Chunk provideChunk(int x, int z) {
		System.out.println("Provide Chunk: " + x + "," + z);
		ChunkPrimer chunkPrimer = new ChunkPrimer();
		generate(x, z, chunkPrimer);
		
		Chunk chunk = new Chunk(this.worldObj, chunkPrimer, x, z);
		
		byte[] biomeArray = chunk.getBiomeArray();
		for(int i = 0; i < biomeArray.length; i++){
			biomeArray[i] = (byte)Biome.getIdForBiome(Biomes.PLAINS);
		}
		
		chunk.generateSkylightMap();
		return chunk;
	}

	@Override
	public void populate(int x, int z) {		
	}

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		return false;
	}

	@Override
	public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		return ImmutableList.of();
	}

	@Override
	public BlockPos getStrongholdGen(World worldIn, String structureName, BlockPos position) {
		return null;
	}

	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {
		// TODO Auto-generated method stub
	}
	

    private void generate(int chunkX, int chunkZ, ChunkPrimer primer) {
        for(int x = 0; x < 16; x++){
        	for(int z = 0; z < 16; z++){
        		if(Math.abs(chunkX) > 2 || Math.abs(chunkZ) > 2){
        			for(int y = 0; y < 100; y++){
        				primer.setBlockState(x, y, z, Blocks.BEDROCK.getDefaultState());	
        			}
        		} else {
            		if((x == 0 && chunkX == -2) || (x == 15 && chunkX == 2) || (z == 0 && chunkZ == -2) || (z == 15 && chunkZ == 2)) {
            			for(int y = 50; y < 60; y++){
            				primer.setBlockState(x, y, z, Blocks.BEDROCK.getDefaultState());
            			}
            		} else {
            			for(int y = 0; y < 50; y++){
            				primer.setBlockState(x, y, z, Blocks.BEDROCK.getDefaultState());
            			}
                		primer.setBlockState(x, 50, z, Blocks.BEDROCK.getDefaultState());
                		primer.setBlockState(x, 51, z, Blocks.STONE.getDefaultState());
                		primer.setBlockState(x, 60, z, Blocks.BEDROCK.getDefaultState());
                		for(int y = 60; y < 100; y++){
                			primer.setBlockState(x, y, z, Blocks.BEDROCK.getDefaultState());
                		}
            		}        			
        		}
        	}
        	
        }
    }

}

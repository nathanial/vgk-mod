package com.vgk.vgkmod;

import java.util.List;
import java.util.Random;

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

public class PocketChunkGenerator implements IChunkGenerator {
	
	private final World worldObj;
	private final Random random;
	private final PocketTerrainGenerator terrainGen = new PocketTerrainGenerator();
	
	public PocketChunkGenerator(World worldObj) {
		this.worldObj = worldObj;
		long seed = 0x1fff;
		this.random = new Random((seed + 314) * 516);
		this.terrainGen.setup(worldObj, this.random, Blocks.NETHERRACK.getDefaultState(), Biomes.PLAINS);
	}

	@Override
	public Chunk provideChunk(int x, int z) {
		System.out.println("Provide Chunk: " + x + "," + z);
		if(Math.abs(x) > 2 || Math.abs(z) > 2){
			ChunkPrimer emptyPrimer = new ChunkPrimer();
			Chunk chunk = new Chunk(this.worldObj, emptyPrimer, x, z);
			return chunk;
		}
		ChunkPrimer chunkPrimer = new ChunkPrimer();
		terrainGen.generate(x, z, chunkPrimer);
		
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

}

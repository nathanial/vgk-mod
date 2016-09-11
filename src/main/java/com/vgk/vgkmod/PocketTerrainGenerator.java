package com.vgk.vgkmod;


import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;

public class PocketTerrainGenerator {

    public static final int LAVALEVEL = 80;

    private final double[] noiseField;
    private double[] noiseData1;
    private double[] noiseData2;
    private double[] noiseData3;
    private double[] noiseData4;

    private NoiseGeneratorOctaves noiseGen1;
    private NoiseGeneratorOctaves noiseGen2;
    private NoiseGeneratorOctaves noiseGen3;
    private NoiseGeneratorPerlin noiseGen4;

    // A NoiseGeneratorOctaves used in generating terrain
    private NoiseGeneratorOctaves noiseGen6;

    private final float[] parabolicField;
    private Biome biome;

    public PocketTerrainGenerator() {
        this.noiseField = new double[825];

        this.parabolicField = new float[25];
        for (int j = -2; j <= 2; ++j) {
            for (int k = -2; k <= 2; ++k) {
                float f = 10.0F / MathHelper.sqrt_float((j * j + k * k) + 0.2F);
                this.parabolicField[j + 2 + (k + 2) * 5] = f;
            }
        }
    }

    public void setup(World world, Random rand, IBlockState baseBlock, Biome biome) {
        this.biome = biome;

        this.noiseGen1 = new NoiseGeneratorOctaves(rand, 16);
        this.noiseGen2 = new NoiseGeneratorOctaves(rand, 16);
        this.noiseGen3 = new NoiseGeneratorOctaves(rand, 8);
        this.noiseGen4 = new NoiseGeneratorPerlin(rand, 4);
        NoiseGeneratorOctaves noiseGen5 = new NoiseGeneratorOctaves(rand, 10);
        this.noiseGen6 = new NoiseGeneratorOctaves(rand, 16);
        NoiseGeneratorOctaves mobSpawnerNoise = new NoiseGeneratorOctaves(rand, 8);

        net.minecraftforge.event.terraingen.InitNoiseGensEvent.ContextOverworld ctx =
                new net.minecraftforge.event.terraingen.InitNoiseGensEvent.ContextOverworld(noiseGen1, noiseGen2, noiseGen3, noiseGen4, noiseGen5, noiseGen6, mobSpawnerNoise);
        ctx = net.minecraftforge.event.terraingen.TerrainGen.getModdedNoiseGenerators(world, rand, ctx);
        this.noiseGen1 = ctx.getLPerlin1();
        this.noiseGen2 = ctx.getLPerlin2();
        this.noiseGen3 = ctx.getPerlin();
        this.noiseGen4 = ctx.getHeight();
//        this.field_185983_b = ctx.getScale();
        this.noiseGen6 = ctx.getDepth();
//        this.field_185985_d = ctx.getForest();
    }

    public void generate(int chunkX, int chunkZ, ChunkPrimer primer) {
        for(int x = 0; x < 16; x++){
        	for(int z = 0; z < 16; z++){
        		if((x == 0 && chunkX == -2) || (x == 15 && chunkX == 2) || (z == 0 && chunkZ == -2) || (z == 15 && chunkZ == 2)) {
        			for(int y = 50; y < 60; y++){
        				primer.setBlockState(x, y, z, Blocks.BEDROCK.getDefaultState());
        			}
        		} else {
            		primer.setBlockState(x, 50, z, Blocks.BEDROCK.getDefaultState());
            		primer.setBlockState(x, 51, z, Blocks.STONE_SLAB.getDefaultState());
            		primer.setBlockState(x, 60, z, Blocks.BEDROCK.getDefaultState());
        		}
        	}
        	
        }
    }
}
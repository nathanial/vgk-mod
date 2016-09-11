package com.vgk.vgkmod.pocketdimension

import java.util

import com.google.common.collect.ImmutableList
import net.minecraft.entity.EnumCreatureType
import net.minecraft.init.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.world.{DimensionType, WorldProvider, World}
import net.minecraft.world.biome.Biome.SpawnListEntry
import net.minecraft.world.chunk.{ChunkPrimer, Chunk, IChunkGenerator}

object IntExtensions {
  implicit def intWithTimes(n: Int) = new {
    def times(f: (Int) => Unit) = {
      var i = 0
      while(i < n) {
        f(i)
        i += 1
      }
    }
  }
}

import IntExtensions._


class PocketDimensionChunkGenerator(worldObj: World) extends IChunkGenerator {
  override def getPossibleCreatures(creatureType: EnumCreatureType, pos: BlockPos): util.List[SpawnListEntry] = ImmutableList.of()
  override def populate(x: Int, z: Int): Unit = {}
  override def recreateStructures(chunkIn: Chunk, x: Int, z: Int): Unit = {}
  override def generateStructures(chunkIn: Chunk, x: Int, z: Int): Boolean = false
  override def getStrongholdGen(worldIn: World, structureName: String, position: BlockPos): BlockPos = null

  override def provideChunk(x: Int, z: Int): Chunk = {
    val chunkPrimer = new ChunkPrimer()
    generate(x, z, chunkPrimer)
    val chunk = new Chunk(worldObj, chunkPrimer, x, z)
    chunk.generateSkylightMap()
    chunk
  }

  def generate(chunkX: Int, chunkZ: Int, primer: ChunkPrimer): Unit = {
    16.times((x) => {
      16.times((z) => {
        if(Math.abs(chunkX) > 2 || Math.abs(chunkZ) > 2){
          100.times((y) => {
            primer.setBlockState(x,y,z, Blocks.BEDROCK.getDefaultState)
          })
        } else {
          if((x == 0 && chunkX == -2) || (x == 15 && chunkX == 2) || (z == 0 && chunkZ == -2) || (z == 15 && chunkZ == 2)){
            10.times((y) => {
              primer.setBlockState(x,y+50,z,Blocks.BEDROCK.getDefaultState)
            })
          } else {
            50.times((y) => {
              primer.setBlockState(x,y,z, Blocks.BEDROCK.getDefaultState)
            })
            primer.setBlockState(x, 50, z, Blocks.BEDROCK.getDefaultState)
            primer.setBlockState(x, 51, z, Blocks.STONE.getDefaultState)
            primer.setBlockState(x, 60, z, Blocks.BEDROCK.getDefaultState)
            40.times((y) => {
             primer.setBlockState(x, y+60, z, Blocks.BEDROCK.getDefaultState)
            })
          }
        }
      })
    })
  }
}

object PocketDimensionWorldProvider {
  def dimensionID = 71727
}

class PocketDimensionWorldProvider extends WorldProvider {
  override def getDimensionType: DimensionType = DimensionType.getById(PocketDimensionWorldProvider.dimensionID)
  override def createChunkGenerator = new PocketDimensionChunkGenerator(worldObj)
  override def getSaveFolder = "DIM_POCKET_DIMENSION"
  override def calculateCelestialAngle(worldTime: Long, partialTicks: Float) = 1.0f
  override def calculateInitialWeather() = {
    this.worldObj.weatherEffects.clear()
    this.worldObj.updateWeatherBody()
  }
}
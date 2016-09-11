package com.vgk.vgkmod.pocketdimension

import com.vgk.vgkmod.{CustomTeleporter, SubstanceBlock}
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraftforge.event.entity.player.PlayerInteractEvent
import net.minecraftforge.fml.common.eventhandler.{EventPriority, SubscribeEvent}
import net.minecraftforge.fml.relauncher.Side


class PocketDimensionEventHandlers {

  @SubscribeEvent(priority=EventPriority.LOWEST, receiveCanceled=false)
  def leftClickBlock(event: PlayerInteractEvent.LeftClickBlock): Unit = {
    if(event.getSide != Side.SERVER) {
      return
    }
    val block = event.getWorld.getBlockState(event.getPos).getBlock
    if(!(block.isInstanceOf[SubstanceBlock])){
      return
    }

    val player = event.getEntityPlayer
    val server = event.getWorld.getMinecraftServer
    if(player.dimension == PocketDimensionWorldProvider.dimensionID){
      val worldServer = server.worldServerForDimension(0)
      val teleporter = new CustomTeleporter(worldServer, 0, 100, 0)
      worldServer.getMinecraftServer.getPlayerList.transferPlayerToDimension(player.asInstanceOf[EntityPlayerMP], 0, teleporter)
    } else {
      val worldServer = server.worldServerForDimension(PocketDimensionWorldProvider.dimensionID)
      val teleporter = new CustomTeleporter(worldServer, 0, 52, 0)
      worldServer.getMinecraftServer.getPlayerList.transferPlayerToDimension(player.asInstanceOf[EntityPlayerMP], PocketDimensionWorldProvider.dimensionID, teleporter)
    }
  }
}
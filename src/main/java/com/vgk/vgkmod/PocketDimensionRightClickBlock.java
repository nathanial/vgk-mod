package com.vgk.vgkmod;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

public class PocketDimensionRightClickBlock {
	
	@SubscribeEvent(priority=EventPriority.LOWEST, receiveCanceled=false)
	public void sendMessage(PlayerInteractEvent.LeftClickBlock event){
		if(event.getSide() != Side.SERVER){
			return;
		}
		Block block = event.getWorld().getBlockState(event.getPos()).getBlock();
		if(!(block instanceof SubstanceBlock)){
			return;
		}
		
		EntityPlayer player = event.getEntityPlayer();
		MinecraftServer server = event.getWorld().getMinecraftServer();		 
		if(player.dimension == PocketDimensionWorldProvider.dimensionID){
			WorldServer worldServer = server.worldServerForDimension(0);
			CustomTeleporter teleporter = new CustomTeleporter(worldServer, 0, 100, 0);
			worldServer.getMinecraftServer().getPlayerList().transferPlayerToDimension((EntityPlayerMP) player, 0, teleporter);
		} else {
			WorldServer worldServer = server.worldServerForDimension(PocketDimensionWorldProvider.dimensionID);
			CustomTeleporter teleporter = new CustomTeleporter(worldServer, 0, 52, 0);
			worldServer.getMinecraftServer().getPlayerList().transferPlayerToDimension((EntityPlayerMP) player, PocketDimensionWorldProvider.dimensionID, teleporter);			
		}
	}
	
}

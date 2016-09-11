package com.vgk.vgkmod;

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
	public void sendMessage(PlayerInteractEvent.RightClickBlock event){
		if(event.getSide() != Side.SERVER){
			return;
		}
		if(event.getItemStack() != null){
			return;
		}
		
		EntityPlayer player = event.getEntityPlayer();
		MinecraftServer server = event.getWorld().getMinecraftServer();
		System.out.println("Event: " + event.getSide());
		System.out.println("Player Type: " + player.getClass());
		System.out.println("Server: " + server);
		String blockState = event.getWorld().getBlockState(event.getPos()).toString();
		System.out.println("Entity: " + event.getEntity() + "," + event.getItemStack() + "," + blockState);
		//System.out.println("Result: " + event.getUseBlock());
		System.out.println("Entity Living: " + event.getEntityLiving());
		System.out.println("Item STack: " + event.getItemStack());
		System.out.println("Use ITem: " + event.getUseItem());
		System.out.println("Use Block: " + event.getUseBlock());
		System.out.println("Canceled: " + event.isCanceled());
		System.out.println("Block State: " + blockState);		
		System.out.println("");
		
		
		 
		if(player.dimension == PocketDimensionWorldProvider.dimensionID){
			WorldServer worldServer = server.worldServerForDimension(0);
			CustomTeleporter teleporter = new CustomTeleporter(worldServer, player.posX, player.posY, player.posZ);
			worldServer.getMinecraftServer().getPlayerList().transferPlayerToDimension((EntityPlayerMP) player, 0, teleporter);
		} else {
			WorldServer worldServer = server.worldServerForDimension(PocketDimensionWorldProvider.dimensionID);
			CustomTeleporter teleporter = new CustomTeleporter(worldServer, player.posX, player.posY + 100, player.posZ);
			worldServer.getMinecraftServer().getPlayerList().transferPlayerToDimension((EntityPlayerMP) player, PocketDimensionWorldProvider.dimensionID, teleporter);			
		}
	}
	
}

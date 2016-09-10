package com.vgk.vgkmod;

import javax.annotation.Nonnull;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockBreakMessage {

	@SubscribeEvent
	public void sendMessage(BreakEvent event){
//		for(int x = 0; x < 10; x++){
//			for(int y = 0; y < 10; y++){
//				for(int z = 0; z < 10; z++){
//					event.getWorld().destroyBlock(event.getPos().toImmutable().add(x - 5,-y + 5,z - 5), false);		
//				}
//			}				
//		}
		EntityPlayer player = event.getPlayer();
		MinecraftServer server = event.getWorld().getMinecraftServer();
		if(event.getPlayer().dimension == PocketDimensionWorldProvider.dimensionID){
			WorldServer worldServer = server.worldServerForDimension(0);
			CustomTeleporter teleporter = new CustomTeleporter(worldServer, player.posX, player.posY, player.posZ);
			worldServer.getMinecraftServer().getPlayerList().transferPlayerToDimension((EntityPlayerMP) player, 0, teleporter);
		} else {
			WorldServer worldServer = server.worldServerForDimension(PocketDimensionWorldProvider.dimensionID);
			CustomTeleporter teleporter = new CustomTeleporter(worldServer, player.posX, player.posY + 100, player.posZ);
			worldServer.getMinecraftServer().getPlayerList().transferPlayerToDimension((EntityPlayerMP) player, PocketDimensionWorldProvider.dimensionID, teleporter);
			
		}
		
	}
	
	public class CustomTeleporter extends Teleporter {
	    private final WorldServer worldServer;
	    private final double x, y, z;
	    
	    @SuppressWarnings("all")
	    public CustomTeleporter(WorldServer world, double x, double y, double z) {
	        super(world);
	        this.worldServer = world;
	        this.x = x;
	        this.y = y;
	        this.z = z;
	    }
	    

	    @Override
	    public void placeInPortal(@Nonnull Entity entity, float rotationYaw) {
	        this.worldServer.getBlockState(new BlockPos((int) this.x, (int) this.y, (int) this.z));

	        entity.setPosition(this.x, this.y, this.z);
	        entity.motionX = 0.0f;
	        entity.motionY = 0.0f;
	        entity.motionZ = 0.0f;
	    }
	}
	
}

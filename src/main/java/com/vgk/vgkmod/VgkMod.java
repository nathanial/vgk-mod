package com.vgk.vgkmod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = VgkMod.MODID, version = VgkMod.VERSION)
public class VgkMod {
    public static final String MODID = "vgkmod";
    public static final String VERSION = "1.0";

    
    @SidedProxy(clientSide="com.vgk.vgkmod.VgkClientProxy", serverSide="com.vgk.vgkmod.VgkServerProxy")
    public static VgkCommonProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
    	proxy.preInit();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	
    	proxy.init();
    	

    }
}

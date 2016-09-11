package com.vgk.vgkmod;

import javax.annotation.Nonnull;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class CustomTeleporter extends Teleporter {
    private final double x, y, z;
    
    @SuppressWarnings("all")
    public CustomTeleporter(WorldServer world, double x, double y, double z) {
        super(world);
        this.x = x;
        this.y = y;
        this.z = x;
    }
    

    @Override
    public void placeInPortal(@Nonnull Entity entity, float rotationYaw) {
        entity.setPosition(this.x, this.y, this.z);
        entity.motionX = 0.0f;
        entity.motionY = 0.0f;
        entity.motionZ = 0.0f;
    }
}
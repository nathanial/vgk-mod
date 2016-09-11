package com.vgk.vgkmod
import net.minecraft.world.{WorldServer, Teleporter}
import net.minecraft.entity.Entity

class CustomTeleporter(world: WorldServer, x: Double, y: Double, z: Double) extends Teleporter(world) {

  override def placeInPortal(entity: Entity, rotationYaw: Float) {
    entity.setPosition(this.x, this.y, this.z)
    System.out.println("Hello")
    entity.motionX = 0.0f
    entity.motionY = 0.0f
    entity.motionZ = 0.0f
  }
}
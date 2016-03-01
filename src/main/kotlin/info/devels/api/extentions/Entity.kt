@file:Suppress("unused")

package info.devels.api.extentions

import cofh.lib.util.position.BlockPosition
import cpw.mods.fml.common.FMLCommonHandler
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.network.play.server.S07PacketRespawn
import net.minecraft.network.play.server.S1DPacketEntityEffect
import net.minecraft.potion.PotionEffect
import net.minecraft.server.management.ServerConfigurationManager
import net.minecraft.util.MathHelper
import net.minecraft.world.World
import net.minecraft.world.WorldServer
import net.minecraftforge.common.util.ForgeDirection


fun EntityLivingBase.facingCardinal(): Int {
    val quadrant = cofh.lib.util.helpers.MathHelper.floor(rotationYaw * 4.0f / 360.0f + 0.5) and 3
    when (quadrant) {
        0 -> return 2
        1 -> return 5
        2 -> return 3
        else -> return 4
    }
}

fun EntityLivingBase.facingForgeDirection(): ForgeDirection {
    return ForgeDirection.VALID_DIRECTIONS[facingCardinal()]
}

fun Entity.transferToDimension(dimension: Int, manager: ServerConfigurationManager) {
    if (this is EntityPlayerMP) {
        return transferPlayerToDimension(dimension, manager)
    }
    val worldServer = manager.serverInstance.worldServerForDimension(this.dimension)
    this.dimension = dimension
    val worldServer1 = manager.serverInstance.worldServerForDimension(this.dimension)
    worldServer.removePlayerEntityDangerously(this)
    riddenByEntity?.mountEntity(null)

    if (ridingEntity != null) {
        mountEntity(null)
    }

    isDead = false
    this.transferToWorld(worldServer, worldServer1)
}

fun Entity.transferToWorld(oldWorld: WorldServer, newWorld: WorldServer) {
    val pOld = oldWorld.provider
    val pNew = newWorld.provider
    val moveFactor = pOld.movementFactor / pNew.movementFactor
    var x = posX * moveFactor
    var z = posZ * moveFactor
    oldWorld.theProfiler.startSection("placing")
    x = MathHelper.clamp_double(x, -29999872.0, 29999872.0)
    z = MathHelper.clamp_double(z, -29999872.0, 29999872.0)

    if (isEntityAlive) {
        setLocationAndAngles(x, posY, z, rotationYaw, rotationPitch)
        newWorld.spawnEntityInWorld(this)
        newWorld.updateEntityWithOptionalForce(this, false)
    }

    oldWorld.theProfiler.endSection()
    setWorld(newWorld)
}

fun EntityPlayerMP.transferPlayerToDimension(dimension: Int, manager: ServerConfigurationManager) {
    val oldDim = this.dimension
    val worldServer = manager.serverInstance.worldServerForDimension(this.dimension)
    this.dimension = dimension
    val worldServer1 = manager.serverInstance.worldServerForDimension(this.dimension)
    playerNetServerHandler.sendPacket(S07PacketRespawn(this.dimension, worldObj.difficultySetting, worldObj.worldInfo.terrainType, theItemInWorldManager.gameType))
    worldServer.removePlayerEntityDangerously(this)

    riddenByEntity?.mountEntity(null)

    if (ridingEntity != null) {
        mountEntity(null)
    }

    isDead = false
    transferToWorld(worldServer, worldServer1)
    manager.func_72375_a(this, worldServer)
    playerNetServerHandler.setPlayerLocation(posX, posY, posZ, rotationYaw, rotationPitch)
    theItemInWorldManager.setWorld(worldServer1)
    manager.updateTimeAndWeatherForPlayer(this, worldServer1)
    manager.syncPlayerInventory(this)

    for (potionEffect in activePotionEffects) {
        if (potionEffect is PotionEffect) {
            playerNetServerHandler.sendPacket(S1DPacketEntityEffect(entityId, potionEffect))
        }
    }

    FMLCommonHandler.instance().firePlayerChangedDimensionEvent(this, oldDim, dimension)
}

fun Entity.transferToBlock(pos: BlockPosition) {
    this.setPosition(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble())
}

fun Entity.transferAtBlock(pos: BlockPosition) {
    if (this.worldObj == null) {
        return
    }

    for (dx in -1..1) {
        for (dz in -1..1) {
            if (World.doesBlockHaveSolidTopSurface(this.worldObj, pos.x + dx, pos.y - 1, pos.z + dz)
                    && !this.worldObj.getBlock( pos.x + dx, pos.y, pos.z + dz).material.isOpaque
                    && !this.worldObj.getBlock( pos.x + dx, pos.y + 1, pos.z + dz).material.isOpaque) {

                this.setPosition(pos.x + dx.toDouble(), pos.y.toDouble(), pos.z + dz.toDouble())
                return
            }
        }
    }
}

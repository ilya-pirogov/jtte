package info.devels.jtte.entities

import cofh.core.block.TileCoFHBase
import cofh.lib.util.position.BlockPosition
import info.devels.api.extentions.blockPosition
import info.devels.api.extentions.breakBlock
import info.devels.api.extentions.markForUpdate
import info.devels.api.extentions.onServer
import info.devels.jtte.JTTE
import info.devels.jtte.blocks.BlockBeacon
import info.devels.jtte.core.*
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.server.MinecraftServer
import net.minecraft.world.ChunkCoordIntPair
import net.minecraftforge.common.ForgeChunkManager


class TileEntityBeacon : TileCoFHBase() {
//    var ticket: ForgeChunkManager.Ticket? = null
//            by lazy {
//        ForgeChunkManager.requestTicket(JTTE.instance, MinecraftServer.getServer().worldServerForDimension(0), ForgeChunkManager.Type.NORMAL)
//    }

    var rtCube = Math.PI * 2 / ticksCube
    var rtBottomPieces = Math.PI * 2 / ticksBottomPieces
    var rtMiddlePieces = Math.PI * 2 / ticksMiddlePieces
    var rtTopPieces = Math.PI * 2 / ticksTopPieces

    var angleCube = 0f
    var angleBottomPieces = 0f
    var angleMiddlePieces = 0f
    var angleTopPieces = 0f

    override fun updateEntity() {
        angleCube += rtCube.toFloat()
        if (angleCube > Math.PI) {
            angleCube -= (2 * Math.PI).toFloat()
        }

        angleBottomPieces += rtBottomPieces.toFloat()
        if (angleBottomPieces > Math.PI) {
            angleBottomPieces -= (2 * Math.PI).toFloat()
        }

        angleMiddlePieces += rtMiddlePieces.toFloat()
        if (angleMiddlePieces > Math.PI) {
            angleMiddlePieces -= (2 * Math.PI).toFloat()
        }

        angleTopPieces += rtTopPieces.toFloat()
        if (angleTopPieces > Math.PI) {
            angleTopPieces -= (2 * Math.PI).toFloat()
        }
    }

    override fun getName(): String {
        return TileEntityBeacon::class.simpleName ?: TileEntityBeacon::class.toString()
    }

    override fun validate() {
        super.validate()

        activate()
    }

    override fun invalidate() {
        super.invalidate()

        deactivate();
    }

    private fun activate() {
        worldObj.onServer {
            beacons.putIfAbsent(blockPosition(), this)
//            if (ticket == null) {
//                ticket = ForgeChunkManager.requestTicket(JTTE.instance, worldObj, ForgeChunkManager.Type.NORMAL)
//            }

//            ForgeChunkManager.forceChunk(ticket, ChunkCoordIntPair(xCoord / 16, zCoord / 16))
        }
        markForUpdate()
    }

    private fun deactivate() {
        worldObj.onServer {
//            ForgeChunkManager.unforceChunk(ticket, ChunkCoordIntPair(xCoord / 16, zCoord / 16))
//            if (ticket != null) {
//                ForgeChunkManager.releaseTicket(ticket)
//                ticket = null
//            }

            beacons.remove(blockPosition())
        }
        markForUpdate()
    }

    override fun blockPlaced() {
        worldObj.onServer {
            for (pos in beacons.keys.toList()) {
                if (pos != blockPosition()) {
                    worldObj.breakBlock(pos)
                }
            }
        }

        activate()
    }

    override fun blockBroken() {
        deactivate()
    }

    override fun getType() = 0

    companion object {
        val beacons = hashMapOf<BlockPosition, TileEntityBeacon>()
    }
}

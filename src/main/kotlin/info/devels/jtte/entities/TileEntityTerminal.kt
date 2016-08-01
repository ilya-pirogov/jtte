package info.devels.jtte.entities

import cofh.api.tileentity.IReconfigurableFacing
import cofh.core.block.TileCoFHBase
import com.mojang.authlib.GameProfile
import info.devels.api.extentions.markForUpdate
import info.devels.jtte.core.ticksCube
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTUtil
import net.minecraft.network.NetworkManager
import net.minecraft.network.Packet
import net.minecraft.network.play.server.S35PacketUpdateTileEntity
import net.minecraftforge.common.util.ForgeDirection
import java.util.*


class TileEntityTerminal : TileCoFHBase(), IReconfigurableFacing {
    var usesBy: GameProfile? = null

    var rtHead = Math.PI * 2 / ticksCube
    var angleHead = 0f

    var state = State.NOT_INITIALIZED
    var face = ForgeDirection.UNKNOWN

    var ticks = 0
    var nOffset = 0
    var screenOffsetX = 0
    var isMoving = false
    val speed = 2

    override fun updateEntity() {
        angleHead += rtHead.toFloat()
        if (angleHead > Math.PI) {
            angleHead -= (2 * Math.PI).toFloat()
        }

        ticks++
        nOffset = ticks % speed
        if (nOffset != 0) {
            return
        }

        if (screenOffsetX > 0 && !isMoving && ticks % 80 != 0) {
            return
        }

        when(screenOffsetX) {
            0 -> { screenOffsetX = 33; isMoving = true }
            33 -> { screenOffsetX = 63; isMoving = true }
            63 -> { screenOffsetX = 93; isMoving = true }
            111 -> { screenOffsetX = 143; isMoving = true }
            151 -> { screenOffsetX = 183; isMoving = true }
            199 -> { screenOffsetX = 231; isMoving = true }
            246 -> { screenOffsetX = 278; isMoving = true }
            286 -> { screenOffsetX = 318; isMoving = true }
            342 -> { screenOffsetX = 373; isMoving = false }
            373 -> { screenOffsetX = 404; isMoving = false }
            404 -> { screenOffsetX = 435; isMoving = false }
            435 -> { screenOffsetX = 466; isMoving = false }
            466 -> { screenOffsetX = 0; isMoving = false }

            else -> screenOffsetX += 1
        }
    }

    override fun setFacing(face: Int): Boolean {
        this.face = ForgeDirection.getOrientation(face)
        if (this.face == ForgeDirection.UP || this.face == ForgeDirection.DOWN) {
            this.face = ForgeDirection.NORTH
        }
        return true
    }

    override fun getFacing(): Int {
        return face.ordinal
    }

    override fun rotateBlock(): Boolean {
        face = face.getRotation(ForgeDirection.EAST)
        return true
    }

    override fun allowYAxisFacing(): Boolean {
        return true
    }

    fun getBottom(): TileEntityTerminal? {
        when (state) {
            State.BOTTOM -> return this
            State.MIDDLE -> return worldObj.getTileEntity(xCoord, yCoord -1, zCoord) as TileEntityTerminal?
            State.TOP -> return  worldObj.getTileEntity(xCoord, yCoord -2, zCoord) as TileEntityTerminal?
            State.NOT_INITIALIZED -> return null
        }
    }

    override fun blockPlaced() {
        var struct = ArrayList<TileEntityTerminal>()

        for (dy in -2..2) {
            val tile = worldObj.getTileEntity(xCoord, yCoord + dy, zCoord)
            if (tile != null && tile is TileEntityTerminal && tile.state == State.NOT_INITIALIZED) {
                struct.add(tile)
            } else if (struct.size < 3) {
                struct.clear()
            } else {
                break
            }
        }

        if (struct.size >= 3) {
            struct[0].state = State.BOTTOM
            struct[1].state = State.MIDDLE
            struct[2].state = State.TOP

            for (tile in struct) {
                tile.face = face

                tile.markDirty()
                tile.markForUpdate()
            }
        }
    }

    override fun blockBroken() {
        when (state) {
            State.BOTTOM -> disable(0, 1, 2)
            State.MIDDLE -> disable(-1, 0, 1)
            State.TOP -> disable(-2, -1, 0)
            State.NOT_INITIALIZED -> {}
        }
    }

    fun disable(vararg dyList: Int) {
        for (dy in dyList) {
            val tile = worldObj.getTileEntity(xCoord, yCoord + dy, zCoord)
            if (tile != null && tile is TileEntityTerminal) {
                tile.state = State.NOT_INITIALIZED
                tile.markDirty()
                tile.markForUpdate()
            }
        }
    }

    override fun getName(): String {
        return TileEntityTerminal::class.simpleName ?: TileEntityTerminal::class.toString()
    }

    fun readState(tag: NBTTagCompound) {
        state = State.valueOf(tag.getString("State") ?: State.NOT_INITIALIZED.name)
        face = ForgeDirection.valueOf(tag.getString("Face") ?: ForgeDirection.UNKNOWN.name)
        usesBy = NBTUtil.func_152459_a(tag.getCompoundTag("UsesBy"))
    }

    fun writeState(tag: NBTTagCompound) {
        tag.setString("State", state.name)
        tag.setString("Face", face.name)
        if (usesBy != null ){
            val usedByTag = NBTTagCompound()
            NBTUtil.func_152460_a(usedByTag, this.usesBy)
            tag.setTag("UsesBy", usedByTag)
        }
    }

    override fun writeToNBT(tag: NBTTagCompound) {
        super.writeToNBT(tag)
        writeState(tag)
    }

    override fun readFromNBT(tag: NBTTagCompound) {
        super.readFromNBT(tag)
        readState(tag)
    }

    override fun getDescriptionPacket(): Packet? {
        val tag = NBTTagCompound()
        writeState(tag)
        return S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag)
    }

    override fun onDataPacket(net: NetworkManager, pkt: S35PacketUpdateTileEntity) {
        readState(pkt.func_148857_g())
    }

    override fun getType() = 0

    enum class State {
        NOT_INITIALIZED,
        BOTTOM,
        MIDDLE,
        TOP
    }
}

@file:Suppress("unused")

package info.devels.api.extentions

import cofh.lib.util.position.BlockPosition
import info.devels.api.entity.HasDirection
import net.minecraft.tileentity.TileEntity


fun TileEntity.blockPosition(): BlockPosition {
    if (this is HasDirection) {
        return BlockPosition(xCoord, yCoord, zCoord, direction)
    }
    return BlockPosition(xCoord, yCoord, zCoord)
}

fun TileEntity.markForUpdate() {
    worldObj.markBlockForUpdate(xCoord, yCoord, zCoord)
}
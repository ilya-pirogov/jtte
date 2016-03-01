package info.devels.api.extentions

import cofh.lib.util.position.BlockPosition
import net.minecraft.util.ChunkCoordinates


fun ChunkCoordinates.blockPosition(): BlockPosition {
    return BlockPosition(posX, posY, posZ)
}

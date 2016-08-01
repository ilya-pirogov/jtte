package info.devels.api.extentions

import cofh.lib.util.position.ChunkCoord
import net.minecraft.world.chunk.Chunk


fun Chunk.position(): ChunkCoord {
    return ChunkCoord(this);
}
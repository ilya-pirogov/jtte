package info.devels.jtte.curse

import cofh.lib.util.position.ChunkCoord
import info.devels.jtte.core.curseRadius
import info.devels.jtte.utils.profile
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.chunk.Chunk


class CurseMap {
    val planned = mutableMapOf<ChunkCoord, CurseChunkMap>()
    val affected = mutableMapOf<ChunkCoord, CurseChunkMap>()

    fun handleMove(player: EntityPlayer) {
        profile("handleMove %.1f %.1f %.1f".format(player.posX, player.posY, player.posZ)) {
            val minX = (player.posX - curseRadius).toInt()
            val minZ = (player.posZ - curseRadius).toInt()
            val maxX = (player.posX + curseRadius).toInt()
            val maxZ = (player.posZ + curseRadius).toInt()

            var x = minX
            while (x < maxX) {
                val x1 = x
                val x2 = Math.min(maxX, x1 + 15 - (x1 absMod 16))

                var z = minZ
                while (z < maxZ) {
                    val z1 = z
                    val z2 = Math.min(maxZ, z1 + 15 - (z1 absMod 16))

                    val chX = x shr 4
                    val chZ = z shr 4

                    val dx1 = x1 absMod 16
                    val dx2 = x2 absMod 16
                    val dz1 = z1 absMod 16
                    val dz2 = z2 absMod 16

                    val map = planned.getOrPut(ChunkCoord(chX, chZ)) { CurseChunkMap() }

                    if (map.markBits(dx1, dz1, dx2, dz2, chX, chZ)) {
                        println("handle chunk %d,%d: %d,%d - %d,%d".format(chX, chZ, dx1, dz1, dx2, dz2))
//                        player.worldObj.getChunkFromChunkCoords(chX, chZ).setChunkModified()
                    }

                    z = z2 + 1
                }
                x = x2 + 1
            }
        }
    }

    fun isCureBlock(x: Int, z: Int): Boolean {
        val chX = x shr 4
        val chZ = z shr 4
        val map = planned[ChunkCoord(chX, chZ)]
        if (map != null) {
            return map.test(x absMod 16, z absMod 16)
        }
        return false
    }
}

package info.devels.jtte.curse

import cofh.lib.util.position.BlockPosition
import cofh.lib.util.position.ChunkCoord
import info.devels.jtte.blocks.blockCursedDirt
import info.devels.jtte.common.BlockChunkPosition
import info.devels.jtte.core.curseRadius
import info.devels.jtte.core.cursedBlocksWhitelist
import info.devels.jtte.utils.profile
import net.minecraft.block.Block
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.potion.Potion
import net.minecraft.potion.PotionEffect
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraft.world.WorldServer
import net.minecraft.world.chunk.Chunk


typealias ProcessCallback = (dx: Int, dz: Int) -> Unit


object CurseMap {
    val planned = mutableMapOf<ChunkCoord, CurseChunkMap>()
    val affected = mutableMapOf<ChunkCoord, CurseChunkMap>()

//    fun handleMove(player: EntityPlayer) {
////        profile("handleMove %.1f %.1f %.1f".format(player.posX, player.posY, player.posZ)) {
//            val minX = (player.posX - curseRadius).toInt()
//            val minZ = (player.posZ - curseRadius).toInt()
//            val maxX = (player.posX + curseRadius).toInt()
//            val maxZ = (player.posZ + curseRadius).toInt()
//
//            var x = minX
//            while (x < maxX) {
//                val x1 = x
//                val x2 = Math.min(maxX, x1 + 15 - (x1 absMod 16))
//
//                var z = minZ
//                while (z < maxZ) {
//                    val z1 = z
//                    val z2 = Math.min(maxZ, z1 + 15 - (z1 absMod 16))
//
//                    val chX = x shr 4
//                    val chZ = z shr 4
//
//                    val dx1 = x1 absMod 16
//                    val dx2 = x2 absMod 16
//                    val dz1 = z1 absMod 16
//                    val dz2 = z2 absMod 16
//
//                    val map = planned.getOrPut(ChunkCoord(chX, chZ)) { CurseChunkMap() }
//
//                    map.bitSet[dx1 + dz1 * 16] = true
//                    if (map.markBits(dx1, dz1, dx2, dz2, chX, chZ)) {
//                        println("handle chunk %d,%d: %d,%d - %d,%d".format(chX, chZ, dx1, dz1, dx2, dz2))
////                        player.worldObj.getChunkFromChunkCoords(chX, chZ).setChunkModified()
//                    }
//
//                    z = z2 + 1
//                }
//                x = x2 + 1
//            }
////        }
//    }

    fun handleMove(player: EntityPlayer) {
//        profile("handleMove %.1f %.1f %.1f".format(player.posX, player.posY, player.posZ)) {
        val minX = (player.posX - curseRadius).toInt()
        val minZ = (player.posZ - curseRadius).toInt()
        val maxX = (player.posX + curseRadius).toInt()
        val maxZ = (player.posZ + curseRadius).toInt()


        for (x in minX..maxX) {
            for (z in minZ..maxZ) {
                val chX = x shr 4
                val chZ = z shr 4
                val dx = x absMod 16
                val dz = z absMod 16

                val map = planned.getOrPut(ChunkCoord(chX, chZ)) { CurseChunkMap() }

                map.bitSet[dx + dz * 16] = true
            }
        }
    }

    fun isPlannedCureBlock(x: Int, z: Int): Boolean {
        val chX = x shr 4
        val chZ = z shr 4
        val map = planned[ChunkCoord(chX, chZ)]
        if (map != null) {
            return map.test(x absMod 16, z absMod 16)
        }
        return false
    }


    fun isAffectedCureBlock(x: Int, z: Int): Boolean {
        val chX = x shr 4
        val chZ = z shr 4
        val map = affected[ChunkCoord(chX, chZ)]
        if (map != null) {
            return map.test(x absMod 16, z absMod 16)
        }
        return false
    }

    fun getChangedBlocks(): List<BlockChunkPosition> {
        val result = mutableListOf<BlockChunkPosition>()

        for ((chunk, planned) in this.planned) {
            val affected = this.affected[chunk]

            if (affected != null) {
                if (planned.bitSet == affected.bitSet) {
                    continue
                }

                val changes = planned.bitSet xand affected.bitSet
                if (changes > 0) {
                    for (i in changes.getEnabledBits()) {
                        val x = chunk.chunkX * 16 + (i % 16)
                        val z = chunk.chunkZ * 16 + (i / 16)

                        result.add(BlockChunkPosition(x, z))
                    }
                }
            } else {
                for (i in planned.bitSet.getEnabledBits()) {
                    val x = chunk.chunkX * 16 + (i % 16)
                    val z = chunk.chunkZ * 16 + (i / 16)

                    result.add(BlockChunkPosition(x, z))
                }
            }
        }
        return result
    }

    fun handleCursedBlocks(world: World, callback: ProcessCallback? = null) {
        for ((x, z) in CurseMap.getChangedBlocks()) {
            if (callback != null) {
                callback(x, z)
            }

            var y = world.getHeightValue(x, z) + 1
            var block: Block
            do {
                y--
                block = world.getBlock(x, y, z)
            } while (!cursedBlocksWhitelist.contains(block.unlocalizedName) && y > 1)

            if (y === 1) {
                continue
            }

            world.setBlock(x, y, z, blockCursedDirt)
            world.notifyBlockChange(x, y, z, block)

            val chunk = ChunkCoord(x shr 4, z shr 4)
            val dx = x absMod 16
            val dz = z absMod 16
            val map = affected[chunk]
            if (map !== null) {
                map.bitSet[dx + dz * 16] = true
            }
        }
    }

    fun handleCurse(player: EntityPlayer) {
        if (isAffectedCureBlock(player.posX.toInt(), player.posZ.toInt())) {
            player.addPotionEffect(PotionEffect(Potion.weakness.id, 120))
        }
    }
}

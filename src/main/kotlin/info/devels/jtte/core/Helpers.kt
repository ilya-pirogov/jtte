package info.devels.jtte.core

import cofh.lib.util.position.Area
import cofh.lib.util.position.BlockPosition
import info.devels.api.extentions.blockPosition
import info.devels.jtte.JTTE
import net.minecraft.server.MinecraftServer
import net.minecraft.world.World


val spawnPosition: BlockPosition
    get() = MinecraftServer.getServer().worldServerForDimension(0).spawnPoint.blockPosition()


fun String.toBlockPosition(): BlockPosition {
    val parts = trim().split(Regex("\\s+"))
    if (parts.size != 3) {
        JTTE.log.error("Wrong block position: %s".format(this))
        return BlockPosition(0, 64, 0)
    }

    val args = parts.map(String::toInt)
    return BlockPosition(args[0], args[1], args[2])
}

fun String.toArea(): Area {
    val parts = trim().split(Regex("\\s+"))
    if (parts.size != 6) {
        JTTE.log.error("Wrong area: %s".format(this))
        return Area(0, 0, 64, 64, 0, 0)
    }

    val args = parts.map(String::toInt)
    return Area(args[0], args[3], args[1], args[4], args[2], args[5])
}

fun BlockPosition.toConfig(): String {
    return "%d %d %d".format(x, y, z)
}

fun Area.toConfig(): String {
    return "%d %d %d   %d %d %d".format(xMin, yMin, zMin, xMax, yMax, zMax)
}

fun World.getClockTime(): Long {
    return (this.worldTime + (dayLength / 4)) % dayLength
}
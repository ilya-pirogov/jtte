package info.devels.jtte.core

import cofh.lib.util.position.BlockPosition
import info.devels.api.extentions.blockPosition
import net.minecraft.server.MinecraftServer


val spawnPosition: BlockPosition
    get() = MinecraftServer.getServer().worldServerForDimension(0).spawnPoint.blockPosition()
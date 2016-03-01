@file:Suppress("unused")

package info.devels.api.extentions

import cofh.lib.util.position.BlockPosition
import net.minecraft.world.World


fun World.breakBlock(position: BlockPosition): Boolean {
    return this.setBlockToAir(position.x, position.y, position.z)
}

inline fun World.onServer(run: () -> Unit) {
    if (!isRemote) {
        run()
    }
}

inline fun World.onClient(run: () -> Unit) {
    if (isRemote) {
        run()
    }
}
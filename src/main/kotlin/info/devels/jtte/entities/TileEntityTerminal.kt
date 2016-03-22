package info.devels.jtte.entities

import cofh.core.block.TileCoFHBase
import info.devels.jtte.core.ticksBottomPieces
import info.devels.jtte.core.ticksCube
import info.devels.jtte.core.ticksMiddlePieces
import info.devels.jtte.core.ticksTopPieces
import net.minecraft.entity.player.EntityPlayerMP


class TileEntityTerminal : TileCoFHBase() {
    var usedBy: EntityPlayerMP? = null

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
        return TileEntityTerminal::class.simpleName ?: TileEntityTerminal::class.toString()
    }

    override fun getType() = 0
}

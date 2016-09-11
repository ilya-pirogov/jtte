@file:Suppress("unused")

package info.devels.jtte.core

import cofh.CoFHCore
import cofh.lib.util.position.Area
import cofh.lib.util.position.BlockPosition
import cofh.lib.util.position.ChunkCoord
import kotlin.reflect.KProperty


const val modId = "jtte"
const val modName = "Journey to the End"
const val version = "0.1.8"
const val version_max = "0.2.0"
const val dependencies = CoFHCore.version_group
const val version_group = "required-after:$modId@[$version,$version_max);"

var ticksCube = 160
var ticksBottomPieces = 90
var ticksMiddlePieces = 60
var ticksTopPieces = 40
var curseDimension = 0
var curseRadius = 24
var dayLength = 24000
var curseStartTime = 7000
var curseEndTime = 8000
var terminalDimension = 0
var cursedBlocksWhitelist = arrayOf("tile.stone", "tile.dirt", "tile.grass", "tile.cobblestone", "tile.sand", "tile.gravel", "tile.farmland", "tile.snow", "tile.clay", "tile.netherrack", "tile.soul_sand")
var clockReturnPoint = BlockPosition(1850, 48, 2149)
var curseFreeZones = arrayOf(Area(clockReturnPoint.x - 16, clockReturnPoint.x + 16, 0, 255, clockReturnPoint.z - 16, clockReturnPoint.z + 16))

var dbgTrX by DebugSetter(0.0)
var dbgTrY by DebugSetter(0.0)
var dbgTrZ by DebugSetter(0.0)

var dbgRotX by DebugSetter(0.0)
var dbgRotY by DebugSetter(0.0)
var dbgRotZ by DebugSetter(0.0)

var dbgScale by DebugSetter(1.0)

fun Double.format(digits: Int) = java.lang.String.format("%.${digits}f", this)

/**
 *

GL11.glScalef(dbgScale.toFloat(), dbgScale.toFloat(), dbgScale.toFloat())
GL11.glTranslatef(dbgTrX.toFloat(), dbgTrY.toFloat(), dbgTrZ.toFloat())
GL11.glRotatef(dbgRotX.toFloat(), 1f, 0f, 0f)
GL11.glRotatef(dbgRotY.toFloat(), 0f, 1f, 0f)
GL11.glRotatef(dbgRotZ.toFloat(), 0f, 0f, 1f)

 *
 */
private class DebugSetter(var value: Double = 0.0) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Double {
        return value
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Double) {
        this.value = value;

        println("""
                GL11.glScalef(${dbgScale.format(2)}f, ${dbgScale.format(2)}f, ${dbgScale.format(2)}f)
                GL11.glTranslatef(${dbgTrX.format(2)}f, ${dbgTrY.format(2)}f, ${dbgTrZ.format(2)}f)
                GL11.glRotatef(${dbgRotX.format(2)}f, 1f, 0f, 0f)
                GL11.glRotatef(${dbgRotY.format(2)}f, 0f, 1f, 0f)
                GL11.glRotatef(${dbgRotZ.format(2)}f, 0f, 0f, 1f)
        """)
//        println("X:$dbgTrX Y:$dbgTrY Z:$dbgTrZ; rotX:$dbgRotX rotY:$dbgRotY rotZ:$dbgRotZ; Scale:$dbgScale")
    }
}

@file:Suppress("unused")

package info.devels.jtte.core

import cofh.CoFHCore
//import kotlin.reflect.KProperty


const val modId = "jtte"
const val modName = "Journey to the End"
const val version = "0.1.4b"
const val version_max = "0.2.0"
const val dependencies = CoFHCore.version_group
const val version_group = "required-after:$modId@[$version,$version_max);"

var ticksCube = 160
var ticksBottomPieces = 90
var ticksMiddlePieces = 60
var ticksTopPieces = 40

//var dbgTrX by DebugSetter(0f)
//var dbgTrY by DebugSetter(0f)
//var dbgTrZ by DebugSetter(0f)
//
//var dbgRotX by DebugSetter(0f)
//var dbgRotY by DebugSetter(0f)
//var dbgRotZ by DebugSetter(0f)
//
//var dbgScale by DebugSetter(1f)
//
//
//private class DebugSetter(var value: Float = 0f) {
//    operator fun getValue(thisRef: Any?, property: KProperty<*>): Float {
//        return value
//    }
//
//    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Float) {
//        this.value = value;
//        println("X:$dbgTrX Y:$dbgTrY Z:$dbgTrZ; rotX:$dbgRotX rotY:$dbgRotY rotZ:$dbgRotZ; Scale:$dbgScale")
//    }
//}

@file:Suppress("unused")

package info.devels.jtte.core

import cofh.CoFHCore


const val modId = "jtte"
const val modName = "Journey to the End"
const val version = "0.1.3"
const val version_max = "0.2.0"
const val dependencies = CoFHCore.version_group
const val version_group = "required-after:$modId@[$version,$version_max);"

var ticksCube = 160
var ticksBottomPieces = 90
var ticksMiddlePieces = 60
var ticksTopPieces = 40

var dbgTrX = 0f
    set(value) {
        notify()
        field = value
    }

var dbgTrY = 0f
    set(value) {
        notify()
        field = value
    }

var dbgTrZ = 0f
    set(value) {
        notify()
        field = value
    }

var dbgRotX = 0f
    set(value) {
        notify()
        field = value
    }

var dbgRotY = 0f
    set(value) {
        notify()
        field = value
    }

var dbgRotZ = 0f
    set(value) {
        notify()
        field = value
    }

var dbgScale = 1f
    set(value) {
        notify()
        field = value
    }

fun notify() {
    println("X:$dbgTrX Y:$dbgTrY Z:$dbgTrZ; rotX:$dbgRotX rotY:$dbgRotY rotZ:$dbgRotZ; Scale:$dbgScale")
}

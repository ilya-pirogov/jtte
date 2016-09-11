package info.devels.jtte.curse

import cpw.mods.fml.common.eventhandler.SubscribeEvent
import cpw.mods.fml.common.gameevent.TickEvent
import cpw.mods.fml.relauncher.Side
import info.devels.api.extentions.position
import info.devels.jtte.core.curseDimension
import info.devels.jtte.core.curseEndTime
import info.devels.jtte.core.curseStartTime
import info.devels.jtte.core.getClockTime
import net.minecraftforge.event.world.ChunkDataEvent


class Handlers {
    var playerTick = 0
    var isCurseHandledToday = false

    @SubscribeEvent
    fun onPlayerTick(event: TickEvent.PlayerTickEvent) {
        if (event.side != Side.SERVER || event.player.dimension != curseDimension) {
            return
        }

        if (playerTick++ % 61 == 0) {
            CurseMap.handleMove(event.player)
            CurseMap.handleCurse(event.player)
        }
    }

    @SubscribeEvent
    fun onChunkLoad(event: ChunkDataEvent.Load) {
        CurseMap.planned.fromRaw(event.chunk.position(), event.data.getIntArray("plannedCurseMap"))
        CurseMap.affected.fromRaw(event.chunk.position(), event.data.getIntArray("affectedCurseMap"))
    }

    @SubscribeEvent
    fun onChunkSave(event: ChunkDataEvent.Save) {
        val a = CurseMap.planned.getRaw(event.chunk.position())
        if (!(CurseMap.planned[event.chunk.position()] ?: CurseChunkMap()).bitSet.isEmpty) {
            println("save the chunk %s: %s".format(event.chunk.position(), a.dump()))
        }
        event.data.setIntArray("plannedCurseMap", CurseMap.planned.getRaw(event.chunk.position()))
        event.data.setIntArray("affectedCurseMap", CurseMap.affected.getRaw(event.chunk.position()))
    }

    @SubscribeEvent
    fun onWorldTick(event: TickEvent.WorldTickEvent) {
        val time = event.world.getClockTime()
        if (!isCurseHandledToday && time > curseStartTime && time < curseEndTime) {
            isCurseHandledToday = true

            CurseMap.handleCursedBlocks(event.world)
        }

        if (time > curseEndTime) {
            isCurseHandledToday = false
        }
    }
}
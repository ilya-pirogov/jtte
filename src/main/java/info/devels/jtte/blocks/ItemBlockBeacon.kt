package info.devels.jtte.blocks

import net.minecraft.block.Block
import net.minecraft.item.ItemBlock


class ItemBlockBeacon(block: Block) : ItemBlock(block) {
    init {
        maxDamage = 0
    }
}

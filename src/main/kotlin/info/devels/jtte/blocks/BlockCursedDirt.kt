package info.devels.jtte.blocks

import info.devels.jtte.JTTE
import net.minecraft.block.Block
import net.minecraft.block.material.Material

class BlockCursedDirt() : Block(Material.grass) {
    init {
//        setHardness(5.0f)
//        setResistance(100f)
//        setStepSound(Block.soundTypeStone)
        setCreativeTab(JTTE.tab)
        setBlockTextureName("jtte:cursed_dirt")
        setBlockName("jtte.cursedDirt")

//        setBlockBounds(0f, 0f, 0f, 1f, 1.8f, 1f)
    }

}
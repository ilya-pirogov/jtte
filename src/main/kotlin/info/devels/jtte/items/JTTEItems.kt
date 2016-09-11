package info.devels.jtte.items

import info.devels.jtte.JTTE
import net.minecraft.item.ItemStack


val itemClock = ItemClock()
val itemCrystal = ItemCrystal()

fun itemsPreInit() {
    itemClock.preInit()
    itemCrystal.preInit()
}

fun itemsInitialize() {

}

fun itemsPostInit() {
    itemClock.postInit()
}

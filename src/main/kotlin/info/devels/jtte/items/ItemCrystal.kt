package info.devels.jtte.items

import cofh.api.core.IInitializer
import cpw.mods.fml.common.registry.GameRegistry
import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly
import info.devels.jtte.JTTE
import info.devels.jtte.core.modId
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.IIcon
import net.minecraft.util.MathHelper


class ItemCrystal: Item(), IInitializer {
    val crystals = arrayOf("crystal1", "crystal2", "crystal3", "crystal4", "crystal5", "crystal6", "crystal7")
    private lateinit var icons: Array<IIcon>

    init {
        hasSubtypes = true
//        maxDamage = 0
//        maxStackSize = 1
        creativeTab = JTTE.tab
//        unlocalizedName = "crystal1"
//        setTextureName("%s:%s".format(JTTE.instance.modId, "crystal1"))
    }

    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    override fun getUnlocalizedName(item: ItemStack): String {
        val i = MathHelper.clamp_int(item.itemDamage, 0, crystals.size)
//        println(super.getUnlocalizedName() + "." + crystals[i])
        return "item.${crystals[i]}"
    }

    override fun preInit(): Boolean {
        GameRegistry.registerItem(this, "crystal")
        return true
    }

    @SideOnly(Side.CLIENT)
    override fun getIconFromDamage(meta: Int): IIcon {
        if (meta > crystals.size) {
            return icons[0]
        }
        return icons[meta]
    }

    override fun getSubItems(item: Item, tab: CreativeTabs?, list: MutableList<Any?>) {
        for (i in 0..itemCrystal.crystals.size - 1) {
            list.add(ItemStack(itemCrystal, 1, i))
        }
    }

    @SideOnly(Side.CLIENT)
    override fun registerIcons(ir: IIconRegister) {
        val icons = mutableListOf<IIcon>()
        for (i in crystals.indices) {
            icons.add(ir.registerIcon("%s:%s%d".format(modId, "crystal", i)))
        }

        this.icons = icons.toTypedArray()
    }

    override fun postInit(): Boolean {
        return true
    }

    override fun initialize(): Boolean {
        return true
    }
}

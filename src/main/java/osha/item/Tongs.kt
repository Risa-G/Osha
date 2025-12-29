package osha.item

import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.IIcon
import osha.Osha

class Tongs : Item {
    constructor() {
        setMaxStackSize(1)
        setUnlocalizedName(Osha.modid + "." + "tongs")
        setCreativeTab(CreativeTabs.tabTools)
        setTextureName("tongs")
    }

    @SideOnly(Side.CLIENT)
    override fun registerIcons(register: IIconRegister?) {
        itemIcon = register?.registerIcon("osha.tongs")
    }

    override fun getIconIndex(icon: ItemStack?): IIcon? {
        return itemIcon
    }
}

package osha.item

import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.block.Block
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.item.ItemBucket
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.IIcon
import net.minecraft.util.MovingObjectPosition
import net.minecraft.world.World
import osha.ConfigureOsha
import osha.Osha
import osha.config.IConfigurable

class Tongs : Item(), IConfigurable {
    init {
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

    override fun onItemRightClick(itemStackIn: ItemStack, worldIn: World, player: EntityPlayer): ItemStack {
        val (hasBucket, place) = searchInventoryForEmptyBucket(player)
        val pos = getMovingObjectPositionFromPlayer(worldIn, player, true)
        val i = pos.blockX
        val j = pos.blockY
        val k = pos.blockZ
        val (i2, j2, k2) = adjacentBlock(i, j, k, pos)
        val block = worldIn.getBlock(i, j, k)
        val material = block.material

        if (!isFilled(itemStackIn)) {
            if (pos == null) {
                return itemStackIn
            }

            if (!hasBucket) {
                return itemStackIn
            }

            if (pos.typeOfHit != MovingObjectPosition.MovingObjectType.BLOCK) {
                return itemStackIn
            }

            if (!worldIn.canMineBlock(player, i, j, k)) {
                return itemStackIn
            }

            if (!player.canPlayerEdit(i, j, k, pos.sideHit, itemStackIn)) {
                return itemStackIn
            }

            if (!material.isLiquid) {
                return itemStackIn
            }

            if (worldIn.isRemote) {
                player.inventory.decrStackSize(place, 1)
                return itemStackIn
            }

            worldIn.setBlockToAir(i, j, k)
            val id = Block.getIdFromBlock(block)
            itemStackIn.tagCompound = NBTTagCompound()
            itemStackIn.tagCompound.setInteger("storing", id)
            return itemStackIn
        }

        if (pos == null) {
            return itemStackIn
        }

        if (!worldIn.canMineBlock(player, i, j, k)) {
            return itemStackIn
        }

        if (!player.canPlayerEdit(i, j, k, pos.sideHit, itemStackIn)) {
            return itemStackIn
        }

        if (!worldIn.isAirBlock(i, j, k) && !material.isSolid) {
            return itemStackIn
        }

        if (worldIn.isRemote) {
            returnBucket(player)
            return itemStackIn
        }

        // deserialize
        val id = itemStackIn.tagCompound.getInteger("storing")
        val deserializedBlock = Block.getBlockById(id)
        worldIn.setBlock(i2, j2, k2, deserializedBlock, 0, 3)
        itemStackIn.tagCompound = null
        return itemStackIn
    }

    private fun searchInventoryForEmptyBucket(player: EntityPlayer): Pair<Boolean, Int> {
        player.inventory.mainInventory
            .filter { it -> it != null }
            .forEachIndexed { index, itemStack ->
            if (itemStack.item is ItemBucket && itemStack.item.unlocalizedName.equals("item.bucket")) {
                return Pair(true, index)
            }
        }

        return Pair(false, -1)
    }

    private fun adjacentBlock(i: Int, j: Int, k: Int, pos: MovingObjectPosition): Triple<Int, Int, Int> {
        return when (pos.sideHit) {
            0 -> Triple(i, j - 1, k)
            1 -> Triple(i, j + 1, k)
            2 -> Triple(i , j, k - 1)
            3 -> Triple(i, j, k + 1)
            4 -> Triple(i - 1, j, k)
            else -> Triple(i + 1, j, k)
        }
    }

    private fun returnBucket(player: EntityPlayer) {
        if (!player.inventory.addItemStackToInventory(ItemStack(Items.bucket))) {
            player.dropPlayerItemWithRandomChoice(ItemStack(Items.bucket), false)
        }
    }

    private fun isFilled(stack: ItemStack): Boolean {
        return stack.tagCompound != null
    }

    override fun isEnabled(): Boolean = ConfigureOsha.enableTongs
}

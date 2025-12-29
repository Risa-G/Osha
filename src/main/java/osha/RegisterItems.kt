package osha

import cpw.mods.fml.common.registry.GameRegistry
import gregtech.api.enums.Materials
import gregtech.api.enums.OrePrefixes
import gregtech.api.util.GTModHandler
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import osha.config.IConfigurable
import osha.item.Tongs

object RegisterItems {
    val tongs = registerItem(Tongs())

    val shapedRecipeMap: HashMap<out Item, Array<out Any>> = hashMapOf(
        tongs to arrayOf(
            "I I",
            "III",
            "RIR",
            'I', OrePrefixes.plate.get(Materials.Iron),
            'R', OrePrefixes.plate.get(Materials.AnyRubber)
        )
    )

    private fun registerItem(item: Item): Item {
        if ((item !is IConfigurable) || item.isEnabled()) {
            val name = item.unlocalizedName
            val firstDot = name.lastIndexOf('.')
            GameRegistry.registerItem(item, name.substring(firstDot + 1))
        }

        return item
    }

    fun registerRecipes() {
        for ((key, value) in shapedRecipeMap) {
            GTModHandler.addCraftingRecipe(ItemStack(key), value)
        }
    }
}

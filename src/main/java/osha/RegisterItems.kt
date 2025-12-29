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
    val shapedRecipeMap: HashMap<out Item, Array<out Any>> = hashMapOf(
        Tongs() to arrayOf(
            "I I",
            "III",
            "RIR",
            'I', OrePrefixes.plate.get(Materials.Iron),
            'R', OrePrefixes.plate.get(Materials.AnyRubber)
        )
    )

    fun register()  {
        for ((item, _) in shapedRecipeMap) {
            if ((item !is IConfigurable) || item.isEnabled()) {
                GameRegistry.registerItem(item, item.unlocalizedName)
            }
        }
    }

    fun registerRecipes() {
        for ((key, value) in shapedRecipeMap) {
            GTModHandler.addCraftingRecipe(ItemStack(key), value)
        }
    }
}

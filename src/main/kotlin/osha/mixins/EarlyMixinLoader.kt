package osha.mixins

import com.gtnewhorizon.gtnhmixins.IEarlyMixinLoader
import com.gtnewhorizon.gtnhmixins.builders.IMixins
import cpw.mods.fml.relauncher.IFMLLoadingPlugin
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion

@MCVersion("1.7.10")
class EarlyMixinLoader : IFMLLoadingPlugin, IEarlyMixinLoader {
    override fun getASMTransformerClass(): Array<String?>? {
        return null
    }

    override fun getModContainerClass(): String? {
        return null
    }

    override fun getSetupClass(): String? {
        return null
    }

    override fun injectData(data: MutableMap<String?, Any?>?) {}

    override fun getAccessTransformerClass(): String? {
        return null
    }

    override fun getMixinConfig(): String {
        // rename the associated .json file by replacing the "mymodid" with your own mod ID
        // in the .json file edit the "package" and "refmap" properties to match your mod
        // also edit the "refmap" property in the "mixins.mymodid.json" file
        return "mixins.nooshaviolation.early.json"
    }

    override fun getMixins(loadedCoreMods: MutableSet<String?>?): MutableList<String?> {
        return IMixins.getEarlyMixins<Mixins?>(Mixins::class.java, loadedCoreMods)
    }
}

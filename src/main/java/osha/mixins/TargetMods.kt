package osha.mixins

import com.gtnewhorizon.gtnhmixins.builders.ITargetMod
import com.gtnewhorizon.gtnhmixins.builders.TargetModBuilder
import javax.annotation.Nonnull

enum class TargetMods : ITargetMod {
    // Read the java doc of ITargetMod and TargetModBuilder for further information
    // Add to this enum information about the mods you need to identify during runtime
    COFHCORE("cofh.asm.LoadingPlugin", "CoFHCore"),
    GT5U("gregtech.asm.GTCorePlugin", "gregtech"),  // Also matches GT6.
    GTNHLIB("GTNHLib Core", "gtnhlib");

    private val builder: TargetModBuilder

    constructor(builder: TargetModBuilder) {
        this.builder = builder
    }

    constructor(modId: String?) : this(null, modId, null)

    constructor(coreModClass: String?, modId: String?, targetClass: String? = null) {
        this.builder = TargetModBuilder().setCoreModClass(coreModClass)
            .setModId(modId)
            .setTargetClass(targetClass)
    }

    @Nonnull
    override fun getBuilder(): TargetModBuilder {
        return builder
    }
}

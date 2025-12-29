package osha.mixins;

import javax.annotation.Nonnull;

import com.gtnewhorizon.gtnhmixins.builders.ITargetMod;
import com.gtnewhorizon.gtnhmixins.builders.TargetModBuilder;

public enum TargetMods implements ITargetMod {

    // Read the java doc of ITargetMod and TargetModBuilder for further information

    // Add to this enum information about the mods you need to identify during runtime

    COFHCORE("cofh.asm.LoadingPlugin", "CoFHCore"),
    GT5U("gregtech.asm.GTCorePlugin", "gregtech"), // Also matches GT6.
    GTNHLIB("GTNHLib Core", "gtnhlib");

    private final TargetModBuilder builder;

    TargetMods(TargetModBuilder builder) {
        this.builder = builder;
    }

    TargetMods(String modId) {
        this(null, modId, null);
    }

    TargetMods(String coreModClass, String modId) {
        this(coreModClass, modId, null);
    }

    TargetMods(String coreModClass, String modId, String targetClass) {
        this.builder = new TargetModBuilder().setCoreModClass(coreModClass)
            .setModId(modId)
            .setTargetClass(targetClass);
    }

    @Nonnull
    @Override
    public TargetModBuilder getBuilder() {
        return builder;
    }
}

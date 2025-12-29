package osha.mixins.late;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import gregtech.common.pollution.Pollution;

@Mixin(value = Pollution.class, remap = false)
public abstract class MixinGregtech_PollutionDissipation {

    @ModifyConstant(method = "tickPollutionInWorld(I)V", constant = @Constant(floatValue = 0.9945f))
    private float dissipationDisable(float i) {
        return 1.0f;
    }
}

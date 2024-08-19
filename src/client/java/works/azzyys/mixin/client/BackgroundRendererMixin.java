package works.azzyys.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.CameraSubmersionType;
import net.minecraft.client.render.FogShape;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BackgroundRenderer.class)
public class BackgroundRendererMixin {

    @Inject(method = "applyFog", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderFogStart(F)V", shift = At.Shift.BEFORE))
    private static void modifyStandardFogDistance(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, float tickDelta, CallbackInfo ci, @Local BackgroundRenderer.FogData fogData, @Local Entity entity, @Local CameraSubmersionType cameraSubmersionType) {
        switch (cameraSubmersionType) {
            case LAVA -> {
                if (entity.isSpectator()) {
                    fogData.fogStart = -8.0F;
                    fogData.fogEnd = viewDistance * 0.5F;
                } else if(entity instanceof LivingEntity && ((LivingEntity)entity).hasStatusEffect(StatusEffects.FIRE_RESISTANCE)) {
                    fogData.fogStart = -6.0F;
                    fogData.fogEnd = viewDistance / 2F;
                }
                else {
                    fogData.fogStart = -6.0F;
                    fogData.fogEnd = viewDistance / 4F;
                }
            }
            case WATER -> {
                fogData.fogStart = -6.0F;
                fogData.fogEnd = viewDistance / 3F;
            }
            case POWDER_SNOW -> {
                if (entity.isSpectator()) {
                    fogData.fogStart = -8.0F;
                    fogData.fogEnd = viewDistance * 0.5F;
                } else {
                    fogData.fogStart = 0F;
                    fogData.fogEnd = 2F;
                }
            }
            case NONE -> {
                if (fogType == BackgroundRenderer.FogType.FOG_SKY) {
                    fogData.fogStart = -3.0F;
                    fogData.fogEnd = viewDistance;
                }
                else {
                    fogData.fogStart = -4.0F;
                    fogData.fogEnd = viewDistance;
                }
            }
        }
        fogData.fogShape = FogShape.SPHERE;
    }
}

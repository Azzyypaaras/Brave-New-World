package works.azzyys.mixin.client;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import works.azzyys.util.Opt;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    @WrapMethod(method = "renderHeldItemTooltip")
    protected void cancelItemTitles(DrawContext context, Operation<Void> original) {
        if (Opt.falseIfNull(MinecraftClient.getInstance().player, p -> p.getAbilities().creativeMode))
            original.call(context);
    }
}

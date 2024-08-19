package works.azzyys.mixin.client;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import org.spongepowered.asm.mixin.Mixin;
import works.azzyys.util.Opt;

@Mixin(HandledScreen.class)
public class HandledScreenMixin {

    @WrapMethod(method = "drawMouseoverTooltip")
    protected void cancelTooltips(DrawContext context, int x, int y, Operation<Void> original) {
        if (Opt.falseIfNull(MinecraftClient.getInstance().player, p -> p.getAbilities().creativeMode))
            original.call(context, x, y);
    }
}

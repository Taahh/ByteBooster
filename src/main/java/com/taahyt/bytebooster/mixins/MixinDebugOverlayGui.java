package com.taahyt.bytebooster.mixins;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.taahyt.bytebooster.ByteBooster;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.overlay.DebugOverlayGui;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

//disabled in config, this was a testing class for when i was testing mixin
// though this didn't work anyways lol

@Mixin(DebugOverlayGui.class)
public class MixinDebugOverlayGui extends AbstractGui
{
    @Final
    @Shadow
    private Minecraft minecraft;
    @Shadow
    public List<String> getGameInformation() { return Lists.newArrayList(); }

    @Final
    @Shadow
    private FontRenderer font;

    @Inject(at = @At("HEAD"), method = "Lnet/minecraft/client/gui/overlay/DebugOverlayGui;drawGameInformation(Lcom/mojang/blaze3d/matrix/MatrixStack;)V", cancellable = true)
    private void drawGameInformation(MatrixStack p_230024_1_, CallbackInfo callback) {
      List<String> list = this.getGameInformation();
      list.add("");
      boolean flag = Minecraft.getInstance().getSingleplayerServer() != null;
      list.add("Debug: Pie [shift]: " + (this.minecraft.options.renderDebugCharts ? "visible" : "hidden") + (flag ? " FPS + TPS" : " FPS") + " [alt]: " + (this.minecraft.options.renderFpsChart ? "visible" : "hidden"));
      list.add("For help: No");

      for(int i = 0; i < list.size(); ++i) {
         String s = list.get(i);
         if (!Strings.isNullOrEmpty(s)) {
            int j = 9;
            int k = this.font.width(s);
            int l = 2;
            int i1 = 2 + j * i;
            fill(p_230024_1_, 1, i1 - 1, 2 + k + 1, i1 + j - 1, -1873784752);
            this.font.draw(p_230024_1_, s, 2.0F, (float)i1, 14737632);
         }
      }

        ByteBooster.getLogger().info("HELLLLLLLLLLLLLLOOO");
      callback.cancel();
   }

}

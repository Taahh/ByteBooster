package com.taahyt.bytebooster.mixins;

import com.google.common.base.Strings;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.taahyt.bytebooster.ByteBooster;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.overlay.DebugOverlayGui;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.IServerWorldInfo;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.security.auth.callback.Callback;
import java.util.List;

//disabled in config, this was a testing class for when i was testing mixin
//this DID work
@Mixin(MinecraftServer.class)
public class MixinMinecraftServer
{

    @Inject(at = @At("HEAD"), method = "Lnet/minecraft/server/MinecraftServer;saveAllChunks(ZZZ)Z", cancellable = true)
    private void saveAllChunks(boolean p_213211_1_, boolean p_213211_2_, boolean p_213211_3_, CallbackInfoReturnable<Boolean> callback) {
        ByteBooster.getLogger().info("SAVING ALL CHUNKS LUL");
        callback.setReturnValue(true);
   }

}

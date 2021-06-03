package com.taahyt.bytebooster.mixins;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.login.server.SCustomPayloadLoginPacket;
import net.minecraft.network.play.server.SCustomPayloadPlayPacket;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

/**
 * We'll be editing the SCustomPayloadLoginPacket class from Minecraft's code here
 */
@Mixin(SCustomPayloadLoginPacket.class)
public class MixinSCustomPayloadLoginPacket {

    /**
     * Get the int variable 'transactionId' from SCustomPayloadLoginPacket
     */
    @Shadow
    private int transactionId;

    /**
     * Get the ResourceLocation variable 'identifier' from SCustomPayloadLoginPacket
     */
    @Shadow
    private ResourceLocation identifier;

    /**
     * Get the PacketBuffer variable 'data' from CCustomPayloadLoginPacket
     */
    @Shadow
    private PacketBuffer data;

    /**
     * @author Taah
     * @reason To replace the 1048576 with 2097152 bytes
     * I do realize I could've modified a constant here instead of modifying the whole thing I believe,
     * but oh well.
     */
    @Overwrite
    public void read(PacketBuffer p_148837_1_) throws IOException {
      this.transactionId = p_148837_1_.readVarInt();
      this.identifier = p_148837_1_.readResourceLocation();
      int i = p_148837_1_.readableBytes();
      if (i >= 0 && i <= 2097152) {
         this.data = new PacketBuffer(p_148837_1_.readBytes(i));
      } else {
         throw new IOException("Payload may not be larger than 2097152 bytes");
      }
   }

}

package com.taahyt.bytebooster.mixins;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.login.client.CCustomPayloadLoginPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.io.IOException;

/**
 * We'll be editing the CCustomPayloadLoginPacket class from Minecraft's code here
 */
@Mixin(CCustomPayloadLoginPacket.class)
public class MixinCCustomPayloadLoginPacket {

    /**
     * Get the int variable 'transactionId' from CCustomPayloadLoginPacket
     */
    @Shadow
    private int transactionId;

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
        if (p_148837_1_.readBoolean()) {
            int i = p_148837_1_.readableBytes();
            if (i < 0 || i > 2097152) {
                throw new IOException("Payload may not be larger than 2097152 bytes");
            }

            this.data = new PacketBuffer(p_148837_1_.readBytes(i));
        } else {
            this.data = null;
        }
    }

}

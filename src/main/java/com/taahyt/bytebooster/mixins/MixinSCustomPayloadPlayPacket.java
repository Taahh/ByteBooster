package com.taahyt.bytebooster.mixins;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SCustomPayloadPlayPacket;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

/**
 * We'll be editing the SCustomPayloadPlayPacket class from Minecraft's code here
 */
@Mixin(SCustomPayloadPlayPacket.class)
public class MixinSCustomPayloadPlayPacket
{

    /**
     * Get the ResourceLocation variable 'identifier' from SCustomPayloadPlayPacket
     */
    @Shadow
    private ResourceLocation identifier;

    /**
     * Get the PacketBuffer variable 'data' from SCustomPayloadPlayPacket
     */
    @Shadow
    private PacketBuffer data;


    /**
     * We're modifying an int value found in the original constructor of SCustomPayloadPlayPacket
     * where the int value was 1048576, and replacing it with a new value, 2097152
     */
    @ModifyConstant(method = "<init>(Lnet/minecraft/util/ResourceLocation;Lnet/minecraft/network/PacketBuffer;)V", constant = @Constant(intValue = 1048576))
    public int newValue(int original)
    {
        return 2097152;
    }

    /**
     * We're modifying a string value found in the original constructor of SCustomPayloadPlayPacket
     * where the string value was "Payload may not be larger than 1048576 bytes" but we're replacing the
     * 1048576 bytes with 2097152 bytes.
     */
    @ModifyConstant(method = "<init>(Lnet/minecraft/util/ResourceLocation;Lnet/minecraft/network/PacketBuffer;)V", constant = @Constant(stringValue = "Payload may not be larger than 1048576 bytes"))
    public String newValue(String original)
    {
        return "Payload may not be larger than 2097152 bytes";
    }

    /**
     * @author Taah
     * @reason To replace the 1048576 with 2097152 bytes
     * I do realize I could've modified a constant here instead of modifying the whole thing I believe,
     * but oh well.
     */
    @Overwrite
    public void read(PacketBuffer p_148837_1_) throws IOException {
        this.identifier = p_148837_1_.readResourceLocation();
        int i = p_148837_1_.readableBytes();
        if (i >= 0 && i <= 2097152) {
            this.data = new PacketBuffer(p_148837_1_.readBytes(i));
        } else {
            throw new IOException("Payload may not be larger than 2097152 bytes");
        }
    }

}

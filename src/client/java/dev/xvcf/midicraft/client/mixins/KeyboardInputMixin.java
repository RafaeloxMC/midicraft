package dev.xvcf.midicraft.client.mixins;

import dev.xvcf.midicraft.client.MidicraftClient;
import net.minecraft.client.player.KeyboardInput;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardInput.class)
public class KeyboardInputMixin {

    @Inject(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/client/player/KeyboardInput;keyPresses:Lnet/minecraft/world/entity/player/Input;", ordinal = 0, shift = At.Shift.AFTER, args = "", opcode = Opcodes.PUTFIELD))
    public void tick(CallbackInfo ci) {
        KeyboardInput input = (KeyboardInput) (Object) this;
        input.keyPresses = MidicraftClient.getInstance().getMidiInputProcessor().getInput();
    }
}

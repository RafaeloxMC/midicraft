package dev.xvcf.midicraft.client.util;

import dev.xvcf.midicraft.client.MidicraftClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.ClientInput;
import net.minecraft.world.entity.player.Input;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import java.util.Arrays;

public class MidiReceiver implements Receiver {
    @Override
    public void send(MidiMessage message, long timeStamp) {
        if(message instanceof ShortMessage shortMessage && (shortMessage.getCommand() == ShortMessage.NOTE_ON || shortMessage.getCommand() == ShortMessage.NOTE_OFF)) {
                handleInput(shortMessage);
        }
    }

    @Override
    public void close() {

    }

    void handleInput(ShortMessage shortMessage) {
        // Structure: [-112, NOTE_ID, VELOCITY]
        // -112 seems to be the command ID for NOTE_ON
        MidicraftClient.LOGGER.info("MIDI Message: {}", Arrays.toString(shortMessage.getMessage()));
        assert Minecraft.getInstance().player != null;
        ClientInput input = Minecraft.getInstance().player.input;
        input.keyPresses = new Input(
                true,
                input.keyPresses.backward(),
                input.keyPresses.left(),
                input.keyPresses.right(),
                input.keyPresses.jump(),
                input.keyPresses.shift(),
                input.keyPresses.sprint()
        );
    }
}

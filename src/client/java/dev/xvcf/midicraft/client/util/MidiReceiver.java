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
        // -112 is command ID for NOTE_ON; -128 is command ID for NOTE_OFF
        MidicraftClient.getInstance().getLogger().debug("MIDI Message: {}", Arrays.toString(shortMessage.getMessage()));
        if(shortMessage.getCommand() == ShortMessage.NOTE_ON) {
            MidicraftClient.getInstance().getMidiInputProcessor().addKey(shortMessage.getData1());
        }
        if(shortMessage.getCommand() == ShortMessage.NOTE_OFF) {
            MidicraftClient.getInstance().getMidiInputProcessor().removeKey(shortMessage.getData1());
        }
    }
}

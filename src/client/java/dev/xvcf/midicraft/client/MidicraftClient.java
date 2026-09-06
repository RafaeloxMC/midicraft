package dev.xvcf.midicraft.client;

import dev.xvcf.midicraft.client.util.MidiReceiver;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sound.midi.*;
import javax.sound.midi.MidiDevice.Info;

public class MidicraftClient implements ClientModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("midicraft");

    @Override
    public void onInitializeClient() {
        Info[] infos = MidiSystem.getMidiDeviceInfo();
        LOGGER.info("--------------------------");
        LOGGER.info("Found MIDI system devices:");
        for (Info info : infos) {
            try {
                MidiDevice inputDevice = MidiSystem.getMidiDevice(info);
                inputDevice.open();
                Transmitter transmitter = inputDevice.getTransmitter();
                Receiver receiver = new MidiReceiver();
                transmitter.setReceiver(receiver);
                LOGGER.info("Available {}", info.getName());
            } catch (MidiUnavailableException e) {
                LOGGER.info("Unavailable {}", info.getName());
            }

        }
        LOGGER.info("--------------------------");
    }
}

package dev.xvcf.midicraft.client;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sound.midi.MidiDevice.Info;
import javax.sound.midi.MidiSystem;

public class MidicraftClient implements ClientModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("midicraft");

    @Override
    public void onInitializeClient() {
        Info[] infos = MidiSystem.getMidiDeviceInfo();
        LOGGER.info("--------------------------");
        LOGGER.info("Found MIDI system devices:");
        for(Info info : infos) {
            LOGGER.info(info.getName());
        }
        LOGGER.info("--------------------------");
    }
}

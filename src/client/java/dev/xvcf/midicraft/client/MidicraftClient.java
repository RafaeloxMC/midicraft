package dev.xvcf.midicraft.client;

import dev.xvcf.midicraft.client.util.MidiInputProcessor;
import dev.xvcf.midicraft.client.util.MidiReceiver;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sound.midi.*;
import javax.sound.midi.MidiDevice.Info;

public class MidicraftClient implements ClientModInitializer {

    private static MidicraftClient instance;
    private final Logger logger = LoggerFactory.getLogger("midicraft");
    private MidiInputProcessor midiInputProcessor;

    @Override
    public void onInitializeClient() {
        instance = this;
        midiInputProcessor = new MidiInputProcessor();

        Info[] infos = MidiSystem.getMidiDeviceInfo();
        getLogger().info("--------------------------");
        getLogger().info("Found MIDI system devices:");
        for (Info info : infos) {
            try {
                MidiDevice inputDevice = MidiSystem.getMidiDevice(info);
                inputDevice.open();
                Transmitter transmitter = inputDevice.getTransmitter();
                Receiver receiver = new MidiReceiver();
                transmitter.setReceiver(receiver);
                getLogger().info("Available {}", info.getName());
            } catch (MidiUnavailableException e) {
                getLogger().info("Unavailable {}", info.getName());
            }

        }
        getLogger().info("--------------------------");
    }

    public static MidicraftClient getInstance() {
        return instance;
    }

    public Logger getLogger() {
        return logger;
    }

    public MidiInputProcessor getMidiInputProcessor() {
        return midiInputProcessor;
    }
}

package dev.xvcf.midicraft.client.util;

import net.minecraft.world.entity.player.Input;

import java.util.HashMap;

public class MidiInputProcessor {

    private HashMap<Integer, Boolean> keysPressed;

    public MidiInputProcessor() {
        keysPressed = new HashMap<>();
    }

    public void addKey(int id) {
        this.keysPressed.put(id, true);
    }

    public void removeKey(int id) {
        this.keysPressed.put(id, false);
    }

    public HashMap<Integer, Boolean> getKeys() {
        return keysPressed;
    }

    public Input getInput() {
        return new Input(
                getKeys().getOrDefault(61, false),
                getKeys().getOrDefault(62, false),
                getKeys().getOrDefault(60, false),
                getKeys().getOrDefault(64, false),
                getKeys().getOrDefault(65, false),
                getKeys().getOrDefault(59, false),
                getKeys().getOrDefault(57, false)
        );
    }
}

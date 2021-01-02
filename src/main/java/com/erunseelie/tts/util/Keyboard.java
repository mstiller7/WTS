package com.erunseelie.tts.util;

import com.erunseelie.tts.ThatsThatSlot;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;

public class Keyboard {

    public static final KeyBinding checkSlot;

    static {
        checkSlot = new KeyBinding("key.tts.checkSlot", KeyConflictContext.GUI, Keyboard.getKey(GLFW.GLFW_KEY_P), ThatsThatSlot.MODID);
    }

    public static InputMappings.Input getKey(int key) {
        return InputMappings.Type.KEYSYM.getOrMakeInput(key);
    }

    public static void init() {
        ClientRegistry.registerKeyBinding(checkSlot);
    }
}

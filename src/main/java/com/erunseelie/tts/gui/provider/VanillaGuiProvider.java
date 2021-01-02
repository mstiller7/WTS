package com.erunseelie.tts.gui.provider;

import com.erunseelie.tts.gui.SlotGUI;
import com.erunseelie.tts.gui.VanillaSlotGUI;

public class VanillaGuiProvider implements SlotGuiProvider {
    private VanillaSlotGUI gui = new VanillaSlotGUI();

    @Override
    public boolean canUse() {
        return true;
    }

    @Override
    public SlotGUI get() {
        return this.gui;
    }
}

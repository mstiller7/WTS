package com.erunseelie.tts.gui.provider;

import com.erunseelie.tts.gui.SlotGUI;

public interface SlotGuiProvider {
    boolean canUse();

    SlotGUI get();
}

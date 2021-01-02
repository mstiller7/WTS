package com.erunseelie.tts.gui;

import com.erunseelie.tts.SlotPlaceInfo;
import mezz.jei.api.IJeiRuntime;

import java.util.Collections;

public class JEISlotGUI extends SlotGUI {
    private IJeiRuntime runtime;

    public JEISlotGUI(IJeiRuntime runtime) {
        this.runtime = runtime;
    }

    @Override
    public void show(SlotPlaceInfo placeInfo) {
        this.runtime.getItemListOverlay().highlightStacks(placeInfo.getValidItems());
    }

    @Override
    public void hide() {
        this.runtime.getItemListOverlay().highlightStacks(Collections.emptyList());
    }
}

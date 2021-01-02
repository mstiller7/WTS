package com.erunseelie.tts.jei;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.JEIPlugin;

@JEIPlugin
public class WTSPluginJEI extends BlankModPlugin {
    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
//        WhatsThatSlot.registerProvider(new JEIGuiProvider(jeiRuntime));
    }
}

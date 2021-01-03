package com.erunseelie.tts.gui.provider;

import com.erunseelie.tts.gui.JEISlotGUI;
import com.erunseelie.tts.gui.SlotGUI;
import mezz.jei.api.IJeiRuntime;
import net.minecraftforge.fml.common.Loader;

public class JEIGuiProvider implements SlotGuiProvider {
	
	private JEISlotGUI gui;
	
	public JEIGuiProvider(IJeiRuntime runtime) {
		this.gui = new JEISlotGUI(runtime);
	}
	
	@Override
	public boolean canUse() {
		return Loader.isModLoaded("jei");
	}
	
	@Override
	public SlotGUI get() {
		return this.gui;
	}
	
}

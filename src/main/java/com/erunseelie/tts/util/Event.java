package com.erunseelie.tts.util;

import com.erunseelie.tts.SlotPlaceInfo;
import com.erunseelie.tts.gui.SlotGUI;
import com.erunseelie.tts.gui.provider.SlotGuiProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.util.InputMappings;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.AirItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.LinkedList;
import java.util.List;

public class Event {
	
	public static List<ItemStack> populateItems() {
		List<ItemStack> items = new LinkedList<>();
		for (Item item : Item.BLOCK_TO_ITEM.values()) {
			if (item instanceof AirItem) {
				continue;
			}
			NonNullList<ItemStack> stacks = NonNullList.create();
			item.fillItemGroup(item.getGroup(), stacks);
			items.addAll(stacks);
		}
		return items;
	}
	
	public static void keyPress() {
		long handle = Minecraft.getInstance().getMainWindow().getHandle();
	}
	
	@SubscribeEvent
	public void onGUIChange(GuiOpenEvent event) {
		if (!(event.getGui() instanceof ContainerScreen)) {
			this.hide();
		}
	}
	
	@SubscribeEvent
	public void onKeyPress(GuiScreenEvent.KeyboardKeyPressedEvent.Pre event) {
		Screen currentScreen = Minecraft.getInstance().currentScreen;
		InputMappings.Input key = InputMappings.getInputByCode(event.getKeyCode(), event.getScanCode());
		//        TODO
		if (key == Keyboard.checkSlot && currentScreen instanceof ContainerScreen) {
			GuiContainer container = (GuiContainer) currentScreen;
			Slot selectedSlot = container.getSlotUnderMouse();
			if (selectedSlot != null) {
				this.slotGUI = WhatsThatSlot.provideSlotGUI();
				if (this.slotGUI != null) {
					MinecraftForge.EVENT_BUS.register(this.slotGUI);
					this.slotGUI.show(SlotPlaceInfo.create(selectedSlot));
				}
			} else {
				this.hide();
			}
		}
	}
	
	private void hide() {
		if (this.slotGUI != null) {
			MinecraftForge.EVENT_BUS.unregister(this.slotGUI);
			this.slotGUI.hide();
			this.slotGUI = null;
		}
	}
	
	public static SlotGUI provideSlotGUI() {
		for (SlotGuiProvider provider : GUI_PROVIDERS) {
			if (provider.canUse()) {
				return provider.get();
			}
		}
		return VANILLA_PROVIDER.get();
	}
	
}

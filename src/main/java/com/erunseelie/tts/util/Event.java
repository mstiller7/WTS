package com.erunseelie.tts.util;

import com.erunseelie.tts.SlotPlaceInfo;
import com.erunseelie.tts.gui.SlotGUI;
import com.erunseelie.tts.gui.provider.SlotGuiProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.AirItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;

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

    @SubscribeEvent
    public void onGUIChange(GuiOpenEvent event) {
        if (!(event.getGui() instanceof GuiContainer)) {
            this.hide();
        }
    }

    @SubscribeEvent
    public void onKeyPress(GuiScreenEvent.KeyboardInputEvent event) {
        Screen currentScreen = Minecraft.getInstance().currentScreen;
        if (Keyboard.isKeyDown(KEY_CHECK_SLOT.getKeyCode()) && currentScreen instanceof GuiContainer) {
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

package com.erunseelie.tts;

import com.erunseelie.tts.util.Event;
import com.erunseelie.tts.util.Keyboard;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

@Mod(ThatsThatSlot.MODID)
public class ThatsThatSlot {
	
	public static final String MODID = "thatsthatslot";
	public static Logger log = LogManager.getLogger(MODID);
	
	public static List<ItemStack> ITEMS = new LinkedList<>();
	
	public ThatsThatSlot() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		bus.addListener(this::preInit);
		bus.addListener(this::preInitClient);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	/**
	 * Common event processing.
	 *
	 * @param event
	 */
	private void preInit(final FMLCommonSetupEvent event) {
		// TODO network stuff.
	}
	
	/**
	 * Client-specific event processing.
	 *
	 * @param event
	 */
	private void preInitClient(final FMLClientSetupEvent event) {
		ClientRegistry.registerKeyBinding(Keyboard.checkSlot);
		ITEMS = Event.populateItems();
	}
	
}

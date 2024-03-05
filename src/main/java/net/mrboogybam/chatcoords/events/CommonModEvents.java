package net.mrboogybam.chatcoords.events;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.mrboogybam.chatcoords.ChatCoords;
import net.mrboogybam.chatcoords.network.PacketHandler;

@Mod.EventBusSubscriber(modid = ChatCoords.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents
{
    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            PacketHandler.register();
        });
    }
}

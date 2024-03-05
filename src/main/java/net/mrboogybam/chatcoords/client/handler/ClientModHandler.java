package net.mrboogybam.chatcoords.client.handler;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrboogybam.chatcoords.ChatCoords;
import net.mrboogybam.chatcoords.client.Keybindings;

@Mod.EventBusSubscriber(modid = ChatCoords.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModHandler
{
    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event)
    {
        event.register(Keybindings.INSTANCE.coordsKey);
    }
}

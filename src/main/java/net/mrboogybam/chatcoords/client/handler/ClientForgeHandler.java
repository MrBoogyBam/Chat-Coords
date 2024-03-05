package net.mrboogybam.chatcoords.client.handler;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrboogybam.chatcoords.ChatCoords;
import net.mrboogybam.chatcoords.client.Keybindings;
import net.mrboogybam.chatcoords.network.PacketHandler;
import net.mrboogybam.chatcoords.network.SSendCoordsPacket;

@Mod.EventBusSubscriber(modid = ChatCoords.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientForgeHandler
{
    @SubscribeEvent
    public static void clientTick(TickEvent.ClientTickEvent event)
    {
        if(Keybindings.INSTANCE.coordsKey.consumeClick())
        {
            PacketHandler.sendToServer(new SSendCoordsPacket());
        }
    }
}

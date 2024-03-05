package net.mrboogybam.chatcoords.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.SimpleChannel;
import net.mrboogybam.chatcoords.ChatCoords;

public class PacketHandler
{
    private static final SimpleChannel INSTANCE = ChannelBuilder.named(
            new ResourceLocation(ChatCoords.MODID, "main"))
            .serverAcceptedVersions((status, version) -> true)
            .clientAcceptedVersions((status, version) -> true)
            .networkProtocolVersion(1)
            .simpleChannel();

    public static void register()
    {
        INSTANCE.messageBuilder(SSendCoordsPacket.class, NetworkDirection.PLAY_TO_SERVER)
                .encoder(SSendCoordsPacket::encode)
                .decoder(SSendCoordsPacket::new)
                .consumerMainThread(SSendCoordsPacket::handle)
                .add();
    }

    public static void sendToServer(Object msg)
    {
        INSTANCE.send(msg, PacketDistributor.SERVER.noArg());
    }
}

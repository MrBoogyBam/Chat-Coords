package net.mrboogybam.chatcoords.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.OutgoingChatMessage;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class SSendCoordsPacket
{
    public SSendCoordsPacket()
    {
    }

    public SSendCoordsPacket(FriendlyByteBuf buffer)
    {
    }

    public void encode(FriendlyByteBuf buffer)
    {
    }

    public void handle(CustomPayloadEvent.Context context)
    {
        ServerPlayer player  = context.getSender();

        if(player == null) return;

        String xRounded = String.format("%.0f", player.getX());
        String yRounded = String.format("%.0f", player.getY());
        String zRounded = String.format("%.0f", player.getZ());
        String currentDimension = "";

        switch(player.getCommandSenderWorld().dimension().location().toString())
        {
            case "minecraft:overworld" -> currentDimension = "Overworld";
            case "minecraft:the_nether" -> currentDimension = "Nether";
            case "minecraft:the_end" -> currentDimension = "End";
        }

        String coords = "x" + xRounded + ", y" + yRounded + ", z" + zRounded + ", " + currentDimension;

        OutgoingChatMessage message = new OutgoingChatMessage.Player(PlayerChatMessage.unsigned(player.getUUID(), coords));

        player.sendChatMessage(message, false, ChatType.bind(ChatType.CHAT, player));
    }
}

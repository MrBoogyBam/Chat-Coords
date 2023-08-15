package net.mrboogybam.chatcoords;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class ChatCoordsClient implements ClientModInitializer {

    private static KeyBinding keyBinding;

    @Override
    public void onInitializeClient() {
        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Send Coordinates",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_N,
                "Chat Coords"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBinding.wasPressed()) {
                assert client.player != null;

                String xRounded = String.format("%.0f", client.player.getX());
                String yRounded = String.format("%.0f", client.player.getY());
                String zRounded = String.format("%.0f", client.player.getZ());
                String currentDimension = "";

                assert MinecraftClient.getInstance().world != null;
                switch (MinecraftClient.getInstance().world.getRegistryKey().getValue().toString()) {
                    case "minecraft:overworld" -> currentDimension = "Overworld";
                    case "minecraft:the_nether" -> currentDimension = "Nether";
                    case "minecraft:the_end" -> currentDimension = "End";
                }

                String coords = "x" + xRounded + ", " + "y" + yRounded + ", " + "z" + zRounded + ", " + currentDimension;

                assert MinecraftClient.getInstance().player != null;
                MinecraftClient.getInstance().player.sendMessage(Text.of(coords));
            }
        });
    }
}

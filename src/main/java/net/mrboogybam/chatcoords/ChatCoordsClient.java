package net.mrboogybam.chatcoords;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ChatCoordsClient implements ClientModInitializer {

    private static KeyBinding keyBinding;

    public String xRounded;
    public String yRounded;
    public String zRounded;

    @Override
    public void onInitializeClient() {
        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Send Coordinates",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_N,
                "Chat Coords"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while(keyBinding.wasPressed()) {
                assert client.player != null;
                double x = client.player.getX();
                double y = client.player.getY();
                double z = client.player.getZ();

                xRounded = String.format("%.0f", x);
                yRounded = String.format("%.0f", y);
                zRounded = String.format("%.0f", z);

                String coords = xRounded + ", " + yRounded + ", " + zRounded;

                assert MinecraftClient.getInstance().player != null;
                MinecraftClient.getInstance().player.sendChatMessage(coords);
            }
        });
    }
}

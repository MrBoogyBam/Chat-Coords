package net.mrboogybam.chatcoords.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.mrboogybam.chatcoords.ChatCoords;

public class Keybindings
{
    public static final Keybindings INSTANCE = new Keybindings();

    private Keybindings()
    {
    }

    private static final String CATEGORY = "key.categories." + ChatCoords.MODID;

    public final KeyMapping coordsKey = new KeyMapping(
            "key." + ChatCoords.MODID + ".coords_key",
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_N, -1),
            CATEGORY
    );
}

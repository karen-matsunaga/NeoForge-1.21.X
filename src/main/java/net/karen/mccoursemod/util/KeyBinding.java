package net.karen.mccoursemod.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.neoforged.neoforge.common.util.Lazy;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATEGORY_MCCOURSE = "key.category.mccoursemod"; // Mccourse custom category
    public static final String KEY_GLOWING_BLOCKS = "key.mccoursemod_glowing_blocks"; // Glowing Blocks custom key input
    public static final String KEY_GLOWING_MOBS = "key.mccoursemod_glowing_mobs"; // Glowing Mobs custom key input
    // Mccourse Bottle custom key inputs
    public static final String KEY_MCCOURSE_BOTTLE_STORED_TEN_LEVELS =
            "key.mccoursemod_mccoursemod_bottle_stored_ten_levels";
    public static final String KEY_MCCOURSE_BOTTLE_RESTORED_TEN_LEVELS =
            "key.mccoursemod_mccoursemod_bottle_restored_ten_levels";
    public static final String KEY_MCCOURSE_BOTTLE_STORED_HUNDRED_LEVELS =
            "key.mccoursemod_mccoursemod_bottle_stored_hundred_levels";
    public static final String KEY_MCCOURSE_BOTTLE_RESTORED_HUNDRED_LEVELS =
            "key.mccoursemod_mccoursemod_bottle_restored_hundred_levels";
    // Unlock custom key input
    public static final String KEY_UNLOCK = "key.mccoursemod_unlock";

    // Register all custom key binding
    public static final Lazy<KeyMapping> GLOWING_BLOCKS_KEY =
           Lazy.of(() -> new KeyMapping(KEY_GLOWING_BLOCKS, KeyConflictContext.IN_GAME,
                                        InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_G,
                                        KEY_CATEGORY_MCCOURSE));

    public static final Lazy<KeyMapping> GLOWING_MOBS_KEY =
           Lazy.of(() -> new KeyMapping(KEY_GLOWING_MOBS, KeyConflictContext.IN_GAME,
                                        InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_M,
                                        KEY_CATEGORY_MCCOURSE));

    public static final Lazy<KeyMapping> MCCOURSE_BOTTLE_STORED_TEN_LEVELS_KEY =
           Lazy.of(() -> new KeyMapping(KEY_MCCOURSE_BOTTLE_STORED_TEN_LEVELS,
                                        KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM,
                                        GLFW.GLFW_KEY_N, KEY_CATEGORY_MCCOURSE));

    public static final Lazy<KeyMapping> MCCOURSE_BOTTLE_RESTORED_TEN_LEVELS_KEY =
           Lazy.of(() -> new KeyMapping(KEY_MCCOURSE_BOTTLE_RESTORED_TEN_LEVELS,
                                        KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM,
                                        GLFW.GLFW_KEY_B, KEY_CATEGORY_MCCOURSE));

    public static final Lazy<KeyMapping> MCCOURSE_BOTTLE_STORED_HUNDRED_LEVELS_KEY =
           Lazy.of(() -> new KeyMapping(KEY_MCCOURSE_BOTTLE_STORED_HUNDRED_LEVELS,
                                        KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM,
                                        GLFW.GLFW_KEY_N, KEY_CATEGORY_MCCOURSE));

    public static final Lazy<KeyMapping> MCCOURSE_BOTTLE_RESTORED_HUNDRED_LEVELS_KEY =
           Lazy.of(() -> new KeyMapping(KEY_MCCOURSE_BOTTLE_RESTORED_HUNDRED_LEVELS,
                                        KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM,
                                        GLFW.GLFW_KEY_B, KEY_CATEGORY_MCCOURSE));

    public static final Lazy<KeyMapping> UNLOCK_KEY =
           Lazy.of(() -> new KeyMapping(KEY_UNLOCK, KeyConflictContext.UNIVERSAL,
                                        InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_V,
                                        KEY_CATEGORY_MCCOURSE));
}
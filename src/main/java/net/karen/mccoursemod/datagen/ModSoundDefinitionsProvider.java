package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.SoundDefinition;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;

public class ModSoundDefinitionsProvider extends SoundDefinitionsProvider {
    public ModSoundDefinitionsProvider(PackOutput output) {
        super(output, MccourseMod.MOD_ID);
    }

    @Override
    public void registerSounds() {
        // CHISEL USE
        add("chisel_use", SoundDefinition.definition().subtitle("sounds.mccoursemod.chisel_use")
                                                                 .with(sound("mccoursemod:chisel_use")));

        // MAGIC BREAK
        add("magic_block_break", SoundDefinition.definition().subtitle("sounds.mccoursemod.magic_block_break")
                                                                        .with(sound("mccoursemod:magic_block_break")));

        // MAGIC STEP
        add("magic_block_step", SoundDefinition.definition().subtitle("sounds.mccoursemod.magic_block_step")
                                                                       .with(sound("mccoursemod:magic_block_step")));

        // MAGIC PLACE
        add("magic_block_place", SoundDefinition.definition().subtitle("sounds.mccoursemod.magic_block_place")
                                                                        .with(sound("mccoursemod:magic_block_place")));

        // MAGIC HIT
        add("magic_block_hit", SoundDefinition.definition().subtitle("sounds.mccoursemod.magic_block_hit")
                                                                      .with(sound("mccoursemod:magic_block_hit")));

        // MAGIC FALL
        add("magic_block_fall", SoundDefinition.definition().subtitle("sounds.mccoursemod.magic_block_fall")
                                                                       .with(sound("mccoursemod:magic_block_fall")));

        // BAR BRAWL
        add("bar_brawl", SoundDefinition.definition().with(sound("mccoursemod:bar_brawl",
                                                                            SoundDefinition.SoundType.SOUND).stream(true)));
    }
}
package net.karen.mccoursemod.item.custom;

import net.karen.mccoursemod.util.ModTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;

public class PaxelItem extends Item {
    public PaxelItem(ToolMaterial material, float attackDamage,
                     float attackSpeed, Properties properties) {
        super(material.applyToolProperties(properties, ModTags.Blocks.MINEABLE_WITH_PAXEL,
                                           attackDamage, attackSpeed, 0F));
    }
}
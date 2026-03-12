package net.chemthunder.attuned.mixin.compat.enchancement;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import moriyashiine.enchancement.common.screenhandlers.EnchantingTableScreenHandler;
import net.chemthunder.attuned.impl.index.AttunedItems;
import net.chemthunder.attuned.impl.item.TuningForkItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EnchantingTableScreenHandler.class)
public class EnchantingTableScreenHandlerMixin {
    @ModifyReturnValue(
            method = "getRepairIngredient(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/recipe/Ingredient;",
            at = @At("RETURN")
    )
    private Ingredient customMaterialSoNoTag(Ingredient original, ItemStack stack) {
        if (stack.getItem() instanceof TuningForkItem item) {
            return Ingredient.ofItems(AttunedItems.ANCILLARY_BIT, Items.IRON_INGOT);
        }

        return original;
    }
}
package uk.debb.vanilla_disable.mixin.merchant;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;
import uk.debb.vanilla_disable.util.maps.Maps;

@Mixin(VillagerData.class)
public abstract class MixinVillagerData implements Maps {
    @Shadow
    @Final
    private VillagerType type;
    @Shadow
    @Final
    private VillagerProfession profession;

    @ModifyReturnValue(method = "getType", at = @At("RETURN"))
    private VillagerType modifyType(VillagerType original) {
        BooleanGamerules gameRule = villagerDataVillagerTypeMap.get(this.type);
        if (!BooleanGamerules.VILLAGER_TYPES_ENABLED.getValue() || (gameRule != null && !gameRule.getValue())) {
            return VillagerType.PLAINS;
        }
        return original;
    }

    @ModifyReturnValue(method = "getProfession", at = @At("RETURN"))
    private VillagerProfession modifyProfession(VillagerProfession original) {
        BooleanGamerules gameRule = villagerDataVillagerProfessionMap.get(this.profession);
        if (!BooleanGamerules.VILLAGER_PROFESSIONS_ENABLED.getValue() || (gameRule != null && !gameRule.getValue())) {
            return VillagerProfession.NONE;
        }
        return original;
    }
}

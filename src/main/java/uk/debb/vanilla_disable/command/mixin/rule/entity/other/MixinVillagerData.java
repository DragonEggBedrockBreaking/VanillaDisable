package uk.debb.vanilla_disable.command.mixin.rule.entity.other;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.CommandDataHandler;

@Mixin(VillagerData.class)
public abstract class MixinVillagerData {
    @Shadow
    @Final
    private VillagerType type;
    @Shadow
    @Final
    private VillagerProfession profession;

    @ModifyReturnValue(method = "getType", at = @At("RETURN"))
    private VillagerType getType(VillagerType original) {
        if (CommandDataHandler.isConnectionNull()) return original;
        if (!CommandDataHandler.getCachedBoolean("entities", "minecraft:villager",
                CommandDataHandler.villagerTypeRegistry.getKey(type) + "_type")) {
            return VillagerType.PLAINS;
        }
        return original;
    }

    @ModifyReturnValue(method = "getProfession", at = @At("RETURN"))
    private VillagerProfession getProfession(VillagerProfession original) {
        if (CommandDataHandler.isConnectionNull()) return original;
        if (!CommandDataHandler.getCachedBoolean("entities", "minecraft:villager",
                CommandDataHandler.villagerProfessionRegistry.getKey(profession) + "_profession")) {
            return VillagerProfession.NONE;
        }
        return original;
    }
}

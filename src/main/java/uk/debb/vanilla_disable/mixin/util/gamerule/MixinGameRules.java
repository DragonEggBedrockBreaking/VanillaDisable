package uk.debb.vanilla_disable.mixin.util.gamerule;

import com.mojang.serialization.DynamicLike;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.data.gamerule.GameruleMigrationDataHandler;

@Mixin(GameRules.class)
public abstract class MixinGameRules {
    @Inject(method = "loadFromTag", at = @At("HEAD"))
    private void loadFromTag(DynamicLike<?> dynamicLike, CallbackInfo ci) {
        GameruleMigrationDataHandler.sqlData.forEach(rule -> rule.value = "");
        GameruleMigrationDataHandler.massColumnSqlData.forEach(rule -> rule.value = "");
        GameruleMigrationDataHandler.allRowSqlDataHolders.forEach(rule -> rule.value = "");
        GameruleMigrationDataHandler.tomlData.forEach(rule -> rule.value = "");

        GameruleMigrationDataHandler.sqlData.forEach(rule ->
                dynamicLike.get(rule.rule).asString().result().ifPresent(str -> rule.value = str));
        GameruleMigrationDataHandler.massColumnSqlData.forEach(rule ->
                dynamicLike.get(rule.rule).asString().result().ifPresent(str -> rule.value = str));
        GameruleMigrationDataHandler.allRowSqlDataHolders.forEach(rule ->
                dynamicLike.get(rule.rule).asString().result().ifPresent(str -> rule.value = str));
        GameruleMigrationDataHandler.tomlData.forEach(rule ->
                dynamicLike.get(rule.rule).asString().result().ifPresent(str -> rule.value = str));
        dynamicLike.get("biomesEnabled").asString().result().ifPresent(str ->
                GameruleMigrationDataHandler.biomesEnabled = str);
    }
}

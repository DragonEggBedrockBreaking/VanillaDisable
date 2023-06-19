package uk.debb.vanilla_disable.gamerules.gamerules;

import com.google.gson.stream.JsonReader;
import net.fabricmc.fabric.api.gamerule.v1.CustomGameRuleCategory;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import uk.debb.vanilla_disable.gamerules.mixin_plugins.CaffeineConfigMixinConfigPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public enum GameruleCategories {
    VD_DAMAGE,
    VD_KNOCKBACK,
    VD_SPAWNING,
    VD_DESPAWNING,
    VD_SPAWN_LIMITS,
    VD_COMMANDS,
    VD_FLUIDS,
    VD_MOBS,
    VD_MOB_TOGGLES,
    VD_EFFECTS,
    VD_ENCHANTMENTS,
    VD_WORLDGEN,
    VD_PLAYER,
    VD_REDSTONE,
    VD_AI,
    VD_BLOCKS,
    VD_FOOD,
    VD_POTIONS,
    VD_DEATH,
    VD_ITEMS,
    VD_DISPENSER,
    VD_ARROW,
    VD_ADVANCEMENT,
    VD_STATS,
    VD_ENTITY,
    VD_MERCHANT,
    VD_BIOME,
    VD_MISC;

    private final CustomGameRuleCategory category;
    private boolean enabled;

    GameruleCategories() {
        this.category = createCustomGameRuleCategory(this.name());
    }

    private CustomGameRuleCategory createCustomGameRuleCategory(String name) {
        String identifier = "gamerule.category.vd." + name.substring(3).toLowerCase();
        return new CustomGameRuleCategory(
                new ResourceLocation(identifier),
                Component.translatable(identifier)
                        .withStyle(ChatFormatting.BOLD)
                        .withStyle(ChatFormatting.DARK_GREEN)
        );
    }

    public void toggle() throws IOException {
        String subpackage = this.name().substring(3).toLowerCase();
        InputStream inputStream = GameruleCategories.class.getResourceAsStream("/vanilla_disable_gamerules.mixins.json");
        if (inputStream != null) {
            Reader reader = new InputStreamReader(inputStream);
            JsonReader jsonReader = new JsonReader(reader);
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                String name = jsonReader.nextName();
                if (name.equals("mixins")) {
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        String mixin = jsonReader.nextString();
                        if (mixin.startsWith(subpackage + ".")) {
                            enabled = CaffeineConfigMixinConfigPlugin.caffeineConfig.getEffectiveOptionForMixin(mixin).isEnabled();
                            break;
                        }
                    }
                    break;
                } else {
                    jsonReader.skipValue();
                }
            }
            reader.close();
        }
    }

    public CustomGameRuleCategory get() {
        return this.category;
    }

    public boolean isEnabled() {
        return this.enabled;
    }
}
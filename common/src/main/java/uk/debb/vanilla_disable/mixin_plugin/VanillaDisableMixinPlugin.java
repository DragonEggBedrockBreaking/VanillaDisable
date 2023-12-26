package uk.debb.vanilla_disable.mixin_plugin;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import uk.debb.vanilla_disable.Constants;

import java.util.List;
import java.util.Set;

public class VanillaDisableMixinPlugin implements IMixinConfigPlugin {
    MixinPluginConfig config;

    @Override
    public void onLoad(String s) {
        config = new MixinPluginConfig();
        Constants.LOG.info("Loaded VanillaDisable mixin config file with {} overrides.", config.properties.size());
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        String[] mixinPath = mixinClassName.substring(30).split("\\.");
            StringBuilder current = new StringBuilder("mixin");
        for (String part : mixinPath) {
            current.append(".").append(part);
            if (config.isMixinConfigured(current.toString())) {
                return config.isMixinEnabled(current.toString());
            }
        }
        return true;
    }

    @Override
    public void acceptTargets(Set<String> set, Set<String> set1) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }


    @Override
    public void preApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo) {

    }

    @Override
    public void postApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo) {

    }
}

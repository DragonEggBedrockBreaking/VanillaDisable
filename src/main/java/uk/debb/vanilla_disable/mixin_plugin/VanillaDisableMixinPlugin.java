package uk.debb.vanilla_disable.mixin_plugin;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class VanillaDisableMixinPlugin implements IMixinConfigPlugin {
    CaffeineConfigMixinPlugin caffeineConfigMixinPlugin = new CaffeineConfigMixinPlugin();
    ConditionalMixinMixinPlugin conditionalMixinMixinPlugin = new ConditionalMixinMixinPlugin();

    @Override
    public void onLoad(String mixinPackage) {
        caffeineConfigMixinPlugin.onLoad(mixinPackage);
        conditionalMixinMixinPlugin.onLoad(mixinPackage);
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return caffeineConfigMixinPlugin.shouldApplyMixin(targetClassName, mixinClassName) &&
                conditionalMixinMixinPlugin.shouldApplyMixin(targetClassName, mixinClassName);
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
        caffeineConfigMixinPlugin.acceptTargets(myTargets, otherTargets);
        conditionalMixinMixinPlugin.acceptTargets(myTargets, otherTargets);
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        caffeineConfigMixinPlugin.preApply(targetClassName, targetClass, mixinClassName, mixinInfo);
        conditionalMixinMixinPlugin.preApply(targetClassName, targetClass, mixinClassName, mixinInfo);
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        caffeineConfigMixinPlugin.postApply(targetClassName, targetClass, mixinClassName, mixinInfo);
        conditionalMixinMixinPlugin.postApply(targetClassName, targetClass, mixinClassName, mixinInfo);
    }
}

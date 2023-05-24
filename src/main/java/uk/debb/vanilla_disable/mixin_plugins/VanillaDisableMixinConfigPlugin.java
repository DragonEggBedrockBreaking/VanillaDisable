package uk.debb.vanilla_disable.mixin_plugins;

import com.llamalad7.mixinextras.MixinExtrasBootstrap;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class VanillaDisableMixinConfigPlugin implements IMixinConfigPlugin {
    private static final CaffeineConfigMixinConfigPlugin caffeineConfigMixinConfigPlugin = new CaffeineConfigMixinConfigPlugin();

    @Override
    public void onLoad(String mixinPackage) {
        MixinExtrasBootstrap.init();
        caffeineConfigMixinConfigPlugin.onLoad(mixinPackage);
    }

    @Override
    public String getRefMapperConfig() {
        return caffeineConfigMixinConfigPlugin.getRefMapperConfig();
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return caffeineConfigMixinConfigPlugin.shouldApplyMixin(targetClassName, mixinClassName);
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
        caffeineConfigMixinConfigPlugin.acceptTargets(myTargets, otherTargets);
    }

    @Override
    public List<String> getMixins() {
        return caffeineConfigMixinConfigPlugin.getMixins();
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mi) {
        caffeineConfigMixinConfigPlugin.preApply(targetClassName, targetClass, mixinClassName, mi);
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mi) {
        caffeineConfigMixinConfigPlugin.postApply(targetClassName, targetClass, mixinClassName, mi);
    }
}
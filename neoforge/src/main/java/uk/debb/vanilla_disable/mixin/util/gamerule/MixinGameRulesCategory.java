package uk.debb.vanilla_disable.mixin.util.gamerule;

import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(GameRules.Category.class)
public abstract class MixinGameRulesCategory {
    @Invoker("<init>")
    private static GameRules.Category init(String name, int id, String descriptionId) {
        throw new AssertionError();
    }

    @Shadow
    @Final
    @Mutable
    private static GameRules.Category[] $VALUES;

    static {
        ArrayList<GameRules.Category> vals = new ArrayList<>(Arrays.asList($VALUES));
        GameRules.Category last = vals.get(vals.size() - 1);
        vals.add(init("VANILLA_DISABLE", last.ordinal() + 1, "vd.gamerule.category.vanilla_disable"));
        $VALUES = vals.toArray(new GameRules.Category[0]);
    }
}

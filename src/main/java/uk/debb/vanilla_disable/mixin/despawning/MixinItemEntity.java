package uk.debb.vanilla_disable.mixin.despawning;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(ItemEntity.class)
public abstract class MixinItemEntity extends Entity {
    @Unique
    final int MAX = GameruleHelper.getInt(Gamerules.ITEM_DESPAWN_TIME);
    @Shadow
    private int age;
    @Shadow
    private int pickupDelay;

    public MixinItemEntity(EntityType<? extends Entity> entityType, Level level) {
        super(entityType, level);
    }

    /**
     * @param ci callback info
     * @author DragonEggBedrockBreaking
     * @reason delete the item when necessary
     */
    @Inject(method = "tick", at = @At("HEAD"))
    private void discardItem(CallbackInfo ci) {
        if (this.age >= MAX * 20 && !(this.getLevel().isClientSide())) {
            this.discard();
        }
    }

    /**
     * @param ci callback info
     * @author DragonEggBedrockBreaking
     * @reason cancel deleting the item if too early
     */
    @Inject(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/item/ItemEntity;discard()V",
                    ordinal = 1
            ),
            cancellable = true
    )
    private void cancelDiscard(CallbackInfo ci) {
        if (this.age < MAX * 20 &&
                this.pickupDelay != Short.MAX_VALUE) {
            ci.cancel();
        }
    }
}
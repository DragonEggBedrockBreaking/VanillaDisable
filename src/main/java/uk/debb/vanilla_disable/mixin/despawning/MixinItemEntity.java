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
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(ItemEntity.class)
public abstract class MixinItemEntity extends Entity {
    public MixinItemEntity(EntityType<? extends Entity> entityType, Level level) {
        super(entityType, level);
    }

    @Shadow private int age;
    @Shadow private int pickupDelay;
    @Unique
    final int MAX = GameruleHelper.getInt(Gamerules.ITEM_DESPAWN_TIME);

    /**
     * @author DragonEggBedrockBreaking
     * @reason delete the item when necessary
     * @param ci callback info
     */
    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void discardItem(CallbackInfo ci) {
        if (VDServer.getServer() == null) return;
        if (this.age >= MAX * 20 && !(this.getLevel().isClientSide())) {
            this.discard();
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason cancel deleting the item if too early
     * @param ci callback info
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
        if (VDServer.getServer() == null) return;
        if (this.age < MAX * 20 &&
            this.pickupDelay != Short.MAX_VALUE) {
            ci.cancel();
        }
    }
}

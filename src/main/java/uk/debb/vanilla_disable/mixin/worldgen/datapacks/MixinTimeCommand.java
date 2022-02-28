package uk.debb.vanilla_disable.mixin.worldgen.datapacks;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.commands.TimeCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(TimeCommand.class)
public abstract class MixinTimeCommand {
    /**
     * @author DragonEggBedrockBreaking
     * @reason patch the "/time set" command to not break the mod
     * @param source the source of the commands
     * @param time the time
     * @return
     */
    @Overwrite
    public static int setTime(CommandSourceStack source, int time) {
        // the "/time set" command can reset the time
        // this makes the mod think that it is the first launch
        // this patches it by pushing forward the time to switch
        // to "/time add", e.g. if it is 24000 and you want 6000,
        // this patch will make it 30000 instead of resetting to 6000
        // to the client, both are noon, so vanilla behaviour is preserved

        int currentTime = (int)(source.getLevel().getDayTime() % 24000L);
        int relativeTime = time % 24000;
        int timeToAdd;
        if (currentTime > relativeTime) {
            timeToAdd = 24000 - (relativeTime - currentTime);
        } else if (relativeTime > currentTime) {
            timeToAdd = relativeTime - currentTime;
        } else {
            timeToAdd = 0;
        }
        return TimeCommand.addTime(source, timeToAdd);
    }
}

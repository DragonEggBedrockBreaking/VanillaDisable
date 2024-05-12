/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package uk.debb.vanilla_disable.neoforge.config.global;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.jetbrains.annotations.NotNull;
import uk.debb.vanilla_disable.config.global.VanillaDisableConfigScreen;

@Mod("vanilla_disable")
public class ModMenuConfigScreenFactory {
    public ModMenuConfigScreenFactory() {
        ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class, () -> new IConfigScreenFactory() {
            @Override
            public @NotNull Screen createScreen(@NotNull Minecraft minecraft, @NotNull Screen screen) {
                return new VanillaDisableConfigScreen(screen);
            }
        });
    }
}
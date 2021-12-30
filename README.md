# Vanilla Disable

## Description

This is a fabric mod that allows you to toggle or change many vanilla features
through the vanilla gamerule system. This mod supports the latest version of Minecraft.
Only critical issues/major crashes will be fixed on older versions of Minecraft.
Mod compatibility is an aim but not always a reality - if there is an incompatibility,
I will likely try to resolve it, but my success is not guaranteed.

## Documentation

See the wiki page for more information.

## Downloads

You can download both stable releases of the mod, and also beta releases, from [Github Releases](https:/github.com/DragonEggBedrockBreaking/VanillaDisable/releases),.

## Compiling

Unlike most minecraft mods, this mod does not use the maven or gradle build systems.
This mod uses a new, faster, more minecraft-focused build system called [Brachyura](https://github.com/CoolCrabs/brachyura).

To compile the project, run the commands (prequisite: you need Java version 17 or above installed and in path):
```
git clone https://github.com/DragonEggBedrockBreaking/VanillaDisable.git/
cd VanillaDisable
java -jar brachyura-bootstrap-0.jar build
```

You can speed it up by using the `-j n`, where n is the number of threads that you want to use.
The artifacts will be in `build/libs/`, just like with the other builds systems.

## License

This mod is available under the [MPL](LICENSE.txt) license.
Some code is from another project which uses the [LGPL-3.0](https://github.com/TISUnion/Carpet-TIS-Addition/blob/master/LICENSE) license.

# Vanilla Disable

## Description

You can use gamerules to enable/disable a lot more stuff with this mod.

## Status

This mod is incomplete/experimental. Features may be added and removed at any time.

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

This mod is available under the [MIT](LICENSE.txt) license.

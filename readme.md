# Puzzle Loader Example Mod
> The example mod for [Puzzle Loader Cosmic](https://github.com/PuzzlesHQ/puzzle-loader-cosmic)

## How To Test Client & Server
For Client testing in the dev env, you can use the `gradle runModdedClient` task
For Server testing in the dev env, you can use the `gradle runModdedServer` task

## Running Vanilla 
You can run the vanilla Client by use the `gradle runVanillaClient` task.
You can run the vanilla Server by use the `gradle runVanillaServer` task.

## Build A Jar
For building, the usual `gradle buildMergedJar` task can be used. The output will be in the `build/libs/` folder

## Updating Your Mod
Client, common and Server code now need to separate this mean in your mod's src folder you will have `src/client` for Client code, `src/server` for Server code, `src/common` for code that need to be on both Server & Client.
- [ClientModInit]() for client side
- [ModInit]() for common side
- [ServerModInit]() for server side
- puzzle.mod.json now has `"formatVersion": 3`
- You can now have three mixin.json for server, common and client, if you want separation.
- Puzzle has update to its new gradle plugin jigsaw-suite, update your `build.gradle` & `gradle.properties` to example's ones.
- Use `gradle cleanOldJigsawLocal` and `gradle cleanOldJigsawGlobal` to remove outdated Jigsaw directories from the local and global environments.

## Notes
- Most project properties can be changed in the `gradle.properties`.
- To add Puzzle mods in the build, make sure to use `commonImplementation` of common mod
# Puzzle Loader Example Mod
> The example mod for [Puzzle Loader](https://github.com/PuzzleLoader/PuzzleLoader)

## How To Test Client & Server
For Client testing in the dev env, you can use the `gradle runClient` task
For Server testing in the dev env, you can use the `gradle runServer` task

## Build A Jar
For building, the usual `gradle buildMergedBundleJar` task can be used. The output will be in the `build/libs/` folder

## Updating Your Mod
Client & Server code now need to separate because of multiplayer, this mean in your mod's src folder you will have `src/client` for Client code & `src/main` for code that need to be on both Server & Client. `ClientModInitializer` is for load & rendering, like UI, graphics effects, etc. Some CR code can only be accessible under `src/client`
- [ClientModInitializer]() for client side
- [ModInitializer]() for both Server & Client side
- puzzle.mod.json now has `"formatVersion": 1`
- You can now have two mixin.json for server & client, if you want separation.
- Puzzle have its own loom call jigsaw, update your `build.gradle` & `gradle.properties` to example's ones.

## Notes
- Most project properties can be changed in the `gradle.properties`.
- To change author, description and stuff that is not there, edit `src/main/resources/puzzle.mod.json`.
- The project name is defined in `settings.gradle`.
- To add Puzzle mods in the build, make sure to use `internal` or `mod` rather than `implementation`.
- To bundle your dependencies use `bundle` and run task `gradle buildMergedBundleJar`.
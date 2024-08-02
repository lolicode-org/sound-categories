# Sound Categories

Fabric library that allows mods to add more sound categories, and modifies the Minecraft sound settings menu to fit as
many categories as required.

**This is a custom build of sound-categories, as the upstream is doing some refactoring and I need a stable version for my mod...**

## Adding to your project

As this build is primarily for my own use, I will not be publishing binaries to any repository. If you really need it, feel free to build it yourself.

Make sure to `include()` to embed the library into your mod, as otherwise the user will have to download
it separately.

## Adding a category

Classes that contain sound categories must implement `CategoryLoader` so that they are picked up by the loader. Every
SoundCategory you define must have the `Register` annotation - they will have the category reference injected once it is
created.

Example class:

```java
import dev.stashy.soundcategories.CategoryLoader;
import net.minecraft.sound.SoundCategory;

public class CustomCategories implements CategoryLoader
{
    @Register(master = true)
    public static SoundCategory MOD_MAIN;
    @Register
    public static SoundCategory SUBCATEGORY;
    @Register(name = "your_custom_name")
    public static SoundCategory CUSTOM_NAME;
    @Register(defaultLevel = 0f)
    public static SoundCategory OFF_BY_DEFAULT;
}
```

The register annotation has a few attributes:

* `master` will group all categories defined underneath it under one, accessible with a button next to the master
  category's slider.
* `name` allows you to set a custom name the category will be created under - useful if you want to have a
  simple `MASTER` variable set and not conflict with Minecraft's own master category.
* `defaultLevel`, quite obviously, is used as the default level the game creates your category with.

After implementing the loader, make sure you add the class as an entrypoint for `sound-categories` in
your `fabric.mod.json`.

## Localization

The newly added category needs language definitions to show any name, apart from its key. You can do so in your `lang`
folder. For an example, you can check the
[ExtraSounds](https://github.com/stashymane/extra-sounds/) language files.

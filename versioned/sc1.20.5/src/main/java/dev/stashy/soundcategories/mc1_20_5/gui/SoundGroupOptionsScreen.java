package dev.stashy.soundcategories.mc1_20_5.gui;

import dev.stashy.soundcategories.shared.gui.VersionedSoundGroupOptionsScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.sound.SoundCategory;

public class SoundGroupOptionsScreen extends VersionedSoundGroupOptionsScreen {
    public SoundGroupOptionsScreen(Screen parent, GameOptions gameOptions, SoundCategory category) {
        super(parent, gameOptions, category);
    }

    @Override
    protected void addParentCategoryWidget() {
        this.list.addReadOnlyCategory(parentCategory);
    }
}

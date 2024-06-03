package dev.stashy.soundcategories.mc1_20_3.gui;

import dev.stashy.soundcategories.shared.gui.VersionedElementListWrapper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.EntryListWidget;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.sound.SoundCategory;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class SoundList extends EntryListWidget<VersionedElementListWrapper.SoundEntry> implements VersionedElementListWrapper {
    public SoundList(MinecraftClient minecraftClient, int i, int j, int k, int l) {
        super(minecraftClient, i, j, k, l);
        this.centerListVertically = false;
    }

    public static SoundList init(MinecraftClient client, int width, int height, int top, int bottom, int itemHeight) {
        return new SoundList(client, width, bottom - top, top, itemHeight);
    }

    @Override
    public void setDimensionsImpl(int width, int height) {
        super.setDimensions(width, height);
    }

    @Override
    public boolean mouseScrolledImpl(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        return this.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
    }

    @Override
    protected int getScrollbarPositionX() {
        return super.getScrollbarPositionX() + 32;
    }

    @Override
    public int getRowWidth() {
        return 400;
    }

    @Override
    public int addSingleOptionEntry(SimpleOption<?> option, boolean editable) {
        var entry = VersionedElementListWrapper.SoundEntry.create(this.client.options, this.width, option);
        if (!editable) {
            entry.widgets.forEach(widget -> widget.active = false);
        }
        return this.addEntry(entry);
    }

    @Override
    public int addOptionEntry(SimpleOption<?> firstOption, @Nullable SimpleOption<?> secondOption) {
        return this.addEntry(VersionedElementListWrapper.SoundEntry.createDouble(this.client.options, this.width, firstOption, secondOption));
    }

    @Override
    public int addCategory(SoundCategory cat) {
        return this.addSingleOptionEntry(VersionedElementListWrapper.createCustomizedOption(this.client, cat));
    }

    @Override
    public int addReadOnlyCategory(SoundCategory cat) {
        return this.addSingleOptionEntry(VersionedElementListWrapper.createCustomizedOption(this.client, cat), false);
    }

    @Override
    public int addDoubleCategory(SoundCategory first, @Nullable SoundCategory second) {
        return this.addOptionEntry(VersionedElementListWrapper.createCustomizedOption(this.client, first),
                (second != null) ? VersionedElementListWrapper.createCustomizedOption(this.client, second) : null
        );
    }

    @Override
    public void addAllCategory(SoundCategory[] categories) {
        this.addAll(Arrays.stream(categories).map(cat -> VersionedElementListWrapper.createCustomizedOption(this.client, cat)).toArray(SimpleOption[]::new));
    }

    @Override
    public int addGroup(SoundCategory group, ButtonWidget.PressAction pressAction) {
        return super.addEntry(VersionedElementListWrapper.SoundEntry.createGroup(this.client.options, VersionedElementListWrapper.createCustomizedOption(this.client, group), this.width, pressAction));
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {

    }
}

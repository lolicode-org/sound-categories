package dev.stashy.soundcategories.mc1_19.option;

import dev.stashy.soundcategories.shared.option.VersionedSimpleOptionProvider;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;

public class SimpleOptionImpl extends VersionedSimpleOptionProvider {
    @Override
    public ClickableWidget createWidget(Object instance, GameOptions options, int x, int y, int width) {
        return ((SimpleOption<Double>) instance).createButton(options, x, y, width);
    }
}

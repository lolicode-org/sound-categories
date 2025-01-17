package dev.stashy.soundcategories.gui;

import dev.stashy.soundcategories.SoundCategories;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.ElementListWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.sound.SoundCategory;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SoundList extends ElementListWidget<SoundList.SoundEntry> {

    public SoundList(MinecraftClient minecraftClient, int i, int j, int k, int l) {
        super(minecraftClient, i, j, k, l);
        this.centerListVertically = false;
    }

    public int addSingleOptionEntry(SimpleOption<?> option) {
        return this.addSingleOptionEntry(option, true);
    }

    public int addSingleOptionEntry(SimpleOption<?> option, boolean editable) {
        var entry = SoundEntry.create(this.client.options, this.width, option);
        if (!editable) {
            entry.widgets.forEach(widget -> widget.active = false);
        }
        return this.addEntry(entry);
    }

    public int addOptionEntry(SimpleOption<?> firstOption, @Nullable SimpleOption<?> secondOption) {
        return this.addEntry(SoundEntry.createDouble(this.client.options, this.width, firstOption, secondOption));
    }

    public void addAll(SimpleOption<?>[] options) {
        for (int i = 0; i < options.length; i += 2) {
            this.addOptionEntry(options[i], i < options.length - 1 ? options[i + 1] : null);
        }
    }

    public int addCategory(SoundCategory cat) {
        return this.addSingleOptionEntry(this.createCustomizedOption(cat));
    }

    public int addReadOnlyCategory(SoundCategory cat) {
        return this.addSingleOptionEntry(this.createCustomizedOption(cat), false);
    }

    public int addDoubleCategory(SoundCategory first, @Nullable SoundCategory second) {
        return this.addOptionEntry(this.createCustomizedOption(first),
                (second != null) ? this.createCustomizedOption(second) : null
        );
    }

    public void addAllCategory(SoundCategory[] categories) {
        this.addAll(Arrays.stream(categories).map(this::createCustomizedOption).toArray(SimpleOption[]::new));
    }

    public int addGroup(SoundCategory group, ButtonWidget.PressAction pressAction) {
        return super.addEntry(SoundEntry.createGroup(this.client.options, this.createCustomizedOption(group), this.width, pressAction));
    }

    public int getRowWidth() {
        return 310;
    }

    private SimpleOption<?> createCustomizedOption(SoundCategory category) {
        final SimpleOption<Double> simpleOption = this.client.options.getSoundVolumeOption(category);
        if (SoundCategories.TOGGLEABLE_CATS.getOrDefault(category, false)) {
            return SimpleOption.ofBoolean(simpleOption.toString(), value -> {
                return Tooltip.of(SoundCategories.TOOLTIPS.getOrDefault(category, ScreenTexts.EMPTY));
            }, simpleOption.getValue() > 0, value -> {
                simpleOption.setValue(value ? 1.0 : 0.0);
            });
        }
        return simpleOption;
    }

    @Environment(EnvType.CLIENT)
    protected static class SoundEntry extends ElementListWidget.Entry<SoundList.SoundEntry> {
        List<? extends ClickableWidget> widgets;

        public SoundEntry(List<? extends ClickableWidget> w) {
            widgets = w;
        }

        public static SoundEntry create(GameOptions options, int width, SimpleOption<?> simpleOption) {
            return new SoundEntry(List.of(simpleOption.createWidget(options, width / 2 - 155, 0, 310)));
        }

        public static SoundEntry createDouble(GameOptions options, int width, SimpleOption<?> first, @Nullable SimpleOption<?> second) {
            List<ClickableWidget> widgets = new ArrayList<>();
            widgets.add(first.createWidget(options, width / 2 - 155, 0, 150));
            if (second != null) {
                widgets.add(second.createWidget(options, width / 2 + 5, 0, 150));
            }
            return new SoundEntry(widgets);
        }

        public static SoundEntry createGroup(GameOptions options, SimpleOption<?> group, int width, ButtonWidget.PressAction pressAction) {
            return new SoundEntry(
                    List.of(
                            group.createWidget(options, width / 2 - 155, 0, 280),
                            new TexturedButtonWidget(width / 2 + 135, 0, 20, 20, SoundCategories.SETTINGS_ICON, pressAction)
                    ));
        }

        public List<? extends Element> children() {
            return this.widgets;
        }

        public List<? extends Selectable> selectableChildren() {
            return this.widgets;
        }

        @Override
        public void render(DrawContext context, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
            int i = 0;
            int j = context.getScaledWindowWidth() / 2 - 155;

            for (ClickableWidget s : this.widgets) {
                s.setPosition(j + i, y);
                s.render(context, mouseX, mouseY, tickDelta);
                i += s.getWidth() + 10;
            }
        }
    }
}

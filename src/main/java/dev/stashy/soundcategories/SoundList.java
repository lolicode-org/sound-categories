package dev.stashy.soundcategories;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.ElementListWidget;
import net.minecraft.client.gui.widget.SoundSliderWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.sound.SoundCategory;

import java.util.ArrayList;
import java.util.List;

public class SoundList extends ElementListWidget<SoundList.SoundEntry>
{

    public SoundList(MinecraftClient minecraftClient, int i, int j, int k, int l, int m)
    {
        super(minecraftClient, i, j, k, l, m);
        this.centerListVertically = false;
    }

    public int addCategory(SoundCategory cat)
    {
        return super.addEntry(SoundEntry.create(cat, this.width));
    }

    public int getRowWidth()
    {
        return 400;
    }

    protected int getScrollbarPositionX()
    {
        return super.getScrollbarPositionX() + 32;
    }

    @Environment(EnvType.CLIENT)
    protected static class SoundEntry extends ElementListWidget.Entry<SoundList.SoundEntry>
    {
        List<ClickableWidget> sliders = new ArrayList<>();

        public SoundEntry(SoundSliderWidget w)
        {
            sliders.add(w);
        }

        public static SoundEntry create(SoundCategory cat, int width)
        {
            return new SoundEntry(
                    new SoundSliderWidget(MinecraftClient.getInstance(), width / 2 - 155, 0, cat, 310));
        }

        public List<? extends Element> children()
        {
            return this.sliders;
        }

        public List<? extends Selectable> selectableChildren()
        {
            return this.sliders;
        }

        @Override
        public void render(MatrixStack matrices, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta)
        {
            this.sliders.forEach((s) -> {
                s.y = y;
                s.render(matrices, mouseX, mouseY, tickDelta);
            });
        }
    }
}

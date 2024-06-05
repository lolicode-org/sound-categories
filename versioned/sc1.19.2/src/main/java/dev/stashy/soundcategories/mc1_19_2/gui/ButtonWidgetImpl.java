package dev.stashy.soundcategories.mc1_19_2.gui;

import dev.stashy.soundcategories.shared.gui.VersionedButtonWrapper;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

import java.util.function.Consumer;

public class ButtonWidgetImpl extends ButtonWidget implements VersionedButtonWrapper {
    public ButtonWidgetImpl(int x, int y, int width, int height, Text message, PressAction onPress, TooltipSupplier tooltipSupplier) {
        super(x, y, width, height, message, onPress, tooltipSupplier);
    }

    public static ButtonWidgetImpl init(int x, int y, int width, int height, Text message, ButtonWidget.PressAction callback, Text tooltipContent) {
        return new ButtonWidgetImpl(x, y, width, height, message, callback, new TooltipSupplier() {
            @Override
            public void onTooltip(ButtonWidget button, MatrixStack matrices, int mouseX, int mouseY) {
            }

            @Override
            public void supply(Consumer<Text> consumer) {
                consumer.accept(tooltipContent);
            }
        });
    }
}

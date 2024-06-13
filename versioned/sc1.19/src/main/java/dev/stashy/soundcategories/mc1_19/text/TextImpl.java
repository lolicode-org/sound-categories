package dev.stashy.soundcategories.mc1_19.text;

import dev.stashy.soundcategories.shared.text.VersionedText;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

public class TextImpl extends VersionedText {
    @Override
    public Text empty() {
        return Text.empty();
    }

    public Text getDoneText() {
        return ScreenTexts.DONE;
    }

    public Text getCancelText() {
        return ScreenTexts.CANCEL;
    }

    public Text translatable(String key) {
        return Text.translatable(key);
    }
}

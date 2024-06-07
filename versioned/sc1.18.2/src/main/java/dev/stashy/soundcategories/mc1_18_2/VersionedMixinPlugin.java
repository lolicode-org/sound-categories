package dev.stashy.soundcategories.mc1_18_2;

import me.lonefelidae16.groominglib.api.AbstractVersionedMixinPlugin;

public final class VersionedMixinPlugin extends AbstractVersionedMixinPlugin {
    @Override
    protected String startVersion() {
        return "1.18.2";
    }

    @Override
    protected String endVersion() {
        return "1.19.2";
    }
}

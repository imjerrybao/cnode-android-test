package cmgine.com.cnode_android_test.model.entity;

import android.support.annotation.StringRes;

import cmgine.com.cnode_android_test.R;

/**
 * Created by Jerry on 25/01/2017.
 */

public enum TabType {

    all(R.string.tab_all),

    good(R.string.tab_good),

    share(R.string.tab_share),

    ask(R.string.tab_ask),

    job(R.string.tab_job);

    @StringRes
    private int nameId;

    TabType(int nameId) {
        this.nameId = nameId;
    }

    @StringRes
    public int getNameId() {
        return nameId;
    }
}

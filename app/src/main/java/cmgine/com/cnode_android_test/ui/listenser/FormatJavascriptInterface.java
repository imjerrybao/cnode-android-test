package cmgine.com.cnode_android_test.ui.listenser;

import android.webkit.JavascriptInterface;

import org.joda.time.DateTime;

import cmgine.com.cnode_android_test.utils.FormatUtils;

/**
 * Created by Jerry on 26/01/2017.
 */

public final class FormatJavascriptInterface {

    public static final FormatJavascriptInterface instance = new FormatJavascriptInterface();
    public static final String NAME = "formatBridge";

    private FormatJavascriptInterface() {}

    @JavascriptInterface
    public String getRelativeTimeSpanString(String time) {
        return FormatUtils.getRelativeTimeSpanString(new DateTime(time));
    }

}

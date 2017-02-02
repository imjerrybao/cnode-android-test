package cmgine.com.cnode_android_test.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * Created by Jerry on 25/01/2017.
 */

public final class ToastUtils {

    private volatile static ToastUtils singleton;

    public static ToastUtils with(@NonNull Context context) {
        if (singleton == null) {
            synchronized (ToastUtils.class) {
                if (singleton == null) {
                    singleton = new ToastUtils(context);
                }
            }
        }
        return singleton;
    }

    private final Toast toast;

    @SuppressLint("ShowToast")
    private ToastUtils(@NonNull Context context) {
        toast = Toast.makeText(context.getApplicationContext(), null, Toast.LENGTH_SHORT);
    }

    public void show(CharSequence msg) {
        toast.setText(msg);
        toast.show();
    }

    public void show(@StringRes int resId) {
        toast.setText(resId);
        toast.show();
    }

}

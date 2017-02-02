package cmgine.com.cnode_android_test.utils;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by Jerry on 26/01/2017.
 */

public final class HandlerUtils {

    private HandlerUtils() {}

    private static final Handler handler = new Handler(Looper.getMainLooper());

    public static boolean post(Runnable r) {
        return handler.post(r);
    }

    public static boolean postDelayed(Runnable r, long delayMillis) {
        return handler.postDelayed(r, delayMillis);
    }

}

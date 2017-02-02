package cmgine.com.cnode_android_test.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;

/**
 * Created by Jerry on 26/01/2017.
 */

public final class ActivityUtils {

    private ActivityUtils() {}

    public static boolean isAlive(Activity activity) {
        return activity != null && !(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && activity.isDestroyed()) && !activity.isFinishing();
    }

    public static void recreate(@NonNull Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            activity.recreate();
        } else {
            Intent intent = activity.getIntent();
            intent.setClass(activity, activity.getClass());
            activity.startActivity(intent);
            activity.finish();
            activity.overridePendingTransition(0, 0);
        }
    }

    public static void recreateDelayed(@NonNull final Activity activity) {
        HandlerUtils.post(new Runnable() {

            @Override
            public void run() {
                recreate(activity);
            }

        });
    }

}

package cmgine.com.cnode_android_test.model.api;

import android.app.Activity;
import android.support.annotation.NonNull;

import cmgine.com.cnode_android_test.model.entity.Result;
import cmgine.com.cnode_android_test.utils.ToastUtils;
import okhttp3.Headers;

/**
 * Created by Jerry on 26/01/2017.
 */

public class DefaultCallback<T extends Result> extends ForegroundCallback<T> {

    public DefaultCallback(@NonNull Activity activity) {
        super(activity);
    }

    @Override
    public boolean onResultError(int code, Headers Headers, Result.Error error) {
        if(code == 401) {
            return onResultAuthError(code, Headers, error);
        } else {
            return onResultOtherError(code, Headers, error);
        }
    }

    public boolean onResultAuthError(int code, Headers Headers, Result.Error error) {
        ToastUtils.with(getActivity()).show("AccessToken无效");
        return false;
    }

    public boolean onResultOtherError(int code, Headers Headers, Result.Error error) {
        ToastUtils.with(getActivity()).show(error.getErrorMessage());
        return false;
    }
}

package cmgine.com.cnode_android_test.model.api;

import android.app.Activity;
import android.support.annotation.NonNull;

import cmgine.com.cnode_android_test.model.entity.Result;
import cmgine.com.cnode_android_test.utils.ActivityUtils;
import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jerry on 26/01/2017.
 */

public class ForegroundCallback<T extends Result> implements Callback<T>, CallbackLifeCircle<T> {

    private final Activity activity;

    public ForegroundCallback(@NonNull Activity activity) {
        this.activity = activity;
    }

    public Activity getActivity() {
        return activity;
    }

    @Override
    public final void onResponse(Call<T> call, Response<T> response) {
        if(ActivityUtils.isAlive(activity)) {
            boolean interrupt;
            if(response.isSuccessful()) {
                interrupt = onResultOk(response.code(), response.headers(), response.body());
            } else {
                interrupt = onResultError(response.code(), response.headers(), Result.buildError(response));
            }
            if(!interrupt) {
                onFinish();
            }
        }
    }

    @Override
    public final void onFailure(Call<T> call, Throwable t) {
        if(ActivityUtils.isAlive(activity)) {
            boolean interrupt;
            if(call.isCanceled()) {
                interrupt = onCallCancel();
            } else {
                interrupt = onCallException(t, Result.buildError(t));
            }
            if(!interrupt) {
                onFinish();
            }
        }
    }

    @Override
    public boolean onResultOk(int code, Headers headers, T result) {
        return false;
    }

    @Override
    public boolean onResultError(int code, Headers Headers, Result.Error error) {
        return false;
    }

    @Override
    public boolean onCallCancel() {
        return false;
    }

    @Override
    public boolean onCallException(Throwable t, Result.Error error) {
        return false;
    }

    @Override
    public void onFinish() {

    }
}
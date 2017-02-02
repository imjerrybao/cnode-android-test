package cmgine.com.cnode_android_test.model.api;

import cmgine.com.cnode_android_test.model.entity.Result;
import okhttp3.Headers;

/**
 * Created by Jerry on 26/01/2017.
 */

public interface CallbackLifeCircle<T extends Result> {

    boolean onResultOk(int code, Headers headers, T result);

    boolean onResultError(int code, Headers Headers, Result.Error error);

    boolean onCallCancel();

    boolean onCallException(Throwable t, Result.Error error);

    void onFinish();

}

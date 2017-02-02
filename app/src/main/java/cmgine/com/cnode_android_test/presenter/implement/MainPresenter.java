package cmgine.com.cnode_android_test.presenter.implement;

import android.app.Activity;
import android.graphics.pdf.PdfDocument;
import android.support.annotation.NonNull;

import java.util.List;

import cmgine.com.cnode_android_test.model.api.ApiClient;
import cmgine.com.cnode_android_test.model.api.ApiDefine;
import cmgine.com.cnode_android_test.model.api.ForegroundCallback;
import cmgine.com.cnode_android_test.model.entity.Result;
import cmgine.com.cnode_android_test.model.entity.TabType;
import cmgine.com.cnode_android_test.model.entity.Topic;
import cmgine.com.cnode_android_test.presenter.contract.IMainPresenter;
import cmgine.com.cnode_android_test.ui.view.IMainView;
import cmgine.com.cnode_android_test.utils.ToastUtils;
import okhttp3.Headers;
import retrofit2.Call;

/**
 * Created by Jerry on 25/01/2017.
 */

public class MainPresenter implements IMainPresenter {

    private static final int PAGE_LIMIT = 20;
    private final Activity activity;
    private final IMainView mainView;
    private TabType tab = TabType.all;
    private Call<Result.Data<List<Topic>>> refreshCall = null;
    private Call<Result.Data<List<Topic>>> loadMoreCall = null;

    public MainPresenter(@NonNull Activity activity, @NonNull IMainView mainView) {
        this.activity = activity;
        this.mainView = mainView;
    }

    private void cancelRefreshCall() {
        if(refreshCall != null) {
            if(refreshCall.isCanceled()) {
                refreshCall.cancel();
            }
            refreshCall = null;
        }
    }
    private void cancelLoadMoreCall() {
        if(loadMoreCall != null) {
            if(loadMoreCall.isCanceled()) {
                loadMoreCall.cancel();
            }
            loadMoreCall = null;
        }
    }

    @Override
    public void switchTab(@NonNull TabType tab) {
        if(this.tab != tab) {
            this.tab = tab;
            cancelRefreshCall();
            cancelLoadMoreCall();
            mainView.onSwitchTabOk(tab);
        }
    }

    @Override
    public void refreshTopicListAsyncTask() {
        if(refreshCall == null) {
            refreshCall = ApiClient.service.getTopicList(tab, 1, PAGE_LIMIT, ApiDefine.MD_RENDER);
            refreshCall.enqueue(new ForegroundCallback<Result.Data<List<Topic>>>(activity) {

                @Override
                public boolean onResultOk(int code, Headers headers, Result.Data<List<Topic>> result) {
                    cancelLoadMoreCall();
                    mainView.onRefreshTopicListOk(result.getData());
                    return false;
                }

                @Override
                public boolean onResultError(int code, Headers Headers, Result.Error error) {
                    ToastUtils.with(getActivity()).show(error.getErrorMessage());
                    return false;
                }

                @Override
                public boolean onCallException(Throwable t, Result.Error error) {
                    ToastUtils.with(getActivity()).show(error.getErrorMessage());
                    return false;
                }

                @Override
                public boolean onCallCancel() {
                    return super.onCallCancel();
                }

                @Override
                public void onFinish() {
                    refreshCall = null;
                    mainView.onRefreshTopicListFinish();
                }
            });
        }
    }

    @Override
    public void loadMoreTopicListAsyncTask(int page) {

    }

    @Override
    public void getUserAsyncTask() {

    }

    @Override
    public void getMessageCountAsyncTask() {

    }
}

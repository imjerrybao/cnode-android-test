package cmgine.com.cnode_android_test.presenter.implement;

import android.app.Activity;
import android.support.annotation.NonNull;

import cmgine.com.cnode_android_test.model.api.ApiClient;
import cmgine.com.cnode_android_test.model.api.DefaultCallback;
import cmgine.com.cnode_android_test.model.entity.Result;
import cmgine.com.cnode_android_test.presenter.contract.ITopicHeaderPresenter;
import cmgine.com.cnode_android_test.ui.view.ITopicHeaderView;
import okhttp3.Headers;

/**
 * Created by Jerry on 26/01/2017.
 */

public class TopicHeaderPresenter implements ITopicHeaderPresenter {

    private final Activity activity;
    private final ITopicHeaderView topicHeaderView;

    public TopicHeaderPresenter(@NonNull Activity activity, @NonNull ITopicHeaderView topicHeaderView) {
        this.activity = activity;
        this.topicHeaderView = topicHeaderView;
    }

    @Override
    public void collectTopicAsyncTask(@NonNull String topicId) {
        ApiClient.service.collectTopic(null, topicId).enqueue(new DefaultCallback<Result>(activity) {

            @Override
            public boolean onResultOk(int code, Headers headers, Result result) {
                topicHeaderView.onCollectTopicOk();
                return false;
            }

        });
    }

    @Override
    public void decollectTopicAsyncTask(@NonNull String topicId) {
        ApiClient.service.decollectTopic(null, topicId).enqueue(new DefaultCallback<Result>(activity) {

            @Override
            public boolean onResultOk(int code, Headers headers, Result result) {
                topicHeaderView.onDecollectTopicOk();
                return false;
            }

        });
    }

}

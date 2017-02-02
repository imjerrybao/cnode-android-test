package cmgine.com.cnode_android_test.presenter.implement;

import android.app.Activity;
import android.support.annotation.NonNull;

import java.util.List;

import cmgine.com.cnode_android_test.model.api.ApiClient;
import cmgine.com.cnode_android_test.model.api.ApiDefine;
import cmgine.com.cnode_android_test.model.api.DefaultCallback;
import cmgine.com.cnode_android_test.model.api.ForegroundCallback;
import cmgine.com.cnode_android_test.model.entity.Result;
import cmgine.com.cnode_android_test.model.entity.Topic;
import cmgine.com.cnode_android_test.model.entity.TopicWithReply;
import cmgine.com.cnode_android_test.presenter.contract.ITopicPresenter;
import cmgine.com.cnode_android_test.ui.view.ITopicView;
import okhttp3.Headers;

/**
 * Created by Jerry on 26/01/2017.
 */

public class TopicPresenter implements ITopicPresenter {

    private Activity activity;
    private ITopicView topicView;

    public TopicPresenter(Activity activity, ITopicView topicView) {
        this.activity = activity;
        this.topicView = topicView;
    }

    @Override
    public void getTopicAsyncTask(@NonNull String topicId) {

        ApiClient.service.getTopicById(topicId, null, ApiDefine.MD_RENDER)
                .enqueue(new DefaultCallback<Result.Data<TopicWithReply>>(activity) {

                    @Override
                    public boolean onResultOk(int code, Headers headers, Result.Data<TopicWithReply> result) {
                        topicView.onGetTopicOk(result.getData());
                        return false;
                    }

                    @Override
                    public void onFinish() {
                        topicView.onGetTopicFinish();
                    }

                });
    }
}

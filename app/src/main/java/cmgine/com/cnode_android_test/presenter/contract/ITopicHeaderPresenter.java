package cmgine.com.cnode_android_test.presenter.contract;

import android.support.annotation.NonNull;

/**
 * Created by Jerry on 26/01/2017.
 */

public interface ITopicHeaderPresenter {

    void collectTopicAsyncTask(@NonNull String topicId);

    void decollectTopicAsyncTask(@NonNull String topicId);

}

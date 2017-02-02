package cmgine.com.cnode_android_test.presenter.contract;

import android.support.annotation.NonNull;

import cmgine.com.cnode_android_test.model.entity.TabType;

/**
 * Created by Jerry on 25/01/2017.
 */

public interface IMainPresenter {
    void switchTab(@NonNull TabType tab);
    void refreshTopicListAsyncTask();
    void loadMoreTopicListAsyncTask(int page);
    void getUserAsyncTask();
    void getMessageCountAsyncTask();
}

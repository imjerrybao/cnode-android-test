package cmgine.com.cnode_android_test.ui.view;

import android.support.annotation.NonNull;

import java.util.List;

import cmgine.com.cnode_android_test.model.entity.TabType;
import cmgine.com.cnode_android_test.model.entity.Topic;

/**
 * Created by Jerry on 25/01/2017.
 */

public interface IMainView {

    void onSwitchTabOk(@NonNull TabType tab);

    void onRefreshTopicListOk(@NonNull List<Topic> topicList);

    void onRefreshTopicListFinish();

    void onLoadMoreTopicListOk(@NonNull List<Topic> topicList);

//    void onLoadMoreTopicListFinish(@LoadMoreFooter.State int state);

    void updateUserInfoViews();

    void updateMessageCountViews(int count);

}

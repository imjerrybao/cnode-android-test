package cmgine.com.cnode_android_test.ui.view;

import android.support.annotation.NonNull;

import cmgine.com.cnode_android_test.model.entity.Reply;
import cmgine.com.cnode_android_test.model.entity.TopicWithReply;

/**
 * Created by Jerry on 26/01/2017.
 */

public interface ITopicView {
    void onGetTopicOk(@NonNull TopicWithReply topic);
    void onGetTopicFinish();
    void appendReplyAndUpdateViews(@NonNull Reply reply);
}

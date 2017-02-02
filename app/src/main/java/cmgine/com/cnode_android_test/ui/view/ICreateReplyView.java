package cmgine.com.cnode_android_test.ui.view;

import android.support.annotation.NonNull;

import cmgine.com.cnode_android_test.model.entity.Reply;

/**
 * Created by Jerry on 26/01/2017.
 */

public interface ICreateReplyView {

    void showWindow();

    void dismissWindow();

    void onAt(@NonNull Reply target, @NonNull Integer targetPosition);

    void onContentError(@NonNull String message);

    void onReplyTopicOk(@NonNull Reply reply);

    void onReplyTopicStart();

    void onReplyTopicFinish();

}

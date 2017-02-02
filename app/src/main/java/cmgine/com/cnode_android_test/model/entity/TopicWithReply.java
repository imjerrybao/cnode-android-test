package cmgine.com.cnode_android_test.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Jerry on 26/01/2017.
 */

public class TopicWithReply extends Topic {

    @SerializedName("is_collect")
    private boolean isCollect;

    @SerializedName("replies")
    private List<Reply> replyList;

    public boolean isCollect() {

        return isCollect;
    }

    public List<Reply> getReplyList() {
        return replyList;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }

}

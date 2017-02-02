package cmgine.com.cnode_android_test.model.entity;

import com.google.gson.annotations.SerializedName;

import cmgine.com.cnode_android_test.utils.FormatUtils;

/**
 * Created by Jerry on 25/01/2017.
 */

public class Author {

    @SerializedName("loginname")
    private String loginName;

    @SerializedName("avatar_url")
    private String avatarUrl;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getAvatarUrl() { // 修复头像地址的历史遗留问题
        return FormatUtils.getCompatAvatarUrl(avatarUrl);
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

}

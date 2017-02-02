package cmgine.com.cnode_android_test.model.api;

import java.util.List;

import cmgine.com.cnode_android_test.model.entity.Result;
import cmgine.com.cnode_android_test.model.entity.TabType;
import cmgine.com.cnode_android_test.model.entity.Topic;
import cmgine.com.cnode_android_test.model.entity.TopicWithReply;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Jerry on 25/01/2017.
 */

public interface ApiService {

    @GET("topics")
    Call<Result.Data<List<Topic>>> getTopicList(
            @Query("tab") TabType tab,
            @Query("page") Integer page,
            @Query("limit") Integer limit,
            @Query("mdrender") Boolean mdrender
    );

    @GET("topic/{topicId}")
    Call<Result.Data<TopicWithReply>> getTopicById(
            @Path("topicId") String topicId,
            @Query("accessToken") String accessToken,
            @Query("mdrender") Boolean mdrender
    );

    //=========
    // 话题收藏
    //=========

    @POST("topic_collect/collect")
    @FormUrlEncoded
    Call<Result> collectTopic(
            @Field("accesstoken") String accessToken,
            @Field("topic_id") String topicId
    );

    @POST("topic_collect/de_collect")
    @FormUrlEncoded
    Call<Result> decollectTopic(
            @Field("accesstoken") String accessToken,
            @Field("topic_id") String topicId
    );

    @GET("topic_collect/{loginName}")
    Call<Result.Data<List<Topic>>> getCollectTopicList(
            @Path("loginName") String loginName
    );


}

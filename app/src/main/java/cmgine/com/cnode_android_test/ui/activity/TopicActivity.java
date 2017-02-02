package cmgine.com.cnode_android_test.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cmgine.com.cnode_android_test.R;
import cmgine.com.cnode_android_test.model.entity.Reply;
import cmgine.com.cnode_android_test.model.entity.Topic;
import cmgine.com.cnode_android_test.model.entity.TopicWithReply;
import cmgine.com.cnode_android_test.presenter.contract.ITopicHeaderPresenter;
import cmgine.com.cnode_android_test.presenter.contract.ITopicPresenter;
import cmgine.com.cnode_android_test.presenter.implement.TopicHeaderPresenter;
import cmgine.com.cnode_android_test.presenter.implement.TopicPresenter;
import cmgine.com.cnode_android_test.ui.listenser.TopicJavascriptInterface;
import cmgine.com.cnode_android_test.ui.view.ICreateReplyView;
import cmgine.com.cnode_android_test.ui.view.IReplyPresenter;
import cmgine.com.cnode_android_test.ui.view.IReplyView;
import cmgine.com.cnode_android_test.ui.view.ITopicHeaderView;
import cmgine.com.cnode_android_test.ui.view.ITopicView;
import cmgine.com.cnode_android_test.ui.widget.TopicWebView;
import cmgine.com.cnode_android_test.utils.RefreshUtils;

/**
 * Created by Jerry on 25/01/2017.
 */

public class TopicActivity extends AppCompatActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, ITopicView, ITopicHeaderView, IReplyView {

    @BindView(R.id.toolbar)
    protected Toolbar toolBar;

    @BindView(R.id.refresh_layout)
    protected SwipeRefreshLayout refreshLayout;

    @BindView(R.id.webview)
    protected TopicWebView webView;

    @BindView(R.id.icon_no_data)
    protected TextView iconNoData;

    private String topicId;
    private TopicWithReply topic;

    private ICreateReplyView createReplyView;

    private ITopicPresenter topicPresenter;
    private ITopicHeaderPresenter topicHeaderPresenter;
    private IReplyPresenter replyPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
        ButterKnife.bind(this);

        toolBar.setNavigationOnClickListener(this);
        topicPresenter = new TopicPresenter(this, this);

        topicId = getIntent().getStringExtra("topicId");

        topicPresenter = new TopicPresenter(this, this);
        topicHeaderPresenter = new TopicHeaderPresenter(this, this);

//        replyPresenter = new ReplyPresenter(this, this);
//        createReplyView = CreateReplyDialog.createWithAutoTheme(this, topicId, this);

        webView.setBridgeAndLoadPage(new TopicJavascriptInterface(this, createReplyView, topicHeaderPresenter, replyPresenter));

        RefreshUtils.init(refreshLayout, this);
        RefreshUtils.refresh(refreshLayout, this);
    }

    @Override
    public void onClick(View v) {
        if(v.getParent() == toolBar) {
            ActivityCompat.finishAfterTransition(this);
        }
    }

    @Override
    public void onRefresh() {
        topicPresenter.getTopicAsyncTask(topicId);
    }

    @Override
    public void onGetTopicOk(@NonNull TopicWithReply topic) {
        this.topic = topic;
        iconNoData.setVisibility(View.GONE);
        webView.updateTopicAndUserId(topic, null);
    }

    @Override
    public void onGetTopicFinish() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void appendReplyAndUpdateViews(@NonNull Reply reply) {

    }

    @Override
    public void onUpReplyOk(@NonNull Reply reply) {

    }

    @Override
    public void onCollectTopicOk() {

    }

    @Override
    public void onDecollectTopicOk() {

    }
}

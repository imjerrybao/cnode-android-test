package cmgine.com.cnode_android_test.ui.activity;

import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.melnykov.fab.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cmgine.com.cnode_android_test.R;
import cmgine.com.cnode_android_test.model.entity.TabType;
import cmgine.com.cnode_android_test.model.entity.Topic;
import cmgine.com.cnode_android_test.presenter.contract.IMainPresenter;
import cmgine.com.cnode_android_test.presenter.implement.MainPresenter;
import cmgine.com.cnode_android_test.ui.adapter.TopicListAdapter;
import cmgine.com.cnode_android_test.ui.view.IMainView;
import cmgine.com.cnode_android_test.utils.RefreshUtils;

public class MainActivity extends AppCompatActivity implements IMainView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.drawer_layout)
    protected DrawerLayout drawerLayout;

    @BindView(R.id.toolbar)
    protected Toolbar toolBar;

    @BindViews({
            R.id.btn_nav_all,
            R.id.btn_nav_good,
            R.id.btn_nav_share,
            R.id.btn_nav_ask,
            R.id.btn_nav_job
    })
    protected List<CheckedTextView> navMainItemList;

    private TopicListAdapter adapter;

    @BindView(R.id.list_view)
    protected ListView listView;

    @BindView(R.id.fab_create_topic)
    protected FloatingActionButton fabCreateTopic;

    private IMainPresenter mainPresenter;

    @BindView(R.id.icon_no_data)
    protected TextView iconNoData;

    @BindView(R.id.refresh_layout)
    protected SwipeRefreshLayout refreshLayout;

    private int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        drawerLayout.setDrawerShadow(R.drawable.navigation_drawer_shadow, GravityCompat.START);
        drawerLayout.addDrawerListener(drawerListener);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        adapter = new TopicListAdapter(this);
        listView.setAdapter(adapter);
        fabCreateTopic.attachToListView(listView);

        mainPresenter = new MainPresenter(this, this);

        RefreshUtils.init(refreshLayout, this);
        RefreshUtils.refresh(refreshLayout, this);
    }


    @Override
    public void onSwitchTabOk(@NonNull TabType tab) {
        page = 0;
        toolBar.setTitle(tab.getNameId());
        adapter.getTopicList().clear();
        adapter.notifyDataSetChanged();
        iconNoData.setVisibility(View.VISIBLE);
        fabCreateTopic.show(true);
        refreshLayout.setRefreshing(true);
        onRefresh();
    }


    @Override
    public void onRefresh() {
        mainPresenter.refreshTopicListAsyncTask();
    }

    @Override
    public void onRefreshTopicListOk(@NonNull List<Topic> topicList) {

        adapter.getTopicList().clear();
        adapter.getTopicList().addAll(topicList);
        adapter.notifyDataSetChanged();
        if(adapter.getTopicList().isEmpty()) {
            iconNoData.setVisibility(View.VISIBLE);
        } else {
            iconNoData.setVisibility(View.GONE);

        }
        page = 1;
    }

    @Override
    public void onRefreshTopicListFinish() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void onLoadMoreTopicListOk(@NonNull List<Topic> topicList) {

    }

    @Override
    public void updateUserInfoViews() {

    }

    @Override
    public void updateMessageCountViews(int count) {

    }

    @OnClick({
            R.id.btn_nav_all,
            R.id.btn_nav_good,
            R.id.btn_nav_share,
            R.id.btn_nav_ask,
            R.id.btn_nav_job
    })
    public void mainNavItemOnClicked(CheckedTextView itemView) {
        for(CheckedTextView navItem : navMainItemList) {
            navItem.setChecked(navItem.getId() == itemView.getId());
        }
        drawerLayout.closeDrawers();
    }

    private final DrawerLayout.DrawerListener drawerListener = new DrawerLayout.SimpleDrawerListener() {
        @Override
        public void onDrawerOpened(View drawerView) {
            super.onDrawerOpened(drawerView);
        }

        @Override
        public void onDrawerClosed(View drawerView) {
            super.onDrawerClosed(drawerView);
            TabType tab = TabType.all;
            for(CheckedTextView navItem : navMainItemList) {
                if(navItem.isChecked()) {
                    switch (navItem.getId()) {
                        case R.id.btn_nav_all:
                            tab = TabType.all;
                            break;
                        case R.id.btn_nav_good:
                            tab = TabType.good;
                            break;
                        case R.id.btn_nav_share:
                            tab = TabType.share;
                            break;
                        case R.id.btn_nav_ask:
                            tab = TabType.ask;
                            break;
                        case R.id.btn_nav_job:
                            tab = TabType.job;
                            break;
                        default:
                            throw new AssertionError("Unknown tab.");
                    }
                    break;
                }
            }
            mainPresenter.switchTab(tab);
        }
    };

}

package cmgine.com.cnode_android_test.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cmgine.com.cnode_android_test.R;
import cmgine.com.cnode_android_test.model.entity.Topic;
import cmgine.com.cnode_android_test.ui.activity.TopicActivity;
import cmgine.com.cnode_android_test.utils.EntityUtils;
import cmgine.com.cnode_android_test.utils.FormatUtils;

/**
 * Created by Jerry on 25/01/2017.
 */

public class TopicListAdapter extends BaseAdapter {

    private final Activity activity;
    private final LayoutInflater inflater;
    private final List<Topic> topicList = new ArrayList<>();

    public TopicListAdapter(@NonNull Activity activity) {
        this.activity = activity;
        inflater = LayoutInflater.from(activity);
    }

    public List<Topic> getTopicList() {
        return topicList;
    }

    @Override
    public int getCount() {
        return topicList.size();
    }

    @Override
    public Object getItem(int position) {
        return topicList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_topic, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.update(topicList.get(position));
        return convertView;
    }

    protected class ViewHolder {

        protected Topic topic;

        @BindView(R.id.ctv_tab)
        protected CheckedTextView ctvTab;

        @BindView(R.id.tv_title)
        protected TextView tvTitle;

        @BindView(R.id.tv_avatar)
        protected ImageView imgAvatar;

        @BindView(R.id.tv_author)
        protected TextView tvAuthor;

        @BindView(R.id.tv_reply_count)
        protected TextView tvReplyCount;

        @BindView(R.id.tv_visit_count)
        protected TextView tvVisitCount;

        @BindView(R.id.tv_create_time)
        protected TextView tvCreateTime;

        @BindView(R.id.tv_last_reply_time)
        protected TextView getTvReplyTime;

        @BindView(R.id.icon_good)
        protected ImageView iconGood;

        protected ViewHolder(@NonNull View itemView) {
            ButterKnife.bind(this, itemView);
        }

        protected void update(@NonNull Topic topic) {
            this.topic = topic;

            ctvTab.setText(topic.isTop() ? R.string.tab_top : topic.getTab().getNameId());
            tvTitle.setText(topic.getTitle());
            ctvTab.setChecked(topic.isTop());
            Glide.with(activity).load(topic.getAuthor().getAvatarUrl()).placeholder(R.drawable.image_placeholder).dontAnimate().into(imgAvatar);
            System.out.println(topic.getAuthor().getAvatarUrl());
            tvAuthor.setText(topic.getAuthor().getLoginName());
            tvCreateTime.setText(activity.getString(R.string.create_at_$s, topic.getCreateAt().toString("yyyy-MM-dd HH:mm:ss")));
            tvReplyCount.setText(String.valueOf(topic.getReplyCount()));
            tvVisitCount.setText(String.valueOf(topic.getVisitCount()));
            getTvReplyTime.setText(FormatUtils.getRelativeTimeSpanString(topic.getLastReplyAt()));
            iconGood.setVisibility(topic.isGood() ? View.VISIBLE : View.GONE);
        }

        @OnClick(R.id.btn_item)
        protected void topicItemOnClicked() {
            Intent intent = new Intent(activity, TopicActivity.class);
            intent.putExtra("topicId", topic.getId());
            intent.putExtra("topic", EntityUtils.gson.toJson(topic));
            activity.startActivity(intent);
        }
    }
}

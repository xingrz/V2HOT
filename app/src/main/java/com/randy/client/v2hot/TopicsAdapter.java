package com.randy.client.v2hot;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.v2ex.api.Topic;

import java.util.List;

public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView username;
        public TextView overview;

        public ViewHolder(View v) {
            super(v);
            this.title = (TextView) v.findViewById(R.id.title);
            this.username = (TextView) v.findViewById(R.id.username);
            this.overview = (TextView) v.findViewById(R.id.overview);
        }

    }

    public static interface OnItemClickListener {

        public void onItemClick(Topic topic);

    }

    private final LayoutInflater inflater;
    private final OnItemClickListener listener;

    private List<Topic> topics;

    public TopicsAdapter(Context context, OnItemClickListener listener) {
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        return new ViewHolder(inflater.inflate(R.layout.topic_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int i) {
        final Topic item = topics.get(i);

        holder.title.setText(item.title);
        holder.username.setText(item.member.username);
        holder.overview.setText(item.content);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return topics == null ? 0 : topics.size();
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
        notifyDataSetChanged();
    }

}

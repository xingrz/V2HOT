package com.randy.client.v2hot;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.v2ex.api.Reply;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class RepliesAdapter extends RecyclerView.Adapter<RepliesAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView avatar;
        public TextView username;
        public TextView content;

        public ViewHolder(View itemView) {
            super(itemView);
            this.avatar = (ImageView) itemView.findViewById(R.id.avatar);
            this.username = (TextView) itemView.findViewById(R.id.username);
            this.content = (TextView) itemView.findViewById(R.id.content);
        }
    }

    private static final DisplayImageOptions IMAGE_OPTIONS = new DisplayImageOptions.Builder()
            .cacheOnDisk(true)
            .cacheInMemory(true)
            .build();

    private final LayoutInflater inflater;
    private final String hostUsername;

    private List<Reply> replies;

    public RepliesAdapter(Context context, String hostUsername) {
        this.inflater = LayoutInflater.from(context);
        this.hostUsername = hostUsername;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        return new ViewHolder(inflater.inflate(R.layout.reply_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        final Reply item = replies.get(i);

        String username = item.member.username;
        holder.username.setText(username.equals(hostUsername) ? username + "(楼主)" : username);

        holder.content.setText(item.content);

        String avatar = item.member.avatarMini;
        if (avatar.startsWith("//")) {
            avatar = "https:" + avatar;
        }

        ImageLoader.getInstance().displayImage(avatar, holder.avatar, IMAGE_OPTIONS);
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        holder.avatar.setImageBitmap(null);
    }

    @Override
    public int getItemCount() {
        return replies == null ? 0 : replies.size();
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
        notifyDataSetChanged();
    }

}

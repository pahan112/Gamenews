package project.company.com.gamenews.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import project.company.com.gamenews.R;
import project.company.com.gamenews.model.News;

/**
 * Created by Pahan on 08.10.2017.
 */

public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.StoriesViewHolder> {

    private List<News> news = new ArrayList<>();

    public StoriesAdapter(List<News> news) {
        this.news = news;
    }

    @Override
    public StoriesAdapter.StoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stories, parent, false);
        return new StoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StoriesAdapter.StoriesViewHolder holder, int position) {
        holder.bind(news.get(position));
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public void setNewsList(List<News> news) {
        this.news = news;
        notifyDataSetChanged();
    }

    public class StoriesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView textViewTitle;
        @BindView(R.id.iv_news)
        ImageView imageViewNews;
        @BindView(R.id.tv_link)
        TextView textViewLink;
        @BindView(R.id.tv_time)
        TextView textViewTime;

        public StoriesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(News news) {
            textViewTitle.setText(news.getName());
            Glide
                    .with(itemView.getContext())
                    .load(news.getCover())
                    .into(imageViewNews);
            String link = news.getLink();
            String[] arr = link.split("/");
            textViewLink.setText(arr[2]);
            int hours = (int) (((System.currentTimeMillis() / (1000 * 60 * 60) )- (news.getDate() / (1000 * 60 * 60))) % 24);
            textViewTime.setText(" - " + hours + " hours ago");
        }
    }
}

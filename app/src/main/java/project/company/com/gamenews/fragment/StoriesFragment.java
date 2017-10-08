package project.company.com.gamenews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import project.company.com.gamenews.R;
import project.company.com.gamenews.adapter.StoriesAdapter;
import project.company.com.gamenews.model.News;
import project.company.com.gamenews.retrofit.RetrofitMain;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pahan on 07.10.2017.
 */

public class StoriesFragment extends Fragment {

    private List<News> mNews = new ArrayList<>();
    private List<News> mNewsTop = new ArrayList<>();
    private StoriesAdapter mStoriesAdapter;

    @BindView(R.id.rv_news)
    RecyclerView mRecyclerViewNews;
    @BindView(R.id.sw_top)
    Switch mSwitchTop;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stories, null);
        ButterKnife.bind(this, view);
        getPost();

        mRecyclerViewNews.setLayoutManager(new LinearLayoutManager(getContext()));
        mStoriesAdapter = new StoriesAdapter(mNews);
        mRecyclerViewNews.setAdapter(mStoriesAdapter);

        checkTop();
        return view;
    }

    private void getPost() {
        Call<List<News>> getAllNews = RetrofitMain.getApiInterface().getNews();
        getAllNews.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                mNews.clear();
                mNews.addAll(response.body());
                mStoriesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {

            }
        });
    }

    private void checkTop() {
        mSwitchTop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mNewsTop.clear();
                    for (News news : mNews) {
                        if (news.isTop()) {
                            mNewsTop.add(news);
                            mStoriesAdapter.setNewsList(mNewsTop);
                        } else {
                            mStoriesAdapter.setNewsList(mNewsTop);
                        }
                    }
                } else {
                    mStoriesAdapter.setNewsList(mNews);
                }
            }
        });
    }
}

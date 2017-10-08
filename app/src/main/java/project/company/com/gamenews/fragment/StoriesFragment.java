package project.company.com.gamenews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    private List<News> news = new ArrayList<>();
    private StoriesAdapter mStoriesAdapter;

    @BindView(R.id.rv_news)
    RecyclerView mRecyclerViewNews;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stories, null);
        ButterKnife.bind(this, view);
        getPost();

        mRecyclerViewNews.setLayoutManager(new LinearLayoutManager(getContext()));
        mStoriesAdapter = new StoriesAdapter(news);
        mRecyclerViewNews.setAdapter(mStoriesAdapter);

        return view;
    }

    private void getPost() {
        Call<List<News>> getAllNews = RetrofitMain.getApiInterface().getNews();
        getAllNews.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                news.clear();
                news.addAll(response.body());
                mStoriesAdapter.notifyDataSetChanged();
                Log.d("myLog", news.get(0).getName());
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {

            }
        });
    }
}

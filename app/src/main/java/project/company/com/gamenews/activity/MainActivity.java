package project.company.com.gamenews.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import project.company.com.gamenews.R;
import project.company.com.gamenews.adapter.MyFragmentPagerAdapter;
import project.company.com.gamenews.model.News;
import project.company.com.gamenews.retrofit.RetrofitMain;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<News> news= new ArrayList<>();

    @BindView(R.id.tab_layout)
    TabLayout mTab;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mViewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), getResources().getStringArray(R.array.titles_tab)));
        mTab.setupWithViewPager(mViewPager);
        getPost();
    }

    private void getPost() {
        Call<List<News>> getAllNews = RetrofitMain.getApiInterface().getNews();
        getAllNews.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                news.clear();
                news.addAll(response.body());
                Log.d("myLog",news.get(0).getName());
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {

            }
        });
    }
}

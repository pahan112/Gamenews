package project.company.com.gamenews.retrofit;

import java.util.List;
import java.util.Queue;

import project.company.com.gamenews.model.News;
import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by Pahan on 07.10.2017.
 */

public interface ApiInterface {
    @GET("feedNews?lang=en&count=10&sources=7,19,13,5,15,16,12,9,10012,10010,10013,10014,10019,10018,10011&feedLineId=5")
    Call<List<News>> getNews();
}

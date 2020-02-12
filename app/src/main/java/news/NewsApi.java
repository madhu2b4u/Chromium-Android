package news;

import news.model.NewsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {

    String BASE_URL = "https://newsapi.org/v2/";

    @GET("top-headlines")
    Call<NewsResponse> getNewsResponse(@Query("country") String country, @Query("apiKey") String apiKey);
}

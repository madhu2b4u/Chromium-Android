package news;

import news.model.NewsResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsApi {

    String BASE_URL = "https://newsapi.org/v2/";
    String API_KEY ="02ff2aa7041041f2b3802c30cba1aea9";

    @GET("top-headlines?country=in&apiKey="+API_KEY)
    Call<NewsResponse> getNewsResponse();
}

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface TinyUrlService {


    @POST("random")
    @FormUrlEncoded
    Call<TinyUrlResponse> makeShortUrl(
            @Field("format")
                    String format,
            @Field("URL")
                    String originalUrl);
}

import retrofit2.http.GET;
import retrofit2.Call;
public interface GoogleWebService {

    @GET("/")
    Call<String> home();
}

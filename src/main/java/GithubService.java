import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Path;

public interface GithubService {
    @GET("users/{user}/repos")
    Call<String> getRepositories
            (@Path("user") String username);
}

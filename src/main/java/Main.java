import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        new Main().run();

    }

    private void run() {
        try {
            runGoogle();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            runGithub();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void runGoogle() throws IOException{

        GoogleWebService service = new Retrofit.Builder()
                .baseUrl("https://google.com")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(GoogleWebService.class);

        Response<String> respones = service.home().execute();
        if (respones.isSuccesful()){
            System.out.println(respones.body());
        }else{
            System.out.println(respones.errorBody().string());
        }
    }

    private void runGithub() throws IOException{
        new Retrofit.Builder().baseUrl("https://api.github.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(GithubService.class);

        Response<String> response = service.getRepositories("octocat").execute();

    }
    private void runTinyUrl()throws IOException{

    }
}

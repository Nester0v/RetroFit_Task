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

        System.out.println("ПОЛУЧИМ ОТВЕТ ОТ https://google.com: " + "\n");

        GoogleWebService service = new Retrofit.Builder()
                .baseUrl("https://google.com")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(GoogleWebService.class);

        Response<String> response = service.home().execute();
        if (response.isSuccessful()){
            System.out.println(response.body());
        }else{
            System.out.println(response.errorBody().string());
        }
        System.out.println("\n");
    }

    private void runGithub() throws IOException{

        System.out.println("ПОЛУЧИМ ОТВЕТ ОТ https://api.github.com/: " + "\n");

       GitHubService service = new Retrofit.Builder().baseUrl("https://api.github.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(GitHubService.class);

        Response<String> response = service.getRepositories("octocat").execute();
        if (response.isSuccessful()){
            System.out.println(response.body());
        }else{
            System.out.println(response.errorBody().string());
        }
        System.out.println("\n");
    }

    private void runTinyUrl()throws IOException{

    }
}

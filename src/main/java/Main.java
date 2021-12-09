import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        try {
            runGoogle();
            runGithub();
            runTinyUrl();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void runGoogle() throws IOException {

        System.out.println("ОТВЕТ ОТ https://google.com: " + "\n");

        GoogleWebService service = new Retrofit.Builder()
                .baseUrl("https://google.com")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(GoogleWebService.class);

        Response<String> response = service.home().execute();
        if (response.isSuccessful()) {
            System.out.println(response.body());
        } else {
            System.out.println(response.errorBody().string());
        }
        System.out.println("\n");
    }

    private void runGithub() throws IOException {

        System.out.println("ОТВЕТ ОТ https://api.github.com/: " + "\n");

        GitHubService service = new Retrofit.Builder().baseUrl("https://api.github.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(GitHubService.class);

        Response<String> response = service.getRepositories("octocat").execute();
        if (response.isSuccessful()) {
            System.out.println(response.body());
        } else {
            System.out.println(response.errorBody().string());
        }
        System.out.println("\n");
    }

    private void runTinyUrl()throws IOException{
        TinyUrlService service = new Retrofit.Builder()
                .baseUrl("http://tiny-url.info/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TinyUrlService.class);
        Response<TinyUrlResponse> response = service.makeShortUrl("json",
                "https://www.google.com.ua/maps/place/%D0%9D%D0%B0%D1%86%D1%96%D0%BE%D0%BD%D0%B0%D0%BB%D1%8C%D0%BD%D0%B8%D0%B9+%D0%B0%D0%B5%D1%80%D0%BE%D0%BA%D0%BE%D1%81%D0%BC%D1%96%D1%87%D0%BD%D0%B8%D0%B9+%D1%83%D0%BD%D1%96%D0%B2%D0%B5%D1%80%D1%81%D0%B8%D1%82%D0%B5%D1%82+%D1%96%D0%BC.+%D0%9C.%D0%84.+%D0%96%D1%83%D0%BA%D0%BE%D0%B2%D1%81%D1%8C%D0%BA%D0%BE%D0%B3%D0%BE+%22%D0%A5%D0%B0%D1%80%D0%BA%D1%96%D0%B2%D1%81%D1%8C%D0%BA%D0%B8%D0%B9+%D0%B0%D0%B2%D1%96%D0%B0%D1%86%D1%96%D0%B9%D0%BD%D0%B8%D0%B9+%D1%96%D0%BD%D1%81%D1%82%D0%B8%D1%82%D1%83%D1%82%22/@50.0525845,36.2861772,14.22z/data=!4m5!3m4!1s0x4127a71d9fca861d:0xa501bb6d97670b53!8m2!3d50.0427316!4d36.2847256?hl=ru").execute();
        //Почему-то не выводит ссылку (((
        if (response.isSuccessful()) {
            System.out.println(response.body().shorturl);
        } else {
            System.out.println(response.errorBody().string());
        }
        System.out.println("\n");

    }
}


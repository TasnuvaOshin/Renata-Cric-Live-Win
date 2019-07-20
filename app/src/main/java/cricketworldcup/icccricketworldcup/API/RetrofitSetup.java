package cricketworldcup.icccricketworldcup.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSetup {
    private static final String BASE_URL = "https://cricket.sportmonks.com/";
    // private static final String BASE_URL = "https://api.myjson.com/";
    static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());
    static Retrofit retrofit = builder.build();


    public static <S> S GetOurRetrofit(Class<S> ourClass) {
        return retrofit.create(ourClass);
        //client means connection estrablished now we are getting the instance
        //ourClass is the interface class
    }
}

package cricketworldcup.icccricketworldcup.API;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OurClient {
    @GET("api/v2.0/livescores?api_token=yhe5EsrLbkneLHyyVmLItXohvXTth7jAYhYnlltH1GCeTN7aT4yKR8LZlAIj&include=runs,localteam,visitorteam")
        // @GET("bins/josyw")
    Call<OurResponse> getOurResponse();
}

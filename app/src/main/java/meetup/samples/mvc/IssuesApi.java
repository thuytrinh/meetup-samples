package meetup.samples.mvc;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IssuesApi {
  @GET("issues") Call<List<Issue>> fetchAsync();
}
package meetup.samples.mvvm;

import java.util.List;

import retrofit2.http.GET;
import rx.Single;

public interface IssuesApi {
  @GET("issues") Single<List<Issue>> fetchAsync();
}
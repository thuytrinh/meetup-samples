package meetup.samples.mvvm;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BASIC;

public final class IssuesViewModels {
  private IssuesViewModels() {}

  public static IssuesViewModel create() {
    final OkHttpClient httpClient = new OkHttpClient.Builder()
        .addInterceptor(new HttpLoggingInterceptor().setLevel(BASIC))
        .build();
    final IssuesApi issuesApi = new Retrofit.Builder()
        .baseUrl("https://api.github.com/repos/square/okhttp/")
        .client(httpClient)
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(IssuesApi.class);
    return new IssuesViewModelImpl(issuesApi);
  }
}
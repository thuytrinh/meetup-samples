package meetup.samples.mvc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import meetup.samples.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IssuesFragment extends Fragment {
  @Bind(R.id.issuesView) ListView issuesView;
  @Bind(R.id.progressBar) View progressBar;
  private IssuesApi issuesApi;
  private Call<List<Issue>> call;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    issuesApi = new Retrofit.Builder()
        .baseUrl("https://api.github.com/repos/square/okhttp/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(IssuesApi.class);
  }

  @Nullable @Override public View onCreateView(
      LayoutInflater inflater,
      ViewGroup container,
      Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.fragment_issues_mvc, container, false);
    ButterKnife.bind(this, view);
    return view;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    progressBar.setVisibility(View.VISIBLE);
    call = issuesApi.fetchAsync();
    call.enqueue(new Callback<List<Issue>>() {
      @Override public void onResponse(Call<List<Issue>> call, Response<List<Issue>> response) {
        if (progressBar != null) {
          progressBar.setVisibility(View.GONE);
        }

        if (issuesView != null) {
          issuesView.setAdapter(new ArrayAdapter<>(
              issuesView.getContext(),
              android.R.layout.simple_list_item_1,
              response.body()
          ));
        }
      }

      @Override public void onFailure(Call<List<Issue>> call, Throwable error) {
        if (progressBar != null) {
          progressBar.setVisibility(View.GONE);
        }
      }
    });
  }

  @Override public void onDestroy() {
    if (call != null) {
      call.cancel();
    }

    super.onDestroy();
  }

  @Override public void onDestroyView() {
    ButterKnife.unbind(this);
    super.onDestroyView();
  }
}
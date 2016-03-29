package meetup.samples.mvvm;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import java.util.List;

import rx.Single;

public interface IssuesViewModel {
  ObservableField<List<Issue>> issues();
  ObservableBoolean isFetching();
  Single<List<Issue>> fetchIssuesAsync();
}
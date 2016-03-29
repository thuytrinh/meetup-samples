package meetup.samples.mvvm;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import java.util.Collections;
import java.util.List;

import rx.Single;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public final class IssuesViewModelImpl implements IssuesViewModel {
  private final IssuesApi issuesApi;
  private final ObservableBoolean isFetching = new ObservableBoolean();
  private final ObservableField<List<Issue>> issues = new ObservableField<>(Collections.<Issue>emptyList());

  public IssuesViewModelImpl(IssuesApi issuesApi) {
    this.issuesApi = issuesApi;
  }

  @Override public ObservableField<List<Issue>> issues() {
    return issues;
  }

  @Override public ObservableBoolean isFetching() {
    return isFetching;
  }

  @Override public Single<List<Issue>> fetchIssuesAsync() {
    return issuesApi.fetchAsync()
        .subscribeOn(Schedulers.io())
        .doOnSuccess(new Action1<List<Issue>>() {
          @Override public void call(List<Issue> newIssues) {
            issues.set(newIssues);
          }
        })
        .doOnSubscribe(new Action0() {
          @Override public void call() {
            isFetching.set(true);
          }
        })
        .doOnUnsubscribe(new Action0() {
          @Override public void call() {
            isFetching.set(false);
          }
        });
  }
}
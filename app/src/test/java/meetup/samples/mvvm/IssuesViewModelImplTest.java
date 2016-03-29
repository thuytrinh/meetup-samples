package meetup.samples.mvvm;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import meetup.samples.BuildConfig;
import rx.Single;
import rx.observers.TestSubscriber;
import rx.subjects.PublishSubject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class IssuesViewModelImplTest {
  @Mock IssuesApi api;
  private IssuesViewModelImpl viewModel;

  @Before public void before() {
    MockitoAnnotations.initMocks(this);
    viewModel = new IssuesViewModelImpl(api);
  }

  @Test public void isNotFetchingInitially() {
    assertThat(viewModel.isFetching().get()).isFalse();
  }

  @Test public void noIssuesInitially() {
    assertThat(viewModel.issues().get())
        .isNotNull().isEmpty();
  }

  @Test public void updateIssuesAfterFetching() {
    final List<Issue> issues = Arrays.<Issue>asList(
        ImmutableIssue.builder().title("a").number(1).build(),
        ImmutableIssue.builder().title("b").number(2).build()
    );
    when(api.fetchAsync()).thenReturn(Single.just(issues));

    final TestSubscriber<List<Issue>> subscriber = new TestSubscriber<>();
    viewModel.fetchIssuesAsync().subscribe(subscriber);

    subscriber.awaitTerminalEvent();
    subscriber.assertNoErrors();

    assertThat(viewModel.issues().get())
        .containsExactlyElementsOf(issues);
  }

  @Test public void isOrIsNotFetching() {
    final PublishSubject<List<Issue>> s = PublishSubject.create();
    when(api.fetchAsync()).thenReturn(s.toSingle());

    final TestSubscriber<List<Issue>> subscriber = new TestSubscriber<>();
    viewModel.fetchIssuesAsync().subscribe(subscriber);

    assertThat(viewModel.isFetching().get()).isTrue();
    s.onNext(Collections.<Issue>emptyList());
    s.onCompleted();
    assertThat(viewModel.isFetching().get()).isFalse();
  }
}
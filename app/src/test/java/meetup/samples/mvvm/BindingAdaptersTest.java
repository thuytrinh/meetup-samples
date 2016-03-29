package meetup.samples.mvvm;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.Collections;

import meetup.samples.BuildConfig;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class BindingAdaptersTest {
  @Test public void visibilityIsVisible() {
    assertThat(BindingAdapters.convertBooleanToViewVisibility(true))
        .isEqualTo(View.VISIBLE);
  }

  @Test public void visibilityIsGone() {
    assertThat(BindingAdapters.convertBooleanToViewVisibility(false))
        .isEqualTo(View.GONE);
  }

  @Test public void setAdapterForIssues() {
    final Context context = mock(Context.class);
    final ListView view = mock(ListView.class);
    when(view.getContext()).thenReturn(context);

    BindingAdapters.setIssues(view, Collections.<Issue>emptyList());
    verify(view).setAdapter(any(ArrayAdapter.class));
  }
}
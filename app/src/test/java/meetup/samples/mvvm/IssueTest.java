package meetup.samples.mvvm;

import android.os.Parcel;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import meetup.samples.BuildConfig;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class IssueTest {
  @Test public void parcel() {
    final Issue expected = ImmutableIssue.builder()
        .title("Some title")
        .number(25251325)
        .build();

    final Parcel parcel = Utils.parcel(expected);
    final Issue actual = Issue.CREATOR.createFromParcel(parcel);
    assertThat(actual).isEqualTo(expected);
  }

  @Test public void json() {
    final Issue expected = ImmutableIssue.builder()
        .title("Some title")
        .number(25251325)
        .build();

    final Gson gson = new Gson();
    final JsonObject json = gson.toJsonTree(expected).getAsJsonObject();
    assertThat(json.has("title")).isTrue();
    assertThat(json.has("number")).isTrue();

    final Issue actual = gson.fromJson(json, Issue.class);
    assertThat(actual).isEqualTo(expected);
  }
}
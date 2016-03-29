package meetup.samples.mvvm;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.JsonAdapter;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

@Gson.TypeAdapters
@Value.Immutable
@JsonAdapter(GsonAdaptersIssue.class)
public abstract class Issue implements Parcelable {
  public static final Creator<Issue> CREATOR = new Creator<Issue>() {
    @Override public Issue createFromParcel(Parcel in) {
      return ImmutableIssue.builder()
          .number(in.readInt())
          .title(in.readString())
          .build();
    }

    @Override public Issue[] newArray(int size) {
      return new Issue[size];
    }
  };

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(number());
    dest.writeString(title());
  }

  @Override public int describeContents() {
    return 0;
  }

  public abstract String title();
  public abstract int number();

  @Override public String toString() {
    return String.format("#%d - %s", number(), title());
  }
}
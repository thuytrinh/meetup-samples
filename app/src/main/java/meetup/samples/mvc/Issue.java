package meetup.samples.mvc;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Issue implements Parcelable {
  public static final Creator<Issue> CREATOR = new Creator<Issue>() {
    @Override public Issue createFromParcel(Parcel in) {
      return new Issue(in);
    }

    @Override public Issue[] newArray(int size) {
      return new Issue[size];
    }
  };

  @SerializedName("title") private String title;
  @SerializedName("number") private int number;

  protected Issue(Parcel in) {
    title = in.readString();
    number = in.readInt();
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(title);
    dest.writeInt(number);
  }

  @Override public int describeContents() {
    return 0;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  @Override public String toString() {
    return String.format("#%d - %s", number, title);
  }
}
package meetup.samples.mvvm;

import android.util.Log;

import rx.functions.Action1;

public final class ErrorHandlers {
  private ErrorHandlers() {}

  public static Action1<Throwable> trackError() {
    return new Action1<Throwable>() {
      @Override public void call(Throwable error) {
        Log.e("ErrorHandlers", null, error);
      }
    };
  }
}
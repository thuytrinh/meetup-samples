package meetup.samples.mvvm;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public final class BindingAdapters {
  private BindingAdapters() {}

  @BindingConversion
  public static int convertBooleanToViewVisibility(boolean value) {
    return value ? View.VISIBLE : View.GONE;
  }

  @BindingAdapter("issues")
  public static void setIssues(ListView view, List<Issue> issues) {
    view.setAdapter(new ArrayAdapter<>(
        view.getContext(),
        android.R.layout.simple_list_item_1,
        issues
    ));
  }
}
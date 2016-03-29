package meetup.samples.mvvm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.components.support.RxFragment;

import meetup.samples.databinding.FragmentIssuesMvvmBinding;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Actions;

public class IssuesFragment extends RxFragment {
  private IssuesViewModel viewModel;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    viewModel = IssuesViewModels.create();
    viewModel.fetchIssuesAsync()
        .toObservable()
        .compose(bindToLifecycle())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(Actions.empty(), ErrorHandlers.trackError());
  }

  @Nullable @Override public View onCreateView(
      LayoutInflater inflater,
      ViewGroup container,
      Bundle savedInstanceState) {
    final FragmentIssuesMvvmBinding binding = FragmentIssuesMvvmBinding.inflate(inflater);
    binding.setViewModel(viewModel);
    return binding.getRoot();
  }
}
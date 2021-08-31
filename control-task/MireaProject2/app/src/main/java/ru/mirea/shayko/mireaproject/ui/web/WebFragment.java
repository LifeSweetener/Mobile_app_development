package ru.mirea.shayko.mireaproject.ui.web;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.shayko.mireaproject.MainActivity;
import ru.mirea.shayko.mireaproject.databinding.FragmentWebBinding;

public class WebFragment extends Fragment {

    private WebViewModel webViewModel;
    private FragmentWebBinding binding;

    public FragmentWebBinding getFragmentWebBinding() { return binding; }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        webViewModel =
                new ViewModelProvider(this).get(WebViewModel.class);

        binding = FragmentWebBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final WebView webView = binding.web;
        webView.setWebViewClient(new MyWebViewClient());  // присоединяем к webView свой веб-клиент для просмотра страницы прямо в нашем приложении, а не в стороннем браузере телефона
        webView.getSettings().setJavaScriptEnabled(true);  // разрешаем использование и обработку JS
        webView.loadUrl("http://www.mirea.ru");  // загружаем страницу университета
        MainActivity main = (MainActivity) this.getActivity();
        main.setWeb(this);
        webViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void reload(View view) {
    }
}
package ru.mirea.shayko.mireaproject.ui.calculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.shayko.mireaproject.MainActivity;
import ru.mirea.shayko.mireaproject.databinding.FragmentCalculatorBinding;

public class CalculatorFragment extends Fragment {
    private CalculatorViewModel calculatorViewModel;
    private FragmentCalculatorBinding binding;

    public FragmentCalculatorBinding getFragmentCalculatorBinding() { return binding; }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        calculatorViewModel =
                new ViewModelProvider(this).get(CalculatorViewModel.class);

        binding = FragmentCalculatorBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        calculatorViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });

        /* В следующих двух строках мы передаём данные о своих view главному активити: */
        MainActivity main = (MainActivity) this.getActivity();
        main.setCalc(this);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void onDigitClick(View view) {
    }
    public void onArithmaticClick(View view) {
    }
    public void clear(View view) {
    }
}
package ru.mirea.shayko.mireaproject.ui.musicplayer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.shayko.mireaproject.databinding.FragmentMusicBinding;
import ru.mirea.shayko.mireaproject.ui.home.HomeViewModel;

public class MusicPlayerFragment extends Fragment {

    private HomeViewModel musicViewModel;
    private FragmentMusicBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        musicViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentMusicBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

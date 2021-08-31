package ru.mirea.shayko.mireaproject.ui.web;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WebViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public WebViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is web fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
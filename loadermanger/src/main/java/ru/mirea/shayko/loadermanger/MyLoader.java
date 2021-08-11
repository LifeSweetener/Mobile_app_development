package ru.mirea.shayko.loadermanger;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;

public class MyLoader extends AsyncTaskLoader<String> {
    private String text;  // возвращаемая строка после загрузки данных этим загрузчиком
    public static final String ARG_WORD = "word";  // ключ для строки-значения в переменной "text" (см. выше)

    public MyLoader(@NonNull Context context, Bundle args) {
        super(context);
        if (args != null)
            text = args.getString(ARG_WORD);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public String loadInBackground() {
        SystemClock.sleep(3000);  // Пауза в три секунды

        /* Обрабатываем поступившую строку: */
        char[] textChars = text.toCharArray();
        text = "";
        for (int i = 0; i + 1 < textChars.length - 1; i += 2) {
            char tempChar = textChars[i];
            textChars[i] = textChars[i+1];
            textChars[i+1] = tempChar;
            text += textChars[i];
            text += textChars[i+1];
        }
        if (textChars.length % 2 != 0)
            text += textChars[textChars.length - 1];
        else {
            text += textChars[textChars.length - 2];
            text += textChars[textChars.length - 1];
        }

        return text;  // После обработки поступивших данных - возвращаем их
    }

    @Override
    protected void onReset() {
        super.onReset();
    }
}

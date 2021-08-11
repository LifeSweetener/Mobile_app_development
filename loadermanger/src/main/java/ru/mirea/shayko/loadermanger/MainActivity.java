package ru.mirea.shayko.loadermanger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    public final String TAG = this.getClass().getSimpleName();
    private int LoaderID = 1234; // ID загрузчика
    private EditText editText = null;  // UI-элемент: поля для ввода текста
    private TextView textView = null;  // UI-элемент: текстовая метка
    LoaderManager loaderManager = null;  // Диспетчер загрузчиков (менеджер загрузчиков)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);  // инициализируем UI-элемент
        textView = findViewById(R.id.textView);  // инициализируем UI-элемент
        loaderManager = LoaderManager.getInstance(this);  // берём ссылку на менеджер загрузчиков нашего приложения
    }

    /* Загрузчик завершён: */
    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        Log.d(TAG, "onLoaderReset");
        if (loader.getId() == LoaderID) {
            Toast.makeText(this, "onLoaderReset:" + LoaderID, Toast.LENGTH_SHORT).show();
        }
    }

    /* Загрузчик создан: */
    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        if (i == LoaderID) {
            Toast.makeText(this, "onCreateLoader:" + i, Toast.LENGTH_SHORT).show();
            return new MyLoader(this, bundle);
        }
        return null;
    }

    /* Загрузка данных (у нас - строки) завершена: */
    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {
        if (loader.getId() == LoaderID) {
            Log.d(TAG, "onLoadFinished" + s);
            Toast.makeText(this, "onLoadFinished:" + s, Toast.LENGTH_SHORT).show();
            textView.setText(s);  // Выводим результат, выданный приложению загрузчиком

            loaderManager.destroyLoader(LoaderID);  // Уничтожаем загрузчик, чтобы в будущем он продолжил обрабатывать пользовательский ввод при нажатии на кнопку нашего GUI
        }
    }

    /* Обработчик нажатия на кнопку нашего GUI: */
    public void load(View view) {
        Bundle bundle = new Bundle();  // Определяем данные (строку) для загрузчика
        if (editText.getText().toString() != "")
            bundle.putString(MyLoader.ARG_WORD, editText.getText().toString());
        else
            bundle.putString(MyLoader.ARG_WORD, "empty");

        loaderManager.initLoader(LoaderID, bundle, this);  // Запускаем загрузчик, передавая одновременно с этим данные (строку)
    }
}
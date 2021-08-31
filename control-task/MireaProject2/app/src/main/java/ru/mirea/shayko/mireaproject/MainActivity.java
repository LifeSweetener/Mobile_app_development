package ru.mirea.shayko.mireaproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import ru.mirea.shayko.mireaproject.databinding.ActivityMainBinding;
import ru.mirea.shayko.mireaproject.databinding.FragmentCalculatorBinding;
import ru.mirea.shayko.mireaproject.databinding.FragmentMusicBinding;
import ru.mirea.shayko.mireaproject.ui.calculator.CalculatorFragment;
import ru.mirea.shayko.mireaproject.ui.musicplayer.MusicService;
import ru.mirea.shayko.mireaproject.ui.web.WebFragment;

public class MainActivity extends AppCompatActivity {
    private CalculatorFragment calc;  // фрагмент с калькулятором
    public boolean setCalc(Object obj) {  // устанавливаем переменной указатель на наш работающий калькулятор
        if (obj instanceof CalculatorFragment) {
            calc = (CalculatorFragment) obj;
            return true;  // успех
        }

        return false;  // неудача (передан не тот фрагмент)
    }

    private WebFragment web;
    public boolean setWeb(Object obj) {  // устанавливаем переменной указатель на наш работающий браузер
        if (obj instanceof WebFragment) {
            web = (WebFragment) obj;
            return true;  // успех
        }

        return false;  // неудача (передан не тот фрагмент)
    }

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private FragmentMusicBinding musicBinding;

    /* Элементы (view) калькулятора: */
    private TextView editText1;
    private TextView editText2;
    private RadioButton radio1;
    private RadioButton radio2;
    private ScrollView scrollView;
    private TextView answer;

    /* Музыкальный плеер: */
    private MusicService player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_musicplayer)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        /* Создаём объект-плеер: */
        //player = new MusicService();

        /* Смена цвета фона двух кнопок в плеере: */
        musicBinding = FragmentMusicBinding.inflate(getLayoutInflater());  // берём фрагмент с нашим плеером
        ConstraintLayout musicRootLayout = musicBinding.getRoot();  // получаем XML-разметку этого фрагмента
        Button musicButton = musicRootLayout.findViewById(R.id.startButton);  // находим и сохраняем в переменную кнопку
        musicButton.setBackgroundColor(Color.rgb(255,255,100));  // меняем цвет фона этой кнопки
        musicButton = musicRootLayout.findViewById(R.id.stopButton);  // ищем и сохраняем в ту же переменную вторую кнопку
        musicButton.setBackgroundColor(Color.rgb(255,255,100));  // теперь меняем цвет фона и этой кнопки
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    /* Вызываем метод для инициализации здесь, в классе, калькулятора и его дальнейшего использования: */
    private void takeViewsFromCalc() {
        FragmentCalculatorBinding binding = calc.getFragmentCalculatorBinding();
        editText1 = binding.editText1;
        editText2 = binding.editText2;
        radio1 = binding.radio1;
        radio2 = binding.radio2;
        scrollView = binding.scroll;
        answer = binding.answer;
    }

    public void onDigitClick(View view) {
        takeViewsFromCalc();  // берём элементы (view) из калькулятора
        /* И начинаем их изменять прямо тут: */
        if (radio1.isChecked())
            editText1.setText(editText1.getText() + (view.toString().charAt((view.toString().lastIndexOf("button")) + 6) + ""));
        else if (radio2.isChecked())
            editText2.setText(editText2.getText() + (view.toString().charAt((view.toString().lastIndexOf("button")) + 6) + ""));
    }

    public void onArithmaticClick(View view) {
        Button buttonOp = null;
        if (view == null)
            return;
        else
            buttonOp = (Button) view;

        if (buttonOp == calc.getFragmentCalculatorBinding().buttonPlus) {
                try {
                    int arg1 = Integer.parseInt(editText1.getText().toString());
                    int arg2 = Integer.parseInt(editText2.getText().toString());
                    answer.setText((arg1+arg2) + "");
                } catch (NumberFormatException ex) {
                    Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
                    return;
                }
        } else if (buttonOp == calc.getFragmentCalculatorBinding().buttonMinus) {
            try {
                int arg1 = Integer.parseInt(editText1.getText().toString());
                int arg2 = Integer.parseInt(editText2.getText().toString());
                answer.setText((arg1-arg2) + "");
            } catch (NumberFormatException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
                return;
            }
        } else if (buttonOp == calc.getFragmentCalculatorBinding().buttonMultiply) {
            try {
                double arg1 = Double.parseDouble(editText1.getText().toString());
                double arg2 = Double.parseDouble(editText2.getText().toString());
                answer.setText((arg1*arg2) + "");
            } catch (NumberFormatException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
                return;
            }
        } else if (buttonOp == calc.getFragmentCalculatorBinding().buttonDevide) {
            try {
                double arg1 = Double.parseDouble(editText1.getText().toString());
                double arg2 = Double.parseDouble(editText2.getText().toString());
                answer.setText(Double.parseDouble((arg1/arg2) + "") + "");
            } catch (NumberFormatException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
                return;
            }
        } else if (buttonOp == calc.getFragmentCalculatorBinding().buttonLog) {
            try {
                int arg1 = Integer.parseInt(editText1.getText().toString());
                int arg2 = Integer.parseInt(editText2.getText().toString());
                answer.setText(log(arg1, arg2) + "");
            } catch (NumberFormatException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
                return;
            }
        }
    }

    /* Вычисление логарифма: */
    private double log(int a, int b) {
        double i = 0;
        if (b > 1) {
            while (Math.abs(Math.pow(a, i) - b) > 0.01)
                i += 0.01;
        } else if (b < 1) {
            while (Math.abs(Math.pow(a, i) - b) > 0.01)
                i -= 0.01;
        }

        return Double.parseDouble(String.format("%.0",i));
    }

    /* Кнопка очистки калькулятора: */
    public void clear(View view) {
        if (view instanceof Button) {
            Button clr = (Button) view;
            if (clr == calc.getFragmentCalculatorBinding().clear){
                editText1.setText("");
                editText2.setText("");
            }
        }
    }

    /* Загрузка страницы МИРЭА в веб-браузере: */
    public void reload(View view) {
        if ((view instanceof Button) && ((Button) (view) == web.getFragmentWebBinding().button))
            web.getFragmentWebBinding().web.loadUrl("http://www.mirea.ru");
        else
            Toast.makeText(this,"Browser error!", Toast.LENGTH_LONG).show();
    }

    /* Две кнопки музыкального плеера: */
    // 1-ая кнопка (начать воспроизведение аудиофайла):
    public void onStartButtonClick(View view) {
        startService(new Intent(this, MusicService.class));
    }

    // 2-ая кнопка (остановить воспроизведение аудиофайла):
    public void onStopButtonClick(View view) {
        stopService(new Intent(this, MusicService.class));
    }
}
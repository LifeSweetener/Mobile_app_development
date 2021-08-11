package ru.mirea.shayko.looper;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.view.View;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.Date;
import java.util.Locale;
import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {
    private MyLooper myLooper;
    LocalDate firstDate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            firstDate = LocalDate.of(2020,12,25);
        myLooper = new MyLooper();
        myLooper.start();
    }

    public void sendMessage(View view) {
        Message msg = new Message();
        LocalDate secondDate = null;
        LocalDate resultDate = null;
        if (firstDate == null) {
            Bundle bundle = new Bundle();
            bundle.putString("job", "student");
            bundle.putString("age", "21");
            msg.setData(bundle);
            if (myLooper != null)
                myLooper.handler.sendMessage(msg);
            return;
        } else if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            secondDate = LocalDate.now();
            resultDate = secondDate.minusYears(firstDate.getYear());
            resultDate = resultDate.minusMonths(firstDate.getMonthValue());
            resultDate = resultDate.minusDays(firstDate.getDayOfMonth());
            Bundle bundle = new Bundle();
            bundle.putString("job", "student");
            bundle.putString("age", resultDate.toString());
            msg.setData(bundle);
            if (myLooper != null)
                myLooper.handler.sendMessage(msg);
        }
    }
}
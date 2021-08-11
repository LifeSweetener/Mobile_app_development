package ru.mirea.shayko.data_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    TextView tvInfo;

    /*
    * Сначала запустится метод runn1, потом runn2, а последним выполнится - runn3.
    * ==============================================================================================================
    * Используя один главный поток приложения "UI thread", мы не сможем одновременно вычислить что-то, а потом как результат вычислений перерисовать
    * элемент View, потому что у этого главного потока слишком мало времени на это - ему нужно делать ещё кучу задач.
    * У UI-потока просто кончится время на перерисовку элемента пользовательского интерфейса.
    * Поэтому были придуманы методы runOnUiThread(), post() и postDelayed(), чтобы разделить вычисления и перерисовку View.
    * Вычисления производит второстепенный поток, который мы создаём, а перерисовку - главный поток, чем он и должен заниматься. А это означает, что
    * времени на перерисовку элемента у главного UI-потока будет предостаточно, и не произойдёт никакой ошибки в приложении.
    *
    * Метод runOnUiThread() полезен при перерисовке какого-то одного View: мы создаём свой второстепенный поток, в нём делаем вычислительные операции
    * и в нём же вызываем этот метод, чтобы передать операцию перерисовки конкретного View главному потоку.
    *
    * Методы post() и postDelayed() полезны, когда у нас имеется множество разных View, и нам нужно все их перерисовать одновременно. Главный поток по очереди,
    * друг за другом перерисовывает их, пока выполняются вычисления в нашем дополнительном потоке.
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvInfo = findViewById(R.id.textView);

        final Runnable runn1 = new Runnable() {
            public void run() {
                tvInfo.setText("runn1");
            }
        };

        final Runnable runn2 = new Runnable() {
            public void run() {
                tvInfo.setText("runn2");
            }
        };

        final Runnable runn3 = new Runnable() {
            public void run() {
                tvInfo.setText("runn3");
            }
        };

        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    runOnUiThread(runn1);
                    TimeUnit.SECONDS.sleep(1);
                    tvInfo.postDelayed(runn3, 2000);
                    tvInfo.post(runn2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();
    }
}
package br.com.ciandt.blocodenotas.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.ciandt.blocodenotas.app.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        try {
            waitThreeSecondsRedirect();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void waitThreeSecondsRedirect() throws InterruptedException {
        Thread.sleep(5000);
        Intent intent = new Intent(this, BlocoDeNotasActivity.class);
        startActivity(intent);

    }

}

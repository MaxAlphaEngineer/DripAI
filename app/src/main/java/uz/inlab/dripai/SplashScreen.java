package uz.inlab.dripai;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import uz.inlab.dripai.models.PrefManager;


public class SplashScreen extends AppCompatActivity {
    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefManager = new PrefManager(this);

      //  prefManager.setFirstTimeLaunch(true);
        if (prefManager.isFirstTimeLaunch()) {
            startActivity(new Intent(this, WelcomeActivity.class));
            finish();
        } else {

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}

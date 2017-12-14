package bgi.com.bgi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    private SharedPreferences preferences;
    public int SPLASH_DISPLAY_LENGTH = 2000;
    private String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
        //setContentView(R.layout.activity_main);
        /*progressBar = (ProgressBar) findViewById(R.id.progressBar);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }

        }, SPLASH_DISPLAY_LENGTH);*/
    }
}

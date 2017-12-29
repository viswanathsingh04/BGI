package bgi.com.bgi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

public class Bottom extends AppCompatActivity {

    FrameLayout message;
    BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    changeFragment(0);
                    return true;
                case R.id.navigation_dashboard:
                    Toast.makeText(Bottom.this, "Clicked Navigation Dashboard option...", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_notifications:
                    Toast.makeText(Bottom.this, "Great!!! You Clicked Navigation Notification Menu...", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom);
        changeFragment(0);
        message = (FrameLayout) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void changeFragment(int position) {

        Fragment newFragment = null;
        if (position == 0) {
            newFragment = new StoreData();
        } else if (position % 2 != 0) {
            newFragment = new StoreData();
        } else {
            newFragment = new StoreData();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.message, newFragment).commit();
    }

}

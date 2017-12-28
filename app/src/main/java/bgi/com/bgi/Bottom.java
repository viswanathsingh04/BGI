package bgi.com.bgi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Bottom extends AppCompatActivity {

    private TextView mTextMessage;
    FrameLayout message;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Fragment fragment = new StoreData();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.message, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
                    //mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    Toast.makeText(Bottom.this, "Clicked Navigation Dashboard option...", Toast.LENGTH_SHORT).show();
                    //mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    Toast.makeText(Bottom.this, "Great!!! You Clicked Navigation Notification Menu...", Toast.LENGTH_SHORT).show();
                    //mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom);

        //mTextMessage = (TextView) findViewById(R.id.message);
        message = (FrameLayout) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}

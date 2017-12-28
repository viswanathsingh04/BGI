package bgi.com.bgi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class Aboutus extends AppCompatActivity {

    private ImageView mImageView3;
    /**
     * Viswanath Pradap Singh A
     */
    private TextView mName;
    /**
     * Android Developer @ Biggie Consulting Service
     */
    private TextView mWorks;
    /**
     * viswanathsingh04@gmail.om
     */
    private TextView mMailId;
    /**
     * +919600630577
     */
    private TextView mContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        initView();
    }

    private void initView() {
        mImageView3 = (ImageView) findViewById(R.id.imageView3);
        mName = (TextView) findViewById(R.id.name);
        mWorks = (TextView) findViewById(R.id.works);
        mMailId = (TextView) findViewById(R.id.mail_id);
        mContact = (TextView) findViewById(R.id.contact);
    }
}

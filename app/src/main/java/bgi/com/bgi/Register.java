package bgi.com.bgi;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImageView2;
    private TextInputLayout mTextInputLayout3;
    private TextInputLayout mTextInputLayout4;
    private TextInputLayout mTextInputLayout5;
    /**
     * Register
     */
    private Button mButton4;
    /**
     * Already Registered??? Click Here to go Login Screen
     */
    private TextView mTextView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        mImageView2 = (ImageView) findViewById(R.id.imageView2);
        mTextInputLayout3 = (TextInputLayout) findViewById(R.id.textInputLayout3);
        mTextInputLayout4 = (TextInputLayout) findViewById(R.id.textInputLayout4);
        mTextInputLayout5 = (TextInputLayout) findViewById(R.id.textInputLayout5);
        mButton4 = (Button) findViewById(R.id.button4);
        mButton4.setOnClickListener(this);
        mTextView3 = (TextView) findViewById(R.id.textView3);
        mTextView3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.button4:
                Toast.makeText(this, "Dummy Registration Successfull", Toast.LENGTH_SHORT).show();
                break;
            case R.id.textView3:
                break;
        }
    }
}

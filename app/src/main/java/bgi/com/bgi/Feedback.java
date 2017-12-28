package bgi.com.bgi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Feedback extends AppCompatActivity implements View.OnClickListener {

    /**
     * Select Feedback Type :
     */
    private TextView mTxtFeedbackType;
    private Spinner mFeedbackType;
    /**  */
    private EditText mFeedbackTitle;
    /**
     * Enter your Feedback
     */
    private EditText mFeedbackDesc;
    /**
     * Submit
     */
    private Button mSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        initView();
    }

    private void initView() {
        mTxtFeedbackType = (TextView) findViewById(R.id.txt_feedback_type);
        mFeedbackType = (Spinner) findViewById(R.id.feedback_type);
        mFeedbackTitle = (EditText) findViewById(R.id.feedback_title);
        mFeedbackDesc = (EditText) findViewById(R.id.feedback_desc);
        mSubmit = (Button) findViewById(R.id.submit);
        mSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.submit:
                Toast.makeText(this, "Thanks for submitting your Feedback...", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
}

package cricketworldcup.icccricketworldcup.Starter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import cricketworldcup.icccricketworldcup.Authentication.LoginActivity;
import cricketworldcup.icccricketworldcup.Authentication.SignupActivity;
import cricketworldcup.icccricketworldcup.R;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);
    }

    public void gotoSignup(View view) {
        startActivity(new Intent(StartActivity.this,SignupActivity.class));
        StartActivity.this.overridePendingTransition(0,0);
    }

    public void gotoLogin(View view) {
        startActivity(new Intent(StartActivity.this,LoginActivity.class));
        StartActivity.this.overridePendingTransition(0,0);
    }
}

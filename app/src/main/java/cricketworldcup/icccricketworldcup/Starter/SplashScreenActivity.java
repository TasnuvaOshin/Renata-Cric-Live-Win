package cricketworldcup.icccricketworldcup.Starter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;

import cricketworldcup.icccricketworldcup.Authentication.LoginActivity;
import cricketworldcup.icccricketworldcup.R;

public class SplashScreenActivity extends AppCompatActivity {
    public ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        progressBar = findViewById(R.id.pb_splash);
        // Start lengthy operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                doWork();
                startApp();
                finish();
            }
        }).start();
    }

    private void doWork() {
        for (int progress=0; progress<92; progress+=30) {
            try {
                Thread.sleep(1000);
                progressBar.setProgress(progress);
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void startApp() {
        Intent intent = new Intent(SplashScreenActivity.this, StartActivity.class);
        startActivity(intent);
        SplashScreenActivity.this.overridePendingTransition(0,0);
    }
}



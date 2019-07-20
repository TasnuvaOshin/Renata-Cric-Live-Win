package cricketworldcup.icccricketworldcup.UserEnd.Latest.FullScreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cricketworldcup.icccricketworldcup.R;
import cricketworldcup.icccricketworldcup.UserEnd.HomeActivity;

public class BroadCast extends AppCompatActivity {
    private WebView webView;


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_cast);
        String link = getIntent().getStringExtra("link");
        webView = findViewById(R.id.wv_live);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl(link);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(BroadCast.this,HomeActivity.class));
        BroadCast.this.overridePendingTransition(0,0);
    }
}

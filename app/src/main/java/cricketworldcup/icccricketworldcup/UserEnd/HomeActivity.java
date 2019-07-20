package cricketworldcup.icccricketworldcup.UserEnd;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import cricketworldcup.icccricketworldcup.Home.HomeFragment;
import cricketworldcup.icccricketworldcup.R;
import cricketworldcup.icccricketworldcup.UserEnd.HighLights.HighLightFragment;
import cricketworldcup.icccricketworldcup.UserEnd.Latest.LatestFragment;
import cricketworldcup.icccricketworldcup.UserEnd.Matches.MatchFragment;
import cricketworldcup.icccricketworldcup.UserEnd.More.MoreFragment;

public class HomeActivity extends AppCompatActivity {
    private FrameLayout frameLayout;
    private BottomNavigationView bottomNavigationView;
    private ProgressDialog progressDialog;
    private HomeFragment homeFragment;
    private LatestFragment latestFragment;
    private MatchFragment matchFragment;
    private HighLightFragment highLightFragment;
    private MoreFragment moreFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        frameLayout = findViewById(R.id.frame_layout);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("No Internet Connection !");
        progressDialog.show();
        homeFragment = new HomeFragment();
        latestFragment = new LatestFragment();
        matchFragment = new MatchFragment();
        highLightFragment = new HighLightFragment();
        moreFragment = new MoreFragment();

        progressDialog.setCanceledOnTouchOutside(true);
        if (isNetworkAvailable()) {
            progressDialog.dismiss();
            SetFragment(homeFragment);
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()) {

                        case R.id.menu_home:
                            SetFragment(homeFragment);
                            return true;

                        case R.id.menu_latest:
                            SetFragment(latestFragment);
                            return true;

                        case R.id.menu_match:
                            SetFragment(matchFragment);
                            return true;

                        case R.id.menu_video:
                            SetFragment(highLightFragment);
                            return true;
                        case R.id.menu_more:
                            SetFragment(moreFragment);
                            return true;

                    }
                    return false;
                }
            });
        } else {


            progressDialog.show();


        }
    }


    private void SetFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack("my_fragment").commit();


    }

//    @Override
//    public void onBackPressed() {
//        moveTaskToBack(false);
//
//    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}

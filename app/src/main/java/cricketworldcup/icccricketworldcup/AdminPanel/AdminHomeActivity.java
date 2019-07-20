package cricketworldcup.icccricketworldcup.AdminPanel;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import cricketworldcup.icccricketworldcup.AdminPanel.QuizSection.QuizHomeFragment;
import cricketworldcup.icccricketworldcup.R;

public class AdminHomeActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private QuizHomeFragment quizHomeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        quizHomeFragment = new QuizHomeFragment();
        SetFragment(quizHomeFragment);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {

                    case R.id.menu_home:
                        SetFragment(quizHomeFragment);
                        break;


                }


                return false;
            }
        });
    }


    private void SetFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack("my_fragment").commit();


    }
}

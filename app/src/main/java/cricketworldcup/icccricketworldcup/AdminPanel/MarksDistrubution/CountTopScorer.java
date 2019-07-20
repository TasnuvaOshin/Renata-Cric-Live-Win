package cricketworldcup.icccricketworldcup.AdminPanel.MarksDistrubution;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cricketworldcup.icccricketworldcup.R;

public class CountTopScorer extends AppCompatActivity {

    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_top_scorer);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Top_Scorer");

        databaseReference.child("url").setValue("http://red-canvas.com/maxpro/Scorecard.png");
    }
}

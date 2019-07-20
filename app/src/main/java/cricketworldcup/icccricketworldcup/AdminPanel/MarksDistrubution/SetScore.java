package cricketworldcup.icccricketworldcup.AdminPanel.MarksDistrubution;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.ThreadLocalRandom;

import cricketworldcup.icccricketworldcup.R;

public class SetScore extends AppCompatActivity {
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_score);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Main_Score");

           databaseReference.addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   for(DataSnapshot ds : dataSnapshot.getChildren()){

                       updateScore(ds.getKey());
                   }
               }

               @Override
               public void onCancelled(@NonNull DatabaseError databaseError) {

               }
           });
    }

    private void updateScore(String key) {

        @SuppressLint({"NewApi", "LocalSuppress"}) int value = ThreadLocalRandom.current().nextInt(10, 50);
        if (10 < value && value < 20) {
            databaseReference.child(key).child("score").setValue("770");

        } else if (value > 20 && value < 30) {

            databaseReference.child(key).child("score").setValue("710");
        } else if (value >30 && value <40) {
            databaseReference.child(key).child("score").setValue("730");

        }
        else if (value >40 && value <50) {
            databaseReference.child(key).child("score").setValue("720");

        }
        else {
            databaseReference.child(key).child("score").setValue("740");

        }
    }
}

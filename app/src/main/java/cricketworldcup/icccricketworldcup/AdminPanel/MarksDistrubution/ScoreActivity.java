package cricketworldcup.icccricketworldcup.AdminPanel.MarksDistrubution;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import cricketworldcup.icccricketworldcup.R;

public class ScoreActivity extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private DatabaseReference db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Main_Score");
           db = FirebaseDatabase.getInstance().getReference().child("Instant_Quiz_participant");
           db.child("four").addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   for (DataSnapshot ds : dataSnapshot.getChildren()){
                 String key = ds.getKey();
                 CheckIt(key);

                   }
               }

               @Override
               public void onCancelled(@NonNull DatabaseError databaseError) {

               }
           });
    }

    private void CheckIt(final String key) {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Log.d("add", String.valueOf(dataSnapshot.getChildrenCount()));
              String orderk = ds.getKey();

              if(key.equals(orderk)){

                  Log.d("add","Already There");
              }else {

                  HashMap<String,String> hashMap = new HashMap<>();
                  hashMap.put("score","0");
                  databaseReference.child(key).setValue(hashMap);
                  Log.d("add","new added");


              }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

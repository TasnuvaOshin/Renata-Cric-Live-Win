package cricketworldcup.icccricketworldcup;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Main2Activity extends AppCompatActivity {
    DatabaseReference databaseReference;
    DatabaseReference result;
    private DatabaseReference db ;
   private String score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Main_Score");
        db = FirebaseDatabase.getInstance().getReference().child("users");

    databaseReference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
       for(DataSnapshot ds : dataSnapshot.getChildren()){

           score  = String.valueOf(ds.child("score").getValue());
           if(score.equals("770")) {
               findoutTheUse(ds.getKey());

           }


       }

        }



        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });

    }

    private void findoutTheUse(String key) {
db.orderByChild(key).addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren()) {

            String role = String.valueOf(ds.child("profile").getValue());
            if(!role.equals("Doctor")) {
                Log.d("name", String.valueOf(ds.child("username").getValue()) + ", " + String.valueOf(ds.child("psocode").getValue()) + " ," + String.valueOf(ds.child("mobileno").getValue()));
            }
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});
    }
}

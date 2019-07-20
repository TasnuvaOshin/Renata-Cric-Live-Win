package cricketworldcup.icccricketworldcup.UserEnd.More.TotalPoint;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cricketworldcup.icccricketworldcup.R;

public class MyPointFragment extends Fragment {

    private TextView textView;
    private DatabaseReference result;
    private FirebaseAuth firebaseAuth;
    private String userid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_point, container, false);
        textView = view.findViewById(R.id.showscore);
        firebaseAuth = FirebaseAuth.getInstance();
        userid = firebaseAuth.getCurrentUser().getUid();
        result = FirebaseDatabase.getInstance().getReference().child("Main_Score");



        result.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){

                    String key = ds.getKey();

                    if(key.equals(userid)){

                        String value = String.valueOf(ds.child("score").getValue());
                        textView.setText(value);

                    }



                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return view;
    }

}

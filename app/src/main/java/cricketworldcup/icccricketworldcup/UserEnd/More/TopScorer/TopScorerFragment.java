package cricketworldcup.icccricketworldcup.UserEnd.More.TopScorer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import cricketworldcup.icccricketworldcup.R;

public class TopScorerFragment extends Fragment {
    private ImageView img;
    private DatabaseReference databaseReference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_top_scorer, container, false);
databaseReference = FirebaseDatabase.getInstance().getReference().child("Top_Scorer");
img = view.findViewById(R.id.img);
databaseReference.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
   String link = String.valueOf(dataSnapshot.child("url").getValue());
        Picasso.get().load(link).into(img);

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});

        return view;
    }


}

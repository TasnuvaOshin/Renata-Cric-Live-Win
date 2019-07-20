package cricketworldcup.icccricketworldcup.UserEnd.More.Standings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import cricketworldcup.icccricketworldcup.R;

public class StandingsFragment extends Fragment {
    private TabHost tabHost;
    private ImageView img;
    private DatabaseReference ImgRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_standings, container, false);

        TabHost host = view.findViewById(R.id.tabHost);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("GROUP-STAGE");
        spec.setContent(R.id.tab1);
        spec.setIndicator("GROUP-STAGE");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("KNOCK-OUT");
        spec.setContent(R.id.tab2);
        spec.setIndicator("KNOCK-OUT");
        host.addTab(spec);
        img = view.findViewById(R.id.img);

        ImgRef = FirebaseDatabase.getInstance().getReference().child("PointTable");

        ImgRef.addListenerForSingleValueEvent(new ValueEventListener() {
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

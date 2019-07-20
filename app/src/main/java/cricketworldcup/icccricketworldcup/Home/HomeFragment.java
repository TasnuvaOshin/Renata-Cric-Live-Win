package cricketworldcup.icccricketworldcup.Home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import cricketworldcup.icccricketworldcup.R;
import cricketworldcup.icccricketworldcup.UserEnd.More.Teams.bangladesh.bangladeshFragment;

public class HomeFragment extends Fragment {
    ImageView imageView;
    ImageButton imageButton;
    bangladeshFragment bangladeshFragment;
    HomeQuizMenuFragment homeQuizMenuFragment;
    private DatabaseReference databaseReference;
    private ImageView banner;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReferenceLive;
    private ImageView teamoneimg, teamtwoimg;
    private TextView teamone, teamtwo;
    private TextView venue;
    private DatabaseReference bannerRef,bannerRef2;
    String s1b1,s1b2,s0b1,s0b2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        banner = view.findViewById(R.id.iv_banner_ads);
        bannerRef = FirebaseDatabase.getInstance().getReference().child("Banner").child("home").child("sectionone");

        bannerRef2 = FirebaseDatabase.getInstance().getReference().child("Banner").child("home").child("sectionzero");

        bannerRef.addListenerForSingleValueEvent(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            s1b1 = String.valueOf(dataSnapshot.child("bannerone").getValue());
            s1b2 = String.valueOf(dataSnapshot.child("bannertwo").getValue());


    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});


        bannerRef2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                s0b1 = String.valueOf(dataSnapshot.child("bannerone").getValue());
                s0b2 = String.valueOf(dataSnapshot.child("bannertwo").getValue());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        databaseReferenceLive = FirebaseDatabase.getInstance().getReference().child("Live_home_match");
        teamone = view.findViewById(R.id.teamonename);
        teamtwo = view.findViewById(R.id.teamtwoname);
        teamoneimg = view.findViewById(R.id.iv_flagone);
        teamtwoimg = view.findViewById(R.id.iv_flagtwo);
        venue = view.findViewById(R.id.tv_venue);
        firebaseAuth = FirebaseAuth.getInstance();
        imageView = view.findViewById(R.id.ban_section);
        imageButton = view.findViewById(R.id.ib_quiz);
        bangladeshFragment = new bangladeshFragment();
        homeQuizMenuFragment = new HomeQuizMenuFragment();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");


        databaseReferenceLive.limitToFirst(1).addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    String longdess = String.valueOf(ds.child("longdes").getValue());
                    String shortdess = String.valueOf(ds.child("shortdes").getValue());
                    String twoimgs = String.valueOf(ds.child("twoimg").getValue());
                    String oneimgs = String.valueOf(ds.child("oneimg").getValue());
                    String teamones = String.valueOf(ds.child("teamone").getValue());
                    String teamtwos = String.valueOf(ds.child("teamtwo").getValue());

                    teamone.setText(teamones);
                    teamtwo.setText(teamtwos);
                    Picasso.get().load(oneimgs).into(teamoneimg);
                    Picasso.get().load(twoimgs).into(teamtwoimg);
                    venue.setText(longdess + " " + shortdess);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        ShowBannerAds();
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetFragment(homeQuizMenuFragment);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetFragment(bangladeshFragment);
            }
        });


        return view;
    }

    private void ShowBannerAds() {
        databaseReference.child(firebaseAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String type = String.valueOf(dataSnapshot.child("type").getValue());
               // Toast.makeText(getActivity(), ""+type, Toast.LENGTH_SHORT).show();
                if (type.equals("1")) {

                    ShowtypeOne();

                } else {
                    ShowtypeZero();

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void ShowtypeOne() {


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            private boolean useDiceOne;

            @Override
            public void run() {

                if (!useDiceOne) {
                //    banner.setImageResource(R.drawable.azisan);
            Picasso.get().load(s1b1).into(banner);
                } else {
                    Picasso.get().load(s1b2).into(banner);
                }
                useDiceOne = !useDiceOne;
                handler.postDelayed(this, 5000);
            }
        }, 5000);


    }

    private void ShowtypeZero() {

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            private boolean useDiceOne;

            @Override
            public void run() {

                if (!useDiceOne) {
                    Picasso.get().load(s0b1).into(banner);
                } else {
                    Picasso.get().load(s0b2).into(banner);
                }
                useDiceOne = !useDiceOne;
                handler.postDelayed(this, 5000);
            }
        }, 5000);

    }


    private void SetFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack("my_fragment").commit();


    }


}

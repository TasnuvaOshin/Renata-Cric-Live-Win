package cricketworldcup.icccricketworldcup.Home;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import cricketworldcup.icccricketworldcup.R;
import cricketworldcup.icccricketworldcup.UserEnd.More.InstantQuiz.InstantQuizFragment;
import cricketworldcup.icccricketworldcup.UserEnd.More.MatchPrediction.MatchPredictionFragment;
import cricketworldcup.icccricketworldcup.UserEnd.SeenQuiz.SeenQuizFragment;


public class HomeQuizMenuFragment extends Fragment {
    private Button b1, b2, b3, b4, b5;
    private SeenQuizFragment seenQuizFragment;
    private MatchPredictionFragment matchPredictionFragment;
    private InstantQuizFragment instantQuizFragment;
    private GuideLineFragment guideLineFragment;
    private PrizeFragment prizeFragment;
    private ImageView banner;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference bannerRef,bannerRef2;
    String s1b1, s1b2, s0b1, s0b2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_quiz_menu, container, false);
        banner = view.findViewById(R.id.iv_banner_ads);
        bannerRef = FirebaseDatabase.getInstance().getReference().child("Banner").child("menu").child("sectionone");

        bannerRef2 = FirebaseDatabase.getInstance().getReference().child("Banner").child("menu").child("sectiontwo");

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

        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
        firebaseAuth = FirebaseAuth.getInstance();
        ShowBannerAds();
        b1 = view.findViewById(R.id.b1);
        b2 = view.findViewById(R.id.b2);
        b3 = view.findViewById(R.id.b3);
        b4 = view.findViewById(R.id.b4);
        b5 = view.findViewById(R.id.b5);

        seenQuizFragment = new SeenQuizFragment();
        matchPredictionFragment = new MatchPredictionFragment();
        instantQuizFragment = new InstantQuizFragment();
        guideLineFragment = new GuideLineFragment();
        prizeFragment = new PrizeFragment();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetFragment(seenQuizFragment);
            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetFragment(instantQuizFragment);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetFragment(matchPredictionFragment);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetFragment(guideLineFragment);
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetFragment(prizeFragment);
            }
        });

        return view;
    }

    private void SetFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack("my_fragment").commit();


    }

    private void ShowBannerAds() {
        databaseReference.child(firebaseAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String type = String.valueOf(dataSnapshot.child("type").getValue());
                if (type.equals("0")) {

                    ShowtypeZero();

                } else {

                    ShowtypeOne();
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
}

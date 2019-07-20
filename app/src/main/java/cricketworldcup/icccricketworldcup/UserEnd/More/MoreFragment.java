package cricketworldcup.icccricketworldcup.UserEnd.More;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import cricketworldcup.icccricketworldcup.Authentication.LoginActivity;
import cricketworldcup.icccricketworldcup.Home.GuideLineFragment;
import cricketworldcup.icccricketworldcup.Home.PrizeFragment;
import cricketworldcup.icccricketworldcup.R;
import cricketworldcup.icccricketworldcup.UserEnd.Matches.MatchFragment;
import cricketworldcup.icccricketworldcup.UserEnd.More.Account.AccountFragment;
import cricketworldcup.icccricketworldcup.UserEnd.More.InstantQuiz.InstantQuizFragment;
import cricketworldcup.icccricketworldcup.UserEnd.More.MatchPrediction.MatchPredictionFragment;
import cricketworldcup.icccricketworldcup.UserEnd.More.Standings.StandingsFragment;
import cricketworldcup.icccricketworldcup.UserEnd.More.Teams.TeamFragment;
import cricketworldcup.icccricketworldcup.UserEnd.More.TopScorer.TopScorerFragment;
import cricketworldcup.icccricketworldcup.UserEnd.More.TotalPoint.MyPointFragment;
import cricketworldcup.icccricketworldcup.UserEnd.SeenQuiz.SeenQuizFragment;


public class MoreFragment extends Fragment implements View.OnClickListener {
private CardView cv_topscorer,cv_guideline,cv_prize,cv_mypoints,cv_logout,cv_account,cv_all_matches,cv_standings,cv_teams,cv_seen_quiz,cv_instant_quiz,cv_match_prediction;
private StandingsFragment standingsFragment;
private TeamFragment teamFragment;
private SeenQuizFragment seenQuizFragment;
private MatchPredictionFragment matchPredictionFragment;
private InstantQuizFragment instantQuizFragment;
private MatchFragment matchFragment;
private AccountFragment accountFragment;
private MyPointFragment myPointFragment;
private GuideLineFragment guideLineFragment;
private PrizeFragment prizeFragment;
private TopScorerFragment topScorerFragment;
    private DatabaseReference databaseReference;
    private ImageView banner;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference bannerRef,bannerRef2;
    String s1b1,s1b2,s0b1,s0b2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        banner = view.findViewById(R.id.iv_banner_ads);
        bannerRef = FirebaseDatabase.getInstance().getReference().child("Banner").child("more").child("sectionone");

        bannerRef2 = FirebaseDatabase.getInstance().getReference().child("Banner").child("more").child("sectionzero");

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


        cv_standings = view.findViewById(R.id.cv_standings);
         cv_all_matches = view.findViewById(R.id.cv_all_match);
         cv_account = view.findViewById(R.id.cv_account);
         cv_logout = view.findViewById(R.id.cv_logout);
         cv_teams = view.findViewById(R.id.teams);
         cv_topscorer = view.findViewById(R.id.cv_topscorer);

         topScorerFragment = new TopScorerFragment();
         prizeFragment = new PrizeFragment();
         guideLineFragment = new GuideLineFragment();
         cv_mypoints = view.findViewById(R.id.cv_mypoints);
         standingsFragment = new StandingsFragment();
         teamFragment = new TeamFragment();
         accountFragment = new AccountFragment();
         seenQuizFragment = new SeenQuizFragment();
         matchPredictionFragment = new MatchPredictionFragment();
         instantQuizFragment = new InstantQuizFragment();
         matchFragment = new MatchFragment();
         myPointFragment = new MyPointFragment();

         cv_guideline = view.findViewById(R.id.cv_guideline);
         cv_prize = view.findViewById(R.id.cv_prize);

         cv_seen_quiz = view.findViewById(R.id.cv_seen_quiz);
         cv_match_prediction = view.findViewById(R.id.cv_match_prediction);
         cv_instant_quiz = view.findViewById(R.id.cv_instant_quiz);

         cv_topscorer.setOnClickListener(this);
         cv_guideline.setOnClickListener(this);
         cv_prize.setOnClickListener(this);
         cv_mypoints.setOnClickListener(this);

         cv_standings.setOnClickListener(this);
         cv_teams.setOnClickListener(this);
         cv_seen_quiz.setOnClickListener(this);
         cv_match_prediction.setOnClickListener(this);
         cv_instant_quiz.setOnClickListener(this);
         cv_all_matches.setOnClickListener(this);
         cv_account.setOnClickListener(this);
         cv_logout.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");


        ShowBannerAds();
        return view;
    }

    private void SetFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack("my_fragment").commit();


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.cv_standings:
                SetFragment(standingsFragment);
                break;

            case R.id.teams:
                SetFragment(teamFragment);
                break;

            case R.id.cv_seen_quiz:
                SetFragment(seenQuizFragment);
                break;

            case R.id.cv_match_prediction:
                SetFragment(matchPredictionFragment);
                break;
            case R.id.cv_instant_quiz:
                SetFragment(instantQuizFragment);
                break;
            case R.id.cv_all_match:
                SetFragment(matchFragment);
                break;


            case R.id.cv_account:
                SetFragment(accountFragment);
                break;

            case R.id.cv_mypoints:
                SetFragment(myPointFragment);
                break;

            case R.id.cv_guideline:
                SetFragment(guideLineFragment);
                break;

            case R.id.cv_prize:
                SetFragment(prizeFragment);
                break;

            case R.id.cv_topscorer:
                SetFragment(topScorerFragment);
                break;

            case R.id.cv_logout:
                firebaseAuth.signOut();
                startActivity(new Intent(getActivity(),LoginActivity.class));
                getActivity().finish();
                break;

        }
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

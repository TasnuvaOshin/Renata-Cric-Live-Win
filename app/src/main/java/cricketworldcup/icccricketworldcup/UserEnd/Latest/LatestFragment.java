package cricketworldcup.icccricketworldcup.UserEnd.Latest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import cricketworldcup.icccricketworldcup.API.OurClient;
import cricketworldcup.icccricketworldcup.API.OurResponse;
import cricketworldcup.icccricketworldcup.API.RetrofitSetup;
import cricketworldcup.icccricketworldcup.API.dataInfo;
import cricketworldcup.icccricketworldcup.API.runInfo;
import cricketworldcup.icccricketworldcup.R;
import cricketworldcup.icccricketworldcup.UserEnd.Latest.FullScreen.BroadCast;
import cricketworldcup.icccricketworldcup.UserEnd.Latest.Scorecard.ScorecardFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LatestFragment extends Fragment {
    private WebView webView;
    private Button button;
    private ScorecardFragment scorecardFragment;
    private ImageView banner;
    private DatabaseReference databaseReference, live;
    private FirebaseAuth firebaseAuth;
    private Button imageButton;
    private String url;
    TextView live_target, live_inning_teamone, live_team_one_name, live_team_one_run, live_team_one_wicket, live_team_one_over, live_inning_teamtwo, live_team_two_name, live_team_two_run, live_team_two_wicket, live_team_two_over;
    private LinearLayout nolive, showlive;
    String tone, ttwo;

    private DatabaseReference databaseReferenceLive;
    private ImageView teamoneimg, teamtwoimg;
    private TextView teamone, teamtwo;
    private TextView venue;
    private DatabaseReference bannerRef,bannerRef2;
    String s1b1,s1b2,s0b1,s0b2;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_latest, container, false);
        banner = view.findViewById(R.id.iv_banner_ads);
        bannerRef = FirebaseDatabase.getInstance().getReference().child("Banner").child("livematch").child("sectionone");

        bannerRef2 = FirebaseDatabase.getInstance().getReference().child("Banner").child("livematch").child("sectionzero");

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

        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
        firebaseAuth = FirebaseAuth.getInstance();
        live = FirebaseDatabase.getInstance().getReference().child("live_telecast");
        imageButton = view.findViewById(R.id.fullScreen);

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


        nolive = view.findViewById(R.id.No_live);
        showlive = view.findViewById(R.id.show_live);
        live_target = view.findViewById(R.id.live_target);
        live_inning_teamone = view.findViewById(R.id.live_inning_teamone);
        live_team_one_name = view.findViewById(R.id.live_team_one_name);
        live_team_one_run = view.findViewById(R.id.live_team_one_run);
        live_team_one_wicket = view.findViewById(R.id.live_team_one_wicket);
        live_team_one_over = view.findViewById(R.id.live_team_one_over);

        live_inning_teamtwo = view.findViewById(R.id.live_inning_teamtwo);
        live_team_two_name = view.findViewById(R.id.live_team_two_name);
        live_team_two_run = view.findViewById(R.id.live_team_two_run);
        live_team_two_wicket = view.findViewById(R.id.live_team_two_wicket);
        live_team_two_over = view.findViewById(R.id.live_team_two_over);


        OurClient client = RetrofitSetup.GetOurRetrofit(OurClient.class);
        Call<OurResponse> list = client.getOurResponse();


        list.enqueue(new Callback<OurResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<OurResponse> call, Response<OurResponse> response) {
                Log.d("Our Response", "We are getting Response");
                Log.d("Our Response", String.valueOf(response.body().toString()));
                List<dataInfo> dataInfoList = response.body().getData();
                int i;
                int j;
                if (dataInfoList.isEmpty()) {
                    showlive.setVisibility(View.GONE);
                    nolive.setVisibility(View.VISIBLE);
                } else {
                    nolive.setVisibility(View.GONE);
                    showlive.setVisibility(View.VISIBLE);
                    live_target.setText(dataInfoList.get(0).getNote());

                    List<runInfo> runInfoList = dataInfoList.get(0).getRuns();
                    if(runInfoList.isEmpty()){

                        showlive.setVisibility(View.GONE);
                        nolive.setVisibility(View.VISIBLE);

                    }else {
                        tone = runInfoList.get(0).getTeam_id();
                        String onename = GetTeamName(tone);
                        live_team_one_name.setText(onename);
                        live_team_one_run.setText(runInfoList.get(0).getScore());
                        live_inning_teamone.setText("Innings: " + runInfoList.get(0).getInning());
                        live_team_one_over.setText("Overs: " + runInfoList.get(0).getOvers());
                        live_team_one_wicket.setText(runInfoList.get(0).getWickets());

                        if (runInfoList.size() > 1) {
                            ttwo = runInfoList.get(1).getTeam_id();
                            String twoname = GetTeamName(ttwo);
                            live_team_two_name.setText(twoname);
                            live_team_two_run.setText(runInfoList.get(1).getScore());
                            live_inning_teamtwo.setText("Innings: " + runInfoList.get(1).getInning());
                            live_team_two_over.setText("Overs: " + runInfoList.get(1).getOvers());
                            live_team_two_wicket.setText(runInfoList.get(1).getWickets());
                        }


                    }

                }


            }

            @Override
            public void onFailure(Call<OurResponse> call, Throwable t) {
                Log.d("Our Response", "We are not getting Response");
            }
        });


        ShowBannerAds();
        scorecardFragment = new ScorecardFragment();
        button = view.findViewById(R.id.btn_scorecard);
        webView = view.findViewById(R.id.wv_broadcast);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setJavaScriptEnabled(true);
        //  webView.loadData("<iframe  src=\"https://www.youtube.com/embed/od1HxdhXcA4\" frameborder=\"0\" allow=\"acceleromete", "text/html", "utf-8");
        // webView.loadUrl("https://www.youtube.com/watch?v=od1HxdhXcA4&t=4s&fbclid=IwAR3oqmNPimU3Fp-J-54NURkH7B6nhMHm5l0fsP3uM-hbszx03fmfO6ls7SU");
        //webView.loadDataWithBaseURL(null, "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/od1HxdhXcA4\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>", "text/html", "UTF-8", null);

        //  webView.loadUrl("https://www.youtube.com/watch?v=-57NF20HULM");
        live.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                url = String.valueOf(dataSnapshot.child("url").getValue());
                webView.loadUrl(url);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetFragment(scorecardFragment);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(getActivity(), ""+url, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), BroadCast.class);
                intent.putExtra("link", url);
                startActivity(intent);
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
                   // banner.setImageResource(R.drawable.azisan);
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

    private String GetTeamName(String tone) {
        String name = null;
        if (tone.equals("39")) {
            name = "SL";
        }
        if (tone.equals("40")) {
            name = "SA";
        }
        if (tone.equals("37")) {
            name = "BAN";
        }
        if (tone.equals("46")) {
            name = "AFG";
        }
        if (tone.equals("10")) {
            name = "IND";
        }
        if (tone.equals("1")) {
            name = "PAK";
        }
        if (tone.equals("36")) {
            name = "AUS";
        }
        if (tone.equals("43")) {
            name = "WIN";
        }
        if (tone.equals("42")) {
            name = "NZ";
        }
        if (tone.equals("38")) {
            name = "ENG";
        }
        return name;
    }

}

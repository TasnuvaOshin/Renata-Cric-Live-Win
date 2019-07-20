package cricketworldcup.icccricketworldcup.UserEnd.HighLights;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import cricketworldcup.icccricketworldcup.R;
import cricketworldcup.icccricketworldcup.UserEnd.Latest.FullScreen.BroadCast;

public class HighLightFragment extends Fragment {
    private WebView webView1, webView2, webView3, webView4, webView5;
    private ImageView banner;
    private DatabaseReference databaseReference, videoRef;
    private FirebaseAuth firebaseAuth;
    private Button imageButton, imageButton2, imageButton3,imageButton4,imageButton5;

      String link1,link2,link3,link4,link5;
    private DatabaseReference bannerRef,bannerRef2;
    String s1b1,s1b2,s0b1,s0b2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_high_light, container, false);
        banner = view.findViewById(R.id.iv_banner_ads);

        bannerRef = FirebaseDatabase.getInstance().getReference().child("Banner").child("highlight").child("sectionone");

        bannerRef2 = FirebaseDatabase.getInstance().getReference().child("Banner").child("highlight").child("sectionzero");

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
        webView1 = view.findViewById(R.id.wv_v1);
        webView2 = view.findViewById(R.id.wv_v2);
        webView3 = view.findViewById(R.id.wv_v3);
        webView4 = view.findViewById(R.id.wv_v4);
        webView5 = view.findViewById(R.id.wv_v5);
        imageButton = view.findViewById(R.id.fullScreen);
        imageButton2 = view.findViewById(R.id.fullScreen2);
        imageButton3 = view.findViewById(R.id.fullScreen3);
        imageButton4 = view.findViewById(R.id.fullScreen4);
        imageButton5 = view.findViewById(R.id.fullScreen5);



        SetUpView(webView1);
        SetUpView(webView2);
        SetUpView(webView3);
        SetUpView(webView4);
        SetUpView(webView5);


        videoRef = FirebaseDatabase.getInstance().getReference().child("highlight_video");

        videoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               link1 = String.valueOf(dataSnapshot.child("video_one").getValue());
               link2 = String.valueOf(dataSnapshot.child("video_two").getValue());
               link3 = String.valueOf(dataSnapshot.child("video_three").getValue());
               link4 = String.valueOf(dataSnapshot.child("video_four").getValue());
               link5 = String.valueOf(dataSnapshot.child("video_five").getValue());
               webView1.loadUrl(link1);
               webView2.loadUrl(link2);
               webView3.loadUrl(link3);
               webView4.loadUrl(link4);
               webView5.loadUrl(link5);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BroadCast.class);
                intent.putExtra("link",link1);
                startActivity(intent);
            }
        });


        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BroadCast.class);
                intent.putExtra("link",link2);
                startActivity(intent);
            }
        });

        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BroadCast.class);
                intent.putExtra("link",link3);
                startActivity(intent);

            }
        });

        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BroadCast.class);
                intent.putExtra("link",link4);
                startActivity(intent);
            }
        });
        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BroadCast.class);
                intent.putExtra("link",link5);
                startActivity(intent);
            }
        });


        return view;
    }


    @SuppressLint("SetJavaScriptEnabled")
    private void SetUpView(WebView webView) {
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setTextZoom(100);
        webView.setInitialScale(1);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setJavaScriptEnabled(true);
        //  webView.loadData("<iframe  src=\"https://www.youtube.com/embed/od1HxdhXcA4\" frameborder=\"0\" allow=\"acceleromete", "text/html", "utf-8");
        // webView.loadUrl("https://www.youtube.com/watch?v=od1HxdhXcA4&t=4s&fbclid=IwAR3oqmNPimU3Fp-J-54NURkH7B6nhMHm5l0fsP3uM-hbszx03fmfO6ls7SU");
        // webView.loadDataWithBaseURL(null, "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/od1HxdhXcA4\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>", "text/html", "UTF-8", null);
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

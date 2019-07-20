package cricketworldcup.icccricketworldcup.UserEnd.Matches;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import cricketworldcup.icccricketworldcup.Model.fixture_model;
import cricketworldcup.icccricketworldcup.R;
import cricketworldcup.icccricketworldcup.UserEnd.Matches.Winner.winnerClass;
import cricketworldcup.icccricketworldcup.UserEnd.Matches.Winner.winnerViewHolder;
import cricketworldcup.icccricketworldcup.Viewholder.fixer_view_holder;


public class MatchFragment extends Fragment {
    private TabHost tabHost;
    private RecyclerView recyclerView;
    private FirebaseRecyclerOptions<fixture_model> options;
    private FirebaseRecyclerAdapter<fixture_model, fixer_view_holder> adapter;
    private DatabaseReference FixturedatabaseReference;
    private ImageView banner;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference bannerRef,bannerRef2;
    String s1b1,s1b2,s0b1,s0b2;

    private RecyclerView winnerRecyclerview;

    FirebaseRecyclerOptions<winnerClass> optionsWinner;
    FirebaseRecyclerAdapter<winnerClass,winnerViewHolder> Winneradapter;
    DatabaseReference databaseReferenceWinner;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_match, container, false);
        banner = view.findViewById(R.id.iv_banner_ads);
        bannerRef = FirebaseDatabase.getInstance().getReference().child("Banner").child("matchfixture").child("sectionone");

        bannerRef2 = FirebaseDatabase.getInstance().getReference().child("Banner").child("matchfixture").child("sectionzero");

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
databaseReferenceWinner  = FirebaseDatabase.getInstance().getReference().child("match");
      winnerRecyclerview = view.findViewById(R.id.recyclerviewwinner);
      winnerRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));






        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
        firebaseAuth = FirebaseAuth.getInstance();
        ShowBannerAds();
        recyclerView = view.findViewById(R.id.rv_fixture);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        TabHost host = view.findViewById(R.id.tabHost);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("FIXTURES");
        spec.setContent(R.id.tab1);
        spec.setIndicator("FIXTURES");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("RESULTS");
        spec.setContent(R.id.tab2);
        spec.setIndicator("RESULTS");
        host.addTab(spec);
        FixturedatabaseReference = FirebaseDatabase.getInstance().getReference().child("match");
        options = new FirebaseRecyclerOptions.Builder<fixture_model>().setQuery(FixturedatabaseReference, fixture_model.class).build();
        adapter = new FirebaseRecyclerAdapter<fixture_model, fixer_view_holder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull fixer_view_holder holder, int position, @NonNull fixture_model model) {

                Picasso.get().load(model.getImg()).into(holder.imageView);
                holder.teamone.setText(model.getTeamone());
                holder.teamtwo.setText(model.getTeamtwo());
                holder.time.setText(model.getLongdes());
                holder.venue.setText(model.getShortdes());
            }

            @NonNull
            @Override
            public fixer_view_holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                return new fixer_view_holder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fixture_row, viewGroup, false));
            }
        };



        optionsWinner = new FirebaseRecyclerOptions.Builder<winnerClass>().setQuery(databaseReferenceWinner,winnerClass.class).build();

        Winneradapter = new FirebaseRecyclerAdapter<winnerClass, winnerViewHolder>(optionsWinner) {
            @Override
            protected void onBindViewHolder(@NonNull winnerViewHolder holder, int position, @NonNull winnerClass model) {
                holder.textView.setText(model.getWinner());
            }

            @NonNull
            @Override
            public winnerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                return new winnerViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.win,viewGroup,false));
            }
        };
























        winnerRecyclerview.setAdapter(Winneradapter);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
        Winneradapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
        Winneradapter.stopListening();
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

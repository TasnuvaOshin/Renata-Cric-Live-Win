package cricketworldcup.icccricketworldcup.UserEnd.More.Teams.England;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import cricketworldcup.icccricketworldcup.Model.squad_model;
import cricketworldcup.icccricketworldcup.R;
import cricketworldcup.icccricketworldcup.UserEnd.PlayerProfile.PlayerProfileFragment;
import cricketworldcup.icccricketworldcup.Viewholder.squad_view_holder;


public class EnglandFragment extends Fragment {
    private TabHost tabHost;
    private RecyclerView recyclerView;
    private FirebaseRecyclerOptions<squad_model> options;
    private FirebaseRecyclerAdapter<squad_model, squad_view_holder> adapter;
    private DatabaseReference databaseReference;
    private Bundle bundle;
    private PlayerProfileFragment playerProfileFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_england, container, false);
        TabHost host = view.findViewById(R.id.tabHost);
        host.setup();
        bundle = new Bundle();
        playerProfileFragment = new PlayerProfileFragment();
        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("FIXTURES");
        spec.setContent(R.id.tab1);
        spec.setIndicator("FIXTURES");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("SQUAD");
        spec.setContent(R.id.tab2);
        spec.setIndicator("SQUAD");
        host.addTab(spec);
        recyclerView = view.findViewById(R.id.rv_ban_squad);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        databaseReference = FirebaseDatabase.getInstance().getReference().child("squad").child("ENGLAND");

        options = new FirebaseRecyclerOptions.Builder<squad_model>().setQuery(databaseReference, squad_model.class).build();
        adapter = new FirebaseRecyclerAdapter<squad_model, squad_view_holder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull squad_view_holder holder, int position, @NonNull final squad_model model) {
                Picasso.get().load(model.getImg()).into(holder.imageView);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bundle.putString("team","ENGLAND");
                        bundle.putString("name",model.getPlayer_name());
                        playerProfileFragment.setArguments(bundle);
                        SetFragment(playerProfileFragment);
                    }
                });
            }

            @NonNull
            @Override
            public squad_view_holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                return new squad_view_holder(LayoutInflater.from(getActivity()).inflate(R.layout.squad_row, viewGroup, false));
            }
        };

        recyclerView.setAdapter(adapter);






        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    private void SetFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();


    }
}

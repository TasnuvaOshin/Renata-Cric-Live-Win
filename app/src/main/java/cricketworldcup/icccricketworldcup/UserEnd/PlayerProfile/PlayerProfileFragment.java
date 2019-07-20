package cricketworldcup.icccricketworldcup.UserEnd.PlayerProfile;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import cricketworldcup.icccricketworldcup.R;


public class PlayerProfileFragment extends Fragment {
    private ImageView imageView;
    private DatabaseReference databaseReference;
    private String team,name,link;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        team = this.getArguments().getString("team");
         name = this.getArguments().getString("name");
         progressDialog = new ProgressDialog(getActivity());
         progressDialog.setMessage("Please Wait...");
         progressDialog.setCanceledOnTouchOutside(false);
         progressDialog.show();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("squad").child(team);
     //   Toast.makeText(getActivity(), "name is"+name+" from team  "+value, Toast.LENGTH_SHORT).show();
        View view = inflater.inflate(R.layout.fragment_player_profile, container, false);
      imageView = view.findViewById(R.id.playerImage);
      databaseReference.orderByChild("player_name").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              for(DataSnapshot ds : dataSnapshot.getChildren()){


                link = String.valueOf(ds.child("pro").getValue());
                  Picasso.get().load(link).into(imageView);
                  progressDialog.dismiss();
              }


          }

          @Override
          public void onCancelled(@NonNull DatabaseError databaseError) {
        progressDialog.dismiss();
          }
      });




        return view;
    }

}

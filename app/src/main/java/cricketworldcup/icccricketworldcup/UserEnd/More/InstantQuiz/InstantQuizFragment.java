package cricketworldcup.icccricketworldcup.UserEnd.More.InstantQuiz;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import cricketworldcup.icccricketworldcup.R;

public class InstantQuizFragment extends Fragment {
    private RadioGroup qusOneGroup;
    private RadioButton qusOneOptOne;
    private RadioButton qusOneOptTwo;
    private TextView qusone;
    private RelativeLayout relativeLayout, quizSection;
    private DatabaseReference databaseReference;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private Button button;
    private DatabaseReference submitDb;
    private View view;
    private ImageView banner;
    private DatabaseReference dn;
    String qno,currenUserId;
    private DatabaseReference bannerRef,bannerRef2;
    String s1b1, s1b2, s0b1, s0b2;

    public InstantQuizFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_instant_quiz, container, false);
        banner = view.findViewById(R.id.iv_banner_adstop);
        bannerRef = FirebaseDatabase.getInstance().getReference().child("Banner").child("instantquiz").child("sectionone");

        bannerRef2 = FirebaseDatabase.getInstance().getReference().child("Banner").child("instantquiz").child("sectionzero");

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


        dn = FirebaseDatabase.getInstance().getReference().child("Count");

        dn.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                qno = String.valueOf(dataSnapshot.child("instant_quiz_no").getValue());
                CheckStatus();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        button = view.findViewById(R.id.submit);
        submitDb = FirebaseDatabase.getInstance().getReference().child("Instant_Quiz_participant");
        relativeLayout = view.findViewById(R.id.root);
        firebaseAuth = FirebaseAuth.getInstance();
        currenUserId = firebaseAuth.getCurrentUser().getUid();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading......");
        progressDialog.show();
        quizSection = view.findViewById(R.id.quiz_section);
        qusOneGroup = view.findViewById(R.id.radioGroup1);
        qusOneOptOne = view.findViewById(R.id.rb_qus_one_op_one);
        qusOneOptTwo = view.findViewById(R.id.rb_qus_one_op_two);
        qusone = view.findViewById(R.id.match_quiz_question_one);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Instant_Quiz_Live");
        CheckQuestionAvailable();
        SetUpQuiz();

        ShowBannerAds();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HandleUserSelection();
            }
        });
        return view;

    }

    private void CheckQuestionAvailable() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("on").equals("1")) {

                    quizSection.setVisibility(View.VISIBLE);

                } else {


                    //  Toast.makeText(getActivity(), "No Quiz Available Right Now !!!", Toast.LENGTH_LONG).show();


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void HandleUserSelection() {


        int selectedId = qusOneGroup.getCheckedRadioButtonId();
        RadioButton radioButtonone = view.findViewById(selectedId);

        // Toast.makeText(getActivity(), "" + radioButtonone.getText(), Toast.LENGTH_SHORT).show();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("userid", firebaseAuth.getCurrentUser().getUid());
        hashMap.put("qus_one_ans", String.valueOf(radioButtonone.getText()));

        submitDb.child(qno).child(firebaseAuth.getCurrentUser().getUid()).setValue(hashMap);
        // quizSection.setVisibility(View.GONE);
        button.setText("Submitted");
        button.setEnabled(false);
        Toast.makeText(getActivity(), "Submited", Toast.LENGTH_SHORT).show();

    }


    private void CheckStatus() {


        submitDb.child(qno).child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    button.setText("Already Submitted");
                    button.setEnabled(false);
                    Toast.makeText(getActivity(), "Already Submited", Toast.LENGTH_SHORT).show();

                } else {
                    quizSection.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void SetUpQuiz() {

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                qusone.setText((CharSequence) dataSnapshot.child("qus").getValue());
                qusOneOptOne.setText((CharSequence) dataSnapshot.child("op_one").getValue());
                qusOneOptTwo.setText((CharSequence) dataSnapshot.child("op_two").getValue());
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void ShowBannerAds() {
        DatabaseReference df = FirebaseDatabase.getInstance().getReference().child("users");
        df.child(firebaseAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
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

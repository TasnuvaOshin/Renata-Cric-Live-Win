package cricketworldcup.icccricketworldcup.AdminPanel.QuizSection.InstantQuiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import cricketworldcup.icccricketworldcup.AdminPanel.AdminHomeActivity;
import cricketworldcup.icccricketworldcup.R;


public class InstantQuiz extends Fragment {
    Button submit, on, off;
    TextInputEditText et_embedded, et_qus, et_op_one, et_op_two, et_anser;
    DatabaseReference databaseReference;
    DatabaseReference SeenLive;
    private RelativeLayout relativeLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_instant_quiz2, container, false);
        on = view.findViewById(R.id.online);
        off = view.findViewById(R.id.offline);
        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SeenLive.child("on").setValue("1");
            }
        });

        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SeenLive.child("on").setValue("0");
            }
        });

        relativeLayout = view.findViewById(R.id.on_off);

        et_qus = view.findViewById(R.id.et_qustion);
        et_op_one = view.findViewById(R.id.et_op_one);
        et_op_two = view.findViewById(R.id.et_op_two);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Quiz").child("Instant_Quiz");
        SeenLive = FirebaseDatabase.getInstance().getReference().child("Instant_Quiz_Live");

        submit = view.findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                if (!TextUtils.isEmpty(et_op_one.getText())) {

                    if (!TextUtils.isEmpty(et_op_two.getText())) {

                        if (!TextUtils.isEmpty(et_qus.getText())) {

                            final HashMap<String, String> hashMap = new HashMap<>();

                            hashMap.put("on", "0");
                            hashMap.put("qus", et_qus.getText().toString());
                            hashMap.put("op_one", et_op_one.getText().toString());
                            hashMap.put("op_two", et_op_two.getText().toString());

                            SeenLive.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    databaseReference.push().setValue(hashMap);
                                    Toast.makeText(getActivity(), "Submited", Toast.LENGTH_SHORT).show();
                                    relativeLayout.setVisibility(View.VISIBLE);
                                }
                            });


                        } else {

                            et_qus.setError("Enter Correctly !");
                            et_qus.requestFocus();
                        }
                    } else {
                        et_op_two.setError("Enter Correctly !");
                        et_op_two.requestFocus();
                    }
                }


            }
        });


        return view;
    }


}

package cricketworldcup.icccricketworldcup.UserEnd.More.Account;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cricketworldcup.icccricketworldcup.R;


public class AccountFragment extends Fragment {

    private TextInputEditText password, nameInput, emailInput, phonenoInput, verificationcode, psocode;
    private String name, email, phoneno;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReferencedata, dbuser;
    private RelativeLayout relativeLayout, regis, passsec;
    private String pso;
    private String pass, profile;
    private String value;
    private ProgressDialog progressDialog;
    private Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        nameInput = view.findViewById(R.id.et_reg_name);
        emailInput = view.findViewById(R.id.et_reg_email_no);
        phonenoInput = view.findViewById(R.id.et_reg_mobile_no);
        psocode = view.findViewById(R.id.et_reg_dsm_no);
        relativeLayout = view.findViewById(R.id.root);
        regis = view.findViewById(R.id.registration_on);
        dbuser = FirebaseDatabase.getInstance().getReference().child("users");
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait....");
        progressDialog.setCanceledOnTouchOutside(false);
        button = view.findViewById(R.id.btn_signin);
        phonenoInput.setEnabled(false);
        emailInput.setEnabled(false);

        dbuser.child(mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                phonenoInput.setHint((CharSequence) dataSnapshot.child("mobileno").getValue());
                emailInput.setHint((CharSequence) dataSnapshot.child("email").getValue());
                nameInput.setHint((CharSequence) dataSnapshot.child("username").getValue());
                psocode.setHint((CharSequence) dataSnapshot.child("psocode").getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(nameInput.getText())) {

                    dbuser.child(mAuth.getCurrentUser().getUid()).child("username").setValue(nameInput.getText().toString());
                    Toast.makeText(getActivity(), "Updated!", Toast.LENGTH_SHORT).show();

                }
                if (!TextUtils.isEmpty(psocode.getText())) {

                    dbuser.child(mAuth.getCurrentUser().getUid()).child("psocode").setValue(psocode.getText().toString());
                    Toast.makeText(getActivity(), "Updated!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }


}

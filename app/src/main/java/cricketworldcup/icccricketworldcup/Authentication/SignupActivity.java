package cricketworldcup.icccricketworldcup.Authentication;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import cricketworldcup.icccricketworldcup.R;
import cricketworldcup.icccricketworldcup.UserEnd.HomeActivity;
import cricketworldcup.icccricketworldcup.Util.ValidationProcess;

public class SignupActivity extends AppCompatActivity {
    private Spinner spinner;
    private TextView textView;
    private TextInputEditText password, nameInput, emailInput, phonenoInput, verificationcode, psocode;
    private String name, email, phoneno;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReferencedata, dbuser;
    private RelativeLayout relativeLayout, regis, passsec;
    private String pso;
    private String pass, profile;
    private String value;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);
        spinner = findViewById(R.id.profile_spinner);
        textView = findViewById(R.id.profile_text);
        nameInput = findViewById(R.id.et_reg_name);
        emailInput = findViewById(R.id.et_reg_email_no);
        phonenoInput = findViewById(R.id.et_reg_mobile_no);
        psocode = findViewById(R.id.et_reg_dsm_no);
        relativeLayout = findViewById(R.id.root);
        regis = findViewById(R.id.registration_on);
        passsec = findViewById(R.id.password_section);
        password = findViewById(R.id.password);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait....");
        progressDialog.setCanceledOnTouchOutside(false);

        databaseReferencedata = FirebaseDatabase.getInstance().getReference().child("data");
        dbuser = FirebaseDatabase.getInstance().getReference().child("users");
        mAuth = FirebaseAuth.getInstance();
        ShowSpinner();
    }

    private void ShowSpinner() {
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.profile, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                //    questionInfo.put("question_four", adapterView.getItemAtPosition(i).toString());
                textView.setText((CharSequence) adapterView.getItemAtPosition(i));

                profile = String.valueOf(adapterView.getItemAtPosition(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void SignUpProcess(View view) {

        hideKeyBorad();
        progressDialog.show();
        name = nameInput.getText().toString().trim();
        email = emailInput.getText().toString().trim();
        phoneno = "+88" + phonenoInput.getText().toString().trim();
        relativeLayout = findViewById(R.id.root);
        pso = psocode.getText().toString();
        if (TextUtils.isEmpty(name)) {

            nameInput.requestFocus();
            nameInput.setError("Please Insert Your Name");
            ValidationProcess.NotifyUser(relativeLayout, "Please Insert Your Name");
            progressDialog.dismiss();
        }
        if (TextUtils.isEmpty(pso)) {

            psocode.requestFocus();
            psocode.setError("Please Insert your PSO Code");
            ValidationProcess.NotifyUser(relativeLayout, "Please Insert your DSM Code");

            progressDialog.dismiss();
        }
        if (TextUtils.isEmpty(phoneno)) {
            phonenoInput.requestFocus();
            phonenoInput.setError("Please Insert your Phone-no");
            ValidationProcess.NotifyUser(relativeLayout, "Please Insert your Phone-no");
            progressDialog.dismiss();
        } else {

            dbuser.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){

                        ValidationProcess.NotifyUser(relativeLayout,"Already Registered !");
                        progressDialog.dismiss();
                       startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                       SignupActivity.this.overridePendingTransition(0,0);



                    }else {



                        databaseReferencedata.orderByChild("D").equalTo(pso).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    //pso is there

                                    progressDialog.dismiss();
                                    regis.setVisibility(View.GONE);
                                    passsec.setVisibility(View.VISIBLE);
                                    ValidationProcess.NotifyUser(relativeLayout, "Please Enter Your Password: ");
                                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                                        ds.child("A").getValue();
                                        value = String.valueOf(ds.child("A").getValue());
                                    }


                                } else {

                                    ValidationProcess.NotifyUser(relativeLayout, "Invalid PSO CODE");
                                    progressDialog.dismiss();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });





                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

















        }
    }

    private void hideKeyBorad() {

        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void PasswordSubmit(View view) {
        progressDialog.show();
        hideKeyBorad();
        if (TextUtils.isEmpty(password.getText().toString())) {

            ValidationProcess.NotifyUser(relativeLayout, "Please Enter the Password");
            password.requestFocus();
            password.setError("Enter the Password");


        } else {
            pass = password.getText().toString().toLowerCase();


            if (value.equals("0")) {
                if (pass.equals("stark")) {
                    SubmitData("stark");


                } else {
                    progressDialog.dismiss();
                    ValidationProcess.NotifyUser(relativeLayout, "Please Write stark");


                }
            } else {

                if (pass.equals("azisan")) {
                    SubmitData("azisan");


                } else {

                    progressDialog.dismiss();
                    ValidationProcess.NotifyUser(relativeLayout, "Please Write azisan");


                }


            }
        }


    }

    private void GoToNext() {

        startActivity(new Intent(SignupActivity.this, HomeActivity.class));
        SignupActivity.this.overridePendingTransition(0, 0);

    }

    private void SubmitData(final String myPass) {


        final String email = "renata"+phonenoInput.getText().toString()+"@gmail.com";

        mAuth.createUserWithEmailAndPassword(email, value + "cricket").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String userid = mAuth.getCurrentUser().getUid();

                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("username", nameInput.getText().toString());
                    hashMap.put("email", email);
                    hashMap.put("mobileno", phonenoInput.getText().toString());
                    hashMap.put("psocode", psocode.getText().toString());
                    hashMap.put("type", value);
                    hashMap.put("pass", myPass);
                    hashMap.put("profile",profile);

                    dbuser.child(userid).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {

                                progressDialog.dismiss();
                                ValidationProcess.NotifyUser(relativeLayout, "Logged In");
                                GoToNext();

                            }
                        }
                    });

                } else {

                    ValidationProcess.NotifyUser(relativeLayout, "Unknow Error Occured");
                }
            }
        });





    }

}

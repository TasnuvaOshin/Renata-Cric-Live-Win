package cricketworldcup.icccricketworldcup.Authentication;

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
import android.widget.RelativeLayout;
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

import cricketworldcup.icccricketworldcup.R;
import cricketworldcup.icccricketworldcup.UserEnd.HomeActivity;
import cricketworldcup.icccricketworldcup.Util.ValidationProcess;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText phoneInput, passInput;
    private ProgressDialog progressDialog;
    private RelativeLayout relativeLayout;
    private DatabaseReference dbuser;
    private String code, pas, email,type;
    private FirebaseAuth firebaseAuth;
    private   String emailads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();
        phoneInput = findViewById(R.id.et_reg_name);
        passInput = findViewById(R.id.et_reg_email_no);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait....");
        progressDialog.setCanceledOnTouchOutside(false);

        relativeLayout = findViewById(R.id.root);
        dbuser = FirebaseDatabase.getInstance().getReference().child("users");
    }

    public void LogInSubmit(View view) {
        hideKeyBorad();
        progressDialog.show();
        if (TextUtils.isEmpty(phoneInput.getText().toString())) {

            phoneInput.requestFocus();
            phoneInput.setError("Please Insert Your Phone no");
            ValidationProcess.NotifyUser(relativeLayout, "Please Insert Your Phone No");
            progressDialog.dismiss();
        }

        if (TextUtils.isEmpty(passInput.getText().toString())) {

            passInput.requestFocus();
            passInput.setError("Please Insert Your Password");
            ValidationProcess.NotifyUser(relativeLayout, "Please Insert Your Password");
            progressDialog.dismiss();
        } else {


            dbuser.orderByChild("mobileno").equalTo(phoneInput.getText().toString()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (dataSnapshot.exists()) {

                    //    ValidationProcess.NotifyUser(relativeLayout, "logged In");
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {

                          emailads = String.valueOf(ds.child("email").getValue());
                            code = String.valueOf(ds.child("pass").getValue());
                            type = String.valueOf(ds.child("type").getValue());
                            //Toast.makeText(LoginActivity.this, ""+emailads, Toast.LENGTH_SHORT).show();

                        }

                        if (passInput.getText().toString().toLowerCase().equals(code)) {

                            firebaseAuth.signInWithEmailAndPassword(emailads,type+ "cricket").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                               if(task.isSuccessful()){
                                   Toast.makeText(LoginActivity.this, "Logged in", Toast.LENGTH_SHORT).show();

                                   progressDialog.dismiss();
                                   GoToNext();

                               }
                                }
                            });

                        } else {


                            ValidationProcess.NotifyUser(relativeLayout, "Please Enter the Password  " + code);
                            passInput.requestFocus();
                            passInput.setError("Enter your Password " + code);
                            progressDialog.dismiss();
                        }

                    } else {
                         progressDialog.dismiss();
                         ValidationProcess.NotifyUser(relativeLayout, "You are not a Registered User");

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

    private void GoToNext() {

        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        LoginActivity.this.overridePendingTransition(0, 0);

    }

    public void SignUpUser(View view) {

        startActivity(new Intent(LoginActivity.this, SignupActivity.class));
        LoginActivity.this.overridePendingTransition(0, 0);

    }
}

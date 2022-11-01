package in.aniruddhag.forcefest_appdev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    public FirebaseAuth mAuth;
    public FirebaseDatabase mDatabase;
    public DatabaseReference databaseReference;
    public FirebaseUser mUser;
    public String Username, Password, Address, AadharNo, PinCode, ContactNo;
    public static final String FILE_NAME = "localData.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        EditText txtin_UsID = findViewById(R.id.txtin_UsID);
        EditText txtin_UsPass = findViewById(R.id.txtin_UsPass);
        EditText txtin_UsAdh = findViewById(R.id.txtin_UsAdh);
        EditText txtin_UsLoc = findViewById(R.id.txtin_UsLoc);
        EditText txtin_UsPinNo = findViewById(R.id.txtin_UsPin);
        EditText txtin_UsCntctN = findViewById(R.id.txtin_UsCntctN);
        Button btn_SI = findViewById(R.id.btn_SI);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance("https://forcefest-78f93-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = mDatabase.getReference();

        txtin_UsID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String sEditable;
                sEditable = editable.toString();
                if (!sEditable.isEmpty()) {
                    if (!Patterns.EMAIL_ADDRESS.matcher(sEditable).matches()) {
                        txtin_UsID.setError("Wrong Email Format");
                    }
                }
            }
        });

        txtin_UsPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String sEditable;
                sEditable = editable.toString();
                if (!sEditable.isEmpty()) {
                    if (sEditable.length() < 8) {
                        txtin_UsPass.setError("Password should be at least of 8 characters.");
                    }
                }
            }
        });

        txtin_UsAdh.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String sEditable;
                sEditable = editable.toString();
                if (!sEditable.isEmpty()) {
                    if (sEditable.length() != 12) {
                        txtin_UsAdh.setError("Aadhar Number should be of 12 digits.");
                    }
                }
            }
        });

        txtin_UsPinNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String sEditable;
                sEditable = editable.toString();
                if (!sEditable.isEmpty()) {
                    if (sEditable.length() != 6) {
                        txtin_UsPinNo.setError("Pin Code should be of 6 digits.");
                    }
                }
            }
        });

        txtin_UsCntctN.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String sEditable;
                sEditable = editable.toString();
                if (!sEditable.isEmpty()) {
                    if (sEditable.length() != 10) {
                        txtin_UsCntctN.setError("Contact Number should be of 10 digits.");
                    }
                }
            }
        });

        btn_SI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Username = txtin_UsID.getText().toString();
                Password = txtin_UsPass.getText().toString();
                Address = txtin_UsLoc.getText().toString();
                AadharNo = txtin_UsAdh.getText().toString();
                PinCode = txtin_UsPinNo.getText().toString();
                ContactNo = txtin_UsCntctN.getText().toString();

                if (Patterns.EMAIL_ADDRESS.matcher(Username).matches()) {
                    mAuth.createUserWithEmailAndPassword(Username, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                mUser = mAuth.getCurrentUser();
                                DatabaseReference databasereference = databaseReference.child(mUser.getUid());
                                databasereference.child("Address").setValue(Address);
                                databasereference.child("AadharNo").setValue(AadharNo);
                                databasereference.child("PinCode").setValue(PinCode);
                                databasereference.child("ContactNo").setValue(ContactNo);

                                databasereference.child("Food").child("NoOfOrders").setValue(0);
                                startActivity(new Intent(SignUp.this, LandingPage.class));
                            } else Toast.makeText(SignUp.this, "Couldn't Successfully Create an Account. Error: " + task.getException(), Toast.LENGTH_LONG).show();

                        }
                    });
                } else {
                    Toast.makeText(SignUp.this, "failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void clickSU(View view) {
        Intent intent = new Intent(this, LogIn.class);
        startActivity(intent);
    }
}
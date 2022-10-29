package in.aniruddhag.forcefest_appdev;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

public class Food extends AppCompatActivity {
    public FirebaseAuth mAuth;
    public FirebaseDatabase mDatabase;
    public DatabaseReference databaseReference;
    public FirebaseUser mUser;

    EditText ET_N_f_dlts, ET_N_f_Chldrn, ET_fd_llrgy;
    TextView TV_fd_succss;
    Button btn_OF;
    Spinner spnnr_fd_vg_nnvg;
    Activity activity;
    View view;
    protected View onCreate(Activity activity) {
        this.activity = activity;
        LayoutInflater li = activity.getLayoutInflater();
        view = li.inflate(R.layout.activity_food, null);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance("https://forcefest-78f93-default-rtdb.asia-southeast1.firebasedatabase.app");
        databaseReference = mDatabase.getReference();

        spnnr_fd_vg_nnvg = (Spinner) view.findViewById(R.id.spnnr_fd_vg_nnvg);
        btn_OF = (Button) view.findViewById(R.id.btn_OF);
        ET_fd_llrgy = (EditText) view.findViewById(R.id.ET_fd_llrgy);
        ET_N_f_Chldrn = (EditText) view.findViewById(R.id.ET_N_f_Chldrn);
        ET_N_f_dlts = (EditText) view.findViewById(R.id.ET_N_f_dlts);
        TV_fd_succss = (TextView) view.findViewById(R.id.TV_fd_succss);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(activity.getApplicationContext(), R.array.strngrry, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnnr_fd_vg_nnvg.setAdapter(adapter);

        btn_OF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sAllergies = ET_fd_llrgy.getText().toString();
                String sFoodType = spnnr_fd_vg_nnvg.getSelectedItem().toString();
                String sN_f_Chldrn = ET_N_f_Chldrn.getText().toString();
                String sN_f_dlts = ET_N_f_dlts.getText().toString();
                String sCurrentTime = Calendar.getInstance().getTime().toString();
                if (!databaseReference.child(mUser.getUid()).child("Food").child("Allergies").setValue(sAllergies).addOnCompleteListener(task -> {})
                        .isSuccessful()) {
                    if (!databaseReference.child(mUser.getUid()).child("Food").child("Veg").setValue(sFoodType).addOnCompleteListener(task1 -> {})
                            .isSuccessful()) {
                        if (!databaseReference.child(mUser.getUid()).child("Food").child("N_f_Chldrn").setValue(sN_f_Chldrn).addOnCompleteListener(task1 -> {})
                                .isSuccessful()) {
                            if (!databaseReference.child(mUser.getUid()).child("Food").child("N_f_dlts").setValue(sN_f_dlts).addOnCompleteListener(task1 -> {})
                                    .isSuccessful()) {
                                if (!databaseReference.child(mUser.getUid()).child("Food").child("Time").setValue(sCurrentTime).addOnCompleteListener(task1 -> {})
                                        .isSuccessful()) {
                                    TV_fd_succss.setText("Your Order was successfully placed!");
                                    TV_fd_succss.setTextColor(Color.rgb(132, 255, 86));
                                }else Unsuccessful();
                            } else Unsuccessful();
                        }else Unsuccessful();
                    } else Unsuccessful();
                } else Unsuccessful();
            }
        });

        return view;
    }

    public void Unsuccessful() {
        TV_fd_succss.setText("Your Order was not successfully placed.");
        TV_fd_succss.setTextColor(Color.rgb(255, 95, 86));
    }
}
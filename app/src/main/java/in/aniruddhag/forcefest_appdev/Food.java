package in.aniruddhag.forcefest_appdev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
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
    Integer integer;

    protected View onCreate(Activity activity) {
        this.activity = activity;
        LayoutInflater li = activity.getLayoutInflater();
        view = li.inflate(R.layout.activity_food, null);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance("https://forcefest-78f93-default-rtdb.asia-southeast1.firebasedatabase.app");
        databaseReference = mDatabase.getReference().child(mUser.getUid()).child("Food");

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

                DatabaseReference databaseReferenceInt = databaseReference.child("Orders").push();

                databaseReference.child("OrderInProgress").setValue(true);
                databaseReferenceInt.child("Allergies").setValue(sAllergies);
                databaseReferenceInt.child("FoodType").setValue(sFoodType);
                databaseReferenceInt.child("NChildren").setValue(sN_f_Chldrn);
                databaseReferenceInt.child("NAdults").setValue(sN_f_dlts);

                if (databaseReferenceInt.child("Time").setValue(sCurrentTime).addOnCompleteListener(task -> {}).isSuccessful()) {
                    TV_fd_succss.setText("Your Order was not successfully placed.");
                    TV_fd_succss.setTextColor(Color.rgb(255, 95, 86));
                } else {
                    TV_fd_succss.setText("Your Order was successfully placed!");
                    TV_fd_succss.setTextColor(Color.rgb(132, 255, 86));
                }
            }
        });
        return view;
    }

    public class iSigleValGet {
        Integer integer;

        public iSigleValGet(Integer integer) {

        }
    }

    public class bSingleValGet {
        Boolean aBoolean;

        public bSingleValGet(Boolean aBoolean) {

        }
    }
}
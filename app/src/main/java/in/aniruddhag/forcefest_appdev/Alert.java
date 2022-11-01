package in.aniruddhag.forcefest_appdev;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Alert extends AppCompatActivity {
    public FirebaseAuth mAuth;
    public FirebaseDatabase mDatabase;
    public DatabaseReference databaseReference;
    public FirebaseUser mUser;

    ArrayList<AlertRecycleModel> alertRecycleModels = new ArrayList<>();

    Activity activity;
    View view;
    protected View onCreate(Activity activity) {
        this.activity = activity;
        LayoutInflater li = activity.getLayoutInflater();
        view = li.inflate(R.layout.activity_alert, null);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance("https://forcefest-78f93-default-rtdb.asia-southeast1.firebasedatabase.app");
        databaseReference = mDatabase.getReference();
//        setUpAlertRecycleModels();

        return view;
    }
    private void setUpAlertRecycleModels(){
        databaseReference.child(mUser.getUid()).child("Food").child("Orders").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    ArrayList<String> alsTime = new ArrayList<>();
                    ArrayList<String> alsTopic = new ArrayList<>();
                    ArrayList<String> alsDescription = new ArrayList<>();

                    ArrayList<String> arrayListSub1 = new ArrayList();
                    for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                        String dataKey = dataSnapshot.getKey();
                        databaseReference.child(mUser.getUid()).child("Food").child("Orders").child(dataKey).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                arrayListSub1.clear();
                                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                                    alsTime.add(dataSnapshot.child("Time").getValue(String.class).toString());
                                    alsTopic.add(dataSnapshot.child("Time").toString());
                                    if (Boolean.TRUE.equals(dataSnapshot.child("Progress").getValue(Boolean.class))) {
                                        alsDescription.add("Order in Progress");
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                } else {
                    Log.e("Error", "Error");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
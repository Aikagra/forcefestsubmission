package in.aniruddhag.forcefest_appdev;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

public class Medicine extends AppCompatActivity {

    Activity activity;
    View view;

    protected View onCreate(Activity activity) {
        this.activity = activity;
        LayoutInflater li = activity.getLayoutInflater();
        view = li.inflate(R.layout.activity_medicine, null);

        return view;
    }
}
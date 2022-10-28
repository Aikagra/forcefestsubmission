package in.aniruddhag.forcefest_appdev;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

public class Home extends AppCompatActivity {

    View view;
    Activity activity;

    protected View onCreate(Activity activity) {
        this.activity = activity;
        LayoutInflater li = activity.getLayoutInflater();
        view = li.inflate(R.layout.activity_home, null);

        return view;
    }
}
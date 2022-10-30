package in.aniruddhag.forcefest_appdev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LandingPage extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        bottomNavigationView = findViewById(R.id.BottomNavigation);
        frameLayout = findViewById(R.id.frmlyut_lndngpg);

        bottomNavigationView.setSelectedItemId(R.id.NB_Home);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int iItemId = (Integer) item.getItemId();
        frameLayout.removeAllViews();

        switch (iItemId) {
            case R.id.NB_Home:
                Home home = new Home();
                frameLayout.addView(home.onCreate(this));
                return true;
            case R.id.NB_Alert:
                Alert alert = new Alert();
                frameLayout.addView(alert.onCreate(this));
                return true;
            case R.id.NB_Food:
                Food food = new Food();
                frameLayout.addView(food.onCreate(this));
                return true;
            case R.id.NB_News:
                News news = new News();
                frameLayout.addView(news.onCreate(this));
                return true;
            case R.id.NB_Medicine:
                Medicine medicine = new Medicine();
                frameLayout.addView(medicine.onCreate(this));
                return true;
        }
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
    public void report(View view) {
        Intent intent = new Intent(this, Report.class);
        startActivity(intent);
    }
}
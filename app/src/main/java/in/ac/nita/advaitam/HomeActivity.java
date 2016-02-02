package in.ac.nita.advaitam;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void openEvents(View view){
        startActivity(new Intent(this, ListActivity.class)
                .putExtra("URL", MyConstants.eventsUrl)
                .putExtra("TITLE", "Events"));
    }
    public void openWorkshops(View view){
        startActivity(new Intent(this, ListActivity.class)
                .putExtra("URL", MyConstants.workshopsUrl)
                .putExtra("TITLE", "Workshops"));
    }
    public void openProNight(View view){
        startActivity(new Intent(this, PronightActivity.class)
                .putExtra("TITLE", "Pro Night"));
    }
    public void openAbouts(View view){
        startActivity(new Intent(this, About.class)
                .putExtra("TITLE", "About"));
    }

    public void openMap(View view){
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://goo.gl/maps/7HYfzEfZfv52")));
    }

}

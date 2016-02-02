package in.ac.nita.advaitam;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;
    CoordinatorLayout rootLayout;
    ImageView headerImage;
    TextView detailView,contactView, titleView;
    AppCompatButton linkButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initToolbar();
        initInstances();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initInstances() {

        rootLayout = (CoordinatorLayout) findViewById(R.id.rootLayout);
        headerImage = (ImageView)findViewById(R.id.headerImage);
        detailView = (TextView)findViewById(R.id.details);
        contactView = (TextView)findViewById(R.id.contact_details);
        linkButton = (AppCompatButton)findViewById(R.id.link_button);
        titleView = (TextView)findViewById(R.id.titleView);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        collapsingToolbarLayout.setTitle(" ");
        titleView.setText(getIntent().getStringExtra("EVENT_TITLE"));
        Picasso.with(this)
                .load(getIntent().getStringExtra("IMG_URL"))
                .placeholder(R.drawable.placeholder)
                .into(headerImage);
        detailView.setText(getIntent().getStringExtra("SMALL_DESC"));
        contactView.setText(getIntent().getStringExtra("CONTACT"));
        linkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getIntent().getStringExtra("LINK"))));
            }
        });
    }

    public void share(View view){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "I'm attending " + titleView.getText() + " in Advaitam 2016 at NIT Agartala. Know more http://advaitam.org.in/");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
}
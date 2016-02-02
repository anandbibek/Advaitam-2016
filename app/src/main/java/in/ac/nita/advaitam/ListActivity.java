package in.ac.nita.advaitam;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;

import in.ac.nita.advaitam.volley.CustomVolley;

public class ListActivity extends AppCompatActivity {

    ArrayList<FeedItem> feedItems;
    RecyclerView recyclerView;
    MyRecyclerAdapter adapter;
    RequestQueue requestQueue;
    View root;
    SwipeRefreshLayout swipeRefreshLayout;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        root = findViewById(R.id.rootLayout);
        requestQueue = CustomVolley.getInstance(this).getRequestQueue();
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefresh);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        feedItems = new ArrayList<>();
        preferences = getSharedPreferences(getIntent().getStringExtra("TITLE"),MODE_PRIVATE);
        //if(savedInstanceState==null) {
            loadData();
            getSupportActionBar().setTitle(getIntent().getStringExtra("TITLE"));
        //}

    }

    private void loadData(){

        String data = preferences.getString("data","EMPTY");
        if(!data.equals("EMPTY")){
            feedItems = Parser.parseDetails(data);
            adapter = new MyRecyclerAdapter(ListActivity.this, feedItems);
            recyclerView.setAdapter(adapter);
        }
        String url = getIntent().getStringExtra("URL");

        if(url!=null) {
            StringRequest request = new StringRequest(url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //Log.wtf("TEXT", response);
                            feedItems = Parser.parseDetails(response);
                            adapter = new MyRecyclerAdapter(ListActivity.this, feedItems);
                            recyclerView.setAdapter(adapter);
                            swipeRefreshLayout.setRefreshing(false);
                            preferences.edit().putString("data",response).apply();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            swipeRefreshLayout.setRefreshing(false);
                            Snackbar.make(root, "Could not load data. Check your internet connection", Snackbar.LENGTH_SHORT).show();
                        }
                    });
            requestQueue.add(request);
            swipeRefreshLayout.setRefreshing(true);
        }
    }
}

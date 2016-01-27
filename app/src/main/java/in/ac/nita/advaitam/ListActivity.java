package in.ac.nita.advaitam;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        requestQueue = CustomVolley.getInstance(this).getRequestQueue();
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefresh);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        feedItems = new ArrayList<>();
        if(savedInstanceState==null)
            loadData();

    }

    private void loadData(){

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
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            swipeRefreshLayout.setRefreshing(false);
                            Snackbar.make(recyclerView, "Something went wrong", Snackbar.LENGTH_SHORT);
                        }
                    });
            requestQueue.add(request);
            swipeRefreshLayout.setRefreshing(true);
        }
    }
}

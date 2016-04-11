package uta.edu.tutorme.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import uta.edu.tutorme.R;
import uta.edu.tutorme.adapters.PostCardAdapter;
import uta.edu.tutorme.models.PostCard;
import uta.edu.tutorme.models.User;
import uta.edu.tutorme.utils.DisplayMessage;
import uta.edu.tutorme.utils.SharedPrefUtils;
import uta.edu.tutorme.utils.Urls;
import uta.edu.tutorme.volly.MyJsonObjectRequest;
import uta.edu.tutorme.volly.VolleyRequestQueue;

public class HomepageActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,Response.Listener<JSONObject>,
        Response.ErrorListener {

    public static final String REQUEST_TAG = "HOMEPAGE_ACTIVITY";

    User user;
    PostCardAdapter adapter;
    RecyclerView list;
    private RequestQueue mQueue;
    ProgressDialog progressDialog;

    private void doOpenCreateNewAdv(){
        Intent intent = new Intent(this,AddNewAdvActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mQueue != null) {
            mQueue.cancelAll(REQUEST_TAG);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        user = SharedPrefUtils.getUserFromSession(getApplicationContext());
        progressDialog = new ProgressDialog(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doOpenCreateNewAdv();
                //Snackbar.make(view, "Add new post", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        LinearLayoutManager lm = new LinearLayoutManager(this);
        adapter = new PostCardAdapter();

        list = (RecyclerView) findViewById(R.id.list);
        list.setHasFixedSize(true);
        list.setLayoutManager(lm);
        list.setAdapter(adapter);

        mQueue = VolleyRequestQueue.getInstance(this.getApplicationContext())
                .getRequestQueue();
        refreshList();

    }

    private void refreshList(){
        adapter.emptyCards();

        progressDialog.setMessage("Loading your posts...");
        progressDialog.show();
        MyJsonObjectRequest getAllPostRequest = new MyJsonObjectRequest(Request.Method
                .GET, Urls.getUserPostsURL(user.getId()),
                null, this, this);

        getAllPostRequest.setTag(REQUEST_TAG);
        mQueue.add(getAllPostRequest);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.homepage, menu);
        TextView username = (TextView) findViewById(R.id.drawer_username);

        username.setText(user.getName());

        TextView email = (TextView) findViewById(R.id.drawer_user_email);
        email.setText(user.getEmail());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            refreshList();
            return true;
        }
        if (id == R.id.action_add_category) {
            Intent i = new Intent(getApplicationContext(),AddCategoryActivity.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.action_add_subcategory) {
            Intent i = new Intent(getApplicationContext(),AddSubCategoryActivity.class);
            startActivity(i);
            return true;
        }
        if(id == R.id.search){
            Intent i = new Intent(getApplicationContext(),SearchActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.drawer_all_post) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.drawer_latest_post) {

        } else if (id == R.id.drawer_change_password) {
            Intent intent = new Intent(getApplicationContext(),ChangePasswordActivity.class);
            startActivity(intent);
        } else if(id==R.id.drawer_edit_profile) {
            Intent intent = new Intent(getApplicationContext(), UpdateProfilePage.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_share) {

        } else if (id == R.id.drawer_logout) {
            SharedPrefUtils.deleteUserFromSharedPref(getApplicationContext());
            Intent intent = new Intent(this,LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progressDialog.hide();
        NetworkResponse response = error.networkResponse;

        if(response!=null && response.statusCode == 401){
            DisplayMessage.displayToast(getApplicationContext(), "Invalid login credentials");
        }
        else{
            DisplayMessage.displayToast(getApplicationContext(), "OOPS!! Some error occured ");
        }
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            JSONArray arr = response.getJSONArray("result");

            if(arr.length()>0) {
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject obj = arr.getJSONObject(i);
                    int id = obj.getInt("id");
                    String title = obj.getString("title");
                    double price = obj.getDouble("price");
                    double rating = obj.getDouble("rating");
                    double sponsored = obj.getDouble("sponsored");
                    String shortdesc = obj.getString("shortdesc");
                    PostCard card = new PostCard(id,title,price,rating,shortdesc,sponsored);
                    adapter.addCard(card);
                    list.scrollToPosition(0);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        progressDialog.hide();
    }
}

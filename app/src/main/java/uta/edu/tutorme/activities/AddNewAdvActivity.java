package uta.edu.tutorme.activities;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.Serializable;

import uta.edu.tutorme.R;
import uta.edu.tutorme.models.Post;


public class AddNewAdvActivity extends AppCompatActivity implements Serializable {


    FloatingActionButton fabSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_adv);
        fabSubmit = (FloatingActionButton) findViewById(R.id.fab1);
        fabSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPost();
            }

        });
    }

    private void sendPost()
    {
        Post post = new Post();
        Intent intent = new Intent(this,HomepageActivity.class);
        intent.putExtra("Post", post);
        startActivity(intent);
    }
}

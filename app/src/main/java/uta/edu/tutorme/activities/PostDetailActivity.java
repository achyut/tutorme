package uta.edu.tutorme.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import uta.edu.tutorme.R;
import uta.edu.tutorme.models.PostCard;
import uta.edu.tutorme.utils.DisplayMessage;
import uta.edu.tutorme.utils.SharedPrefUtils;

public class PostDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        SharedPrefUtils.checkIfLoggedIn(getApplicationContext());
        PostCard card = (PostCard)getIntent().getSerializableExtra("post");
        DisplayMessage.displayToast(getApplicationContext(), "Position " + card.getTitle());
    }
}

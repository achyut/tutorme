package uta.edu.tutorme.activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;

import uta.edu.tutorme.R;
import uta.edu.tutorme.models.Post;
import uta.edu.tutorme.models.PostCard;
import uta.edu.tutorme.utils.DisplayMessage;
import uta.edu.tutorme.utils.SharedPrefUtils;

public class PostDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        SharedPrefUtils.checkIfLoggedIn(getApplicationContext());
        PostCard postCard = (PostCard)getIntent().getSerializableExtra("post");
        DisplayMessage.displayToast(getApplicationContext(), "Position " + postCard.getTitle());

       //***** Dummy Supporters  Start *****//*
        Calendar calendarStartDate = Calendar.getInstance();
        calendarStartDate.set(Calendar.DATE, 10);
        calendarStartDate.set(Calendar.MONTH, 9);
        calendarStartDate.set(Calendar.YEAR, 2016);

        Calendar calendarEndDate = Calendar.getInstance();
        calendarEndDate.set(Calendar.DATE,17);
        calendarEndDate.set(Calendar.MONTH, 12);
        calendarEndDate.set(Calendar.YEAR,2016);

        Calendar calendarStartTime = Calendar.getInstance();
        calendarStartTime.set(Calendar.HOUR,9);
        calendarStartTime.set(Calendar.MINUTE, 00);
        calendarStartTime.set(Calendar.SECOND,00);

        Calendar calendarEndTime = Calendar.getInstance();
        calendarEndTime.set(Calendar.HOUR,10);
        calendarEndTime.set(Calendar.MINUTE, 30);
        calendarEndTime.set(Calendar.SECOND,00);


/***** Dummy Supporters End  *****/

        Post post = new Post();
        post.setTitle("Piano Class Available");
        post.setAddress("University Center, Arlington Texas");
      //  post.setCategory("Music");
        post.setContact("342554353");
        //post.setCreated_by();
        post.setEmail("ap@gmail.com");
        post.setShortdesc("Basic piano learning classes");
        post.setLongdesc("In this class, you will learn the basics and the notes for the piano");
        post.setStartdate(calendarStartDate.getTime());
        post.setEnddate(calendarEndDate.getTime());
        post.setStarttime(calendarStartTime.getTime());
        post.setEndtime(calendarEndTime.getTime());

        TextView postTitle =  (TextView)findViewById(R.id.exiting_edit_posttitle);
        TextView postAddress =  (TextView)findViewById(R.id.exiting_edit_address);
        TextView postShortDesc =  (TextView)findViewById(R.id.exiting_edit_shortdes);
        TextView postLongDesc =  (TextView)findViewById(R.id.exiting_edit_longdes);
        TextView postEmail =  (TextView)findViewById(R.id.exiting_edit_Emailaddress);
        TextView postContact =  (TextView)findViewById(R.id.exiting_edit_phonenumber);
        TextView postStartDate =  (TextView)findViewById(R.id.exiting_edit_startdate);
        TextView postEndDate =  (TextView)findViewById(R.id.exiting_edit_enddate);
        TextView postStartTime =  (TextView)findViewById(R.id.exiting_edit_starttime);
        TextView postEndTime =  (TextView)findViewById(R.id.exiting_edit_endtime);
        postTitle.setText(post.getTitle());
        postAddress.setText(post.getAddress());
        postShortDesc.setText(post.getShortdesc());
        postLongDesc.setText(post.getLongdesc());
        postEmail.setText(post.getEmail());
        postContact.setText(post.getContact());
        postStartDate.setText(post.getStartdate().toString());
        postEndDate.setText(post.getEnddate().toString());
        postStartTime.setText(post.getStarttime().toString());
        postEndTime.setText(post.getEndtime().toString());

    }
}

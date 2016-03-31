package uta.edu.tutorme.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import uta.edu.tutorme.R;
import uta.edu.tutorme.activities.PostDetailActivity;
import uta.edu.tutorme.models.PostCard;
import uta.edu.tutorme.utils.DisplayMessage;

/**
 * Created by ananda on 3/7/16.
 */
public class PostCardHolder extends RecyclerView.ViewHolder{

    TextView postcardtitle, postcardprice, postcardshortdesc;
    ImageView ivProfile;
    Context context;

    public PostCardHolder(Context context ,View itemView) {
        super(itemView);
        postcardtitle = (TextView) itemView.findViewById(R.id.postcard_title);
        postcardprice = (TextView) itemView.findViewById(R.id.postcard_price);
        postcardshortdesc = (TextView) itemView.findViewById(R.id.postcard_shortdesc);
        ivProfile = (ImageView) itemView.findViewById(R.id.iv_yak_profile);
        this.context = context;
    }

    public void bind(final List<PostCard> cards,PostCard card, final int position){
        postcardtitle.setText(card.getTitle());
        postcardprice.setText(String.valueOf(card.getPrice()));

        postcardshortdesc.setText(card.getShortdesc());
        postcardshortdesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostCard card = cards.get(position);
                Bundle bundle = new Bundle();
                Intent intent = new Intent(context,PostDetailActivity.class);
                intent.putExtra("post",card);
                context.startActivity(intent);
            }
        });
    }

}

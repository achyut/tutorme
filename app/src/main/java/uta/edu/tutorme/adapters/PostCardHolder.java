package uta.edu.tutorme.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import uta.edu.tutorme.R;
import uta.edu.tutorme.models.PostCard;

/**
 * Created by ananda on 3/7/16.
 */
public class PostCardHolder extends RecyclerView.ViewHolder  {

    TextView postcardtitle, postcardprice, porstcardshortdesc;
    ImageView ivProfile;
    ImageButton ibReply, ibRetweet, ibStar;

    public PostCardHolder(View itemView) {
        super(itemView);
        postcardtitle = (TextView) itemView.findViewById(R.id.postcard_title);
        postcardprice = (TextView) itemView.findViewById(R.id.postcard_price);
        porstcardshortdesc = (TextView) itemView.findViewById(R.id.postcard_shortdesc);
        ivProfile = (ImageView) itemView.findViewById(R.id.iv_yak_profile);

    }

    public void bind(PostCard card){
        postcardtitle.setText(card.getTitle());
        postcardprice.setText(String.valueOf(card.getPrice()));
        porstcardshortdesc.setText(card.getShortdesc());
    }

}

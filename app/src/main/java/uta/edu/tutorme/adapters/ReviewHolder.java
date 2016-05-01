package uta.edu.tutorme.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import uta.edu.tutorme.R;
import uta.edu.tutorme.models.Review;

/**
 * Created by ananda on 4/10/16.
 */
public class ReviewHolder extends RecyclerView.ViewHolder{
    TextView reviewText;
    RatingBar review_rating_bar;

    Context context;

    public ReviewHolder(Context context ,View itemView) {
        super(itemView);
        reviewText = (TextView) itemView.findViewById(R.id.review_text);
        review_rating_bar = (RatingBar) itemView.findViewById(R.id.review_rating_bar);
        this.context = context;
    }

    public void bind(final List<Review> cards,Review card, final int position){
        reviewText.setText(card.getReview());
        review_rating_bar.setRating(Float.parseFloat(card.getRating()));
        review_rating_bar.setEnabled(false);
    }
}

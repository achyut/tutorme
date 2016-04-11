package uta.edu.tutorme.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import uta.edu.tutorme.R;
import uta.edu.tutorme.models.Review;

/**
 * Created by ananda on 4/10/16.
 */
public class ReviewAdapter  extends RecyclerView.Adapter<ReviewHolder> {
    List<Review> cards;


    public ReviewAdapter() {
        this.cards = new ArrayList<Review>();
    }

    public void addCard(Review card){
        cards.add(card);
        notifyItemInserted(cards.size() - 1);
    }

    public void removeCard(int index){
        if(!cards.isEmpty()){
            cards.remove(index);
            notifyItemRemoved(index);
        }

    }

    public void removeCard(Review card){
        if(!cards.isEmpty()){
            int index = cards.indexOf(card);
            removeCard(index);
        }

    }
    public void emptyCards(){
        cards.clear();
        notifyDataSetChanged();
    }

    @Override
    public ReviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.review_card, parent, false);

        return new ReviewHolder(parent.getContext(),itemView);
    }

    @Override
    public void onBindViewHolder(ReviewHolder holder, int position) {
        Review card = cards.get(position);
        holder.bind(cards,card,position);
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

}

package uta.edu.tutorme.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import uta.edu.tutorme.R;
import uta.edu.tutorme.models.PostCard;

/**
 * Created by ananda on 3/7/16.
 */
public class PostCardAdapter extends RecyclerView.Adapter<PostCardHolder> {
    List<PostCard> cards;


    public PostCardAdapter() {
        this.cards = new ArrayList<PostCard>();
    }

    public void addCard(PostCard card){
        cards.add(card);
        notifyItemInserted(cards.size() - 1);
    }

    public void removeCard(int index){
        if(!cards.isEmpty()){
            cards.remove(index);
            notifyItemRemoved(index);
        }

    }

    public void removeCard(PostCard card){
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
    public PostCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.row_card, parent, false);

        return new PostCardHolder(parent.getContext(),itemView);
    }

    @Override
    public void onBindViewHolder(PostCardHolder holder, int position) {
        PostCard card = cards.get(position);
        holder.bind(cards,card,position);
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

}

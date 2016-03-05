package uta.edu.tutorme.adapters;
import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

import uta.edu.tutorme.models.Post;
/**
 * Created by Aishwarya on 3/5/2016.
 */
public class PostAdapter extends ArrayAdapter<Post>
{
    private List<Post> postList;
        Context context;
        public PostAdapter(Context context, int userId,
                               List<Post> postList) {
            super(context, userId, postList);
            this.postList = postList;
            this.context = context;
        }
    public List<Post> getPostList()
    {
        return postList;
    }
}

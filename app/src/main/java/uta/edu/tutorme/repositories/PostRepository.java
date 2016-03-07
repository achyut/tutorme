package uta.edu.tutorme.repositories;

import java.util.List;

import uta.edu.tutorme.models.Post;
import uta.edu.tutorme.models.User;

/**
 * Created by Aishwarya on 3/1/2016.
 */
public class PostRepository extends MapRepositoryImpl<Long,Post>{
    List<Post> p;

    public void save(Long id, Post post) {
        post.save();

    }

    public void delete(Long id, Post post) {
        post.delete();
    }

    public List<Post> findPostById(Long id, User u) {
        Post post = new Post();
        if (u.getUsertype().equalsIgnoreCase("tutor") && u.getId() == id)
            post.getU().setId(id);
        p.add(post);
            return p;
        }
    }

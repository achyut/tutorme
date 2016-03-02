package uta.edu.tutorme.services;

import uta.edu.tutorme.models.Post;
import uta.edu.tutorme.repositories.PostRepository;

/**
 * Created by Aishwarya on 3/1/2016.
 */
public class PostService extends GenericSerciveImpl<Long,Post,PostRepository>{

        public PostService(PostRepository repository) {
            super(repository);
        }
}

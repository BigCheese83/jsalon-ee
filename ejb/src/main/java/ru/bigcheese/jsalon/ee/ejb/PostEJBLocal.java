package ru.bigcheese.jsalon.ee.ejb;

import ru.bigcheese.jsalon.core.model.Post;
import ru.bigcheese.jsalon.ee.ejb.result.CrudEntityResult;
import ru.bigcheese.jsalon.ee.ejb.result.FindResult;

import javax.ejb.Local;

/**
 * Created by BigCheese on 27.05.15.
 */
@Local
public interface PostEJBLocal {
    CrudEntityResult<Post> createPost(Post post);
    CrudEntityResult<Post> updatePost(Post post);
    CrudEntityResult<Post> deletePost(Long id);
    FindResult<Post> getAllPosts();
}

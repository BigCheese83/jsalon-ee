package ru.bigcheese.jsalon.ee.ejb;

import ru.bigcheese.jsalon.core.model.PostServiceBind;
import ru.bigcheese.jsalon.ee.ejb.result.CrudEntityResult;
import ru.bigcheese.jsalon.ee.ejb.result.FindResult;

import javax.ejb.Local;

/**
 * Created by BigCheese on 18.04.16.
 */
@Local
public interface PostServiceEJBLocal {
    CrudEntityResult<PostServiceBind> createPostServiceBind(PostServiceBind bind);
    CrudEntityResult<PostServiceBind> updatePostServiceBind(PostServiceBind bind);
    CrudEntityResult<PostServiceBind> deletePostServiceBind(Long id);
    FindResult<PostServiceBind> getAllPostServiceBinds();
}

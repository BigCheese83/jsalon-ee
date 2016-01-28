package ru.bigcheese.jsalon.ee.ejb;

import ru.bigcheese.jsalon.core.model.Post;
import ru.bigcheese.jsalon.core.util.ExceptionUtils;
import ru.bigcheese.jsalon.ee.dao.PostDao;
import ru.bigcheese.jsalon.ee.ejb.result.CrudEntityResult;
import ru.bigcheese.jsalon.ee.ejb.result.FindResult;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

import static ru.bigcheese.jsalon.ee.ejb.result.ActionResult.*;

/**
 * Created by BigCheese on 27.05.15.
 */
@Stateless
public class PostEJB implements PostEJBLocal {

    @Resource
    SessionContext context;

    @Inject
    PostDao postDao;

    @Override
    public CrudEntityResult createPost(Post post) {
        try {
            if (!postDao.existsByName(post.getName())) {
                postDao.persist(post);
                return new CrudEntityResult(NORMAL, post.toString() + " успешно создана.", post.getId());
            } else {
                return new CrudEntityResult(WARNING, post.toString() + " уже содержится в БД.", post.getId());
            }
        } catch (Throwable e) {
            context.setRollbackOnly();
            return new CrudEntityResult(FATAL_ERROR, "Ошибка создания должности. " + ExceptionUtils.parse(e));
        }
    }

    @Override
    public CrudEntityResult updatePost(Post post) {
        try {
            if (postDao.existsById(post.getId())) {
                postDao.update(post);
                return new CrudEntityResult(NORMAL, post.toString() + " успешно обновлена.", post.getId());
            } else {
                return new CrudEntityResult(WARNING, post.toString() + " не найдена в БД.", post.getId());
            }
        } catch (Throwable e) {
            context.setRollbackOnly();
            return new CrudEntityResult(FATAL_ERROR, "Ошибка обновления должности. " + ExceptionUtils.parse(e));
        }
    }

    @Override
    public CrudEntityResult deletePost(Long id) {
        try {
            if (id == null) throw new IllegalArgumentException("ID=NULL");
            if (postDao.existsById(id)) {
                postDao.delete(id);
                return new CrudEntityResult(NORMAL, "Должность успешно удалена.", id);
            } else {
                return new CrudEntityResult(WARNING,  "Должность с ID=" + id + " не найдена в БД.", id);
            }
        } catch (Throwable e) {
            context.setRollbackOnly();
            return new CrudEntityResult(FATAL_ERROR, "Ошибка удаления должности. " + ExceptionUtils.parse(e));
        }
    }

    @Override
    public FindResult<Post> getAllPosts() {
        try {
            List<Post> result = postDao.findAll();
            return new FindResult<>(result);
        } catch (Throwable e) {
            return new FindResult<>(FATAL_ERROR, ExceptionUtils.parse(e));
        }
    }
}
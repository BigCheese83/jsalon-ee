package ru.bigcheese.jsalon.ee.ejb;

import ru.bigcheese.jsalon.core.model.bind.PostServiceBind;
import ru.bigcheese.jsalon.core.util.ExceptionUtils;
import ru.bigcheese.jsalon.ee.dao.PostServiceDao;
import ru.bigcheese.jsalon.ee.ejb.result.CrudEntityResult;
import ru.bigcheese.jsalon.ee.ejb.result.FindResult;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;

import static ru.bigcheese.jsalon.ee.ejb.result.ActionResult.FATAL_ERROR;
import static ru.bigcheese.jsalon.ee.ejb.result.ActionResult.NORMAL;
import static ru.bigcheese.jsalon.ee.ejb.result.ActionResult.WARNING;

/**
 * Created by BigCheese on 18.04.16.
 */
@Stateless
public class PostServiceFacadeBean implements PostServiceFacade {

    @Resource
    private SessionContext context;

    @Inject
    private PostServiceDao psDao;

    @Override
    public CrudEntityResult<PostServiceBind> createPostServiceBind(PostServiceBind bind) {
        try {
            psDao.persist(bind);
            return new CrudEntityResult<>(NORMAL, "Связь успешно создана.", bind);
        } catch (Throwable e) {
            context.setRollbackOnly();
            return new CrudEntityResult<>(FATAL_ERROR, "Ошибка создания связи. " + ExceptionUtils.parse(e));
        }
    }

    @Override
    public CrudEntityResult<PostServiceBind> updatePostServiceBind(PostServiceBind bind) {
        try {
            if (psDao.existsById(bind.getId())) {
                psDao.update(bind);
                return new CrudEntityResult<>(NORMAL, "Связь успешно обновлена.", bind);
            } else {
                return new CrudEntityResult<>(WARNING, "Связь c ID=" + bind.getId() + " не найдена в БД.");
            }
        } catch (Throwable e) {
            context.setRollbackOnly();
            return new CrudEntityResult<>(FATAL_ERROR, "Ошибка обновления связи. " + ExceptionUtils.parse(e));
        }
    }

    @Override
    public CrudEntityResult<PostServiceBind> deletePostServiceBind(Long id) {
        try {
            if (id == null) throw new IllegalArgumentException("ID=NULL");
            if (psDao.existsById(id)) {
                psDao.delete(id);
                return new CrudEntityResult<>(NORMAL, "Связь успешно удалена.");
            } else {
                return new CrudEntityResult<>(WARNING,  "Связь с ID=" + id + " не найдена в БД.");
            }
        } catch (Throwable e) {
            context.setRollbackOnly();
            return new CrudEntityResult<>(FATAL_ERROR, "Ошибка удаления связи. " + ExceptionUtils.parse(e));
        }
    }

    @Override
    public FindResult<PostServiceBind> getAllPostServiceBinds() {
        try {
            return new FindResult<>(psDao.findAll());
        } catch (Throwable e) {
            return new FindResult<>(FATAL_ERROR, ExceptionUtils.parse(e));
        }
    }
}

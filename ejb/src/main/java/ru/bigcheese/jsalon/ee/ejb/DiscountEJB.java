package ru.bigcheese.jsalon.ee.ejb;

import ru.bigcheese.jsalon.core.model.Discount;
import ru.bigcheese.jsalon.core.util.ExceptionUtils;
import ru.bigcheese.jsalon.ee.dao.DiscountDao;
import ru.bigcheese.jsalon.ee.ejb.result.CrudEntityResult;
import ru.bigcheese.jsalon.ee.ejb.result.FindResult;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

import static ru.bigcheese.jsalon.ee.ejb.result.ActionResult.*;

/**
 * Created by BigCheese on 14.05.15.
 */
@Stateless
public class DiscountEJB implements DiscountEJBLocal {

    @Resource
    SessionContext context;

    @Inject
    DiscountDao discountDao;

    @Override
    public CrudEntityResult createDiscount(Discount discount) {
        try {
            if (!discountDao.existsByName(discount.getName())) {
                discountDao.persist(discount);
                return new CrudEntityResult(NORMAL, discount.toString() + " успешно создана.", discount.getId());
            } else {
                return new CrudEntityResult(WARNING, "Должность \"" + discount.getName() + "\" уже содержится в БД.", discount.getId());
            }
        } catch (Throwable e) {
            context.setRollbackOnly();
            return new CrudEntityResult(FATAL_ERROR, "Ошибка создания скидки. " + ExceptionUtils.parse(e));
        }
    }

    @Override
    public CrudEntityResult updateDiscount(Discount discount) {
        try {
            if (discountDao.existsById(discount.getId())) {
                discountDao.update(discount);
                return new CrudEntityResult(NORMAL, discount.toString() + " успешно обновлена.", discount.getId());
            } else {
                return new CrudEntityResult(WARNING, discount.toString() + " не найдена в БД.", discount.getId());
            }
        } catch (Throwable e) {
            context.setRollbackOnly();
            return new CrudEntityResult(FATAL_ERROR, "Ошибка обновления скидки. " + ExceptionUtils.parse(e));
        }
    }

    @Override
    public CrudEntityResult deleteDiscount(Long id) {
        try {
            if (id == null) throw new IllegalArgumentException("ID=NULL");
            if (discountDao.existsById(id)) {
                discountDao.delete(id);
                return new CrudEntityResult(NORMAL, "Скидка успешно удалена.", id);
            } else {
                return new CrudEntityResult(WARNING,  "Скидка с ID=" + id + " не найдена в БД.", id);
            }
        } catch (Throwable e) {
            context.setRollbackOnly();
            return new CrudEntityResult(FATAL_ERROR, "Ошибка удаления скидки. " + ExceptionUtils.parse(e));
        }
    }

    @Override
    public FindResult<Discount> getAllDiscounts() {
        try {
            List<Discount> result = discountDao.findAll();
            return new FindResult<>(result);
        } catch (Throwable e) {
            return new FindResult<>(FATAL_ERROR, ExceptionUtils.parse(e));
        }
    }
}
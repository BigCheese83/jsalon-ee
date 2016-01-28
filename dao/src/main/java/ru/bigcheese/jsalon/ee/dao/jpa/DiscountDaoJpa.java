package ru.bigcheese.jsalon.ee.dao.jpa;

import ru.bigcheese.jsalon.core.model.Discount;
import ru.bigcheese.jsalon.ee.dao.DiscountDao;
import ru.bigcheese.jsalon.ee.dao.entity.DiscountEntity;
import ru.bigcheese.jsalon.ee.dao.entity.EntityMapper;
import ru.bigcheese.jsalon.ee.dao.qualifier.JPA;

import java.util.List;

import static ru.bigcheese.jsalon.ee.dao.entity.DiscountEntity.*;

/**
 * Created by BigCheese on 11.03.15.
 */
@JPA
public class DiscountDaoJpa extends AbstractBaseDaoJpa<Discount, Long, DiscountEntity>
        implements DiscountDao {

    @Override
    public Discount getDiscountByName(String name) {
        List<Discount> find = executeNamedQuery(FIND_BY_NAME, name);
        return !find.isEmpty() ? find.get(0) : null;
    }

    @Override
    public boolean existsByName(String name) {
        return !executeNamedQuery(EXISTS_BY_NAME, String.class, name).isEmpty();
    }

    @Override
    Discount toModel(DiscountEntity entity) {
        return EntityMapper.toDiscountModel(entity);
    }

    @Override
    DiscountEntity toEntity(Discount model) {
        return EntityMapper.toDiscountEntity(model);
    }
}

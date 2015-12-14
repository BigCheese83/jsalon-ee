package ru.bigcheese.jsalon.ee.dao.jpa;

import ru.bigcheese.jsalon.core.model.Discount;
import ru.bigcheese.jsalon.ee.dao.DiscountDao;
import ru.bigcheese.jsalon.ee.dao.entity.DiscountEntity;
import ru.bigcheese.jsalon.ee.dao.entity.EntityMapper;
import ru.bigcheese.jsalon.ee.dao.qualifier.JPA;

import java.util.List;

/**
 * Created by BigCheese on 11.03.15.
 */
@JPA
public class DiscountDaoJpa extends AbstractBaseDaoJpa<Discount, Long, DiscountEntity>
        implements DiscountDao {

    @Override
    public List<Discount> getDiscountsByName(String name) {
        return super.executeNamedQuery(DiscountEntity.FIND_BY_NAME, name);
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

package ru.bigcheese.jsalon.ee.dao.entity;

import ru.bigcheese.jsalon.core.model.Discount;
import ru.bigcheese.jsalon.core.model.Post;
import ru.bigcheese.jsalon.core.model.Service;

/**
 * Created by BigCheese on 06.08.15.
 */
public class EntityMapper {

    public static Service toServiceModel(ServiceEntity entity) {
        if (entity == null) return null;
        Service result = new Service();
        result.setId(entity.getId());
        result.setName(entity.getName());
        result.setCost(entity.getCost());
        result.setDuration(entity.getDuration());
        result.setDescription(entity.getDescription());
        return result;
    }

    public static ServiceEntity toServiceEntity(Service model) {
        if (model == null) return null;
        ServiceEntity result = new ServiceEntity();
        result.setId(model.getId());
        result.setName(model.getName());
        result.setCost(model.getCost());
        result.setDuration(model.getDuration());
        result.setDescription(model.getDescription());
        return result;
    }

    public static Discount toDiscountModel(DiscountEntity entity) {
        if (entity == null) return null;
        return new Discount(entity.getId(), entity.getName(), entity.getValue());
    }

    public static DiscountEntity toDiscountEntity(Discount model) {
        if (model == null) return null;
        return new DiscountEntity(model.getId(), model.getName(), model.getValue());
    }

    public static Post toPostModel(PostEntity entity) {
        if (entity == null) return null;
        return new Post(entity.getId(), entity.getName());
    }

    public static PostEntity toPostEntity(Post model) {
        if (model == null) return null;
        return new PostEntity(model.getId(), model.getName());
    }
}

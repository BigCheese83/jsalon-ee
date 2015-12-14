package ru.bigcheese.jsalon.ee.ejb;

import ru.bigcheese.jsalon.core.model.Discount;
import ru.bigcheese.jsalon.ee.ejb.result.CrudEntityResult;
import ru.bigcheese.jsalon.ee.ejb.result.FindResult;

import javax.ejb.Local;

/**
 * Created by BigCheese on 14.05.15.
 */
@Local
public interface DiscountEJBLocal {
    CrudEntityResult createDiscount(Discount discount);
    CrudEntityResult updateDiscount(Discount discount);
    CrudEntityResult deleteDiscount(Long id);
    FindResult<Discount> getAllDiscounts();
}

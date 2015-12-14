package ru.bigcheese.jsalon.ee.ejb;

import ru.bigcheese.jsalon.core.model.Service;
import ru.bigcheese.jsalon.ee.ejb.result.CrudEntityResult;
import ru.bigcheese.jsalon.ee.ejb.result.FindResult;

import javax.ejb.Local;

/**
 * Created by BigCheese on 02.06.15.
 */
@Local
public interface ServiceEJBLocal {
    CrudEntityResult createService(Service service);
    CrudEntityResult updateService(Service service);
    CrudEntityResult deleteService(Long id);
    FindResult<Service> getAllServices();
}

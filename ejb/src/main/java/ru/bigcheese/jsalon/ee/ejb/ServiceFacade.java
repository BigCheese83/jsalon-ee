package ru.bigcheese.jsalon.ee.ejb;

import ru.bigcheese.jsalon.core.model.to.ModelTO;
import ru.bigcheese.jsalon.core.model.Service;
import ru.bigcheese.jsalon.ee.ejb.result.CrudEntityResult;
import ru.bigcheese.jsalon.ee.ejb.result.FindResult;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by BigCheese on 02.06.15.
 */
@Local
public interface ServiceFacade {
    CrudEntityResult<Service> createService(Service service);
    CrudEntityResult<Service> updateService(Service service);
    CrudEntityResult<Service> deleteService(Long id);
    FindResult<Service> getAllServices();
    List<ModelTO> filterServicesByName(String name);
    List<ModelTO> filterServicesByNameAndMaster(String name, String fio);
}

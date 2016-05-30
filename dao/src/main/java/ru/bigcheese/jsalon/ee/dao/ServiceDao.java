package ru.bigcheese.jsalon.ee.dao;

import ru.bigcheese.jsalon.core.model.to.ModelTO;
import ru.bigcheese.jsalon.core.model.Service;

import java.util.List;

/**
 * Created by BigCheese on 02.06.15.
 */
public interface ServiceDao extends BaseDao<Service, Long> {
    Service getServiceByName(String name);
    boolean existsByName(String name);
    List<ModelTO> filterServicesByName(String name);
    List<ModelTO> filterServicesByNameAndMaster(String name, String... fio);
}

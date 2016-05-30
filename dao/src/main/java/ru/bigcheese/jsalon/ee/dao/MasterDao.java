package ru.bigcheese.jsalon.ee.dao;

import ru.bigcheese.jsalon.core.model.Master;
import ru.bigcheese.jsalon.core.model.to.ModelTO;
import ru.bigcheese.jsalon.core.model.Passport;

import java.util.List;

/**
 * Created by BigCheese on 25.12.15.
 */
public interface MasterDao extends BaseDao<Master, Long> {
    boolean existsByPassport(Passport passport);
    List<Master> findMastersByCriteria(QueryCriteria criteria);
    List<Master> findLimitMastersByCriteria(int count, QueryCriteria criteria);
    List<ModelTO> filterMastersByNames(String... names);
    List<ModelTO> filterMastersByNamesAndService(String service, String... names);
}

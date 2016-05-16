package ru.bigcheese.jsalon.ee.ejb;

import ru.bigcheese.jsalon.core.model.Master;
import ru.bigcheese.jsalon.ee.dao.QueryCriteria;
import ru.bigcheese.jsalon.ee.ejb.result.CrudEntityResult;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by BigCheese on 11.01.16.
 */
@Local
public interface MasterEJBLocal {
    CrudEntityResult<Master> createMaster(Master master);
    CrudEntityResult<Master> updateMaster(Master master);
    CrudEntityResult<Master> deleteMaster(Long id);
    List<Master> findLimitMastersByCriteria(int count, QueryCriteria criteria);
    List<Master> findMastersByCriteria(QueryCriteria criteria);
    List<String> filterMastersByNames(String fio);
    List<String> filterMastersByNamesAndService(String fio, String service);
}

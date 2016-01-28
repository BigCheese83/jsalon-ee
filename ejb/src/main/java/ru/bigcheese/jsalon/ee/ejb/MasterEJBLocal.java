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
    CrudEntityResult createMaster(Master master);
    CrudEntityResult updateMaster(Master master);
    CrudEntityResult deleteMaster(Long id);
    List<Master> findLimitUsersByCriteria(int count, QueryCriteria criteria);
    List<Master> findMastersByCriteria(QueryCriteria criteria);
}

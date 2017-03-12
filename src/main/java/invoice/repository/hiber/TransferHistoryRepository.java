package invoice.repository.hiber;

import invoice.dataobject.TransferHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by song on 2016/12/22.
 */
@Repository
public interface TransferHistoryRepository extends CrudRepository<TransferHistory, Integer>{

}

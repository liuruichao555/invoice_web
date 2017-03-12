package invoice.repository.hiber;

import invoice.dataobject.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by song on 2016/12/20.
 */
@Repository
public interface CompanyRepository extends CrudRepository<Company, Integer> {
}

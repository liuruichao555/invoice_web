package invoice.repository.hiber;

import invoice.dataobject.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by song on 2016/12/19.
 */
@Repository
public interface UserRepository extends CrudRepository<User, String> {
    List<User> findByMobile(String mobile);
    List<User> findByName(String name);
}

package invoice.repository.hiber;

import invoice.dataobject.Reimbursement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by song on 2016/12/22.
 */
@Repository
public interface ReimburseRepository extends PagingAndSortingRepository<Reimbursement, String> {
    public Page<Reimbursement> findByNameOrDepartment(String name, String department, Pageable pageable);
    Page<Reimbursement> findByCompanyTitle(String companyUserId, Pageable pageable);

    Page<Reimbursement> findByCompanyTitleAndNameOrDepartment(String companyUserId, String q, String q1, Pageable pageable);

    Page<Reimbursement> findByCompanyTitleAndState(String companyUserId, String state, Pageable pageable);

    Page<Reimbursement> findByCompanyTitleAndStateAndNameOrDepartment(String companyUserId, String state, String q, String q1, Pageable pageable);

    Page<Reimbursement> findBySubmitter(String userId, Pageable pageable);

    Page<Reimbursement> findBySubmitterAndCompanyTitleOrDepartment(String userId, String q, String q1, Pageable pageable);

    Page<Reimbursement> findBySubmitterAndState(String userId, String state, Pageable pageable);

    Page<Reimbursement> findBySubmitterAndStateAndCompanyTitleOrDepartment(String userId, String state, String q, String q1, Pageable pageable);
}

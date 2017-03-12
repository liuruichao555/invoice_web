package invoice.repository.hiber;

import invoice.dataobject.Invoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by song on 15/11/19.
 */
@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, String> {
//    List<Invoice> findByNumber(String number);
}

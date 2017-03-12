package invoice.repository.hiber;

import invoice.dataobject.Ticket;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by song on 2016/12/28.
 */
public interface TicketRepository extends CrudRepository<Ticket, Integer> {
}

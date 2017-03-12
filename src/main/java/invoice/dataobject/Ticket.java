package invoice.dataobject;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by song on 2016/12/28.
 */
@Entity
@Table(name = "ticket")
@JsonInclude(JsonInclude.Include.NON_NULL)
//相当于购物小票
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(columnDefinition = "TIMESTAMP")
    Date date;
    TicketStatus status;
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = javax.persistence.CascadeType.ALL)
    List<Item> itemList;

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}

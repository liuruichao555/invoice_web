package invoice.dataobject;

import invoice.dto.TransferDTO;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by song on 2016/12/22.
 */
@Entity
@Table
public class TransferHistory {
    @Id
    int id;

    @Embedded
    TransferDTO transferDTO;

    @Column(columnDefinition = "TIMESTAMP")
    Timestamp timestamp;

    public TransferDTO getTransferDTO() {
        return transferDTO;
    }

    public void setTransferDTO(TransferDTO transferDTO) {
        this.transferDTO = transferDTO;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

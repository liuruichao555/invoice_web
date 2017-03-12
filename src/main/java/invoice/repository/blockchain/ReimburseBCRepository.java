package invoice.repository.blockchain;

import invoice.dto.ReimbursementDTO;
import invoice.repository.blockchain.BlockChainRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.IdGenerator;
import util.BCServer;
import util.Result;

/**
 * Created by song on 2016/12/28.
 */
@Repository
public class ReimburseBCRepository extends BlockChainRepository{

    public Result save(ReimbursementDTO reimbursementDTO) {
        Object[] args = {"createbx",
                reimbursementDTO.getId(),reimbursementDTO.getInvoiceNumberList().replace("\t",","), reimbursementDTO.getMobile(),
                reimbursementDTO.getCompanyTitle(),
                BCServer.getTimeStamp(), "",BCServer.getTimeStamp()};

        return fireInvoke(args);
    }

    public Result find(String id, String companyUserId) {
        Object[] args = {"getReimburseInfo", id, companyUserId};
        return fireQuery(args);
    }

    public Result confirm(String id, String companyUserId) {
        Object[] args = {"confirmbx", id, companyUserId, BCServer.getTimeStamp()};
        return fireInvoke(args);
    }
}

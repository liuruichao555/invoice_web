package invoice.repository.blockchain;

import com.google.gson.Gson;
import org.springframework.stereotype.Repository;
import util.BCServer;
import util.JsonResponse;
import util.Result;
import util.UrlHelper;

/**
 * Created by song on 2016/12/25.
 */
@Repository
public class TransferBCRepository extends BlockChainRepository{
    public Result transfer(String fromId, String toId, String number) {
        Object[] args = {"transfer", number, fromId, toId, BCServer.getTimeStamp()};
        return fireInvoke(args);
    }
}

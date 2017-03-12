package invoice.service;

import invoice.dataobject.Invoice;
import invoice.dataobject.Trader;
import invoice.dataobject.TransferHistory;
import invoice.dto.TransferDTO;
import invoice.repository.blockchain.TransferBCRepository;
import invoice.repository.hiber.TransferHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.Result;

import javax.annotation.Resource;

/**
 * Created by song on 2016/12/21.
 */
@Service
public class TransferService {

    @Resource
    InvoiceService invoiceService;

    @Resource
    TransferHistoryRepository transferHistoryRepository;

    @Resource
    TransferBCRepository transferBCRepository;

    @Transactional
    public Result transfer(String fromId, String toId, Invoice invoice){
//        TODO 权限判断from与session
        Result result = new Result();
        if(!invoice.getOwnerId().equals(fromId)){
            result.setFailed("交易权限错误");
            return result;
        }
//        invoice.setOwnerId(toId);
//        result = invoiceService.update(invoice);
//        if(result.getStatus() < 0)
//            return result;
//        TransferHistory transferHistory = new TransferHistory();
//        TransferDTO transferDTO = new TransferDTO();
//        transferDTO.setFromId(fromId);
//        transferDTO.setInvoiceNumber(invoice.getNumber());
//        transferDTO.setToId(toId);
//        transferHistory.setTransferDTO(transferDTO);
//        transferHistoryRepository.save(transferHistory);
        result = transferBCRepository.transfer(fromId, toId, invoice.getNumber());
        return result;
    }

    public Result transfer(Trader from, Trader to, Invoice invoice){
        return transfer(from.getTraderId(), to.getTraderId(), invoice);
    }

    public Result transfer(TransferDTO transferDTO) {
        Invoice invoice = invoiceService.findByNumber(transferDTO.getInvoiceNumber());
        return transfer(transferDTO.getFromId(), transferDTO.getToId(), invoice);
    }
}

package invoice.service;

import invoice.dataobject.Reimbursement;
import invoice.dto.ReimbursementDTO;
import invoice.repository.blockchain.ReimburseBCRepository;
import invoice.repository.hiber.InvoiceRepository;
import invoice.repository.hiber.ReimburseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.PageDecorator;
import util.PageUtil;
import util.Result;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by song on 2016/12/22.
 */
@Service
public class ReimburseService extends BaseService {
    private final Logger logger = LoggerFactory.getLogger(ReimburseService.class);
    @Resource
    ReimburseRepository reimburseRepository;
    @Resource
    ReimburseBCRepository reimburseBCRepository;
    @Resource
    InvoiceRepository invoiceRepository;
    @Resource
    TransferService transferService;

    @Transactional
    public Result add(ReimbursementDTO reimbursementDTO){
        String id = "bx"+util.IdGenerator.getInvoiceId();
        reimbursementDTO.setId(id);
        Reimbursement reimbursement = new Reimbursement();
        BeanUtils.copyProperties(reimbursementDTO, reimbursement);
        reimbursement.setState("待处理");
        reimbursement.setSubmitter(reimbursementDTO.getMobile());
//        List<Invoice> invoiceList = new ArrayList<Invoice>();
//        for(String number : reimbursementDTO.getInvoiceNumberList()){
//            Invoice invoice = new Invoice();
//            invoice.setNumber(number);
//            invoiceList.add(invoice);
//            transferService.transfer(invoice.getOwnerId(), reimbursement.getCompanyTitle(), invoiceRepository.findOne(number));
//        }
//        reimbursement.setInvoiceList(invoiceList);
        reimburseRepository.save(reimbursement);
        return resultWrap(reimburseBCRepository.save(reimbursementDTO));
    }

    public PageDecorator<Reimbursement> getList(int pageNum, String q, String state, String asc, String desc, String companyUserId) {
        final PageRequest pageRequest = new PageRequest(pageNum-1, PageUtil.PAGE_SIZE);
        Page<Reimbursement> pageData;
        if("all".equals(state)||state==null){
            if(q == null||q.equals("")){
                pageData = reimburseRepository.findByCompanyTitle(companyUserId, pageRequest);
            }else {
                pageData = reimburseRepository.findByCompanyTitleAndNameOrDepartment(companyUserId, q, q, pageRequest);

            }
        }else{
            logger.info(state);
            if(q == null||q.equals("")){
                pageData = reimburseRepository.findByCompanyTitleAndState(companyUserId, state, pageRequest);
            }else {
                pageData = reimburseRepository.findByCompanyTitleAndStateAndNameOrDepartment(companyUserId, state, q, q, pageRequest);
            }
        }
        PageDecorator pageDecorator = PageUtil.pageWrap(pageData);
        return pageDecorator;
    }

    public PageDecorator<Reimbursement> getMyList(int pageNum, String q, String state, String asc, String desc, String userId) {
        final PageRequest pageRequest = new PageRequest(pageNum-1, PageUtil.PAGE_SIZE);
        Page<Reimbursement> pageData;
        if("all".equals(state)||state==null){
            if(q == null||q.equals("")){
                pageData = reimburseRepository.findBySubmitter(userId, pageRequest);
            }else {
                pageData = reimburseRepository.findBySubmitterAndCompanyTitleOrDepartment(userId, q, q, pageRequest);

            }
        }else{
            logger.info(state);
            if(q == null||q.equals("")){
                pageData = reimburseRepository.findBySubmitterAndState(userId, state, pageRequest);
            }else {
                pageData = reimburseRepository.findBySubmitterAndStateAndCompanyTitleOrDepartment(userId, state, q, q, pageRequest);
            }
        }
        PageDecorator pageDecorator = PageUtil.pageWrap(pageData);
        return pageDecorator;
    }

    public Reimbursement find(String id, String companyUserId) {
//        return reimburseBCRepository.find(id, companyUserId);
        return reimburseRepository.findOne(id);
    }


    public Result confirm(String id, String companyUserId) {
        Result result = reimburseBCRepository.confirm(id, companyUserId);
        if(result.getStatus() > 0){
            Reimbursement reimbursement = reimburseRepository.findOne(id);
            reimbursement.setState("已完成");
            reimburseRepository.save(reimbursement);
        }
        return result;
    }
}

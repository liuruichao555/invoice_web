package invoice.service;

import invoice.dataobject.*;
import invoice.dto.InvoiceOwnerMobileDTO;
import invoice.repository.blockchain.InvoiceBCRepository;
import invoice.repository.hiber.InvoiceRepository;
import invoice.repository.hiber.TicketRepository;
import invoice.repository.hiber.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdGenerator;
import util.Result;
import util.encrypt.PasswordSaltEncoder;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by song on 2016/12/20.
 */
@Service
public class InvoiceService extends BaseService{
    private final Logger logger = LoggerFactory.getLogger(InvoiceService.class);

    @Resource
    TransferService transferService;

    @Resource
    InvoiceRepository invoiceRepository;

    @Resource
    InvoiceBCRepository invoiceBCRepository;

    @Resource
    UserRepository userRepository;

    @Resource
    TicketRepository ticketRepository;

    @Resource
    CompanyService companyService;


    @Transactional
    public Result save(Invoice invoice) {
        return resultWrap(invoiceBCRepository.save(invoice));
    }
//
//    @Transactional Result update(Invoice invoice){
//        return save(invoice);
//    }

    @Transactional Invoice findByNumber(String number){
        return invoiceRepository.findOne(number);
    }

    @Transactional
    public List<Invoice> findAll() {
        return (List<Invoice>) invoiceRepository.findAll();
    }

    @Transactional
    public List<Invoice> findAll(User user) {
        return invoiceBCRepository.findAll(user.getTraderId(), user.getType());
    }

    @Transactional
    public Result create(InvoiceOwnerMobileDTO invoiceOwnerMobileDTO, String sellerId) {
        List<User> userList = userRepository.findByMobile(invoiceOwnerMobileDTO.getOwnerMobile());
        if(userList.size() ==0 )
            return new Result(-1, "没有该电话号码对应用户");
        Invoice invoice = new Invoice();
        BeanUtils.copyProperties(invoiceOwnerMobileDTO, invoice);
//      从链上查出  invoice.setOwnerId(sellerId);
        CompanyVO seller = new CompanyVO();
        seller.setTitle(sellerId);
        invoice.setSeller(seller);
        save(invoice);
        invoice.setOwnerId(sellerId);//为了第一次transfer
        return transferService.transfer(sellerId, userList.get(0).getTraderId(), invoice);
    }

    @Transactional
    public Invoice find(String invoiceNumber, String traderId){
        Invoice invoice = invoiceBCRepository.findOne(invoiceNumber, traderId);
        return invoice;
    }

    @Transactional
    public Result autoCreate(AutoDTO autoDTO, HttpServletRequest request) {
        Ticket ticket = ticketRepository.findOne(autoDTO.getTicketId());
        if(ticket.getStatus() == TicketStatus.INVALID){
            return errorResult("此小票已经兑换过发票");
        }
        ticket.setStatus(TicketStatus.INVALID);
        ticketRepository.save(ticket);


        List<User> userList = userRepository.findByMobile(autoDTO.getMobile());
        User user;
        if(userList.size() == 0){
            String mobile = autoDTO.getMobile();
            user = new User();
            user.setId(IdGenerator.getClientUUID());
            user.setPwd(PasswordSaltEncoder.encode(mobile.substring(7), user.getId()));
            user.setMobile(mobile);
            user.setName(mobile);
            user.setType(UserType.INDIVIDUAL);
            userRepository.save(user);
        }else
            user = userList.get(0);

        Company company = companyService.getDefaultSellerCompany();
        CompanyVO buyer = new CompanyVO();
        buyer.setTitle(autoDTO.getTitle());
        CompanyVO seller = new CompanyVO();
        BeanUtils.copyProperties(company, seller);
        Invoice invoice = new Invoice();
        invoice.setBuyer(buyer);
        invoice.setSeller(seller);
        invoice.setNumber(IdGenerator.getInvoiceId());
        invoice.setOwnerId(seller.getTraderId());
        invoice.setDate(new Date());
        invoice.setItemList(ticket.getItemList());
        save(invoice);

        request.getSession().setAttribute("user", user);
        logger.info(invoice.getNumber());
        return transferService.transfer(seller.getTraderId(), user.getTraderId(), invoice);
    }
}

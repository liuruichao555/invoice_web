package invoice.controller;

/**
 * Created by song on 15/11/19.
 */
import invoice.dataobject.Invoice;
import invoice.dataobject.AutoDTO;
import invoice.dataobject.User;
import invoice.dataobject.UserType;
import invoice.dto.InvoiceOwnerMobileDTO;
import invoice.dto.InvoiceTableRowDTO;
import invoice.dto.TransferDTO;
import invoice.repository.hiber.UserRepository;
import invoice.service.InvoiceService;
import invoice.service.TransferService;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import util.Result;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@EnableAutoConfiguration
//@ComponentScan("invoice.dataobject.*")
@RequestMapping("rest/invoice")
public class InvoiceRestController extends BaseController{
    @Resource
    InvoiceService invoiceService;
    @Resource
    TransferService transferService;
    @Resource
    UserRepository userRepository;

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Result create(@RequestBody InvoiceOwnerMobileDTO invoiceOwnerMobileDTO, HttpServletRequest request) {
        Object ou = request.getSession().getAttribute("user");
        if(ou == null)
            return new Result(-1, "没有登录");
        User user = (User)ou;
        if(user.getType() != UserType.COM)
            return new Result(-1, "没有权限");

        Result result = invoiceService.create(invoiceOwnerMobileDTO, ((User)ou).getTraderId());
        return result;
    }

    @RequestMapping(value = "transfer", method = RequestMethod.POST)
    public Result transfer(@RequestBody TransferDTO transferDTO) {
        return transferService.transfer(transferDTO);
    }

    @RequestMapping(value = "{number}", method = RequestMethod.GET)
    public Result find(@PathVariable String number, HttpServletRequest request){
        Invoice invoice = invoiceService.find(number, ((User) request.getSession().getAttribute("user")).getTraderId());
//        InvoiceOwnerMobileDTO invoiceOwnerMobileDTO = new InvoiceOwnerMobileDTO();
//        BeanUtils.copyProperties(invoice, invoiceOwnerMobileDTO);
//        invoiceOwnerMobileDTO.setOwnerMobile(user.getMobile());
        return resultWrap(invoice);
    }

    @RequestMapping(value = "{ownerId}/{number}", method = RequestMethod.GET)
    public Result findByTraderId(@PathVariable String ownerId, @PathVariable String number, HttpServletRequest request){
        Invoice invoice = invoiceService.find(number, ownerId);
//        InvoiceOwnerMobileDTO invoiceOwnerMobileDTO = new InvoiceOwnerMobileDTO();
//        BeanUtils.copyProperties(invoice, invoiceOwnerMobileDTO);
//        invoiceOwnerMobileDTO.setOwnerMobile(user.getMobile());
        return resultWrap(invoice);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Result find(HttpServletRequest request){
        List<Invoice> invoiceList = invoiceService.findAll((User) request.getSession().getAttribute("user"));
        if(invoiceList == null)
            return resultWrap(null);

        List<InvoiceTableRowDTO> invoiceTableRowDTOList = new ArrayList<>();
        for (Invoice invoice : invoiceList) {
            InvoiceTableRowDTO dto = new InvoiceTableRowDTO();
            BeanUtils.copyProperties(invoice, dto);
            dto.setBuyerId(invoice.getBuyer().getTraderId());
            dto.setSellerId(invoice.getSeller().getTraderId());
            String status = "";
            switch (invoice.getStatus()){
                case "0" : status = "新创建";break;
                case "1" : status = "流转中";break;
                case "2" : status = "报销中";break;
                case "3" : status = "报销完成";break;
                default: status = "";
            }
            dto.setStatus(status);
            invoiceTableRowDTOList.add(dto);
        }
        return resultWrap(invoiceTableRowDTOList);
    }


    @RequestMapping(value = "auto", method = RequestMethod.POST)
    public Result autoCreate(@RequestBody AutoDTO autoDTO, HttpServletRequest request){
        return invoiceService.autoCreate(autoDTO, request);
    }

}

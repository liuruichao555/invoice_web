package invoice.controller;

import invoice.dataobject.Invoice;
import invoice.dataobject.Reimbursement;
import invoice.dataobject.User;
import invoice.dataobject.UserType;
import invoice.dto.InvoiceOwnerMobileDTO;
import invoice.dto.ReimbursementDTO;
import invoice.service.ReimburseService;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;
import util.PageDecorator;
import util.Result;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;

/**
 * Created by song on 2016/12/22.
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("rest/reimburse")
public class ReimburseRestController extends BaseController {

    @Resource
    ReimburseService reimburseService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Result add(@RequestBody ReimbursementDTO reimbursementDTO, HttpServletRequest request) {
        Result result;
        try {

            reimbursementDTO.setMobile(((User)request.getSession().getAttribute("user")).getMobile());
            result = reimburseService.add(reimbursementDTO);
        }catch (DataIntegrityViolationException exception){
            result = new Result();
            result.setFailed("重复报销!");
        }
        return result;
    }

    @RequestMapping(value = "mine/list/{pageNum}", method = RequestMethod.GET)
    @ResponseBody
    public PageDecorator<Reimbursement> mineList(@PathVariable int pageNum, String q, String asc, String desc, String state, HttpServletRequest request/*,Model model*/){
        if(q!=null)
            q = URLDecoder.decode(q);
        if(state!=null)
            state = URLDecoder.decode(state);
        String companyUserId = safeGetUserId(request);
        return reimburseService.getMyList(pageNum, q, state, asc, desc, companyUserId);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result view(@PathVariable String id, HttpServletRequest request/*,Model model*/){
        String companyUserId = safeGetCompanyUserId(request);
        return resultWrap(reimburseService.find(id, companyUserId));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Result confirm(@PathVariable String id, HttpServletRequest request/*,Model model*/){
        String companyUserId = safeGetCompanyUserId(request);
        return resultWrap(reimburseService.confirm(id, companyUserId));
    }

    @RequestMapping(value = "/list/{pageNum}", method = RequestMethod.GET)
    @ResponseBody
    public PageDecorator<Reimbursement> viewList(@PathVariable int pageNum, String q, String asc, String desc, String state, HttpServletRequest request/*,Model model*/){
        if(q!=null)
            q = URLDecoder.decode(q);
        if(state!=null)
            state = URLDecoder.decode(state);
        String companyUserId = safeGetCompanyUserId(request);
        System.out.println("company: " + companyUserId);
        return reimburseService.getList(pageNum, q, state, asc, desc, companyUserId);
    }

    private String safeGetUserId(HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("user");
        if(user == null || user.getType() != UserType.INDIVIDUAL)
            return "";
        else{
            return user.getName();
        }
    }

    String safeGetCompanyUserId(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        if(user == null || user.getType() != UserType.COM)
            return "oxchains";
        else{
            return user.getName();
        }
    }
}

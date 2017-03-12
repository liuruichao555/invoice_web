package invoice.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


/**
 * MobileController
 *
 * @author liuruichao
 * Created on 2017/1/10 15:13
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/mobile")
public class MobileController {
    private String MOBILE_DIR_PRE = "/mobile";

    @RequestMapping("")
    public String index() {
        return MOBILE_DIR_PRE + "/index";
    }

    @RequestMapping("/auto")
    public String auto() {
        return MOBILE_DIR_PRE + "/auto";
    }

    @RequestMapping("/invoice/list")
    public String invoiceList() {
        return MOBILE_DIR_PRE + "/invoiceList";
    }

    @RequestMapping("/reimburse/add")
    public String reimburseAdd(HttpServletRequest request, String buyerId, String list) {
        request.setAttribute("buyerId", buyerId);
        request.setAttribute("list", list);
        return MOBILE_DIR_PRE + "/reimburseAdd";
    }

    @RequestMapping("/reimburse/list")
    public String reimburseList() {
        return MOBILE_DIR_PRE + "/reimburseList";
    }

    @RequestMapping("/reimburse/edit/{id}")
    public String reimburseEdit(@PathVariable String id, HttpServletRequest request) {
        request.setAttribute("id", id);
        return MOBILE_DIR_PRE + "/reimburseEdit";
    }

    @RequestMapping("/reimburse/mine/list")
    public String reimburseMineList() {
        return MOBILE_DIR_PRE + "/reimburseMineList";
    }

    @RequestMapping("/login")
    public String login() {
        return MOBILE_DIR_PRE + "/login";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return MOBILE_DIR_PRE + "/login";
    }

    @RequestMapping("/register")
    public String register() {
        return MOBILE_DIR_PRE + "/register";
    }

    @RequestMapping("/apply")
    public String register2() {
        return MOBILE_DIR_PRE + "/register2";
    }

    @RequestMapping("/invoice/{mobile}/{number}")
    public String invoiceDetail(@PathVariable String mobile, @PathVariable String number, HttpServletRequest request) {
        request.setAttribute("mobile", mobile);
        request.setAttribute("number", number);
        return MOBILE_DIR_PRE + "/invoiceDetail";
    }
}

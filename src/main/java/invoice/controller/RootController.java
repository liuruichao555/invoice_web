package invoice.controller;

import invoice.dataobject.Company;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import util.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by song on 2016/12/27.
 */
@Controller
@EnableAutoConfiguration
public class RootController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "hello";
    }

    @RequestMapping(value = "invoice/add", method = RequestMethod.GET)
    public String index1() {
        return "/invoice/add";
    }

    @RequestMapping(value = "invoice/{number}", method = RequestMethod.GET)
    public String index1_5() {
        return "/invoice/view";
    }

    @RequestMapping(value = "invoice/{mobile}/{number}", method = RequestMethod.GET)
    public String index1_9(Map<String,String> map, @PathVariable String mobile, HttpServletRequest request) {
        map.put("traderId", mobile);
        request.setAttribute("traderId", mobile);
        return "/invoice/viewByMobile";
    }


    @RequestMapping(value = "invoice/list", method = RequestMethod.GET)
    public String index1_6() {
        return "/invoice/list";
    }

    @RequestMapping(value = "reimburse/add", method = RequestMethod.GET)
    public String index1_8() {
        return "reimburse/add";
    }
    @RequestMapping(value = "reimburse/edit/{id}", method = RequestMethod.GET)
    public String reimburseEdit(){
        return "reimburse/edit";
    }
    @RequestMapping(value = "reimburse/view/{id}", method = RequestMethod.GET)
    public String reimburseView(){
        return "reimburse/view";
    }
    @RequestMapping(value = "reimburse/delete", method = RequestMethod.GET)
    public String reimburseDelete(){
        return "reimburse/delete";
    }
    @RequestMapping(value = "reimburse/list", method = RequestMethod.GET)
    public String reimburseList(){
        return "reimburse/list";
    }
    @RequestMapping(value = "reimburse/mine/list", method = RequestMethod.GET)
    public String reimburseMyList(){
        return "reimburse/myList";
    }


    @RequestMapping(value = "auto", method = RequestMethod.GET)
    public String index1_7() {
        return "/invoice/auto";
    }


    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String index4() {
        return "/user/register";
    }

    @RequestMapping(value = "apply", method = RequestMethod.GET)
    public String index2() {
        return "/user/apply";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String index3(HttpServletRequest request) {
        if(request.getSession().getAttribute("user") != null)
            return "hello";
        return "/user/login";
    }
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String index3_1(HttpServletRequest request) {
        request.getSession().invalidate();
        return "/user/login";
    }
}

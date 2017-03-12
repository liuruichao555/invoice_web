package invoice.controller;

/**
 * Created by song on 2016/12/21.
 */

import invoice.dataobject.Company;
import invoice.dataobject.Invoice;
import invoice.dataobject.User;
import invoice.service.CompanyService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import util.Result;

import javax.annotation.Resource;

@RestController
@EnableAutoConfiguration
@RequestMapping("company")
public class CompanyController {
    @Resource
    CompanyService companyService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Result add(@RequestBody Company company) {
        return companyService.add(company);
    }
}

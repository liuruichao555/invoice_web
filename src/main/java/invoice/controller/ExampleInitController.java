package invoice.controller;

import com.google.gson.Gson;
import invoice.dataobject.*;
import invoice.repository.hiber.CompanyRepository;
import invoice.repository.hiber.TicketRepository;
import invoice.repository.hiber.UserRepository;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import util.encrypt.PasswordSaltEncoder;

import javax.annotation.Resource;

/**
 * Created by song on 2016/12/28.
 */
@Controller
@EnableAutoConfiguration
public class ExampleInitController {
    @Resource
    TicketRepository ticketRepository;
    @Resource
    CompanyRepository companyRepository;
    @Resource
    UserRepository userRepository;

    @RequestMapping(value = "example", method = RequestMethod.GET)
    public String index1() {
        Company company = new Company();
        company.setTitle("hsyy");
        company.setAddress("Beijing");
        company.setBankName("ICBC");
        company.setBankAccount("62221234567890");
        company.setTaxId("123456");
        companyRepository.save(company);
        User user = new User();
        user.setId("hsyy");
        user.setCompanyId(company.getId());
        user.setName("hsyy");
        user.setPwd(PasswordSaltEncoder.encode("JD", user.getId()));
        user.setMobile(company.getPhone());
        user.setType(UserType.COM);

        String itemListJson= "{\"itemList\":[{\"id\":0,\"name\":\"iphone\",\"spec\":\"bad\",\"unit\":\"box\",\"quantity\":1,\"price\":6888.0,\"taxRate\":0.0,\"taxAmount\":0.0,\"totalPrice\":6888.0}]}";
        for(int i = 0;i < 1000; i++) {
            Ticket ticket = new Gson().fromJson(itemListJson, Ticket.class);
            ticket.setStatus(TicketStatus.VALID);
            ticketRepository.save(ticket);
        }
//
        return "/initComplete";
    }
}

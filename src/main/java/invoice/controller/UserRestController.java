package invoice.controller;

import invoice.dataobject.Company;
import invoice.dataobject.User;
import invoice.dataobject.UserType;
import invoice.dto.UserCompanyDTO;
import invoice.repository.hiber.CompanyRepository;
import invoice.repository.hiber.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import util.IdGenerator;
import util.Result;
import util.encrypt.PasswordSaltEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by song on 2016/12/19.
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/rest/user")
public class UserRestController {
    @Resource
    UserRepository userRepository;
    @Resource
    CompanyRepository companyRepository;
    @RequestMapping("/register")
    public Result register(@RequestBody User user, HttpServletRequest request){
        Result result = new Result();
        if(userRepository.findByName(user.getName()).size() >0 || userRepository.findByMobile(user.getMobile()).size() >0){
            result.setFailed("用户名或手机号已经存在");
            return result;
        }
        user.setType(UserType.INDIVIDUAL);
        user.setId(IdGenerator.getClientUUID());
        user.setPwd(PasswordSaltEncoder.encode(user.getPwd(), user.getId()));
        User retUser = userRepository.save(user);
        result.setSuccess("注册成功", retUser);
        request.getSession().setAttribute("userId", user.getId());
        request.getSession().setAttribute("user", user);
        return result;
    }

    @RequestMapping("/apply")
    public Result apply(@RequestBody UserCompanyDTO userCompanyDTO, HttpServletRequest request){
        Result result = new Result();
        User user = new User();
        Company company = new Company();
        BeanUtils.copyProperties(userCompanyDTO, user);
        BeanUtils.copyProperties(userCompanyDTO, company);
        company.setTitle(user.getName());
        if(dupCheck(user)){
            result.setFailed("用户名或手机号已经存在");
            return result;
        }
        //TODO 下移到service 事务
        user.setType(UserType.COM);
        user.setId(user.getName());
        user.setPwd(PasswordSaltEncoder.encode(user.getPwd(), user.getId()));
        user.setCompanyId(companyRepository.save(company).getId());
        User retUser = userRepository.save(user);
        result.setSuccess("注册成功", retUser);
        request.getSession().setAttribute("userId", user.getId());
        request.getSession().setAttribute("user", user);
        return result;
    }

    boolean dupCheck(User user){
        return userRepository.findByName(user.getName()).size() >0 || userRepository.findByMobile(user.getMobile()).size() >0;
    }

    @RequestMapping("/login")
    public Result login(@RequestBody User user, HttpServletRequest request){
        user.setId(IdGenerator.getClientUUID());
        List<User> userList = userRepository.findByName(user.getName());
        User user2 = userList.size() > 0 ? userList.get(0):null ;
        Result result = new Result();
        if(user2 == null||!user2.getPwd().equals(PasswordSaltEncoder.encode(user.getPwd(), user2.getId()))){
            result.setFailed("用户或密码错");
        }else{
            result.setSuccess("登录成功", user2.getId());
            request.getSession().setAttribute("userId", user2.getId());
            request.getSession().setAttribute("user", user2);
        }
        return result;
    }

    @RequestMapping("/logout")
    public Result logout(HttpServletRequest request){
        request.getSession().invalidate();
        return null;
    }
}

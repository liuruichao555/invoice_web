package invoice.service;

import invoice.dataobject.Company;
import invoice.repository.hiber.CompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.Result;

import javax.annotation.Resource;

/**
 * Created by song on 2016/12/21.
 */
@Service
public class CompanyService extends BaseService{
    @Resource
    CompanyRepository companyRepository;

    @Transactional
    public Result add(Company company) {
//        String mobile = invoice.getUser().getMobile();
//        List<User> userList;
//        if(mobile != null && (userList = userRepository.findByMobile(mobile)).size() > 0){
//            invoice.setUser(userList.get(0));
//        }
        return resultWrap(companyRepository.save(company));
    }

    @Transactional
    public Company getDefaultSellerCompany(){
        return companyRepository.findOne(1);
    }

}

package invoice.dataobject;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by song on 2016/12/19.
 */
@Entity
@Table(name = "user")
public class User implements Trader, Serializable{
    @Id
    String id;
    String mobile;
    String name;
    String pwd;
    UserType type;
    int companyId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String getTraderId() {
        return this.getMobile();
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}

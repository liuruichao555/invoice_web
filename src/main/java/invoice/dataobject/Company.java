package invoice.dataobject;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

/**
 * Created by song on 2016/12/19.
 */
@Entity
@Table(name = "company")
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Company implements Trader{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String title;
//    纳税人识别号
    String taxId;
    String address;
    String phone;
//    开户行
    String bankName;
//    账号
    String bankAccount;
//    购买方/销售方
    Enum<CompanyType> type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Enum<CompanyType> getType() {
        return type;
    }

    public void setType(Enum<CompanyType> type) {
        this.type = type;
    }

    @Override
    public String getTraderId() {
        return this.getTitle();
    }
}

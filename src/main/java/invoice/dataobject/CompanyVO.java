package invoice.dataobject;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Embeddable;

/**
 * Created by song on 2016/12/21.
 */
@Embeddable
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyVO implements Trader{
    String title;
    //    纳税人识别号
    String taxId;
    String address;
    String phone;
    //    开户行
    String bankName;
    //    账号
    String bankAccount;

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
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

    @Override
    public String getTraderId() {
        return this.getTitle();
    }
}
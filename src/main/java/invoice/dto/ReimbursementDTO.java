package invoice.dto;

import java.util.List;

/**
 * Created by song on 2016/12/22.
 */
public class ReimbursementDTO {
    String id;
    String name;
    String mobile;
    String companyTitle;//单位信息
    String department;//部门
    String invoiceNumberList;//发票列表
    String description;//说明信息

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyTitle() {
        return companyTitle;
    }

    public void setCompanyTitle(String companyTitle) {
        this.companyTitle = companyTitle;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getInvoiceNumberList() {
        return invoiceNumberList;
    }

    public void setInvoiceNumberList(String invoiceNumberList) {
        this.invoiceNumberList = invoiceNumberList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

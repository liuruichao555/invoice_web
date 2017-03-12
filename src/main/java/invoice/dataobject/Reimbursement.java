package invoice.dataobject;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by song on 2016/12/21.
 */

//报销
@Entity
@Table
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Reimbursement {
    @Id
    String id;
    String name;
    String submitter;
    String companyTitle;//单位信息
    String department;//部门
    @Column(length = 1000)
    String invoiceNumberList;//发票列表
    String description;//说明信息
    String state;

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    @Column(columnDefinition = "TIMESTAMP")
    Date date;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompanyTitle() {
        return companyTitle;
    }

    public void setCompanyTitle(String companyTitle) {
        this.companyTitle = companyTitle;
    }

    public String getInvoiceNumberList() {
        return invoiceNumberList;
    }

    public void setInvoiceNumberList(String invoiceNumberList) {
        this.invoiceNumberList = invoiceNumberList;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

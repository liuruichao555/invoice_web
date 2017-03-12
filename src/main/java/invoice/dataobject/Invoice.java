package invoice.dataobject;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by song on 15/11/18.
 */
@Entity
@Table(name = "invoice")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Invoice {
    @Id
    String number;
    String code;

    @Column(columnDefinition = "TIMESTAMP")
    Date date;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = javax.persistence.CascadeType.ALL)
    List<Item> itemList;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="title", column=@Column(name="seller_title")),
            @AttributeOverride(name="taxId", column=@Column(name="seller_taxId")),
            @AttributeOverride(name="address", column=@Column(name="seller_address")),
            @AttributeOverride(name="phone", column=@Column(name="seller_phone")),
            @AttributeOverride(name="bankName", column=@Column(name="seller_bankName")),
            @AttributeOverride(name="bankAccount", column=@Column(name="seller_bankAccount"))
    })
    CompanyVO seller;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="title", column=@Column(name="buyer_title")),
            @AttributeOverride(name="taxId", column=@Column(name="buyer_taxId")),
            @AttributeOverride(name="address", column=@Column(name="buyer_address")),
            @AttributeOverride(name="phone", column=@Column(name="buyer_phone")),
            @AttributeOverride(name="bankName", column=@Column(name="buyer_bankName")),
            @AttributeOverride(name="bankAccount", column=@Column(name="buyer_bankAccount"))
    })
    CompanyVO buyer;

    @Column(name = "type", columnDefinition = "varchar(20) default 'COMMON'")
    InvoiceType type;

    String ownerId;

//    交易历史
    @Transient
    String history;

/*
status：存储发票的状态
    0：新创建发票，还未经过流转
    1：流转中发票
    2：报销中发票
    3：报销完成发票
 */
    @Transient
    String status;

    @Transient
    String submittm;

    @Transient
    String confirmtm;


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public CompanyVO getSeller() {
        return seller;
    }

    public void setSeller(CompanyVO seller) {
        this.seller = seller;
    }

    public CompanyVO getBuyer() {
        return buyer;
    }

    public void setBuyer(CompanyVO buyer) {
        this.buyer = buyer;
    }

    public InvoiceType getType() {
        return type;
    }

    public void setType(InvoiceType type) {
        this.type = type;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubmittm() {
        return submittm;
    }

    public void setSubmittm(String submittm) {
        this.submittm = submittm;
    }

    public String getConfirmtm() {
        return confirmtm;
    }

    public void setConfirmtm(String confirmtm) {
        this.confirmtm = confirmtm;
    }
}
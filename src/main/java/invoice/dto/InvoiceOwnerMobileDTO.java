package invoice.dto;

import invoice.dataobject.CompanyVO;
import invoice.dataobject.InvoiceType;
import invoice.dataobject.Item;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by song on 2016/12/25.
 */
public class InvoiceOwnerMobileDTO {
    String number;
    String code;
    Date date;
    List<Item> itemList;
    CompanyVO seller;
    CompanyVO buyer;
    InvoiceType type;
    String ownerMobile;

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

    public String getOwnerMobile() {
        return ownerMobile;
    }

    public void setOwnerMobile(String ownerMobile) {
        this.ownerMobile = ownerMobile;
    }
}

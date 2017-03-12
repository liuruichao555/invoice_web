package invoice.dto;

import invoice.dataobject.CompanyVO;
import invoice.dataobject.InvoiceType;
import invoice.dataobject.Item;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by song on 2016/12/28.
 */
public class InvoiceTableRowDTO {
    String number;
    String history;
    String buyerId;
    String sellerId;
    Date date;
    String status;//报销状态

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

package invoice.repository.blockchain;

import com.google.gson.Gson;
import invoice.dataobject.CompanyVO;
import invoice.dataobject.Invoice;
import invoice.dataobject.UserType;
import invoice.dto.HistoryDTO;
import invoice.repository.blockchain.BlockChainRepository;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import util.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by song on 2016/12/21.
 */
@Repository
public class InvoiceBCRepository extends BlockChainRepository {
    private final Logger logger = LoggerFactory.getLogger(InvoiceBCRepository.class);

    public Invoice findOne(String invoiceNumber, String traderId){
        Object[] args = {"getInvoice", invoiceNumber, traderId};
        String invoiceJson = fireQuery(args).getMessage();
        Object[] metaArgs = {"getMetadata", invoiceNumber, traderId};
        String metaJson = fireQuery(metaArgs).getMessage();
        Invoice invoice;
        try {
            invoice = new Gson().fromJson(metaJson, Invoice.class);
        }catch (Exception e){
            logger.info("访问发票错误,Json格式错误");
            return null;
        }
//        Invoice invoiceMeta = new Gson().fromJson(metaJson, Invoice.class);
        logger.info("invoiceJson: {}", invoiceJson);
        invoice.setDate(TimeUtil.stampToDate(metaParse(invoiceJson, 5)));
        invoice.setHistory(metaParse(invoiceJson, 2).replace(",","->"));
        invoice.setOwnerId(metaParse(invoiceJson, 3));
        invoice.setStatus(metaParse(invoiceJson, 4));
        invoice.setSubmittm(metaParse(invoiceJson, 6));
        invoice.setConfirmtm(metaParse(invoiceJson, 7));

//        invoice.set
        return invoice;
    }

    public static void main(String args[]){
        new InvoiceBCRepository().findAll("JD", UserType.COM);
    }

    public Result save(Invoice invoice) {
        String metaData = new Gson().toJson(invoice).replace("\"","\\\"");
        if(invoice.getBuyer() == null)
            invoice.setBuyer(new CompanyVO());
        Object[] args = {"create", invoice.getNumber(),
                invoice.getSeller().getTraderId(),invoice.getBuyer().getTraderId(),
                BCServer.getTimeStamp(), metaData};
        return fireInvoke(args);
    }


    public List<Invoice> findAll(String traderId, UserType type) {
        Object[] args = {"myHistory", traderId, ""+type.ordinal()};
        String resp = fireQuery(args).getMessage();
        logger.info(resp);
        if(resp == null || resp.equals("null"))
            return null;

        String[] strings = new Gson().fromJson(resp, String[].class);
        List<Invoice> invoiceList = new ArrayList<Invoice>();
        for(String str : strings){
            Invoice invoice = findOne(str.split(JsonResponse.SEPARATOR)[2], traderId);
            if(invoice == null)
                continue;
            invoiceList.add(invoice);
        }
        return invoiceList;
    }
}

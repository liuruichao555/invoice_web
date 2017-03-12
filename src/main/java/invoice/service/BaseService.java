package invoice.service;

import util.Result;

/**
 * Created by song on 2016/12/21.
 */
public class BaseService {
//    Result resultWrap(Object t, String msg){
//        Result result = new Result();
//        if(t == null)
//            result.setFailed(msg);
//        else
//            result.setSuccess(msg, t);
//        return result;
//    }
    public int PAGE_SIZE = 15;
    Result resultWrap(Object t){
        if(t instanceof Result)
            return  (Result) t;
        Result result = new Result();
        if(t == null)
            result.setFailed("error");
        else
            result.setSuccess("success", t);
        return result;
    }

    Result errorResult(String msg){
        Result result = new Result();
        result.setFailed(msg);
        return result;
    }
}

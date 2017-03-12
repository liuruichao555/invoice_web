package invoice.controller;

import util.Result;

/**
 * Created by song on 2016/12/27.
 */
public class BaseController {
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
}

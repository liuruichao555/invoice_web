package util;

import java.util.Date;

/**
 * Created by song on 2016/12/23.
 */
public class BCServer {
    public static String SERVER_URL = "https://a82d517e2e7c408d9fe8a01f431f8f7d-vp0.us.blockchain.ibm.com:5004";
    public static String CCID = "e4efe0c85079de02306b612523c77f304237fbfdfda11cd741c4bf62df6c3546e8190d5673fa61949cc8652aff146304b6e64d759ba4355bdd3c73bdf67600a4";
    public static String getTimeStamp(){
        return System.currentTimeMillis() / 1000 + "";
    }
}

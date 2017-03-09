package victor.cominotti.channelmessaging;

/**
 * Created by Victor Cominotti on 29/01/2017.
 */

public class WebServiceConnect {
    public static String CODE_ACCEPT = "200";

    private String response;
    private String code;
    private String accesstoken;

    public String getResponse(){
        return response;
    }

    public String getCode(){
        return code;
    }

    public String getAccesstoken(){
        return accesstoken;
    }

    public void setResponse(String response){
        this.response = response;
    }

    public void setCode(String code){
        this.code = code;
    }

    public void setAccesstoken(String accesstoken){
        this.accesstoken = accesstoken;
    }
}

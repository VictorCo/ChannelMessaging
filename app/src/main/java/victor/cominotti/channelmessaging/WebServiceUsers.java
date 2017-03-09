package victor.cominotti.channelmessaging;

import java.util.ArrayList;

/**
 * Created by Victor on 02/03/2017.
 */

public class WebServiceUsers {

    private ArrayList<WebServiceUser> users;

    public WebServiceUsers(ArrayList<WebServiceUser> wsus){
        users = wsus;
    }

    public WebServiceUsers(){
        users = new ArrayList<WebServiceUser>();
    }

    public void addUser(WebServiceUser wsu){
        users.add(wsu);
    }

    public ArrayList<WebServiceUser> getUsers(){
        return users;
    }

    public WebServiceUser getUser(int id){
        for (WebServiceUser wsu : users){
            if (wsu.userID == id)
                return wsu;
        }

        return null;
    }
}

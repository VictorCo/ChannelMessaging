package victor.cominotti.channelmessaging;

/**
 * Created by Victor on 26/02/2017.
 */

public class WebServiceMessage {
    public int userID;
    public String message;
    public String date;
    public String imageUrl;

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof WebServiceMessage))
            return false;

        WebServiceMessage wsm = (WebServiceMessage)obj;

        return     (userID == wsm.userID)
                && (message.equals(wsm.message))
                && (date.equals(wsm.date))
                && (imageUrl.equals(wsm.imageUrl));
    }
}

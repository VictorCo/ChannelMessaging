package victor.cominotti.channelmessaging;

import java.util.ArrayList;

/**
 * Created by Victor on 26/02/2017.
 */

public class WebServiceMessages {

    public ArrayList<WebServiceMessage> messages = new ArrayList<WebServiceMessage>();

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof WebServiceMessages))
            return false;

        WebServiceMessages wsms = (WebServiceMessages) obj;

        if (messages.size() != wsms.messages.size())
            return false;

        for (int i = 0; i < messages.size(); i++) {
            if (!messages.get(i).equals(wsms.messages.get(i)))
                return false;
        }
        return true;
    }
}

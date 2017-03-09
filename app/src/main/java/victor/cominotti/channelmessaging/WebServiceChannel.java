package victor.cominotti.channelmessaging;

/**
 * Created by Victor Cominotti on 30/01/2017.
 */

public class WebServiceChannel {
    public int channelID;
    public int connectedusers;
    public String name;

    @Override
    public String toString() {
        return "channelID -> " + channelID + " , "
                + "connectedusers -> " + connectedusers + " , "
                + "name -> " + name;
    }
}

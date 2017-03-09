package victor.cominotti.channelmessaging;

import java.util.ArrayList;

/**
 * Created by Victor Cominotti on 30/01/2017.
 */

public class WebServiceChannels {
    private ArrayList<WebServiceChannel> channels = new ArrayList<WebServiceChannel>();

    public ArrayList<WebServiceChannel> getChannels(){
        return channels;
    }

    public void setChannels(ArrayList<WebServiceChannel> channels) {
        this.channels = channels;
    }

    @Override
    public String toString() {
        return channels.toString();
    }
}

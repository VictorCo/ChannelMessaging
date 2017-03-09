package victor.cominotti.channelmessaging;

/**
 * Created by Victor on 02/03/2017.
 */

public class WebServiceUser {
    public int userID;
    public String identifiant;
    public int lastactivity;
    public String imageUrl;

    @Override
    public String toString() {
        return userID + " - " +
                identifiant + " - " +
                lastactivity + " - " +
                imageUrl;
    }

}

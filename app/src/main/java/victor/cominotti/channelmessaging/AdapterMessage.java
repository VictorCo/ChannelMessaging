package victor.cominotti.channelmessaging;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.*;

/**
 * Created by Victor on 26/02/2017.
 */

public class AdapterMessage extends ArrayAdapter<WebServiceMessage> {

    private final Context context;
    private final ArrayList<WebServiceMessage> wsm;
    private static WebServiceMessages oldWsms = new WebServiceMessages();
    public AdapterMessage(Context context, ArrayList<WebServiceMessage> wsm){
        super(context,R.layout.showmessage, wsm);
        this.context = context;
        this.wsm = wsm;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.showmessage, parent, false);
        TextView txtVMessage = (TextView) rowView.findViewById(R.id.txtVMessage);
        TextView txtVDate = (TextView) rowView.findViewById(R.id.txtVDate);

        txtVMessage.setText(wsm.get(position).message);
        txtVDate.setText(formatDate(wsm.get(position).date));

        oldWsms.messages = wsm;
        return rowView;
    }

    public WebServiceMessages getOldWsms(){
        return oldWsms;
    }

    private String formatDate(String date){

        Pattern p = Pattern.compile("(\\d{2}:){2}\\d{2}");
        Matcher m = p.matcher(date);
        m.find();
        return m.group();
    }
}

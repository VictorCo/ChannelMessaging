package victor.cominotti.channelmessaging;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Victor Cominotti on 16/02/2017.
 */

public class AdapterChannel extends ArrayAdapter<WebServiceChannel> {

    private final Context context;
    private ArrayList<WebServiceChannel> wsc;
    public AdapterChannel(Context context, ArrayList<WebServiceChannel> wsc){
        super(context,R.layout.showchannel, wsc);
        this.context = context;
        this.wsc = wsc;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.showchannel, parent, false);
        TextView txtVChannelNumber = (TextView)rowView.findViewById(R.id.txtVChannelNumber);
        txtVChannelNumber.setText("Channel " + wsc.get(position).channelID);
        TextView txtVNumberUsers = (TextView)rowView.findViewById(R.id.txtVNumberUsers);
        txtVNumberUsers.setText("Nombre d'utilisateurs connectés : " + wsc.get(position).connectedusers);

        return rowView;
    }
}

package victor.cominotti.channelmessaging;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by Victor Cominotti on 29/01/2017.
 */

public class ChannelListActivity extends Activity implements AdapterView.OnItemClickListener {

    private WebServiceChannels wscs;
    private Intent iMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_list);

        String accesstoken = getSharedPreferences(Preference.PREFS_NAME,Preference.W_R).getString("accesstoken","");
        final ListView lvChannel = (ListView) findViewById(R.id.lvListChannel);
        AsyncTaskWebService atws = new AsyncTaskWebService(this);
        lvChannel.setOnItemClickListener(this);
        atws.setOnWebServiceListener(new OnWebServiceListener() {
            @Override
            public void onWebService(String response) {
                //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                Gson gson = new Gson();
                wscs = gson.fromJson(response,WebServiceChannels.class);
                lvChannel.setAdapter(new AdapterChannel(getApplicationContext(),wscs.getChannels()));
            }
        });

        atws.execute("http://www.raphaelbischof.fr/messaging/?function=getchannels",
                     "accesstoken", accesstoken);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        iMessage = new Intent(getApplicationContext(), MessageActivity.class);
        iMessage.putExtra("ID", wscs.getChannels().get(position).channelID);
        startActivity(iMessage);
        //Toast.makeText(getApplicationContext(), String.valueOf(wscs.getChannels().get(position).channelID),Toast.LENGTH_LONG).show();
    }
}

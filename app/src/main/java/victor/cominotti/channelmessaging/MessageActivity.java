package victor.cominotti.channelmessaging;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

/**
 * Created by Victor on 25/02/2017.
 */

public class MessageActivity extends Activity {

    private String accesstoken;
    private int channelId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        accesstoken = getSharedPreferences(Preference.PREFS_NAME, Preference.W_R).getString(Preference.ACCESSTOKEN,null);
        channelId = getIntent().getIntExtra("ID",-1);


        final Handler handler = new Handler();
        final Runnable r = new Runnable() {
            @Override
            public void run() {
                refreshListView();
                handler.postDelayed(this,1000);
            }
        };
        handler.postDelayed(r,1000);


    }

    private void refreshListView() {
        final ListView lvMessage = (ListView)findViewById(R.id.lvMessage);
        AsyncTaskWebService atws = new AsyncTaskWebService(getApplicationContext());
        atws.setOnWebServiceListener(new OnWebServiceListener() {
            @Override
            public void onWebService(String response) {
                Gson g = new Gson();
                WebServiceMessages wsms = g.fromJson(response, WebServiceMessages.class);
                final AdapterMessage am = new AdapterMessage(getApplicationContext(),wsms.messages);

                //si les données ne sont pas les même on update la listview
                if (!wsms.equals(am.getOldWsms())) {
                    AsyncTaskWebService atwsUser = new AsyncTaskWebService(getApplicationContext());
                    atwsUser.setOnWebServiceListener(new OnWebServiceListener() {
                        @Override
                        public void onWebService(String response) {
                            Gson g = new Gson();
                            WebServiceUsers wsus = g.fromJson(response, WebServiceUsers.class);
                            lvMessage.setAdapter(am);
                        }
                    });

                    atwsUser.execute("http://www.raphaelbischof.fr/messaging/?function=getchannelusers",
                                 "accesstoken", accesstoken,
                                 "channelid", String.valueOf(channelId));
                }

            }
        });

        atws.execute("http://www.raphaelbischof.fr/messaging/?function=getmessages",
                     "accesstoken", accesstoken,
                     "channelid", String.valueOf(channelId));
    }

}

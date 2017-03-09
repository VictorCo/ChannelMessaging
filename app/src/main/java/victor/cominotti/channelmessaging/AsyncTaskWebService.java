package victor.cominotti.channelmessaging;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Victor Cominotti on 28/01/2017.
 */

public class AsyncTaskWebService extends AsyncTask<String, Integer, String> {

    //url qui représente un webservice
    private String url;

    //string 1->nom paramètre post
    //string 2->valeur du paramètre
    private HashMap<String,String> paramsPOST;

    private ArrayList<OnWebServiceListener> listeners;
    private Context myContext;

    public AsyncTaskWebService(Context pContext){
        paramsPOST = new HashMap<String,String>();
        listeners = new ArrayList<OnWebServiceListener>();
        myContext = pContext;
    }

    public void setOnWebServiceListener(OnWebServiceListener listener) {
        this.listeners.add(listener);
    }


    //arg[0] -> url webservice
    //arg[impair] -> nom parametre
    //arg[pair] -> valeur parametre
    @Override
    protected String doInBackground(String... arg) {
        WebServiceRequest wb = new WebServiceRequest();
        url = arg[0];
        for (int i=1;i<arg.length;i+=2)
            paramsPOST.put(arg[i],arg[i+1]);

        return wb.performPostCall(url, paramsPOST);
    }

    @Override
    protected void onPostExecute(String s) {
        for (OnWebServiceListener i : listeners)
            i.onWebService(s);
    }
}

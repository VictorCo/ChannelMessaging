package victor.cominotti.channelmessaging;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import static victor.cominotti.channelmessaging.R.id.btnValider;
import static victor.cominotti.channelmessaging.R.id.txtEIdentifiant;
import static victor.cominotti.channelmessaging.R.id.txtEMdp;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText txtEIdentifiant;
    private EditText txtEMdp;
    private Button btnValider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtEIdentifiant = (EditText)findViewById(R.id.txtEIdentifiant);
        txtEMdp = (EditText)findViewById(R.id.txtEMdp);
        btnValider = (Button)findViewById(R.id.btnValider);
        btnValider.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnValider :
                AsyncTaskWebService atws = new AsyncTaskWebService(getApplicationContext());
                atws.setOnWebServiceListener(new OnWebServiceListener() {
                    @Override
                    public void onWebService(String response) {
                        Gson g = new Gson();
                        WebServiceConnect wsc = g.fromJson(response, WebServiceConnect.class);
                        if (wsc.getCode().equals(WebServiceConnect.CODE_ACCEPT)){
                            SharedPreferences settings = getSharedPreferences(Preference.PREFS_NAME,Preference.W_R);
                            SharedPreferences.Editor editor = settings.edit();
                            editor.putString("accesstoken", wsc.getAccesstoken());
                            editor.commit();
                            startActivity(new Intent(getApplicationContext(),ChannelListActivity.class));
                        }


                        else
                            Toast.makeText(getApplicationContext(),"Mauvais login ou mot de passe",Toast.LENGTH_LONG).show();

                    }
                });

                atws.execute("http://www.raphaelbischof.fr/messaging/?function=connect",
                        "username", txtEIdentifiant.getText().toString(),
                        "password", txtEMdp.getText().toString());
                break;
        }
    }
}

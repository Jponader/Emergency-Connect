package jsp.emergencyconnect;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;

    private static Context sContext;
    private static Button button;

    Context context;
    ArrayList arrayList;
    Switch toggle = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sContext=   getApplicationContext();
        button = findViewById(R.id.send);
        context=this;

        GlobalClass g = (GlobalClass) getApplication();

        Resources res = getResources();

        String[] names = res.getStringArray(R.array.contacts);
        String[] numbers = res.getStringArray(R.array.numbers);
        int[] bluetooth = res.getIntArray(R.array.bluetooth);
        int[] wifi = res.getIntArray(R.array.Wifi);

        listView=(ListView) findViewById(R.id.list_view);
        listView.setAdapter(new CustomAdapter(this,names,numbers,bluetooth, wifi));
        listView.setVisibility(g.getVisible());

        toggle=(Switch)findViewById(R.id.toggle);
        toggle.setChecked(g.getChecked());
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ListView layout = findViewById(R.id.list_view);
                GlobalClass g = (GlobalClass) getApplication();
                final EditText edit = (EditText) findViewById(R.id.message);

                if(isChecked) {
                    g.setVisible(0);
                    layout.setVisibility(g.getVisible());
                    g.setChecked(true);
                }
                else {

                    g.setData(-1);
                    g.setSelected(null);
                    g.setVisible(4);
                    layout.setVisibility(g.getVisible());
                    g.setChecked(false);

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public static Context getContext() {
        return  sContext;
    }
    public  static Button getButton(){return button;}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(context,"Settings",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void onPause(){
        super.onPause();
    }



    protected void onResume(Bundle savedInstanceState){

        toggle=(Switch)findViewById(R.id.toggle);
        toggle.setChecked(true);

        super.onResume();

        return;
    }

    protected void onStop(Bundle savedInstanceState){
        super.onStop();

        return;
    }

}

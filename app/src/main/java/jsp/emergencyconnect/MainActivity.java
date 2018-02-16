package jsp.emergencyconnect;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

        Resources res = getResources();
        String[] names = res.getStringArray(R.array.contacts);
        String[] numbers = res.getStringArray(R.array.numbers);
        int[] bluetooth = res.getIntArray(R.array.bluetooth);
        int[] wifi = res.getIntArray(R.array.Wifi);

        listView=(ListView) findViewById(R.id.list_view);
        listView.setAdapter(new CustomAdapter(this,names,numbers,bluetooth, wifi));

        toggle=(Switch)findViewById(R.id.toggle);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                LinearLayout layout = (LinearLayout) findViewById(R.id.message_layout);
                GlobalClass g = (GlobalClass) getApplication();
                final EditText edit = (EditText) findViewById(R.id.message);

                if(isChecked) {
                  //  Toast.makeText(context, "You Clicked ON ", Toast.LENGTH_LONG).show();
                    layout.setVisibility(View.VISIBLE);
                }
                else {
                   // Toast.makeText(context, "You Clicked OFF ", Toast.LENGTH_LONG).show();
                    button.setEnabled(false);

                    edit.setText("");

                    View lastSelectedView =g.getSelected();
                    if(lastSelectedView != null) lastSelectedView.setBackgroundColor(Color.TRANSPARENT);

                    g.setData(-1);
                    g.setSelected(null);
                    layout.setVisibility(View.INVISIBLE);

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

    public void send (View v){
        GlobalClass g = (GlobalClass) getApplication();
        if(g.getData() == -1)return;

        Resources res = getResources();
        String[] names = res.getStringArray(R.array.contacts);

        final EditText edit = (EditText) findViewById(R.id.message);

        int data=g.getData();

        alertView(names[data],edit.getText().toString());

        edit.setText("");
    }

    private void alertView( String number , String message) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle(number)
                .setMessage(message)

                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                    }
                })
                .show();
    }

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

}

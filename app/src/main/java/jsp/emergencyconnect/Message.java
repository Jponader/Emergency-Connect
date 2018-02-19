package jsp.emergencyconnect;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Message extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
    }

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
}

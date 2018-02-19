package jsp.emergencyconnect;

/**
 * Created by JSP on 1/31/2018.
 */

         import android.app.Application;
         import android.content.Intent;
         import android.graphics.Color;
         import android.widget.BaseAdapter;
         import android.content.Context;
         import android.view.LayoutInflater;
         import android.view.View;
         import android.view.View.OnClickListener;
         import android.view.ViewGroup;
         import android.widget.BaseAdapter;
         import android.widget.Button;
         import android.widget.EditText;
         import android.widget.ImageView;
         import android.widget.TextView;
         import android.widget.Toast;
public class CustomAdapter extends BaseAdapter {

    private View lastSelectedView = null;

    String [] result;
    Context context;
    String [] result2;
    int[] result3;
    int [] result4;
    private static LayoutInflater inflater=null;
    public CustomAdapter(MainActivity mainActivity, String[] names, String[] numbers, int[] bluetooth, int[] wifi) {
        result=names;
        result2 = numbers;
        result3 = bluetooth;
        result4 = wifi;
        context=mainActivity;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder
    {
        TextView tv1;
        TextView tv2;

        ImageView iv1;
        ImageView iv2;
    }
    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.list_layout, null);
        holder.tv1=(TextView) rowView.findViewById(R.id.item1);
        holder.tv2=(TextView) rowView.findViewById(R.id.item2);
        holder.tv1.setText(result[position]);
        holder.tv2.setText(result2[position]);

        setPic(position, rowView);

        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalClass g = (GlobalClass) MainActivity.getContext();

                clearSelection();

                if((lastSelectedView == v)&&(g.getData()!=-1)){
                    g.setData(-1);
                    return;
                }

                lastSelectedView = v;
                v.setBackgroundColor(Color.parseColor("#DCDCDC"));

                g.setData(position);
                g.setSelected(lastSelectedView);


                startMessage();
            }
        });
        return rowView;
    }

    public void clearSelection()
    {
        if(lastSelectedView != null) lastSelectedView.setBackgroundColor(Color.TRANSPARENT);
    }

    public void setPic(int position,View rowView)
    {
        Holder holder=new Holder();

        holder.iv1=(ImageView) rowView.findViewById(R.id.bluetooth);
        holder.iv2=(ImageView) rowView.findViewById(R.id.wifi);

        if(result3[position] ==1) {
             holder.iv1.setImageResource(R.drawable.bluetooth_green);
        }
        else {
            holder.iv1.setImageResource(R.drawable.bluetooth_red);
         }

        if(result4[position] ==1) {
            holder.iv2.setImageResource(R.drawable.wifi_green);
        }
        else {
            holder.iv2.setImageResource(R.drawable.wifi_red);
        }

    }

    public void startMessage()
    {
        GlobalClass g = (GlobalClass) MainActivity.getContext();
        Intent intent = new Intent(g, Message.class);
        g.startActivity(intent);
    }

}
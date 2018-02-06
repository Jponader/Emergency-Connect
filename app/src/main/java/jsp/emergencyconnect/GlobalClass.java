package jsp.emergencyconnect;

/**
 * Created by JSP on 2/3/2018.
 */

import android.app.Application;
import android.view.View;

/**
 * Created by JSP on 2/3/2018.
 */

public class GlobalClass extends Application {
    private int data = -1;

    public int getData(){
        return this.data;
    }

    public void setData(int d){
        this.data=d;
    }

    private View selected = null;

    public View getSelected(){
        return this.selected;
    }

    public void setSelected(View d){
        this.selected=d;
    }


}

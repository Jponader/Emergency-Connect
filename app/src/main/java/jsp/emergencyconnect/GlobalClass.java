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
    private int visible = 4;

    private boolean checked = false;

    public boolean getChecked(){return this.checked;}

    public void setChecked(boolean checked){this.checked = checked;}

    public int getVisible(){return this.visible;}

    public void setVisible(int visible) {
        this.visible = visible;
    }

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

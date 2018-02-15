package lionelmalloggi.sixmeetingcards.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Lionel on 14/02/2018.
 */

public abstract class CardViewHolder<T> implements Cloneable {

    public abstract void setView(View view);
    public abstract void bindView(T object);

    public Object clone(){
        try {
            return super.clone();
        }catch(CloneNotSupportedException e){
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }
}

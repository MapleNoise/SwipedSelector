package lionelmalloggi.sixmeetingcards.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by Lionel on 14/02/2018.
 */

public class CardAdapter<T> extends ArrayAdapter<T> {
    private List<T> data;
    private int resourceItem;
    private CardViewHolder typeOfViewHolder;

    public CardAdapter(Context context, int resourceItem, List<T> data, CardViewHolder typeOfViewHolder) {
        super(context, 0);
        this.data = data;
        this.resourceItem = resourceItem;
        this.typeOfViewHolder = typeOfViewHolder;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent) {

        CardViewHolder viewHolder;

        if (contentView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            contentView = inflater.inflate(resourceItem, parent, false);
            viewHolder = typeOfViewHolder;
            viewHolder.setView(contentView);
            contentView.setTag(viewHolder);
        } else {
            viewHolder = (CardViewHolder) contentView.getTag();
        }

        T object = getItem(position);
        viewHolder.bindView(object);

        return contentView;
    }
}

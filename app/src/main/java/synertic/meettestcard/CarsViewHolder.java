package synertic.meettestcard;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import lionelmalloggi.sixmeetingcards.view.CardViewHolder;

/**
 * Created by Lionel on 14/02/2018.
 */

public class CarsViewHolder extends CardViewHolder {

    public TextView model;
    public TextView brand;
    public ImageView image;
    private Context ctx;

    public CarsViewHolder(Context ctx){
        this.ctx = ctx;
    }

    @Override
    public void setView(View view) {
        this.brand = (TextView) view.findViewById(R.id.textViewBrand);
        this.model = (TextView) view.findViewById(R.id.textViewModel);
        this.image = (ImageView) view.findViewById(R.id.imageViewCard);
    }

    @Override
    public void bindView(Object object) {
        Cars item = (Cars)object;
        model.setText(item.model);
        brand.setText(item.brand);
        Glide.with(ctx).load(item.url).into(image);
    }
}

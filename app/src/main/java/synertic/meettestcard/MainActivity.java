package synertic.meettestcard;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lionelmalloggi.sixmeetingcards.view.MeetingCardSwipeListener;
import lionelmalloggi.sixmeetingcards.view.MeetingCardView;

/**
 * Created by Lionel on 14/02/2018.
 */

public class MainActivity extends AppCompatActivity {

    private MeetingCardView<Cars> meetingCardView;
    public FragmentManager _fragManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        meetingCardView = findViewById(R.id.meetingCard);
        meetingCardView.setItemData(createCars(), R.layout.item_cars_card, new CarsViewHolder(getBaseContext()));
        meetingCardView.displayCardsAtIndex(0);
        meetingCardView.displayNavigationButton(true);
        meetingCardView.setSwipedListener(new MeetingCardSwipeListener() {
            @Override
            public void OnObjectSwiped(Object obj) {
                Cars item = (Cars)obj;
                Toast.makeText(MainActivity.this, item.brand +" - "+item.model+" is Swiped", Toast.LENGTH_LONG).show();
            }

            @Override
            public void ObjectIsSwipping(Object obj, float x, float y) {

            }
        });

        TopFragment profileFragment = TopFragment.newInstance();
        _fragManager = getSupportFragmentManager();
        meetingCardView.setTopView(profileFragment, _fragManager);
    }

    private List<Cars> createCars() {
        List<Cars> cars = new ArrayList<>();
        cars.add(new Cars(1,"TT", "Audi", "https://images.caradisiac.com/photo/4/7/0/8/74708/S5-AUDI-TT-RS-06-photo93763-74708.jpg"));
        cars.add(new Cars(2, "i8", "BMW", "https://www.nationwidevehiclecontracts.co.uk/m/1/bmw-i8-800.jpg"));
        cars.add(new Cars(3, "Enzo", "Ferrari", "https://www.graypaulclassiccars.com/media/2226/img_4390.jpg"));
        cars.add(new Cars(4, "Cayenne", "Porsche", "http://www.largus.fr/images/images/nouveau-porsche-cayenne-2017-vue-avant.jpg"));
        cars.add(new Cars(5, "Megane", "Renault", "http://sf1.viepratique.fr/wp-content/uploads/sites/9/2017/09/renault_megane_r.s.-1-750x410.jpeg"));
        cars.add(new Cars(6, "DS3", "Citroën", "https://voiture.kidioui.fr/image/img-auto/citroen-ds3.jpg"));
        cars.add(new Cars(7,"Model 3", "Tesla", "http://www.automobile-propre.com/wp-content/uploads/2017/07/Model-3-Profile-Grey-New-730x411.png"));
        cars.add(new Cars(8,"GT86", "Toyota", "http://cdn1.autoexpress.co.uk/sites/autoexpressuk/files/styles/article_main_image/public/2016/12/dsc_3953.jpg"));
        cars.add(new Cars(9,"Giulia", "Alfa Romeo", "http://www.largus.fr/images/images/alfa-giulia-quadrifoglio-09.jpg"));
        return cars;
    }
}

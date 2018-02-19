# Swipe Card Selector

Librairie spécifique pour selectionner des cards (6 maximum) via des actions Swipe vers le haut.

## Getting Started

Importer sur Android Studio le répertoire `sixmeetingcards` comme module
Sur le `build.gradle` de l'app ne pas oublier l'import si cela n'a pas été fait automatiquement :
```
compile project(':sixmeetingcards')
```

## Implémentation

### Initialiser la vue des Cards

Sur l'XML de l'Activity ou Fragment, on ajoute les balises (`layout/activity_main.xml`) :
```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <lionelmalloggi.sixmeetingcards.view.MeetingCardView
        android:id="@+id/meetingCard"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

</RelativeLayout>
```

Dans l'Activity ou Fragment, on ajoute la variable (`MainActivity.java`) :
```
private MeetingCardView<Cars> meetingCardView;
```

Dans le `OnCreate` on initialise la View `MeetingCardView` avec l'id du layout de l'XML :
```
meetingCardView = findViewById(R.id.meetingCard);
```

On remarque que la Classe `MeetingCardView` contient un paramètre générique qui sera l'objet des items de nos cards. Ici des `Cars`.

Notre model ressemblera par exemple à :
```
public class Cars {
    public int id;
    public String brand;
    public String model;
    public String url;

    public Cars(int id, String model, String brand, String url) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.url = url;
    }
}

```

### Ajouter les données dans les Cards

La méthode `.setItemData()` ajoute les données d'une liste dans des `ViewHolder` .

Elle prend en paramètre :
* Une liste d'objet
* Le layout des cards qui seront swiped
* Une initalisation de votre `ViewHolder` custom

```
meetingCardView.setItemData(createCars(), R.layout.item_cars_card, new CarsViewHolder(getBaseContext()));
```

```
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
```

Le `ViewHolder` custom devra __OBLIGATOIREMENT__ être `extends` par `CardViewHolder` (`CarsViewHolder.java`):
```
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
```
On remarque les 2 methodes surchargées avec `setView()` qui permet d'initialiser chaque élément graphique de la card et `bindView()` qui va remplir les données de la card.

__Parsing à faire obligatoirement__ : `Cars item = (Cars)object;`

*J'utilise la librairie Glide pour télécharger les images via une url*


Pour terminer, on crée le layout des cards qui seront swipeables (`layout/item_cars_card.xml`) :
```
<?xml version="1.0" encoding="utf-8"?>
<android.support.v7.widget.CardView
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:card_view="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:cardUseCompatPadding="true"
    card_view:cardCornerRadius="8dp"
    card_view:cardBackgroundColor="@android:color/white">

    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <ImageView
            android:id="@+id/imageViewCard"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:scaleType="centerCrop"/>

        <LinearLayout
            android:orientation="vertical"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:padding="8dp"
            android:background="@drawable/background_black_gradation"
            android:layout_alignParentBottom="true">

            <TextView
            android:id="@+id/textViewBrand"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:textStyle="bold"
            android:textColor="@android:color/white"/>

            <TextView
            android:id="@+id/textViewModel"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:textColor="@android:color/white"/>

        </LinearLayout>

    </RelativeLayout>

</android.support.v7.widget.CardView>
```

### Listener sur le swipe d'une Card
```
meetingCardView.setSwipedListener(new MeetingCardSwipeListener() {
    @Override
    public void OnObjectSwiped(Object obj) {
        Cars item = (Cars)obj;
        Toast.makeText(MainActivity.this, item.brand +" - "+item.model+" is Swiped", Toast.LENGTH_LONG).show();
    }
});
```

### Ajouter un Top(Header)View au dessus des Cards
Dans l'Activity parent il faudra créer une variable (`MainActivity.java`):
```
public FragmentManager _fragManager;
```

et l'initialiser dans le `OnCreate` de l'Activity (`MainActivity.java`) :
```
 _fragManager = getSupportFragmentManager();
```
Il suffit ensuite d'ajouter le fragment à l'aide du FragmentManager :
```
 meetingCardView.setTopView(profileFragment, _fragManager);
```


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
        this.brand = brand;
        this.model = model;
        this.url = url;
    }
}

```

### Ajouter les données dans les Cards

La méthode `.setItemData(List<T> data, int resource, CardViewHolder typeOfViewHolder)` ajoute les données d'une liste dans des `ViewHolder` .
Ici nous avons une liste de `Cars`  (`MainActivity.java`) :
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

Il faudra créer les layout des cards qui seront swipeables (`layout/item_cars_card.xml`) :
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





### Installing

A step by step series of examples that tell you have to get a development env running

Say what the step will be

```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags).

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone who's code was used
* Inspiration
* etc


package lionelmalloggi.sixmeetingcards.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import lionelmalloggi.sixmeetingcards.R;
import lionelmalloggi.sixmeetingcards.cardstackview.CardStackView;
import lionelmalloggi.sixmeetingcards.cardstackview.SwipeDirection;

/**
 * Created by Lionel on 14/02/2018.
 */

public class MeetingCardView<T> extends FrameLayout {

    public CardStackView cardTopLeft;
    public CardStackView cardTopCenter;
    public CardStackView cardTopRight;
    public CardStackView cardBottomLeft;
    public CardStackView cardBottomCenter;
    public CardStackView cardBottomRight;


    private CardAdapter adapterCardTopLeft;
    private CardAdapter adapterCardTopCenter;
    private CardAdapter adapterCardTopRight;
    private CardAdapter adapterCardBottomLeft;
    private CardAdapter adapterCardBottomCenter;
    private CardAdapter adapterCardBottomRight;

    private RelativeLayout rlNavigationButtons;
    public FrameLayout flContainer;

    private Button btPrevious;
    private Button btNext;

    private List<T> data;
    private List<List<T>> dataSplited;

    private int currentIndex = 0;

    private MeetingCardSwipeListener<T> listener;

    public MeetingCardView(Context context) {
        this(context, null);
    }

    public MeetingCardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MeetingCardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        inflate(getContext(), R.layout.six_cards_frame, this);
        cardTopLeft = findViewById(R.id.card1);
        cardTopCenter = findViewById(R.id.card2);
        cardTopRight = findViewById(R.id.card3);
        cardBottomLeft = findViewById(R.id.card4);
        cardBottomCenter = findViewById(R.id.card5);
        cardBottomRight = findViewById(R.id.card6);
        flContainer = findViewById(R.id.flContainer);
        rlNavigationButtons = findViewById(R.id.rlNavigationButtons);
        btPrevious = findViewById(R.id.btPrevious);
        btNext = findViewById(R.id.btNext);
        positionningCard();
    }

    public void displayNavigationButton(boolean display){
        if(display){
            rlNavigationButtons.setVisibility(View.VISIBLE);
            initListenerButton();
        }else{
            rlNavigationButtons.setVisibility(View.GONE);
        }
    }

    private void positionningCard(){
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int height = display.getHeight();
        int width = display.getWidth();

        cardTopLeft.setPadding(0, height/3, 2*width/3, height/3);
        cardTopCenter.setPadding(width/3, height/3, width/3, height/3);
        cardTopRight.setPadding(2*width/3, height/3, 0, height/3);
        cardBottomLeft.setPadding(0, 2*height/3, 2*width/3, 0);
        cardBottomCenter.setPadding(width/3, 2*height/3, width/3, 0);
        cardBottomRight.setPadding(2*width/3, 2*height/3, 0, 0);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)flContainer.getLayoutParams();
        params.setMargins(0, 0, 0, 2*height/3);
        flContainer.setLayoutParams(params);
    }

    public void setTopView(Fragment fragment, FragmentManager manager){
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.flContainer, fragment);
        transaction.addToBackStack(fragment.toString());
        transaction.commit();
        manager.executePendingTransactions();
    }

    private void initAdapter(final int resource, final CardViewHolder typeOfViewHolder){

        List<CardViewHolder> stackViewHolder = createStackViewHolder(typeOfViewHolder);

        adapterCardTopLeft = new CardAdapter<T>(getContext(), resource, data, stackViewHolder.get(0));
        adapterCardTopCenter = new CardAdapter<T>(getContext(), resource, data, stackViewHolder.get(1));
        adapterCardTopRight = new CardAdapter<T>(getContext(), resource, data, stackViewHolder.get(2));
        adapterCardBottomLeft = new CardAdapter<T>(getContext(), resource, data, stackViewHolder.get(3));
        adapterCardBottomCenter = new CardAdapter<T>(getContext(), resource, data, stackViewHolder.get(4));
        adapterCardBottomRight = new CardAdapter<T>(getContext(), resource, data, stackViewHolder.get(5));
    }

    private List<CardViewHolder> createStackViewHolder(CardViewHolder typeOfViewHolder) {
        List<CardViewHolder> stackViewHolder = new ArrayList<>();
        for(int cpt = 0; cpt < 6; cpt++){
            stackViewHolder.add((CardViewHolder)typeOfViewHolder.clone());
        }

        return stackViewHolder;
    }

    public void setItemData(List<T> data, int resource, CardViewHolder typeOfViewHolder) {
        this.data = data;
        dataSplited = splitDatas();
        initAdapter(resource, typeOfViewHolder);
    }

    private List<List<T>> splitDatas(){

        List<List<T>> list = new ArrayList<>();
        List<T> sixStackList = null;
        int cpt = 0;
        for(T data : this.data) {

            if(cpt % 6 == 0) {
                sixStackList = new ArrayList<>();
                list.add(sixStackList);
            }

            sixStackList.add(data);
            cpt++;
        }

        return list;
    }

    public void displayCardsAtIndex(int index){

        currentIndex = index;
        List<T> list = splitDatas().get(index);

        adapterCardTopLeft.clear();
        adapterCardTopCenter.clear();
        adapterCardTopRight.clear();
        adapterCardBottomLeft.clear();
        adapterCardBottomCenter.clear();
        adapterCardBottomRight.clear();

        if(list.size() > 0) {
            adapterCardTopLeft.add(list.get(0));
            cardTopLeft.setAdapter(adapterCardTopLeft);
        }
        if(list.size() > 1) {
            adapterCardTopCenter.add(list.get(1));
            cardTopCenter.setAdapter(adapterCardTopCenter);
        }
        if(list.size() > 2) {
            adapterCardTopRight.add(list.get(2));
            cardTopRight.setAdapter(adapterCardTopRight);
        }
        if(list.size() > 3) {

            adapterCardBottomLeft.add(list.get(3));
            cardBottomLeft.setAdapter(adapterCardBottomLeft);
        }
        if(list.size() > 4) {
            adapterCardBottomCenter.add(list.get(4));
            cardBottomCenter.setAdapter(adapterCardBottomCenter);
        }

        if(list.size() > 5) {
            adapterCardBottomRight.add(list.get(5));
            cardBottomRight.setAdapter(adapterCardBottomRight);
        }
    }

    public void setSwipedListener(MeetingCardSwipeListener listener){
        this.listener = listener;
        initListenerCards();
    }

    private void initListenerCards(){
        cardTopLeft.setCardEventListener(new CardStackView.CardEventListener() {
            @Override
            public void onCardDragging(float percentX, float percentY) {
                System.out.println("percentX: "+percentX);
                System.out.println("percentY: "+percentY);
            }

            @Override
            public void onCardSwiped(SwipeDirection direction) {
                Log.d("CardStackView", "onCardSwiped: " + direction.toString());
                listener.OnObjectSwiped((T) adapterCardTopLeft.getItem(0));
            }

            @Override
            public void onCardReversed() {
                Log.d("CardStackView", "onCardReversed");
            }

            @Override
            public void onCardMovedToOrigin() {
                Log.d("CardStackView", "onCardMovedToOrigin");
            }

            @Override
            public void onCardClicked(int index) {
                Log.d("CardStackView", "onCardClicked: " + index);
            }
        });

        cardTopCenter.setCardEventListener(new CardStackView.CardEventListener() {
            @Override
            public void onCardDragging(float percentX, float percentY) {
                System.out.println("percentX: "+percentX);
                System.out.println("percentY: "+percentY);
            }

            @Override
            public void onCardSwiped(SwipeDirection direction) {
                Log.d("CardStackView", "onCardSwiped: " + direction.toString());
                listener.OnObjectSwiped((T) adapterCardTopCenter.getItem(0));
            }

            @Override
            public void onCardReversed() {
                Log.d("CardStackView", "onCardReversed");
            }

            @Override
            public void onCardMovedToOrigin() {
                Log.d("CardStackView", "onCardMovedToOrigin");
            }

            @Override
            public void onCardClicked(int index) {
                Log.d("CardStackView", "onCardClicked: " + index);
            }
        });

        cardTopRight.setCardEventListener(new CardStackView.CardEventListener() {
            @Override
            public void onCardDragging(float percentX, float percentY) {
                System.out.println("percentX: "+percentX);
                System.out.println("percentY: "+percentY);
            }

            @Override
            public void onCardSwiped(SwipeDirection direction) {
                Log.d("CardStackView", "onCardSwiped: " + direction.toString());
                listener.OnObjectSwiped((T) adapterCardTopRight.getItem(0));
            }

            @Override
            public void onCardReversed() {
                Log.d("CardStackView", "onCardReversed");
            }

            @Override
            public void onCardMovedToOrigin() {
                Log.d("CardStackView", "onCardMovedToOrigin");
            }

            @Override
            public void onCardClicked(int index) {
                Log.d("CardStackView", "onCardClicked: " + index);
            }
        });

        cardBottomLeft.setCardEventListener(new CardStackView.CardEventListener() {
            @Override
            public void onCardDragging(float percentX, float percentY) {
                System.out.println("percentX: "+percentX);
                System.out.println("percentY: "+percentY);
            }

            @Override
            public void onCardSwiped(SwipeDirection direction) {
                Log.d("CardStackView", "onCardSwiped: " + direction.toString());
                listener.OnObjectSwiped((T) adapterCardBottomLeft.getItem(0));
            }

            @Override
            public void onCardReversed() {
                Log.d("CardStackView", "onCardReversed");
            }

            @Override
            public void onCardMovedToOrigin() {
                Log.d("CardStackView", "onCardMovedToOrigin");
            }

            @Override
            public void onCardClicked(int index) {
                Log.d("CardStackView", "onCardClicked: " + index);
            }
        });

        cardBottomCenter.setCardEventListener(new CardStackView.CardEventListener() {
            @Override
            public void onCardDragging(float percentX, float percentY) {
                System.out.println("percentX: "+percentX);
                System.out.println("percentY: "+percentY);
            }

            @Override
            public void onCardSwiped(SwipeDirection direction) {
                Log.d("CardStackView", "onCardSwiped: " + direction.toString());
                listener.OnObjectSwiped((T) adapterCardBottomCenter.getItem(0));
            }

            @Override
            public void onCardReversed() {
                Log.d("CardStackView", "onCardReversed");
            }

            @Override
            public void onCardMovedToOrigin() {
                Log.d("CardStackView", "onCardMovedToOrigin");
            }

            @Override
            public void onCardClicked(int index) {
                Log.d("CardStackView", "onCardClicked: " + index);
            }
        });

        cardBottomRight.setCardEventListener(new CardStackView.CardEventListener() {
            @Override
            public void onCardDragging(float percentX, float percentY) {
                System.out.println("percentX: "+percentX);
                System.out.println("percentY: "+percentY);
            }

            @Override
            public void onCardSwiped(SwipeDirection direction) {
                Log.d("CardStackView", "onCardSwiped: " + direction.toString());
                listener.OnObjectSwiped((T)adapterCardBottomRight.getItem(0));
            }

            @Override
            public void onCardReversed() {
                Log.d("CardStackView", "onCardReversed");
            }

            @Override
            public void onCardMovedToOrigin() {
                Log.d("CardStackView", "onCardMovedToOrigin");
            }

            @Override
            public void onCardClicked(int index) {
                Log.d("CardStackView", "onCardClicked: " + index);
            }
        });
    }

    private void initListenerButton(){
        btNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = currentIndex+1;
                displayCardsAtIndex(index);
            }
        });

        btPrevious.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = currentIndex-1;
                displayCardsAtIndex(index);
            }
        });
    }
}

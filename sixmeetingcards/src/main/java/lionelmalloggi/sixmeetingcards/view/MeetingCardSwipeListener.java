package lionelmalloggi.sixmeetingcards.view;

/**
 * Created by Lionel on 14/02/2018.
 */

public interface MeetingCardSwipeListener<T> {
    void OnObjectSwiped(T obj);
    void ObjectIsSwipping(T obj, float x, float y);
}

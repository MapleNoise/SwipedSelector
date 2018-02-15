package synertic.meettestcard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by lionelmalloggi on 15/02/2018.
 */

public class TopFragment extends Fragment {

    public View _rootView;

    public static TopFragment newInstance() {

        TopFragment fragment = new TopFragment();
        return fragment;
    }

    public TopFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _rootView = inflater.inflate(R.layout.fragment_top, container, false);

        setView();

        return _rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    private void setView() {

    }
}
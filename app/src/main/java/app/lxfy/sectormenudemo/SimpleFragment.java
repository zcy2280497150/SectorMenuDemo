package app.lxfy.sectormenudemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @author ZCY
 * @date 2019-09-23
 */
public class SimpleFragment extends Fragment {

    public static final String BUNDLE_KEY = "BUNDLE_KEY";

    public static SimpleFragment newInstance(String title){
        SimpleFragment simpleFragment = new SimpleFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY,title);
        simpleFragment.setArguments(bundle);
        return simpleFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_simple, container, false);
        String title = null;
        Bundle bundle = getArguments();
        if (null != bundle){
            title = bundle.getString(BUNDLE_KEY);
        }
        TextView titleTv = rootView.findViewById(R.id.title_tv);
        TextView contentTv = rootView.findViewById(R.id.content_tv);
        titleTv.setText(title);
        contentTv.setText(title);
        return rootView;
    }

}

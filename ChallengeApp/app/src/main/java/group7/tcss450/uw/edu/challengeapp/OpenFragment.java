package group7.tcss450.uw.edu.challengeapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class OpenFragment extends Fragment implements View.OnClickListener {

    private OnFragmentInteractionListener mListener;

    public OpenFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_open, container, false);
        Log.d("ACTIVITY", "In OpenFragment");
        Button b = (Button) v.findViewById(R.id.login);
        b.setOnClickListener(this);
        b = (Button) v.findViewById(R.id.register);
        b.setOnClickListener(this);

        return v;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            switch (v.getId()) {
                case R.id.login:
                    mListener.onFragmentInteraction(R.string.login);
                    break;
                case R.id.register:
                    mListener.onFragmentInteraction(R.string.register);
                    break;
            }
        }
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(int fragName);
    }
}

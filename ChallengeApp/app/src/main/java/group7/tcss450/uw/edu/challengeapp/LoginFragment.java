package group7.tcss450.uw.edu.challengeapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;


public class LoginFragment extends Fragment implements View.OnClickListener {

        private OnLoginInteractionListener mListener;

        public LoginFragment() {

            // Required empty public constructor
        }

        @Override
        public void onStart() {
            super.onStart();

            Log.d("ACTIVITY", "Made it into LoginFragment!");
            /*if (getArguments() != null) {
                String name = getString(getArguments().getInt("key"));
                updateColorContent(name);
            }*/
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View v = inflater.inflate(R.layout.fragment_login, container, false);
            Button b = (Button) v.findViewById(R.id.confirm_register);
            b.setOnClickListener(this);
            Log.d("ACTIVITY", "Set confirm button onClick");
            return v;
        }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLoginInteractionListener) {
            mListener = (OnLoginInteractionListener) context;
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

    public String getUsername() {
        EditText editText = (EditText) getActivity().findViewById(R.id.username);
        String returnString = editText.getText().toString();
        Log.d("ACTIVITY", "Username is " + returnString);
        return returnString;
    }


    public String getPassword() {
        EditText editText = (EditText) getActivity().findViewById(R.id.password);
        String returnString = editText.getText().toString();
        Log.d("ACTIVITY", "Password is " + returnString);
        return returnString;
    }


    @Override
    public void onClick(View v) {
        EditText userNameText = (EditText) getActivity().findViewById(R.id.username);
        String username = userNameText.getText().toString();
        EditText passWordText = (EditText) getActivity().findViewById(R.id.password);
        String password = passWordText.getText().toString();

        if (!username.equals("") && !password.equals("")) {
            if (mListener != null) {
                mListener.onLoginInteraction(username, password);
            }
        } else {
            if (username.equals("")) {
                userNameText.setError(getString(R.string.username_empty));
            } else {
                passWordText.setError(getString(R.string.password_empty));
            }

            if (FirebaseAuth.getInstance().fetchProvidersForEmail(username).equals("")) {
                userNameText.setError(getString(R.string.non_existing_email));
            }
        }
    }


    public interface OnLoginInteractionListener {
        void onLoginInteraction(String username, String password);
    }
}

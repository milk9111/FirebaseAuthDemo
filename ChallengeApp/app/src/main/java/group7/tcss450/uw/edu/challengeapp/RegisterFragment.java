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
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;


public class RegisterFragment extends Fragment implements View.OnClickListener {

    private static final int USERNAME_EMPTY = 1;
    private static final int PASSWORD_EMPTY = 2;
    private static final int CONFIRM_PASSWORD_EMPTY = 3;
    private static final int PASSWORDS_DIFFERENT = 4;
    private static final int EXISTING_EMAIL = 5;

    private OnRegisterInteractionListener mListener;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register, container, false);
        Log.d("ACTIVITY", "Made it into RegisterFragment!");
        Button b = (Button) v.findViewById(R.id.confirm_register);
        b.setOnClickListener(this);
        return v;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRegisterInteractionListener) {
            mListener = (OnRegisterInteractionListener) context;
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
        EditText userNameText = (EditText) getActivity().findViewById(R.id.username_register);
        String username = userNameText.getText().toString();
        EditText passwordText = (EditText) getActivity().findViewById(R.id.password_register);
        String password = passwordText.getText().toString();
        EditText confirmPasswordText = (EditText) getActivity().findViewById(R.id.password_confirm_register);
        String confirmPassword = confirmPasswordText.getText().toString();

        int errorCode = findError(username, password, confirmPassword);

        if (errorCode == 0) {
            if (mListener != null) {
                mListener.onRegisterInteraction(username, password);
            }
        } else {
            switch (errorCode) {
                case USERNAME_EMPTY:
                    userNameText.setError(getString(R.string.username_empty));
                    break;
                case PASSWORD_EMPTY:
                    passwordText.setError(getString(R.string.password_empty));
                    break;
                case CONFIRM_PASSWORD_EMPTY:
                    confirmPasswordText.setError(getString(R.string.confirm_password_empty));
                    break;
                case PASSWORDS_DIFFERENT:
                    confirmPasswordText.setError(getString(R.string.passwords_different));
                    break;
                case EXISTING_EMAIL:
                    userNameText.setError(getString(R.string.existing_email));
                    break;
            }
        }
    }


    private int findError (String username, String password, String confirmPassword) {
        int errorCode = 0;

        if (username.equals("")) {
            errorCode = USERNAME_EMPTY;
        } else if (password.equals("")) {
            errorCode = PASSWORD_EMPTY;
        } else if (confirmPassword.equals("")) {
            errorCode = CONFIRM_PASSWORD_EMPTY;
        } else if (!confirmPassword.equals(password)) {
            errorCode = PASSWORDS_DIFFERENT;
        } /*else if (!(FirebaseAuth.getInstance().fetchProvidersForEmail(username).equals(""))) {
            Log.w("FIREBASE", FirebaseAuth.getInstance().fetchProvidersForEmail(username).getResult().toString());
            errorCode = EXISTING_EMAIL;
        }*/

        return errorCode;
    }


    public interface OnRegisterInteractionListener {
        // TODO: Update argument type and name
        void onRegisterInteraction(String username, String password);
    }
}

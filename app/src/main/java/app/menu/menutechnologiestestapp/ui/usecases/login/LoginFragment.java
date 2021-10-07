package app.menu.menutechnologiestestapp.ui.usecases.login;

import static app.menu.menutechnologiestestapp.utils.ErrorUtils.getStringResourceByName;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TypefaceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import javax.inject.Inject;

import app.menu.menutechnologiestestapp.R;
import app.menu.menutechnologiestestapp.data.ApiResponseObject;
import app.menu.menutechnologiestestapp.databinding.FragmentLoginBinding;
import app.menu.menutechnologiestestapp.utils.CustomTypefaceSpan;
import app.menu.menutechnologiestestapp.utils.ViewUtils;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginFragment extends Fragment {

    // attributes
    private ProgressDialog progressDialog;
    private NavController navController;

    @Inject
    Context context;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentLoginBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        LoginViewModel loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.setLoginViewModel(loginViewModel);
        binding.setLifecycleOwner(this);
        TypefaceSpan typefaceSpan = new CustomTypefaceSpan(Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf"));
        binding.emailET.setHint(getSpannableString(typefaceSpan, binding.emailET.getHint()));
        binding.passwordET.setHint(getSpannableString(typefaceSpan, binding.passwordET.getHint()));

        navController = NavHostFragment.findNavController(this);

        loginViewModel.getApiResponseObjectLiveData().observe(getViewLifecycleOwner(), this::consumeResponse);
        loginViewModel.getErrorMessageLiveData().observe(getViewLifecycleOwner(), this::consumeErrorMessageFromServer);
        return binding.getRoot();
    }

    private SpannableString getSpannableString(TypefaceSpan typefaceSpan, CharSequence hint) {
        SpannableString spannableString = new SpannableString(hint);
        spannableString.setSpan(typefaceSpan, 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    private void consumeResponse(ApiResponseObject apiResponseObject) {
        switch (apiResponseObject.getStatus()) {
            case LOADING:
                progressDialog = new ProgressDialog(context);
                ViewUtils.showProgressDialog(progressDialog);
                break;
            case SUCCESS:
                ViewUtils.hideProgressDialog(progressDialog);
                Toast.makeText(context, context.getString(R.string.login_successful), Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.action_loginFragment_to_venuesListFragment);
                break;
            case ERROR:
                ViewUtils.hideProgressDialog(progressDialog);
                Toast.makeText(context, getStringResourceByName(context, apiResponseObject.getError()), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void consumeErrorMessageFromServer(String errorMessage) {
        ViewUtils.hideProgressDialog(progressDialog);
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
package app.menu.menutechnologiestestapp.ui.usecases.login;

import static app.menu.menutechnologiestestapp.data.ErrorObject.getErrorMessage;
import static app.menu.menutechnologiestestapp.utils.ErrorConstants.EMAIL_INVALID;
import static app.menu.menutechnologiestestapp.utils.ErrorConstants.INTERNET_CONNECTION;
import static app.menu.menutechnologiestestapp.utils.ErrorConstants.MISSING_FIELDS;
import static app.menu.menutechnologiestestapp.utils.ErrorConstants.PASSWORD_NOT_STRONG;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import app.menu.menutechnologiestestapp.data.ApiResponseObject;
import app.menu.menutechnologiestestapp.data.dto.AccessTokenDto;
import app.menu.menutechnologiestestapp.data.dto.LoginResponseDto;
import app.menu.menutechnologiestestapp.domain.mapper.AccessTokenMapper;
import app.menu.menutechnologiestestapp.domain.model.AccessToken;
import app.menu.menutechnologiestestapp.domain.repository.Repository;
import app.menu.menutechnologiestestapp.utils.AuthUtils;
import app.menu.menutechnologiestestapp.utils.SingleLiveEvent;
import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class LoginViewModel extends ViewModel {

    private final Repository repository;
    private final AuthUtils authUtils;

    // observable to observe changes to email and password values via 2 way data binding
    public MutableLiveData<String> emailLiveData = new MutableLiveData<>("");
    public MutableLiveData<String> passwordLiveData = new MutableLiveData<>("");

    // observable to follow possible responses from user interaction (from form validation)
    private final MutableLiveData<ApiResponseObject> apiResponseObjectLiveData = new SingleLiveEvent<>();

    // observable to respond to error messages from the server and show the message to the user
    private final MutableLiveData<String> errorMessageLiveData = new SingleLiveEvent<>();

    @Inject
    public LoginViewModel(Repository repository, AuthUtils authUtils) {
        this.repository = repository;
        this.authUtils = authUtils;
    }

    public LiveData<ApiResponseObject> getApiResponseObjectLiveData() {
        return apiResponseObjectLiveData;
    }

    public LiveData<String> getErrorMessageLiveData() {
        return errorMessageLiveData;
    }

    public void onLoginClick() {
        if (!inputValid()) {
            apiResponseObjectLiveData.setValue(ApiResponseObject.error(MISSING_FIELDS));
            return;
        }
        if (!emailValid()) {
            apiResponseObjectLiveData.setValue(ApiResponseObject.error(EMAIL_INVALID));
            return;
        }
        if (!passwordStrong()) {
            apiResponseObjectLiveData.setValue(ApiResponseObject.error(PASSWORD_NOT_STRONG));
            return;
        }
        Map<String, Object> loginParams = new HashMap<>();
        loginParams.put("email", emailLiveData.getValue());
        loginParams.put("password", passwordLiveData.getValue());
        apiResponseObjectLiveData.setValue(ApiResponseObject.loading());
        repository.login(loginParams).enqueue(new Callback<LoginResponseDto>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponseDto> call, @NonNull Response<LoginResponseDto> response) {
                if (response.isSuccessful() && response.body() != null) {
                    setAccessTokenValuesLocally(response.body().getCustomerData().getAccessToken());
                    apiResponseObjectLiveData.setValue(ApiResponseObject.success(null));
                } else {
                    errorMessageLiveData.setValue(getErrorMessage(response));
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponseDto> call, @NonNull Throwable t) {
                apiResponseObjectLiveData.setValue(ApiResponseObject.error(INTERNET_CONNECTION));
                Log.e(LoginViewModel.class.getSimpleName(), t.getLocalizedMessage());
            }
        });
    }

    private void setAccessTokenValuesLocally(AccessTokenDto accessTokenDto) {
        AccessToken accessToken = new AccessTokenMapper().convertDtoToModel(accessTokenDto);
        authUtils.setAccessToken(accessToken.getToken());
        authUtils.setAccessTokenValidTo(accessToken.getAccessTokenValidTo());
        authUtils.setRefreshTokenValidTo(accessToken.getRefreshTokenValidTo());
    }

    private boolean inputValid() {
        return !TextUtils.isEmpty(emailLiveData.getValue()) && !TextUtils.isEmpty(passwordLiveData.getValue());
    }

    private boolean emailValid() {
        return emailLiveData.getValue() != null && Patterns.EMAIL_ADDRESS.matcher(emailLiveData.getValue()).matches();
    }

    private boolean passwordStrong() {
        return passwordLiveData.getValue() != null && passwordLiveData.getValue().length() > 5;
    }
}

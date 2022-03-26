package me.richardeldridge.shared.rest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public abstract class DefaultCallback<T> implements Callback<T> {

    @SuppressWarnings({"ConstantConditions"})
    @Override public void onResponse(@NonNull Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            onSuccess(response.body(), response.code());
        } else {
            onError(response.errorBody(), response.code());
        }
    }

    @Override public void onFailure(@NonNull Call<T> call, @NonNull Throwable throwable) {
        onError(null, -1);
    }

    public abstract void onSuccess(@NonNull final T response, int code);

    public abstract void onError(@Nullable ResponseBody body, int code);
}
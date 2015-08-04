package com.pyntail.somabar.retrofit;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.pyntail.somabar.helpers.Applog;

public abstract class CallbackRetrofit<T> implements Callback<T> {

	public static final String TAG = CallbackRetrofit.class.getSimpleName();

	/* Success Callback */
	public abstract void on200(T value, Response response);

	/* No Content Callback */
	public abstract void on204(T value, Response response);

	/* Unauthorized Callback */
	public abstract void on401(RetrofitError error);

	/* Not Found Callback */
	public abstract void on404(RetrofitError error);

	/* Request Timeout Callback */
	public abstract void on408(RetrofitError error);

	/* Conlflict Callback */
	public abstract void on409(RetrofitError error);

	/* Internal Server Error Callback */
	public abstract void on500(RetrofitError error);

	/* Not Implemented Error Callback */
	public abstract void on501(RetrofitError error);

	/* Bad Gateway Error Callback */
	public abstract void on502(RetrofitError error);

	/* Other Error Callback */
	public abstract void onFailure(RetrofitError error);

	@Override
	public void success(T object, Response response) {
		Applog.Debug(TAG, "onSuccess");

		switch (response.getStatus()) {
		case 204:
			on204(object, response);
			break;

		case 200:
			on200(object, response);
			break;
		default:
			break;
		}

	}

	@Override
	public void failure(RetrofitError error) {
		Applog.Debug(TAG, "onFailure");
		if (error.getResponse() != null) {
			final Response response = error.getResponse();
			switch (response.getStatus()) {

			case 401:
				on401(error);
				break;
			case 404:
				on404(error);
				break;

			case 408:
				on408(error);
				break;

			case 409:
				on409(error);
				break;

			case 500:
				on500(error);
				break;

			case 501:
				on501(error);
				break;

			case 502:
				on502(error);
				break;

			default:
				onFailure(error);

			}

		}
		else
			onFailure(error);
	}

}

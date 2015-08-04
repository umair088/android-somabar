package com.pyntail.somabar.retrofit;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;

import com.pyntail.somabar.constants.APIConstants;
import com.pyntail.somabar.helpers.Applog;
import com.squareup.okhttp.OkHttpClient;

public class WebServiceFactory {

	private static WebService instance;

	public static WebService getInstance() {
		if (instance == null) {
			OkHttpClient name = new OkHttpClient();
			name.setReadTimeout(25, TimeUnit.SECONDS);

			RestAdapter restAdapter = new RestAdapter.Builder()
					.setClient(OKHttpClientCreator.createClient())
					.setEndpoint(APIConstants.SERVER_URL)
					.setLogLevel(LogLevel.FULL)
					.setConverter(
							new retrofit.converter.GsonConverter(GsonFactory
									.getConfiguredGson()))
					.setLog(new RestAdapter.Log() {
						@Override
						public void log(String msg) {
							Applog.Debug("Response=  ", msg);
						}
					}).setLogLevel(LogLevel.FULL).build();

			
			
			instance = restAdapter.create(WebService.class);
		}

		return instance;

	}

}

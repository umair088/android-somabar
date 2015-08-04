package com.pyntail.somabar.retrofit;

import java.security.GeneralSecurityException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;

import retrofit.client.OkClient;

import com.squareup.okhttp.OkHttpClient;

public class OKHttpClientCreator {

	public static OkClient createClient() {
		OkHttpClient client = new OkHttpClient();

		client.setReadTimeout(20, TimeUnit.SECONDS);
		client.setConnectTimeout(20, TimeUnit.SECONDS);

		SSLContext sslContext;
		try {
			sslContext = SSLContext.getInstance("TLS"); //$NON-NLS-1$
			sslContext.init(null, null, null);
		} catch (GeneralSecurityException e) {
			throw new AssertionError(); // The system has no TLS. Just give up.
		}
		client.setSslSocketFactory(sslContext.getSocketFactory());
		return new OkClient(client);
	}

}

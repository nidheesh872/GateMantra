package com.gatemantra.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class ServiceHandler {

	static InputStream is = null;
	static String response = null;
	public final static int GET = 1;
	public final static int POST = 2;

	public ServiceHandler() {

	}

	/*
	 * Making service call
	 * @url - url to make request
	 * @method - http request method
	 * */
	public String makeServiceCall(String url, int method) {
		return this.makeServiceCall(url, method, null);
	}

	/*
	 * Making service call
	 * @url - url to make request
	 * @method - http request method
	 * @params - http request params
	 * */
	public String makeServiceCall(String url, int method,
			List<NameValuePair> params) {
		try {
			// http client
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpEntity httpEntity = null;
			HttpResponse httpResponse = null;
			Log.d("info","make servc call enter");			
			// Checking http request method type
			if (method == POST) {
				Log.d("info","POST make servc call enter");		
				HttpPost httpPost = new HttpPost(url);
				// adding post params
				if (params != null) {
					Log.d("info","POST PAR make servc call enter");		
					httpPost.setEntity(new UrlEncodedFormEntity(params));
				}

				httpResponse = httpClient.execute(httpPost);

			} else if (method == GET) {
				// appending params to url
				Log.d("info","GET make servc call enter");		
				if (params != null) {
					Log.d("info","GET PARAM make servc call enter");		
					String paramString = URLEncodedUtils
							.format(params, "utf-8");
					url += "?" + paramString;
				}
				HttpGet httpGet = new HttpGet(url);
				Log.d("info","GET entity make servc call enter");
				httpResponse = httpClient.execute(httpGet);
				Log.d("info","GET response make servc call enter");
			}
			httpEntity = httpResponse.getEntity();
			Log.d("info","GET response entity make servc call enter");
			is = httpEntity.getContent();
			Log.d("info","GET response content make servc call enter");

		} catch (UnsupportedEncodingException e) {
			Log.e("error", "service handler exception1"+e.getMessage());
		} catch (ClientProtocolException e) {
			Log.e("error", "service handler exception2"+e.getMessage());
		} catch (IOException e) {
			Log.e("error", "service handler exception3"+e.getMessage());
		}
		catch (Exception e) {
			Log.e("error", "service handler exception4"+e.getMessage());
		}

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			response = sb.toString();
		} catch (Exception e) {
			Log.e("Buffer Error", "Error: 5" + e.toString());
		}

		return response;

	}
}
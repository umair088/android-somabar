package com.pyntail.somabar.retrofit;

import com.pyntail.somabar.helpers.UIHelper;

import android.app.Activity;

public class WebResponse<T> {
	
	private String Response;
	private String Message;
	private T Result;
	private boolean status;
	
	public String getResponse() {
		return Response;
	}
	
	public void setResponse( String response ) {
		Response = response;
	}
	
	public String getMessage() {
		return Message;
	}
	
	public void setMessage( String message ) {
		Message = message;
	}
	
	public T getResult() {
		return Result;
	}
	
	public void setResult( T result ) {
		Result = result;
	}
	
	public boolean isSuccess() {
		return Response.contentEquals( "Success" );
	}
	
	public void setSuccess(boolean status) {
		this.status = status;
		
	}
	
	public void showMessage(Activity activity) {
		if(Message!=null)
		UIHelper.showLongToastInCenter(activity, Message);
	}
}

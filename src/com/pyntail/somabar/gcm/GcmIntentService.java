package com.pyntail.somabar.gcm;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.pyntail.somabar.R;
import com.pyntail.somabar.activities.MainActivity;
import com.pyntail.somabar.activities.SignInActivity;
import com.pyntail.somabar.helpers.Applog;
import com.pyntail.somabar.retrofit.GsonFactory;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;





public class GcmIntentService extends IntentService {
	
	
	public static final String TAG = GcmIntentService.class.getSimpleName();
	
	public static int NOTIFICATION_ID = 1;
	NotificationCompat.Builder builder;
	private NotificationManager mNotificationManager;

	public GcmIntentService() {
		super("GcmIntentService");

		// prefHelper = new BasePreferenceHelper(this);
	}

	@Override
	protected void onHandleIntent(Intent intent) {

		
		Applog.Debug(TAG,"Got Notification");
		Bundle extras = intent.getExtras();

		GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
		// The getMessageType() intent parameter must be the intent you received
		// in your BroadcastReceiver.
		String messageType = gcm.getMessageType(intent);

		if (!extras.isEmpty()) { // has effect of unparcelling Bundle
			/*
			 * Filter messages based on message type. Since it is likely that
			 * GCM will be extended in the future with new message types, just
			 * ignore any message types you're not interested in, or that you
			 * don't recognize.
			 */
			Log.e(TAG, "Received: " + extras.toString());
			if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR
					.equals(messageType)) {

				// sendNotification("Send error: " + extras.getString( "Alert"
				// ).toString() + "");
				sendNotification(false, null);

			} else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED
					.equals(messageType)) {

				// sendNotification("Deleted messages on server: " +
				// extras.toString());
				sendNotification(false, null);
				// If it's a regular GCM message, do some work.
			} else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE
					.equals(messageType)) {
				// This loop represents the service doing some work.

				Log.i(TAG, "Completed work @ " + SystemClock.elapsedRealtime());
				// Post notification of received message.
				// TODO Implement proper notifications
				// sendNotification("Received: " + extras.toString() + "");

				sendNotification(true, extras);
				Log.i(TAG, "Received: " + extras.toString());

			}
		}
	}

	// Put the message into a notification and post it.
	// This is just one simple example of what you might choose to do with
	// a GCM message.
	private void sendNotification(boolean isAdded, Bundle extras) {
		if (!isAdded) {
			return;
		}
		mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		GcmDataObject gcmObject = new GcmDataObject();
		Intent intent = new Intent(this, MainActivity.class);
		
		
		
		if(extras.getString("title")!=null)
		gcmObject.setTitle(extras.getString("title"));
		else
		gcmObject.setTitle(extras.getString("You've got new notification."));
		
		
			
		
		
		if(extras.getString("message")!=null)
		gcmObject.setMsg(extras.getString("message"));
		
		if(extras.getString("url")!=null)
		gcmObject.setUrl(extras.getString("url"));

		if(extras.getString("push_type")!=null)
		gcmObject.setPush_type(extras.getString("push_type"));

		if(extras.getString("object_type")!=null)
		gcmObject.setObjectType(extras.getString("object_type"));
		
		String str = GsonFactory.getConfiguredGson().toJson(gcmObject);
		extras.putString("gcmObject", str);
		intent.putExtras(extras);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Flag added to resume
															// running app.
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);
		// TODO PEnding IOntent
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				this)
		        .setWhen(System.currentTimeMillis())
				.setSmallIcon(R.drawable.ic_launcher)
				.setTicker("Somabar Notification")
				.setContentTitle(gcmObject.getTitle())
				.setStyle(
						new NotificationCompat.BigTextStyle().bigText(
								"Somabar Notification").setSummaryText(
								gcmObject.getMsg())).setOnlyAlertOnce(true)
				.setAutoCancel(true).setContentText(gcmObject.getMsg());
		
		mBuilder.setContentIntent(contentIntent);
		
		//To Vibrate Device
		Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);		
		v.vibrate(650);
		
		
		//To enable LED
		mBuilder.setLights(Color.RED, 3000, 3000);
        
		//message tone
		//mBuilder.setSound(Uri.parse("uri://example.mp3"));
		
		mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
		NOTIFICATION_ID++;
		
		

	}

	

}

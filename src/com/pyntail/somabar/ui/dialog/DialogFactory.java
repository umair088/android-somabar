package com.pyntail.somabar.ui.dialog;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.widget.EditText;

public class DialogFactory {

	public static Dialog createProgressDialog(Activity activity, String title,
			String loadingMessage) {
		ProgressDialog prDialog = new ProgressDialog(activity);
		prDialog.setTitle(title);
		prDialog.setMessage(loadingMessage);
		return prDialog;
	}

	public static Dialog createQuitDialog(Activity activity,
			DialogInterface.OnClickListener dialogPositive, int messageId) {
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle(android.R.string.dialog_alert_title)
				.setMessage(messageId)
				.setCancelable(true)
				.setPositiveButton(android.R.string.yes, dialogPositive)
				.setNegativeButton(android.R.string.no,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();

							}
						});
		return builder.create();

	}

	public static void showPDfFileDialog(final Uri file, final Context context) {

		Intent intent;
		intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(file, "application/pdf");
		try {
			context.startActivity(intent);
		} catch (ActivityNotFoundException e) {
			// No application to view, ask to download one
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setTitle("No Application Found");
			builder.setMessage("Download one from Android Market?");
			builder.setPositiveButton("Yes, Please",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Intent marketIntent = new Intent(Intent.ACTION_VIEW);
							marketIntent.setData(Uri
									.parse("market://details?id=com.adobe.reader"));
							context.startActivity(marketIntent);
						}
					});
			builder.setNegativeButton("No, Thanks", null);
			builder.create().show();
		}

	}

	public static Dialog createSimpleDialog(Activity activity,
			DialogInterface.OnClickListener dialogPositive, int messageId,
			int titleId) {
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle(android.R.string.dialog_alert_title)
				.setMessage(messageId)
				.setCancelable(true)
				.setPositiveButton(android.R.string.yes, dialogPositive)
				.setNegativeButton(android.R.string.no,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();

							}
						});
		return builder.create();

	}

	public static Dialog createMessageDialog(Activity activity,
			DialogInterface.OnClickListener dialogPositive,
			CharSequence message, String titleId) {
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle(android.R.string.dialog_alert_title)
				.setMessage(message).setCancelable(true)
				.setPositiveButton(android.R.string.yes, dialogPositive);
		return builder.create();

	}

	public static Dialog createMessageDialog2(Activity activity,
			DialogInterface.OnClickListener dialogPositive,
			CharSequence message, String titleId) {
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle(android.R.string.dialog_alert_title)
				.setMessage(message).setCancelable(true)
				.setPositiveButton("Yes", dialogPositive)
				.setNegativeButton("No", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();

					}
				});
		return builder.create();

	}

	public static Dialog createInputDialog(Activity activity,
			DialogInterface.OnClickListener dialogPositive,
			DialogInterface.OnClickListener dialogNegative, String title,
			String message) {

		AlertDialog.Builder alert = new AlertDialog.Builder(activity);
		alert.setIcon(android.R.drawable.ic_dialog_alert);
		alert.setTitle(title);
		alert.setMessage(message);
		final EditText input = new EditText(activity);
		alert.setView(input);

		alert.setPositiveButton("Yes", dialogPositive);

		alert.setNegativeButton("No", dialogNegative);

		return alert.create();

	}

	

	public static SweetAlertDialog showSweetAlertDialogTwoChoice(
			Context context, int dialogtype, String title, String content,
			String possitiveText, String negativeText,
			OnSweetClickListener listner, OnSweetClickListener cancelListner) {

		SweetAlertDialog pDialog = new SweetAlertDialog(context, dialogtype);
		pDialog.setTitleText(title);
		pDialog.setContentText(content);
		pDialog.setCancelable(true);
		pDialog.setConfirmText(possitiveText);
		pDialog.setCancelText(negativeText);
		pDialog.setConfirmClickListener(listner);
		pDialog.setCancelClickListener(cancelListner);
		// pDialog.setBackGroundIos();

		return pDialog;
	}

	public static SweetAlertDialog showSweetLoader(Context context,
			int dialogtype, String title) {

		SweetAlertDialog pDialog = new SweetAlertDialog(context, dialogtype);
		pDialog.setTitleText(title);

		pDialog.setCancelable(false);

		pDialog.show();
		return pDialog;
	}

}

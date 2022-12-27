package com.example.mascotas;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.text.Html;

import androidx.appcompat.app.AlertDialog;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;

public class SendEmail extends AsyncTask<Message, String, String> {
    Activity activity;
    //Initialize progress dialog
    private ProgressDialog progressDialog;

    public SendEmail(Activity activity){
    this.activity = activity;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        //Create and show progress dialog
        progressDialog = ProgressDialog.show(activity.getBaseContext(),
                activity.getResources().getString(R.string.title),
                activity.getResources().getString(R.string.message),true,false);
    }

    @Override
    protected String doInBackground(Message... messages) {
        try {
            //When success
            Transport.send(messages[0]);
            return "Success";
        } catch (MessagingException e) {
            //When error
            e.printStackTrace();
            return "Error";
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        //Dismiss progress dialog
        progressDialog.dismiss();
        if(s.equals("Success")){
//When success

            //Initialize alert dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(activity.getBaseContext());
            builder.setCancelable(false);
            builder.setTitle(Html.fromHtml("<font color= '#509324'>Enviado</font>"));
            builder.setMessage("Email enviado correctamente");
            builder.setPositiveButton("Aceptar", (dialog, which) -> {
                dialog.dismiss();
                
            });
        }
    }
}

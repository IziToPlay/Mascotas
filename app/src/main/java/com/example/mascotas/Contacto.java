package com.example.mascotas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Contacto extends AppCompatActivity {
    TextInputEditText tiNombreCompleto;
    TextInputEditText tiEmail;
    TextInputEditText tiMensaje;
    AppCompatButton btnEnviarComentario;
    String sEmail, sPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        Toolbar miActionBar = findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tiNombreCompleto = findViewById(R.id.tiNombreCompleto);
        tiEmail = findViewById(R.id.tiEmail);
        tiMensaje = findViewById(R.id.tiMensaje);
        btnEnviarComentario = findViewById(R.id.btnEnviarComentario);

        //Sender email credential
        //Cuenta creada para esta tarea, cambiarlo con su propio correo en caso de testing
        sEmail = "joslui1720083@gmail.com";
        sPassword = "alsuagoornoolhxz";

        btnEnviarComentario.setOnClickListener(v -> {
            Properties properties = new Properties();
            properties.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
            properties.put("mail.smtp.port", "587"); //TLS Port
            properties.put("mail.smtp.auth", "true"); //enable authentication
            properties.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
            //properties.put("mail.smtp.ssl.enable", "true")

            //Initialize session
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(sEmail,sPassword);
                }
            });
            try {
                //Initialize email content
                Message message = new MimeMessage(session);
                //Sender email
                message.setFrom(new InternetAddress(sEmail));
                //Recipient email
                message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(tiEmail.getText().toString().trim()));
                //Email subject
                message.setSubject(tiNombreCompleto.getText().toString());
                //Email message
                message.setText(tiMensaje.getText().toString());
                //Send email
                new SendEmail().execute(message);
            }catch (MessagingException e){
                e.printStackTrace();
            }
        });

    }

    public class SendEmail extends AsyncTask<Message, String, String> {
        //Initialize progress dialog
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            //Create and show progress dialog
            progressDialog = ProgressDialog.show(Contacto.this,
                    getResources().getString(R.string.title),
                    getResources().getString(R.string.message),true,false);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Contacto.this);
                builder.setCancelable(false);
                builder.setTitle(Html.fromHtml("<font color= '#509324'>Enviado</font>"));
                builder.setMessage("Email enviado correctamente.");
                builder.setPositiveButton("Aceptar", (dialog, which) -> {
                    dialog.dismiss();
                    tiNombreCompleto.setText("");
                    tiEmail.setText("");
                    tiMensaje.setText("");
                });
                //Show alert dialog
                builder.show();
            }else{
                //When error
                Toast.makeText(getApplicationContext(),"Algo salió mal. Intente nuevamente.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
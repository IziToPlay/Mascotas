package com.example.mascotas;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/*public class NotificacionService extends FirebaseMessagingService {
    public static final String TAG = "FIREBASE";
    public static final String TAG_TOKEN = "FIREBASE_TOKEN";
    private static final int REQUEST_CODE = 1;
    private static final String CHANNEL_ID = "Mi Notificacion";

    @Override
    public void onNewToken(@NonNull String token) {
        //super.onNewToken(token);
        enviarTokenRegistro(token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        //super.onMessageReceived(message);
        Log.d(TAG,"From: " + message.getFrom());
        Log.d(TAG,"Notification Message Body: " + message.getNotification().getBody());
        Intent i = new Intent(this,MainActivity.class); //Al dar clic en la notificacion me dirige a la pantalla del MainActivity
        PendingIntent pendingIntent = null;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            pendingIntent = PendingIntent.getActivity(this,REQUEST_CODE,i,PendingIntent.FLAG_IMMUTABLE);
        }
        else
        {
            pendingIntent = PendingIntent.getActivity(this,REQUEST_CODE,i,PendingIntent.FLAG_UPDATE_CURRENT);
        }

        //Obtener sonido
        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);

        //Obtener icono
        //Drawable drawable = ResourcesCompat.getDrawable(getResources(),R.drawable.ic_icon,null);
        //BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        //Bitmap largeIcon = bitmapDrawable.getBitmap();

        //Configurando la notificación
        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_bell)
                .setContentTitle("Notificacion")//message.getNotification().getTitle())
                .setContentText(message.getNotification().getBody())
                .setSound(sonido)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        //.setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(REQUEST_CODE,notificacion.build());
    }
    private void enviarTokenRegistro(String token){
        Log.d(TAG_TOKEN,token);
    }
}*/

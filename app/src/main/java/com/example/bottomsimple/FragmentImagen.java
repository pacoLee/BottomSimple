package com.example.bottomsimple;

import static org.apache.commons.lang3.ClassUtils.getPackageName;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class FragmentImagen extends Fragment {

    ImageView imgvLarge;
    View view;
    ImageButton imgvShare;
    ImageButton imgvDownload;
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public FragmentImagen() {
        // Required empty public constructor
    }

    public static FragmentImagen newInstance(String param1, String param2) {
        FragmentImagen fragment = new FragmentImagen();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String imageId = getArguments().getString("imageId");
        view= inflater.inflate(R.layout.fragment_imagen, container, false);
        imgvLarge=(ImageView) view.findViewById(R.id.imgvLarge);
        imgvShare=(ImageButton) view.findViewById(R.id.imgbShare);
        imgvDownload=(ImageButton) view.findViewById(R.id.imgbDownload);
        char primerCaracter = imageId.charAt(0);
        char segundoCaracter = imageId.charAt(1);
        Picasso.get().load("https://cards.scryfall.io/large/front/" + primerCaracter + "/" + segundoCaracter + "/" + imageId + ".jpg").into(imgvLarge);
        imgvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable mDrawable = imgvLarge.getDrawable();
                Bitmap mBitmap = ((BitmapDrawable) mDrawable).getBitmap();

                String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), mBitmap, "Image Description", null);
                Uri uri = Uri.parse(path);

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_STREAM, uri);
                startActivity(Intent.createChooser(intent, "Share Image"));
            }
        });
        imgvDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable mDrawable = imgvLarge.getDrawable();
                Bitmap mBitmap = ((BitmapDrawable) mDrawable).getBitmap();
                saveImageToDownloadFolder(imageId+".jpg",mBitmap);
            }
        });

        return view;
    }
    public void saveImageToDownloadFolder(String imageFile, Bitmap ibitmap){
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getContext());
        String contentTitle = "Start downloading";
        Intent notifyIntent = new Intent();
        PendingIntent notifyPendingIntent = PendingIntent.getActivity(getContext(),1, notifyIntent, PendingIntent.FLAG_MUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
        //PendingIntent notifyPendingIntent = PendingIntent.getActivity(getContext(), 1, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notificationBuilder = createNotificationBuilder("downloader_channel");
        notificationBuilder.setContentIntent(notifyPendingIntent);
        notificationBuilder.setTicker("Start downloading image");
        notificationBuilder.setOngoing(true);
        notificationBuilder.setAutoCancel(false);
        notificationBuilder.setSmallIcon(android.R.drawable.stat_sys_download);
        notificationBuilder.setContentTitle(contentTitle);
        notificationManagerCompat.notify(1, notificationBuilder.build());
        boolean success;

        try {
            File filePath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), imageFile);
            if(filePath.exists()){
                Toast.makeText(getContext(), imageFile + " already exists", Toast.LENGTH_SHORT).show();
                success=false;
            }else {
                OutputStream outputStream = new FileOutputStream(filePath);
                ibitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                outputStream.flush();
                outputStream.close();
                Toast.makeText(getContext(), imageFile + " sucessfully saved in Download Folder", Toast.LENGTH_SHORT).show();
                success=true;
            }
        } catch (Exception e){
            success=false;
            e.printStackTrace();
        }
        contentTitle = "Downloaded";
        String statusText = success ? "Image "+imageFile+" downloaded" : "Download failed";
        int resId = success ? android.R.drawable.stat_sys_download_done : android.R.drawable.stat_notify_error;

        Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + imageFile+".jpg"), "image/*");
        intent.putExtra(Settings.EXTRA_CHANNEL_ID, "foreground_services");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


        notificationBuilder.setContentTitle(contentTitle);
        notificationBuilder.setSmallIcon(resId);
        notificationBuilder.setOngoing(false);
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setContentText(statusText);
        notificationBuilder.setProgress(0, 0, false);
        notificationBuilder.setContentIntent(PendingIntent.getActivity(getContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT| PendingIntent.FLAG_MUTABLE));
        notificationManagerCompat.notify(1, notificationBuilder.build());
    }
    private NotificationCompat.Builder createNotificationBuilder(String channelId) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String channelName = getString(R.string.app_name);
            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_LOW);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
        return new NotificationCompat.Builder(getContext(), channelId);
    }
}

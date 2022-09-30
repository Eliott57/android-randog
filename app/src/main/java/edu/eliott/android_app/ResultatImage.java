package edu.eliott.android_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.annotation.Target;
import java.net.UnknownHostException;

public class ResultatImage extends AppCompatActivity {

    private void setupImage(){
        JsonObjectRequest requete = new JsonObjectRequest(
                "https://dog.ceo/api/breeds/image/random",
                response -> {
                    try {
                        JSONObject json = new JSONObject(response.toString());
                        String imageUrl = json.getString("message");
                        ImageView i = (ImageView) findViewById(R.id.imageView1);
                        Glide
                                .with(this)
                                .load(imageUrl)
                                .transition(DrawableTransitionOptions.withCrossFade())
                                .listener(new RequestListener<Drawable>() {
                                    @Override
                                    public boolean onLoadFailed(@Nullable GlideException e, Object model, com.bumptech.glide.request.target.Target<Drawable> target, boolean isFirstResource) {
                                        Button monBouton = findViewById(R.id.button8);
                                        Button wallpaperButton = findViewById(R.id.button2);

                                        monBouton.setVisibility(View.VISIBLE);
                                        wallpaperButton.setVisibility(View.VISIBLE);
                                        return false;
                                    }

                                    @Override
                                    public boolean onResourceReady(Drawable resource, Object model, com.bumptech.glide.request.target.Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                        Button monBouton = findViewById(R.id.button8);
                                        Button wallpaperButton = findViewById(R.id.button2);

                                        monBouton.setVisibility(View.VISIBLE);
                                        wallpaperButton.setVisibility(View.VISIBLE);
                                        return false;
                                    }
                                })
                                .into(i);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> error.printStackTrace()
        );

        RequestManager.getInstance(this).addToRequestQueue(requete);
    }

    private void setWallpaper() {
        ImageView i = (ImageView) findViewById(R.id.imageView1);
        i.setDrawingCacheEnabled(true);
        Bitmap img = i.getDrawingCache();

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;

        WallpaperManager manager = WallpaperManager.getInstance(getApplicationContext());

        Bitmap bitmap = Bitmap.createScaledBitmap(img, width, height, true);

        try{
            manager.setBitmap(bitmap);
            Toast.makeText(this, "Wallpaper set!", Toast.LENGTH_SHORT).show();
            i.destroyDrawingCache();
            i.setDrawingCacheEnabled(false);
        } catch (IOException e) {
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat_image);

        Button monBouton = findViewById(R.id.button8);
        Button wallpaperButton = findViewById(R.id.button2);

        monBouton.setVisibility(View.INVISIBLE);
        wallpaperButton.setVisibility(View.INVISIBLE);

        wallpaperButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setWallpaper();
            }
        });

        monBouton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setupImage();
            }
        });

        getSupportActionBar().setTitle("RanDog");

        Toast toast = Toast.makeText(
                this,
                "Searching...",
                Toast.LENGTH_LONG);

        toast.getView().setBackgroundColor(Color.parseColor("#483434"));

        TextView toastMessage =(TextView) toast.getView().findViewById(android.R.id.message);
        toastMessage.setTextColor(Color.parseColor("#FFF3E4"));
        toast.show();

        setupImage();
    }
}
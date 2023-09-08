package com.example.imagepicker;


import static java.nio.channels.spi.AsynchronousChannelProvider.provider;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultLauncherKt;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.github.drjacky.imagepicker.ImagePicker;
import com.github.drjacky.imagepicker.constant.ImageProvider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public class MainActivity extends AppCompatActivity {
    ImageView cover;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialize components
        cover = findViewById(R.id.imageView);
        fab=findViewById(R.id.floatingActionButton);
        ActivityResultLauncher<Intent> launcher=
                registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),(ActivityResult result)->{
                    if(result.getResultCode()==RESULT_OK){
                        Uri uri=result.getData().getData();
                        // Use the uri to load the image
                    }else if(result.getResultCode()==ImagePicker.RESULT_ERROR){
                        // Use ImagePicker.Companion.getError(result.getData()) to show an error
                    }
                });
      fab.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              ImagePicker.Companion.with(MainActivity.this);
                    /*  .crop()
                      .cropOval()
                      .maxResultSize(512,512,true);*/

              launcher.launch(
                      ImagePicker.with(MainActivity.this)
                              .galleryOnly().createIntent()

              );


                   /*  .createIntentFromDialog((Function1)(new Function1(){
                          public Object invoke(Object var1){
                              this.invoke((Intent)var1);
                              return Unit.INSTANCE;
                          }

                          public final void invoke(@NotNull Intent it){
                              Intrinsics.checkNotNullParameter(it,"it");
                              launcher.launch(
                              ImagePicker.with(MainActivity.this)
                                      .cameraOnly().createIntent()

                              );

                          }
                      }));*/
          }
      });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri uri= data.getData();
        cover.setImageURI(uri);
    }
}

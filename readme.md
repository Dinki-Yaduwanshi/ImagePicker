# The IMAGE-PICKER APP
I used two open-source projects to create an easy and customizable way to pick images in Android apps. It can save you time and be a starting point for your own app. You can create a profile activity along with coverImage such that they give user freedom to choose and set pictures by accessing gallery and camera along with bunch of features . Here's a summarized list of what it offers.

1. Easy to Use: You can quickly add image picking to your app.
2. Make it Yours: You can change how it looks and works to match your app's style.
3. Open Source: It's built from open-source projects, so you can trust it, and the community can help.



# Usage

Gradle dependency:

```
implementation 'com.github.Drjacky:ImagePicker:2.3.22'
implementation 'de.hdodenhof:circleimageview:3.1.0'
```
# Demo
https://www.loom.com/share/87f1ecafcaa340ea854a6fc62688feae

# Where is it used?
This app can be used in any app that requires user to set profile picture and cover image. It can be used in social media apps like Facebook, Instagram, Twitter, etc. It can also be used in other apps like e-commerce apps, food delivery apps, etc.


# Features
- Pick Gallery Images
- Pick Images from Google Drive
- Capture Camera Image
- Crop Images(Crop image based on provided aspect ratio or let user choose one)
- Compress Images(Compress image based on provided resolution and size)
- Retrieve Image Result as File, File Path as String or Uri object
## 🎨Customization

**If you want to get the activity result:**


**Java**

```java
ActivityResultLauncher<Intent> launcher=
        registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),(ActivityResult result)->{
        if(result.getResultCode()==RESULT_OK){
        Uri uri=result.getData().getData();
        // Use the uri to load the image
        }else if(result.getResultCode()==ImagePicker.RESULT_ERROR){
        // Use ImagePicker.Companion.getError(result.getData()) to show an error
        }
        });
```

**Both Camera and Gallery:**

```kotlin
    ImagePicker.with(this)
    //...
    .provider(ImageProvider.BOTH) //Or bothCameraGallery()
    .createIntentFromDialog { launcher.launch(it) }
```

**Crop image:**

```kotlin
    .crop()
```

**Crop image with 16:9 aspect ratio:**

```kotlin
    .crop(16f, 9f)
```

**Crop square image(e.g for profile):**

```kotlin
    .cropSquare()    //Crop square image, its same as crop(1f, 1f)
```

**Oval crop image:**

```kotlin
    .crop()
    .cropOval() //Allow dimmed layer to have a circle inside
```

**Set Max Width and Height of final image:**

```kotlin
    .maxResultSize(512, 512, true) //true: Keep Ratio
```

**Java sample for using `createIntentFromDialog`:**

```java
ImagePicker.Companion.with(this) //use the activity.this
        .crop()
        .cropOval()
        .maxResultSize(512,512,true)
        .provider(ImageProvider.BOTH) //Or bothCameraGallery()
        .createIntentFromDialog((Function1)(new Function1(){
public Object invoke(Object var1){
        this.invoke((Intent)var1);
        return Unit.INSTANCE;
        }

public final void invoke(@NotNull Intent it){
        Intrinsics.checkNotNullParameter(it,"it");
        launcher.launch(it);
        }
        }));
```

**If you want just one option(camera or gallery):**

```kotlin
    launcher.launch(
       ImagePicker.with(this)
           //...
           .cameraOnly() // or galleryOnly()
           .createIntent()
    )
```




**Let the user to pick multiple files or single file in gallery mode:**

```kotlin
        .setMultipleAllowed(true)
```

**Let the user defines the output format:**

```kotlin
        .setOutputFormat(Bitmap.CompressFormat.WEBP)
```

**Intercept ImageProvider; could be used for analytics purposes:**

```kotlin
ImagePicker.with(this)
        .setImageProviderInterceptor { imageProvider -> //Intercept ImageProvider
            Log.d("ImagePicker", "Selected ImageProvider: "+imageProvider.name)
        }
        .createIntent()
```

**Intercept Dialog dismiss event:**

```kotlin
    ImagePicker.with(this)
    	.setDismissListener {
    		// Handle dismiss event
    		Log.d("ImagePicker", "onDismiss");
    	}
    	.createIntent()
```

**Limit MIME types while choosing a gallery image:**

```kotlin
        .galleryMimeTypes(  //Exclude gif images
                    mimeTypes = arrayOf(
                      "image/png",
                      "image/jpg",
                      "image/jpeg"
                    )
                  )
```

**Add Following parameters in your **colors.xml** file, if you want to customize uCrop Activity:**

```xml
    <resources>
        <!-- Here you can add color of your choice  -->
        <color name="ucrop_color_toolbar">@color/teal_500</color>
        <color name="ucrop_color_statusbar">@color/teal_700</color>
        <color name="ucrop_color_widget_active">@color/teal_500</color>
    </resources>
```



# Structure of project
`MainActivity.java`

It is the main activity of the app. It contains the code for the main screen of the app which gives users a UI with option to set a background cover image as well as the profile image. It contains the code for the two floatingbuttons buttons and the functions.

`activity_main.xml
`

It is the layout file for the main activity. It contains the code for the UI of the main screen of the app which gives users a UI with option to set a background cover image as well as the profile image. It contains the code for the two floating buttons and the functions.

## How to run?
- Clone the repo
- Open in android studio
- Click on Build -> Run 'app'

## 💥Compatibility

  * Library - Android Kitkat 4.4+ (API 19)
  * Sample - Android Kitkat 4.4+ (API 19)

## 📃 Libraries Used
* ImagePicker [https://github.com/Drjacky/ImagePicker](https://github.com/Drjacky/ImagePicker)
* CircleImageView [https://github.com/hdodenhof/CircleImageView](https://github.com/hdodenhof/CircleImageView)

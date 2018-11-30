package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import static ca.klapstein.baudit.BauditDateFormat.getBauditDateFormat;

import ca.klapstein.baudit.R;
import ca.klapstein.baudit.data.BodyPhoto;
import ca.klapstein.baudit.data.Patient;
import ca.klapstein.baudit.data.Problem;
import ca.klapstein.baudit.data.Record;
import ca.klapstein.baudit.data.RecordPhoto;
import ca.klapstein.baudit.models.DataModel;
import ca.klapstein.baudit.presenters.AddPhotoPresenter;
import ca.klapstein.baudit.views.AddPhotoView;


public class CameraActivity extends AppCompatActivity implements AddPhotoView {

    private ImageView imageView;
    private FloatingActionButton saveButton;
    private static final int REQUEST_CAPTURE_IMAGE = 100;
    private File photoFile;
    private Bitmap imageBitmap;
    private Boolean recordPhoto;
    private AddPhotoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        recordPhoto = getIntent().getBooleanExtra("recordPhoto", false);
        presenter = new AddPhotoPresenter(this, getApplicationContext());

        imageView = (ImageView) findViewById(R.id.takenPhoto);
        saveButton = (FloatingActionButton) findViewById(R.id.savePhotoButton);

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            photoFile = null;
            try {
                photoFile = createFile();
            } catch (IOException ex) {
                Log.e("CameraActivity", "Couldn't Create File");
            }

            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this, "com.example.android.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_CAPTURE_IMAGE);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private File createFile() throws IOException {

        String timeStamp = getBauditDateFormat().format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == REQUEST_CAPTURE_IMAGE && resultCode == RESULT_OK) {
            String filePath = photoFile.getPath();
            imageBitmap = BitmapFactory.decodeFile(filePath);
            if (!presenter.ValidatePhoto(imageBitmap))
                updatePhotoError();
            imageView.setImageBitmap(imageBitmap);
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updatePhotoImage(imageBitmap);
                    commitAddPhotoSuccess();
                }
            });
        } else
            updatePhotoError();
    }

    @Override
    public void updatePhotoImage(Bitmap bitmap){

        DataModel dataManager = new DataModel(this);
        Patient patient = dataManager.getLoggedInPatient();
        if(recordPhoto){
            //TODO: get correct record
            RecordPhoto recordPhoto = new RecordPhoto(bitmap);
            int problemId = getIntent().getIntExtra("probemId", 0);
            int recordId = getIntent().getIntExtra("recordId", -1);
            //Problem problem = (Problem) patient.getProblemTreeSet().toArray()[problemId];
            //Record record = (Record) problem.getRecordTreeSet().toArray()[recordId];
            //record.addPhoto(recordPhoto);

        } else {
            //TODO: how to add body photo if patient isnt logged in?
            //BodyPhoto bodyPhoto = new BodyPhoto(bitmap);
            //patient.setBodyPhoto(bodyPhoto);
        }
    }

    @Override
    public void updatePhotoError(){ finish(); }

    @Override
    public void commitAddPhotoSuccess(){
        //TODO: add to remote and local
    }
}
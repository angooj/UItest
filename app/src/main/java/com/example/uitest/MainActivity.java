
package com.example.uitest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends AppCompatActivity {


    ImageView selectedImage;
    TextView tagged_disease;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_gallery);

        Gallery gallery = (Gallery) findViewById(R.id.gallery);
        selectedImage=(ImageView)findViewById(R.id.imageView);
        tagged_disease=(TextView)findViewById(R.id.tagged_disease) ;
        gallery.setSpacing(1);
        final GalleryImageAdapter galleryImageAdapter= new GalleryImageAdapter(this);
        gallery.setAdapter(galleryImageAdapter);


        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // show the selected Image
                selectedImage.setImageResource(galleryImageAdapter.mImageIds[position]);
            }
        });

        selectedImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final String[] COUNTRIES = new String[] {
                        "chickenpox", "eczema", "fifth_disease", "hand_foot_mouth_disease", "herpes_cold_sores"};

                    LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
                    View promptView = layoutInflater.inflate(R.layout.user_input_dialog_box, null);
                    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertDialogBuilder.setView(promptView);

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                            android.R.layout.simple_list_item_1, COUNTRIES);
                    final AutoCompleteTextView textView = (AutoCompleteTextView)
                            promptView.findViewById(R.id.autoCompleteTextView);
                    textView.setAdapter(adapter);



                    alertDialogBuilder.setCancelable(false)
                            .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    tagged_disease.setText(textView.getText());





                                }

                            });
                    alertDialogBuilder.setNeutralButton("CANCEL", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }

                    });
                    AlertDialog b = alertDialogBuilder.create();
                    b.show();
            }
        });
    }




}

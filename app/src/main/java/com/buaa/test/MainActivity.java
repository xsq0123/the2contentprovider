package com.buaa.test;


import android.widget.EditText;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private String newId;
    // private SearchView searchView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button btadd=(Button) findViewById(R.id.bt_query);
        Button  btdel=(Button) findViewById(R.id.bt_add);
        Button  btupd=(Button) findViewById(R.id.bt_modify);
        Button btque=(Button) findViewById(R.id.bt_del);

        btadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et_word=(EditText) findViewById(R.id.et_word);
                EditText et_mean=(EditText) findViewById(R.id.et_mean);
                EditText  et_egg=(EditText) findViewById(R.id.et_egg);
                Uri uri=Uri.parse("content://com.example.mydbdemo.provider/words");
                ContentValues values=new ContentValues();
                String a=et_word.getText().toString().trim();
                String b=et_mean.getText().toString().trim();
                String c=et_egg.getText().toString().trim();
                values.put("word",a);
                values.put("mean",b);
                values.put("egg",c);
                Uri newuri=getContentResolver().insert(uri,values);
                newId=newuri.getPathSegments().get(1);
            }
        });

        btdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("content://com.example.mydbdemo.provider/words/"+newId);
                getContentResolver().delete(uri,null,null);
            }
        });

        btupd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et_word=(EditText) findViewById(R.id.et_word);
                EditText et_mean=(EditText) findViewById(R.id.et_mean);
                EditText  et_egg=(EditText) findViewById(R.id.et_egg);
                Uri uri=Uri.parse("content://com.example.mydbdemo.provider/words/"+newId);
                ContentValues values=new ContentValues();
                values.put("word",et_word.getText().toString().trim());
                values.put("mean",et_mean.getText().toString().trim());
                values.put("egg",et_egg.getText().toString().trim());
                getContentResolver().update(uri,values,null,null);

            }
        });

        btque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("content://com.example.mydbdemo.provider/words");
                Cursor cursor=getContentResolver().query(uri,null,null,null,null);
                while (cursor!=null){
                    String word=cursor.getString(cursor.getColumnIndex("word"));
                    String mean=cursor.getString(cursor.getColumnIndex("mean"));
                    String egg=cursor.getString(cursor.getColumnIndex("egg"));
                    Log.d("UseSbCPACtivity","word word is"+word);
                    Log.d("UseSbCPACtivity","word mean is"+mean);
                    Log.d("UseSbCPACtivity","word egg is"+egg);
                }
                cursor.close();
            }
        });
    }


}

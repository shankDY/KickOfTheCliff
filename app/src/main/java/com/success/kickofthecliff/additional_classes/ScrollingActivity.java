package com.success.kickofthecliff.additional_classes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.success.kickofthecliff.R;
import com.squareup.picasso.Picasso;

public class ScrollingActivity extends AppCompatActivity implements View.OnClickListener{
    TextView textView;
    ImageView photo;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        textView = (TextView) findViewById(R.id.text2);
        photo = (ImageView) findViewById(R.id.photo2);
        button=(Button)findViewById(R.id.mapButton);

        button.setOnClickListener(this);//создаем слушатель

        Intent intent = getIntent();

        textView.setText(intent.getStringExtra("text"));
        setTitle(intent.getStringExtra("title"));


        String url = String.valueOf(intent.getStringExtra("photo"));//получаем ссылку на картинку с предыдущего активити

        //загрузка изображения в imageView с помощью библиотеки Picasso
        Picasso.with(this)
                .load(url)
                .placeholder(R.drawable.sleep)
                .error(R.drawable.close_outline)
                .into(photo);

    }

    /*обработчик событий для кнопки*/
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mapButton:
                Intent intent=new Intent(this, MapsActivity.class);
                intent.putExtra("latitude", 43.1056200);//передаем широту
                intent.putExtra("longitude", 131.8735300);//передаем долготу
                startActivity(intent);
        }
    }
}

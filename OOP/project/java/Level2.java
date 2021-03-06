package com.example.project;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;


public class Level2 extends AppCompatActivity {

    public int numLeft;
    public int numRight;
    Array array = new Array();
    Random random = new Random();
    public int count = 0;


    Dialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level2);

        final ImageView img_left = (ImageView)findViewById(R.id.img_left);
        //код скругляет углы левой картинки
        img_left.setClipToOutline(true);
        final ImageView img_right = (ImageView)findViewById(R.id.img_right);
        //код скругляет углы правой картинки
        img_right.setClipToOutline(true);

        final TextView text_left = findViewById(R.id.text_left);
        final TextView text_right = findViewById(R.id.text_right);


        //развернуть игру на весь экран
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //создаем диалоговое окно
        dialog = new Dialog(this);//создаем диалоговое окно
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.previewdialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

        //устанавливает картинку в диалоговое окно
        ImageView previewimg = (ImageView)dialog.findViewById(R.id.previewimg);
        previewimg.setImageResource(R.drawable.previewimgtwo);

        //устанавливаем описание задания
        TextView textdescription = (TextView)dialog.findViewById(R.id.textdescription);
        textdescription.setText(R.string.leveltwo);

        //кнопка которая закрывает диалоговое окно
        TextView btnclose = (TextView)dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //обрабатываем нажатие кнопки
                try {

                    Intent intent = new Intent(Level2.this, GameLevels.class);
                    startActivity(intent);
                    finish();

                }catch (Exception e) {

                }
                dialog.dismiss();
            }
        });

        //кнопка "продолжить"
        Button btncontinue = (Button)dialog.findViewById(R.id.btncontinue);
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();

        //Кнопка "назад"
        Button bth_back = (Button) findViewById(R.id.button_back);
        bth_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //обрабатываем нажатие кнопки назад
            try {
                Intent intent = new Intent(Level2.this, GameLevels.class);
                startActivity(intent);
                finish();
            }catch (Exception e) {

            }
            }
        });


        //массив для прогресса игры
        final int[] progress = {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5,
                R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10,
                R.id.point11, R.id.point12, R.id.point13, R.id.point14, R.id.point15,
                R.id.point16, R.id.point17, R.id.point18, R.id.point19, R.id.point20,

        };


        // подключаем анимацию
        final Animation a = AnimationUtils.loadAnimation(Level2.this, R.anim.alpha);


        //генерируем случайное число
        numLeft = random.nextInt(10);
        img_left.setImageResource(array.images2[numLeft]);
        text_left.setText(array.texts2[numLeft]);

        numRight = random.nextInt(10);
        while (numLeft==numRight){
            numRight = random.nextInt(10);
        }
        img_right.setImageResource(array.images2[numRight]);
        text_right.setText(array.texts2[numRight]);



        img_left.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                //условие касания кнопки
                if(event.getAction()== MotionEvent.ACTION_DOWN){
                    //если нажан на кнопку Начало
                    img_right.setEnabled(false);
                    if (numLeft>numRight) {
                        img_left.setImageResource(R.drawable.img_true);
                    } else{
                        img_left.setImageResource(R.drawable.img_false);
                    }
                }else if (event.getAction()==MotionEvent.ACTION_UP){
                    //если отпустил кнопку Начало
                    if(numLeft>numRight){
                        if(count<20){
                            count=count+1;
                        }

                        //закрашиваем прогресс серым цветом
                        for(int i=0; i<20; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for(int i=0;i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }else{
                        if(count>0){
                            if(count==1){
                                count=0;
                            }else{
                                count=count-2;

                            }
                        }
                        //закрашиваем прогресс серым цветом
                        for(int i=0; i<19; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for(int i=0;i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }

                    if(count==20){
                        //выход из уровня

                    }else{
                        //генерируем случайное число
                        numLeft = random.nextInt(10);
                        img_left.setImageResource(array.images2[numLeft]);
                        img_left.startAnimation(a);
                        text_left.setText(array.texts2[numLeft]);

                        numRight = random.nextInt(10);
                        while (numLeft==numRight){
                            numRight = random.nextInt(10);
                        }
                        img_right.setImageResource(array.images2[numRight]);
                        img_right.startAnimation(a);
                        text_right.setText(array.texts2[numRight]);

                        img_right.setEnabled(true); //включаем правую картинку
                    }
                }
                return true;
            }

        });

        img_right.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                //условие касания кнопки
                if(event.getAction()== MotionEvent.ACTION_DOWN){
                    //если нажан на кнопку Начало
                    img_left.setEnabled(false);
                    if (numRight>numLeft) {
                        img_right.setImageResource(R.drawable.img_true);
                    } else{
                        img_right.setImageResource(R.drawable.img_false);
                    }
                }else if (event.getAction()==MotionEvent.ACTION_UP){
                    //если отпустил кнопку Начало
                    if(numRight>numLeft){
                        if(count<20){
                            count=count+1;
                        }

                        //закрашиваем прогресс серым цветом
                        for(int i=0; i<20; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for(int i=0;i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }else{
                        if(count>0){
                            if(count==1){
                                count=0;
                            }else{
                                count=count-2;

                            }
                        }
                        //закрашиваем прогресс серым цветом
                        for(int i=0; i<19; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for(int i=0;i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }

                    if(count==20){
                        //выход из уровня

                    }else{
                        //генерируем случайное число
                        numRight = random.nextInt(10);
                        img_right.setImageResource(array.images2[numRight]);
                        img_right.startAnimation(a);
                        text_right.setText(array.texts2[numRight]);

                        numLeft = random.nextInt(10);
                        while (numLeft==numRight){
                            numLeft = random.nextInt(10);
                        }
                        img_left.setImageResource(array.images2[numLeft]);
                        img_left.startAnimation(a);
                        text_left.setText(array.texts2[numLeft]);

                        img_left.setEnabled(true); //включаем правую картинку
                    }
                }
                return true;
            }

        });
    }



    @Override
    public void onBackPressed() {
        //обрабатываем нажатие кнопки "назад"
        try {
            Intent intent = new Intent(Level2.this, GameLevels.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {

        }
    }
}

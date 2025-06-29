package com.example.dota;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class HeroesDetailActivity extends AppCompatActivity {

    private ImageView roshaImage;
    private Animation roshanAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heroes_detail);

        roshaImage = findViewById(R.id.rosha_image);

        // Оптимизация для плавности анимации
        roshaImage.setLayerType(ImageView.LAYER_TYPE_HARDWARE, null);

        // Инициализация анимации
        roshanAnimation = AnimationUtils.loadAnimation(this, R.anim.animated_roshan);

        // Настройка обработчика окончания анимации (опционально)
        roshanAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // Действия при старте анимации
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Автоповтор анимации
                roshaImage.startAnimation(roshanAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // Действия при повторе
            }
        });

        // Запуск анимации
        roshaImage.startAnimation(roshanAnimation);

        // Обработка клика
        roshaImage.setOnClickListener(v -> {
            Toast.makeText(this, "Roshan активирован!", Toast.LENGTH_SHORT).show();

            // Дополнительный эффект при клике
            Animation clickAnim = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
            roshaImage.startAnimation(clickAnim);
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (roshanAnimation != null) {
            roshaImage.clearAnimation();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (roshanAnimation != null) {
            roshaImage.startAnimation(roshanAnimation);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Освобождение ресурсов
        roshanAnimation = null;
    }
}
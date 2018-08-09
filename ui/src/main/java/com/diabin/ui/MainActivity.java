package com.diabin.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.diabin.ui.dragbubble.DragBubbleActivity;
import com.diabin.ui.dragbubble.DragBubbleView;
import com.diabin.ui.parabola.ParabolaActivity;

public class MainActivity extends AppCompatActivity {
    Button parabola;
    Button drag_bubble_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parabola = findViewById(R.id.parabola);
        parabola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.startActivity(new Intent
                        (MainActivity.this,
                                ParabolaActivity.class));
            }
        });
        drag_bubble_view = findViewById(R.id.drag_bubble_view);
        drag_bubble_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.startActivity(new Intent
                        (MainActivity.this,
                                DragBubbleActivity.class));
            }
        });
    }
}

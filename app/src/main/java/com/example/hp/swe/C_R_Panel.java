package com.example.hp.swe;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class C_R_Panel extends Activity {
    Button class_add,exam_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c__r__panel);
        class_add = findViewById(R.id.add_class);
        exam_add = findViewById(R.id.add_exam);

        class_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(C_R_Panel.this,CR_class_schedule.class));
            }
        });

        exam_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(C_R_Panel.this,Pop_up_exam_adder.class));

            }
        });
    }

}

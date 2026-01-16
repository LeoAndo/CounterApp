package jp.ac.jec.cm0199.jecandroidjavatemplate;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private int count = 0;
    private TextView txtCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtCount = findViewById(R.id.txt_count);
        Button btnIncrement = findViewById(R.id.btn_increment);
        Button btnDecrement = findViewById(R.id.btn_decrement);
        TextView btnReset = findViewById(R.id.btn_reset);

        btnIncrement.setOnClickListener(v -> {
            count++;
            updateCountDisplay();
        });

        btnDecrement.setOnClickListener(v -> {
            count--;
            updateCountDisplay();
        });

        btnReset.setOnClickListener(v -> {
            count = 0;
            updateCountDisplay();
        });
    }

    private void updateCountDisplay() {
        txtCount.setText(String.valueOf(count));
    }
}
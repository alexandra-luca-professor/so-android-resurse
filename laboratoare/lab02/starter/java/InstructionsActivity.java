package ro.so.android.numeprenume;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InstructionsActivity extends AppCompatActivity {
    private static final String TAG = "Lab02";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, getClass().getSimpleName() + " onCreate");

        EdgeToEdge.enable(this);

        // TODO 1: incarca layout-ul activity_instructions cu setContentView.

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.instructionsRoot),
                (view, windowInsets) -> {
                    Insets insets = windowInsets.getInsets(
                            WindowInsetsCompat.Type.systemBars()
                                    | WindowInsetsCompat.Type.displayCutout()
                    );
                    view.setPadding(
                            insets.left, insets.top,
                            insets.right, insets.bottom
                    );
                    return windowInsets;
                }
        );

        // TODO 2: gaseste butonul cu ID-ul buttonInstructionsBack.

        // TODO 3: adauga un listener de click pe buton. In lambda:
        //   a) scrie in Logcat mesajul "Inchidem InstructionsActivity";
        //   b) inchide ecranul curent cu finish().
    }

    // TODO 4: adauga metodele lifecycle onStart(), onResume(), onPause(),
    // onStop(), onRestart() si onDestroy(), la fel ca in GameActivity.
    // Fiecare apeleaza metoda din super si scrie in Logcat numele clasei
    // urmat de numele metodei, de exemplu "InstructionsActivity onStart".
}

package ro.so.android.numeprenume;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GameActivity extends AppCompatActivity {
    private static final String TAG = "Lab02";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, getClass().getSimpleName() + " onCreate");

        EdgeToEdge.enable(this);

        // TODO 1: incarca layout-ul activity_game cu setContentView.

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.gameRoot),
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

        // TODO 2: gaseste butonul cu ID-ul buttonBack (findViewById).

        // TODO 3: adauga un listener de click pe buton. In lambda:
        //   a) scrie in Logcat mesajul "Inchidem GameActivity";
        //   b) inchide ecranul curent cu finish().
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, getClass().getSimpleName() + " onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, getClass().getSimpleName() + " onResume");
    }

    // TODO 4: suprascrie onPause(). Apeleaza super.onPause() si scrie in Logcat
    // numele clasei urmat de " onPause", la fel ca in celelalte metode.

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, getClass().getSimpleName() + " onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, getClass().getSimpleName() + " onRestart");
    }

    // TODO 5: suprascrie onDestroy(). Apeleaza super.onDestroy() si scrie in Logcat
    // numele clasei urmat de " onDestroy", la fel ca in celelalte metode.
}

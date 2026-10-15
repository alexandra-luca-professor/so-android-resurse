package ro.so.android.numeprenume;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Lab02";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, getClass().getSimpleName() + " onCreate");

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
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

        // TODO 1: gaseste butonul cu ID-ul buttonStart si pastreaza-l
        // intr-o variabila de tip Button (findViewById).

        // TODO 2: adauga un listener de click pe buton (setOnClickListener).
        // In lambda:
        //   a) scrie in Logcat mesajul "Deschidem GameActivity" (Log.d);
        //   b) creeaza un Intent explicit catre GameActivity.class,
        //      folosind MainActivity.this ca prim parametru;
        //   c) porneste ecranul cu startActivity(intent).

        // TODO (tema): gaseste butonul buttonInstructions si adauga-i un listener
        // care scrie in Logcat "Deschidem InstructionsActivity" si porneste
        // InstructionsActivity, la fel ca la butonul Start.
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

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, getClass().getSimpleName() + " onPause");
    }

    // TODO 3: suprascrie onStop(). Apeleaza super.onStop() si scrie in Logcat
    // numele clasei urmat de " onStop", la fel ca in celelalte metode.

    // TODO 4: suprascrie onRestart(). Apeleaza super.onRestart() si scrie in Logcat
    // numele clasei urmat de " onRestart", la fel ca in celelalte metode.

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, getClass().getSimpleName() + " onDestroy");
    }
}

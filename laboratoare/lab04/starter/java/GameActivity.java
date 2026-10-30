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
    private static final String TAG = "Lab04";

    private BoardView boardView;
    private GameLoop gameLoop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, getClass().getSimpleName() + " onCreate");

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_game);

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

        Log.d(TAG, getClass().getSimpleName() + " PID=" + android.os.Process.myPid());

        boardView = findViewById(R.id.boardView);

        Button buttonUp = findViewById(R.id.buttonUp);
        Button buttonDown = findViewById(R.id.buttonDown);
        Button buttonLeft = findViewById(R.id.buttonLeft);
        Button buttonRight = findViewById(R.id.buttonRight);

        buttonUp.setOnClickListener(v -> boardView.changeDirection(0, -1));

        // TODO 8: butonul Sus este scris mai sus, ca exemplu: trimite directia
        // (0, -1), adica randul scade cu 1. Scrieti la fel celelalte trei
        // butoane, cate o linie:
        //   buttonDown  -> directia (0, 1)
        //   buttonLeft  -> directia (-1, 0)
        //   buttonRight -> directia (1, 0)


        // TODO 4: creati game loop-ul, in doua linii.
        //
        //   1) Cream obiectul GameLoop si ii spunem ce sa faca la fiecare tick:
        //      sa apeleze boardView.updateGame().
        //          gameLoop = new GameLoop(() -> boardView.updateGame());
        //
        //   2) Stabilim viteza: un pas la 300 ms.
        //          gameLoop.setIntervalMs(300L);


        // TODO 9 (tema): butoanele Lent si Rapid.
        //   Luati butoanele cu findViewById (ID-urile buttonSlow si buttonFast),
        //   la fel ca butoanele de directie de mai sus.
        //   La apasare:
        //     - Lent  apeleaza gameLoop.setIntervalMs(300L);
        //     - Rapid apeleaza gameLoop.setIntervalMs(150L);


        Button buttonBack = findViewById(R.id.buttonBack);

        buttonBack.setOnClickListener(view -> {
            Log.d(TAG, "Inchidem GameActivity");

            finish();
        });
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

        // TODO 5a: ecranul jocului este vizibil, deci pornim bucla.
        // Scrieti aici:
        //     gameLoop.start();

    }

    @Override
    protected void onPause() {
        // TODO 5b: ecranul jocului nu mai este in prim-plan (de exemplu ati
        // apasat Home), deci oprim bucla, ca sarpele sa nu se miste in fundal.
        // Scrieti aici, INAINTE de super.onPause():
        //     gameLoop.stop();

        super.onPause();
        Log.d(TAG, getClass().getSimpleName() + " onPause");
    }

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, getClass().getSimpleName() + " onDestroy");
    }
}

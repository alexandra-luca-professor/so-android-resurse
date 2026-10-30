package ro.so.android.numeprenume;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public class GameLoop {
    private static final String TAG = "Lab04";

    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Runnable onTick;

    private boolean running = false;
    private long intervalMs = 300L;
    private long tickCount = 0L;

    private final Runnable tick = new Runnable() {
        @Override
        public void run() {
            if (!running) {
                return;
            }

            tickCount++;
            Log.d(TAG, "TICK " + tickCount + ", interval=" + intervalMs + " ms");

            onTick.run();

            if (running) {
                handler.postDelayed(this, intervalMs);
            }
        }
    };

    public GameLoop(Runnable onTick) {
        this.onTick = onTick;
    }

    public void start() {
        if (running) {
            return;
        }

        running = true;
        Log.d(TAG, "START");
        handler.postDelayed(tick, intervalMs);
    }

    public void stop() {
        running = false;
        handler.removeCallbacks(tick);
        Log.d(TAG, "STOP");
    }

    public void setIntervalMs(long intervalMs) {
        this.intervalMs = intervalMs;
        Log.d(TAG, "VITEZA: interval=" + intervalMs + " ms");
    }
}

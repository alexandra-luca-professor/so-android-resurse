package ro.so.android.numeprenume;

import android.media.AudioManager;
import android.media.ToneGenerator;
import android.util.Log;

// GameSounds reda sunetele jocului: un bip la mancare si un ton la final.
// ToneGenerator produce tonuri de sistem, deci nu avem nevoie de fisiere audio.
// Il pornim in onResume() si il eliberam in onPause(), ca bucla.
public final class GameSounds {
    private static final String TAG = "Lab05";
    private static final int VOLUME = 70;

    private ToneGenerator generator;

    // Pregateste generatorul de tonuri. Daca sunetul nu e disponibil, jocul merge fara el.
    public void start() {
        if (generator != null) {
            return;
        }
        try {
            generator = new ToneGenerator(AudioManager.STREAM_MUSIC, VOLUME);
        } catch (RuntimeException e) {
            Log.w(TAG, "Sunetul nu este disponibil", e);
            generator = null;
        }
    }

    // Elibereaza resursa audio a sistemului.
    public void stop() {
        if (generator == null) {
            return;
        }
        generator.release();
        generator = null;
    }

    // Bip scurt, de 90 ms, cand sarpele mananca.
    public void eat() {
        play(ToneGenerator.TONE_PROP_BEEP, 90);
    }

    // Ton de 500 ms la final: unul la victorie, altul la pierdere.
    public void gameOver(boolean won) {
        if (won) {
            play(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 500);
        } else {
            play(ToneGenerator.TONE_CDMA_ABBR_ALERT, 500);
        }
    }

    private void play(int tone, int durationMs) {
        if (generator == null) {
            return;
        }
        try {
            generator.startTone(tone, durationMs);
        } catch (RuntimeException e) {
            Log.w(TAG, "Tonul nu a putut fi redat", e);
        }
    }
}

package ro.so.android.numeprenume;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class BoardView extends View {
    private static final String TAG = "Lab03";

    public static final int COLS = 10;
    public static final int ROWS = 10;

    private final Paint gridPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint outlinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint bodyPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint spotPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint eyePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint pupilPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint tonguePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint obstaclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint piecePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private float cellSize = 0f;
    private float boardLeft = 0f;
    private float boardTop = 0f;

    private final GameState state = new GameState();

    public BoardView(Context context) {
        super(context);
        init();
    }

    public BoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BoardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        gridPaint.setColor(Color.parseColor("#6F8F7E"));
        gridPaint.setStyle(Paint.Style.STROKE);
        gridPaint.setStrokeWidth(2f);
        gridPaint.setAntiAlias(false);

        outlinePaint.setColor(Color.parseColor("#14401F"));
        outlinePaint.setStyle(Paint.Style.STROKE);
        outlinePaint.setStrokeCap(Paint.Cap.ROUND);
        outlinePaint.setStrokeJoin(Paint.Join.ROUND);

        bodyPaint.setColor(Color.parseColor("#4CAF66"));
        bodyPaint.setStyle(Paint.Style.STROKE);
        bodyPaint.setStrokeCap(Paint.Cap.ROUND);
        bodyPaint.setStrokeJoin(Paint.Join.ROUND);

        spotPaint.setColor(Color.parseColor("#2F7A44"));
        spotPaint.setStyle(Paint.Style.FILL);

        eyePaint.setColor(Color.WHITE);
        eyePaint.setStyle(Paint.Style.FILL);

        pupilPaint.setColor(Color.parseColor("#14281D"));
        pupilPaint.setStyle(Paint.Style.FILL);

        tonguePaint.setColor(Color.parseColor("#E63946"));
        tonguePaint.setStyle(Paint.Style.STROKE);
        tonguePaint.setStrokeCap(Paint.Cap.ROUND);

        obstaclePaint.setColor(Color.parseColor("#8B9A93"));
        obstaclePaint.setStyle(Paint.Style.FILL);

        piecePaint.setColor(Color.rgb(46, 125, 50));
        piecePaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        float availableWidth = w - getPaddingLeft() - getPaddingRight();
        float availableHeight = h - getPaddingTop() - getPaddingBottom();

        cellSize = Math.min(
                availableWidth / COLS,
                availableHeight / ROWS
        );

        float boardWidth = COLS * cellSize;
        float boardHeight = ROWS * cellSize;

        boardLeft = getPaddingLeft() + (availableWidth - boardWidth) / 2f;
        boardTop = getPaddingTop() + (availableHeight - boardHeight) / 2f;

        gridPaint.setStrokeWidth(Math.max(2f, Math.round(cellSize * 0.04f)));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (cellSize <= 0f) {
            return;
        }

        drawGrid(canvas);
        drawSnakeSimple(canvas);
        drawObstacles(canvas);

        // TODO 4: dupa ce sarpele din patrate apare corect (TODO 3), inlocuiti
        // mai sus drawSnakeSimple(canvas) cu drawSnake(canvas).
        // drawSnake() este deja scrisa mai jos si deseneaza sarpele cu corp
        // continuu, ochi si limba. Nu trebuie sa o modificati.
    }

    private void drawGrid(Canvas canvas) {
        float top = Math.round(boardTop);
        float bottom = Math.round(boardTop + ROWS * cellSize);
        float left = Math.round(boardLeft);
        float right = Math.round(boardLeft + COLS * cellSize);

        for (int col = 0; col <= COLS; col++) {
            float x = Math.round(boardLeft + col * cellSize);

            canvas.drawLine(x, top, x, bottom, gridPaint);
        }

        for (int row = 0; row <= ROWS; row++) {
            float y = Math.round(boardTop + row * cellSize);

            canvas.drawLine(left, y, right, y, gridPaint);
        }
    }

    private void drawSnakeSimple(Canvas canvas) {
        float gap = cellSize * 0.08f;

        // TODO 3: deseneaza sarpele, cate un patrat pentru fiecare celula.
        // Parcurgeti lista state.snake cu un for (Cell cell : state.snake).
        // Pentru fiecare celula:
        //   a) calculati coltul din stanga-sus al celulei, in pixeli:
        //        float left = boardLeft + cell.col * cellSize;
        //        float top = boardTop + cell.row * cellSize;
        //   b) desenati patratul cu canvas.drawRect(...), cu piecePaint.
        //      Lasati spatiul gap pe fiecare latura, ca segmentele sa nu se lipeasca:
        //        stanga: left + gap          sus: top + gap
        //        dreapta: left + cellSize - gap    jos: top + cellSize - gap
    }

    private void drawSnake(Canvas canvas) {
        if (state.snake.isEmpty()) {
            return;
        }

        float headWidth = cellSize * 0.72f;
        float tailWidth = cellSize * 0.30f;
        float outline = cellSize * 0.09f;

        drawBodyStroke(canvas, outlinePaint, headWidth + outline, tailWidth + outline);
        drawBodyStroke(canvas, bodyPaint, headWidth, tailWidth);
        drawSpots(canvas, headWidth, tailWidth);
        drawHead(canvas);
    }

    private void drawBodyStroke(Canvas canvas, Paint paint, float widthAtHead, float widthAtTail) {
        int n = state.snake.size();

        if (n == 1) {
            Cell only = state.snake.get(0);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(centerX(only), centerY(only), widthAtHead / 2f, paint);
            paint.setStyle(Paint.Style.STROKE);
            return;
        }

        for (int i = n - 1; i >= 1; i--) {
            float t = (float) i / (n - 1);
            paint.setStrokeWidth(widthAtHead + (widthAtTail - widthAtHead) * t);

            Cell from = state.snake.get(i);
            Cell to = state.snake.get(i - 1);

            canvas.drawLine(
                    centerX(from), centerY(from),
                    centerX(to), centerY(to),
                    paint
            );
        }
    }

    private void drawSpots(Canvas canvas, float widthAtHead, float widthAtTail) {
        int n = state.snake.size();

        for (int i = 1; i < n; i += 2) {
            float t = (n == 1) ? 0f : (float) i / (n - 1);
            float width = widthAtHead + (widthAtTail - widthAtHead) * t;

            Cell cell = state.snake.get(i);
            canvas.drawCircle(centerX(cell), centerY(cell), width * 0.22f, spotPaint);
        }
    }

    private void drawHead(Canvas canvas) {
        Cell head = state.snake.get(0);

        float length = cellSize * 1.02f;
        float width = cellSize * 0.84f;
        float outline = cellSize * 0.09f;

        canvas.save();
        canvas.translate(centerX(head), centerY(head));
        canvas.rotate(headAngle());

        outlinePaint.setStyle(Paint.Style.FILL);
        canvas.drawOval(
                -length / 2f - outline / 2f, -width / 2f - outline / 2f,
                length / 2f + outline / 2f, width / 2f + outline / 2f,
                outlinePaint
        );
        outlinePaint.setStyle(Paint.Style.STROKE);

        bodyPaint.setStyle(Paint.Style.FILL);
        canvas.drawOval(-length / 2f, -width / 2f, length / 2f, width / 2f, bodyPaint);
        bodyPaint.setStyle(Paint.Style.STROKE);

        float eyeX = length * 0.16f;
        float eyeY = width * 0.24f;
        float eyeRadius = width * 0.15f;

        canvas.drawCircle(eyeX, -eyeY, eyeRadius, eyePaint);
        canvas.drawCircle(eyeX, eyeY, eyeRadius, eyePaint);
        canvas.drawCircle(eyeX + eyeRadius * 0.35f, -eyeY, eyeRadius * 0.48f, pupilPaint);
        canvas.drawCircle(eyeX + eyeRadius * 0.35f, eyeY, eyeRadius * 0.48f, pupilPaint);

        float start = length / 2f;
        float fork = start + cellSize * 0.20f;
        float tip = cellSize * 0.12f;

        tonguePaint.setStrokeWidth(Math.max(2f, cellSize * 0.06f));
        canvas.drawLine(start - cellSize * 0.04f, 0f, fork, 0f, tonguePaint);
        canvas.drawLine(fork, 0f, fork + tip, -tip, tonguePaint);
        canvas.drawLine(fork, 0f, fork + tip, tip, tonguePaint);

        canvas.restore();
    }

    private float headAngle() {
        if (state.snake.size() < 2) {
            return 0f;
        }

        Cell head = state.snake.get(0);
        Cell neck = state.snake.get(1);

        int dirCol = head.col - neck.col;
        int dirRow = head.row - neck.row;

        if (dirRow > 0) {
            return 90f;
        }
        if (dirCol < 0) {
            return 180f;
        }
        if (dirRow < 0) {
            return 270f;
        }
        return 0f;
    }

    private float centerX(Cell cell) {
        return boardLeft + cell.col * cellSize + cellSize / 2f;
    }

    private float centerY(Cell cell) {
        return boardTop + cell.row * cellSize + cellSize / 2f;
    }

    private void drawObstacles(Canvas canvas) {
        // TODO 7 (tema): deseneaza obstacolele din lista state.obstacles.
        // Parcurgeti lista cu un for, ca la sarpe, si pentru fiecare celula apelati:
        //   drawCell(canvas, cell, obstaclePaint);
        // drawCell() este scrisa mai jos: deseneaza un patrat pe toata celula.
    }

    private void drawCell(Canvas canvas, Cell cell, Paint paint) {
        float left = boardLeft + cell.col * cellSize;
        float top = boardTop + cell.row * cellSize;

        canvas.drawRect(
                left,
                top,
                left + cellSize,
                top + cellSize,
                paint
        );
    }

    @Nullable
    public Cell cellFromTouch(float x, float y) {
        if (cellSize <= 0f) {
            return null;
        }

        if (x < boardLeft || y < boardTop) {
            return null;
        }
        if (x >= boardLeft + COLS * cellSize || y >= boardTop + ROWS * cellSize) {
            return null;
        }

        int col = (int) ((x - boardLeft) / cellSize);
        int row = (int) ((y - boardTop) / cellSize);

        if (col < 0 || col >= COLS || row < 0 || row >= ROWS) {
            return null;
        }

        return new Cell(col, row);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                Cell cell = cellFromTouch(event.getX(), event.getY());

                // TODO 5: scrieti in Logcat celula atinsa.
                //   a) daca cell nu este null, scrieti cu Log.d(TAG, ...) mesajul
                //      "col=" + cell.col + ", row=" + cell.row
                //   b) altfel, scrieti mesajul "atingere in afara tablei".

                return true;

            case MotionEvent.ACTION_UP:
                performClick();
                return true;

            case MotionEvent.ACTION_CANCEL:
                return true;

            default:
                return super.onTouchEvent(event);
        }
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }
}

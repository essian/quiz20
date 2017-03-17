package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Essian on 17/03/2017.
 */

public class ScoreDbHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "scoreBase.db";

    public ScoreDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE SCORE ("
        + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "NAME TEXT, "
        + "SCORE INTEGER);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static void insertScore(SQLiteDatabase db, String name, int score) {
        ContentValues scoreValues = new ContentValues();
//        scoreValues.put("NAME", name);
        scoreValues.put(ScoresDbSchema.ScoreTable.Cols.NAME, name);
        scoreValues.put(ScoresDbSchema.ScoreTable.Cols.SCORE, score);
        db.insert("SCORE", null, scoreValues);
    }
}

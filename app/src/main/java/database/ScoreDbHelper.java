package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import database.ScoresDbSchema.ScoreTable;
import database.ScoresDbSchema.ScoreTable.Cols;

/**
 * Created by Essian on 17/03/2017.
 */

public class ScoreDbHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "scoreBase.db";

    /**
     * basic constructor
     * @param context
     */
    public ScoreDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    /**
     * Creates score table
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + ScoreTable.NAME + "("
               + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + ScoreTable.Cols.NAME + " TEXT, "
                        + ScoreTable.Cols.SCORE + " INTEGER);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * Inserts a score in the database
     * @param db database
     * @param name name of user
     * @param score score of user
     */
    public static void insertScore(SQLiteDatabase db, String name, int score) {
        ContentValues scoreValues = new ContentValues();
        scoreValues.put(Cols.NAME, name);
        scoreValues.put(Cols.SCORE, score);
        db.insert(ScoreTable.NAME, null, scoreValues);
    }
}

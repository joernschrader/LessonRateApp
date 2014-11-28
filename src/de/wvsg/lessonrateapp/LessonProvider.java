package de.wvsg.lessonrateapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.support.v4.content.CursorLoader;


public class LessonProvider extends CursorLoader {

	// database constants
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "data.db";
	public static final String DATABASE_TABLE = "lessons";
	// database fields
	public static final String COLUMN_ROWID = "_id";
	public static final String COLUMN_DATETIME = "lesson_date_time";
	public static final String COLUMN_SUBJECT = "subject";
	public static final String COLUMN_TEACHER = "teacher";
	public static final String COLUMN_TOPIC = "topic";
	public static final String COLUMN_RATE = "rate";

	private static final String DATABASE_CREATE = "create table "
			+ DATABASE_TABLE + " (" + COLUMN_ROWID
			+ " integer primary key autoincrement, " + COLUMN_SUBJECT
			+ " text not null, " + COLUMN_TEACHER + " text not null, "
			+ COLUMN_TOPIC + " text not null, " + COLUMN_RATE
			+ " integer not null, " + COLUMN_DATETIME + " integer not null);";

	public class LessonDataBase extends SQLiteOpenHelper {

		// constructor
		LessonDataBase(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
			throw new UnsupportedOperationException();
		}
	}

	private LessonDataBase mDb;

	public LessonProvider(Context context) {
		super(context);
		mDb = new LessonDataBase(context);
	}

	@Override
	public Cursor loadInBackground() {
		return getAllLessons();
	}
	
	
	public Cursor loadDetail(long id) {
		SQLiteQueryBuilder sqlBuilder = new SQLiteQueryBuilder();
		sqlBuilder.setTables(DATABASE_TABLE);
		Cursor cursor = sqlBuilder.query(mDb.getReadableDatabase(), null, 
				LessonProvider.COLUMN_ROWID + "=?", new String[] { Long.toString(id) },
				null, null, null, null);
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
		}
		return cursor;
	}
	
	public Cursor getAllLessons() {
		SQLiteQueryBuilder sqlBuilder = new SQLiteQueryBuilder();
		sqlBuilder.setTables(DATABASE_TABLE);
		Cursor cursor = sqlBuilder.query(mDb.getReadableDatabase(), null, null,
				null, null, null, null);
		return cursor;
	}
	
	public long insert(ContentValues values) {
		SQLiteDatabase wDb = mDb.getWritableDatabase();
		long newRowId = wDb.insertOrThrow(DATABASE_TABLE, null, values);
		return newRowId;
	}
	
	public int update(ContentValues values) {
		SQLiteDatabase wDb = mDb.getWritableDatabase();
		int count = wDb.update(DATABASE_TABLE, values, COLUMN_ROWID+ "=?", 
				new String[] { values.getAsString(COLUMN_ROWID) });
		return count;
	}
	
	public void delete(long id) {
		mDb.getWritableDatabase().delete(DATABASE_TABLE, COLUMN_ROWID + " = " + id, null);
	}

}

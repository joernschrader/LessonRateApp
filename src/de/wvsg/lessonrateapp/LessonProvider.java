package de.wvsg.lessonrateapp;


public class LessonProvider {

	public static final String COLUMN_ROWID = "_id";
	/*
	public class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {
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

	// ContentProvider URI and source
	public static String AUTHORITY = "de.wvsg.rateapp.LessonProvider";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
			+ "/lessons");

	// MIME-types for special requests
	public static final String LESSONS_MIME_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
			+ "/vnd.de.wvsg.lessonrateapp.lesson";
	public static final String LESSON_MIME_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
			+ "/vnd.de.wvsg.lessonrateapp.lesson";

	// UriMatcher
	private static final int LIST_LESSON = 0;
	private static final int ITEM_LESSON = 1;
	private static final UriMatcher sURIMatcher = buildUriMatcher();

	// database constants
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "data.db";
	private static final String DATABASE_TABLE = "lessons";

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

	private SQLiteDatabase mDb;

	private static UriMatcher buildUriMatcher() {
		UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
		matcher.addURI(AUTHORITY, "lesson", LIST_LESSON);
		matcher.addURI(AUTHORITY, "lesson/#", ITEM_LESSON);
		return matcher;
	}

	@Override
	public boolean onCreate() {
		mDb = new DatabaseHelper(getContext()).getWritableDatabase();
		return false;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		switch (sURIMatcher.match(uri)) {
		case LIST_LESSON:
			return LESSONS_MIME_TYPE;
		case ITEM_LESSON:
			return LESSON_MIME_TYPE;
		default:
			throw new IllegalArgumentException("Unknown Uri: " + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		values.remove(LessonProvider.COLUMN_ROWID);
		long id = mDb.insertOrThrow(LessonProvider.DATABASE_TABLE, null, values);
		getContext().getContentResolver().notifyChange(uri, null);
		return ContentUris.withAppendedId(uri, id);
	}

	@Override
	public Cursor query(Uri uri, String[] ignored1, String ignored2,
			String[] ignored3, String ignored4) {
		String[] projection = new String[] {
			LessonProvider.COLUMN_ROWID, LessonProvider.COLUMN_SUBJECT,
			LessonProvider.COLUMN_TEACHER, LessonProvider.COLUMN_TOPIC,
			LessonProvider.COLUMN_RATE, LessonProvider.COLUMN_DATETIME
		};
		
		// UriMatcher: determine query-type -> format sql
		Cursor c;
		switch (sURIMatcher.match(uri)) {
		case LIST_LESSON:
			c = mDb.query(LessonProvider.DATABASE_TABLE, projection, null, null, null, null, null);
			break;
		case ITEM_LESSON:
			c = mDb.query(LessonProvider.DATABASE_TABLE, projection, LessonProvider.COLUMN_ROWID + "=?", 
					new String[] { Long.toString(ContentUris.parseId(uri)) }, 
					null, null, null, null);
			if (c != null && c.getCount() > 0 ) {
				c.moveToFirst();
			}
			break;
		default:
			throw new IllegalArgumentException("Unbekannte URI: " + uri);
		}
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	*/

}

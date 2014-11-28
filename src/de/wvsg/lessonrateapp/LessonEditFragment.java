package de.wvsg.lessonrateapp;

import java.util.Calendar;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class LessonEditFragment extends Fragment {
	public static final String DEFAULT_EDIT_FRAGMENT_TAG = "editFragmentTag";
	
	static final String YEAR = "year";
	static final String MONTH = "month";
	static final String DAY = "day";
	static final String HOUR = "hour";
	static final String MINS = "mins";
	static final String CALENDAR = "calendar";
	  
	private EditText mSubject;
	private EditText mTeacher;
	private EditText mTopic;
	private RatingBar mRate;
	private Button mConfirmButton;
	private long mRowId;
    private Calendar mCalendar;
    
    private LessonProvider lp; 

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		lp = new LessonProvider(getActivity());
		Bundle arguments = getArguments();
		if (arguments != null) {
			mRowId = arguments.getLong(LessonProvider.COLUMN_ROWID);
		}
		if (savedInstanceState != null
	            && savedInstanceState.containsKey(CALENDAR)) {
	        mCalendar = (Calendar) savedInstanceState.getSerializable(CALENDAR);
	    } else {
	        mCalendar = Calendar.getInstance();
	    }
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.lesson_edit,
				container, false);

		mConfirmButton = (Button) rootView.findViewById(R.id.confirm);
		mSubject = (EditText) rootView.findViewById(R.id.subject);
		mTeacher = (EditText) rootView.findViewById(R.id.teacher);
		mTopic = (EditText) rootView.findViewById(R.id.topic);
		mRate = (RatingBar) rootView.findViewById(R.id.rate);

		if (mRowId != 0) {
			Cursor cursor = lp.loadDetail(mRowId);
			mSubject.setText(cursor.getString(cursor.getColumnIndex(LessonProvider.COLUMN_SUBJECT)));
			mTeacher.setText(cursor.getString(cursor.getColumnIndex(LessonProvider.COLUMN_TEACHER)));
			mTopic.setText(cursor.getString(cursor.getColumnIndex(LessonProvider.COLUMN_TOPIC)));
			mRate.setRating(cursor.getFloat(cursor.getColumnIndex(LessonProvider.COLUMN_RATE)));
		}
		
		mConfirmButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				// Some database operations
				ContentValues values = new ContentValues();
				values.put(LessonProvider.COLUMN_ROWID, mRowId);
				values.put(LessonProvider.COLUMN_SUBJECT, mSubject.getText().toString());
				values.put(LessonProvider.COLUMN_TEACHER, mTeacher.getText().toString());
				values.put(LessonProvider.COLUMN_TOPIC, mTopic.getText().toString());
				values.put(LessonProvider.COLUMN_RATE, (int) mRate.getRating());
				values.put(LessonProvider.COLUMN_DATETIME,
						mCalendar.getTimeInMillis());

				if (mRowId == 0) {
					values.remove(LessonProvider.COLUMN_ROWID);
					mRowId = (int) lp.insert(values);
				} else {
					int count = lp.update(values);
				}

				Toast.makeText(getActivity(),
						getString(R.string.task_saved_message),
						Toast.LENGTH_LONG).show();
				
				getActivity().finish();

			}
		});

		return rootView;
	}
}


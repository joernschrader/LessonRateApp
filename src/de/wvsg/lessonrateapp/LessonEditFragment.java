package de.wvsg.lessonrateapp;

import java.util.Calendar;

import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
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
	
	private Button mConfirmButton;
	private EditText mSubject;
	private EditText mTeacher;
	private EditText mTopic;
	private RatingBar mRate;
	private long mRowId;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle arguments = getArguments();
		if (arguments != null) {
			mRowId = arguments.getLong(LessonProvider.COLUMN_ROWID);
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

//		mConfirmButton.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// Some database operations
//				int mRowId = 0;
//				Calendar mCalendar = Calendar.getInstance();
//				ContentValues values = new ContentValues();
//				values.put(LessonProvider.COLUMN_ROWID, mRowId);
//				values.put(LessonProvider.COLUMN_SUBJECT, mSubject.getText().toString());
//				values.put(LessonProvider.COLUMN_TEACHER, mTeacher.getText().toString());
//				values.put(LessonProvider.COLUMN_TOPIC, mTopic.getText().toString());
//				values.put(LessonProvider.COLUMN_RATE, (int) mRate.getRating());
//				values.put(LessonProvider.COLUMN_DATETIME,
//						mCalendar.getTimeInMillis());
//
//				if (mRowId == 0) {
//					Uri itemUri = getActivity().getContentResolver().insert(
//							LessonProvider.CONTENT_URI, values);
//					mRowId = (int) ContentUris.parseId(itemUri);
//				} else {
//					// Update
//				}
//
//				Toast.makeText(getActivity(),
//						getString(R.string.task_saved_message),
//						Toast.LENGTH_LONG).show();
//
//			}
//		});

		return rootView;
	}
}


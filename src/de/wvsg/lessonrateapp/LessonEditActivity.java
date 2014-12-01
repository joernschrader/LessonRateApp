package de.wvsg.lessonrateapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class LessonEditActivity extends FragmentActivity implements OnFinishEditor {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lesson_edit_activity);
		
		Fragment fragment = getSupportFragmentManager().findFragmentByTag
				(LessonEditFragment.DEFAULT_EDIT_FRAGMENT_TAG);
		
		if (fragment == null) {
			fragment = new LessonEditFragment();
			Bundle args = new Bundle();
			args.putLong(LessonProvider.COLUMN_ROWID, getIntent()
					.getLongExtra(LessonProvider.COLUMN_ROWID, 0L));
			fragment.setArguments(args);
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
			transaction.add(R.id.edit_container, fragment,
					LessonEditFragment.DEFAULT_EDIT_FRAGMENT_TAG);
			transaction.commit();
		}
	}

	@Override
	public void finishEditor() {
		finish();
	}
}

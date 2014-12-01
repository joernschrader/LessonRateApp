package de.wvsg.lessonrateapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class LessonListAndEditorActivity extends FragmentActivity implements OnEditLesson, OnFinishEditor {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lesson_list_and_editor);
		LessonListFragment fragment = new LessonListFragment();
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.list_container_tab, fragment,
				LessonListFragment.DEFAULT_LIST_FRAGMENT_TAG);
		transaction.addToBackStack(null);
		transaction.commit();
	}

	@Override
	public void editLesson(long id) {
		LessonEditFragment fragment = new LessonEditFragment();
		Bundle args = new Bundle();
		args.putLong(LessonProvider.COLUMN_ROWID, getIntent()
				.getLongExtra(LessonProvider.COLUMN_ROWID, id));
		fragment.setArguments(args);
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.edit_container_tab, fragment,
				LessonEditFragment.DEFAULT_EDIT_FRAGMENT_TAG);
		transaction.addToBackStack(null);
		transaction.commit();
	}

	@Override
	public void finishEditor() {
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		Fragment previousFragment = fragmentManager.findFragmentByTag(
				LessonEditFragment.DEFAULT_EDIT_FRAGMENT_TAG);
		transaction.remove(previousFragment);
		transaction.commit();	
		
		Fragment listFragment = fragmentManager.findFragmentByTag(
				LessonListFragment.DEFAULT_LIST_FRAGMENT_TAG);
		listFragment.onResume();
	}

}

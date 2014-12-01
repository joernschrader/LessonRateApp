package de.wvsg.lessonrateapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;



public class LessonListActivity extends FragmentActivity implements OnEditLesson {

    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isTablet()) {
        	startActivity(new Intent(this, LessonListAndEditorActivity.class));
        	finish();
        	return;
        }
        setContentView(R.layout.lesson_list);

    }

	private boolean isTablet() {
		int sizeMask = this.getResources().getConfiguration().screenLayout &
				Configuration.SCREENLAYOUT_SIZE_MASK;
		boolean large = (sizeMask == Configuration.SCREENLAYOUT_SIZE_LARGE);
		boolean xlarge = (sizeMask == 4);
		return large || xlarge;
	}


	@Override
	public void editLesson(long id) {
		startActivity(new Intent(this, LessonEditActivity.class).putExtra(
				LessonProvider.COLUMN_ROWID, id));		
	}

}

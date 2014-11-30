package de.wvsg.lessonrateapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;



public class LessonListActivity extends FragmentActivity {

    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isTablet()) {
        	startActivity(new Intent(this, LessonListAndEditorActivity.class));
        	finish();
        	return;
        }
        setContentView(R.layout.lesson_list);
//        if (savedInstanceState == null) {
//            getFragmentManager().beginTransaction()
//                    .add(R.id.container, new PlaceholderFragment())
//                    .commit();
//        }
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.lesson_list, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    /**
//     * A placeholder fragment containing a simple view.
//     */
//    public static class PlaceholderFragment extends Fragment {
//
//        public PlaceholderFragment() {
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.lesson_row, container, false);
//            return rootView;
//        }
//    }
	
	private boolean isTablet() {
		int sizeMask = this.getResources().getConfiguration().screenLayout &
				Configuration.SCREENLAYOUT_SIZE_MASK;
		boolean large = (sizeMask == Configuration.SCREENLAYOUT_SIZE_LARGE);
		boolean xlarge = (sizeMask == 4);
		return large || xlarge;
	}
}

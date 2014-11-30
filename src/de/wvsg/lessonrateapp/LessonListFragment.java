package de.wvsg.lessonrateapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;

public class LessonListFragment extends ListFragment implements LoaderCallbacks<Cursor> {
	
	private SimpleCursorAdapter mAdapter;
	private LessonProvider lp;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		
		lp = new LessonProvider(getActivity());
		
		String[] from = new String[] { LessonProvider.COLUMN_TOPIC, LessonProvider.COLUMN_TEACHER };

		int[] to = new int[] { R.id.text1, R.id.text2 };
		
		mAdapter = new SimpleCursorAdapter(getActivity(),  R.layout.lesson_row, null, from, to, SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		setListAdapter(mAdapter);
		
		getLoaderManager().initLoader(0, null, this);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setEmptyText(getResources().getString(R.string.no_lessons));
		registerForContextMenu(getListView());
		setHasOptionsMenu(true);
	}
	
	@Override
	public void onResume() {
		Log.d("LessonListFragment","onResume()");
		super.onResume();
		mAdapter.swapCursor(lp.getAllLessons());
		//getLoaderManager().restartLoader(0, null, this);

	}

	@Override 
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent i = new Intent(getActivity(), LessonEditActivity.class);
		i.putExtra(LessonProvider.COLUMN_ROWID, id);
		startActivity(i);
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getActivity().getMenuInflater();
		inflater.inflate(R.menu.list_menu_item_long, menu);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.menu_delete:
			AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
			lp.delete(info.id);
			mAdapter.swapCursor(lp.getAllLessons());
			//mAdapter.notifyDataSetChanged();
			return true;
		}
		return super.onContextItemSelected(item);
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.list_menu, menu);
	}
			
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_insert:
			editLesson(0);
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}

	private void editLesson(long id) {
		Intent i = new Intent(getActivity(), LessonEditActivity.class);
		i.putExtra(LessonProvider.COLUMN_ROWID, id);
		startActivity(i);		
	}
	

	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		return lp;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		mAdapter.swapCursor(cursor);
		mAdapter.notifyDataSetChanged();
		Log.d("LOADER MANAGER", "ON LOAD FINISHED");
		
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		mAdapter.swapCursor(null);		
	}

}

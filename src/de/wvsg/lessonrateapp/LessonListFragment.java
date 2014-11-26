package de.wvsg.lessonrateapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class LessonListFragment extends ListFragment {
	
	private ListAdapter mAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String[] items = new String[] { "Foo", "Bar", "Fizz", "Bin" };
		
		mAdapter = new ArrayAdapter<String>(getActivity(), 
				R.layout.lesson_row, R.id.text1, items);
		setListAdapter(mAdapter);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setEmptyText(getResources().getString(R.string.no_lessons));
		registerForContextMenu(getListView());
		setHasOptionsMenu(true);
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
			// TODO Unterricht entfernen
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
}

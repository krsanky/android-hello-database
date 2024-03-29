package org.oldcode.android;

import static android.provider.BaseColumns._ID;
import static org.oldcode.android.Constants.TABLE_NAME;
import static org.oldcode.android.Constants.TIME;
import static org.oldcode.android.Constants.TITLE;
import android.app.ListActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

public class Events extends ListActivity {

	private EventsData events;
	
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        events = new EventsData(this);
        
        try {
        	addEvent("hello android?");
        	Cursor cursor = getEvents();
        	showEvents(cursor);
        } finally {
        	events.close();
        }
    
    }

	private void addEvent(String string) {
		SQLiteDatabase db = events.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(TIME, System.currentTimeMillis());
		values.put(TITLE, string);
		db.insertOrThrow(TABLE_NAME, null, values);
	}
	
	private static String[] FROM = { _ID, TIME, TITLE };
	private static String ORDER_BY = TIME + " DESC";
	private Cursor getEvents() {
		SQLiteDatabase db = events.getReadableDatabase();
		Cursor cr = db.query(TABLE_NAME, FROM, null, null, null, null, ORDER_BY);
		startManagingCursor(cr);
		return cr;
	}
	
	private static int[] TO = { R.id.rowid, R.id.time, R.id.title, };
	private void showEvents(Cursor cursor) {
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, 
															  R.layout.item, 
															  cursor, 
															  FROM, 
															  TO);
		setListAdapter(adapter);
	}

	



}


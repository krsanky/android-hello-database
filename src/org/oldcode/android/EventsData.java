package org.oldcode.android;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;

import static org.oldcode.android.Constants.TABLE_NAME;
import static org.oldcode.android.Constants.TIME;
import static org.oldcode.android.Constants.TITLE;

public class EventsData extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "events.db";
	private static final int DATABASE_VERSION = 1;

	public EventsData(Context context, String name, CursorFactory factory,
			          int version) 
	{
		super(context, name, factory, version);
	}

	/* Create a helper object for the Events database */
	public EventsData(Context ctx) {
		super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + TABLE_NAME + 
				   " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
				        TIME + " INTEGER," + TITLE + " TEXT NOT NULL);"); 
	}

	@Override public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion) { 
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME); 
		onCreate(db); 
	} 
	
}

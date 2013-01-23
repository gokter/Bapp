package com.flyingh.bapp;

import android.app.Activity;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	protected static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final Uri uri = Uri
				.parse("content://com.flyingh.providers.PersonProvider/person");
		getContentResolver().registerContentObserver(uri, true,
				new ContentObserver(new Handler()) {
					@Override
					public void onChange(boolean selfChange) {
						Cursor cursor = getContentResolver().query(uri, null,
								null, null, "id desc limit 1");
						if (cursor.moveToFirst()) {
							String msg = cursor.getString(cursor
									.getColumnIndex("name"))
									+ "\t"
									+ cursor.getInt(cursor
											.getColumnIndex("age"));
							Log.i(TAG, msg);
						}
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}

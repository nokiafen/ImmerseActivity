package com.example.chl.myapplication;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;
import android.util.Log;

/**
 * Created by chenhailin on 2017/9/5.
 */

public class ProviderTest extends AndroidTestCase {
    private final static String TAG = "AccessContentProvider";

    public void testSave() throws Throwable {
        ContentResolver resolver = this.getContext().getContentResolver();
        Uri insertUri = Uri.parse("content://com.faith.providers.personprovider/student");
        ContentValues values = new ContentValues();
        values.put("name", "meijun");
        values.put("age", "15");
        values.put("phone", "199893");
        Uri uri = resolver.insert(insertUri, values);
        Log.d(TAG, uri.toString());
    }

    public void testUpdate() throws Throwable {
        ContentResolver resolver = this.getContext().getContentResolver();
        Uri updateUri = Uri.parse("content://com.faith.providers.personprovider/student/6");
        ContentValues values = new ContentValues();
        values.put("name", "meijun");
        values.put("age", "35");
        values.put("phone", "1998933243");
        resolver.update(updateUri, values, null, null);
    }

    public void testQuery() throws Throwable {
        ContentResolver resolver = this.getContext().getContentResolver();
        Uri queryUri = Uri.parse("content://com.faith.providers.personprovider/student");
        Cursor cursor = resolver.query(queryUri, null, null, null, null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            short age = cursor.getShort(cursor.getColumnIndex("age"));
            Log.d(TAG, "id = " + id + ",name = " + name
                    + ",phone = " + phone + ",age = " + age);
        }
    }

    public void testDelete() throws Throwable {
        ContentResolver resolver = this.getContext().getContentResolver();
        Uri deleteUri = Uri.parse("content://com.faith.providers.personprovider/student/6");
        resolver.delete(deleteUri, null, null);
    }

    public void testType() throws Throwable {
        ContentResolver resolver = this.getContext().getContentResolver();
        Uri typeUri = Uri.parse("content://com.faith.providers.personprovider/student/6");
        String type = resolver.getType(typeUri);
        Log.d(TAG, type);
    }
}

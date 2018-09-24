package com.example.android.inventoryapp1.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.inventoryapp1.data.CafeContract.CafeEntry;

/**
 * Database helper for Inventory app. Manages database creation and version management.
 */
public class CafeDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = CafeDbHelper.class.getSimpleName();

    /** Name of the database file */
    private static final String DATABASE_NAME = "inventory.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link CafeDbHelper}.
     *
     * @param context of the app
     */
    public CafeDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the inventory table
        // TODO correct mistake
        String SQL_CREATE_INVENTORY_TABLE = "CREATE TABLE " + CafeEntry.TABLE_NAME + " ("
                + CafeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CafeEntry.COLUMN_PRODUCT_NAME + " INTEGER NOT NULL, "
                + CafeEntry.COLUMN_PRICE + " INTEGER NOT NULL, "
                + CafeEntry.COLUMN_QUANTITY + " INTEGER NOT NULL DEFAULT 0, "
                + CafeEntry.COLUMN_SUPPLIER_NAME + " TEXT NOT NULL, "
                + CafeEntry.COLUMN_SUPPLIER_PHONE + " TEXT NOT NULL);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_INVENTORY_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}

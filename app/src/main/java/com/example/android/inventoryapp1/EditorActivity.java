package com.example.android.inventoryapp1;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.android.inventoryapp1.data.CafeContract.CafeEntry;
import com.example.android.inventoryapp1.data.CafeDbHelper;


/**
 * Allows user to create a new inventory element or edit an existing one.
 */
public class EditorActivity extends AppCompatActivity {

    /** EditText field to enter the inventory's element name */
    private Spinner mNameSpinner;

    /** EditText field to enter the inventory's element price */
    private EditText mPriceEditText;

    /** EditText field to enter the inventory's element quantity */
    private EditText mQuantityEditText;

    /** EditText field to enter the inventory's element supplier name */
    private EditText mSupplierNameEditText;

    /** EditText field to enter the inventory's element supplier phone */
    private EditText mSupplierPhoneEditText;



    /**
     * element name of the invetory element. The possible valid values are in the CafeContract.java file:
     * {@link CafeEntry#CAFE_NEVADO}, {@link CafeEntry#CAFE_TOLIMA}, or
     * {@link CafeEntry#CAFE_CALDAS}.
     */
    private int mProductName = CafeEntry.CAFE_NEVADO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        // Find all relevant views that we will need to read user input from
        mNameSpinner = (Spinner) findViewById(R.id.spinner_product_name);
        mPriceEditText = (EditText) findViewById(R.id.edit_price);
        mQuantityEditText = (EditText) findViewById(R.id.edit_quantity);
        mSupplierNameEditText = (EditText) findViewById(R.id.edit_supplier_name);
        mSupplierPhoneEditText = (EditText) findViewById(R.id.edit_supplier_phone);

        setupSpinner();
    }

    /**
     * Setup the dropdown spinner that allows the user to select the name of the inventory element.
     */
    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_product_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mNameSpinner.setAdapter(genderSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.cafe_nevado))) {
                        mProductName = CafeEntry.CAFE_NEVADO;
                    } else if (selection.equals(getString(R.string.cafe_tolima))) {
                        mProductName = CafeEntry.CAFE_TOLIMA;
                    } else {
                        mProductName = CafeEntry.CAFE_CALDAS;
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mProductName = CafeEntry.CAFE_NEVADO;
            }
        });
    }

    /**
     * Get user input from editor and save new element into database.
     */
    private void insertCafe() {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space

        String priceString = mPriceEditText.getText().toString().trim();
        int price = Integer.parseInt(priceString);

        String quantityString = mQuantityEditText.getText().toString().trim();
        int quantity = Integer.parseInt(quantityString);

        String supplierNameString = mSupplierNameEditText.getText().toString().trim();
        String supplierPhoneString = mSupplierPhoneEditText.getText().toString().trim();

        // Create database helper
        CafeDbHelper mDbHelper = new CafeDbHelper(this);

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and pet attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(CafeEntry.COLUMN_PRODUCT_NAME, mProductName);
        values.put(CafeEntry.COLUMN_PRICE, priceString);
        values.put(CafeEntry.COLUMN_QUANTITY, quantityString);
        values.put(CafeEntry.COLUMN_SUPPLIER_NAME, supplierNameString);
        values.put(CafeEntry.COLUMN_SUPPLIER_PHONE, supplierPhoneString);

        // Insert a new row for pet in the database, returning the ID of that new row.
        long newRowId = db.insert(CafeEntry.TABLE_NAME, null, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving element", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "element saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Save element to database
                insertCafe();
                // Exit activity
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                // Do nothing for now
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

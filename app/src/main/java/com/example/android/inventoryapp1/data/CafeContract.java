package com.example.android.inventoryapp1.data;

import android.provider.BaseColumns;

/**
 * API Contract for the Inventory app.
 */
public final class CafeContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private CafeContract() {}

    /**
     * Inner class that defines constant values for the inventory database table.
     * Each entry in the table represents a single inventory element.
     */
    public static final class CafeEntry implements BaseColumns {

        /** Name of database table for inventory */
        public final static String TABLE_NAME = "Cafe Inventory";

        /**
         * Unique ID number for the inventory element (only for use in the database table).
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * PRODUCT NAME of the inventory element.
         *
         * The only possible values are {@link #CAFE_NEVADO}, {@link #CAFE_TOLIMA},
         * or {@link #CAFE_CALDAS}.
         * Type: INTEGER
         */
        public final static String COLUMN_PRODUCT_NAME ="name";

        /**
         * Possible values for the Product name of the inventory element.
         */
        public static final int CAFE_NEVADO= 0;
        public static final int CAFE_TOLIMA = 1;
        public static final int CAFE_CALDAS = 2;

        /**
         * Price of the inventory element.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_PRICE = "price";

        /**
         * Quantity of the inventory element.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_QUANTITY = "quantity";


        /**
         * Supplier Name of the inventory element.
         *
         * Type: TEXT
         */
        public final static String COLUMN_SUPPLIER_NAME = "supplier name";

        /**
         * Supplier Phone of the inventory element.
         *
         * Type: TEXT
         */
        public final static String COLUMN_SUPPLIER_PHONE = "supplier phone";

    }
}

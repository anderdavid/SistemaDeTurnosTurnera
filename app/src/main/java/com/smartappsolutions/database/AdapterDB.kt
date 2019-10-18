package com.smartappsolutions.database


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.sql.SQLException


class AdapterDB(val context: Context) {

    companion object{
        private val TAG = "AdapterDB"

        private val DATABASE_NAME = "turnera_db"
        private val VERSION = 1

        val TABLE_LOGIN_STATUS = "login_status"
        val ID_LOGIN_STATUS = "_id"
        val KEY_STATUS = "status"

        private val CREATE_TABLE_LOGIN_STATUS =
            "create table " + TABLE_LOGIN_STATUS + "(" + ID_LOGIN_STATUS + " integer primary key autoincrement," +
                    " " + KEY_STATUS + " text);"

    }

    private var mDatabaseHelper: DatabaseHelper? =null
    private var mSqLiteDatabase: SQLiteDatabase? = null

    var mContext:Context


    init {
        mContext =context
        mDatabaseHelper = DatabaseHelper(mContext)

    }

    private class DatabaseHelper internal constructor(context: Context) :
        SQLiteOpenHelper(context, DATABASE_NAME, null, VERSION) {

        init {
            Log.d(TAG, DATABASE_NAME + " onInit()");
        }

        override fun onCreate(db: SQLiteDatabase) {
            db.execSQL(CREATE_TABLE_LOGIN_STATUS)
            Log.d(TAG,"onCreate table $CREATE_TABLE_LOGIN_STATUS")
        }

        override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
            Log.d(TAG, "onUpgrade")
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_LOGIN_STATUS)
            onCreate(db)
        }

    }

    @Throws(SQLException::class)
    fun open(): AdapterDB {
        mSqLiteDatabase = mDatabaseHelper!!.writableDatabase

        return this
    }


    @Throws(SQLException::class)
    fun close() {

        mDatabaseHelper!!.close()
    }

    /*fun dropDatabase() {
        mSqLiteDatabase!!.execSQL("DROP DATABASE $DATABASE_NAME")
    }*/

    fun dropTable(table: String) {
        mSqLiteDatabase!!.execSQL("DROP TABLE $table")
    }

    fun truncateTable(table: String) {
        mSqLiteDatabase!!.execSQL("DELETE FROM $table")
    }

    fun createLoginStatus(status: String): Long {

        val initialValues = ContentValues()
        initialValues.put(KEY_STATUS, status)

        return mSqLiteDatabase!!.insert(TABLE_LOGIN_STATUS, null, initialValues)
    }

    fun deleteLoginStatus(id: Long): Boolean {
        Log.d(TAG, "delete LoginStatus")
        return mSqLiteDatabase!!.delete(TABLE_LOGIN_STATUS, "$ID_LOGIN_STATUS=$id", null) > 0
    }

    fun fetchLoginStatus(id: String): Cursor {
        val mCursor =
            mSqLiteDatabase!!.rawQuery("SELECT *FROM $TABLE_LOGIN_STATUS WHERE $ID_LOGIN_STATUS='$id'", null)
        mCursor?.moveToFirst()
        return mCursor

    }

    fun fetchAllLoginStatus(): Cursor? {
        val mCursor = mSqLiteDatabase!!.rawQuery("SELECT * FROM $TABLE_LOGIN_STATUS", null)
        mCursor?.moveToFirst()
        return mCursor

    }




}
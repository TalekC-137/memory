package com.scollon.markk

import android.content.ContentValues
import android.content.Context
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandlerVisual(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "visualDatabase"

        private val TABLE_CONTACTS = "visualTable"

        private val KEY_ID = "_id"
        private val KEY_SCORE = "score"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_SCORE + " INT"
                + ")")
        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_CONTACTS")
        onCreate(db)
    }
    fun addEmployee(emp: RecordModel): Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(KEY_SCORE, emp.score)

        val success = db.insert(TABLE_CONTACTS, null, contentValues)

        db.close()
        return success
    }

    fun getOne(id: Int): RecordModel? {
        val db = this.writableDatabase
        val query = arrayOf<String>(KEY_ID, KEY_SCORE)
        val cursor = db.query(
            TABLE_CONTACTS,
            query,
            KEY_ID.toString() + "=?",
            arrayOf(id.toString()),
            null,
            null,
            null,
            null
        )
        cursor?.moveToFirst()
        return RecordModel(
            cursor!!.getInt(0),
            cursor.getInt(1)
        )
    }

    fun getBmiCount(): Int {
        val db = this.readableDatabase
        val count = DatabaseUtils.queryNumEntries(db, TABLE_CONTACTS)
        db.close()
        var countInt = count.toInt()
        return countInt
    }

    fun getBiggestInTheColumn(): Int {

        val db = this.readableDatabase
        return DatabaseUtils.longForQuery(db, "SELECT MAX(score) FROM $TABLE_CONTACTS", null)
            .toInt()
    }
}
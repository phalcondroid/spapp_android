package Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlLiteHelper extends SQLiteOpenHelper {
 
    public SqlLiteHelper(Context contexto, String nombre, CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE session (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "logisticresource_id INTEGER, " +
                "uuid TEXT" +
        ")");

        db.execSQL("CREATE TABLE shipping (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "shipping_id TEXT, " +
                "origin_address TEXT, " +
                "destiny_address TEXT, " +
                "origin_latitude TEXT, " +
                "origin_longitude TEXT, " +
                "destiny_latitude TEXT, " +
                "destiny_longitude TEXT, " +
                "amount_declared TEXT, " +
                "service TEXT, " +
                "cost TEXT, " +
                "description TEXT"+
        ")");

        db.execSQL("CREATE TABLE client ("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "shipping_id TEXT,"+
                "client_id TEXT," +
                "client_type TEXT," +
                "identify TEXT," +
                "name TEXT," +
                "email TEXT," +
                "phone TEXT" +
        ")");

        db.execSQL("CREATE TABLE employee ("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "image TEXT," +
                "email TEXT," +
                "movil_phone TEXT" +
        ")");

        db.execSQL("CREATE TABLE vehicle ("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "model TEXT," +
                "brand TEXT," +
                "color TEXT," +
                "plate TEXT," +
                "image TEXT" +
        ")");

        db.execSQL("CREATE TABLE routing (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "lat TEXT," +
                "lng TEXT," +
                "type TEXT," +
                "distance TEXT," +
                "shipping_id TEXT" +
        ")");

        db.execSQL("CREATE TABLE chat (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "client_id INTEGER, " +
                "chat TEXT, " +
                "hour TEXT, " +
                "sentby TEXT " +
        ")");
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
    	
    }
}
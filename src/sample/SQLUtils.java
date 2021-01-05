package sample;
import DataBase.DB;

public class SQLUtils {
    public static void insert(){
        DB.insertSQL("insert into tblPlaylist values('g42');");
    }
}

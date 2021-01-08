package DataBase;

import DataBase.DBSetter.DB;
import DataBase.Opration.Music;

import java.util.ArrayList;

public class SQL {
    /**
     * Queries the database with a provided select statement argument
     * @param query the select statement
     * @return a string ArrayList containing the output of the query
     */
    public static ArrayList<String> selectSQL(String query){
        ArrayList<String> output = new ArrayList<>();

        DB.selectSQL(query);

        do{
            String data = DB.getDisplayData();
            if (data.equals(DB.NOMOREDATA)){
                break;
            }else{
                data = data.trim();
                output.add(data);
            }
        } while(true);

        return output;
    }


    /**
     * This is working for get the music file path from DB
     * @return Path : the music file path as String
     */
    public static String getPath(){
        Music pathOfMusic = new Music();
        String getMusicPath = pathOfMusic.getPath();

        return getMusicPath;
    }
}

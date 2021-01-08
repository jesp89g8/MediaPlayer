package DataBase;

import DataBase.DBSetter.DB;
import java.util.ArrayList;

/**
 * @ Group Jesper Raheela Zia and Fei
 * @ create 2021-01-05-08.39
 * @ grade CS20_EASV_SØNDERBORG
 * @ Description This is the DB-Connecter which connect the Database and get result.
 * @ Version 0.1
 *
 */

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
        int getMusicID = 2;

        DB.selectSQL("select fldPath from table_music where fldMusicID = " + getMusicID);
        return DB.getData();
    }
}

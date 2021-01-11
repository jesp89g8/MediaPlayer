package DataBase;

import DataBase.DBSetter.DB;
import DataBase.Opration.Music;

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
        ArrayList<String> out = new ArrayList<>();

        DB.selectSQL(query);

        do{
            String data = DB.getDisplayData();
            if (data.equals(DB.NOMOREDATA)){
                break;
            }else{
                data = data.trim();
                out.add(data);
            }
        } while(true);

        return out;
    }

    /**
     * Queries the database with a provided select statement argument, this
     * returns a multi row arraylist, for when more records are needed
     * @param query the select statement
     * @return a multi row arraylist
     */
    public static ArrayList<ArrayList<String>> selectSQLMulti(String query){
        ArrayList<ArrayList<String>> out = new ArrayList<>();
        out.add(new ArrayList<>());
        int row = 0;

        DB.selectSQL(query);

        do{
            String data = DB.getDisplayData();
            if (data.equals(DB.NOMOREDATA)){
                break;
            }else{
                if(data.contains("\n")){
                    data = data.trim();
                    out.get(row).add(data);
                    row++;
                    out.add(new ArrayList<>());
                }
                else{
                    data = data.trim();
                    out.get(row).add(data);
                }
            }
        } while(true);

        return out;
    }
}

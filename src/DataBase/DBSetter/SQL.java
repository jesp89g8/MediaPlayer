package DataBase.DBSetter;

import java.util.ArrayList;

/**
 * @author  Jesper Raheela Zia and Fei
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

        DB.selectSQL(query);

        do{
            String data = DB.getDisplayData();
            if (data.equals(DB.NOMOREDATA)){
                break;
            }else{
                if(data.contains("\n")){
                    data = data.trim();
                    out.get(out.size() - 1).add(data);
                    out.add(new ArrayList<>());
                }
                else{
                    data = data.trim();
                    out.get(out.size() - 1).add(data);
                }
            }
        } while(true);

        out.remove(out.size() - 1);

        return out;
    }
}

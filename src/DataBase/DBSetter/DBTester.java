package DataBase.DBSetter;

/**
 * @author tha
 * @ Group Jesper Raheela Zia and Fei
 * @ Override by Fei Gu
 * @ create 2021-01-05-08.39
 * @ grade CS20_EASV_SØNDERBORG
 * @ Description This is the DB Tester which testing for connect and modify the Database and get result.
 * @ Version 0.1
 *
 */
public class DBTester {
    
    public static void main (String [] args){

        DB.selectSQL("Select * from table_music");
        
        do{
           String data = DB.getDisplayData();
           if (data.equals(DB.NOMOREDATA)){
               break;
           }else{
               System.out.print(data);
           }   
        } while(true);
        
        

        DB.selectSQL("Select fldMusicID from table_music");
        
        System.out.println("numberOfColumns="+DB.getNumberOfColumns());
        do{
           String data = DB.getData();
           if (data.equals(DB.NOMOREDATA)){
               break;
           }else{
               System.out.print(data);
           }   
        } while(true);
       
    }
    
}

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

        DB.insertSQL("Insert into table_music values('16','name','artist','type','offer','path','language');");
        DB.selectSQL("Select * from table_music");
        
        do{
           String data = DB.getDisplayData();
           if (data.equals(DB.NOMOREDATA)){
               break;
           }else{
               System.out.print(data);
           }   
        } while(true);
        
        
        DB.deleteSQL("Delete from table_music where fldMusicID like '16';");
        
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

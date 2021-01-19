package DataBase.DBSetter;

/**
 * @author tha
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

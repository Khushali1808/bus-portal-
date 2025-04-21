import java.sql.*;
import java.util.Scanner;
class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException() {
        super("User already exits");
    }
}

class idDoesNotExistsException extends Exception{
    public idDoesNotExistsException(){
        super("Id does not existss");
    }
}
class noSuchBusException extends Exception{
    public noSuchBusException(){
        super("No such bus found");
    }
}
class invalidPassException extends Exception{
    public invalidPassException(){
        super("Invalid password syntax you use(!,_,@) as special characters");
    }
}
class DataManager{
    String url="jdbc:mysql://localhost:3306/project";
    String userName="root";
    String password="";
    String driverName="com.mysql.jdbc.Driver";

    static Connection con;
    static String role;

    Scanner sc=new Scanner(System.in);
    //adds User
    void addUser(String username,String number,String pass,String email) throws Exception{
        System.out.println();
        int flag=0;
        Class.forName(driverName);
        con=DriverManager.getConnection(url, userName, password);
        String sql1="Select email from users";
        PreparedStatement pst1=con.prepareStatement(sql1);
        ResultSet rs=pst1.executeQuery();
        while(rs.next()){
            if(rs.getString("email").equals(email)){
                flag=1;
            }
        }
        if(flag==0){
            String sql="Insert into users (username,number,password,email) values (?,?,?,?)";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,username);
            pst.setString(2, number);
            pst.setString(3,pass);
            pst.setString(4, email);
            int r=pst.executeUpdate();
            if(r>0){
                System.out.println("Registration Successfull");
            }else{
                System.out.println("Registration Unsuccessfull");
            }
        }else{
            try {
                throw new UserAlreadyExistsException();
            } catch (UserAlreadyExistsException e) {
                System.out.println(e);
            }
        }
    }
    //Deletes user
    void delUser(int id) throws Exception{
        System.out.println();
        int flag=0;
        Class.forName(driverName);
        con=DriverManager.getConnection(url, userName, password);
        String sql1="Select id from users";
        PreparedStatement pst1=con.prepareStatement(sql1);
        ResultSet rs=pst1.executeQuery();
        while(rs.next()){
            if(rs.getInt("id")==(id)){
                flag=1;
            }
        }
        if(flag==1){
            String sql="Delete from users where id=?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setInt(1, id);
            int r=pst.executeUpdate();
            if(r>0){
                System.out.println("Deletion Completed");
            }else{
                System.out.println("Deletion Incomplete");
            }
        }else{
            try {
                throw new idDoesNotExistsException();
            } catch (idDoesNotExistsException e) {
                System.out.println(e);
            }
        }
        
    }
    //updates username
    void updateUsername(int id,String name) throws Exception{
        System.out.println();
        int flag=0;
        Class.forName(driverName);
        con=DriverManager.getConnection(url, userName, password);
        String sql1="Select id from users";
        PreparedStatement pst1=con.prepareStatement(sql1);
        ResultSet rs=pst1.executeQuery();
        while(rs.next()){
            if(rs.getInt("id")==(id)){
                flag=1;
            }
        }
        if(flag==1){
            String sql="update users set username = ? where id=?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,name);
            pst.setInt(2,id);
            int r=pst.executeUpdate();
            if(r>0){
                System.out.println("username updated");
            }else{
                System.out.println("username not updated");
            }
        }else{
            try {
                throw new idDoesNotExistsException();
            } catch (idDoesNotExistsException e) {
                System.out.println(e);
            }
        }
    }
    //updates mobile number of user
    void updateNumber(int id,String number) throws Exception{
        System.out.println();
        int flag=0;
        Class.forName(driverName);
        con=DriverManager.getConnection(url, userName, password);
        String sql1="Select id from users";
        PreparedStatement pst1=con.prepareStatement(sql1);
        ResultSet rs=pst1.executeQuery();
        while(rs.next()){
            if(rs.getInt("id")==(id)){
                flag=1;
            }
        }
        if(flag==1){     
            String sql="update users set number = ? where id="+id;
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,number);
            int r=pst.executeUpdate();
            if(r>0){
                System.out.println("mobile number updated");
            }else{
                System.out.println("updation unsuccessfull");
            }
        }else{
            try {
                throw new idDoesNotExistsException();
            } catch (idDoesNotExistsException e) {
                System.out.println(e);
            }
        }
    }
    //updates email of user
    void updateEmail(int id,String email) throws Exception{
        System.out.println();
        int flag=0;
        Class.forName(driverName);
        con=DriverManager.getConnection(url, userName, password);
        String sql1="Select id from users";
        PreparedStatement pst1=con.prepareStatement(sql1);
        ResultSet rs=pst1.executeQuery();
        while(rs.next()){
            if(rs.getInt("id")==(id)){
                flag=1;
            }
        }
        if(flag==1){ 
        String sql="update users set email = ? where id="+id;
        PreparedStatement pst=con.prepareStatement(sql);
        pst.setString(1,email);
        int r=pst.executeUpdate();
        if(r>0){
            System.out.println("email updated");
        }else{
            System.out.println("updation unsuccessfull");
        }    
        }else{
            try {
                throw new idDoesNotExistsException();
            } catch (idDoesNotExistsException e) {
                System.out.println(e);
            }
        }
    }
    //adds bus
    void addBus()throws Exception{
        System.out.println();
        Class.forName(driverName);
        con=DriverManager.getConnection(url, userName, password);
        con.setAutoCommit(false);
        Route r=new Route();
        if(r.addRoutes()==0){
            String sql="Insert into bus_details(busName,busFrom,busTo,Route) values(?,?,?,?)";
            PreparedStatement pst=con.prepareStatement(sql);
            System.out.print("Enter the bus name : ");
            String name=sc.next();
            sc.nextLine();
            System.out.print("Enter Start Stop : ");
            String start=sc.nextLine();
            sc.nextLine();
            System.out.print("Enter End Stop : ");
            String stop=sc.nextLine();
            pst.setString(1, name);
            pst.setString(2, start);
            pst.setString(3, stop);
            pst.setClob(4, r.fr);
            pst.execute();
            int choice=0;
            System.out.println("1. Add Route in database");
            System.out.println("2. Renter the bus Route");
            System.out.println("3.exit.....");
            System.out.print("Enter Choice : ");
            choice=sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    con.commit();
                    break;
                case 2:
                    con.rollback();
                    addBus();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default : 
                    System.out.println("Enter valid Choice...");
                    break;
            }
        }else{
            r.addRoutes();
        }
    }
    //adds return bus of the existing bus by id
    void addReturnBus()throws Exception{
        System.out.println();
        // Class.forName(driverName);
        con=DriverManager.getConnection(url, userName, password);
        Route r=new Route();
        r.ReturnRoute();
        String sql="Insert into bus_details(busName,busFrom,busTo,Route) values(?,?,?,?)";
        PreparedStatement pst=con.prepareStatement(sql);
        con.setAutoCommit(false);
        System.out.print("Enter the bus name : ");
        String name=sc.next();
        sc.nextLine();
        System.out.print("Enter Start Stop : ");
        String start=sc.nextLine();
        System.out.print("Enter End Stop : ");
        String stop=sc.nextLine();
        pst.setString(1, name);
        pst.setString(2, start);
        pst.setString(3, stop);
        pst.setClob(4, r.fr);
        pst.execute();
        int choice=0;
        System.out.println("1. Add Route in database");
        System.out.println("2. Renter the bus Route");
        System.out.println("3.exit.....");
        System.out.print("Enter Choice : ");
        choice=sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                con.commit();
                break;
            case 2:
                con.rollback();
                addReturnBus();
                break;
            case 3:
                System.out.println("Exiting....");
                break;
            default : 
                System.out.println("Enter valid Choice...");
                break;
        }
        
    }
    //updates bus name
    void updatebusNumber(int id,String name)throws Exception{
        System.out.println();
        int flag=0;
        Class.forName(driverName);
        con=DriverManager.getConnection(url, userName, password);
        String sql1="Select busId from bus_details";
        PreparedStatement pst1=con.prepareStatement(sql1);
        ResultSet rs=pst1.executeQuery();
        while(rs.next()){
            if(rs.getInt("busId")==(id)){
                flag=1;
            }
        }
        if(flag==1){ 
            String sql="Update bus_details set busName=? where busId=? ";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,name);
            pst.setInt(2,id);
            int r=pst.executeUpdate();
            if(r>0){
                System.out.println("BusNumber updated Successfully");
            }else{
                System.out.println("BusNumber not update");
            }
        }else{
            try {
                throw new idDoesNotExistsException();
            } catch (idDoesNotExistsException e) {
                System.out.println(e);
            }
        }
    }
    //updates entire route
    void updatebusRoute(int id)throws Exception{
        System.out.println();
        Route r=new Route();
        int flag=0;
        Class.forName(driverName);
        con=DriverManager.getConnection(url, userName, password);
        String sql1="Select busId from bus_details";
        PreparedStatement pst1=con.prepareStatement(sql1);
        ResultSet rs=pst1.executeQuery();
        while(rs.next()){
            if(rs.getInt("busId")==(id)){
                flag=1;
            }
        }
        if(flag==1){  
            r.addRoutes();
            r.DisplayEnteredRoute();
            String sql="Update bus_details set Route=? where busId=?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setClob(1,r.fr);
            pst.setInt(2, id);
            int choice=0;
            System.out.println("1. Add Route in database");
            System.out.println("2. Renter the bus Route");
            System.out.println("3.exit.....");
            System.out.print("Enter Choice : ");
            choice=sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    con.commit();
                    break;
                case 2:
                    con.rollback();
                    updatebusRoute(id);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default : 
                    System.out.println("Enter valid Choice...");
                    break;
            }
        }else{
            try {
                throw new idDoesNotExistsException();
            } catch (idDoesNotExistsException e) {
                System.out.println(e);
            }
        }
    }
    //Updates a specific bus stop from route
    void updateBusStop(int id) throws Exception{
        System.out.println();
        Route r=new Route();
        int flag=0;
        Class.forName(driverName);
        con=DriverManager.getConnection(url, userName, password);
        String sql1="Select * from bus_details";
        PreparedStatement pst1=con.prepareStatement(sql1);
        ResultSet rs=pst1.executeQuery();
        r.showRoute(id);
        while(rs.next()){
            if(rs.getInt("busId")==(id)){
                flag=1;  
            }
        }
        if(flag==1){
            DataManager.con.setAutoCommit(false);
            String sql2="Update bus_details set Route=? where busId=?";
            PreparedStatement pst2=con.prepareStatement(sql2);
            pst2.setClob(1,r.fr1);
            pst2.setInt(2, id);
            int choice=0;
            System.out.println("1. Add Route in database");
            System.out.println("2. Renter the bus Route");
            System.out.println("3.exit.....");
            System.out.print("Enter Choice : ");
            choice=sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    con.commit();
                    break;
                case 2:
                    con.rollback();
                    updateBusStop(id);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default : 
                    System.out.println("Enter valid Choice...");
                    break;
        }
    }
       if(flag==0){
            try {
                throw new idDoesNotExistsException();
            } catch (idDoesNotExistsException e) {
                System.out.println(e);
            }
        }
    
    }
    //Deletes Bus by its id
    void delBus(int id) throws Exception{
        System.out.println();
        int flag=0;
        Class.forName(driverName);
        con=DriverManager.getConnection(url, userName, password);
        String sql1="Select busId from bus_details";
        PreparedStatement pst1=con.prepareStatement(sql1);
        ResultSet rs=pst1.executeQuery();
        while(rs.next()){
            if(rs.getInt("busId")==(id)){
                flag=1;
            }
        }
        if(flag==1){
            String sql="Delete from buses where busId=? and busName=? ";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setInt(1, id);
            int r=pst.executeUpdate();
            if(r>0){
                System.out.println("Bus added Successfully");
            }else{
                System.out.println("Bus not added");
            }
        }else{
            try {
                throw new idDoesNotExistsException();
            } catch (idDoesNotExistsException e) {
                System.out.println(e);
            }
        }
    }
    //Displays All bus
    void BusDisplay() throws Exception{
        System.out.println();
        con=DriverManager.getConnection(url, userName,password);
        String sql="Select * from bus_details";
        PreparedStatement pst=con.prepareStatement(sql);
        ResultSet rs=pst.executeQuery();
        while(rs.next()){
            System.out.println(rs.getString("busName"));
            System.out.println(rs.getString("BusFrom"));
            System.out.println(rs.getString("BusTo"));
            System.out.println();
        }
    }

    //Bus displays by start and stop input by user
    void BusDisplayByStartStop(String start, String stop) throws Exception {
        System.out.println();
        con = DriverManager.getConnection(url, userName, password);
        String sql = "SELECT * FROM bus_details WHERE Route LIKE CONCAT('%', ?, '%') AND Route LIKE CONCAT('%', ?, '%') AND INSTR(Route, ?) < INSTR(Route, ?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, start);
        pst.setString(2, stop);
        pst.setString(3, start);
        pst.setString(4, stop);
        ResultSet rs = pst.executeQuery();
        int i = 0;
        while (rs.next()) {
            System.out.println(rs.getString("busName"));
            System.out.println(rs.getString("busFrom"));
            System.out.println(rs.getString("busTo"));
          
            System.out.println();
            i = 1;
        }
        if (i == 0) {
            System.out.println("No such bus");
        }   
    }
    //Displays bus by its name
    void BusDisplayByName(String name)throws Exception{
        System.out.println();
        int flag=0;
        con=DriverManager.getConnection(url, userName,password);
        String sql="Select * from bus_details where busName=?";
        PreparedStatement pst=con.prepareStatement(sql);
        pst.setString(1,name);
        ResultSet rs=pst.executeQuery();
        while(rs.next()){
            if(rs.getString("busName").equals(name)){
                flag=1;  
                System.out.println(rs.getString("busName"));
                System.out.println(rs.getString("BusFrom"));
                System.out.println(rs.getString("BusTo"));
                System.out.println();
            }
        }
        if(flag==0){
            try{
                throw new noSuchBusException();
            }catch(noSuchBusException e){
                System.out.println(e);
            }
        }
    }
    //verifies account if gmail is valid in login
    int verifyAcc(String email,String pass) throws Exception{
        System.out.println();
        Class.forName(driverName);
        con=DriverManager.getConnection(url, userName, password);
        String sql="Select * from users";
        Statement st=DataManager.con.createStatement();
        ResultSet rs=st.executeQuery(sql);
        int flag=0;
        System.out.println(email);
        while(rs.next()){

            if(rs.getString("email").equals(email)){
                role=rs.getString("role")+"";
                flag=1;
            }
        }
        return flag;
    }

    int verifyPass(String pass,String pass1) throws Exception{
        System.out.println();
        int flag=0;
       if(pass.equals(pass1)){
        char s[]=pass.toCharArray();
        for(char y:s){
            System.out.println(y);
            if((y>=65&&y<=90)||(y>=49&&y<=57)||(y>=95&&y<=122)||y==33||y==64||y==95){
                flag=1;
            }else{
                try {
                    flag=0;
                    throw new invalidPassException();
                } catch (invalidPassException e) {
                    System.out.println(e);
                }
                break;
            } 
        }
       }
        return flag;
    }
}
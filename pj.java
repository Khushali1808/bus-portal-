import java.io.*;
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
    String driverName="com.mysql.cj.jdbc.Driver";

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
           // sc.nextLine();
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
            // System.out.println("Number of stops: " + rs.getInt("stopCount"));
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
// Customized exception for insertFirst method
class EmptyStringException extends Exception {
    public EmptyStringException(String message) {
        super(message);
    }
}

// Customized exception for deleteValue method
class ValueNotFoundException extends Exception {
    public ValueNotFoundException(String message) {
        super(message);
    }
}
////////////////////////////////////  CLASS ROUTE \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
class Route{
    Scanner sc=new Scanner(System.in);
    class Node{
        String data;
        Node next;
        Node prev;
        Node(String data){
            this.data=data;
            this.next=null;
            this.prev=null;
        }
    }
    Node first=null;

    void insertFirst(String stop)throws EmptyStringException{
        if (stop.isEmpty()) {
            throw new EmptyStringException("Stop name cannot be empty");
        }else{
            Node n=new Node(stop);
            if(first==null){
                first=n;
            }else{
                n.next=first;
                first.prev=n;
                first=n;
            }
        }
    }

    void insertlast(String stop)throws EmptyStringException{
        Node n=new Node(stop);
        if(first==null){
            insertFirst(stop);
        }else{
            Node temp=first;
            while(temp.next!=null){
                temp=temp.next;
            }
            temp.next=n;
            n.prev=temp;
        }
    }

    void insertBefore(String stop,String newStop)throws EmptyStringException{
        int flag=0;
        Node c=first;
        while(c!=null){
            if(c.data.equalsIgnoreCase(stop)){
                flag=1;
            }
            c=c.next;
        }
        if(flag==0){
            System.out.println("No such value in linked list");
        }else{
            Node n=new Node(newStop);
            if(first.data.equals(stop)){
                insertFirst(newStop);
            }else{
                Node c1=first;
                boolean flag1=true;
                while(flag1){
                    c1=c1.next;
                    if(c1.next.data.equals(stop)){
                        flag1=false;
                    }
                }
                n.next=c1.next;
                n.prev=c1;
                c1.next=n;
                n.next.prev=n;
            }
        }
    }

    void insertAfter(String stop,String newStop)throws EmptyStringException{
        int flag=0;
        Node c=first;
        while(c!=null){
            if(c.data.equals(stop)){
                flag=1;
            }
            c=c.next;
        }
        if(flag==0){
            System.out.println("No such value in linked list");
        }else{
            Node n=new Node(newStop);
            Node c1=first;
            boolean flag1=true;
            while(flag1){
                c1=c1.next;
                if(c1.data.equals(stop)){
                    flag1=false;
                }
            }
            if(c1.next==null){
                insertlast(newStop);
            }else{
                n.next=c1.next;
                n.prev=c1;
                c1.next=n;
                n.next.prev=n;
            }
        }
    }

    void deleteAtFirst(){
        if(first == null){
            System.out.println("no element in linked list");
        }
        else if (first.next == null){
            first = null;
        }
        else{
            Node del = first;
            first = first.next;
            first.prev = null;
            del.next = null;
        }
    }
    void deleteAtLast(){
        if(first == null){
            System.out.println("no element in linked list");
        }
        else if(first.next == null){
            first = null;
        }
        else {
            Node temp = first;
                while(temp.next.next != null){
                temp = temp.next;
            }
            Node del = temp.next;
            temp.next = null;
            del.prev = null;
        }
    }
        
    void deleteValue(String stop)throws ValueNotFoundException {
        Node dummy = first;
        int flag = 0;
        while(dummy!=null){
            if(dummy.data.equals(stop)){
                flag = 1;
            }
            dummy = dummy.next;
        }
        if(flag == 0){
            throw new ValueNotFoundException("Value not found in the list");
        }
        else{
            Node temp = first;
            while(temp!=null){
                if(temp.data.equals(stop)){
                    break;
                }
                temp=temp.next;
            }
            if(temp == first){
                deleteAtFirst();
            }
            else if(temp.next==null){
                deleteAtLast();
            }
            else{
                temp.next.prev = temp.prev;
                temp.prev.next = temp.next;
                temp.next = null;
                temp.prev = null;
            }
        }
    }
    FileReader fr1;
    //updates stop entered by user
    void updateStop(String existingStop, String newStop) throws Exception {
        // Check if the existing stop exists in the list
        Node temp = first;
        boolean flag = false;
        while (temp != null) {
            if (temp.data.equals(existingStop)) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        
        if (!flag) {
            System.out.println("Existing stop not found in the list");
            return;
        }        
        // Delete the existing stop
        deleteValue(existingStop);
        BufferedWriter br=new BufferedWriter(new FileWriter("Route.txt"));
        Node h=first;
        while(h!=null){
            br.write(h.data);
            br.newLine();
            h=h.next;
        }
        br.flush();
        br.close();
        System.out.println("Do you want to insert Stop (yes/no): ");
        String ans=sc.next();
        // Insert the new stop before or after the adjacent stop
        if(ans.equalsIgnoreCase("yes")){   
            if (temp.prev != null) {
                insertBefore(temp.prev.data, newStop);
            } else if (temp.next != null) {
                insertAfter(temp.next.data, newStop);
            } else {
                insertFirst(newStop);
            }
            BufferedWriter br1=new BufferedWriter(new FileWriter("Route.txt"));
            Node h1=first;
            while(h1!=null){
                br1.write(h1.data);
                br1.newLine();
                h1=h1.next;
            }
            br1.flush();
            br1.close();
        }else{
            System.out.println("Stop deleted and no new stop inserted");
        }
        fr1=new FileReader("Route.txt");
    }
    
    FileReader fr;
    //displays Entire Entered route
    void DisplayEnteredRoute()throws Exception{
        if(first==null){
            System.out.println("no elements in linked list");
        }
        else if(first.next == null){
            System.out.println(first.data);
        }
        else{
            Node temp = first;
            BufferedWriter br=new BufferedWriter(new FileWriter("Route.txt"));
            do{
                System.out.println(temp.data);
                br.write(temp.data);
                br.newLine();
                temp = temp.next;
            }while(temp!=null);
            br.close();
            fr=new FileReader("Route.txt");
        }
    }
    //adds a new entire route
    int addRoutes()throws Exception{
        int flag=0;
        try {
            int choice=0;
        do{
        System.out.println("1. Add Stop");
        System.out.println("2. Exit");
        System.out.print("Enter Choice : ");
        choice=sc.nextInt();
        sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter stop : ");
                    String stop=sc.nextLine();
                    insertlast(stop);
                    break;
                case 2:
                    System.out.println("Exiting....");
                    break;
                default : 
                    System.out.println("Enter valid Choice...");
                    break;
            }
        }while(choice!=2);        
        DisplayEnteredRoute();
        
        } catch (Exception e) {
            flag=1;
            System.out.println(e);
            System.out.println("ReEnter the route");
            
        }
        return flag;
    }
    static int id;
    void showRoute(int id) throws Exception{
        // first.prev=null;
        // first=null;
        // first.next=null;
        Route.id=id;
        String sql="Select route from bus_details where busId="+id;
        PreparedStatement pst=DataManager.con.prepareStatement(sql);
        ResultSet rs=pst.executeQuery();
        String s="",st[];
        while(rs.next()){
            Clob c=rs.getClob("Route");
            Reader r=c.getCharacterStream();
            int i=r.read();
            while(i!=-1){
                s=s+(char)i;
                i=r.read();
            }
        }
        st=s.split("\r\n");
        for(int j=0;j<st.length;j++){
            System.out.println(st[j]);
            insertlast(st[j]);
        }
        System.out.print("Which stop you want to update : ");
        String oldStop=sc.nextLine();
        System.out.print("Enter new stop to update : ");
        String newStop=sc.nextLine();
        updateStop(oldStop, newStop);
    }
    
    //adds return route in clob file and store it in db
    void ReturnRoute()throws Exception{
        String url="jdbc:mysql://localhost:3306/project";
        String userName="root";
        String password="";
        String driverName="com.mysql.jdbc.Driver";
        Class.forName(driverName);
        Connection con=DriverManager.getConnection(url, userName, password);
        System.out.print("Enter the id of the bus you want to add the return route : ");
        int id=sc.nextInt();
        String sql="Select * from bus_details where busId=?";
        PreparedStatement pst=con.prepareStatement(sql);
        pst.setInt(1,id);
        ResultSet rs=pst.executeQuery();
        while(rs.next()){
            System.out.println(rs.getInt(1));
            Clob clob=rs.getClob(5);
            Reader r=clob.getCharacterStream();
            int i=r.read();
            String j="";
            while(i!=-1){
                j=j+((char)i);
                i=r.read();
            }
            String a[]=j.split("\r\n");
            for(int k=0;k<a.length;k++)
            {
                // a[k].trim();
                insertFirst(a[k]);        
            }
        }
        DisplayEnteredRoute();
    }
}
///////////////////////////////////////////// CLASS MAIN \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
 class Main {
    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        DataManager dm=new DataManager();
        int choice=0;
        System.out.println("-------------------------------------------------------------------");
        System.out.println("                      WELCOME TO BRTS PORTAL");
        System.out.println("-------------------------------------------------------------------");
        System.out.println("\nMenu : ");
        int choice1=0;
        do { 
            System.out.println();
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit....");
            System.out.print("Enter your choice : ");
            choice1=sc.nextInt();
            sc.nextLine();
            switch(choice1){
                case 1:
                {
                    System.out.print("Enter email : ");
                    String lemail=sc.next();
                    System.out.print("Enter password : ");
                    String lpass=sc.next();
                    if(dm.verifyAcc(lemail, lpass)==1){
                        if(dm.role.equals("admin")){
                            do {
                                System.out.println("1. UPDATE USERNAME");
                                System.out.println("2. UPDATE NUMBER");
                                System.out.println("3. UPDATE EMAIL");
                                System.out.println("4. DEL USER");
                                System.out.println("5. ADD BUS");
                                System.out.println("6. ADD A RETURN BUS OF EXISTING BUS ROUTE");
                                System.out.println("7. UPDATE BUS NUMBER");
                                System.out.println("8. UPDATE BUS ROUTE");
                                System.out.println("9. DEL BUS");
                                System.out.println("10. BUS DISPLAY");
                                System.out.println("11. EXIT........");
                                System.out.print("Enter your choice : ");
                                choice=sc.nextInt();
                                sc.nextLine();
                                switch(choice){
                                    case 1:
                                    {
                                        System.out.print("Enter id of user to update : ");
                                        int id=sc.nextInt();
                                        sc.nextLine();
                                        System.out.print("Enter name to be updated : ");
                                        String name=sc.nextLine();
                        
                                        dm.updateUsername(id, name);
                                    }
                                        break;
                                    case 2:
                                    {
                                        System.out.print("Enter id to update : ");
                                        int id=sc.nextInt();
                                        sc.nextLine();
                                        System.out.print("Enter mobile number to update : ");
                                        String num=sc.next();
                                        dm.updateNumber(id, num);
                                    }
                                        break;
                                    case 3:
                                    {
                                        System.out.print("Enter id to update : ");
                                        int id=sc.nextInt();
                                        sc.nextLine();
                                        System.out.print("Enter email to update : ");
                                        String email=sc.next();
                                        dm.updateEmail(id, email);
                                    }
                                        break;
                                    case 4:
                                    {
                                        System.out.print("Enter id to delete the user  : ");
                                        int id=sc.nextInt();
                                        sc.nextLine();
                                        dm.delUser(id);
                                    }
                                        break;
                                    case 5:
                                    {
                                        dm.addBus();
                                    }
                                        break;
                                    case 6:
                                    {
                                        dm.addReturnBus();
                                    }
                                        break;
                                    case 7:
                                    {
                                        System.out.print("Enter bus id to update bus name : ");
                                        int  id=sc.nextInt();
                                        sc.nextLine();
                                        System.out.print("Enter bus Number : ");
                                        String name=sc.next();
                                        dm.updatebusNumber(id,name);
                                    }
                                        break;
                                    case 8:
                                    {
                                        System.out.print("Enter bus id to update bus route : ");
                                        int id=sc.nextInt();
                                        sc.nextLine();
                                        System.out.println("1. Update entire Route");
                                        System.out.println("2. Update stop/ Del Stop");
                                        System.out.print("Enter choice : ");
                                        int c=sc.nextInt();
                                        switch(c){
                                            case 1:
                                                dm.updatebusRoute(id);
                                            break;
                                            case 2:
                                                dm.updateBusStop(id);
                                            break;
                                            default:
                                            System.out.println("Enter valid choice..");
                                            break;
                                        }
                                    }
                                        break;
                                    case 9:
                                    {
                                        System.out.print("Enter bus id to delete : ");
                                        int id=sc.nextInt();
                                        dm.delBus(id);
                                    }
                                        break;
                                    case 10:
                                    {
                                        int ch=0;
                                        do { 
                                            System.out.println("1. Search Bus by name");
                                            System.out.println("2. search bus by start and stop");
                                            System.out.println("3. exitt");
                                            System.out.print("Enter choice : ");
                                            ch=sc.nextInt();
                                            sc.nextLine();
                                            switch(ch){
                                                case 1:
                                                    System.out.print("Enter bus name : ");
                                                    String name=sc.next();
                                                    dm.BusDisplayByName(name);
                                                break;
                                                case 2:
                                                    System.out.print("From : ");
                                                    String busFrom=sc.nextLine();
                                                    System.out.print("To : ");
                                                    String busTo=sc.nextLine();
                                                    dm.BusDisplayByStartStop(busFrom, busTo);
                                                break;
                                                default:
                                                System.out.println("Enter valid choice...");
                                                break;
                                            }
                                        } while (ch!=3);
                                    }
                                    break;
                                    default: 
                                    System.out.println("Enter valid choice ... ");
                                    break;
                                }
                            } while (choice!=11);
                        }else {
                            do { 
                                System.out.println("1. UPDATE USERNAME");
                                System.out.println("2. UPDATE NUMBER");
                                System.out.println("3. UPDATE EMAIL");
                                System.out.println("4. DEL USER");
                                System.out.println("5. BUS DISPLAY");
                                System.out.println("6. EXIT........");
                                System.out.print("Enter your choice : ");
                                choice=sc.nextInt();
                                sc.nextLine();
                                switch(choice){
                                    case 1:
                                    {
                                        System.out.print("Enter id of user to update : ");
                                        int id=sc.nextInt();
                                        sc.nextLine();
                                        System.out.print("Enter name to be updated : ");
                                        String name=sc.nextLine();
                                        dm.updateUsername(id, name);
                                    }
                                        break;
                                    case 2:
                                    {
                                        System.out.print("Enter id to update : ");
                                        int id=sc.nextInt();
                                        sc.nextLine();
                                        System.out.print("Enter mobile number to update : ");
                                        String num=sc.next();
                                        dm.updateNumber(id, num);
                                    }
                                        break;
                                    case 3:
                                    {
                                        System.out.print("Enter id to update : ");
                                        int id=sc.nextInt();
                                        sc.nextLine();
                                        System.out.print("Enter email to update : ");
                                        String email=sc.next();
                                        dm.updateEmail(id, email);
                                    }
                                        break;
                                    case 4:
                                    {
                                        System.out.print("Enter id to delete the user  : ");
                                        int id=sc.nextInt();
                                        sc.nextLine();
                                        dm.delUser(id);
                                    }
                                        break;
                                   
                                    case 5:
                                    {
                                        int ch=0;
                                        do { 
                                            System.out.println("1. Search Bus by name");
                                            System.out.println("2. search bus by start and stop");
                                            System.out.println("3. Display all bus");
                                            System.out.println("4. Display");
                                            System.out.print("Enter choice : ");
                                            ch=sc.nextInt();
                                            sc.nextLine();
                                            switch(ch){
                                                case 1:
                                                    System.out.print("Enter bus name : ");
                                                    String name=sc.next();
                                                    dm.BusDisplayByName(name);
                                                break;
                                                case 2:
                                                    System.out.print("From : ");
                                                    String busFrom=sc.nextLine();
                                                    System.out.print("To : ");
                                                    String busTo=sc.nextLine();
                                                    dm.BusDisplayByStartStop(busFrom, busTo);
                                                break;
                                                case 3:
                                                    dm.BusDisplay();
                                                default:
                                                System.out.println("Enter valid choice...");
                                                break;
                                            }
                                        } while (ch!=4);
                                    }
                                    break;
                                    default: 
                                    System.out.println("Enter valid choice ... ");
                                    break;
                                }
                            } while (choice!=6);
                        }
                    }
                    else{
                        System.out.println("Incorrect Password or email");
                    }
                }
                break;
                case 2:{
                    System.out.print("Enter name : ");
                    String username=sc.nextLine();
                    System.out.print("Enter mobile number : ");
                    String number=sc.nextLine();
                    System.out.println("Enter email : ");
                    String email=sc.nextLine();
                    System.out.print("Enter password (use only !,_,@ as special chars): ");
                    String pass=sc.nextLine();
                    System.out.print("ReEnter password (use only !,_,@ as special chars): ");
                    String pass1=sc.nextLine();
                    if(dm.verifyPass(pass, pass1)==1){
                        dm.addUser(username, number, pass, email);
                    }else{
                        System.out.println("Renter password correctly...");
                    }                   
                }
                break;
            }
        } while (choice1!=3);
    }
}

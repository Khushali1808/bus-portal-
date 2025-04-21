import java.io.*;
import java.sql.*;
import java.util.Scanner;

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
                
                insertFirst(a[k]);        
            }
        }
        DisplayEnteredRoute();
    }
}
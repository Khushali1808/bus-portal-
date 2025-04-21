import java.util.*;

public class Main {
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

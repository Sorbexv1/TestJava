package javaproject;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class MembershipManagement {

    final private Scanner reader = new Scanner(System.in);

    private int getIntInput(){
        int choice = 0;

        while (choice == 0){
            try {
                System.out.println("Please enter number: ");
            choice = reader.nextInt();
                if (choice == 0)
                    throw new InputMismatchException();
                reader.nextLine();
            }catch (InputMismatchException e){
                System.out.println("ERROR. Ivalid input. Please enter number: ");
                reader.nextLine();
            }
        }
        return choice;
    }

    private void printClubOptions(){
        System.out.print(" 1) Club Mercury \n 2) Club Neptune \n 3) Club Jupiter \n 4) Multi Clubs");
    }

    public int getChoice(){
         int choice;
        System.out.print("WELCOME TO OZONE FITNESS CENTER\n");
        System.out.println("=============================");
        System.out.println(" 1) Add Member \n 2) Remove member \n 3) Display Member Information");
        System.out.println("Please select an option (or Enter -1 to quit): ");
        choice = getIntInput();
        return choice;
    }

    public String addMembers(LinkedList<Member> m){

        String name;
        int club;
        String mem;
        double fees;
        int memberID;
        Member mbr;
        Calculator<Integer> cal;

        System.out.println("Please enter member name: ");
        name = reader.nextLine();

        printClubOptions();
        System.out.println("Please chose club: ");
        club = getIntInput();
        while (club < 1 || club > 4){
            System.out.println("Error. Wrong input! Please choose correct one: ");
            club = getIntInput();
        }


        if(m.size() > 0)
            memberID = m.getLast().getMemberID()+1;
        else
            memberID = 1;

        if(club !=4){
            //Lamda function
            cal = (n) -> {
                switch (n) {
                    case 1:
                        return 900;
                    case 2:
                        return 950;
                    case 3:
                        return 1000;
                    default:
                        return -1;
                }
            };
            fees = cal.calculateFees(club);
            mbr = new SingleClubMember('S',memberID,name,fees,club);
            m.add(mbr);
            mem = mbr.toString();
            System.out.println("\nSTATUS: Single CLub Member added\n");
        }else{
            cal = (n) -> {
                switch (n) {
                    case 1:
                        return 900;
                    case 2:
                        return 950;
                    case 3:
                        return 1000;
                    case 4:
                        return 1200;
                    default:
                        return -1;
                }
            };
            fees = cal.calculateFees(club);
            mbr = new MultiClubMember('M',memberID,name,fees,100);
            m.add(mbr);
            mem = mbr.toString();
            System.out.println("\nSTATUS: Multy CLub Member added\n");
        }
        return mem;
    }

    public void removeMember(LinkedList<Member> m){
            int memberID;

        System.out.println("Please enter memberID, to delete");
        memberID = getIntInput();
        for (int i = 0; i<m.size();i++){
            if(m.get(i).getMemberID()==memberID){
                m.remove(i);
                System.out.println("Member deleted");
                return;
            }
        }
        System.out.println("Member not found");
    }

    public void printMemberInfo(LinkedList<Member> m){
        int memberID;

        System.out.println("Please enter memberID, to delete");
        memberID = getIntInput();
        for (int i = 0; i<m.size();i++){
            if(m.get(i).getMemberID()==memberID){
                String[] memberInfo = m.get(i).toString().split(", ");
                if(memberInfo[0].equals("S")) {
                    System.out.println("Member Type: " + memberInfo[0]);
                    System.out.println("Member ID: " + memberInfo[1]);
                    System.out.println("Member Name: " + memberInfo[2]);
                    System.out.println("Member Fees: " + memberInfo[3]);
                    System.out.println("Club ID: " + memberInfo[4]);
                }else{
                    System.out.println("Member Type: "+memberInfo[0]);
                    System.out.println("Member ID: "+memberInfo[1]);
                    System.out.println("Member Name: "+memberInfo[2]);
                    System.out.println("Member Fees: "+memberInfo[3]);
                    System.out.println("Membership Point: "+memberInfo[4]);
                }
                return;
            }
        }
        System.out.println("Member not found");
    }
}

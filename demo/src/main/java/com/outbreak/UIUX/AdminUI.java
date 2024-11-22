package com.outbreak.UIUX;

import java.util.ArrayList;
import java.util.Scanner;

import com.outbreak.HostelRoom.HostelRoom;
import com.outbreak.RTPCR.RTPCR;
import com.outbreak.UseCases.UseCase1.useCase1;
import com.outbreak.UseCases.useCase4.useCase4;
import com.outbreak.UseCases.useCase4.useCase4b;
import com.outbreak.UseCases.useCase5.useCase5;
import com.outbreak.UseCases.useCase7.useCase7a;
import com.outbreak.UseCases.useCase7.useCase7b;
import com.outbreak.UseCases.useCase8.useCase8;

public class AdminUI
{
    public int displayIntialPrompt()
    {

        //while(true) {
        Scanner sc = new Scanner(System.in);
        System.out.println("******************************************** OUTBREAK OBSERVER ********************************************************");
        System.out.println("                         \033[3m Track the spread, Fight covid-19, Protect your community.\033[0m\n"); 
        System.out.println("Hello Admin! Welcome to home screen of Outbreak Observer.\n What action would you like to take:\n");
        System.out.println("\t 1) Enter RTPCR details of a student.");
        System.out.println("\t 2) Get RTPCR details of students.");
        System.out.println("\t 3) Update Vaccination details of a student.");
        System.out.println("\t 4) Get Vaccination details of students.");
        System.out.println("\t 5) Get list of infected students and details.");
        System.out.println("\t 6) Update infected students data.");
        System.out.println("\t 7) Get list of students who are roommates of Infected students.");
        System.out.println("\t 8) Get Data about hostel & qurantine facilities.");
        System.out.println("\t 10) Quit");
        int i;
        while(true)
        {
            String s = sc.next();
            if(s.equals("quit")) s="-1";

            if(s.equals("-1") || s.equals("0") || s.equals("1") || s.equals("2") || s.equals("3") || s.equals("4") || s.equals("5") || s.equals("6") || s.equals("7") || s.equals("8") || s.equals("9") || s.equals("10")) 
            {
                i = Integer.parseInt(s); 
                return i;
            }
        }
        //System.out.print("\033[H\033[2J");  
        //System.out.flush();
        //}          
    }

    public RTPCR displayRTPCRPrompt1()
    {
        System.out.print("\033[H\033[2J");  
        System.out.flush();

        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<String>();

        System.out.println("******************************************** OUTBREAK OBSERVER ********************************************************");
        System.out.println("                         \033[3m Track the spread, Fight covid-19, Protect your community.\033[0m\n"); 
            
        System.out.println("Hello Admin! Welcome to home screen of Outbreak Observer.\n Enter the RTPCR details (keep in mind that the sid must exist, date format is (yyyy-mm-dd),test_result to be entered as Positive or Negative) :\n\n");
        String  studentId, testDate, test_result;
        System.out.print("studentId: ");
        studentId=sc.nextLine();  
        System.out.print("testDate: ");
        testDate=sc.nextLine();
        System.out.print("test_result: ");
        test_result=sc.nextLine();
        
        RTPCR test=new RTPCR("0",studentId,testDate,test_result,"NULL");
        return test;
    }

    public int displayRTPCRPrompt2()
    {
        System.out.print("\033[H\033[2J");  
        System.out.flush();

        Scanner sc = new Scanner(System.in);

        System.out.println("******************************************** OUTBREAK OBSERVER ********************************************************");
        System.out.println("                         \033[3m Track the spread, Fight covid-19, Protect your community.\033[0m\n"); 
            
        System.out.println("Hello Admin! Welcome to home screen of Outbreak Observer.\n Chose an option to fetch RTPCR details in the way you want:\n");
        System.out.println("\t 1) Get details of all the RTPCRs of a student, by Id.");
        System.out.println("\t 2) Get RTPCR details of a student, by Id, on a given Date.");
        System.out.println("\t 3) Get RTPCR details of all students in institute on a given Date.");
        System.out.println("\t 4) Get RTPCR details of all students of a batch.");
        
        int i = sc.nextInt();
        return i;
    }

    public String displayRTPCRPrompt2a()
    {
        System.out.print("\033[H\033[2J");  
        System.out.flush();

        Scanner sc = new Scanner(System.in);
        String s;

        System.out.println("******************************************** OUTBREAK OBSERVER ********************************************************");
        System.out.println("                         \033[3m Track the spread, Fight covid-19, Protect your community.\033[0m\n"); 
            
        System.out.println("Hello Admin! Welcome. Enter the details: \n\n");
    
        System.out.print("Enter studentId: ");
        s = sc.nextLine();
        
        return s;
    }

    public ArrayList<String> displayRTPCRPrompt2b()
    {
        System.out.print("\033[H\033[2J");  
        System.out.flush();

        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<String>();

        System.out.println("******************************************** OUTBREAK OBSERVER ********************************************************");
        System.out.println("                         \033[3m Track the spread, Fight covid-19, Protect your community.\033[0m\n"); 
            
        System.out.println("Hello Admin! Welcome. Enter the details:\n\n");
    
        System.out.print("Enter studentId: ");
        list.add(sc.nextLine());
        
        System.out.print("Enter date Taken: ");
        list.add(sc.nextLine());
        return list;
    }

    public String displayRTPCRPrompt2c()
    {
        System.out.print("\033[H\033[2J");  
        System.out.flush();

        Scanner sc = new Scanner(System.in);
        String s;

        System.out.println("******************************************** OUTBREAK OBSERVER ********************************************************");
        System.out.println("                         \033[3m Track the spread, Fight covid-19, Protect your community.\033[0m\n"); 
            
        System.out.println("Hello Admin! Welcome. Enter the details:\n\n");
        
        System.out.print("Enter date Taken: ");
        s = sc.nextLine();

        return s;
    }

    public String displayRTPCRPrompt2d()
    {
        System.out.print("\033[H\033[2J");  
        System.out.flush();

        Scanner sc = new Scanner(System.in);
        String s;

        System.out.println("******************************************** OUTBREAK OBSERVER ********************************************************");
        System.out.println("                         \033[3m Track the spread, Fight covid-19, Protect your community.\033[0m\n"); 
            
        System.out.println("Hello Admin! Welcome. Enter the details:\n\n");
    
        System.out.print("Enter batch (IMTXXXX/MTXXXX): ");
        s=sc.nextLine();
        return s;
    }

    public void displayRTPCRdetails(useCase1 uc1)
    {
        System.out.print("\033[H\033[2J");  
        System.out.flush();

        System.out.println("******************************************** OUTBREAK OBSERVER ********************************************************");
        System.out.println("                         \033[3m Track the spread, Fight covid-19, Protect your community.\033[0m\n"); 
            
        System.out.println("Hello Admin! Welcome to home screen of Outbreak Observer.\n Here are the RTPCR test details of student " + uc1.getstudentId() + ", conducted on Date: "+ uc1.getDate() +"\n");
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        System.out.println("| Student Id\t| Student Name\t\t\t| Test Id\t| Test Date\t| Test Result\t| Hostel Room |");
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        System.out.println(String.format("| %s\t| %-25s\t| %-10s\t| %-10s\t| %-10s\t| %-11s |", uc1.getstudentId(),uc1.getName(),uc1.getTestId(),uc1.getDate(),uc1.getTestResult(),uc1.getRoomNo()));
        System.out.println("---------------------------------------------------------------------------------------------------------------");
    }

    public void displayRTPCRdetails(ArrayList<useCase1> ucList)
    {
        System.out.print("\033[H\033[2J");  
        System.out.flush();

        ArrayList<Integer> rlist = new ArrayList<Integer>();
        System.out.println("******************************************** OUTBREAK OBSERVER ********************************************************");
        System.out.println("                         \033[3m Track the spread, Fight covid-19, Protect your community.\033[0m\n"); 
            
        System.out.println("Hello Admin! Welcome to home screen of Outbreak Observer.\n\n Here are the RTPCR test details of all students of batch 2021:\n\n");
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        System.out.println("| Student Id\t| Student Name\t\t\t| Test Id\t| Test Date\t| Test Result\t| Hostel Room |");
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        
        int pos_count=0; int tr=0;
        
        try{
            for(int i=0; i<ucList.size(); i++)
            {
                System.out.println(String.format("| %s\t| %-25s\t| %-10s\t| %-10s\t| %-10s\t| %-11s |", ucList.get(i).getstudentId(),ucList.get(i).getName(),ucList.get(i).getTestId(),ucList.get(i).getDate(),ucList.get(i).getTestResult(),ucList.get(i).getRoomNo()));
                System.out.println("---------------------------------------------------------------------------------------------------------------");
                if(ucList.get(i).getTestResult().equals("Positive")) {pos_count++; rlist.add(i);}
                tr++;
            }
        } catch(IndexOutOfBoundsException e){
            System.out.println("There is no data for that date. Please check the date you entered.\n");
        }
            System.out.println("\n\n*************************************************************************************************************************");
            System.out.println("|Total number of rows printed: \t\t\t\t\t\t|" + String.format("%5d",tr) + "\t\t\t\t\t\t|");
            System.out.println("\n|Total number of postive results: \t\t\t\t\t|" + String.format("%5d",pos_count) + "\t\t\t\t\t\t|");
        
        System.out.println("*************************************************************************************************************************\n\n");
    }


    public void displayRTPCRDetailsOnDate(ArrayList<useCase1> ucList)
    {
        System.out.print("\033[H\033[2J");  
        System.out.flush();

        ArrayList<Integer> rlist = new ArrayList<Integer>();
        System.out.println("******************************************** OUTBREAK OBSERVER ********************************************************");
        System.out.println("                         \033[3m Track the spread, Fight covid-19, Protect your community.\033[0m\n"); 
        
        int pos_count=0; int tr=0;

        try{
            System.out.println("Hello Admin! Welcome to home screen of Outbreak Observer.\n\n Here are the RTPCR test details of all students, conducted on date: "+ucList.get(0).getDate()+":\n\n");
            System.out.println("---------------------------------------------------------------------------------------------------------------");
            System.out.println("| Student Id\t| Student Name\t\t\t| Test Id\t| Test Date\t| Test Result\t| Hostel Room |");
            System.out.println("---------------------------------------------------------------------------------------------------------------");

            for(int i=0; i<ucList.size(); i++)
            {
                System.out.println(String.format("| %s\t| %-25s\t| %-10s\t| %-10s\t| %-10s\t| %-11s |", ucList.get(i).getstudentId(),ucList.get(i).getName(),ucList.get(i).getTestId(),ucList.get(i).getDate(),ucList.get(i).getTestResult(),ucList.get(i).getRoomNo()));
                System.out.println("---------------------------------------------------------------------------------------------------------------");
                if(ucList.get(i).getTestResult().equals("Positive")) pos_count++;
                tr++;
                rlist.add(i);
            }
        } catch (IndexOutOfBoundsException e){
            System.out.println("There is no data for that date. Please check the date you entered.\n");
        }
        System.out.println("\n\n*************************************************************************************************************************");
        System.out.println("|Total number of rows printed: \t\t\t\t\t\t|" + String.format("%5d",tr) + "\t\t\t\t\t\t|");
        System.out.println("\n|Total number of postive results: \t\t\t\t\t|" + String.format("%5d",pos_count) + "\t\t\t\t\t\t|");

        System.out.println("*************************************************************************************************************************\n\n");
    }

    public void displayRTPCRDetailsOfStudent(ArrayList<useCase1> ucList)
    {
        ArrayList<Integer> rlist = new ArrayList<Integer>();
        System.out.println("******************************************** OUTBREAK OBSERVER ********************************************************");
        System.out.println("                         \033[3m Track the spread, Fight covid-19, Protect your community.\033[0m\n"); 
        
        int pos_count=0; int tr=0;
        try{
            System.out.println("Hello Admin! Welcome to home screen of Outbreak Observer.\n\n Here are the RTPCR test details of student "+ucList.get(0).getstudentId()+ ":\n\n");
            System.out.println("---------------------------------------------------------------------------------------------------------------");
            System.out.println("| Student Id\t| Student Name\t\t\t| Test Id\t| Test Date\t| Test Result\t| Hostel Room |");
            System.out.println("---------------------------------------------------------------------------------------------------------------");
        
            for(int i=0; i<ucList.size(); i++)
            {
                System.out.println(String.format("| %s\t| %-25s\t| %-10s\t| %-10s\t| %-10s\t| %-11s |", ucList.get(i).getstudentId(),ucList.get(i).getName(),ucList.get(i).getTestId(),ucList.get(i).getDate(),ucList.get(i).getTestResult(),ucList.get(i).getRoomNo()));
                System.out.println("---------------------------------------------------------------------------------------------------------------");
                if(ucList.get(i).getTestResult().equals("Positive")) pos_count++;
                tr++;
                rlist.add(i);
            }

            System.out.println("\n\n*************************************************************************************************************************");
            System.out.println("|Total number of rows printed: \t\t\t\t\t\t|" + String.format("%5d",tr) + "\t\t\t\t\t\t|");
            System.out.println("\n|Total number of postive results: \t\t\t\t\t|" + String.format("%5d",pos_count) + "\t\t\t\t\t\t|");
        } catch(IndexOutOfBoundsException e){
            System.out.println("There is no data for that date. Please check the date you entered.\n");
        }
  
        System.out.println("*************************************************************************************************************************\n\n");
    }

    public String displayVaccinationPrompt3a()
    {
        Scanner sc = new Scanner(System.in);
        String sId;

        System.out.println("******************************************** OUTBREAK OBSERVER ********************************************************");
        System.out.println("                         \033[3m Track the spread, Fight covid-19, Protect your community.\033[0m\n"); 
            
        System.out.println("Hello Admin! Welcome to home screen of Outbreak Observer.\n Update the Vaccination details (enter \"null\" if vaccination status need not be updated):\n\n");
    
        System.out.print("studentId: ");
        sId = sc.nextLine();
        return sId;
    }

    public ArrayList<String> displayVaccinationPrompt3b(int doseNo)
    {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<String>();
        System.out.println("Current Dose Number: "+doseNo+"\n\nEnter details:\n");
        System.out.print("DateTaken (yyyy-mm-dd): ");
        list.add( sc.nextLine());  
        System.out.print("VaccineName: ");
        list.add( sc.nextLine());
        System.out.print("Comment: ");
        list.add( sc.nextLine() );

        return list;
    }

    public int displayVaccinationPrompt4()
    { 
        Scanner sc = new Scanner(System.in);
        System.out.println("******************************************** OUTBREAK OBSERVER ********************************************************");
        System.out.println("                         \033[3m Track the spread, Fight covid-19, Protect your community.\033[0m\n"); 
            
        System.out.println("Hello Admin! Welcome to home screen of Outbreak Observer.\n Chose an option to fetch the Vaccination details the way you want: \n");  
        System.out.println("\t 1) Get details of all the doses of a student, by Id.");
        System.out.println("\t 2) Get dose details of all students.");
        System.out.println("\t 3) Get vaccination details of all students in institute.");
        System.out.println("\t 4) Get vaccination details of all students of a batch.");

        int i = sc.nextInt();
        return i;
    }

    public String displayVaccinationPrompt4a()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("******************************************** OUTBREAK OBSERVER ********************************************************");
        System.out.println("                         \033[3m Track the spread, Fight covid-19, Protect your community.\033[0m\n"); 
            
        System.out.print("Hello Admin! Welcome.\nEnter student ID: ");  
        String s = sc.next();
        return s;
    }

    public String displayVaccinationPrompt4d()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("******************************************** OUTBREAK OBSERVER ********************************************************");
        System.out.println("                         \033[3m Track the spread, Fight covid-19, Protect your community.\033[0m\n"); 
            
        System.out.print("Hello Admin! Welcome.Enter batch No: ");  
        String s = sc.next();
        return s;
    }

    public void displayVaccinationDetailsOfStudent(ArrayList<useCase4> ucList)
    {
        System.out.println("******************************************** OUTBREAK OBSERVER ********************************************************");
        System.out.println("                         \033[3m Track the spread, Fight covid-19, Protect your community.\033[0m\n"); 
            
        System.out.println("Hello Admin! Welcome to home screen of Outbreak Observer.\n\nHere are the dose test details of student" + ucList.get(0).getstudentId() +":\n\n");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        System.out.println("| Student Id\t| Student Name\t\t\t\t| doseNo\t| DateTaken\t\t| vaccineName\t|");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        
        int dose_count=0;
        for(int i=0; i<ucList.size(); i++)
        {
            System.out.println(String.format("| %s\t| %-30s\t| %-10s\t| %-15s\t| %-10s\t|", ucList.get(i).getstudentId(),ucList.get(i).getName(),ucList.get(i).getdoseNo(),ucList.get(i).getDate(),ucList.get(i).getvaccineName()));
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
            dose_count++;
        }

        System.out.println("\n\n*****************************************************************************************************************");
        System.out.println("|                                                                                                               |");
        System.out.println("|Total number of dosesTaken: \t\t\t\t\t|" + String.format("%5d",dose_count) + "\t\t\t\t\t\t|");
        System.out.println("|                                                                                                               |");
        System.out.println("*****************************************************************************************************************");
    }

    public void displayVaccinationDetails(ArrayList<useCase4b> ucList, int studentcount)
    {
        System.out.println("******************************************** OUTBREAK OBSERVER ********************************************************");
        System.out.println("                         \033[3m Track the spread, Fight covid-19, Protect your community.\033[0m\n"); 
            
        System.out.println("Hello Admin! Welcome to home screen of Outbreak Observer.\n\n Here are the vaccination details of all the students :\n\n");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("| Student Id\t| Student Name\t\t\t\t| vaccination_status\t\t\t\t\t| DosesTaken\t|");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
        
        int vcount=0, ccount=0;
        for(int i=0; i<ucList.size(); i++)
        {
            System.out.println(String.format("| %s\t| %-30s\t| %-50s\t| %-10s\t|", ucList.get(i).getstudentId(),ucList.get(i).getName(),ucList.get(i).getVaccinationStatus(),ucList.get(i).getDosesTaken()));
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
            if(Integer.parseInt(ucList.get(i).getDosesTaken())>0) vcount++; 
            if(Integer.parseInt(ucList.get(i).getDosesTaken())>=2) ccount++;
        }

        System.out.println("\n\n*************************************************************************************************************************");
        System.out.println("|                                                                                                                       |");
        System.out.println("|  Total number of vaccinated students: \t\t\t\t|" + String.format("%5d",vcount) + "\t\t\t\t\t\t|");
        System.out.println("|                                                                                                                       |");
        System.out.println("|  Total number of non-vaccinated students: \t\t\t\t|" + String.format("%5d",studentcount-vcount) + "\t\t\t\t\t\t|");
        System.out.println("|                                                                                                                       |");
        System.out.println("|  Total number of students Taken 2 or more doses: \t\t\t|" + String.format("%5d",ccount) + "\t\t\t\t\t\t|");
        System.out.println("|                                                                                                                       |");
        System.out.println("*************************************************************************************************************************");
    }

    public void displayVaccinationDetailsOfBatch(ArrayList<useCase4b> ucList, String batch, int studentcount)
    {
        System.out.println("******************************************** OUTBREAK OBSERVER ********************************************************");
        System.out.println("                         \033[3m Track the spread, Fight covid-19, Protect your community.\033[0m\n"); 
            
        System.out.println("Hello Admin! Welcome to home screen of Outbreak Observer.\n\n Here are the vaccination details of batch "+ batch + ":\n\n");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("| Student Id\t| Student Name\t\t\t\t\t| vaccination_status\t\t\t\t| DosesTaken\t|");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
        
        int vcount=0, ccount=0;
        for(int i=0; i<ucList.size(); i++)
        {
            System.out.println(String.format("| %s\t| %-40s\t| %-40s\t| %-10s\t|", ucList.get(i).getstudentId(),ucList.get(i).getName(),ucList.get(i).getVaccinationStatus(),ucList.get(i).getDosesTaken()));
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
            if(Integer.parseInt(ucList.get(i).getDosesTaken())>0) vcount++; 
            if(Integer.parseInt(ucList.get(i).getDosesTaken())>=2) ccount++;
        }

        System.out.println("\n\n*************************************************************************************************************************");
        System.out.println("|                                                                                                                       |");
        System.out.println("|  Total number of vaccinated students: \t\t\t\t|" + String.format("%5d",vcount) + "\t\t\t\t\t\t|");
        System.out.println("|                                                                                                                       |");
        System.out.println("|  Total number of non-vaccinated students: \t\t\t\t|" + String.format("%5d",studentcount-vcount) + "\t\t\t\t\t\t|");
        System.out.println("|                                                                                                                       |");
        System.out.println("|  Total number of students Taken 2 or more doses: \t\t\t|" + String.format("%5d",ccount) + "\t\t\t\t\t\t|");
        System.out.println("|                                                                                                                       |");
        System.out.println("*************************************************************************************************************************");
    }

    public int displayInfectedPrompt5()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("******************************************** OUTBREAK OBSERVER ********************************************************");
        System.out.println("                         \033[3m Track the spread, Fight covid-19, Protect your community.\033[0m\n"); 
            
        System.out.println("Hello Admin! Welcome to home screen of Outbreak Observer.\nChose an option to fetch the RTPCR details the way you want: \n");  
        System.out.println("\t 1) Get all infected students details list.");
        System.out.println("\t 2) Get infected students details list batch-wise .");
        
        int i=-1;
        while(true){
            String s = sc.next();
            if(s.equals("quit")) s="-1";
            if(s.equals("-1") || s.equals("1") || s.equals("2") ) { i=Integer.parseInt(s); return i;} 
        }
    }

    public void displayInfectedDetailsList(ArrayList<useCase5> ucList)
    {
        System.out.println("******************************************** OUTBREAK OBSERVER ********************************************************");
        System.out.println("                         \033[3m Track the spread, Fight covid-19, Protect your community.\033[0m\n"); 
            
        System.out.println("Hello Admin! Welcome to home screen of Outbreak Observer.\n\n Here is the list of infected students:\n\n");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("| Student Id\t| Student Name\t\t\t| diagnosis_Date\t\t| testId\t| qroomNo\t| healthStatus\t|");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
        
        int icount=0;
        for(int i=0; i<ucList.size(); i++)
        {
            System.out.println(String.format("| %s\t| %-25s\t| %-25s\t| %-10s\t| %-10s\t| %-10s\t|", ucList.get(i).getstudentId(),ucList.get(i).getName(),ucList.get(i).getDate(),ucList.get(i).getTestId(), ucList.get(i).getqRoomNo(), ucList.get(i).getHealthStatus()));
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
            
            icount++;
            
        }

        System.out.println("\n\n*************************************************************************************************************************");
        System.out.println("|                                                                                                                       |");
        System.out.println("|Total number of infected students: \t\t\t\t\t|" + String.format("%5d",icount) + "\t\t\t\t\t\t|");
        System.out.println("|                                                                                                                       |");
        System.out.println("*************************************************************************************************************************");
    }

    public String displayInfectedPrompt5b()
    {
        String s;
        Scanner sc = new Scanner(System.in);
        System.out.println("******************************************** OUTBREAK OBSERVER ********************************************************");
        System.out.println("                         \033[3m Track the spread, Fight covid-19, Protect your community.\033[0m\n"); 
            
        System.out.print("Hello Admin! Welcome.\nEnter batch No (IMTXXXX/MTXXXX):");
        s=sc.next();
        return s;
    }

    public void displayInfectedDetailsListByBatch(ArrayList<useCase5> ucList, String batch)
    {
        System.out.println("******************************************** OUTBREAK OBSERVER ********************************************************");
        System.out.println("                         \033[3m Track the spread, Fight covid-19, Protect your community.\033[0m\n"); 
            
        System.out.println("Hello Admin! Welcome to home screen of Outbreak Observer.\n\n Here is the list of infected students of batch " + batch + ":\n\n");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("| Student Id\t| Student Name\t\t\t| diagnosis_Date\t\t| testId\t\t| qroomNo\t\t| healthStatus\t\t|");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
        
        int icount=0;
        for(int i=0; i<ucList.size(); i++)
        {
            System.out.println(String.format("| %s\t| %-25s\t| %-25s\t| %-20s\t| %-15s\t| %-15s\t|", ucList.get(i).getstudentId(),ucList.get(i).getName(),ucList.get(i).getDate(),ucList.get(i).getTestId(), ucList.get(i).getqRoomNo(), ucList.get(i).getHealthStatus()));
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
            icount++;
        }

        System.out.println("\n\n*************************************************************************************************************************");
        System.out.println("|                                                                                                                       |");
        System.out.println("|Total number of infected students: \t\t\t\t\t|" + String.format("%5d",icount) + "\t\t\t\t\t\t|");
        System.out.println("|                                                                                                                       |");
        System.out.println("*************************************************************************************************************************");
    }

    public int displayInfectedPrompt6()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("******************************************** OUTBREAK OBSERVER ********************************************************");
        System.out.println("                         \033[3m Track the spread, Fight covid-19, Protect your community.\033[0m\n"); 
            
        System.out.println("Hello Admin! Welcome to home screen of Outbreak Observer.\n Chose an option to fetch the RTPCR details the way you want: \n");  
        System.out.println("\t 1) Update infected students details list.");
        System.out.println("\t 2) Remove a case from the infected students List. Since the case is no longer valid");
        
        int i=-1;
        while(true){
            String s = sc.next();
            if(s.equals("quit")) s="-1";
            if(s.equals("-1") || s.equals("1") || s.equals("2")) { i=Integer.parseInt(s); return i;} 
        }
    }

    public ArrayList<String> displayUpdateInfected_Prompt2()
    {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<String>();

        System.out.println("******************************************** OUTBREAK OBSERVER ********************************************************");
        System.out.println("                         \033[3m Track the spread, Fight covid-19, Protect your community.\033[0m\n"); 
            
        System.out.println("Hello Admin! Welcome to home screen of Outbreak Observer.\n Here you can update the infected details. Please mention the caseId of the case. And proceed to update. Enter \"null\" wherever you don't want to modify:\n\n");
        System.out.println("caseId: ");
        list.add( sc.nextLine() );
        System.out.println("qroomNO: ");
        list.add( sc.nextLine() );
        System.out.println("startDate: ");
        list.add( sc.nextLine() );
        System.out.println("endDate: ");
        list.add( sc.nextLine() );
        System.out.println("healthStatus: ");
        list.add( sc.nextLine() );

        return list;
    }

    public ArrayList<String> displayUpdateInfected_Prompt3()
    {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<String>();

        System.out.println("******************************************** OUTBREAK OBSERVER ********************************************************");
        System.out.println("                         \033[3m Track the spread, Fight covid-19, Protect your community.\033[0m\n"); 
            
        System.out.println("Hello Admin! Welcome to home screen of Outbreak Observer.\n Here you can remove Remove a case from the infected students List, if the case is no longer valid. Please mention the caseId of the case you wish to remove\n\n");
        System.out.println("caseId: ");
        list.add( sc.nextLine() );

        return list;
    }
     
    
    public void displayRoomatesOfInfectedStudents(ArrayList<ArrayList<useCase7b>> uc7b, ArrayList<useCase7a> uc7a)
    {
        System.out.println("******************************************** OUTBREAK OBSERVER ********************************************************");
        System.out.println("                         \033[3m Track the spread, Fight covid-19, Protect your community.\033[0m\n"); 
            
        System.out.println("Hello Admin! Welcome to home screen of Outbreak Observer.\n Here is the list of infected students followed bytheir roommates status:\n\n");
        
        for(int i=0; i<uc7a.size(); i++)
        {
            System.out.println(String.format("\n Infected Student : %s \t name: %s \t caseId: %s \t\n\n hroomNo: %s \t qroomNo: %s \t",uc7a.get(i).getstudentId(), uc7a.get(i).getName(), uc7a.get(i).getCaseId(), uc7a.get(i).gethroomNo(), uc7a.get(i).getqRoomNo()));
            if(uc7b.get(i).size()>0){
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("| Student Id\t| Student Name\t\t\t| vaccination_status\t| rtpcr_recent_result\t\t| rtpcr_date\t\t|");
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
        
                for(int j=0; j<uc7b.get(i).size(); j++)
                {
                    System.out.println(String.format("| %s\t| %-25s\t| %-20s\t| %-24s\t| %-18s\t|", uc7b.get(i).get(j).getstudentId(),uc7b.get(i).get(j).getName(),uc7b.get(i).get(j).getVaccinationStatus(),uc7b.get(i).get(j).getRTPCRResult(),uc7b.get(i).get(j).getRTPCRDate()));
                    System.out.println("---------------------------------------------------------------------------------------------------------------------------------");

                }
            }
            else System.out.println("\nThis student doesn't have any roommates");
            
        }

             
    }
    public int displayInfectedPrompt8()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("******************************************** OUTBREAK OBSERVER ********************************************************");
        System.out.println("                         \033[3m Track the spread, Fight covid-19, Protect your community.\033[0m\n"); 
            
        System.out.println("Hello Admin! Welcome to home screen of Outbreak Observer.\n Chose an option to fetch the Hostel and Quarantine details the way you want: \n");  
        System.out.println("\t 1) Get Hostel Room details based on RoomNo");
        System.out.println("\t 2) Get Hostel Room details of all rooms");
        System.out.println("\t 3) Get All Quarantine Room details");
        System.out.println("\t 4) Get Empty Quarantine Room details");
        int i=-1;
        while(true){
            String s = sc.next();
            if(s.equals("quit")) s="-1";
            if(s.equals("-1") || s.equals("1") || s.equals("2") || s.equals("3") || s.equals("4") ) { i=Integer.parseInt(s); return i;} 
        }
    }

    public String display8a(){

        Scanner sc = new Scanner(System.in);
        System.out.println("******************************************** OUTBREAK OBSERVER ********************************************************");
        System.out.println("                         \033[3m Track the spread, Fight covid-19, Protect your community.\033[0m\n"); 
            
        System.out.println("Hello Admin! Welcome to home screen of Outbreak Observer.\n\nEnter Details");

        System.out.print("Enter roomNo: ");
        String s=sc.next();

        return s;
    }

    public void Printrooms(ArrayList<useCase8> hostel, int capacity, int vacancy){
        System.out.println("******************************************** OUTBREAK OBSERVER ********************************************************");
        System.out.println("                         \033[3m Track the spread, Fight covid-19, Protect your community.\033[0m\n"); 
            
        System.out.println("Hello Admin! Welcome to home screen of Outbreak Observer.\n Here is the list of hostel rooms:\n\n");

        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("| RoomNo| StudentId\t\t| Name\t\t\t| Capacity\t\t| Vacancy\t\t| HostelType\t\t|");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------");


        
        for(int i=0; i<hostel.size(); i++)
        {
            System.out.println(String.format("| %s\t| %-20s\t| %-20s\t| %-20d\t| %-15d\t| %-20s\t|", hostel.get(i).getRoomNo(),hostel.get(i).getstudentId(),hostel.get(i).getName(),hostel.get(i).getCapacity(), hostel.get(i).getVacancy(), hostel.get(i).getType()));
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
        }

        System.out.println("\n\n*************************************************************************************************************************");

        System.out.println("|Total Capacity of Hostel facilities: \t\t\t\t\t|" + String.format("%5d",capacity) + "\t\t\t\t\t|\n");

        System.out.println("|Total Vacancy of Hostel facilities: \t\t\t\t\t|" + String.format("%5d",vacancy) + "\t\t\t\t\t|");
     
        System.out.println("*************************************************************************************************************************");
    }
    public void PrintQuarantine(ArrayList<HostelRoom> hostel, int capacity, int vacancy){
        System.out.println("******************************************** OUTBREAK OBSERVER ********************************************************");
        System.out.println("                         \033[3m Track the spread, Fight covid-19, Protect your community.\033[0m\n"); 
            
        System.out.println("Hello Admin! Welcome to home screen of Outbreak Observer.\n Here is the list of hostel rooms:\n\n");

        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("| RoomNo| HostelType\t\t| Capacity\t\t| Vacancy\t\t|");
        System.out.println("---------------------------------------------------------------------------------");
        
        for(int i=0; i<hostel.size(); i++)
        {
            System.out.println(String.format("| %s\t| %-20s\t| %-20d\t| %-15d\t|", hostel.get(i).getroomNo(), hostel.get(i).gethostelType(), hostel.get(i).getCapacity(), hostel.get(i).getVacancy()));
        System.out.println("---------------------------------------------------------------------------------");
        }


        System.out.println("\n\n*************************************************************************************************************************");

        System.out.println("|Total Capacity of Quarantine facilities: \t\t\t\t\t|" + String.format("%5d",capacity) + "\t\t\t\t\t|\n");

        System.out.println("|Total Vacancy of Quarantine facilities: \t\t\t\t\t|" + String.format("%5d",vacancy) + "\t\t\t\t\t|");
     
        System.out.println("*************************************************************************************************************************");
    }
}
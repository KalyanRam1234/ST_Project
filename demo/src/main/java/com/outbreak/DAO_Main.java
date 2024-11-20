package com.outbreak;

import com.outbreak.Student.*;
import com.outbreak.UseCases.UseCase1.*;
import com.outbreak.UseCases.useCase2.useCase2;
import com.outbreak.UseCases.useCase2.useCase2_DAO;
import com.outbreak.UseCases.useCase3.useCase3;
import com.outbreak.UseCases.useCase3.useCase3_DAO;
import com.outbreak.UseCases.useCase4.useCase4;
import com.outbreak.UseCases.useCase4.useCase4_DAO;
import com.outbreak.UseCases.useCase4.useCase4b;
import com.outbreak.UseCases.useCase5.useCase5;
import com.outbreak.UseCases.useCase5.useCase5_DAO;
import com.outbreak.UseCases.useCase6.useCase6_DAO;
import com.outbreak.UseCases.useCase7.useCase7_DAO;
import com.outbreak.UseCases.useCase7.useCase7a;
import com.outbreak.UseCases.useCase7.useCase7b;
import com.outbreak.UseCases.useCase8.useCase8;
import com.outbreak.UseCases.useCase8.useCase8DAO;
import com.outbreak.UIUX.AdminUI;
import com.outbreak.UIUX.ClientUI;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import com.outbreak.HostelRoom.HostelDAO;
import com.outbreak.HostelRoom.HostelRoom;
import com.outbreak.Login.LoginDAO;
import com.outbreak.RTPCR.RTPCR;
import com.outbreak.RTPCR.RTPCRDAO;
public class DAO_Main {
    public static DAO_Factory daoFactory;
    public static void main(String[] args){

        String user="";
        Scanner sc = new Scanner(System.in);
        
        try{
            daoFactory = new DAO_Factory();

            daoFactory.activateConnection();

            LoginDAO dao=daoFactory.getLoginDAO();

            while(true){
                System.out.print("1)Login\n2)Register\n3)Exit\nEnter a choice: ");
                int choice=sc.nextInt();
                if(choice==1){
                    System.out.println("Login");
                    System.out.print("Enter username: ");
                    String username=sc.next();
                    System.out.print("Enter password: ");
                    String password=sc.next();
                    int p=dao.Login(username, password);
                    if(p==0){
                        System.out.println("Login Successful");
                        user=username;
                        break;
                    }
                    else if(p==1){
                        System.out.println("Incorrect Password, please try again");
                    }
                    else if(p==2){
                        System.out.println("Username doesn't exist, Please register");
                        
                    }
                }
                else if(choice==2){
                    //Need to change this,by adding entry in student and hostelroom table
                    System.out.println("Register");
                    System.out.print("Enter username: ");
                    String username=sc.next();
                    System.out.print("Enter password: ");
                    String password=sc.next();
                    int p=dao.Register(username, password);

                    if(p==0){
                        System.out.println("Registration Successful");
                        user=username;
                        break;
                    }
                    else System.out.println("Registration failed, please try again");
                }
                else if(choice==3) System.exit(0);
            }

            daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
        }
        catch(Exception e){
            daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
            e.printStackTrace();
        }

        
        try{
            daoFactory = new DAO_Factory();          
            ClientUI UIC=new ClientUI();
            AdminUI UI = new AdminUI();
            boolean flag = false;

            if(user.equals("Admin")){
            while(true)
            {
                daoFactory.activateConnection();  
                System.out.print("\033[H\033[2J");  
                System.out.flush();
                int i = UI.displayIntialPrompt();
                
                switch(i)
                {
                    case 1:{
                        System.out.print("\033[H\033[2J");  
                        System.out.flush();
                        RTPCR list = UI.displayRTPCRPrompt1();
                        RTPCRDAO sdao = daoFactory.getRTPCRDAO();

                        sdao.enterRTPCR(list);
                        daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.COMMIT);

                        break;
                    }

                    case 2:{
                        System.out.print("\033[H\033[2J");  
                        System.out.flush();
                        int c = UI.displayRTPCRPrompt2();
                        
                        useCase1_DAO sdao = daoFactory.getuseCase1DAO();
                        switch(c)
                        {
                            case 1:{
                                
                                String s1a = UI.displayRTPCRPrompt2a();
                                ArrayList<useCase1> list = sdao.getStudentRTPCR_Status(s1a);
                                UI.displayRTPCRDetailsOfStudent(list);
                                break;
                            }
                            
                            case 2:{
                                ArrayList<String> list = UI.displayRTPCRPrompt2b();
                                useCase1 uc1 = sdao.getStudentRTPCR_Status(list.get(0), list.get(1));
                                UI.displayRTPCRdetails(uc1);
                                break;
                            }

                            case 3:{
                                String date = UI.displayRTPCRPrompt2c();
                                ArrayList<useCase1> list = sdao.getCollegeRTPCR_Status(date);
                                UI.displayRTPCRDetailsOnDate(list);
                                break;
                            }

                            case 4:{
                                String batch = UI.displayRTPCRPrompt2d();
                                ArrayList<useCase1> list = sdao.getBatchRTPCR_Status(batch);
                                UI.displayRTPCRdetails(list);
                                break;
                            }
                        }
                        
                        break;
                    }

                    case 3:{
                        System.out.print("\033[H\033[2J");  
                        System.out.flush();
                        useCase3_DAO sdao = daoFactory.getuseCase3DAO();
                        StudentDAO dao=daoFactory.getStudentDAO();
                        String id = UI.displayVaccinationPrompt3a();
                        int i3 = sdao.getCurrentDoseNo(id);
                        if(i3==-1){
                            System.out.println("Student doesn't exist");
                            break;
                        } 
                        ArrayList<String> list = UI.displayVaccinationPrompt3b(i3);
                        
                        useCase3 uc3 = new useCase3(id, i3, list.get(0), list.get(1), list.get(2));

                        sdao.enterIntoDosesTable(uc3);

                        if(!uc3.getvStatus().equals("null"))
                        {
                            sdao.updateVaccinationStatus(uc3);
                        }

                        daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.COMMIT);
                        break;
                    }

                    case 4:{
                        System.out.print("\033[H\033[2J");  
                        System.out.flush();

                        useCase4_DAO sdao = daoFactory.getuseCase4DAO();
                        StudentDAO studentdao=daoFactory.getStudentDAO();
                        int c4 = UI.displayVaccinationPrompt4();

                        System.out.print("\033[H\033[2J");  
                        System.out.flush();
                        switch(c4)
                        {
                            case 1:{
                                
                                String s1a = UI.displayVaccinationPrompt4a();
                                ArrayList<useCase4> list = sdao.getStudentDoses(s1a);
                                if(list.size()==0){
                                    System.out.println("Student doesn't exist");
                                    break;
                                }
                                UI.displayVaccinationDetailsOfStudent(list);
                                break;
                            }
                            
                            case 2:{
                                ArrayList<useCase4> list = sdao.getAllStudentDoses();
                                UI.displayVaccinationDetailsOfStudent(list);
                                break;
                            }

                            case 3:{
                                ArrayList<useCase4b> list = sdao.getVaccinationDetails();
                                UI.displayVaccinationDetails(list, studentdao.getStudentCount());
                                break;
                            }

                            case 4:{
                                String batch = UI.displayRTPCRPrompt2d();
                                ArrayList<useCase4b> list = sdao.getVaccinationDetailsBatch(batch);
                                UI.displayVaccinationDetailsOfBatch(list, batch, studentdao.getStudentCountByBatch(batch));
                                break;
                            }
                        }
                        break;
                    }

                    case 5:{
                        System.out.print("\033[H\033[2J");  
                        System.out.flush();
                        useCase5_DAO sdao = daoFactory.getuseCase5DAO();
                        int i5 = UI.displayInfectedPrompt5();
                       
                        System.out.print("\033[H\033[2J");  
                        System.out.flush();

                        switch(i5)
                        {
                            case 1:{
                                ArrayList<useCase5> list = sdao.getInfectedList();
                                UI.displayInfectedDetailsList(list);
                                break;
                            }

                            case 2:{
                                String batch = UI.displayInfectedPrompt5b();
                                ArrayList<useCase5> list = sdao.getInfectedListByBatch(batch);
                                UI.displayInfectedDetailsListByBatch(list,batch);
                                break;
                            }
                        }
                        break;
                    }

                    case 6:
                    { 
                        System.out.print("\033[H\033[2J");  
                        System.out.flush();

                        useCase6_DAO sdao = daoFactory.getuseCase6DAO();
                        
                        int i5 = UI.displayInfectedPrompt6();
                       
                        System.out.print("\033[H\033[2J");  
                        System.out.flush();
                        
                        switch(i5)
                        {
                            case 1:{
                                ArrayList<String> list = UI.displayUpdateInfected_Prompt2();
                                sdao.updateInfectedDetails(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4));
                                
                                break;
                            }

                            case 2:{
                                ArrayList<String> list = UI.displayUpdateInfected_Prompt3();
                                sdao.removeInfectedDetails(list.get(0));
                                break;
                            }
                        }
                        daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.COMMIT);
                        break;
                    }

                    case 7:{
                        System.out.print("\033[H\033[2J");  
                        System.out.flush();

                        useCase7_DAO dao = daoFactory.getuseCase7DAO();
                        
                        ArrayList<useCase7a> uc7a=dao.getInfectedStudentsList();

                        ArrayList<ArrayList<useCase7b>> uc7b = dao.getRooomatesOfInfectedStudents(uc7a);

                        UI.displayRoomatesOfInfectedStudents(uc7b, uc7a);

                        break;
                    }

                    case 8: {
                        System.out.print("\033[H\033[2J");  
                        System.out.flush();

                        useCase8DAO sdao = daoFactory.getuseCase8DAO();
                        
                        int us8 = UI.displayInfectedPrompt8();
                        
                        System.out.print("\033[H\033[2J");  
                        System.out.flush();
                        
                        switch(us8)
                        {
                            case 1:{
                                HostelDAO hrr=daoFactory.getHostelDAO();
                                ArrayList<useCase8> hostel = new ArrayList<useCase8>(0);
                                String roomNo=UI.display8a();
                                useCase8 hr=sdao.getRoomDetails(roomNo);
                                hostel.add(hr);
                                if(Objects.isNull(hr)){
                                    System.out.println("Invalid roomNo");
                                }
                                else{
                                    UI.Printrooms(hostel, hrr.getHostelCapacity("Hostel"), hrr.getHostelVacancy("Hostel"));
                                } 
                                break;
                            }

                            case 2:{
                                HostelDAO hrr=daoFactory.getHostelDAO();
                                ArrayList<useCase8> hostel = sdao.getAllRoomDetails("Hostel");
                                UI.Printrooms(hostel, hrr.getHostelCapacity("Hostel"), hrr.getHostelVacancy("Hostel"));
                                break;
                            }

                            case 3:{
                                HostelDAO hrr=daoFactory.getHostelDAO();
                                ArrayList<HostelRoom> hostel = hrr.getallRooms();
                                UI.PrintQuarantine(hostel, hrr.getHostelCapacity("Quarantine"), hrr.getHostelVacancy("Quarantine"));
                                break;
                            }

                            case 4:{
                                HostelDAO hrr=daoFactory.getHostelDAO();
                                ArrayList<HostelRoom> hostel = hrr.getEmptyHRooms();
                                UI.PrintQuarantine(hostel, hrr.getHostelCapacity("Quarantine"), hrr.getHostelVacancy("Quarantine"));
                            }
                        }
                        break;
                    }
                    case 9: {

                        break;
                    }
                    case 10:{
                        flag=true;
                        break;
                    }
                }

                if(flag) break;
                System.out.println("Please 0 enter to continue");
                sc.next();
            }
            }

            else if(user.equals("")==false){
                while(true){
                    daoFactory.activateConnection();  
                    System.out.print("\033[H\033[2J");  
                    System.out.flush();
                    int i = UIC.displayIntialPrompt(user);
                    
                    switch(i){
                        case 1:{
                            useCase1_DAO sdao=daoFactory.getuseCase1DAO();
                            ArrayList<useCase1> list = sdao.getStudentRTPCR_Status(user);
                            UI.displayRTPCRDetailsOfStudent(list);
                            break;
                        }
                        case 2: {
                            useCase4_DAO sdao=daoFactory.getuseCase4DAO();
                            ArrayList<useCase4> list = sdao.getStudentDoses(user);
                            UI.displayVaccinationDetailsOfStudent(list);
                            break;
                        }
                        case 3: {
                            flag=true;
                            break;
                        }
                    }

                    if(flag) break;
                    System.out.println("Please 0 enter to continue");
                    sc.next();
                }
            }

            daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.COMMIT);

        }catch(Exception e){
                //Handle errors for Class.forName
                daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
                e.printStackTrace();
        }
        sc.close();
    } 
    
}

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

public class ClientUI {
    public int displayIntialPrompt(String username)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("******************************************** OUTBREAK OBSERVER ********************************************************");
        System.out.println("                         \033[3m Track the spread, Fight covid-19, Protect your community.\033[0m\n"); 
        System.out.println("Hello " + username+"! Welcome to home screen of Outbreak Observer.\n What action would you like to take:\n");
        System.out.println("\t 1) Get RTPCR details.");
        System.out.println("\t 2) Get Vaccination details.");
        System.out.println("\t 3) Quit");
        int i;
        while(true)
        {
            String s = sc.next();
            if(s.equals("quit")) s="-1";

            if(s.equals("-1") || s.equals("0") || s.equals("1") || s.equals("2") || s.equals("3") ) 
            {
                i = Integer.parseInt(s); 
                return i;
            }
        }       
    }

    
}

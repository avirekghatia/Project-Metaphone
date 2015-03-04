/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Krishna Ghatia
 */
import java.util.*;
import javax.xml.bind.annotation.XmlElement;

public class BootClass {
    public static void main(String[] args)
    {
        System.out.println("Hi! Welcome to the Employee Management System");
        menu();
        menuSelection();
    }


public static void menu()
{
    System.out.println("Menu");
    System.out.println("1. Add an employee");
    System.out.println("2. Modify employee details");
    System.out.println("3. Delete an employee");
    System.out.println("4. Search an employee");
    System.out.println("5. List all employees");
}

public static void menuSelection()
{
    int input;
    Scanner in = new Scanner(System.in);
    input=in.nextInt();
    userInteraction interaction = new userInteration();
    switch(input)
    {
        case 1:
            interaction.addEmployee();  //add an employee
            break;
            
        case 2:
            interaction.updateEmployee();   //modify an employee
            break;
            
        case 3:
            interaction.removeEmployee();   //delete
            break;
        
        case 4:
            interaction.searchEmployee();   //search
            break;
            
        case 5:
            interaction.listEmployee();     //list
            break;
            
        default:
            System.out.println("Please enter a valid choice");
            break;
    }
}

}
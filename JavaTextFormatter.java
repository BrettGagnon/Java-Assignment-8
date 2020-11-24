/*
Brett Gagnon
Intro to Java Programming
Text Formatting Program #8
11/3/2018
 */
package javatextformatter;

import java.io.BufferedReader;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


/**
 *
 * @author brett
 */
public class JavaTextFormatter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
        // Create a scanner to read from keyboard
    Scanner sc = new Scanner (System.in);
    //variable to hold the formatted output width
    int fwidth;
    
        
        do{
             //Prompt user to enter a size between 30-100
             System.out.println("Enter a maximum formatted output width (30-100):");
            
             //Assign the value entered to variable fwidth
             fwidth = sc.nextInt();
            
             //if the width is less than 30 or geater than 100, prompt error message
             if (fwidth < 30 || fwidth > 100)
             {
                System.out.println("Invalid Entry!!"); 
             }
          // While the width is less than 1 or greater than 10, repeat the do-while  
        } while (fwidth < 30 || fwidth > 100);
        
        //Declare variables
        String fileName;
        File fileObject;
        Scanner sc2 = new Scanner(System.in);
        
        
        //do while to repeat following steps until a valid input file is entered
        do{
        //Prompt for the file name of the input text file
        System.out.println("Enter the file name of the input text file");
        fileName = sc2.nextLine();
       
        //Create new File object named fileObject
        fileObject = new File(fileName);
        
        //Check whether the file exists
        if (!fileObject.exists())
            System.out.println("No file by that name.");
        
        //Check if you can read from the file
        if(!fileObject.canRead())
            System.out.println("Not allowed to read from that file.");
        
        //Open the file for reading using the Scanner class
        Scanner inputStream = null;
        try
        {
            inputStream = new Scanner(fileObject);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not Found");
            System.exit(0);
        }
       
        while (inputStream.hasNext())
        {
            String line = inputStream.nextLine();
            System.out.println(line);
        }
        inputStream.close();
        
        }while (!fileObject.exists() || !fileObject.canRead());
        
        //next do while to get ouput file
        
        //Initialize variables
        String response;
        File fileObject2;
        do
        {
            //Prompt for the file name of the output file to write the formatted words to
            System.out.println("Enter the name of the output file:");
            Scanner sc3 = new Scanner (System.in);
            String fileName2;
            fileName2 = sc3.nextLine();
            
            //Create a new File object to test if the file exists
            
            fileObject2 = new File(fileName2);
            
            //Check whether the file exists
            if (fileObject2.exists())
            {
                //prompt user to overwrite or not
                System.out.println("This file already exists, do you want to "
                    + "overwrite? yes or no");
                Scanner sc4 = new Scanner (System.in);
                response = sc4.nextLine();
                
                //if the response is yes
                if (response.equalsIgnoreCase("yes"))
                {
                 break;
                }
            }
            else
            {
               //prompt to create new file or not
                //prompt user to overwrite or not
                System.out.println("Do you want to create a new file?");
                Scanner sc5 = new Scanner (System.in);
                response = sc5.nextLine(); 
            }
        
        } while (response.equalsIgnoreCase("no") );

        try
        {
            //new Scanner object with fileObject2
            String text = "";
            String addText = "";
            String newText= "";
            Scanner sc6 = new Scanner(fileObject);
            PrintWriter outputStream2 = new PrintWriter (new FileOutputStream(fileObject2));

            //Add heading of stars to the beginning of the file
            //In order to add a header, we must copy the contents of the file to string
            //result, doing this line by line.
            
            //string for asterisk header with length of fwidth
            String line2 = "";
            for (int n = 0; n < fwidth; n++)
            {
             line2 = line2 + "*";
            }

            //add the header which now exists in line2 to the beginning of the file
            outputStream2.write(line2 + "\r\n");

            //while there are still words in the text file
            while (sc6.hasNext())
            {
                addText = sc6.next() + " ";
                newText = text + addText;
             
             if (newText.length() > fwidth)
             {
                 outputStream2.print(text + "\r\n");
                 text = addText;
             }
             else
             {
                 text = newText;
             }
            }
            outputStream2.print(text);
            outputStream2.close();
        } catch (FileNotFoundException e)
        {
            
        }
    }
    
}

import java.io.*;
import java.util.ArrayList;
import java.util.Date;


//Class that is exactly the same as the other projects before it, but this time we are adding in
//File objects and Files to add onto the directories that we were originally doing
public class getSubDir 
{
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Top Level Vars and Containers

    ArrayList<dirObj> dirObjArrayList = new ArrayList<dirObj>();        //ArrayList of dirObj objects

    ArrayList<fileObj> fileObjArrayList = new ArrayList<fileObj>();     //ArrayList of file objects

    











//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Fill Object Methods

    //Method to iterate through the files and populate the fields in the objects
    //and then add them to the arrayList above, taking the arguments of a String and a dirObj
    public void getSub(String path, dirObj dirParent)
    { 
        
        

        File root = new File(path);                                                 //Creating file object and array to loop through
        File[] list = root.listFiles();                                             //static area created by the File.listFiles() method that will fill the array with all of the files/objects in the root dir

        
        for(File f : list)                                                          //Our loop that does the work
        {
            
            if(f.isDirectory())
            {
                dirObj currentDir = new dirObj();                                   //creates a new dirObj object
                currentDir.dirName = f.getName();                                   //sets the name of the object to the file.getName()
                currentDir.parentIndex = dirObjArrayList.indexOf(dirParent);        //Sets the parentIndex of the dirObj to the index of the parent obj in the arraylist
                dirObjArrayList.add(currentDir);                                    //adds the obj to the arrayList
                dirParent.childrenIndexes.add(dirObjArrayList.indexOf(currentDir)); //adds the index of the current obj to the parent obj.childrenIndexes arraylist
                getSub(f.getAbsolutePath(), currentDir);                            //recurses through the function
            }
            
            else if(f.isFile())                                                     //if the file object in the file array is a file, then do these things
            {
                fileObj currentFileObj = new fileObj();                             //Create a new fileObj     
                fillFileObj(currentFileObj, f, dirObjArrayList.indexOf(dirParent)); //run the fillFileObj method with arguments of the new fileObj, the file f, and the index of the parent dir
                dirParent.childrenFiles.add(fileObjArrayList.indexOf(currentFileObj));  //adds the index of the file in the file arrayList into an arraylist in the dirObj class pointing to all the files inside of it
            }
        }

        
    }

    //Method to start the beginning of the program taking a String as the argument
    public void firstEntry(String path)
    {
        //Timer
        timer firstEntryTimer = new timer();

        dirObj rootObj = new dirObj();              //creates a dirObj object
        File root = new File(path);                 //File based off of the path
        rootObj.dirName = root.getName();           //sets the name of the dirObj object
        rootObj.parentIndex = 0;                    //sets the parent index of the root, which should always be 0
        dirObjArrayList.add(rootObj);               //adds object to the arrayList
        getSub(path, rootObj);                      //calls the getSub method to begin populating the rest of the arrayList

        //ending the timer
        firstEntryTimer.startStop = false;
        firstEntryTimer.stopTime();
        firstEntryTimer.printTime("firstEntry");
    } 

    //Method to fill my fileObj class fields
    public void fillFileObj(fileObj fileObject, File tempFile, int index)
    {
        //Timer
        timer fillFileObjTimer = new timer();

        String originalName = tempFile.getName();                                               //Creates a string to manipulate for our fields below for fileName and fileExtension
        int periodIndex = originalName.indexOf('.');                                            //Creates an int to hold the value of where the period is in our fileName
        Date lastModified = new Date(tempFile.lastModified());                                  //Creates a date Object for us to use
        

        fileObject.lastAccessedDate = lastModified;                                             //Sets the lastAccessedDate field to the long of tempFile.lastModified
        if(originalName.length() > 0 && periodIndex > 0)
        {
            fileObject.fileName = originalName.substring(0,periodIndex);                            //Sets the name of the fileName field to the file name
            fileObject.fileExtension = originalName.substring(periodIndex, originalName.length());  //Field to hold the extension of the file which is demoninated by a period in the filename
        }
        fileObject.dirIndex = index;                                                            //sets the directory index field to the index argument to point to its parent dir
        
        
        fileObject.fileSize = tempFile.length();                                                //Field to hold the size of the file

        //Added functionality that we just never got to
        //fileObject.dateCreated = ;                                                            //Field to hold the date that the file was created
        
        fileObjArrayList.add(fileObject);                                                       //Adds the file Object to the arrayList
        
        //stopping the Timer
        fillFileObjTimer.startStop = false;
        fillFileObjTimer.stopTime();
        fillFileObjTimer.printTime("fillFileObj");

    }
        













//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Display Methods

    //Method to print our info out about the directory objects
    public void displayInfo()
    {
        //Timer
        timer displayInfoTimer = new timer();

       for(int i = 0; i < dirObjArrayList.size(); i++)
       {
           System.out.println("=====================================================================");                                 //Prints visual break line
           System.out.println("Directory Name: " + dirObjArrayList.get(i).dirName);                                                     //Prints out dirName
           System.out.println("Parent Index: " + dirObjArrayList.get(dirObjArrayList.get(i).parentIndex).dirName);                      //Prints out dirName of Parent index
           System.out.println("Current Index: " + i);                                                                                   //Prints out the current index
           System.out.print("     |\n     V\n");                                                                                        //Prints some lines for visual effect
           if(dirObjArrayList.get(i).childrenIndexes.size() > 0)                                                                        //Checks for a childrenIndex arrayList in our dirObj
            {
                 
                for(int c = 0; c < dirObjArrayList.get(i).childrenIndexes.size(); c++)                                                  //Nested loop to print out 
                {
                    System.out.println("     Child: " + dirObjArrayList.get(dirObjArrayList.get(i).childrenIndexes.get(c)).dirName);    //Prints out the dirObjArrayList index that the child points to
                }
                
            }
            else System.out.println("     No Children Directories");
            if(dirObjArrayList.get(i).childrenFiles.size() > 0)
            {
                for(int r = 0; r < dirObjArrayList.get(i).childrenFiles.size(); r++)
                {
                    System.out.println("    File: " + fileObjArrayList.get(dirObjArrayList.get(i).childrenFiles.get(r)).fileName);
                }
            }                                                                    //Tells us there aren't any children dir
            System.out.println("--------------------------------------------------------------------");                                 //Prints visual break line
       }

       //stopping the timer
       displayInfoTimer.startStop = false;
       displayInfoTimer.stopTime();
       displayInfoTimer.printTime("displayInfo");
    }

    //Just prints out my fileObj objects, using for testing currently
    public void displayFiles() 
    {
        //timer
        timer displayFilesTimer = new timer();

        System.out.println("Files------------------------------");                             //prints out a visual break
        for(fileObj f : fileObjArrayList)                                                      //enhanced for loop to iterate over every object in the arraylist
        {                                                                                      //Loop that prints out all of the fields of the object
            System.out.println("FileName: " + f.fileName);                                      
            System.out.println("Last Modified: " + f.lastAccessedDate);
            System.out.println("Directory Index: " + f.dirIndex);
            System.out.println("Date Created: " + f.dateCreated);
            System.out.println("File Size: " + f.fileSize + " bytes");
            System.out.println("File Extension: " + f.fileExtension);
            System.out.println("=====================================");                        //Visual Break
        }

        //stopping timer
        displayFilesTimer.startStop = false;
        displayFilesTimer.stopTime();
        displayFilesTimer.printTime("displayFiles");
    }

       //Recursive method to print the subDirectories in a tree format taking a dirObj and String as arguments
       public void displayTree(dirObj currentObj, String indent)
       {
            

           System.out.println(indent + currentObj.dirName);                                                     //Prints out the current dir
           if(currentObj.childrenFiles.size() > 0)
           {
               for(int c = 0; c < currentObj.childrenFiles.size(); c++)
               {
                   System.out.println("   " + indent + "File: " + fileObjArrayList.get(currentObj.childrenFiles.get(c)).fileName);
               }
           }
           for(int i = 0; i < currentObj.childrenIndexes.size(); i++)                                           //loop to go through the childrenIndex ArrayList of current dir
           {
               displayTree(dirObjArrayList.get(currentObj.childrenIndexes.get(i)), indent + "   ");             //displays the dirObjArrayList index object of current dirs children that point to it
           }

         
       }
   
       //Method to start our displayTree method above
       public void startTreeDisplay()
       {
            //timer
            timer startTreeTimer = new timer();

            displayTree(dirObjArrayList.get(0), "");                                                             //gets the tree started with the root as the first entry

            startTreeTimer.startStop = false;
            startTreeTimer.stopTime();
            startTreeTimer.printTime("startTreeDisplay");
       }









}

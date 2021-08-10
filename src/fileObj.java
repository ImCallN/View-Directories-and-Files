import java.util.Date;

//Class that will contain our file object info

public class fileObj 
{
    String fileName;                    //name of file
    Date dateCreated;                   //Date the file was created
    String fileExtension;               //file extension, for example ".txt"
    long fileSize;                      //in bytes
    Date lastAccessedDate;              //date the file was last modified
    int dirIndex;                       //the parent index of the file
}

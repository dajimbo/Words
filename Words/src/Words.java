import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Words {

    // Reads input from a local file path and parses each word separated by
    // whitespace to count the number of occurences of each word
    // and output the word with the maximum number of occurences
    public static String numOccurrences(String filePath)
    {
        //stores the words of the file
        String input = readfile(filePath);

        String result = "";
        int currOcc = 0;
        int maxOcc = 0;
        String currLetter = "";
        String nextLetter = "";

        //We must initialize currInput and end for the first word in
        //the file otherwise we wont get to the second loop for comparison
        String currInput = input.substring(0, input.indexOf(" "));
        int end = currInput.length();

        for (int i = 0; i < input.length() ; i++)
        {
            currLetter = input.substring(i, i + 1);
            currOcc = 1;

            //Case1: Variable "end" cannot be the nextIndex of " "
            //for the final word, so we search for the last index of
            //whitespace and set our var end to the inputs length.
            if(i == input.lastIndexOf(" "))
            {
                end = input.length();
                currInput = input.substring(i + 1, input.length());
            }

            //Case2: For all words other than first and last word
            //currInput will start on the index after whitespace
            //and end on the index of the NEXT whitespace found
            else if(currLetter.equals(" ")) {
                end= input.indexOf(" ", i + 1);
                currInput = input.substring(i + 1,end);
            }

            //Here we ensure currLetter is not a whitespace or punctuation before
            //entering the second loop and comparing
            if(currLetter.matches("\\w"))
            {
                for (int j = i + 1; j < end; j++)
                {
                    nextLetter = input.substring(j, j + 1);
                    if (currLetter.equalsIgnoreCase(nextLetter))
                    {
                        currOcc++;
                        if (currOcc > maxOcc)
                        {
                            maxOcc = currOcc;
                            result = currInput;

                        //Ensures that if a tie occurs the first word with maxOcc will output
                        } else if (currOcc == maxOcc) {
                            result = result;
                        }//elif
                    }//if
                }//for
            }//if
        }//for

        //gets rid of all punctuation marks using regex and outputs the result
        return result.replaceAll("\\W", "");
    }

    //Reads a file using a local file path
    public static String readfile(String filePath)  {
       File file = new File(filePath);
        String input = "";
        try {
            Scanner scan = new Scanner(new File(filePath));
            while(scan.hasNext())
            {
                input += scan.next() + " ";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return input;
    }

    public static void main(String [] args)
    {
      String path = "Enter path here";
      System.out.println(numOccurrences("C:\\java\\test.txt"));
    }
}

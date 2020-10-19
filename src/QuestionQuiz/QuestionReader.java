package QuestionQuiz;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class QuestionReader {
    static ArrayList<String> Questions = new ArrayList<>();
    static ArrayList<String> Answers = new ArrayList<>();
   static  Random r = new Random();
    int Result;

    public QuestionReader(String file) throws FileNotFoundException {
        Scanner in = new Scanner(new FileReader(file));
        String line = "";
        while (in.hasNextLine()) {
            line = in.nextLine();

            if (line.endsWith("?"))
                Questions.add(line);
            else
                Answers.add(line);

        }

    }

        public String Question(int i){
        try{
              Result = r.nextInt(Questions.size()-0) + 0;

            return Questions.get(Result);} catch(IndexOutOfBoundsException e){
            return "No more questions in file.";
        }
    }
    public String Answer(int i){
        try{
return Answers.get(Result);} catch(IndexOutOfBoundsException e){
            return "No more answers in file. ";
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        QuestionReader read = new QuestionReader("/Users/ibbys/Desktop/SampleQuiz.txt");
        for (String s : Answers) {
            System.out.println(s);
        }

    }
}

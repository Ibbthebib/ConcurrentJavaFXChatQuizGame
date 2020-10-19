package Samplejavafx;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import javafx.scene.Parent;

import java.util.Scanner;

//public class Question {
//     String prompt;
//    String answer;
//
//    public Question(String prompt, String answer){
//        this.prompt = prompt;
//        this.answer = answer;
//    }
//
//
//
//    public static void takeTest(Question[] questions){
//        int score = 0;
//        Scanner input = new Scanner(System.in);
//        for(int i =0; i<questions.length; i++){
//            System.out.println(questions[i].prompt);
//            String answer = input.nextLine();
//            if(answer.equals(questions[i].answer)){
//                score++;
//            }
//
//        }
//        System.out.println("you got " + score + "/" + questions.length);
//    }
//
//    public String toString(){
//        return prompt + " " + answer;
//    }
//    public static void main(String[] args) {
//        String q1 = "What colour are apples\n" + "(a)red/green\n(b)Orange\n(c)Magenta\n";
//        String q2 = "What colour are Bananas\n" + "(a)red/green\n(b)Yellow\n(c)Blue\n";
//
//        Question[] question = {new Question(q1,"a"), new Question(q2, "b")};
//
//       takeTest(question);
//    }
//
//
//}

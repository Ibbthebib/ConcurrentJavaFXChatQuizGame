//package Samplejavafx;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.collections.ObservableMap;
//
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.Scanner;
//
//public class QuizDataSet implements Runnable {
//
////    static ObservableList<Question> questions = FXCollections.observableArrayList();
//    ArrayList<Question> questions ;
////    static ObservableList<String> answers = FXCollections.observableArrayList();
//
//
//    public QuizDataSet(String file) throws IOException {
//        Scanner in = new Scanner(new FileReader(file));
//        String question = "";
//        String answer = "";
//        int count = 0;
//        while (in.hasNextLine()) {
//            if (count % 2 == 0) {
//                question = in.nextLine() + "\n";
//            }
//            if (count % 2 == 1) {
//                answer = in.nextLine() + "\n";
//            }
//            count++;
//            questions.add(new Question(question, answer));
//
//
//        }
//
//
//    }
//    public static void takeTest(ArrayList<Question> questions){
//        int score = 0;
//        Scanner input = new Scanner(System.in);
//        for(int i =0; i<questions.size(); i++){
//            System.out.println(questions.get(i).prompt);
//            String answer = input.nextLine();
//            if(answer.equals(questions.get(i).answer)){
//                score++;
//            }
//
//        }
//        System.out.println("you got " + score + "/" + questions.size());
//    }
////        public String getNextLine(){
////
////
////        return getNextLine()
////        }
//
//
//    public static void main(String[] args) throws IOException {
//        QuizDataSet data = new QuizDataSet("/Users/ibbys/Desktop/QuizDataSet.txt");
//
////        questions.forEach(x -> System.out.println(x));
//
//        takeTest(data.questions);
////        System.out.println(Arrays.toString(answers.toArray()));
//    }
//
//    @Override
//    public void run() {
////questions.forEach(x -> System.out.println(x));
//    }
//}
//
//

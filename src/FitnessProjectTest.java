import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;


/* 
 * Description of product:
 * This project is a fitness journal in which there is an info screen where you can learn about the different
 * exercises for different muscle groups. As well, there is the exercise tracking aspect in which you may track
 * your various exercises for both cardio and weightlifting. This data is all saved, and you can then click a graph
 * button which will show various graphs for different time periods, where you can select times periods and exercises
 * to show things such as reps, weights, and calories burned
 * 
 * Features:
 * - adding weightlifting exercises
 * - only valid inputs
 * - invalid characters aren't inputted into textboxes
 * - each exercise you can click on an info button for more information
 * - each field has an info button you can hover over for information
 * - videos show up for the exercise selected
 * 
 * Major Skills:
 * - reading in data from text fields
 * - reading the data into non volatile text files
 * - loading the data back into the program into arrays
 * - Using the JFreeChart API and sorting through all the data in order to display useful graphs with input from the user
 * - Using a mouselistener to determine where on the human body is being clicked, and displaying appropriate exercises
 * 
 * Areas of Concern:
 * - user creates more than 50 users
 * - checking if the delimiter is an input inside the account name creation
 * - seeing if a duplicate account is created
 * 
 * Files neeeded:
 * - all gif files for the exercises
 * - text files storing data for each user (generated)
 * - file for extra exercises
 * - all the files for icons
 * 
 * Potential errors:
 * - if the delimiter is present in any of the fields, the program will crash
 * - if another account with the same username and password is created, it is accepted and the second one wont do anything
 * - if the user creates more than 50 accounts, it will run out of text files
 * 
 */

public class FitnessProjectTest {


	//file for list of exercises
   public static File exerciseFile = new File("res/ExtraExercises.txt");
   public static File cardioExerciseFile = new File("res/CardioExtraExercises.txt");
   
   //file for list of users
   public static File userDataFile = new File("res/userDataFile.txt");

   //array of files for each user
   public static File[] cardioFiles = new File[50];
   public static File[] weightFiles = new File[50];

   //arraylist of exercises
   public static ArrayList<String> cardioExercises = new ArrayList<String>();
   public static ArrayList<Exercise> weightliftingExercises = new ArrayList<Exercise>();
   public static ArrayList<String> exercises = new ArrayList<String>();

	/**
	 * @author: Masum, Shrill
	 */
   public static void main(String[] args) throws IOException, FontFormatException {


	   //create files for users
       cardioFiles[0] = new File("res/cardioFile0.txt");
       weightFiles[0] = new File("res/weightFile0.txt");
       cardioFiles[1] = new File("res/cardioFile1.txt");
       weightFiles[1] = new File("res/weightFile1.txt");
       cardioFiles[2] = new File("res/cardioFile2.txt");
       weightFiles[2] = new File("res/weightFile2.txt");
       cardioFiles[3] = new File("res/cardioFile3.txt");
       weightFiles[3] = new File("res/weightFile3.txt");
       cardioFiles[4] = new File("res/cardioFile4.txt");
       weightFiles[4] = new File("res/weightFile4.txt");
       cardioFiles[5] = new File("res/cardioFile5.txt");
       weightFiles[5] = new File("res/weightFile5.txt");
       cardioFiles[6] = new File("res/cardioFile6.txt");
       weightFiles[6] = new File("res/weightFile6.txt");
       cardioFiles[7] = new File("res/cardioFile7.txt");
       weightFiles[7] = new File("res/weightFile7.txt");
       cardioFiles[8] = new File("res/cardioFile8.txt");
       weightFiles[8] = new File("res/weightFile8.txt");
       cardioFiles[9] = new File("res/cardioFile9.txt");
       weightFiles[9] = new File("res/weightFile9.txt");
       cardioFiles[10] = new File("res/cardioFile10.txt");
       weightFiles[10] = new File("res/weightFile10.txt");
       cardioFiles[11] = new File("res/cardioFile11.txt");
       weightFiles[11] = new File("res/weightFile11.txt");
       cardioFiles[12] = new File("res/cardioFile12.txt");
       weightFiles[12] = new File("res/weightFile12.txt");
       cardioFiles[13] = new File("res/cardioFile13.txt");
       weightFiles[13] = new File("res/weightFile13.txt");
       cardioFiles[14] = new File("res/cardioFile14.txt");
       weightFiles[14] = new File("res/weightFile14.txt");
       cardioFiles[15] = new File("res/cardioFile15.txt");
       weightFiles[15] = new File("res/weightFile15.txt");
       cardioFiles[16] = new File("res/cardioFile16.txt");
       weightFiles[16] = new File("res/weightFile16.txt");
       cardioFiles[17] = new File("res/cardioFile17.txt");
       weightFiles[17] = new File("res/weightFile17.txt");
       cardioFiles[18] = new File("res/cardioFile18.txt");
       weightFiles[18] = new File("res/weightFile18.txt");
       cardioFiles[19] = new File("res/cardioFile19.txt");
       weightFiles[19] = new File("res/weightFile19.txt");

       
       //open homescreen
       new HomeScreen();
       HomeScreen frame = new HomeScreen();
       frame.setVisible(true);


   }

   //method for setting up all cardio exercises
   public static void initalizeCardioExercises(){

	   //reset list
       cardioExercises.clear();
       
       //add everything to list
       cardioExercises.add("Walking");
       cardioExercises.add("Running");
       cardioExercises.add("Biking");
       cardioExercises.add("Swimming");
       cardioExercises.add("Steps Machine");

//       try {
//           NewExercise.load2("res/CardioExtraExercises.txt");
//       } catch (IOException e) {
//           // TODO Auto-generated catch block
//           e.printStackTrace();
//       }

   }
   
   //method for setting up weightlifting exercises
   public static void initalizeWeightliftingExercises(){

	   //reset list
       weightliftingExercises.clear();
       
       //add everything to list
       weightliftingExercises.add(new Exercise("Flat Bench Press", Weightlifting.FLAT_BENCH_PRESS, "Chest"));
       weightliftingExercises.add(new Exercise("Decline Bench Press", Weightlifting.DECLINE_BENCH_PRESS, "Chest"));
       weightliftingExercises.add(new Exercise("Incline Bench Press", Weightlifting.INCLINE_BENCH_PRESS, "Chest"));
       weightliftingExercises.add(new Exercise("Front Press (military or DB)", Weightlifting.FRONT_PRESS, ""));
       weightliftingExercises.add(new Exercise("Dead Lift", Weightlifting.DEADLIFT, "Legs"));
       weightliftingExercises.add(new Exercise("Squats", Weightlifting.SQUATS, "Legs"));
       weightliftingExercises.add(new Exercise("One Arm Dumbbell Deadlift", Weightlifting.ONE_ARM_DEADLIFT, "Back"));
       weightliftingExercises.add(new Exercise("Leg Extensions", Weightlifting.LEG_EXTENSIONS, "Legs"));
       weightliftingExercises.add(new Exercise("Hamstring Curls", Weightlifting.HAMSTRING_CURLS, ""));
       weightliftingExercises.add(new Exercise("Calf Raises", Weightlifting.CALF_RAISES, "Legs"));
       weightliftingExercises.add(new Exercise("Shoulder Press", Weightlifting.SHOULDER_PRESS, "Shoulders"));
       weightliftingExercises.add(new Exercise("Lateral Raises", Weightlifting.LATERAL_RAISES, ""));
       weightliftingExercises.add(new Exercise("Front Raises", Weightlifting.FRONT_RAISES, ""));
       weightliftingExercises.add(new Exercise("Upright Row", Weightlifting.UPRIGHT_ROW, "Shoulders"));
       weightliftingExercises.add(new Exercise("Straight Bar Curls", Weightlifting.STRAIGHT_BAR_CURLS, "Biceps"));
       weightliftingExercises.add(new Exercise("Dumbbell Hammer Curls", Weightlifting.DB_HAMMER_CURLS, "Biceps"));
       weightliftingExercises.add(new Exercise("Tricep Extension", Weightlifting.TRICEP_EXTENSION, ""));
       weightliftingExercises.add(new Exercise("Skull Crushers", Weightlifting.SKULL_CRUSHERS, "Triceps"));
       weightliftingExercises.add(new Exercise("Lateral Pulldowns", Weightlifting.LATERAL_PULLDOWNS, "Back"));
       weightliftingExercises.add(new Exercise("Single Arm DB Rows", Weightlifting.SINGLE_ARM_DB_ROWS, "Back"));
       weightliftingExercises.add(new Exercise("Bench Squat", Weightlifting.BENCH_SQUAT, "Legs"));
       weightliftingExercises.add(new Exercise("V-Bar Extensions", Weightlifting.V_BAR_EXTENSIONS, "Triceps"));
       weightliftingExercises.add(new Exercise("Fly's", Weightlifting.FLY, "Chest"));

       //add user created exercises to the list
       try {
           NewExercise.load("res/ExtraExercises.txt");

       } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
   }
}



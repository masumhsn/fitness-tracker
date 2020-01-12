//declares all imports needed for this screen
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.Component;
import java.awt.GridLayout;


//tells the class that this screen extends JFrame and also uses ActionListener
public class HomeScreen extends JFrame implements ActionListener {

   private JPanel contentPane;                                              //declares the JPanel as contentPane
   private JLabel fitnessTrackerLabel = new JLabel("My Fitness Journal");   //the title label for this screen
   private JButton startButton = new JButton("Login");                      //declaring a button thats going to show up on the screen
   private JButton setupButton = new JButton("Create an Account");                     //declaring a button thats going to show up on the screen


   /**
    * Launch the application.
    */
   public static void main(String[] args) {
       Scanner name = new Scanner(System.in)
               ;     EventQueue.invokeLater(new Runnable() {
           public void run() {
               try {
                   HomeScreen frame = new HomeScreen();
                   frame.setVisible(true);
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
       });
   }

   /**
    * Create the frame.
    */
   //constructor method
   public HomeScreen() {

       setContentPane(new JLabel(new ImageIcon("res/images/BlueGradient.png")));     //setting the contentPane as a JLabel for the background
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                             //telling the screen to exit the program when closed
       setBounds(0, 0, 1000, 622);                                                 //setting bounds for the JFrame
       contentPane = new JPanel();
       contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));

       fitnessTrackerLabel.setBounds(330, -19, 400, 133);                          //setting bounds for the title
       fitnessTrackerLabel.setFont(new Font("Athelas", Font.PLAIN, 45));           //setting the font and size
       add(fitnessTrackerLabel);                                                   //adding the label onto the screen

       startButton.addActionListener(this);                                        //telling the button that its going to be using ActionListener
       startButton.setBounds(340, 250, 300, 50);                                   //setting the bounds
       startButton.setFont(new Font("Athelas", Font.PLAIN, 30));                   //setting the font and size of the words inside the label
       startButton.setForeground(new Color(0, 0, 0));
       startButton.setBackground(Color.WHITE);                                     //setting the color of the button
       add(startButton);                                                           //adding start button onto the screen

       setupButton.addActionListener(this);                                        //telling the button that its going to be using ActionListener
       setupButton.setBounds(340, 350, 300, 50);                                   //setting the bounds
       setupButton.setBackground(new Color(255, 255, 255));
       setupButton.setFont(new Font("Athelas", Font.PLAIN, 30));                   //setting the font and size of the words inside the label
       add(setupButton);                                                           //adding the set up button onto the screen


       //to load the Weightlifting class because in the next screen we need the array list from it for the exercises

       FitnessProjectTest.initalizeWeightliftingExercises();

       for(int x = 0; x < FitnessProjectTest.weightliftingExercises.size(); x++){
           FitnessProjectTest.exercises.add(FitnessProjectTest.weightliftingExercises.get(x).getExercise());
       }

   }

   //method thats going to be telling the buttons what to do if clicked on
   @Override
   public void actionPerformed(ActionEvent event) {

       //if the start button is clicked on, lead the user to the info screen
       if(event.getSource()==startButton) {
           try {
               new LoginScreen();
               dispose();
           } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
           }
       }

       //if the set up button is clicked on, lead the user to the set up button
       if(event.getSource()==setupButton) {
           try {
               new CreateAccount();
               dispose();
           } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
           }
       }
   }

}  //end of class



//declaring all imports needed for this screen
import java.awt.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.EmptyBorder;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

//tells the class that its extending JFrame and also using ActionListener and MouseListener
@SuppressWarnings("serial")
public class InfoScreen extends JFrame implements ActionListener, MouseListener {

   //adds the image of the body diagram as JLabel
   private JLabel bodyImage = new JLabel(new ImageIcon("Images/bodymuscleimage.png"));

   private BufferedImage backIcon = ImageIO.read(new File("res/Images/back-button.png"));
   private JButton back = new JButton(new ImageIcon(backIcon));

   @SuppressWarnings("rawtypes")
   JList bodyList = new JList();                                  //declares that there is going to be a JList
   JScrollPane pane = new JScrollPane(bodyList);                  //adds a scrollbar to the JList if needed
   DefaultListModel<String> DLM=new DefaultListModel<>();         //declares that the DLM will be an array String

   //declaring all buttons
   //declaring all the labels as images
   JLabel chestExerciseLabel = new JLabel(new ImageIcon("Images/chest.png"));
   JLabel backExerciseLabel = new JLabel(new ImageIcon("Images/back.png"));
   JLabel legExerciseLabel = new JLabel(new ImageIcon("Images/leg1.png"));
   JLabel legExerciseLabel2 = new JLabel(new ImageIcon("Images/leg2.png"));
   JLabel shoulderExerciseLabel = new JLabel(new ImageIcon("Images/shoulder1.png"));
   JLabel shoulderExerciseLabel2 = new JLabel(new ImageIcon("Images/shoulder2.png"));
   JLabel bicepExerciseLabel = new JLabel(new ImageIcon("Images/bicep.png"));
   JLabel tricepExerciseLabel = new JLabel(new ImageIcon("Images/tricep.png"));
   JButton trackCardioButton = new JButton("Track Cardio");
   JButton trackWeightliftingButton = new JButton("Track Weightlifting");
   //attempt to make a textArea for the pop-up
   //public String textArea;

   /**
    * Create the frame.
    */
   //constructor method
   @SuppressWarnings({ "rawtypes", "unchecked" })
   public InfoScreen() throws IOException {

       //sets the layout of the screen and sets contentPane as a JLabel to display a background
       this.setLayout(null);
       setContentPane(new JLabel(new ImageIcon("res/Images/RedGradient.jpg")));

       //tells the JFrame to close when exited
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       //sets the bound for the screen
       setBounds(0, 0, 1000, 622);


       //places the body outline diagram on the screen
       add(bodyImage);
       bodyImage.setBounds(10, 0, 700, 600);

       back.setBorder(BorderFactory.createEmptyBorder());
       back.setContentAreaFilled(false);
       back.setBounds(10, 10, 100, 76);
       add(back);
       back.addActionListener(this);
       back.setToolTipText("Go back to the info screen.");

       chestExerciseLabel.addMouseListener(this);              //makes the label clickable
       chestExerciseLabel.setBounds(303, 120, 107, 47);        //sets the bounds
       bodyImage.add(chestExerciseLabel);                      //adds the label onto the body image instead of contentPane

       backExerciseLabel.addMouseListener(this);               //makes the label clickable
       backExerciseLabel.setBounds(305, 98, 100, 25);          //sets the bounds
       bodyImage.add(backExerciseLabel);                       //adds the label onto the body image instead of contentPane

       legExerciseLabel.addMouseListener(this);                //makes the label clickable
       legExerciseLabel.setBounds(255, 260, 150, 300);         //sets the bounds
       bodyImage.add(legExerciseLabel);                        //adds the label onto the body image instead of contentPane

       legExerciseLabel2.addMouseListener(this);               //makes the label clickable
       legExerciseLabel2.setBounds(310, 260, 150, 300);        //sets the bounds
       bodyImage.add(legExerciseLabel2);                       //adds the label onto the body image instead of contentPane

       shoulderExerciseLabel.addMouseListener(this);           //makes the label clickable
       shoulderExerciseLabel.setBounds(280, 115, 50, 50);      //sets the bounds
       bodyImage.add(shoulderExerciseLabel);                   //adds the label onto the body image instead of contentPane

       shoulderExerciseLabel2.addMouseListener(this);          //makes the label clickable
       shoulderExerciseLabel2.setBounds(380, 114, 50, 50);     //sets the bounds
       bodyImage.add(shoulderExerciseLabel2);                  //adds the label onto the body image instead of contentPane

       bicepExerciseLabel.addMouseListener(this);              //makes the label clickable
       bicepExerciseLabel.setBounds(238, 152, 100, 100);       //sets the bounds
       bodyImage.add(bicepExerciseLabel);                      //adds the label onto the body image instead of contentPane

       tricepExerciseLabel.addMouseListener(this);             //makes the label clickable
       tricepExerciseLabel.setBounds(375, 147, 100, 100);      //sets the bounds
       bodyImage.add(tricepExerciseLabel);                     //adds the label onto the body image instead of contentPane

       DLM = new DefaultListModel();                           //declares a new default list model for the exercises
       bodyList = new JList(DLM);                              //calls the list "bodyList" as a JList

       //setting the bounds for bodyList and adding it onto contentPane
       bodyList.setBounds(610, 100, 300, 200);
       add(bodyList);

       //button to go to the cardio tracking screen
       trackCardioButton.addActionListener(this);
       trackCardioButton.setFont(new Font("Athelas", Font.PLAIN, 20));
       trackCardioButton.setBounds(580, 450, 300, 40);
       add(trackCardioButton);

       //button to go to the weightlifting tracking screen
       trackWeightliftingButton.addActionListener(this);
       trackWeightliftingButton.setFont(new Font("Athelas", Font.PLAIN, 20));
       trackWeightliftingButton.setBounds(580, 500, 300, 40);
       add(trackWeightliftingButton);
   }


   //method to tell each button what to do if clicked on
   public void actionPerformed(ActionEvent event) {

       //tells the "Track Cardio" button to go to the Cardio screen if clicked on
       if (event.getSource()==trackCardioButton) {
           try {
               new Cardio();
           } catch (IOException e) {
               e.printStackTrace();
           } catch (FontFormatException e) {
               e.printStackTrace();
           }
           dispose();
       }

       //tells the "Track Weightlifting" button to go to the Weightlifting screen if clicked on
       if (event.getSource()==trackWeightliftingButton) {
           try {
               new Weightlifting();
           } catch (IOException e) {
               e.printStackTrace();
           }
           dispose();
       }

       //this was the attempt for the clickable JList
//    if (event.getSource()==bodyList) {
//
//            //description(textArea).getDescription();
//
//    }

   }

   //this method tells each label what to do when clicked on
   @Override
   public void mouseClicked(MouseEvent event) {

       //doesn't let the information for each label repeat if its clicked on more than once
       DLM.clear();

       /*
        * in all these if statements, the labels are told that if a label is clicked on
        * they will display a certain amount of exercises, for that body part, in the JList
        */

       //if the chest icon(label) is clicked on, it will display certain exercises for the chest onto the Jlist
       if (event.getSource()==chestExerciseLabel) {
           DLM.addElement(FitnessProjectTest.weightliftingExercises.get(0).getExercise());
           DLM.addElement(FitnessProjectTest.weightliftingExercises.get(1).getExercise());
           DLM.addElement(FitnessProjectTest.weightliftingExercises.get(2).getExercise());
           DLM.addElement(FitnessProjectTest.weightliftingExercises.get(22).getExercise());
       }

       //if the back icon(label) is clicked on, it will display certain exercises for the bacl onto the Jlist
       if (event.getSource()==backExerciseLabel) {
           DLM.addElement(FitnessProjectTest.weightliftingExercises.get(6).getExercise());
           DLM.addElement(FitnessProjectTest.weightliftingExercises.get(18).getExercise());
           DLM.addElement(FitnessProjectTest.weightliftingExercises.get(19).getExercise());
       }

       //if the leg icon(label) is clicked on, it will display certain exercises for the leg onto the Jlist
       if (event.getSource()==legExerciseLabel) {
           DLM.addElement(FitnessProjectTest.weightliftingExercises.get(4).getExercise());
           DLM.addElement(FitnessProjectTest.weightliftingExercises.get(5).getExercise());
           DLM.addElement(FitnessProjectTest.weightliftingExercises.get(7).getExercise());
           DLM.addElement(FitnessProjectTest.weightliftingExercises.get(9).getExercise());
           DLM.addElement(FitnessProjectTest.weightliftingExercises.get(20).getExercise());
       }

       //if the second leg icon(label) is clicked on, it will display same exercises for the leg onto the Jlist
       if (event.getSource()==legExerciseLabel2) {
           DLM.addElement(FitnessProjectTest.weightliftingExercises.get(4).getExercise());
           DLM.addElement(FitnessProjectTest.weightliftingExercises.get(5).getExercise());
           DLM.addElement(FitnessProjectTest.weightliftingExercises.get(7).getExercise());
           DLM.addElement(FitnessProjectTest.weightliftingExercises.get(9).getExercise());
           DLM.addElement(FitnessProjectTest.weightliftingExercises.get(20).getExercise());
       }

       //if the shoulder icon(label) is clicked on, it will display certain exercises for the shoulder onto the Jlist
       if (event.getSource()==shoulderExerciseLabel) {
           DLM.addElement(FitnessProjectTest.weightliftingExercises.get(10).getExercise());
           DLM.addElement(FitnessProjectTest.weightliftingExercises.get(13).getExercise());
       }

       //if the second shoulder icon(label) is clicked on, it will display the same exercises for the shoulder onto the Jlist
       if (event.getSource()==shoulderExerciseLabel2) {
           DLM.addElement(FitnessProjectTest.weightliftingExercises.get(10).getExercise());
           DLM.addElement(FitnessProjectTest.weightliftingExercises.get(13).getExercise());
       }

       //if the bicep icon(label) is clicked on, it will display certain exercises for the bicep onto the Jlist
       if (event.getSource()==bicepExerciseLabel) {
           DLM.addElement(FitnessProjectTest.weightliftingExercises.get(14).getExercise());
           DLM.addElement(FitnessProjectTest.weightliftingExercises.get(15).getExercise());
       }

       //if the tricep icon(label) is clicked on, it will display certain exercises for the tricep onto the Jlist
       if (event.getSource()==tricepExerciseLabel) {
           DLM.addElement(FitnessProjectTest.weightliftingExercises.get(17).getExercise());
           DLM.addElement(FitnessProjectTest.weightliftingExercises.get(21).getExercise());
       }

   }


   @Override
   public void mousePressed(MouseEvent e) {

   }

   @Override
   public void mouseReleased(MouseEvent e) {

   }

   @Override
   public void mouseEntered(MouseEvent e) {

   }

   @Override
   public void mouseExited(MouseEvent e) {

   }

}  //end of class


/*
* /i attempted this
*/

//is going to make a JList for each label that's clicked on, on the body diagram

//chest exercises JList

////back exercises JList
//JList backList = new JList();
//backList.setBounds(650, 60, 300, 300);
//contentPane.add(chestList);

////legs exercises JList
//JList legsList = new JList();
//legsList.setBounds(650, 60, 300, 300);
//contentPane.add(legsList);

////shoulders exercise JList
//JList shouldersList = new JList();
//shouldersList.setBounds(650, 60, 300, 300);
//contentPane.add(shouldersList);

////biceps exercise JList
//JList bicepsList = new JList();
//bicepsList.setBounds(650, 60, 300, 300);
//contentPane.add(bicepsList);

////triceps exercise JList
//JList tricepList = new JList();
//tricepList.setBounds(650, 60, 300, 300);
//contentPane.add(tricepList);

/*
* also attempted this
* was trying to make a pop up box when the user clicked on each exercise in the JList
*/

///////////////////////////////////////////////////////////////////////////////////////////////////////////
//private String Flat_Bench_Press = "Lie down on the bench press and make sure the " +
//"bar is right over your chest. Arch your back so " +
//"your chest is pumping out. Grab the bar with your" +
//" hands shoulder width apart. Slowly lower the bar until" +
//" it hits your chest then push the bar up and repeat.";
//
//private String Decline_Bench_Press = "Lie down on the bench press and make sure the " +
//"bar is right over your chest. Lie down in way that your " +
//"body is below the horizontal. Arch your back so your chest " +
//"is pumping out. Grab the bar with your hands shoulder width " +
//"apart. Slowly lower the bar till it hits your lower chest " +
//"then push the bar up and repeat.";
//
//private String Incline_Bench_Press = "Lie down on the bench press and make sure the " +
//"bar is right over your chest. Lie down in way that your " +
//"body is above the horizontal.Arch your back so your chest " +
//"is pumping out. Grab the bar with your hands shoulder width " +
//"apart. Slowly lower the bar till it hits your upper chest then " +
//"push the bar up and repeat.";
//
//private String Fly = "Sit down on the fly chair and adjust the fly handles. Adjusting " +
//"the handles behind you will make the reps easier, adjusting " +
//"the handles more in front of you will make the reps easier. " +
//"Grab the handles, and then put together in front of your chest. Squeeze " +
//"your chest as the handles meet, and make sure your arms are straight. Repeat.";
//
//private String Deadlift = "Get into a explosive position. Bend knees, chest up, back " +
//"straight. Grab the bar with your hands shoulder width apart, and " +
//"stay in this position. Keeping your arms straight, lift and " +
//"straighten your body like a squat. Lower then repeat.";
//
//private String Squats = "Grab the bar with your hands so it stays balanced on your " +
//"trapezius, then lower. Place the bar along your trapezius " +
//"muscles, just below the neck. Get into a explosive position. " +
//"Bend knees, chest up, back straight. After that push up and " +
//"straighten legs. Repeat.";
//
//private String ONE_ARM_DEADLIFT = "Stand up straight with your feet shoulder-width " +
//"apart.Hold the dumbbell in your right hand and bend your left " +
//"knee so that you are standing on just your right leg. Bend forward " +
//"and lower the weight toward the ground. Point your left leg out " +
//"behind you to help maintain balance. Return to the starting position.";
//
//private String LEG_EXTENSIONS = "For this exercise you will need to use a leg extension " +
//"machine. First choose your weight and sit on the machine with your " +
//"legs under the pad (feet pointed forward) and the hands holding the " +
//"side bars. This will be your starting position. Using your quadriceps, " +
//"extend your legs to the maximum as you exhale. Ensure that the rest of " +
//"the body remains stationary on the seat. Pause a second on the contracted " +
//"position. Slowly lower the weight back to the original position as you " +
//"inhale, ensuring that you do not go past the 90-degree angle limit. Repeat " +
//"for the recommended amount of times.";
//
//private String HAMSTRING_CURLS = "Start by lying face down on the hamstring curl machine. " +
//"The pad of the machine should be on the back of your legs at the bottom " +
//"of your calves. Keeping your body flat on the bench, lightly grasp the " +
//"handles near the front to stabilize your upper body. Bend your knees to " +
//"bring your heels toward your glutes stopping once your knees have reached " +
//"a 90 degree angle. Hold for one second. Slowly lower down for three seconds. " +
//"Repeat for a desired number of repetitions.";
//
//private String CALF_RAISES = "Adjust the padded lever of the calf raise machine to fit your " +
//"height. Place your shoulders under the pads provided and position your toes " +
//"facing forward. The balls of your feet should be secured on top of the calf " +
//"block with the heels extending off it. Push the lever up by extending your " +
//"hips and knees until your torso is standing erect. The knees should be kept " +
//"with a slight bend; never locked. Toes should be facing forward. This will " +
//"be your starting position. Raise your heels as you breathe out by extending " +
//"your ankles as high as possible and flexing your calf. Ensure that the knee " +
//"is kept stationary at all times. There should be no bending at any time. Hold " +
//"the contracted position by a second before you start to go back down. Go back " +
//"slowly to the starting position as you breathe in by lowering your heels as " +
//"you bend the ankles until calves are stretched. Repeat for the recommended " +
//"amount of repetitions.";
//
//private String FRONT_RAISES = "Find a set of dumbbells which are of suitable weight for you. " +
//"Standing erect, feet shoulder width apart, grasp the dumbbells down by your " +
//"sides .Raise one arm up in front of you, with a slight bend in the elbow. " +
//"Continue raising the dumbbell until your arm is parallel with the ground. " +
//"Slowly reverse the movement to bring the dumbbell back down to the side. " +
//"Repeat the same action with the opposite arm. Repeat for the desired number " +
//"of repetitions.";
//
//private String FRONT_PRESS = "Stand with feet shoulder-width apart and grip the bar " +
//"with your fingertips, elbows pointing forward. Rest the bar on the " +
//"front of your shoulders. Drop down into a shallow squat, centring " +
//"your weight under the barbell. Press up through your heels. Drive " +
//"the bar directly above your head until your arms are straight. " +
//"Lower the bar down to your chest. Maintain a neutral arch in your " +
//"spine throughout the move.";
//
//private String SHOULDER_PRESS = "Hold a dumbbell in each hand and sit on a bench with " +
//"back support. Plant your feet firmly on the floor about hip-width " +
//"apart. Bend your elbows and raise your upper arms to shoulder " +
//"height so the dumbbells are at ear level. Pull your abdominals " +
//"in so there is a slight gap between the small of your back and " +
//"the bench. Place the back of your head against the pad. Push the " +
//"dumbbells up and in until the ends of the dumbbells touch lightly, " +
//"directly over your head, and then lower the dumbbells back to ear level.";
//
//private String LATERAL_RAISES = "May be performed in a standing or sitting positions. Be " +
//"sure that if you are seated your back is straight; if standing your " +
//"knees are slightly bent to protect your lower back. In a standing " +
//"position - with your feet firmly placed for balance; grasp a dumbbell " +
//"in each hand. Keep the dumbbells at hanging straight down at your " +
//"sides. Simultaneously, raise the dumbbells directly to your sides. Be " +
//"sure to turn your palm facing down, and have your elbows slightly bent. Your " +
//"hand, elbow and shoulder form a parallel line. Hold for 1-2 seconds and then " +
//"release to the starting position.";
//
//private String UPRIGHT_ROW = "Grasp a dumbbell in each hand with a pronated (palms forward) grip " +
//"that is slightly less than shoulder width. The dumbbells should be " +
//"resting on top of your thighs. Your arms should be extended with a " +
//"slight bend at the elbows and your back should be straight. This will " +
//"be your starting position. Use your side shoulders to lift the dumbbells " +
//"as you exhale. The dumbbells should be close to the body as you move " +
//"it up and the elbows should drive the motion. Continue to lift them " +
//"until they nearly touch your chin. Lower the dumbbells back down slowly " +
//"to the starting position. Inhale as you perform this portion of the " +
//"movement. Repeat for the recommended amount of repetitions.";
//
//private String STRAIGHT_BAR_CURLS = "Stand up with your torso upright while holding a barbell at a " +
//"shoulder-width grip. The palm of your hands should be facing forward " +
//"and the elbows should be close to the torso. This will be your starting " +
//"position. While holding the upper arms stationary, curl the weights " +
//"forward while contracting the biceps as you breathe out. Only the " +
//"forearms should move. Continue the movement until your biceps are " +
//"fully contracted and the bar is at shoulder level. Hold the contracted " +
//"position for a second and squeeze the biceps hard. Slowly begin to bring " +
//"the bar back to starting position as your breathe in. Repeat for the " +
//"recommended amount of repetitions.";
//
//private String DB_HAMMER_CURLS = "Stand up with your torso upright and a dumbbell on each hand being " +
//"held at arm's length. The elbows should be close to the torso. The palms of " +
//"the hands should be facing your torso. This will be your starting position. " +
//"Now, while holding your upper arm stationary, exhale and curl the weight forward " +
//"while contracting the biceps. Continue to raise the weight until the biceps are " +
//"fully contracted and the dumbbell is at shoulder level. Hold the contracted position " +
//"for a brief moment as you squeeze the biceps. Tip: Focus on keeping the elbow " +
//"stationary and only moving your forearm. After the brief pause, inhale and slowly " +
//"begin the lower the dumbbells back down to the starting position. Repeat for the " +
//"recommended amount of repetitions.";
//
//private String TRICEP_EXTENSION = "Start with your feet shoulder width apart with a deep bend in your " +
//"knees for a strong athletic base. With your elbows tucked tight in front of your " +
//"body, slowly extend you arms and fully contracting your triceps. Bend your elbows " +
//"and slowly return your arms back to the starting position and repeat.";
//
//private String SKULL_CRUSHERS = "Load an EZ bar with the correct weight and place on the safety collars. Lay " +
//"flat on a bench, grasping the EZ bar above your head with a shoulder width, overhand " +
//"grip. Keeping the upper arms perpendicular to the ground, lower the weight by " +
//"descending the forearms to bring the bar to the top of the head. Stop once the " +
//"forearms just below parallel to the ground. Reverse the movement by extending " +
//"the arms back to the starting position. Do not lock out the elbows at the top of " +
//"the exercise. Repeat for the desired number of repetitions.";
//
//private String LATERAL_PULLDOWNS = "Attach a wide grip handle to the lat pulldown machine and assume a seated " +
//"position. Grasp the handle with a pronated grip (double overhand) and initiate " +
//"the movement by depressing the shoulder blade and then flexing the elbow while " +
//"extending the shoulder. Pull the handle towards your body until the elbows are " +
//"in line with your torso and then slowly lower the handle back to the starting " +
//"position under control. Repeat for the desired number of repetitions.";
//
//private String SINGLE_ARM_DB_ROWS = "Choose a flat bench and place a dumbbell on each side of it. Place the right " +
//"leg on top of the end of the bench, bend your torso forward from the waist until " +
//"your upper body is parallel to the floor, and place your right hand on the other " +
//"end of the bench for support. Use the left hand to pick up the dumbbell on the " +
//"floor and hold the weight while keeping your lower back straight. The palm of " +
//"the hand should be facing your torso. This will be your starting position. Pull " +
//"the resistance straight up to the side of your chest, keeping your upper arm close " +
//"to your side and keeping the torso stationary. Breathe out as you perform this " +
//"step. Lower the resistance straight down to the starting position. Breathe in as you " +
//"perform this step. Repeat the movement for the specified amount of repetitions. Switch " +
//"sides and repeat again with the other arm.";
//
//private String BENCH_SQUAT = "Set up for the bench squat by setting the barbell to just below shoulder height and " +
//"loading the weight you want to use. Move a flat bench between the rack so that one end " +
//"is underneath the barbell. Stand under the bar with your feet at about shoulder width " +
//"apart on either side of the bench. Position the bar so that it is resting on the muscles " +
//"on the top of your back, not on the back of your neck. The bar should feel comfortable. If " +
//"it doesn't, try adding some padding to the bar. Now take your hands over the back and grip " +
//"the bar with a wide grip for stability. You should now bend at the knees and straighten your " +
//"back in preparation to take the weight off the rack. Keeping your back straight and eyes up, " +
//"push up through the legs and take the weight off the rack. Take a small step back and stabilize " +
//"yourself. Keeping your eyes facing forwards, slowly lower your body down. Don't lean forward as " +
//"you come down. Your buttocks should come out and drop straight down. Squat down until your buttocks " +
//"touches the bench, and then slowly raise your body back up by pushing through your heels. Do not " +
//"rest on the bench! Do not lock the knees out when you stand up, and then repeat the movement.";
//
//private String V_BAR_EXTENSIONS = "Attach a v-bar to a cable stack as high as possible and assume a standing " +
//"position. Grasp the v-bar with a semi pronated grip (palms slightly facing) and lean " +
//"forward slightly by hinging at the hips. Initiate the movement by extending the elbows " +
//"and flexing the triceps. Pull the handle downward until the elbows are almost locked out " +
//"and then slowly lower under control back to the starting position. Repeat for the desired " +
//"number of repetitions.";
//////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//public void description(String description) {
//JTextArea textArea = new JTextArea(description);
//textArea.setEditable(false);
//JScrollPane scrollPane = new JScrollPane(textArea);
//textArea.setLineWrap(true);
//textArea.setWrapStyleWord(true);
//scrollPane.setPreferredSize(new Dimension(300, 150));
//JOptionPane.showMessageDialog(textArea,scrollPane, "Instructions", JOptionPane.INFORMATION_MESSAGE);
//}



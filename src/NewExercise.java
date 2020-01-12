 import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

public class NewExercise extends JFrame implements ActionListener {

	//number of added exercises 
   public static int numAdded = 0;
   public static int numAddedCardio = 0;

   //JLabels for headers
   private JLabel screenTitle = new JLabel("Add New Exercises");
   private JLabel weightlifting = new JLabel("Weightlifting:");
   private JLabel cardio = new JLabel("Cardio:");
   private JLabel weightliftingName = new JLabel("Name:");
   private JLabel weightliftingDescription = new JLabel("Discription:");
   private JLabel weightliftingBodyType = new JLabel("Body Type:");
   private JLabel cardioName = new JLabel("Name:");

   //jlabel for image on corner
   private JLabel img = new JLabel(new ImageIcon("res/Images/ThinkstockPhotos-515657174.jpg"));
   
   //border of image
   private Border border = BorderFactory.createLineBorder(Color.BLACK, 5);

   //text fields to enter input about new exercises
   JTextField[] newExInputs = new JTextField[3];
   JTextField newExInputs2 = new JTextField();

   //create fonts
   private Font font = new Font("Sylfaen", Font.BOLD, 60);
   private Font font2 = new Font("Sylfaen", Font.BOLD, 30);
   private Font font3 = new Font("Sylfaen", Font.PLAIN, 20);
   
   //create buttons to make exercises
   private JButton saveNewEx = new JButton("Save Exercise");
   private JButton saveNewEx2 = new JButton("Save Exercise");

   //create arrays to read in all exercises
   private static String userOutputsNewEx[][] = new String[500][3];
   private static String userOutputsNewEx2[][] = new String[500][3];

   //arraylist version of previous arrays
   private static ArrayList<String[]> selectOutputsNewEx = new ArrayList<String[]>();

   //index of current exercise number added
   static int index = 0;

   //create buttons and icon for each
   private BufferedImage buttonIcon1 = ImageIO.read(new File("res/Images/info.png"));
   private JButton button1 = new JButton(new ImageIcon(buttonIcon1));

   private BufferedImage buttonIcon2 = ImageIO.read(new File("res/Images/info.png"));
   private JButton button2 = new JButton(new ImageIcon(buttonIcon2));

   private BufferedImage buttonIcon3 = ImageIO.read(new File("res/Images/info.png"));
   private JButton button3 = new JButton(new ImageIcon(buttonIcon3));

   private BufferedImage buttonIcon4 = ImageIO.read(new File("res/Images/info.png"));
   private JButton button4 = new JButton(new ImageIcon(buttonIcon4));

	/**
	 * @author: Masum, Shrill
	 */
   public NewExercise() throws IOException {

	   //setup frame
       setLayout(null);
       setBounds(0, 0, 1000, 600);
       this.setTitle("Add New Exercises");
       setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("res/Images/oranage.jpg")))));
       setResizable(false);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       //set bounds for image
       img.setBounds(575, 280, 400, 275);
       img.setBorder(border);
       add(img);

       //set bounds for labels and buttons
       screenTitle.setForeground(Color.BLACK);
       screenTitle.setFont(font);
       screenTitle.setBounds(200, 10, 600, 65);
       add(screenTitle);

       weightlifting.setForeground(Color.BLACK);
       weightlifting.setFont(font2);
       weightlifting.setBounds(20, 100, 225, 35);
       add(weightlifting);

       weightliftingName.setForeground(Color.BLACK);
       weightliftingName.setFont(font3);
       weightliftingName.setBounds(80, 153, 75, 25);
       add(weightliftingName);

       button1.setBorder(BorderFactory.createEmptyBorder());
       button1.setContentAreaFilled(false);
       button1.setBounds(40, 150, 30, 30);
       add(button1);
       button1.addActionListener(this);
       button1.setToolTipText("Enter the name of your exercise.");

       weightliftingDescription.setForeground(Color.BLACK);
       weightliftingDescription.setFont(font3);
       weightliftingDescription.setBounds(80, 228, 125, 25);
       add(weightliftingDescription);

       button2.setBorder(BorderFactory.createEmptyBorder());
       button2.setContentAreaFilled(false);
       button2.setBounds(40, 225, 30, 30);
       add(button2);
       button2.addActionListener(this);
       button2.setToolTipText("Describe how to perform the exercise.");

       weightliftingBodyType.setForeground(Color.BLACK);
       weightliftingBodyType.setFont(font3);
       weightliftingBodyType.setBounds(80, 303, 130, 25);
       add(weightliftingBodyType);

       button3.setBorder(BorderFactory.createEmptyBorder());
       button3.setContentAreaFilled(false);
       button3.setBounds(40, 300, 30, 30);
       add(button3);
       button3.addActionListener(this);
       button3.setToolTipText("State the body part being worked out.");

       //do not allow incorrect entries
       newExInputs[0] = new JTextField();
       newExInputs[0].addKeyListener(new KeyAdapter() {
           public void keyTyped(KeyEvent e) {
               char c = e.getKeyChar();
               if (((c >= '0') && (c <= '9') ||
                       (c == KeyEvent.VK_BACK_SPACE) ||
                       (c == KeyEvent.VK_DELETE))) {
                   getToolkit().beep();
                   e.consume();
               }
           }
       });
       newExInputs[0].setForeground(Color.BLACK);
       newExInputs[0].addActionListener(this);
       newExInputs[0].setBounds(145, 150, 250, 35);
       add(newExInputs[0]);

       newExInputs[1] = new JTextField();
       newExInputs[1].addKeyListener(new KeyAdapter() {
           public void keyTyped(KeyEvent e) {
               char c = e.getKeyChar();
               if (((c >= '0') && (c <= '9') ||
                       (c == KeyEvent.VK_BACK_SPACE) ||
                       (c == KeyEvent.VK_DELETE))) {
                   getToolkit().beep();
                   e.consume();
               }
           }
       });
       newExInputs[1].setForeground(Color.BLACK);
       newExInputs[1].addActionListener(this);
       newExInputs[1].setBounds(195, 225, 200, 35);
       add(newExInputs[1]);

       newExInputs[2] = new JTextField();
       newExInputs[2].addKeyListener(new KeyAdapter() {
           public void keyTyped(KeyEvent e) {
               char c = e.getKeyChar();
               if (((c >= '0') && (c <= '9') ||
                       (c == KeyEvent.VK_BACK_SPACE) ||
                       (c == KeyEvent.VK_DELETE))) {
                   getToolkit().beep();
                   e.consume();
               }
           }
       });
       newExInputs[2].setForeground(Color.BLACK);
       newExInputs[2].addActionListener(this);
       newExInputs[2].setBounds(190, 300, 205, 35);
       add(newExInputs[2]);

       saveNewEx.setOpaque(false);
       saveNewEx.setContentAreaFilled(false);
       saveNewEx.setBorderPainted(false);
       saveNewEx.setBorder(null);
       saveNewEx.setFont(new Font("Segoe Script", Font.PLAIN, 30));
       saveNewEx.setForeground(Color.BLACK);
       saveNewEx.setBounds(40, 400, 350, 35);
       add(saveNewEx);
       saveNewEx.addActionListener(this);

       // Makes button bigger when hovered over
       saveNewEx.addMouseListener(new java.awt.event.MouseAdapter() {
           public void mouseEntered(java.awt.event.MouseEvent evt) {
               saveNewEx.setForeground(Color.BLACK);
               saveNewEx.setFont(new Font("Segoe Script", Font.PLAIN, 40));
           }

           public void mouseExited(java.awt.event.MouseEvent evt) {
               saveNewEx.setForeground(Color.BLACK);
               saveNewEx.setFont(new Font("Segoe Script", Font.PLAIN, 30));
           }
       });

//       cardio.setForeground(Color.BLACK);
//       cardio.setFont(font2);
//       cardio.setBounds(660, 100, 225, 35);
//       add(cardio);
//
//       cardioName.setForeground(Color.BLACK);
//       cardioName.setFont(font3);
//       cardioName.setBounds(720, 153, 125, 25);
//       add(cardioName);
//
//       button4.setBorder(BorderFactory.createEmptyBorder());
//       button4.setContentAreaFilled(false);
//       button4.setBounds(680, 150, 30, 30);
//       add(button4);
//       button4.addActionListener(this);
//       button4.setToolTipText("Enter the name of your exercise.");
//
//       newExInputs2 = new JTextField();
//       newExInputs2.addKeyListener(new KeyAdapter() {
//           public void keyTyped(KeyEvent e) {
//               char c = e.getKeyChar();
//               if (((c >= '0') && (c <= '9') ||
//                       (c == KeyEvent.VK_BACK_SPACE) ||
//                       (c == KeyEvent.VK_DELETE))) {
//                   getToolkit().beep();
//                   e.consume();
//               }
//           }
//       });
//       newExInputs2.setForeground(Color.BLACK);
//       newExInputs2.addActionListener(this);
//       newExInputs2.setBounds(785, 150, 205, 35);
//       add(newExInputs2);
//
//       saveNewEx2.setOpaque(false);
//       saveNewEx2.setContentAreaFilled(false);
//       saveNewEx2.setBorderPainted(false);
//       saveNewEx2.setBorder(null);
//       saveNewEx2.setFont(new Font("Segoe Script", Font.PLAIN, 30));
//       saveNewEx2.setForeground(Color.BLACK);
//       saveNewEx2.setBounds(660, 200, 350, 35);
//       add(saveNewEx2);
//       saveNewEx2.addActionListener(this);

       // Makes button bigger when hovered over
       saveNewEx2.addMouseListener(new java.awt.event.MouseAdapter() {
           public void mouseEntered(java.awt.event.MouseEvent evt) {
               saveNewEx2.setForeground(Color.BLACK);
               saveNewEx2.setFont(new Font("Segoe Script", Font.PLAIN, 40));
           }

           //returns size to normal when not hovered over
           public void mouseExited(java.awt.event.MouseEvent evt) {
               saveNewEx2.setForeground(Color.BLACK);
               saveNewEx2.setFont(new Font("Segoe Script", Font.PLAIN, 30));
           }
       });

       setVisible(true);

   }

   //create method to save
	/**
	 * @author: Masum, Shrill
	 */
   private void save() {

       try {

    	   //if file doesn't exist, create it
           if (!FitnessProjectTest.exerciseFile.exists()) {
               FitnessProjectTest.exerciseFile.createNewFile();
           }

           //create writers for the files
           FileWriter fWriter = new FileWriter(FitnessProjectTest.exerciseFile, true);
           PrintWriter pWriter = new PrintWriter(fWriter);


           //write in csv format
           for (int y = 0; y < newExInputs.length; y++) {

               pWriter.write(newExInputs[y].getText() + ",");

           }

           pWriter.println();

           //Closing PrintWriter Stream
           pWriter.close();

       } catch (IOException ioe) {
           System.out.println("Exception occurred:");
           ioe.printStackTrace();
       }
   }

   //save method for the cardio
	/**
	 * @author: Shrill
	 */
   private void save2() {

       try {

    	   //if file doesn't exist, create ite
           if (!FitnessProjectTest.cardioExerciseFile.exists()) {
               FitnessProjectTest.cardioExerciseFile.createNewFile();
           }

           //create writers for the files
           FileWriter fWriter = new FileWriter(FitnessProjectTest.cardioExerciseFile, true);
           PrintWriter pWriter = new PrintWriter(fWriter);

           //write in csv format
           pWriter.write(newExInputs2.getText() + ",");

           pWriter.println();

           //Closing PrintWriter Stream
           pWriter.close();

       } catch (IOException ioe) {
           System.out.println("Exception occurred:");
           ioe.printStackTrace();
       }
   }

   //create load method
	/**
	 * @author: Masum
	 * @param: name of file which is being loaded
	 */
   public static void load(String fileName) throws IOException {

	   //get filepath
       Path path = Paths.get(fileName);

       //create scanner to read file
       Scanner scanner = new Scanner(path);

       //reset number of added exercises (the whole process resets every time)
       numAdded = 0;
       
       //while the file isn't finished
       while (scanner.hasNextLine()) {

    	   //get next line
           String line = scanner.nextLine();
           numAdded++;
           userOutputsNewEx[index] = line.split(",");

           index++;

       }

       int selectingIndex = 0;

       //cycle through one line
       for (int x = 0; x < index; x++) {

           for (int y = 0; y < 3; y++) {

        	   //add value to arraylist
               selectOutputsNewEx.add(userOutputsNewEx[x]);

               selectingIndex++;

           }
       }

       //cycle through the array of data
       for (int x = 0; x < selectOutputsNewEx.size(); x += 3) {

           String[] f = new String[3];

           f = selectOutputsNewEx.get(x);

           //if the data wanted
           if ((x/3) < numAdded) {
        	   //add to the list of exercises
               FitnessProjectTest.weightliftingExercises.add(new Exercise(f[0], f[1], f[2]));
           }
       }
   }

   //load for cardio exercises 
	/**
	 * @author: Shrill
	 * @param: name of file which is being loaded
	 */

   public static void load2(String fileName) throws IOException {

       Path path = Paths.get(fileName);

       Scanner scanner = new Scanner(path);
       selectOutputsNewEx.clear();


       numAddedCardio = 0;
       while (scanner.hasNextLine()) {

           String line = scanner.nextLine();
           numAdded++;

           userOutputsNewEx2[index] = line.split(",");

           index++;

       }

       int selectingIndex = 0;

       for (int x = 0; x < index; x++) {

           selectOutputsNewEx.add(userOutputsNewEx2[x]);

           selectingIndex++;

       }
       for (int x = 0; x < selectOutputsNewEx.size();x++) {
           String[] f = new String[27];

           f = selectOutputsNewEx.get(x);

           if (f[0].equals(null)) {
               selectOutputsNewEx.remove(x);
           }
       }


       for (int x = 0; x < selectOutputsNewEx.size(); x ++) {

           String[] f;

           f = selectOutputsNewEx.get(x);

           System.out.println(f[0]);


           if ((x/3) < numAdded)
               FitnessProjectTest.cardioExercises.add(f[0]);

       }
   }

   //create message boxes
	/**
	 * @author: Shrill
	 * @param: string of message to be shown
	 */
   public void closeInfo(String description) {
       JTextArea textArea = new JTextArea(description);
       textArea.setEditable(false);
       JScrollPane scrollPane = new JScrollPane(textArea);
       textArea.setLineWrap(true);
       textArea.setWrapStyleWord(true);
       scrollPane.setPreferredSize(new Dimension(200, 50));
       JOptionPane.showMessageDialog(textArea, scrollPane, "Restart", JOptionPane.OK_OPTION);
   }

	/**
	 * @author: Shrill
	 * @param: string of message to be shown
	 */
   public void error(String description) {
       JTextArea textArea = new JTextArea(description);
       textArea.setEditable(false);
       JScrollPane scrollPane = new JScrollPane(textArea);
       textArea.setLineWrap(true);
       textArea.setWrapStyleWord(true);
       scrollPane.setPreferredSize(new Dimension(200, 50));
       JOptionPane.showMessageDialog(textArea, scrollPane, "Restart", JOptionPane.WARNING_MESSAGE);
   }

   //if action performed
   @Override
   public void actionPerformed(ActionEvent e) {

	   //if save exercise button clicked
       if (e.getSource() == saveNewEx) {

    	   //if all fields not properly put in
           if (newExInputs[0].getText().equals("") || newExInputs[1].getText().equals("") || newExInputs[2].getText().equals("")) {
        	   
        	   //show error message
               error("Please fill out all fields under the weightlifting section!");
           } else {

        	   //save data
               save();
               String name = newExInputs[0].getText();
               String description = newExInputs[1].getText();
               String bodyType = newExInputs[2].getText();
               
               //show message to restart program
               closeInfo("Program must be restarted to update exercises. Please click OKAY and run program again!");

               //close program
               dispose();

           }
       }

       //if cardio save button pressed
       if(e.getSource() == saveNewEx2){

    	   //if not filled
           if (newExInputs2.getText().equals("")) {
               error("Please fill out all fields under the cardio section!");
           } else {

        	   //save to file
               save2();
               String name = newExInputs2.getText();

               //show message to restart program and close it
               closeInfo("Program must be restarted to update exercises. Please click OKAY and run program again!");
               dispose();

           }

       }
       
   }
   
}



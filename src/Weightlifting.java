import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

//create class which creates a jframe upon opening, and can listen to clicked buttons, etc.
public class Weightlifting extends JFrame implements ActionListener {

	//default combobox option
	private String message1 = "- Select an Option -";

	//get image
	private BufferedImage addIcon = ImageIO.read(new File("res/Images/add.png"));
	
	//create button to add new exercise
	private JButton add = new JButton(new ImageIcon(addIcon));

	//array of 3 comboboxes for user input
	public static JComboBox[] userInputs1 = new JComboBox[3];
	
	//array of 3 text fields for user input
	private JTextField[] userInputs2 = new JTextField[3];

	//array of all user inputs when loading in, set to large number
	private static String userOutputs[][] = new String[500][6];

	//arraylist of only useful outputs
	private static ArrayList <String[]>selectOutputs = new ArrayList <String[]>();

	//label with title
	private JLabel screenTitle = new JLabel("Weightlifting Tracker");

	//create 3 fonts for later use
	private Font font = new Font("Sylfaen", Font.BOLD, 60);
	private Font font2 = new Font("Sylfaen", Font.BOLD, 30);
	private Font font3 = new Font("Sylfaen", Font.PLAIN, 20);

	//create label for fitness videos
	private JLabel img;
	private Border border = BorderFactory.createLineBorder(Color.BLACK, 5);

	//create labels for user interface
	private JLabel exercise = new JLabel("Exercise:");
	private JLabel date = new JLabel("Date");
	private JLabel day = new JLabel("Day:");
	private JLabel month = new JLabel("Month:");
	private String[] months = {"January", "February","March","April","May","June","July","August","September","October","November","December"};
	
	//set the date to the current year, month and day
	private int monthField = Calendar.getInstance().get(Calendar.MONTH);
	private String dayField = Integer.toString(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
	private int year = Calendar.getInstance().get(Calendar.YEAR);
	
	//default message for combobox
	private String[] message = {"Enter Month First"};

	//create labels for user interface
	private JLabel weight = new JLabel("Weight:");
	private JLabel reps = new JLabel("Reps:");
	private JLabel sets = new JLabel("Sets:");

	//create button to save data
	private JButton save = new JButton("Save");
	
	//create button to view data in graph
	private JButton graph = new JButton("Graph");

	//get image for info button
	private BufferedImage buttonIcon = ImageIO.read(new File("res/Images/info.png"));
	
	//create button for info 
	private JButton button = new JButton(new ImageIcon(buttonIcon));

	//get image for the 'back' button
	private BufferedImage backIcon = ImageIO.read(new File("res/Images/back-button.png"));
	
	//create button for going to the previous screen
	private JButton back = new JButton(new ImageIcon(backIcon));

	//get image for icon
	private ImageIcon image = new ImageIcon("res/Images/weights.png");

	//create and set index of number of data entries to 0
	static int index = 0;

	//add all the descriptions to an array of strings
	public static String FLAT_BENCH_PRESS = "Lie down on the bench press and make sure the " +
			"bar is right over your chest. Arch your back so " +
			"your chest is pumping out. Grab the bar with your" +
			" hands shoulder width apart. Slowly lower the bar until" +
			" it hits your chest then push the bar up and repeat.";

	public static String DECLINE_BENCH_PRESS = "Lie down on the bench press and make sure the " +
			"bar is right over your chest. Lie down in way that your " +
			"body is below the horizontal. Arch your back so your chest " +
			"is pumping out. Grab the bar with your hands shoulder width " +
			"apart. Slowly lower the bar till it hits your lower chest " +
			"then push the bar up and repeat.";

	public static String INCLINE_BENCH_PRESS = "Lie down on the bench press and make sure the " +
			"bar is right over your chest. Lie down in way that your " +
			"body is above the horizontal.Arch your back so your chest " +
			"is pumping out. Grab the bar with your hands shoulder width " +
			"apart. Slowly lower the bar till it hits your upper chest then " +
			"push the bar up and repeat.";

	public static String FLY = "Sit down on the fly chair and adjust the fly handles. Adjusting " +
			"the handles behind you will make the reps easier, adjusting " +
			"the handles more in front of you will make the reps easier. " +
			"Grab the handles, and then put together in front of your chest. Squeeze " +
			"your chest as the handles meet, and make sure your arms are straight. Repeat.";

	public static String DEADLIFT = "Get into a explosive position. Bend knees, chest up, back " +
			"straight. Grab the bar with your hands shoulder width apart, and " +
			"stay in this position. Keeping your arms straight, lift and " +
			"straighten your body like a squat. Lower then repeat.";

	public static String SQUATS = "Grab the bar with your hands so it stays balanced on your " +
			"trapezius, then lower. Place the bar along your trapezius " +
			"muscles, just below the neck. Get into a explosive position. " +
			"Bend knees, chest up, back straight. After that push up and " +
			"straighten legs. Repeat.";

	public static String ONE_ARM_DEADLIFT = "Stand up straight with your feet shoulder-width " +
			"apart.Hold the dumbbell in your right hand and bend your left " +
			"knee so that you are standing on just your right leg. Bend forward " +
			"and lower the weight toward the ground. Point your left leg out " +
			"behind you to help maintain balance. Return to the starting position.";

	public static String LEG_EXTENSIONS = "For this exercise you will need to use a leg extension " +
			"machine. First choose your weight and sit on the machine with your " +
			"legs under the pad (feet pointed forward) and the hands holding the " +
			"side bars. This will be your starting position. Using your quadriceps, " +
			"extend your legs to the maximum as you exhale. Ensure that the rest of " +
			"the body remains stationary on the seat. Pause a second on the contracted " +
			"position. Slowly lower the weight back to the original position as you " +
			"inhale, ensuring that you do not go past the 90-degree angle limit. Repeat " +
			"for the recommended amount of times.";

	public static String HAMSTRING_CURLS = "Start by lying face down on the hamstring curl machine. " +
			"The pad of the machine should be on the back of your legs at the bottom " +
			"of your calves. Keeping your body flat on the bench, lightly grasp the " +
			"handles near the front to stabilize your upper body. Bend your knees to " +
			"bring your heels toward your glutes stopping once your knees have reached " +
			"a 90 degree angle. Hold for one second. Slowly lower down for three seconds. " +
			"Repeat for a desired number of repetitions.";

	public static String CALF_RAISES = "Adjust the padded lever of the calf raise machine to fit your " +
			"height. Place your shoulders under the pads provided and position your toes " +
			"facing forward. The balls of your feet should be secured on top of the calf " +
			"block with the heels extending off it. Push the lever up by extending your " +
			"hips and knees until your torso is standing erect. The knees should be kept " +
			"with a slight bend; never locked. Toes should be facing forward. This will " +
			"be your starting position. Raise your heels as you breathe out by extending " +
			"your ankles as high as possible and flexing your calf. Ensure that the knee " +
			"is kept stationary at all times. There should be no bending at any time. Hold " +
			"the contracted position by a second before you start to go back down. Go back " +
			"slowly to the starting position as you breathe in by lowering your heels as " +
			"you bend the ankles until calves are stretched. Repeat for the recommended " +
			"amount of repetitions.";

	public static String FRONT_RAISES = "Find a set of dumbbells which are of suitable weight for you. " +
			"Standing erect, feet shoulder width apart, grasp the dumbbells down by your " +
			"sides .Raise one arm up in front of you, with a slight bend in the elbow. " +
			"Continue raising the dumbbell until your arm is parallel with the ground. " +
			"Slowly reverse the movement to bring the dumbbell back down to the side. " +
			"Repeat the same action with the opposite arm. Repeat for the desired number " +
			"of repetitions.";

	public static String FRONT_PRESS = "Stand with feet shoulder-width apart and grip the bar " +
			"with your fingertips, elbows pointing forward. Rest the bar on the " +
			"front of your shoulders. Drop down into a shallow squat, centring " +
			"your weight under the barbell. Press up through your heels. Drive " +
			"the bar directly above your head until your arms are straight. " +
			"Lower the bar down to your chest. Maintain a neutral arch in your " +
			"spine throughout the move.";

	public static String SHOULDER_PRESS = "Hold a dumbbell in each hand and sit on a bench with " +
			"back support. Plant your feet firmly on the floor about hip-width " +
			"apart. Bend your elbows and raise your upper arms to shoulder " +
			"height so the dumbbells are at ear level. Pull your abdominals " +
			"in so there is a slight gap between the small of your back and " +
			"the bench. Place the back of your head against the pad. Push the " +
			"dumbbells up and in until the ends of the dumbbells touch lightly, " +
			"directly over your head, and then lower the dumbbells back to ear level.";

	public static String LATERAL_RAISES = "May be performed in a standing or sitting positions. Be " +
			"sure that if you are seated your back is straight; if standing your " +
			"knees are slightly bent to protect your lower back. In a standing " +
			"position - with your feet firmly placed for balance; grasp a dumbbell " +
			"in each hand. Keep the dumbbells at hanging straight down at your " +
			"sides. Simultaneously, raise the dumbbells directly to your sides. Be " +
			"sure to turn your palm facing down, and have your elbows slightly bent. Your " +
			"hand, elbow and shoulder form a parallel line. Hold for 1-2 seconds and then " +
			"release to the starting position.";

	public static String UPRIGHT_ROW = "Grasp a dumbbell in each hand with a pronated (palms forward) grip " +
			"that is slightly less than shoulder width. The dumbbells should be " +
			"resting on top of your thighs. Your arms should be extended with a " +
			"slight bend at the elbows and your back should be straight. This will " +
			"be your starting position. Use your side shoulders to lift the dumbbells " +
			"as you exhale. The dumbbells should be close to the body as you move " +
			"it up and the elbows should drive the motion. Continue to lift them " +
			"until they nearly touch your chin. Lower the dumbbells back down slowly " +
			"to the starting position. Inhale as you perform this portion of the " +
			"movement. Repeat for the recommended amount of repetitions.";

	public static String STRAIGHT_BAR_CURLS = "Stand up with your torso upright while holding a barbell at a " +
			"shoulder-width grip. The palm of your hands should be facing forward " +
			"and the elbows should be close to the torso. This will be your starting " +
			"position. While holding the upper arms stationary, curl the weights " +
			"forward while contracting the biceps as you breathe out. Only the " +
			"forearms should move. Continue the movement until your biceps are " +
			"fully contracted and the bar is at shoulder level. Hold the contracted " +
			"position for a second and squeeze the biceps hard. Slowly begin to bring " +
			"the bar back to starting position as your breathe in. Repeat for the " +
			"recommended amount of repetitions.";

	public static String DB_HAMMER_CURLS = "Stand up with your torso upright and a dumbbell on each hand being " +
			"held at arm's length. The elbows should be close to the torso. The palms of " +
			"the hands should be facing your torso. This will be your starting position. " +
			"Now, while holding your upper arm stationary, exhale and curl the weight forward " +
			"while contracting the biceps. Continue to raise the weight until the biceps are " +
			"fully contracted and the dumbbell is at shoulder level. Hold the contracted position " +
			"for a brief moment as you squeeze the biceps. Tip: Focus on keeping the elbow " +
			"stationary and only moving your forearm. After the brief pause, inhale and slowly " +
			"begin the lower the dumbbells back down to the starting position. Repeat for the " +
			"recommended amount of repetitions.";

	public static String TRICEP_EXTENSION = "Start with your feet shoulder width apart with a deep bend in your " +
			"knees for a strong athletic base. With your elbows tucked tight in front of your " +
			"body, slowly extend you arms and fully contracting your triceps. Bend your elbows " +
			"and slowly return your arms back to the starting position and repeat.";

	public static String SKULL_CRUSHERS = "Load an EZ bar with the correct weight and place on the safety collars. Lay " +
			"flat on a bench, grasping the EZ bar above your head with a shoulder width, overhand " +
			"grip. Keeping the upper arms perpendicular to the ground, lower the weight by " +
			"descending the forearms to bring the bar to the top of the head. Stop once the " +
			"forearms just below parallel to the ground. Reverse the movement by extending " +
			"the arms back to the starting position. Do not lock out the elbows at the top of " +
			"the exercise. Repeat for the desired number of repetitions.";

	public static String LATERAL_PULLDOWNS = "Attach a wide grip handle to the lat pulldown machine and assume a seated " +
			"position. Grasp the handle with a pronated grip (double overhand) and initiate " +
			"the movement by depressing the shoulder blade and then flexing the elbow while " +
			"extending the shoulder. Pull the handle towards your body until the elbows are " +
			"in line with your torso and then slowly lower the handle back to the starting " +
			"position under control. Repeat for the desired number of repetitions.";

	public static String SINGLE_ARM_DB_ROWS = "Choose a flat bench and place a dumbbell on each side of it. Place the right " +
			"leg on top of the end of the bench, bend your torso forward from the waist until " +
			"your upper body is parallel to the floor, and place your right hand on the other " +
			"end of the bench for support. Use the left hand to pick up the dumbbell on the " +
			"floor and hold the weight while keeping your lower back straight. The palm of " +
			"the hand should be facing your torso. This will be your starting position. Pull " +
			"the resistance straight up to the side of your chest, keeping your upper arm close " +
			"to your side and keeping the torso stationary. Breathe out as you perform this " +
			"step. Lower the resistance straight down to the starting position. Breathe in as you " +
			"perform this step. Repeat the movement for the specified amount of repetitions. Switch " +
			"sides and repeat again with the other arm.";

	public static String BENCH_SQUAT = "Set up for the bench squat by setting the barbell to just below shoulder height and " +
			"loading the weight you want to use. Move a flat bench between the rack so that one end " +
			"is underneath the barbell. Stand under the bar with your feet at about shoulder width " +
			"apart on either side of the bench. Position the bar so that it is resting on the muscles " +
			"on the top of your back, not on the back of your neck. The bar should feel comfortable. If " +
			"it doesn't, try adding some padding to the bar. Now take your hands over the back and grip " +
			"the bar with a wide grip for stability. You should now bend at the knees and straighten your " +
			"back in preparation to take the weight off the rack. Keeping your back straight and eyes up, " +
			"push up through the legs and take the weight off the rack. Take a small step back and stabilize " +
			"yourself. Keeping your eyes facing forwards, slowly lower your body down. Don't lean forward as " +
			"you come down. Your buttocks should come out and drop straight down. Squat down until your buttocks " +
			"touches the bench, and then slowly raise your body back up by pushing through your heels. Do not " +
			"rest on the bench! Do not lock the knees out when you stand up, and then repeat the movement.";

	public static String V_BAR_EXTENSIONS = "Attach a v-bar to a cable stack as high as possible and assume a standing " +
			"position. Grasp the v-bar with a semi pronated grip (palms slightly facing) and lean " +
			"forward slightly by hinging at the hips. Initiate the movement by extending the elbows " +
			"and flexing the triceps. Pull the handle downward until the elbows are almost locked out " +
			"and then slowly lower under control back to the starting position. Repeat for the desired " +
			"number of repetitions.";

	//constructor method
	/**
	 * @author: Shrill
	 */
	public Weightlifting() throws IOException {

		//if the arraylists contain anything, clear it
		if(FitnessProjectTest.exercises.size() > 0) {

			FitnessProjectTest.exercises.clear();

		}
		
		if (FitnessProjectTest.weightliftingExercises.size() > 0) {

			FitnessProjectTest.weightliftingExercises.clear();
		}

		//setup frame to set size and color (full screen)
		setLayout(null);
		setBounds(0, 0, 1000, 622);
		this.setTitle("Weightlifting");
		setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("res/Images/BlueGradient.png")))));
		setResizable(false);
		setSize(1000, 622);
		repaint();

		//setup all the weightlifting exercises for the dropdown
		FitnessProjectTest.initalizeWeightliftingExercises();

		//setup the add exercise button
		add.setBorder(BorderFactory.createEmptyBorder());
		add.setContentAreaFilled(false);
		add.setBounds(525, 100, 30, 35);
		add(add);
		add.addActionListener(this);
		add.setToolTipText("Add new exercises!");

		//add image for video of exercise
		img = new JLabel();
		img.setBounds(620, 160, 350, 410);
		img.setBorder(border);
		add(img);

		//setup screen title
		screenTitle.setForeground(Color.BLACK);
		screenTitle.setFont(font);
		screenTitle.setBounds(150, 10, 700, 75);
		add(screenTitle);

		//setup date label
		date.setForeground(Color.BLACK);
		date.setFont(font2);
		date.setBounds(20, 100, 200, 35);
		add(date);

		//setup day label
		day.setForeground(Color.BLACK);
		day.setFont(font3);
		day.setBounds(50, 210, 75, 20);
		add(day);

		//setup month label
		month.setForeground(Color.BLACK);
		month.setFont(font3);
		month.setBounds(50, 150, 75, 20);
		add(month);

		//setup exercise label
		exercise.setForeground(Color.BLACK);
		exercise.setFont(font2);
		exercise.setBounds(605, 100, 200, 35);
		add(exercise);

		//setup save button
		save.setOpaque(false);
		save.setContentAreaFilled(false);
		save.setBorderPainted(false);
		save.setBorder(null);
		save.setFont(new Font("Segoe Script" , Font.PLAIN, 40));
		save.setForeground(Color.BLACK);
		save.setBounds(20, 475, 200, 75);
		add(save);
		save.addActionListener(this);

		// Makes button bigger when hovered over
		save.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				save.setForeground(Color.BLACK);
				save.setFont(new Font("Segoe Script" , Font.PLAIN, 70));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				save.setForeground(Color.BLACK);
				save.setFont(new Font("Segoe Script" , Font.PLAIN, 40));
			}
		});

		//setup instructions button
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setContentAreaFilled(false);
		button.setBounds(565, 102, 30, 30);
		add(button);
		button.addActionListener(this);
		button.setToolTipText("Click for instructions!");

		//setup graph button
		graph.setOpaque(false);
		graph.setContentAreaFilled(false);
		graph.setBorderPainted(false);
		graph.setBorder(null);
		graph.setFont(new Font("Segoe Script" , Font.PLAIN, 40));
		graph.setForeground(Color.BLACK);
		graph.setBounds(0, 400, 250, 75);
		add(graph);
		graph.addActionListener(this);

		// Makes button bigger when hovered over
		graph.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				graph.setForeground(Color.BLACK);
				graph.setFont(new Font("Segoe Script" , Font.PLAIN, 70));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				graph.setForeground(Color.BLACK);
				graph.setFont(new Font("Segoe Script" , Font.PLAIN, 40));
			}
		});
		
		//setup 'back' button
		back.setBorder(BorderFactory.createEmptyBorder());
		back.setContentAreaFilled(false);
		back.setBounds(10, 10, 100, 76);
		add(back);
		back.addActionListener(this);
		back.setToolTipText("Go back to the info screen.");

		//call method which sets up all the text fields for user input
		textFieldSetup();

		//setup frame
		setVisible(true);
		repaint();

	}

	/**
	 * @author: Masum, Shrill
	 * @param: name of file which is being loaded
	 */
	private static void load(String fileName) throws IOException{

		//setup reader
		Path path = Paths.get(fileName);
		Scanner scanner = new Scanner(path);

		//if there is more data in the text file
		while(scanner.hasNextLine()){

			//get all data from the next line into a string
			String line = scanner.nextLine();

			//split the string into an array, within an element of an array created a 2d array
			userOutputs[index] = line.split(",");

			//increase counter
			index++;

		}

		//create counter for useful data
		int selectingIndex = 0;

		//cycle through all data
		for (int x = 0; x < index; x++) {

			//check each of the 6 data pieces in one line
			for (int y = 0; y < 6; y++) {

				//add array to arraylist 
				selectOutputs.add(userOutputs[x]);

				//increase counter of useful outputs
				selectingIndex++;

			}
		}

		//cycle through useful inputs
		for (int x = 0; x < selectOutputs.size(); x++) {

			//get one array from arraylist
			String[] f = new String[6];
			f = selectOutputs.get(x);

			//cycle through array data
			for (int y = 0; y < 6; y++) {

				//          System.out.println(f[y]);

			}
		}
	}
	
	//setup user input fields
	/**
	 * @author: Shrill
	 */

	public void textFieldSetup(){

		//setup first combobox for selecting the month
		userInputs1[0] = new JComboBox(months);
		userInputs1[0].setSelectedIndex(monthField);
		userInputs1[0].addActionListener(this);
		userInputs1[0].setBounds(125, 142, 235, 40);
		add(userInputs1[0]);

		//setup second combobox for selecting the day
		userInputs1[1] = new JComboBox(daysGenerator());
		userInputs1[1].setSelectedItem(dayField);
		userInputs1[1].addActionListener(this);
		userInputs1[1].setBounds(100, 202, 260, 40);
		add(userInputs1[1]);

		//add default message to combobox
		FitnessProjectTest.exercises.add(0, message1);

		//add all exercises
		for(int x = 0; x < FitnessProjectTest.weightliftingExercises.size(); x++){
			FitnessProjectTest.exercises.add(FitnessProjectTest.weightliftingExercises.get(x).getExercise());
		}
		
		//setup third combobox for selecting exercise
		userInputs1[2] = new JComboBox(FitnessProjectTest.exercises.toArray());
		userInputs1[2].setSelectedIndex(0);
		userInputs1[2].addActionListener(this);
		userInputs1[2].setBounds(755, 100, 225, 40);
		add(userInputs1[2]);
		imageInitalization("res/Videos/" + userInputs1[2].getSelectedItem().toString() + ".gif");

		//setup text field for user input
		userInputs2[0] = new JTextField(20);
		
		//only allow numbers to be 
		userInputs2[0].addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') ||
						(c == KeyEvent.VK_BACK_SPACE) ||
						(c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		userInputs2[0].setBounds(460, 400, 150, 40);
		add(userInputs2[0]);
		TextFieldListener tfListener = new TextFieldListener();
		userInputs2[0].addActionListener(tfListener);

		//setup second text field for user input
		userInputs2[1] = new JTextField(20);
		userInputs2[1].addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') ||
						(c == KeyEvent.VK_BACK_SPACE) ||
						(c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		userInputs2[1].setBounds(460, 450, 150, 40);
		add(userInputs2[1]);
		TextFieldListener tf1Listener = new TextFieldListener();
		userInputs2[1].addActionListener(tf1Listener);

		//setup third text field for user input
		userInputs2[2] = new JTextField(20);
		userInputs2[2].addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') ||
						(c == KeyEvent.VK_BACK_SPACE) ||
						(c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		userInputs2[2].setBounds(460, 500, 150, 40);
		add(userInputs2[2]);
		TextFieldListener tf2Listener = new TextFieldListener();
		userInputs2[2].addActionListener(tf2Listener);
		
		//setup weight label
		weight.setForeground(Color.BLACK);
		weight.setFont(font2);
		weight.setBounds(340, 400, 150, 35);
		add(weight);

		//setup reps label
		reps.setForeground(Color.BLACK);
		reps.setFont(font2);
		reps.setBounds(370, 450, 100, 35);
		add(reps);

		//setup sets label
		sets.setForeground(Color.BLACK);
		sets.setFont(font2);
		sets.setBounds(380, 500, 100, 35);
		add(sets);

	}

	/**
	 * @author: Shrill
	 * @param: name of file which is being loaded
	 */

	public void imageInitalization(String filePath){

		img.setVisible(false);
		img = new JLabel(new ImageIcon(filePath));
		img.setBounds(620, 160, 350, 410);
		img.setBorder(border);
		add(img);
		img.repaint();
		img.setVisible(true);

	}

	/**
	 * @author: Masum, Shrill
	 */
	//saves in data from fields into file without overwriting
	private void save () {

		try{

			//create the file if file is not already present
			if(!FitnessProjectTest.weightFiles[LoginScreen.currentUser].exists()){
				FitnessProjectTest.weightFiles[LoginScreen.currentUser].createNewFile();
			}

			//append the content to file
			FileWriter fWriter = new FileWriter(FitnessProjectTest.weightFiles[LoginScreen.currentUser],true);
			PrintWriter pWriter = new PrintWriter(fWriter);

			//cycle through user inputs
			for(int x = 0; x < userInputs1.length; x++){

				//write in value into textfile
				pWriter.write(String.valueOf(userInputs1[x].getSelectedItem()) + ",");

			}

			//cycle through user inputs
			for(int y = 0; y < userInputs2.length; y++){

				//write in value into textfile
				pWriter.write(userInputs2[y].getText() + ",");

			}

			//next line
			pWriter.println();

			//Closing PrintWriter Stream
			pWriter.close();

		}catch(IOException ioe){
			System.out.println("Exception occurred:");
			ioe.printStackTrace();
		}
		
	}

	/**
	 * @author: Shrill
	 */
	//finds number of days in the month
	public String[] daysGenerator (){

		int temp = userInputs1[0].getSelectedIndex();
		String[] daysTemp;

		if (temp == 1) {
			if(isLeapYear())
				daysTemp = new String[29];
			else
				daysTemp = new String[28];
		} else if (bigMonth((temp + 1))){
			daysTemp = new String[31];
		} else {
			daysTemp = new String[30];
		}

		for (int i = 1; i <= daysTemp.length; i++) {
			daysTemp[i - 1] = Integer.toString(i);
		}

		return daysTemp;
	}

	/**
	 * @author: Shrill
	 */

	public boolean bigMonth(int month) {
		int[] arr = {1, 3, 5, 7, 8, 10, 12};
		for (int n : arr) {
			if (month == n) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @author: Shrill
	 */

	public boolean isLeapYear() {
		if (year%4 == 0) {
			if (year % 100 == 0) {
				if (year % 400 == 0)
					return true;
				return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * @author: Shrill
	 */
	public void description(String description){
		JTextArea textArea = new JTextArea(description);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		scrollPane.setPreferredSize( new Dimension( 300, 150 ) );
		JOptionPane.showMessageDialog(textArea, scrollPane, "Instructions", JOptionPane.INFORMATION_MESSAGE, image);
	}

	/**
	 * @author: Shrill
	 */

	public void saveError(String description){
		JTextArea textArea = new JTextArea(description);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		scrollPane.setPreferredSize( new Dimension( 200, 50 ) );
		JOptionPane.showMessageDialog(textArea, scrollPane, "Instructions", JOptionPane.INFORMATION_MESSAGE);
	}

	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		//if change in months combobox
		if(e.getSource() == userInputs1[0]){

			//setup days combobox
			remove(userInputs1[1]);
			userInputs1[1] = new JComboBox(daysGenerator());
			userInputs1[1].setSelectedIndex(0);
			userInputs1[1].addActionListener(this);
			userInputs1[1].setBounds(100, 202, 260, 40);
			add(userInputs1[1]);
			invalidate();
			validate();
			repaint();
			pack();
			userInputs1[1].setVisible(true);

		}

		//if change in days combobox
		if(e.getSource() == userInputs1[2]){

			//if default is 
			if(userInputs1[2].getItemAt(0).equals(message1)) {
				userInputs1[2].removeItemAt(0);
				revalidate();
				repaint();
			}


			if (userInputs1[2].getSelectedIndex() < 22) {
				imageInitalization("res/Videos/" + userInputs1[2].getSelectedItem().toString() + ".gif");
			} else {
				imageInitalization("res/Videos/ezgif.com-resize.gif");

			}
		}

		//if save button clicked
		if (e.getSource() == save) {

			//if entries not filled
			if(userInputs1[2].getItemAt(0).equals(message1) || userInputs2[0].getText().equals("") || userInputs2[1].getText().equals("") || userInputs2[2].getText().equals("")){
				//show error
				saveError("Please fill in all fields before pressing SAVE!");
			} else {
				
				//save data and reset fields
				save();

				userInputs2[0].setText("");;
				userInputs2[1].setText("");;
				userInputs2[2].setText("");;


				try {
					load("res/weightFile"+ LoginScreen.currentUser +".txt");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

		//if graph button clicked
		if (e.getSource() == graph) {

			//load data and open graph page
			try {
				load("res/weightFile"+ LoginScreen.currentUser +".txt");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			new GraphWeight(userOutputs, selectOutputs);
			dispose();

		}

		//if button clicked
		if(e.getSource() == button){

			//if exercise not picked
			if(e.getSource() == button && userInputs1[2].getItemAt(0).equals(message1)){

				//show message
				description("PLEASE CHOSE A WEIGHTLIFTING EXERCISE FIRST!");

			}else

				//set exercise
				description(FitnessProjectTest.weightliftingExercises.get(userInputs1[2].getSelectedIndex()).getDescription());

		}

		//if back button clicked
		if (e.getSource() == back) {

			//return to old screen, infoscreen
			dispose();
			InfoScreen frame = null;
			try {
				frame = new InfoScreen();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			frame.setVisible(true);

		}

		//if add button clicked
		if(e.getSource() == add){
			dispose();


			//clear currently existing exercise lists
			if(FitnessProjectTest.exercises.size() > 0) {

				FitnessProjectTest.exercises.clear();

			}

			if (FitnessProjectTest.weightliftingExercises.size() > 0) {

				FitnessProjectTest.weightliftingExercises.clear();

			}

			//open new exercise page
			try {
				new NewExercise();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	private class TextFieldListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

		}
		
	}
	
}



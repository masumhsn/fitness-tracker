import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

//Create class which creates JFrame upon starting, with an actionlistener to listen to buttons
public class GraphWeight extends JFrame implements ActionListener {

	//2d array of all inputs
	String globalAllOutputs[][] = new String[500][5];
	
	//create line chart
	JFreeChart lineChart;

	//arraylist of arrays of all inputs
	private static ArrayList <String[]>selectOutputs = new ArrayList <String[]>();
	
	//dropdown of months
	private static JComboBox monthDropdown;
	
	//dropdown of exercises
	private static JComboBox exerciseDropdown;

	//dataset for reps, weights, and goal
	XYSeries lineSeriesReps;
	XYSeries lineSeriesWeights;
	XYSeries goalLine;

	//create panel for pie cahrt
	ChartPanel lineChartPanel;
	
	//variable for current selected month
	String singleMonth;

	//label messages for graphs without enough data
	private JLabel notGraph = new JLabel("You don't have enough data to display the graph!");
	private JLabel notGraph2 = new JLabel("Come back after doing this exercise more!");
	private JLabel instructions = new JLabel("Select a month and exercise");
	private JLabel instructions2 = new JLabel("to see your progress!");

	//button to return to info screen
	private JButton back = new JButton("Back");
	
	//create fonts
	private Font font = new Font("Sylfaen", Font.BOLD, 60);
	private Font font2 = new Font("Sylfaen", Font.BOLD, 21);
	private Font font3 = new Font("Sylfaen", Font.PLAIN, 30);

	//constructor method
	/**
	 * @author: Masum
	 * @param: 2D array of all data
	 * @param: arraylist of arrays of all data without null values
	 */
	public GraphWeight (String allOutputs[][], ArrayList<String[]> data) {

		//set background image
		try {
			setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("res/Images/resizedGradient.png")))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		//if list is not empty
		if(FitnessProjectTest.exercises.size() > 0) {

			//clear list
			FitnessProjectTest.exercises.clear();

		}
		
		//if list is not empty
		if (FitnessProjectTest.weightliftingExercises.size() > 0) {

			//clear list
			FitnessProjectTest.weightliftingExercises.clear();
			
		}

		//set weightlifting exercises
		FitnessProjectTest.initalizeWeightliftingExercises();

		//make all outputs accessable to entire class
		globalAllOutputs = allOutputs;

		//setup frame
		setLayout(null);
		setBounds(0, 0, 1010, 620);

		//setup month dropdown
		monthDropdown = new JComboBox(Cardio.months);
		monthDropdown.addActionListener(this);
		monthDropdown.setBounds(20, 300, 120, 50);
		add(monthDropdown);


		//add al exercises to dropdown
		for(int x = 0; x < FitnessProjectTest.weightliftingExercises.size(); x++){
			FitnessProjectTest.exercises.add(FitnessProjectTest.weightliftingExercises.get(x).getExercise());
		}

		//setup exercise dropdown
		exerciseDropdown = new JComboBox(FitnessProjectTest.exercises.toArray());
		exerciseDropdown.setSelectedIndex(0);
		exerciseDropdown.addActionListener(this);
		exerciseDropdown.setBounds(170, 300, 200, 50);
		add(exerciseDropdown);

		//setup instructions buttons
		instructions.setBounds(20, 180, 400, 100);
		instructions.setFont(font3);
		add(instructions);

		instructions2.setBounds(20, 220, 400, 100);
		instructions2.setFont(font3);
		add(instructions2);

		//create datasets for reps, weights, and goal
		lineSeriesReps = new XYSeries("Total reps");
		lineSeriesWeights = new XYSeries("Weight");
		goalLine = new XYSeries("goalLine");

		//get current selected string
		String selectedMonth = (String) monthDropdown.getSelectedItem();
		String selectedExercise = (String) exerciseDropdown.getSelectedItem();

		//find only useful data given a month and exercise
		findSpecificData(globalAllOutputs, selectedMonth, selectedExercise);
		
		//add the useful data to an arraylist
		addSpecificDataReps();

		//combine all data sets
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(lineSeriesReps);
		dataset.addSeries(lineSeriesWeights);
		dataset.addSeries(goalLine);

		//create linechart
		lineChart = ChartFactory.createXYLineChart("Total Reps and Weights", "Day", "Total Reps", dataset, PlotOrientation.VERTICAL, true, true, false);

		//setup linechart design
		XYPlot linePlot = (XYPlot)lineChart.getPlot();
		XYLineAndShapeRenderer lineRenderer = new XYLineAndShapeRenderer();
		lineRenderer.setSeriesPaint(0,Color.green);
		lineRenderer.setSeriesPaint(1,Color.blue);
		lineRenderer.setSeriesStroke(0, new BasicStroke(3.0f));
		lineRenderer.setSeriesStroke(1, new BasicStroke(3.0f));
		lineRenderer.setBaseFillPaint(Color.black);
		linePlot.setRenderer(lineRenderer);
		linePlot.setBackgroundPaint(Color.white);

		//setup panel for linechart
		lineChartPanel = new ChartPanel( lineChart);
		lineChartPanel.setBounds(400, 0, 600, 580);
		add(lineChartPanel);
		setVisible(true);

		//setup labels
		notGraph.setBounds(490, 0, 490, 500);
		notGraph.setFont(font2);
		notGraph.setVisible(true);
		add(notGraph);
		notGraph2.setBounds(490, 30, 490, 500);
		notGraph2.setFont(font2);
		notGraph2.setVisible(true);
		add(notGraph2);

		//only show graph if enough data
		if (lineSeriesReps.getItemCount() < 2) {
			lineChartPanel.setVisible(false);	
		}else {
			lineChartPanel.setVisible(true);
		}


		//setup back button
		back.setOpaque(false);
		back.setContentAreaFilled(false);
		back.setBorderPainted(false);
		back.setBorder(null);
		back.setFont(new Font("Segoe Script" , Font.PLAIN, 40));
		back.setForeground(Color.BLACK);
		back.setBounds(50, 50, 250, 75);
		add(back);
		back.addActionListener(this);

		back.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				back.setForeground(Color.BLACK);
				back.setFont(new Font("Segoe Script" , Font.PLAIN, 70));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				back.setForeground(Color.BLACK);
				back.setFont(new Font("Segoe Script" , Font.PLAIN, 40));
			}
		});

	}

	//method to find only data wanted for graph
	/**
	 * @author: Masum
	 * @param: 2D array of all raw outputs
	 * @param: wanted month
	 * @param: wanted exercise
	 */
	public void findSpecificData(String allOutputs[][], String findMonth, String findEx) {

		//set counter to 0
		int selectingIndex = 0;
		
		//cycle through exercises
		for (int x = 0; x < Weightlifting.index; x++) {

			//if data has both values
			if ( allOutputs[x][2].equals(findEx)) {

				if (allOutputs[x][0].equals(findMonth)) {

					//add to outputs
					selectOutputs.add(allOutputs[x]);
					selectingIndex++;
				}

			}

		}

	}


	//add the found specific data into dataset
	/**
	 * @author: Masum
	 */
	public void addSpecificDataReps () {

		//cycle through list
		for (int x = 0; x < selectOutputs.size(); x++) {

			//get one value of arraylist
			String[] oneLine = new String[6];
			oneLine = selectOutputs.get(x);
			
			//get single value data for graph
			int singleData = Integer.parseInt(oneLine[4]) * Integer.parseInt(oneLine[5]);
			int singleDay = Integer.parseInt(oneLine[1]);

			//add data to graph
			singleMonth = oneLine[0];
			lineSeriesReps.add(singleDay, singleData);

		}

	}

	//add found specific values for weight graph
	/**
	 * @author: Masum
	 */
	public void addSpecificDataWeights () {

		//cycle through list
		for (int x = 0; x < selectOutputs.size(); x++) {

			//get one line in arraylist
			String[] oneLine = new String[6];
			oneLine = selectOutputs.get(x);

			//get data
			int singleData = Integer.parseInt(oneLine[3]);
			int singleDay = Integer.parseInt(oneLine[1]);
			
			//add data to chart
			singleMonth = oneLine[0];
			lineSeriesWeights.add(singleDay, singleData);

		}

	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		//clear all lists
		lineSeriesReps.clear();
		selectOutputs.clear();
		
		//get current month and exercise
		String selectedMonth = (String) monthDropdown.getSelectedItem();
		String selectedExercise = (String) exerciseDropdown.getSelectedItem();

		//find and add specific data
		findSpecificData(globalAllOutputs, selectedMonth, selectedExercise);
		addSpecificDataReps();

		//clear all lists
		lineSeriesWeights.clear();
		addSpecificDataWeights();

		//if back button clicked
		if (e.getSource() == back) {

			//close, go to previous page
			dispose();
			try {
				new Weightlifting();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		//create temp user from array
		User tempUser = CreateAccount.userArray.get(LoginScreen.currentUser);
		int [] tempWeightGoals = tempUser.getWeightGoals();

		//reset goal
		goalLine.clear();

		//if within default exercises
		if (exerciseDropdown.getSelectedIndex() > 0 && exerciseDropdown.getSelectedIndex() < 23) {

			//add goal data to line
			int adjust = exerciseDropdown.getSelectedIndex() - 1;
			goalLine.add(1, tempWeightGoals[adjust]);
			goalLine.add(lineSeriesReps.getMaxX(), tempWeightGoals[adjust]);
		}

		//only show if sufficient data
		if (lineSeriesReps.getItemCount() < 2) {
			lineChartPanel.setVisible(false);	
		}else {
			lineChartPanel.setVisible(true);
		}

	}

}

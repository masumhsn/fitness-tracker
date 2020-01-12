import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Graph extends JFrame implements ActionListener {

	//initialize default age
	int age = 17;
	
	//initialize default weight
	int weight = 120; 
	String globalAllOutputs[][] = new String[500][5];
	JFreeChart monthLineChart;
	JFreeChart pieChartMonth;

	//arraylists of outputs
	private static ArrayList <String[]>selectOutputs = new ArrayList <String[]>();
	
	//dropdown of months
	private static JComboBox monthDropdown = new JComboBox(Cardio.months);

	//button to go back
	private JButton back = new JButton("Back");

	//switch between time view month view
	private JButton timeViewButton = new JButton ("Year View");
	boolean monthView = true;

	//create panels for charts
	private JPanel pieMonthPanel;
	private JPanel pieYearPanel;
	private JPanel lineMonthPanel;
	private JPanel lineYearPanel;

	//create datasets 
	XYSeries lineSeriesMonth;
	DefaultPieDataset pieDataSetMonth;
	DefaultPieDataset pieDataSetYear;

	//create labels
	private JLabel notGraph = new JLabel("You don't have enough data to display the graph!");
	private JLabel notGraph2 = new JLabel("Come back after doing this exercise more!");
	private JLabel notGraph3 = new JLabel("You don't have enough data to display the graph!");
	private JLabel notGraph4 = new JLabel("Come back after doing more cardio!");

	//create fonts
	private Font font = new Font("Sylfaen", Font.BOLD, 60);
	private Font font2 = new Font("Sylfaen", Font.BOLD, 21);
	private Font font3 = new Font("Sylfaen", Font.PLAIN, 20);

	//string of months to reference for combobox
	private String[] months = {"January","February","March","April","May","June","July","August","September","October","November","December"};

	//current month
	String singleMonth;
	
	//constructor for graph
	/**
	 * @author: Masum
	 * @param: 2D array of all outputs
	 * @param: arraylist of valid outputs
	 */
	public Graph (String allOutputs[][], ArrayList<String[]> data) {

		//create temp users for date age while
		User tempAgeWeightUser = CreateAccount.userArray.get(LoginScreen.currentUser);
		age = tempAgeWeightUser.getAge();
		weight = tempAgeWeightUser.getWeight();
		
		//make 3d array visible to entire class
		globalAllOutputs = allOutputs;

		//setup frame
		setLayout(null);
		setBounds(0, 0, 1000, 600);

		//setup month dropdown
		monthDropdown.addActionListener(this);
		monthDropdown.setBounds(500, 500, 200, 50);
		add(monthDropdown);

		//setup time view button
		timeViewButton.addActionListener(this);
		timeViewButton.setBounds(300, 500, 200, 50);
		add(timeViewButton);

		//set default selected month
		singleMonth = "January";

		//create dataset
		pieDataSetYear = new DefaultPieDataset();

		//cycle through all data
		for (int x = 0; x < data.size(); x++) {

			//get one array from arraylist
			String[] oneExercise = new String[5];
			oneExercise = data.get(x);

			//recieve single data
			int singleData = Integer.parseInt(oneExercise[4]);

			//get value for
			pieDataSetYear.setValue(oneExercise[2], singleData);

		}

		//create pie chart
		JFreeChart pieChartYear = ChartFactory.createPieChart("Time spent on Cardio", pieDataSetYear, true,true, true	);
		//create panel for pie chart
		pieYearPanel = new ChartPanel(pieChartYear);

		//setup panel for chart
		add(pieYearPanel);
		pieYearPanel.setBounds(0, 0, 490, 500);
		pieYearPanel.setVisible(false);

		//setup data for pie chart
		pieDataSetMonth = new DefaultPieDataset();

		//find default specific outputs
		findSpecificLineDataMonth(allOutputs, "January");				
		addSpecificPieDataMonth(); 

		//create pie chart for month stats
		pieChartMonth = ChartFactory.createPieChart("Time spent on cardio in " + singleMonth, pieDataSetMonth, true,true, true	);
		pieMonthPanel = new ChartPanel(pieChartMonth);

		//setup panel
		add(pieMonthPanel);
		pieMonthPanel.setBounds(0, 0, 490, 500);
		pieMonthPanel.setVisible(true);

		//create linechart for month
		lineSeriesMonth = new XYSeries("Calories Burned");
		
		//find specific data for month
		findSpecificLineDataMonth(allOutputs, "January");
		//add data to dataset
		addSpecificLineDataMonth();

		//create linechart data series
		XYSeriesCollection lineDataMonth = new XYSeriesCollection(lineSeriesMonth);
		monthLineChart = ChartFactory.createXYLineChart("Calories burned in " + singleMonth, "Day", "Calories Burned", lineDataMonth, PlotOrientation.VERTICAL, true, true, false);

		//setup design for charts
		XYPlot linePlotMonth = (XYPlot)monthLineChart.getPlot();
		XYLineAndShapeRenderer lineRenderer = new XYLineAndShapeRenderer();
		lineRenderer.setPaint(Color.cyan);
		linePlotMonth.setRenderer(lineRenderer);
		linePlotMonth.setBackgroundPaint(Color.white);

		//setup panel with graph
		lineMonthPanel = new ChartPanel(monthLineChart);
		lineMonthPanel.setBounds(490, 0, 490, 500);
		add(lineMonthPanel);

		//add dataset
		DefaultCategoryDataset caloriesYearDataSet = new DefaultCategoryDataset();

		//array for adding up total calories in a month
		int caloriesInMonth[] = new int[12];

		//cycle through dataset
		for (int x = 0; x < data.size(); x++) {

			//get one array from arraylist
			String[] oneLine = new String[6];
			oneLine = data.get(x);
			
			//get single pieces of data
			double singleDist = Integer.parseInt(oneLine[3]);
			double singleTime = Integer.parseInt(oneLine[4]);
			double singleHRate= Integer.parseInt(oneLine[5]);
			
			//calculate 
			double singleData = ((age * 0.2017) - (weight * 0.09036) + (singleHRate * .6309) - 55.0969) * (singleTime / 4.184);
			//			System.out.println(singleData);
			if (singleData < 0)
				singleData = 0;
			singleMonth = oneLine[0];

			for (int f = 0; f < months.length; f++) {

				if (months[f].equals(singleMonth)) {

					caloriesInMonth[f] += singleData;
					//					System.out.println(singleData);

				}

			}

		}

		//cycle through 12 months
		for (int x = 0; x < 12; x++) {

			//add value to proper month of all calories
			caloriesYearDataSet.addValue(caloriesInMonth[x]/5, "", months[x].substring(0,3));

		}

		//create linechart for total calories
		JFreeChart caloriesYearLineChart = ChartFactory.createLineChart("Calories Burned in the Year", "Month", "Calories Burned", caloriesYearDataSet);

		//setup design for linechart for the year
		Plot linePlotYear = caloriesYearLineChart.getPlot();
		LineAndShapeRenderer lineRendererYear = new LineAndShapeRenderer();
		lineRendererYear.setPaint(Color.cyan);
		linePlotYear.setBackgroundPaint(Color.white);
		lineRendererYear.setStroke( new BasicStroke(10.0f));

		//add year panel to frame
		lineYearPanel = new ChartPanel(caloriesYearLineChart);
		lineYearPanel.setBounds(490, 0, 490, 500);
		add(lineYearPanel);


		//setup messages for when not enough data
		notGraph.setBounds(490, 0, 490, 500);
		notGraph.setFont(font2);
		notGraph.setVisible(true);
		add(notGraph);
		notGraph2.setBounds(490, 30, 490, 500);
		notGraph2.setFont(font2);
		notGraph2.setVisible(true);
		add(notGraph2);
		notGraph3.setBounds(0, 0, 490, 500);
		notGraph3.setFont(font2);
		notGraph3.setVisible(true);
		add(notGraph3);
		notGraph4.setBounds(0, 30, 490, 500);
		notGraph4.setFont(font2);
		notGraph4.setVisible(true);
		add(notGraph4);

		//only show charts if sufficient data
		if (lineSeriesMonth.getItemCount() < 2) {
			lineMonthPanel.setVisible(false);	
		}else {
			lineMonthPanel.setVisible(true);
		}

		if (pieDataSetMonth.getItemCount() < 1) {
			pieMonthPanel.setVisible(false);	
		}else {
			pieMonthPanel.setVisible(true);
		}

		//at default, dont show year stats
		lineYearPanel.setVisible(false);
		
		//setup frame
		setVisible(true);

		//setup back button
		back.setOpaque(false);
		back.setContentAreaFilled(false);
		back.setBorderPainted(false);
		back.setBorder(null);
		back.setFont(new Font("Segoe Script" , Font.PLAIN, 40));
		back.setForeground(Color.BLACK);
		back.setBounds(70, 490, 250, 75);
		add(back);
		back.addActionListener(this);

		//make button larger if hovered over
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

	//find specific data for the line graph in a month
	/**
	 * @author: Masum
	 * @param: 2D array of all potential outputs
	 * @param: string of data type being looked for
	 */
	public void findSpecificLineDataMonth(String allOutputs[][], String find) {

		//counter to 0
		int selectingIndexPie = 0;
		
		//cycle through cardio exercises logged
		for (int x = 0; x < Cardio.index; x++) {

			//for 5 parameters
			for (int y = 0; y < 5; y++) {

				//if dataset has what you are looking for
				if (allOutputs[x][y].equals(find)) {

					//add to arraylist
					selectOutputs.add(allOutputs[x]);
					selectingIndexPie++;

				}

			}

		}

	}


	//adds data found with previous method
	/**
	 * @author: Masum
	 */
	public void addSpecificLineDataMonth () {

		//cycle through all outputs
		for (int x = 0; x < selectOutputs.size(); x++) {

			//get one line from araylist
			String[] oneLine = new String[6];
			oneLine = selectOutputs.get(x);
			
			//get wanted data
			double singleDist = Integer.parseInt(oneLine[3]);
			double singleTime = Integer.parseInt(oneLine[4]);
			double singleHRate= Integer.parseInt(oneLine[5]);
			
			//calculate total reps
			double singleData = ((age * 0.2017) - (weight * 0.09036) + (singleHRate * .6309) - 55.0969) * (singleTime / 4.184);

			//if calories in the negative, make it zero
			if (singleData < 0)
				singleData = 0;
			int singleDay = Integer.parseInt(oneLine[1]);
			singleMonth = oneLine[0];
			
			//add data to datset
			lineSeriesMonth.add(singleDay, singleData);

		}

	}


	//add data for pie chart
	/**
	 * @author: Masum
	 */

	public void addSpecificPieDataMonth () {

		for (int x = 0; x < selectOutputs.size(); x++) {

			String[] oneExercise = new String[5];
			oneExercise = selectOutputs.get(x);
			int singleData = Integer.parseInt(oneExercise[4]);

			pieDataSetMonth.setValue(oneExercise[2], singleData);

		}

	}


	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		//clear lists storing month and outputs
		lineSeriesMonth.clear();
		selectOutputs.clear();
		
		//get current selected month
		String selectedMonth = (String) monthDropdown.getSelectedItem();
		
		//find outputs out of all outputs with the selected month
		findSpecificLineDataMonth(globalAllOutputs, selectedMonth);
		addSpecificLineDataMonth();

		//change title based on month
		monthLineChart.setTitle("Calories burned in " + selectedMonth);

		//change title based on month
		pieChartMonth.setTitle("Time spent on cardio in " + selectedMonth);

		//clear pie chart data
		pieDataSetMonth.clear();
		selectOutputs.clear();
		
		//find specific data given month
		findSpecificLineDataMonth(globalAllOutputs, selectedMonth);
		
		//add data to dataset
		addSpecificPieDataMonth();
		
		//change title based on month
		setTitle("Calories burned in " + selectedMonth);

		//if back button clicked
		if (e.getSource() == back) {

			//close the screen
			dispose();
			
			//open cardio screen
			try {
				new Cardio();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (FontFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		//if time view button is clicked
		if (e.getSource() == timeViewButton) {

			//switch the month view
			if (monthView == false)
				monthView = true;
			else if (monthView == true) 
				monthView = false;

		}

		//if on year view
		if (monthView == false) {

			//hide month charts
			lineMonthPanel.setVisible(false);
			pieMonthPanel.setVisible(false);
			monthDropdown.setVisible(false);

			//show year charts
			pieYearPanel.setVisible(true);
			lineYearPanel.setVisible(true);

			//show to the user which mode clicking the button will bring you to
			timeViewButton.setText("Month View");


			//only show if enough data
			if (pieDataSetYear.getItemCount() < 1) {
				pieYearPanel.setVisible(false);	
			}else {
				pieYearPanel.setVisible(true);
			}

			//if on month view
		} else {

			//show month charts
			lineMonthPanel.setVisible(true);
			pieMonthPanel.setVisible(true);
			monthDropdown.setVisible(true);
			
			//hide year charts
			pieYearPanel.setVisible(false);
			lineYearPanel.setVisible(false);

			//show to the user which mode clicking the button will bring you to
			timeViewButton.setText("Year View");
			
			//if there is enough data, show
			if (lineSeriesMonth.getItemCount() < 2) {
				lineMonthPanel.setVisible(false);	
			}else {
				lineMonthPanel.setVisible(true);
			}

			if (pieDataSetMonth.getItemCount() < 1) {
				pieMonthPanel.setVisible(false);	
			}else {
				pieMonthPanel.setVisible(true);
			}

		}

	}
	
}

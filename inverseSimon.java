import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class inverseSimon extends JFrame  implements ActionListener{

	static JButton red, blue, green, yellow, start, newGame;
	static JPanel startPanel = new JPanel();
	static JPanel mainPanel = new JPanel();
	static JPanel gameOverPanel = new JPanel();
	static Dimension gameButton = new Dimension(250, 250);
	static Random rGen = new Random();
	static int round = -1;
	static int miniRound = -1;
	static int i;
	
	static ArrayList<Integer> eachRoundsColor = new ArrayList();
	public static void main(String[] args) {
		inverseSimon window = new inverseSimon();
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	inverseSimon(){
		super("Inverse Simon");
		init();
		this.setSize(500,500);
		this.setVisible(true);
		this.add(startPanel);
		this.setLayout(new GridLayout(1,0));
	}
	
	void init(){
		//start
		start = new JButton("START");
		start.setSize(gameButton);
		start.setBackground(Color.PINK);
		start.addActionListener(this);
		
		//newGame
		newGame = new JButton("Play Again?");
		newGame.setSize(gameButton);
		newGame.setBackground(Color.PINK);
		newGame.addActionListener(this);
		
		//red
		red = new JButton();
		red.setSize(gameButton);
		red.setBackground(Color.RED);
		red.addActionListener(this);
		
		//blue
		blue = new JButton();
		blue.setSize(gameButton);
		blue.addActionListener(this);
		blue.setBackground(Color.BLUE);
		
		//green
		green = new JButton();
		green.setSize(gameButton);
		green.addActionListener(this);
		green.setBackground(Color.GREEN);
		
		//yellow
		yellow = new JButton();
		yellow.setSize(gameButton);
		yellow.addActionListener(this);
		yellow.setBackground(Color.YELLOW);
		
		//mainPanel
		mainPanel.add(red);
		mainPanel.add(blue);
		mainPanel.add(green);
		mainPanel.add(yellow);
		mainPanel.setLayout(new GridLayout(2,2));
		mainPanel.setSize(new Dimension(400,400));
		mainPanel.setVisible(false);
		
		//startPanel
		startPanel.setVisible(true);
		startPanel.add(start);
		
		//gameOverPanel
		gameOverPanel.setVisible(false);
		gameOverPanel.add(newGame);
	}
	
	public void actionPerformed(ActionEvent event) {
		try {
			if (event.getSource() == start){
				this.add(mainPanel);
				this.remove(startPanel);
				mainPanel.setVisible(true);
				nextRound();
			}
			
			else if (event.getSource() == newGame){
				this.add(mainPanel);
				this.remove(gameOverPanel);
				gameOverPanel.setVisible(false);
				mainPanel.setVisible(true);
				round = -1;
				miniRound = -1;
				eachRoundsColor.clear();
				nextRound();
			}
			
			else if ((event.getSource() == red && eachRoundsColor.get(miniRound) != 0) || (event.getSource() == blue && eachRoundsColor.get(miniRound) != 1) || (event.getSource() == green && eachRoundsColor.get(miniRound) != 2) || (event.getSource() == yellow && eachRoundsColor.get(miniRound) != 3)){
				if (eachRoundsColor.size() - 1 == miniRound){
					miniRound = -1;
					new java.util.Timer().schedule( 
						new java.util.TimerTask() {
						@Override
							public void run() {
								nextRound();
							}
						}, 
						500
					);
				}
				
				
				else {
					miniRound++;
					//stuff();
				}
				
			}
			
	
			else {
				gameOver();
	
			}
		}
		catch (Exception err) {
			gameOver();
		}

	}
	
	public void nextRound(){
		round++;
		miniRound++;
		i = 0;
		playRound();
		runColors();
	}
	
	public void playRound() {
		eachRoundsColor.add(rGen.nextInt(3));
	}
	
	public void runColors(){
		if (eachRoundsColor.get(i) == 0) {
			red.setBackground(Color.PINK);
		}
		else if (eachRoundsColor.get(i) == 1) {
			blue.setBackground(Color.PINK);
		}
		else if (eachRoundsColor.get(i) == 2) {
			green.setBackground(Color.PINK);
		}
		else if (eachRoundsColor.get(i) == 3){
			yellow.setBackground(Color.PINK);
		}
		
		new java.util.Timer().schedule( 
	        new java.util.TimerTask() {
	            @Override
	            public void run() {
	    			red.setBackground(Color.RED);
	    			blue.setBackground(Color.BLUE);
	    			green.setBackground(Color.GREEN);
	    			yellow.setBackground(Color.YELLOW);
	    			i++;

	    			
	    			new java.util.Timer().schedule( 
	    			        new java.util.TimerTask() {
	    			            @Override
	    			            public void run() {
	    			    			if (i <= eachRoundsColor.size() - 1){
	    			    				runColors();
	    			    			}
	    			            }
	    			        }, 
	    			        500
	    				);
	            }
	        }, 
	        1000
		);

    }
	
	 public void gameOver(){
			mainPanel.setVisible(false);
			gameOverPanel.setVisible(true);
			this.remove(mainPanel);
			this.add(gameOverPanel);
	}
}
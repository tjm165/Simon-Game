import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;
import java.awt.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.Border;

public class simon extends JFrame implements ActionListener {

	static JPanel mainPanel = new JPanel();
	
	static JLabel score = new JLabel("score");	
	static JPanel startPanel = new JPanel();
	static JPanel gamePanel = new JPanel();
	static JButton red, blue, green, yellow, start, newGame;
	static JPanel gameOverPanel = new JPanel();

	static Dimension gameButton = new Dimension(250, 250);
	//static Border myBorder = BorderFactory.createLineBorder(Color.black, 4);
	static Random rGen = new Random();
	static int round = -1;
	static int miniRound = -1;
	static int i;
	
	static ArrayList<Integer> eachRoundsColor = new ArrayList();
	public static void main(String[] args) {
		mainPanel.setLayout(new GridLayout(2,1));
		gamePanel.setLayout(new GridLayout(2,2));
		simon window = new simon();
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	simon(){
		super("Simon");
		init();
		this.setSize(500,700);
		this.setVisible(true);
		//this.add(startPanel);
	}
	
	void init(){
		//
		start = new JButton("START");
		start.setSize(gameButton);
		start.setMaximumSize(gameButton);
		start.setMinimumSize(gameButton);
		start.setPreferredSize(gameButton);
		start.setBackground(Color.PINK);
		start.addActionListener(this);
		
		//newGame
		newGame = new JButton("Play Again?");
		newGame.setSize(gameButton);
		newGame.setMaximumSize(gameButton);
		newGame.setMinimumSize(gameButton);
		newGame.setPreferredSize(gameButton);
		newGame.setBackground(Color.PINK);
		newGame.addActionListener(this);
		
		
		//red
		red = new JButton();
		red.setSize(gameButton);
		red.setMaximumSize(gameButton);
		red.setMinimumSize(gameButton);
		red.setPreferredSize(gameButton);
		red.setBackground(Color.RED);
		//red.setBorder(myBorder);
		red.addActionListener(this);
		
		//blue
		blue = new JButton();
		blue.setSize(gameButton);
		blue.setMaximumSize(gameButton);
		blue.setMinimumSize(gameButton);
		blue.setPreferredSize(gameButton);
		blue.addActionListener(this);
		//blue.setBorder(myBorder);
		blue.setBackground(Color.BLUE);
		
		//green
		green = new JButton();
		green.setSize(gameButton);
		green.setMaximumSize(gameButton);
		green.setMinimumSize(gameButton);
		green.setPreferredSize(gameButton);
		green.addActionListener(this);
		//green.setBorder(myBorder);
		green.setBackground(Color.GREEN);
		
		//yellow
		yellow = new JButton();
		yellow.setSize(gameButton);
		yellow.setMaximumSize(gameButton);
		yellow.setMinimumSize(gameButton);
		yellow.setPreferredSize(gameButton);
		yellow.addActionListener(this);
		//yellow.setBorder(myBorder);
		yellow.setBackground(Color.YELLOW);
		
		//mainPanel
		gamePanel.add(red);
		gamePanel.add(blue);
		gamePanel.add(green);
		gamePanel.add(yellow);
		mainPanel.add(gamePanel);
		mainPanel.setSize(new Dimension(20,400));
		//mainPanel.setBorder(myBorder);
		mainPanel.setVisible(true);
		
		//startPanel
		startPanel.setVisible(true);
		mainPanel.add(start);
		
		//gameOverPanel
		gameOverPanel.setVisible(false);
		gameOverPanel.add(newGame);
	
		score.setHorizontalAlignment(SwingConstants.CENTER);
		score.setVerticalAlignment(SwingConstants.TOP);
		red.add(score);
	}
	
	public void actionPerformed(ActionEvent event) {
		try {
			if (event.getSource() == start){
				this.add(mainPanel);
				//this.remove(startPanel);
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
			
			else if ((event.getSource() == red && eachRoundsColor.get(miniRound) == 0) || (event.getSource() == blue && eachRoundsColor.get(miniRound) == 1) || (event.getSource() == green && eachRoundsColor.get(miniRound) == 2) || 	(event.getSource() == yellow && eachRoundsColor.get(miniRound) == 3)){
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
	
	public void nextRound(){
		round++;
		miniRound++;
		i = 0;
		score.setText("Score " + round);
		playRound();
		runColors();
	}
	
	public void playRound() {
		eachRoundsColor.add(rGen.nextInt(3));
	}
	
	public void gameOver(){
			try {
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(new File("buzzer.wav")));
				clip.start();
			}
			catch(Exception e){}
			mainPanel.setVisible(false);
			gameOverPanel.setVisible(true);
			this.remove(mainPanel);
			this.add(gameOverPanel);
	}

}
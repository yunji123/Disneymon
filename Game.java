// Yunji Lee
// 05/26/13
// Game.java
// This program is my for my final project. There is a main game play
// panel, an instructions panel, an options panel, and an upgrades panel.
// The main play panel is a grid where coinciding with monsters causes
// the user to go into encounter mode. To win, you need to get all the
// cakes on the screen. You unlock difficulties as you win games. Your 
// character has health points, and when you hit 0 you get a chance
// to revive by answering a question correctly. Please refer to the 
// instructions for a lengthier description.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Game implements KeyListener
{
	private JFrame frame;
	private Image [] image = new Image[50];
	private String [] imageName = new String[50];
	private Font smallFont1 = new Font("Futura Lt", Font.BOLD, 14);
	private int imageNum;
	
	private int wins = 0;
	private int loses = 0;
	
	// PACMAN:
	private int[][] pacMan = new int[10][10];//Please ignore the pacman related stuff, I used my old code to make this.
	private int [] cheeseX = new int[30];//Locations of cakes
	private int [] cheeseY = new int[30];
	private int [] ghostsX = new int[30];
	private int [] ghostsY = new int[30];
	private int paclocX, paclocY;//final locations
	int animate = 0;
	private Font bigBoldFont = new Font("Futura Hv", Font.BOLD, 40);
	private Font smallFont = new Font("Futura Hv", Font.BOLD, 12);
	private char move = 'h';
	private boolean reset = true;
	private boolean count = false;
	private boolean go = true; // ghosts move or not (upgrade activation)
	private boolean moved = false;//check for victory
	private int cheeseC = 0;
	private int victory = -1;//victory int
	private boolean battle = false;
	private int money = 0;
	private int moneyPlus = 0;
	
	//IMAGES:
	private int [] sizeX = new int[6];
	private int [] sizeY = new int[6];
	private String [] charName = new String[6];
	
	//DIFF. LEVELS:
	private boolean easy = true;
	private boolean medium = false;
	private boolean hard = false;
	
	private Image medStart, medHover, hStart, hHover;
	private JButton med, hardb;
	
	//ENCOUNTERS:
	//private int charHealth = 150;
	private int charHealth = 150; //FOR TESTING
	private int enemyHealth = 50; //HEALTH
	private int myDamage;
	private boolean miss = false;
	private boolean enemyTurn = false;
	private boolean myTurn = true;
	private boolean reset1 = false;
	private boolean winner = false;
	private boolean loser = false;
	private int counterspec = 1;
	private int counteritem = 1;
	
	private String [] attack1 = new String[30];
	private String [] attack2 = new String[30];
	private String [] attack3 = new String[30];
	private int specialattcount = 5;
	
	private String [] question = new String[100];
	private String [][] answer = new String[100][100];
	private int [] correct = new int[100];
	private boolean answeris = false;
	private boolean answerisnot = false;
	private int quizCounter = 1;
	
	private JButton ok1, ok2;	
	
	//UPGRADES:
	private boolean invisible;
	private int invisicount;
	private boolean bomb;
	private int smallhpcount = 0;
	private int bighpcount = 0;

	
	// BOTTOMLAYOUT
	private boolean pan2, pan3, pan4, pan5;
	
	// CARD
	private CardLayout cards;
	
	// GRID
	private GridLayout grid;
	
	// BORDER
	private BorderLayout border;
	
	private Container c, g, n, b, f;
	
	private DrawPanel1 panel1;
	private DrawPanel2 panel2;
	private DrawPanel3 panel3;	
	private DrawPanel4 panel4;
	private DrawPanel5 panel5;
	private DrawPanel6 panel6;
	private DrawPanel7 panel7;
	private DrawPanel8 panel8; //upgrades2
	private DrawPanel9 panel9;
	
	private JButton special1, special2, special3;
	
	public Game()
	{
				
		imageNum = 1;
		imageName[1] = "char1.png"; // 1-4 char's
		imageName[2] = "char2.png";
		imageName[3] = "char3.png";
		imageName[4] = "char4.png";
		
		imageName[5] = "Title.png";
		imageName[6] = "Options.png";
		imageName[7] = "Upgrades.png";
		imageName[8] = "Arrow.png";
		imageName[9] = "MrsQuiz.png";
		imageName[10] = "QuizMonster.png";
		
		imageName[11] = "Char1Back.png"; //11-14 backs of char's
		imageName[12] = "Char2Back.png";
		imageName[13] = "Char3Back.png";
		imageName[14] = "Char4Back.png";
		
		imageName[15] = "Instructions.png";
		imageName[16] = "Cake.png";
		
		imageName[17] = "BuyButton.png";
		imageName[18] = "BuyButtonHover.png";
		imageName[19] = "NextPage.png";
		imageName[20] = "NextPageHover.png";
		imageName[21] = "NextPage2.png";
		imageName[22] = "NextPageHover2.png";
		imageName[23] = "Potion1.png";
		imageName[24] = "Potion2.png";
		imageName[25] = "OkButton.png";
		imageName[26] = "OkButtonHover.png";
		imageName[27] = "Easy.png";
		imageName[28] = "Medium.png";
		imageName[29] = "Hard.png";
		imageName[30] = "EasyHover.png";
		imageName[31] = "MediumHover.png";
		imageName[32] = "HardHover.png";
		
		imageName[33] = "StartButton.png";
		imageName[34] = "InstructionsButton.png";
		imageName[35] = "OptionsButton.png";
		imageName[36] = "UpgradesButton.png";
		imageName[37] = "UpgradesButtonHover.png";
		imageName[38] = "StartButtonHover.png";
		imageName[39] = "InstructionsButtonHover.png";
		imageName[40] = "OptionsButtonHover.png";
		
		imageName[41] = "MrDong.png";
		imageName[42] = "MediumLocked.png";
		imageName[43] = "HardLocked.png";
		imageName[44] = "MediumLockedHover.png";
		imageName[45] = "HardLockedHover.png";
		
		sizeX[1] = 140; sizeY[1] = 202;
		sizeX[2] = 177; sizeY[2] = 193;
		sizeX[3] = 156; sizeY[3] = 200;
		sizeX[4] = 180; sizeY[4] = 200;
		
		charName[1] = "BUBZ";
		charName[2] = "RUSSEL";
		charName[3] = "WOODY";
		charName[4] = "SULLEY";
		
		bomb = invisible = false;
		invisicount = 0;
		
		pan2 = pan3 = pan4 = pan5 = false;
		easy = true;
		medium = hard = false;
		
		attack1[1] = "BUBZ BEAM: 2SA";
		attack2[1] = "PLANET PUNT: 1SA";
		attack3[1] = "STARRY SLAP: 1SA";
		
		attack1[3] = "WOODY WONDER: 2SA";
		attack2[3] = "MOOING MONKEYS: 1SA";
		attack3[3] = "RANCH RANGER: 1SA";
		
		attack1[2] = "RUSSEL RAM: 2SA";
		attack2[2] = "SCOUT SLAP: 1SA";
		attack3[2] = "KID KICK: 1SA";
		
		attack1[4] = "SULLEY SQUISH: 2SA";
		attack2[4] = "PAJAMA PINCH: 1SA";
		attack3[4] = "MONSTER MUNCH: 1SA";
		
		
	}
	public static void main (String [] args)
	{
		Game test = new Game();
		test.run();
	}
	public void run()	
	{
		frame = new JFrame("Disneymon Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(750, 700);	
		frame.setVisible(true);	
			
		GetMyImage();
		
		special1 = new JButton(attack1[imageNum]);
		special2 = new JButton(attack2[imageNum]);	
		special3 = new JButton(attack3[imageNum]);
	
		// CARDLAYOUT
		c = frame.getContentPane();	
		cards = new CardLayout();
		c.setLayout(cards);
		
		panel1 = new DrawPanel1(); //MAINMENU
		panel1.setBackground(new Color(153, 255, 255));
		c.add(panel1, "Panel 1");

		panel2 = new DrawPanel2(); //PACMAN PLAY
		panel2.setBackground(new Color(233, 224, 219));
		panel2.addKeyListener(this);//adds keylistener
		panel2.requestFocus();
		c.add(panel2, "Panel 2");

		panel3 = new DrawPanel3(); //OPTIONS
		panel3.setBackground(new Color(222, 237, 189));
		c.add(panel3, "Panel 3");
		
		panel4 = new DrawPanel4(); //UPGRADES 1
		panel4.setBackground(Color.white);
		c.add(panel4, "Panel 4");
		
		panel5 = new DrawPanel5(); //INSTRUCTIONS
		panel5.setBackground(Color.white);
		c.add(panel5, "Panel 5");
		
		panel6 = new DrawPanel6(); //BATTLE
		c.add(panel6, "Panel 6");
	
		panel7 = new DrawPanel7(); //EDUCATION
		panel7.setBackground(Color.white);
		c.add(panel7, "Panel 7");
		
		panel8 = new DrawPanel8(); //UPGRADES 2
		panel8.setBackground(Color.white);
		c.add(panel8, "Panel 8");
		
		panel9 = new DrawPanel9(); //UPGRADES 2
		panel9.setBackground(Color.white);
		c.add(panel9, "Panel 9");
	}
	public void GetMyImage()
	{
		for(int i = 1; i < 46; i++)
		{
			try
			{
				image[i] = ImageIO.read(new File(imageName[i]));
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	class DrawPanel1 extends JPanel //MAIN MENU
	{
		//private JButton options, play, upgrades, instructions;
		private BottomPanel lower;
		private ButtonPanel center;
		
		Image pStart = image[33];
		Image pHover = image[38];
		JButton play = new JButton(new ImageIcon(pStart));
		
		Image iStart = image[34];
		Image iHover = image[39];
		JButton instructions = new JButton(new ImageIcon(iStart));
		
		Image oStart = image[35];
		Image oHover = image[40];
		JButton options = new JButton(new ImageIcon(oStart));
		
		Image uStart = image[36];
		Image uHover = image[37];
		JButton upgrades = new JButton(new ImageIcon(uStart));
		
		public DrawPanel1()
		{
			// DESCRIPTION:
			// Have title, menu buttons and when clicked will lead to diff
			// panel (options, play, upgrades)
			
			this.setLayout(null);
			setBackground(Color.gray);
			center = new ButtonPanel();
			center.setLocation(160, 300); //null
			center.setSize(400, 200);
			center.setBackground(Color.black);
			center.setOpaque(false);
			this.add(center);
			
			//length: 46 width: 400
		}
		class ButtonPanel extends JPanel implements ActionListener
		{
			public ButtonPanel()
			{
				this.setLayout(new GridLayout(4, 1, 0, 5));
				
				play.setRolloverIcon(new ImageIcon(pHover));
				play.setBorder(BorderFactory.createEmptyBorder());
				play.setContentAreaFilled(false);
				play.setFocusable(false);
				play.addActionListener(this);
				this.add(play);
				play.setBounds(0, 0, 400, 46);
				play.setActionCommand("PLAY");
				
				instructions.setRolloverIcon(new ImageIcon(iHover));
				instructions.setBorder(BorderFactory.createEmptyBorder());
				instructions.setContentAreaFilled(false);
				instructions.setFocusable(false);
				instructions.addActionListener(this);
				this.add(instructions);
				instructions.setBounds(0, 0, 400, 46);
				instructions.setActionCommand("INSTRUCTIONS");
				
				options.setRolloverIcon(new ImageIcon(oHover));
				options.setBorder(BorderFactory.createEmptyBorder());
				options.setContentAreaFilled(false);
				options.setFocusable(false);
				options.addActionListener(this);
				this.add(options);
				options.setBounds(0, 0, 400, 46);
				options.setActionCommand("OPTIONS");
				
				upgrades.setRolloverIcon(new ImageIcon(uHover));
				upgrades.setBorder(BorderFactory.createEmptyBorder());
				upgrades.setContentAreaFilled(false);
				upgrades.setFocusable(false);
				upgrades.addActionListener(this);
				this.add(upgrades);
				upgrades.setBounds(0, 0, 400, 46);
				upgrades.setActionCommand("UPGRADES");
				
			}
			public void actionPerformed(ActionEvent evt) 
			{
				String command = evt.getActionCommand();
				if(command.equals("OPTIONS"))
				{
					cards.show(c, "Panel 3");	
				}
				if(command.equals("INSTRUCTIONS"))
				{
					cards.show(c, "Panel 5");	
				}
				if(command.equals("PLAY"))
				{
					cards.show(c, "Panel 2");	
					panel2.requestFocus();
				}		
				if(command.equals("UPGRADES"))
				{
					cards.show(c, "Panel 4");	
				}		
			}
		}
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			//g.setColor(Color.black);
			//g.fillRect(154, 294, 412, 212);
			//g.drawRect(5, 5, 725, 655);
			//g.drawRect(6, 6, 723, 653);
			
			g.drawImage(image[5], 160, 95, 381, 223, this);
			
			g.setColor(Color.black);
			Font oFont = new Font("Futura Hv", Font.PLAIN, 15);
			g.setFont(oFont);
			g.drawString("by YUNJI LEE", 620, 650);
		}
	}
	class DrawPanel2 extends JPanel//PLAY
	{
		private BottomPanel lower;
		private Pacman play;
		private int counterr = 0;
		private int tempcount = 5;
		
		public DrawPanel2()
		{
			pan2 = true;
			this.setLayout(new BorderLayout());
			lower = new BottomPanel();
			this.add(lower, BorderLayout.SOUTH);
			pan2 = false;
			
			play = new Pacman(); //adding a panel
			this.add(play, BorderLayout.CENTER);
			//this.requestFocus();
		}
		class Pacman extends JPanel//class for new panel
		{
			private MyPanel mypanel;

			public Pacman()
			{
				this.setLayout(new BorderLayout());
				
				mypanel = new MyPanel();
				mypanel.setSize(615, 660);
				mypanel.setBackground(new Color(233, 224, 219));
				this.add(mypanel, BorderLayout.CENTER);
				//this.requestFocus();
			}
			class MyPanel extends JPanel//Drawing the UI
			{
				public MyPanel()//Roughly 60 fps for rendering
				{
					Mover mover = new Mover();
					Timer timer = new Timer(100, mover);//Rate in milliseconds
					timer.start();
				}
				public void paintComponent(Graphics g)//Pacman's original position
				{
					super.paintComponent(g);
					Graphics2D g2 = (Graphics2D)g;
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					
					if(reset)
					{
						paclocX = (int)(Math.random()*9)+1;
						paclocY = (int)(Math.random()*9)+1;
						pacMan[paclocX][paclocY] = 1;
						for (int i = 0; i < 30; i++)
						{
							cheeseX[i] = (int)(9*Math.random()+1);
							cheeseY[i] = (int)(9*Math.random()+1);
							ghostsX[i] = (int)(9*Math.random()+1);
							ghostsY[i] = (int)(9*Math.random()+1);
						}
						victory = cheeseC =0;
						move = 'h';
						moved = false;
						reset = false;
						
						invisible = false;
						
						winner = loser = false;
						
						//HEALTHS
						enemyHealth = 50;
						charHealth = 150;
						//charHealth = 150; 
						specialattcount = 5;
					}
					drawUI(g);
					if(easy)
					{
						for(int i = 0; i < 6; i++)
						{
							g.drawImage(image[10], (ghostsX[i]*50)+57-20, (ghostsY[i]*50)+57+10, 40, 45, this);
						}
					}
					else if(medium)
					{
						for(int i = 0; i < 10; i++)
						{
							g.drawImage(image[10], (ghostsX[i]*50)+57-20, (ghostsY[i]*50)+57+10, 40, 45, this);
						}
					}
					else if(hard)
					{
						for(int i = 0; i < 20; i++)
						{
							g.drawImage(image[10], (ghostsX[i]*50)+57-20, (ghostsY[i]*50)+57+10, 40, 45, this);
						}
					}
					for (int i = 0; i < 6; i++)//Ghosts and pacman
					{
						g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
						g2.setStroke(new BasicStroke(3));
						
						g.setColor(new Color(79, 36, 18));
						//g.drawImage(image[10], (ghostsX[i]*50)+57-20, (ghostsY[i]*50)+57+10, 40, 45, this);
						
						g.setColor(new Color(204, 204, 204));
						//CAKES
						if(easy || medium)
						{
							g.drawImage(image[16], (cheeseX[i]*50)+57-18,(cheeseY[i]*50)+57+8, 33, 33, this);
						}
						g.setColor(new Color(197, 154, 111));
					
						
						if(!invisible || invisicount == 0)
						{
							g.drawImage(image[imageNum], (50*paclocX)+57-20,(50*paclocY)+57+10, 40, 42, this);
						}
						else if(invisible && invisicount > 0)
						{
							float alpha = 0.1f;
							int rule = AlphaComposite.SRC_OVER;
							Composite comp = AlphaComposite.getInstance(rule , alpha );
							g2.setComposite(comp);
							
							g2.drawImage(image[imageNum], (50*paclocX)+57-20,(50*paclocY)+57+10, 40, 42, this);
							comp = AlphaComposite.getInstance(rule , 1);
							g2.setComposite(comp);
							
							Font newFont = new Font("Futura Hv", Font.PLAIN, 12);
							g.setFont(newFont);
							g.setColor(Color.red);
							g.drawString(Integer.toString(tempcount), 630, 230);
				
						}
						if(victory%2.0 == 0 && victory != 0)
						{
							g.setColor(new Color(197, 154, 111));
							g.setColor(Color.black);
							g.setFont(bigBoldFont);
							g.drawString("CONGRATULATIONS!", 65, 270);
							g.drawString("YOU WON THE GAME.", 60, 330);
						}
					}
				}
				public void drawUI(Graphics g)//UI set up
				{
					g.setColor(Color.black);
					g.drawRect(52-20, 52+10,500,500);//Lines on the side
					
					g.setColor(new Color(79, 36, 18)); // SIDE PANEL
					g.fillRect(550, 62, 165, 510);
					g.setColor(Color.white);
					g.setFont(smallFont);
					g.drawString("Character Health", 560, 100);
					g.drawRect(555, 110, 155, 20);
					g.setColor(new Color(185, 50, 65));
					g.fillRect(558, 113, charHealth, 15);
					g.setColor(Color.white);
					Font aFont = new Font("Futura Lt", Font.BOLD, 10);
					g.setFont(aFont);
					g.drawString(Integer.toString(charHealth)+" / 150", 605, 125);
					
					Graphics2D g2 = (Graphics2D)g;
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					
					// CURRENCY
					g.setColor(Color.white);
					g.setFont(smallFont);
					g.drawString("Money: $" + Integer.toString(money), 560, 150);
					g.drawString("Special Attacks Left: " + Integer.toString(specialattcount), 560, 170);
					
					// UPGRADES
					g.drawString("Inventory", 560, 200);
					g.drawRect(555, 210, 150, 80);
					Font newFont = new Font("Futura Lt", Font.PLAIN, 12);
					g.setFont(newFont);
					g.drawString("Invisibility: " + Integer.toString(invisicount), 560, 230);
					g.drawString("Weak HP Potion: " + Integer.toString(smallhpcount), 560, 250);
					g.drawString("Strong HP Potion: " + Integer.toString(bighpcount), 560, 270);
					
					//LEVEL
					g.setFont(smallFont);
					g.drawString("Current Difficulty: ", 560, 320);
					g.setFont(newFont);
					if(easy)
					{
						g.setColor(new Color(79, 36, 18));
						g.fillRect(560, 340, 100, 20);
						g.setColor(Color.white);
						g.drawString("EASY", 560, 340);
					}
					
					else if(medium)
					{
						g.setColor(new Color(79, 36, 18));
						g.fillRect(560, 340, 100, 20);
						g.setColor(Color.white);
						g.drawString("MEDIUM", 560, 340);
					}
					else if(hard)
					{
						g.setColor(new Color(79, 36, 18));
						g.fillRect(560, 340, 100, 20);
						g.setColor(Color.white);
						g.drawString("HARD", 560, 340);
					}
					
					//DIALOGUE BOX
					g.setFont(smallFont);
					g.setColor(Color.white);
					g.drawString("Cake Found: " + Integer.toString(cheeseC) + " / 6", 560, 400);
					g.drawString("Wins: " + Integer.toString(wins), 560, 420);
					g.drawString("Losses: " + Integer.toString(loses/3), 560, 440);
					
					newFont = new Font("Futura Lt", Font.PLAIN, 12);
					g.setFont(newFont);
					g.drawString("You unlocked difficulties:", 560, 470);
					g.setFont(smallFont);
					g.drawString("EASY", 560, 490);
					if(wins >= 5)
					{
						g.drawString("MEDIUM", 560, 510);
					}
					if(wins >= 10)
					{
						g.drawString("HARD", 560, 530);
					}
					//add textbox with boundaries
					
					g.setColor(Color.white);
					g.fillRect(48-20, 573+10, 509, 22);
					g.setColor(new Color(204, 204, 204));
					g.fillRect(50-20, 575+10, 505, 20);
					Font tFont = new Font("Futura Lt", Font.PLAIN, 12);
					g.setFont(tFont);
					g.setColor(new Color(79, 36, 18));
					g.drawString("Directions: a = left, d = right, w = up, s = down, r = reset board", 110, 600 );//Directions for the user
					g.setColor(Color.white);
					Font myFont = new Font("Futura Hv", Font.BOLD, 40);
					g.setFont(myFont);
					g.drawString("D I S N E Y M O N", 187, 42);
					g.setColor(new Color(79, 36, 18));
					g.drawString("D I S N E Y M O N", 185, 40);
				}
				class Mover implements ActionListener//Timer method helps animate pacman's mouth
				{
					public void actionPerformed(ActionEvent e)
					{
						repaint();
						if(!count)
						{
							animate++;
							if(animate == 5)
							{
								count = true;
							}
						}
						else if(count)
						{
							animate--;
							if(animate == 0)
							{
								count = false;
							}
						}
						if(invisible)
						{
							counterr++;
							System.out.print("counter+\n");
							if(invisible && counterr%10==0)
							{
								tempcount--;
								if(tempcount == 0)
								{
									tempcount = 5;
								}
							}
						}
						if(invisible && counterr%50==0) //invisicount depletes every 5 seconds
						{
							invisicount--;
							System.out.print("invisicount-\n");
							if(invisicount == 0)
							{
								invisible = false;
							}
						}
					}
				}
			}
		}
	}
	class DrawPanel3 extends JPanel //OPTIONS
	{
		// CHARACTER
		private BottomPanel lower;
		private CenterPanel center;

		public DrawPanel3()
		{
			pan3 = true;
			this.setLayout(new BorderLayout());
			lower = new BottomPanel();
			this.add(lower, BorderLayout.SOUTH);
			pan3 = false;
			
			center = new CenterPanel(); //adding a panel
			this.add(center, BorderLayout.CENTER);
		}
		class CenterPanel extends JPanel //class for new panel
		{
			private InnerPanel1 inner1;
			private InnerPanel2 inner2;
			private InnerPanel3 inner3; //title
			private InnerPanel4 inner4;
			private InnerPanel5 inner5; //circle choosing
			private JTextArea name;
			
			public CenterPanel()
			{
				this.setLayout(null); //NULL LAYOUT
				
				setBackground(new Color(204,255,153));

				inner3 = new InnerPanel3();
				inner3.setLocation(90, 0);
				inner3.setSize(500, 500);
				this.add(inner3);
				
				name = new JTextArea(7, 20);
				name.setText(" ");
				Font myFont = new Font("Futura Hv", Font.PLAIN, 13);
				name.setFont(myFont);
				this.add(name);
				name.setBounds(430, 360, 180, 20);
				
				//Chars
				inner1 = new InnerPanel1();
				inner1.setLocation(70, 170); //null
				inner1.setSize(200, 90);
				inner1.setOpaque(false);
				this.add(inner1);
				
				inner2 = new InnerPanel2();
				inner2.setLocation(67, 280); //null
				inner2.setSize(400, 400);
				this.add(inner2);
				
				inner4 = new InnerPanel4();
				inner4.setLocation(430, 190); //null
				inner4.setSize(130, 100);
				inner4.setOpaque(false);
				this.add(inner4);
				
				inner5 = new InnerPanel5();
				inner5.setLocation(570, 200); 
				inner5.setSize(50, 100);
				this.add(inner5);
			}
			class InnerPanel3 extends JPanel
			{
				public InnerPanel3()
				{}
				public void paintComponent(Graphics g)
				{
					Graphics2D g2 = (Graphics2D)g;
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
						
					g2.drawImage(image[6], 70, 0, 400, 200, this);
				}
			}
			class InnerPanel4 extends JPanel implements ActionListener
			{
				
				Image ezStart = image[27];
				Image ezHover = image[30];
				JButton okay = new JButton(new ImageIcon(ezStart));
				
				public InnerPanel4() //130, 100
				{
					this.setLayout(new GridLayout(3, 1, 3, 3));
					
					if(wins >= 5)
					{
						medStart = image[28];
						medHover = image[31];
					}
					else if(wins < 5)
					{
						medStart = image[42];
						medHover = image[44]; //LOCKED WORDS:D
					}
					med = new JButton(new ImageIcon(medStart));
					
					if(wins >= 10)
					{
						hStart = image[29];
						hHover = image[32];
					}
					else if(wins < 10)
					{
						hStart = image[43];
						hHover = image[45];
					}
			
					hardb = new JButton(new ImageIcon(hStart));
		
					okay.setRolloverIcon(new ImageIcon(ezHover));
					okay.setBorder(BorderFactory.createEmptyBorder());
					okay.setContentAreaFilled(false);
					okay.setFocusable(false);
					okay.addActionListener(this);
					this.add(okay);
					okay.setBounds(340, 360, 55, 25);
					okay.setActionCommand("EASY");
					
					med.setRolloverIcon(new ImageIcon(medHover));
					med.setBorder(BorderFactory.createEmptyBorder());
					med.setContentAreaFilled(false);
					med.setFocusable(false);
					med.addActionListener(this);
					this.add(med);
					med.setBounds(340, 360, 55, 25);
					med.setActionCommand("MEDIUM");
					
				
					hardb.setRolloverIcon(new ImageIcon(hHover));
					hardb.setBorder(BorderFactory.createEmptyBorder());
					hardb.setContentAreaFilled(false);
					hardb.setFocusable(false);
					hardb.addActionListener(this);
					this.add(hardb);
					hardb.setBounds(340, 360, 55, 25);
					hardb.setActionCommand("HARD");
				}
				public void actionPerformed(ActionEvent evt) 
				{
					med.revalidate();
					hardb.revalidate();
					String command = evt.getActionCommand();
					if(command.equals("EASY"))
					{
						easy = true;
						medium = hard = false;
						reset = true;
					}
					if(command.equals("MEDIUM") && wins >= 5)
					{
						medium = true;
						easy = hard = false;
						reset = true;
					}
					if(command.equals("HARD") && wins >= 10)
					{
						hard = true;
						easy = medium = false;
						reset = true;
					}		
				}
			}
			class InnerPanel1 extends JPanel implements ActionListener
			{
				private JRadioButton bubz, russel, woody, sulley;
				private ButtonGroup characters;
				private JTextArea text;
				
				public InnerPanel1()
				{
					this.setLayout(new GridLayout(5, 1)); // (rows, columns)
					characters = new ButtonGroup();
					
					text = new JTextArea(7, 20);
					text.setText("CHOOSE YOUR CHARACTER:");
					Font myFont = new Font("Futura Hv", Font.PLAIN, 13);
					text.setFont(myFont);
					text.setEditable(false);
					text.setOpaque(false);
					this.add(text);
					
					bubz = new JRadioButton("BUBZ");
					characters.add(bubz);
					bubz.setBackground(new Color(153,153,204));
					bubz.addActionListener(this);
					this.add(bubz);
					
					russel = new JRadioButton("RUSSEL");
					characters.add(russel);
					russel.setOpaque(false);
					russel.addActionListener(this);
					this.add(russel);
					
					woody = new JRadioButton("WOODY");
					characters.add(woody);
					woody.setBackground(new Color(153,153,204));
					woody.addActionListener(this);
					this.add(woody);
					
					sulley = new JRadioButton("SULLEY");
					characters.add(sulley);
					sulley.setOpaque(false);
					sulley.addActionListener(this);
					this.add(sulley);
				}
				public void actionPerformed(ActionEvent e)
				{
					if(bubz.isSelected())
					{
						imageNum = 1;
						
						special1.setText(attack1[imageNum]);
						special2.setText(attack2[imageNum]);	
						special3.setText(attack3[imageNum]);
					}
					else if(russel.isSelected())
					{
						imageNum = 2;
						special1.setText(attack1[imageNum]);
						special2.setText(attack2[imageNum]);	
						special3.setText(attack3[imageNum]);
					}
					else if(woody.isSelected())
					{
						imageNum = 3;
						special1.setText(attack1[imageNum]);
						special2.setText(attack2[imageNum]);	
						special3.setText(attack3[imageNum]);
					}
					else if(sulley.isSelected())
					{
						imageNum = 4;
						special1.setText(attack1[imageNum]);
						special2.setText(attack2[imageNum]);	
						special3.setText(attack3[imageNum]);
					}
					reset = true;
				}
			}
			class InnerPanel2 extends JPanel //CHARACTER IMAGES
			{
				private JTextArea text;
				public InnerPanel2()
				{
					this.setLayout(new GridLayout(2, 2)); // (rows, columns)
				}
				public void paintComponent(Graphics g)
				{
					Graphics2D g2 = (Graphics2D)g;
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					
					//g.setColor(Color.white);
					//g.fillRect(0, 0, 300, 300);
					g.setColor(new Color(153,153,204));
					g.drawRect(0, 0, 300, 300);
					g.drawLine(150, 0, 150, 300);
					g.drawLine(0, 158, 300, 158);
					
					g.drawImage(image[1], 20, 5, 110, 158, this);
					g.drawImage(image[2], 150, 10, 135, 150, this);
					g.drawImage(image[3], 10, 155, 110, 145, this);
					g.drawImage(image[4], 155, 153, 135, 150, this);
					
					g.setFont(smallFont1);
					g.setColor(new Color(72,72,142));
					g.drawString("BUBZ", 4, 155);
					g.drawString("RUSSEL", 154, 155);
					g.drawString("WOODY", 4, 296);
					g.drawString("SULLEY", 154, 296);
					
					this.requestFocus();
				}
			}
			class InnerPanel5 extends JPanel
			{
				public InnerPanel5()
				{}
				public void paintComponent(Graphics g)
				{
					Graphics2D g2 = (Graphics2D)g;
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
						
					//marks buttons to show which one's selected
					g.setColor(new Color(70, 45, 115));
					if(easy)
					{
						g.fillOval(0, 0, 10, 10);
						g.setColor(new Color(153, 255, 255));
						g.fillRect(0, 35, 10, 10);
						g.fillRect(0, 70, 10, 10);
					}
					else if(medium)
					{
						g.fillOval(0, 35, 10, 10);
						g.setColor(new Color(153, 255, 255));
						g.fillRect(0, 0, 10, 10);
						g.fillRect(0, 70, 10, 10);
					}
					else if(hard)
					{
						g.fillOval(0, 70, 10, 10);
						g.setColor(new Color(153, 255, 255));
						g.fillRect(0, 0, 10, 10);
						g.fillRect(0, 35, 10, 10);
					}
					inner5.repaint();
				}
			}
			public void paintComponent(Graphics g)
			{
				Graphics2D g2 = (Graphics2D)g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				
				// diff. level menu
				g.setColor(new Color(153,153,204));
				//g.fillRect(427, 186, 136, 106);
				//g.setColor(Color.black);
				g.drawRect(427, 186, 136, 106);
				
				g.drawRect(67, 167, 206, 96);
				
				g.setColor(Color.black);
				Font smallFont = new Font("Futura Hv", Font.PLAIN, 15);
				g.setFont(smallFont);
				g.drawString("DIFFICULTY:", 427, 170);
				
				g.setColor(new Color(153,153,204));
				g.fillRect(426, 356, 187, 27);
				g.setColor(Color.black);
				g.drawRect(426, 356, 187, 27);
				g.drawString("ENTER YOUR NAME:", 427, 345);
			}
			public void actionPerformed(ActionEvent evt) 
			{
				//inner5.repaint();
			}
		}
	}
	class DrawPanel4 extends JPanel //UPGRADES 1
	{	
		// INVISIBLE (TRANSPARENCY), 
		//USE FLOWLAYOUT
		private BottomPanel lower;
		private MainPanel center;
		
		public DrawPanel4()
		{
			pan4 = true;
			this.setLayout(new BorderLayout());
			lower = new BottomPanel();
			this.add(lower, BorderLayout.SOUTH);
			pan4 = false;
			
			center = new MainPanel();
			this.add(center, BorderLayout.CENTER);
		}
		class MainPanel extends JPanel implements ActionListener//class for new panel
		{
			private boolean stop;
			
			Image startButton = image[17];
			Image startButtonHover = image[18];
			JButton buyButton1 = new JButton(new ImageIcon(startButton));
			JButton buyButton2 = new JButton(new ImageIcon(startButton));
			
			Image pageButton = image[19];
			Image pageHover = image[20];
			JButton nextPage = new JButton(new ImageIcon(pageButton));
			
			private JTextArea text1, text2;
			private boolean canBuy;
			private BuyWindow buy;
			
			public MainPanel()
			{
				this.setLayout(null);
				setBackground(new Color(250, 243, 228));

				buyButton1.setRolloverIcon(new ImageIcon(startButtonHover));
				buyButton1.setBorder(BorderFactory.createEmptyBorder());
				buyButton1.setContentAreaFilled(false);
				buyButton1.setFocusable(false);
				buyButton1.addActionListener(this);
				this.add(buyButton1);
				buyButton1.setBounds(515, 350, 90, 40);
				buyButton1.setActionCommand("BuyButton1");
				
				buyButton2.setRolloverIcon(new ImageIcon(startButtonHover));
				buyButton2.setBorder(BorderFactory.createEmptyBorder());
				buyButton2.setContentAreaFilled(false);
				buyButton2.setFocusable(false);
				buyButton2.addActionListener(this);
				this.add(buyButton2);
				buyButton2.setBounds(515, 550, 90, 40);
				buyButton2.setActionCommand("BuyButton2");
				
				nextPage.setRolloverIcon(new ImageIcon(pageHover));
				nextPage.setBorder(BorderFactory.createEmptyBorder());
				nextPage.setContentAreaFilled(false);
				nextPage.setFocusable(false);
				nextPage.addActionListener(this);
				this.add(nextPage);
				nextPage.setBounds(650, 40, 50, 550);
				nextPage.setActionCommand("NextPage");
				
				buy = new BuyWindow();
				buy.setLocation(300, 300);
				buy.setSize(250, 100);
				this.add(buy);
				if(canBuy)
				{
					buy.setVisible(true);
				}
				else
				{
					buy.setVisible(false);
				} 
				
				text1 = new JTextArea(10, 10);
				text1.setLineWrap(true);
				text1.setWrapStyleWord(true);
				text1.setText("This grants the user invisibility for 5 seconds. Player is immune to ghosts during that time period.\n\nPrice: $300\n-> Press 'i' to use powerup.");
				Font myFont = new Font("Futura Lt", Font.PLAIN, 12);
				text1.setFont(myFont);
				text1.setEditable(false);
				this.add(text1);
				text1.setBounds(350, 230, 240, 110);
				
				text2 = new JTextArea(10, 10);
				text2.setLineWrap(true);
				text2.setWrapStyleWord(true);
				text2.setText("This weak health potion will heal your character by 40 HP.\n\nPrice: $100\n- > This can be used during encounters.");
				text2.setEditable(false);
				this.add(text2);
				text2.setFont(myFont);
				text2.setBounds(350, 430, 240, 110);
			}
			class BuyWindow extends JPanel implements ActionListener
			{
				Image okStart = image[25];
				Image okHover = image[26];
				JButton okay = new JButton(new ImageIcon(okStart));
				
				public BuyWindow()
				{
					this.setLayout(null);
					setBackground(Color.white);
					
					//OK BUTTON
					okay.setRolloverIcon(new ImageIcon(okHover));
					okay.setBorder(BorderFactory.createEmptyBorder());
					okay.setContentAreaFilled(false);
					okay.setFocusable(false);
					okay.addActionListener(this);
					this.add(okay);
					okay.setBounds(340, 360, 55, 25);
					okay.setActionCommand("OK");
				}
				public void paintComponent(Graphics g)
				{
					g.setColor(Color.white);
					g.fillRoundRect(240, 300, 250, 100, 15, 15);
					g.setColor(Color.black);
					g.drawRoundRect(243, 303, 244, 94, 15, 15);
					
					g.setColor(new Color(75, 49, 9));
					Font myFont = new Font("Futura Hv", Font.PLAIN, 16);
					g.setFont(myFont);
					if(canBuy)
					{
						g.drawString("You've bought this item.", 280, 345);
					}
					else
					{
						g.drawString("You can't afford this item.", 277, 345);
					}
				}
				public void actionPerformed(ActionEvent evt) 
				{
					String command = evt.getActionCommand();
					if(command.equals("OK"))
					{
						buy.setVisible(false);
						stop = false;
					}
				}
			}
			
			
			public void paintComponent(Graphics g)
			{
				//IF RUN OUT OF ROOM, MAKE PAGE 2
				super.paintComponent(g);
				
				Graphics2D g2 = (Graphics2D)g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				
				g.setColor(new Color(193, 165, 125));
				g.drawRect(344, 224, 252, 122);
				
				g.drawRect(344, 424, 252, 122);
				
				//TITLE
				g2.drawImage(image[7], 120, 0, 450, 200, this);
				//regular image:
				g2.drawImage(image[1], 50, 240, 110, 158, this);
				//arrow
				g2.drawImage(image[8], 150, 265, 80, 80, this);
				
				//smallpotion
				g2.drawImage(image[23], 100, 430, 139, 170, this);
				
				g.setColor(Color.black);
				Font aFont = new Font("Futura Hv", Font.PLAIN, 30);
				g.setFont(aFont);
				g.drawString("1. INVISIBILITY", 50, 230);
				g.drawString("2. WEAK POTION", 50, 430);
				
				//INVISIBLE (TRANSPARENCY)
				float alpha = 0.4f;
				int rule = AlphaComposite.SRC_OVER;
				Composite comp = AlphaComposite.getInstance(rule , alpha );
				g2.setComposite(comp );
				
				g2.drawImage(image[1], 200, 240, 110, 158, this);
				comp = AlphaComposite.getInstance(rule , 1);
				g2.setComposite(comp );
				//g2.dispose();
			}
			public void actionPerformed(ActionEvent e)
			{
				String command = e.getActionCommand();
				if(command.equals("BuyButton1"))
				{
					if(money >= 300)
					{
						canBuy = true;
						panel4.setComponentZOrder(buy, 1);
						buy.setVisible(true);
						panel4.repaint();
						invisicount++;
						money = money-300;
					}
					else if(money < 300)
					{
						canBuy = false;
						panel4.setComponentZOrder(buy, 1);
						buy.setVisible(true);
						panel4.repaint();
					}
					stop = true;
				}
				else if(command.equals("BuyButton2"))
				{	
					if(money >= 100)
					{
						canBuy = true;
						panel4.setComponentZOrder(buy, 1);
						buy.setVisible(true);
						panel4.repaint();
						smallhpcount++;
						money = money-100;
						System.out.print(smallhpcount + "\n");
						System.out.println("smallhp+");
					}
					else
					{
						canBuy = false;
						panel4.setComponentZOrder(buy, 1);
						buy.setVisible(true);
						panel4.repaint();
					}
					stop = true;
				}
				else if(command.equals("NextPage") && stop == false)
				{
					cards.show(c, "Panel 8");
				}
			}
		}
	}
	
	class DrawPanel8 extends JPanel //UPGRADES 2
	{	
		private BottomPanel lower;
		private MainPanel center;
		private boolean stop;
		
		public DrawPanel8()
		{
			pan4 = true;
			this.setLayout(new BorderLayout());
			lower = new BottomPanel();
			this.add(lower, BorderLayout.SOUTH);
			pan4 = false;
			
			center = new MainPanel();
			this.add(center, BorderLayout.CENTER);
		}
		class MainPanel extends JPanel implements ActionListener//class for new panel
		{
			private JTextArea text1;
			private boolean canBuy;
			private BuyWindow buy;
			//private NoWindow no;
			
			Image startButton = image[17];
			Image startButtonHover = image[18];
			JButton buyButton1 = new JButton(new ImageIcon(startButton));
			
			Image pageButton = image[21];
			Image pageHover = image[22];
			JButton nextPage = new JButton(new ImageIcon(pageButton));
			
			public MainPanel()
			{
				this.setLayout(null);
				setBackground(new Color(250, 243, 228));
				
				buyButton1.setRolloverIcon(new ImageIcon(startButtonHover));
				buyButton1.setBorder(BorderFactory.createEmptyBorder());
				buyButton1.setContentAreaFilled(false);
				buyButton1.setFocusable(false);
				buyButton1.addActionListener(this);
				this.add(buyButton1);
				buyButton1.setBounds(515, 350, 90, 40);
				buyButton1.setActionCommand("BuyButton1");
				
				nextPage.setRolloverIcon(new ImageIcon(pageHover));
				nextPage.setBorder(BorderFactory.createEmptyBorder());
				nextPage.setContentAreaFilled(false);
				nextPage.setFocusable(false);
				nextPage.addActionListener(this);
				this.add(nextPage);
				nextPage.setBounds(650, 40, 50, 550);
				nextPage.setActionCommand("NextPage");
				
				buy = new BuyWindow();
				buy.setLocation(300, 300);
				buy.setSize(250, 100);
				this.add(buy);
				if(canBuy)
				{
					buy.setVisible(true);
				}
				else
				{
					buy.setVisible(false);
				} 

				
				text1 = new JTextArea(10, 10);
				text1.setLineWrap(true);
				text1.setWrapStyleWord(true);
				text1.setText("This strong health potion will heal your character by 60 HP.\n\nPrice: $200\n- > This can be used during encounters.");
				Font myFont = new Font("Futura Lt", Font.PLAIN, 12);
				text1.setFont(myFont);
				text1.setEditable(false);
				this.add(text1);
				text1.setBounds(350, 230, 240, 110);
			}
			class BuyWindow extends JPanel implements ActionListener
			{
				Image okStart = image[25];
				Image okHover = image[26];
				JButton okay = new JButton(new ImageIcon(okStart));

				
				public BuyWindow()
				{
					this.setLayout(null);
					setBackground(Color.white);
					
					okay.setRolloverIcon(new ImageIcon(okHover));
					okay.setBorder(BorderFactory.createEmptyBorder());
					okay.setContentAreaFilled(false);
					okay.setFocusable(false);
					okay.addActionListener(this);
					this.add(okay);
					okay.setBounds(340, 360, 55, 25);
					okay.setActionCommand("OK");
				}
				public void paintComponent(Graphics g)
				{
					g.setColor(Color.white);
					g.fillRoundRect(240, 300, 250, 100, 15, 15);
					g.setColor(Color.black);
					g.drawRoundRect(243, 303, 244, 94, 15, 15);

					g.setColor(new Color(75, 49, 9));
					Font myFont = new Font("Futura Hv", Font.PLAIN, 16);
					g.setFont(myFont);
					if(canBuy)
					{
						g.drawString("You've bought this item.", 280, 345);
					}
					else
					{
						g.drawString("You can't afford this item.", 277, 345);
					}
				}
				public void actionPerformed(ActionEvent evt) 
				{
					String command = evt.getActionCommand();
					if(command.equals("OK"))
					{
						buy.setVisible(false);
						stop = false;
					}
				}
			}
			public void paintComponent(Graphics g)
			{
				//IF RUN OUT OF ROOM, MAKE PAGE 2
				super.paintComponent(g);
				g.setColor(Color.black);
				
				Graphics2D g2 = (Graphics2D)g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				
				g.setColor(new Color(193, 165, 125));
				g.drawRect(344, 224, 252, 122);
				g.setColor(Color.black);
				
				//TITLE
				g2.drawImage(image[7], 120, 0, 450, 200, this);
				
				g2.drawImage(image[24], 120, 240, 139, 170, this);
				
				Font aFont = new Font("Futura Hv", Font.PLAIN, 30);
				g.setFont(aFont);
				g.drawString("3. STRONG POTION", 50, 230);
			}
			public void actionPerformed(ActionEvent e)
			{
				String command = e.getActionCommand();
				if(command.equals("BuyButton1"))
				{
					if(money >= 200)
					{
						canBuy = true;
						panel8.setComponentZOrder(buy, 1);
						buy.setVisible(true);
						panel8.repaint();
						bighpcount++;
						money = money-200;
					}
					else
					{
						canBuy = false;
						panel8.setComponentZOrder(buy, 1);
						buy.setVisible(true);
						panel8.repaint();
					}
					stop = true;
					System.out.println("bighp+");
				}
				else if(command.equals("NextPage") && stop == false)
				{
					cards.show(c, "Panel 4");
				}
			}
		}
	}
	
	class DrawPanel5 extends JPanel //INSTRUCTIONS
	{
		private BottomPanel lower;
		private CenterPanel center;

		public DrawPanel5()
		{
			pan5 = true;
			this.setLayout(new BorderLayout());
			lower = new BottomPanel();
			this.add(lower, BorderLayout.SOUTH);
			pan5 = false;
			
			center = new CenterPanel(); //adding a panel
			this.add(center, BorderLayout.CENTER);
		}
		class CenterPanel extends JPanel //class for new panel
		{
			private JTextArea text1;
			private JScrollPane scroll;
			
			public CenterPanel()
			{
				this.setLayout(null); //NULL LAYOUT
				setBackground(new Color(210, 190, 170));
				
				text1 = new JTextArea(100, 50);
				text1.setLineWrap(true);
				text1.setWrapStyleWord(true);
				text1.setText("\nPLAY MODE INSTRUCTIONS:\n\n- Use the 'wasd' key controls to move your character.Press 'r' to restart your game, and corresponding letters to activate your upgrade/powerups.\n- To win, your character must eat all the cakes on the gameplay board, while defeating any monsters that comes across your way. When you coincide with a monster, your screen will switch to the 'encounter' mode.\n- The side panel on the right will show your character's health, money, the amount of special attack points you have left, and the number of upgrades you have.\n\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n\nENCOUNTER MODE INSTRUCTIONS:\n\n- Clicking attack deals regular damage, whereas special attacks inflict significantly more. SA stands for how many special attack points the attack will use. The more SA used, the more damage. If you have no SA left, clicking a special attack button will do nothing. For each new game, you recieve 5 SA. \n- During encounters, you can use items which include potions from the upgrades store. These increase your health points.\n- If you WIN an encounter, your character is sent back to the regular game play panel, and you may continue playing.\n- If you LOSE your battle, an option to take a quiz to revive is available. If you click OK, a quiz panel shows up and you must answer correctly in order to continue playing. Answering incorrectly will cause you to have to completely restart your game.\n\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n\nLEVEL DIFFERENCES:\n\n- Easy: There are six monsters, and this unlocks automatically.\n- Medium: There are ten monsters, and you need 5 wins to unlock.\n- Hard: There are twenty monsters, you need 10 wins to unlock, and the cakes become invisible.\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nAlso, I'm hoping you have the fonts Futura Lt and Hv. If not I attached them in the file (which hopefully works).\n\n\nSPECIAL NOTE FOR MR. DONG: Win 10 games for an end-of-the-year letter! ^^");
				Font myFont = new Font("Futura Lt", Font.PLAIN, 14);
				text1.setFont(myFont);
				text1.setMargin(new Insets(20,20,20,20));
				text1.setEditable(false);
				this.add(text1);
				text1.setSize(520, 380);
				//text1.setBounds(110, 210, 520, 380);
				
				scroll = new JScrollPane(text1);
				scroll.setBounds(80, 210, 570, 380);
				scroll.setBorder(BorderFactory.createEmptyBorder()); //invisible borders
				this.add(scroll);
			}
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.setColor(Color.black);
				//g.drawImage(image[imageNum+10], 80, 200, sizeX[imageNum], sizeY[imageNum], this);
				Graphics2D g2 = (Graphics2D)g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					
				g2.drawImage(image[15], 70, 5, 570, 200, this);
				
				g.setColor(new Color(135, 69, 56));
				g.fillRect(70, 200, 590, 400);
				g.setColor(Color.white);
				g.fillRect(80, 210, 570, 380);
			}
		}
	}
	class DrawPanel6 extends JPanel //ENCOUNTER
	{
		private ButtonPanel button;
		private CharsPanel chars;
		private EnemyHit enemy;
		private WinBattle win;
		private SpecialAttack special;
		private LoseBattle lose;
		private UseItem itemgrid;
		private NoMore none;
		private NoRetreat noretreat;

		public DrawPanel6()
		{
			this.setLayout(null);
			setBackground(new Color(233, 224, 219));
			
			lose = new LoseBattle();
			lose.setLocation(250, 200);
			lose.setSize(250, 100);
			this.add(lose);
			if(loser)
			{
				lose.setVisible(true);
			}
			else
			{
				lose.setVisible(false);
			} 
			
			special = new SpecialAttack();
			special.setLocation(529, 370);
			special.setSize(170, 100);
			this.add(special); 
			special.setVisible(false);
			
			none = new NoMore();
			none.setLocation(250, 200);
			none.setSize(250, 100);
			this.add(none);
			none.setVisible(false);
			
			noretreat = new NoRetreat();
			noretreat.setLocation(250, 200);
			noretreat.setSize(250, 100);
			this.add(noretreat);
			noretreat.setVisible(false);
			
			itemgrid = new UseItem();
			itemgrid.setLocation(197, 557);
			itemgrid.setSize(150, 71);
			this.add(itemgrid); 
			itemgrid.setVisible(false);
			
			enemy = new EnemyHit();
			enemy.setLocation(250, 200);
			enemy.setSize(250, 100);
			this.add(enemy);
			if(enemyTurn)
			{
				enemy.setVisible(true);
				enemy.requestFocus();
				System.out.print("enemy\n");
			}
			else
			{
				enemy.setVisible(false);
			}
			
			chars = new CharsPanel();
			chars.setLocation(0, 0);
			chars.setSize(800, 450);
			this.add(chars);
			
			button = new ButtonPanel();
			button.setLocation(0, 450);
			button.setSize(800, 300);
			this.add(button);
			
			win = new WinBattle();
			win.setLocation(250, 200);
			win.setSize(250, 100);
			this.add(win);
			this.requestFocus();
			if(winner)
			{
				win.setVisible(true);
			}
			if(!winner)
			{
				win.setVisible(false);
			}
		}
		class CharsPanel extends JPanel
		{
			public CharsPanel()
			{
				this.setLayout(null);
				setBackground(new Color(233, 224, 219));
			}
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.setColor(Color.black);
				
				Graphics2D g2 = (Graphics2D)g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					
				Font myFont = new Font("Futura Lt", Font.BOLD, 15);
				g.setFont(myFont);
				
				//ME
				g.setColor(new Color(200, 200, 176));
				g.fillOval(20, 357, 264, 72);
				g.setColor(new Color(176, 176, 144));
				g.fillOval(23, 360, 256, 64);
				
				g.setColor(Color.black);
				g.drawString(charName[imageNum], 70, 180);
				g2.drawImage(image[imageNum+10], 80, 200, sizeX[imageNum], sizeY[imageNum], this);
			
				//ENEMY
				g.setColor(new Color(200, 200, 176)); //60, 30
				g.fillOval(510, 237, 176, 40);
				g.setColor(new Color(176, 176, 144));
				g.fillOval(513, 240, 170, 34);
				
				g.setColor(Color.black);
				g.drawString("ENEMY", 510, 60);
				g2.drawImage(image[10], 500, 50, 210, 238, this);
				
				
				//HEALTH BARS + STUFF
				//mine
				g.setColor(new Color(64, 64, 80));
				g.fillRoundRect(30, 10, 250, 100, 10, 10);
				myFont = new Font("Futura Lt", Font.PLAIN, 13);
				g.setFont(myFont);
				g.setColor(Color.white);
				g.drawString("HEALTH: ", 45, 85);
				
				g.drawLine(30, 50, 280, 50);
				
				g.setColor(new Color(207, 81, 50));
				myFont = new Font("Futura Lt", Font.BOLD, 20);
				g.setFont(myFont);
				g.drawString(charName[imageNum] + "'S STATS", 45, 40);
				
				//enemy's
				g.setColor(new Color(64, 64, 80));
				g.fillRoundRect(455, 330, 250, 100, 10, 10);
				myFont = new Font("Futura Lt", Font.PLAIN, 13);
				g.setFont(myFont);
				g.setColor(Color.white);
				g.drawString("HEALTH: ", 468, 405);
				
				g.drawLine(452, 370, 704, 370);
				
				g.setColor(new Color(207, 81, 50));
				myFont = new Font("Futura Lt", Font.BOLD, 20);
				g.setFont(myFont);
				g.drawString("ENEMY'S STATS", 468, 360);
				
				//HEALTH BARS
				//mine
				g.setColor(Color.white);
				g.drawRect(110, 70, 155, 20);
				g.setColor(new Color(185, 50, 65));
				g.fillRect(113, 73, charHealth, 15);
				g.setColor(Color.white);
				Font aFont = new Font("Futura Lt", Font.BOLD, 10);
				g.setFont(aFont);
				g.drawString(Integer.toString(charHealth)+" / 150", 160, 85);
				
				//enemy's 
				g.setFont(myFont);
				g.setColor(Color.white);
				g.drawRect(535, 390, 155, 20);
				g.setColor(new Color(185, 50, 65));
				g.fillRect(538, 393, enemyHealth, 15);
				g.setColor(Color.white);
				g.setFont(aFont);
				g.drawString(Integer.toString(enemyHealth)+" / 50", 590, 405);
			}
		}
		class EnemyHit extends JPanel implements ActionListener
		{
			private int enemyHit;
			private JButton back;
			
			public EnemyHit()
			{
				this.setLayout(null);
				enemyHit = (int)((Math.random() * 3)*10);
				
				back = new JButton("OK");
				back.addActionListener(this);
				back.setLocation(160, 65);
				back.setSize(70, 25);
				this.add(back);
			}
			public void paintComponent(Graphics g)
			{
				enemyHit = (int)((Math.random() * 3)*10);
				
				super.paintComponent(g);
				g.setColor(Color.white);
				g.fillRoundRect(0, 0, 250, 100, 15, 15);
				g.setColor(Color.black);
				Font myFont = new Font("Futura Lt", Font.BOLD, 15);
				g.setFont(myFont);
				if(miss)
				{
					g.drawString("Your attack missed!", 24, 30);
				}
				else
				{
					g.drawString("You have dealt " + myDamage + " damage.", 24, 30);
				}
				g.drawString("Enemy has dealt " + enemyHit + " damage.", 24, 50);
			}
			public void actionPerformed(ActionEvent evt) 
			{
				String command = evt.getActionCommand();
				if(command.equals("OK"))
				{
					enemy.setVisible(false);
					charHealth = charHealth - enemyHit;
					myTurn = true;
					enemyTurn = false;
					panel6.repaint();					
	
					if(charHealth <= 0)
					{
						myTurn = false;
						lose.setVisible(true);
						//lose.setComponentZOrder(lose, 1);
						lose.requestFocus();
					}
				}
			}
		}
		class WinBattle extends JPanel implements ActionListener
		{
			private JButton back;
			
			public WinBattle()
			{
				this.setLayout(null);
				this.requestFocus();
				
				back = new JButton("OK");
				back.addActionListener(this);
				back.setLocation(160, 65);
				back.setSize(70, 25);
				this.add(back);
			}
			public void paintComponent(Graphics g)
			{			
				super.paintComponent(g);
				g.setColor(Color.white);
				g.fillRoundRect(0, 0, 250, 100, 15, 15);
				g.setColor(new Color(207, 81, 50));
				
				moneyPlus = (int)((Math.random() * 3)*10)+10;
				System.out.println(moneyPlus);
				money += moneyPlus;
				
				g.drawRoundRect(4, 4, 242, 92, 15, 15);
				g.drawRoundRect(5, 5, 240, 90, 15, 15);
				g.setColor(Color.black);
				Font myFont = new Font("Futura Lt", Font.BOLD, 15);
				g.setFont(myFont);
				g.drawString("Congratulations!", 60, 35);
				myFont = new Font("Futura Lt", Font.PLAIN, 15);
				g.setFont(myFont);
				g.drawString("You won the battle and $" + Integer.toString(moneyPlus) + ".", 30, 55);
				this.requestFocus();
				
				System.out.println(money);
				
			}
			public void actionPerformed(ActionEvent evt) 
			{
				String command = evt.getActionCommand();
				if(command.equals("OK"))
				{
					win.setVisible(false);
					winner = false;
					myTurn = true;
					enemyTurn = false;
					enemyHealth = 50;
					//charHealth = 150;
					enemyTurn = false;
					myTurn = true;
					counterspec = 1;
					counteritem = 1;
					
					cards.show(c, "Panel 2");
					panel2.requestFocus();
					winner = false;	
				}
			}
		}
		
		class LoseBattle extends JPanel implements ActionListener
		{
			private JButton ok, no;
			
			public LoseBattle()
			{
				this.setLayout(null);
				this.setBackground(Color.white);
				this.requestFocus();
				
				ok = new JButton("YES");
				ok.addActionListener(this);
				ok.setLocation(50, 65);
				ok.setSize(70, 25);
				this.add(ok);
				
				no = new JButton("NO");
				no.addActionListener(this);
				no.setLocation(145, 65);
				no.setSize(70, 25);
				this.add(no);
			}
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.setColor(Color.white);
				g.fillRoundRect(0, 0, 250, 100, 15, 15);
				g.setColor(Color.black);
				Font myFont = new Font("Futura Lt", Font.BOLD, 13);
				g.setFont(myFont);
				g.drawString("You lost the battle!", 60, 30);
				g.drawString("Take quiz to regain 25% health?", 20, 50);
			}
			public void actionPerformed(ActionEvent evt)
			{
				String command = evt.getActionCommand();
				if(command.equals("YES"))
				{
					cards.show(c, "Panel 7");
					//panel7.setVisible(true);
					panel7.requestFocus();	
					loser = false;
					lose.setVisible(false);
					myTurn = true;
				}
				if(command.equals("NO"))
				{
					cards.show(c, "Panel 1");
					loser = false;
				}
			}
		}
		class NoMore extends JPanel implements ActionListener
		{
			private JButton back;
			
			public NoMore()
			{
				this.setLayout(null);
				this.requestFocus();
				
				back = new JButton("OK");
				back.addActionListener(this);
				back.setLocation(160, 65);
				back.setSize(70, 25);
				this.add(back);
			}
			public void paintComponent(Graphics g)
			{			
				super.paintComponent(g);
				g.setColor(Color.white);
				g.fillRoundRect(0, 0, 250, 100, 15, 15);
				g.setColor(new Color(207, 81, 50));
				
				g.drawRoundRect(4, 4, 242, 92, 15, 15);
				g.drawRoundRect(5, 5, 240, 90, 15, 15);
				g.setColor(Color.black);
				Font myFont = new Font("Futura Lt", Font.PLAIN, 15);
				g.setFont(myFont);
				g.drawString("You don't have this item.", 40, 45);
				this.requestFocus();
				
			}
			public void actionPerformed(ActionEvent evt) 
			{
				String command = evt.getActionCommand();
				if(command.equals("OK"))
				{
					none.setVisible(false);
				}
			}
		}
		class NoRetreat extends JPanel implements ActionListener
		{
			private JButton back;
			
			public NoRetreat()
			{
				this.setLayout(null);
				this.requestFocus();
				
				back = new JButton("OK");
				back.addActionListener(this);
				back.setLocation(160, 65);
				back.setSize(70, 25);
				this.add(back);
			}
			public void paintComponent(Graphics g)
			{			
				super.paintComponent(g);
				g.setColor(Color.white);
				g.fillRoundRect(0, 0, 250, 100, 15, 15);
				g.setColor(new Color(207, 81, 50));
				
				g.drawRoundRect(4, 4, 242, 92, 15, 15);
				g.drawRoundRect(5, 5, 240, 90, 15, 15);
				g.setColor(Color.black);
				Font myFont = new Font("Futura Lt", Font.PLAIN, 15);
				g.setFont(myFont);
				g.drawString("You don't have enough $ to retreat.", 18, 45);
				this.requestFocus();
				
			}
			public void actionPerformed(ActionEvent evt) 
			{
				String command = evt.getActionCommand();
				if(command.equals("OK"))
				{
					noretreat.setVisible(false);
				}
			}
		}
		class UseItem extends JPanel implements ActionListener
		{
			private JButton smallhp, bighp;
			
			public UseItem()
			{
				this.setLayout(new GridLayout(2, 1, 5, 5));
				this.setBackground(new Color(88, 144, 152));
				
				System.out.println(Integer.toString(smallhpcount));
				smallhp = new JButton("WEAK POTION");
				smallhp.addActionListener(this);
				this.add(smallhp);
				
				bighp = new JButton("STRONG POTION");
				bighp.addActionListener(this);
				this.add(bighp);
				
			}
			public void paintComponent(Graphics g)
			{
				g.setColor(new Color(88, 144, 152));
			}
			public void actionPerformed(ActionEvent evt)
			{
				String command = evt.getActionCommand();
				if(command.equals("WEAK POTION"))
				{
					if(smallhpcount > 0)
					{
						if(charHealth <= 110)
						{
							charHealth += 40;
						}
						else
						{
							charHealth = 150;
						}
						smallhpcount--;
					}
					else
					{
						panel6.setComponentZOrder(none, 1); 
						none.setVisible(true);
					}
					panel6.repaint();
				}
				else if(command.equals("STRONG POTION"))
				{
					if(smallhpcount > 0)
					{
						if(charHealth <= 60)
						{
							charHealth += 90;
						}
						else
						{
							charHealth = 150;
						}
						bighpcount--;
					}
					else
					{
						panel6.setComponentZOrder(none, 1); 
						none.setVisible(true);
					}
					panel6.repaint();
				}
			}
		}
		class SpecialAttack extends JPanel implements ActionListener
		{
			//private JButton special1, special2, special3;
			
			public SpecialAttack()
			{
				this.setLayout(new GridLayout(3, 1, 5, 5));
				this.setBackground(new Color(88, 144, 152));
				
				//special1 = new JButton(attack1[imageNum]);
				special1.addActionListener(this);
				this.add(special1);
				
				//special2 = new JButton(attack2[imageNum]);
				special2.addActionListener(this);
				this.add(special2);
				
				//special3 = new JButton(attack3[imageNum]);
				special3.addActionListener(this);
				this.add(special3);
				
			}
			public void paintComponent(Graphics g)
			{
				g.setColor(new Color(88, 144, 152));
				//g.fillRect(0, 0, 200, 100);
			}
			public void actionPerformed(ActionEvent evt)
			{
				String command = evt.getActionCommand();
				if(command.equals(attack1[imageNum]))
				{
					specialattcount -= 2;
					myDamage = (int)((Math.random() * 5)*10)+40;
				}
				else if(command.equals(attack2[imageNum]))
				{
					specialattcount--;
					myDamage = (int)((Math.random() * 5)*10)+20;
				}
				else if(command.equals(attack3[imageNum]))
				{
					specialattcount--;
					myDamage = (int)((Math.random() * 5)*10)+20;
				}
				if(myDamage == 0)
				{
					miss = true;
				}
				else
				{
					miss = false;
				}
				enemyHealth = (enemyHealth - myDamage);
				if(enemyHealth > 0 && charHealth > 0)
				{
					enemyTurn = true;
					enemy.setVisible(true);
					myTurn = false;
					
					panel6.repaint();
					panel6.requestFocus();
				}
				else if(enemyHealth <= 0)
				{
					enemyHealth = 0;

					winner = true;
					panel6.setComponentZOrder(win, 1); //HOORAY SETS ORDER
					win.setVisible(true);
					myTurn = false;
					//enemyTurn = false;
					//enemy.setVisible(false);
					panel6.repaint();
					panel6.requestFocus();
				}
				else if(charHealth <= 0)
				{
					loser = true;
					lose.setVisible(true);
					myTurn = false;
					panel6.repaint();
					panel6.requestFocus();
				}
				special.setVisible(false);
			}
		}
		class ButtonPanel extends JPanel
		{
			private Buttons button;
			private Background bg;
			
			public ButtonPanel()
			{
				this.setLayout(null);
				setBackground(new Color(64, 64, 80));
				
				button = new Buttons();
				button.setLocation(350, 30);
				button.setSize(350, 150);
				this.add(button);
				
				bg = new Background();
				bg.setLocation(15, 10);
				bg.setSize(710, 200);
				this.add(bg);
			}
			class Background extends JPanel
			{
				public Background()
				{
					this.setLayout(null);
					setBackground(new Color(64, 64, 80));
				}
				public void paintComponent(Graphics g)
				{
					super.paintComponent(g);
					g.setColor(new Color(207, 81, 50));
					g.fillRoundRect(0, 0, 710, 200, 15, 15);
					
					g.setColor(new Color(88, 144, 152));
					g.fillRect(320, 10, 380, 170);
					
					Font myFont = new Font("Futura Lt", Font.BOLD, 25);
					g.setFont(myFont);
					g.setColor(Color.white);
					g.drawString("What will " + charName[imageNum] + " do?", 25, 100);
				}
			}
			class Buttons extends JPanel implements ActionListener
			{
				private JButton attack, specialatt, retreat, item;
				public Buttons()
				{
					this.setLayout(new GridLayout(2, 2, 10, 5));
					setBackground(new Color(88, 144, 152));
					
					attack = new JButton("ATTACK");
					attack.addActionListener(this);
					this.add(attack);
					
					specialatt = new JButton("SPECIAL ATT");
					specialatt.addActionListener(this);
					this.add(specialatt);
					
					item = new JButton("USE ITEM");
					item.addActionListener(this);
					this.add(item);
					
					retreat = new JButton("$200 RETREAT");
					retreat.addActionListener(this);
					this.add(retreat);
				}
				public void actionPerformed(ActionEvent evt) 
				{
					String command = evt.getActionCommand();
					if(command.equals("ATTACK") && myTurn == true)
					{
						myDamage = (int)((Math.random() * 5)*10);
						if(myDamage == 0)
						{
							miss = true;
						}
						else
						{
							miss = false;
						}
						enemyHealth = (enemyHealth - myDamage);
						if(enemyHealth > 0 && charHealth > 0)
						{
							enemyTurn = true;
							enemy.setVisible(true);
							myTurn = false;
							
							panel6.repaint();
							panel6.requestFocus();
						}
						else if(enemyHealth <= 0)
						{
							enemyHealth = 0;

							winner = true;
							panel6.setComponentZOrder(win, 1); //HOORAY SETS ORDER
							win.setVisible(true);
							myTurn = false;
							//enemyTurn = false;
							//enemy.setVisible(false);
							panel6.repaint();
							panel6.requestFocus();
						}
						else if(charHealth <= 0)
						{
							loser = true;
							lose.setVisible(true);
							myTurn = false;
							panel6.repaint();
							panel6.requestFocus();
						}
					}
					else if(command.equals("SPECIAL ATT") && myTurn == true && specialattcount > 0)
					{
						counterspec++;
						if(counterspec % 2 == 0)
						{
							special.setVisible(true);
						}
						else if(counterspec % 2 != 0)
						{
							special.setVisible(false);
						}
					}
					else if(command.equals("$200 RETREAT"))
					{
						if(money >= 200)
						{
							myTurn = true;
							enemyTurn = false;
							enemyHealth = 50;
							cards.show(c, "Panel 2");	
							panel2.requestFocus();
							money-=200;
						}
						else
						{
							panel6.setComponentZOrder(noretreat, 1); 
							noretreat.setVisible(true);
						}
					}		
					else if(command.equals("USE ITEM") && myTurn == true)
					{
						counteritem++;
						if(counteritem % 2 == 0)
						{
							panel6.setComponentZOrder(itemgrid, 1);
							itemgrid.repaint();
							System.out.println(Integer.toString(smallhpcount));
							itemgrid.setVisible(true);
						}
						else if(counteritem % 2 != 0)
						{
							itemgrid.setVisible(false);
						}
					}		
				}
			}
		}
	}
	class DrawPanel7 extends JPanel//EDUCATION
	{
		private CenterPanel1 center1;
		private CorrectAnswer correctans;
		private WrongAnswer wrongans;

		
		public DrawPanel7()
		{
			//answeris = false;
			//answerisnot = false;
			
			this.setLayout(null);
					
			center1 = new CenterPanel1();
			center1.setLocation(10, 10);
			center1.setSize(700, 500);
			this.add(center1);
			
			correctans = new CorrectAnswer();
			correctans.setLocation(230, 530);
			correctans.setSize(300, 100);
			this.add(correctans);
			
			wrongans = new WrongAnswer();
			wrongans.setLocation(230, 530);
			wrongans.setSize(300, 100);
			this.add(wrongans);
		

		}
		class CenterPanel1 extends JPanel implements ActionListener
		{
			private JTextArea text1;
			private JButton submit;
			private JRadioButton one, two, three, four;
			private ButtonGroup options;
			private int randomQuestion;
			
			
			public CenterPanel1()
			{
				this.setLayout(new GridLayout(6, 1));
				options = new ButtonGroup();
				//one.setSelected(true);
				
				question[0] = "\nWhich of these is NOT a function of the respiratory system?";
				answer[0][0] = "Filtering the air that enters body.";
				answer[0][1] = "Allows for body to breathe and exchange gas.";
				answer[0][2] = "Allows body to make vocal sounds.";
				answer[0][3] = "Helps body with digestion of food.";
				correct[0] = 3;
				
				question[1] = "\nWhich of these is NOT a type of java layout?";
				answer[1][0] = "BorderLayout";
				answer[1][1] = "CardLayout";
				answer[1][2] = "NumberLayout";
				answer[1][3] = "GridLayout";
				correct[1] = 2;
				
				question[2] = "\nWhat is cos(\u03C0/3)?";
				answer[2][0] = "3\u03C0/2";
				answer[2][1] = "1/2";
				answer[2][2] = "1/3";
				answer[2][3] = "2\u03C0/2";
				correct[2] = 1;
				
				question[3] = "\nWhich male sex hormone is produced by the testies?";
				answer[3][0] = "Testosterone";
				answer[3][1] = "Estrogen";
				answer[3][2] = "Progesterone";
				answer[3][3] = "Testistrogen";
				correct[3] = 0;
				
				question[4] = "\nWhat connects the ovaries to the uterus?";
				answer[4][0] = "Epididymis";
				answer[4][1] = "Vas Deferens";
				answer[4][2] = "Fallopian Tubes";
				answer[4][3] = "Seminiferous Tubules";
				correct[4] = 2;
				
				question[5] = "\nWhat is sin(\u03C0/4)";
				answer[5][0] = "3\u03C0/2";
				answer[5][1] = "\u221A2/2";
				answer[5][2] = "\u221A2/3\u03C0";
				answer[5][3] = "\u221A3/2";
				correct[5] = 1;
				
				randomQuestion = (int)(Math.random()*6); // 0 4
				//randomQuestion = 0; 
						
				text1 = new JTextArea(200, 50);
				text1.setText(question[randomQuestion]);
				Font myFont = new Font("Futura Lt", Font.BOLD, 12);
				text1.setFont(myFont);
				text1.setEditable(false);
				this.add(text1);
				
				one = new JRadioButton(answer[randomQuestion][0]);
				options.add(one);
				one.addActionListener(this);
				this.add(one);
				
				two = new JRadioButton(answer[randomQuestion][1]);
				options.add(two);
				two.addActionListener(this);
				this.add(two);
				
				three = new JRadioButton(answer[randomQuestion][2]);
				options.add(three);
				three.addActionListener(this);
				this.add(three);
				
				four = new JRadioButton(answer[randomQuestion][3]);
				options.add(four);
				four.addActionListener(this);
				this.add(four);
				
				submit = new JButton("SUBMIT");
				submit.addActionListener(this);
				this.add(submit);
			}
			public void paintComponent(Graphics g)
			{
				
			}
			public void actionPerformed(ActionEvent e)
			{
				String command = e.getActionCommand();
				if(command.equals("SUBMIT") && answeris == false && answerisnot == false)
				{
					if(one.isSelected())
					{
						if(correct[randomQuestion] == 0)
						{
							one.setSelected(false);
							loser = false;
							charHealth = (int)(150 * .25);
							
							answeris = true;
							panel7.setComponentZOrder(correctans, 1);
							correctans.setVisible(true);
						}
						else
						{
							System.out.print("\nFALSE\n");
							answerisnot = true;
							panel7.setComponentZOrder(wrongans, 1);
							wrongans.setVisible(true);
						}
					}
					else if(two.isSelected())
					{
						if(correct[randomQuestion] == 1)
						{
							two.setSelected(false);
							loser = false;
							charHealth = (int)(150 * .25);
							
							answeris = true;
							panel7.setComponentZOrder(correctans, 1);
							correctans.setVisible(true);
						}
						else
						{
							System.out.print("\nFALSE\n");
							answerisnot = true;
							panel7.setComponentZOrder(wrongans, 1);
							wrongans.setVisible(true);
						}
					}
					else if(three.isSelected())
					{
						if(correct[randomQuestion] == 2)
						{
							three.setSelected(false);
							loser = false;
							charHealth = (int)(150 * .25);
							
							answeris = true;
							panel7.setComponentZOrder(correctans, 1);
							correctans.setVisible(true);
						}
						else
						{
							System.out.print("\nFALSE\n");
							answerisnot = true;
							panel7.setComponentZOrder(wrongans, 1);
							wrongans.setVisible(true);
						}
					}
					else if(four.isSelected())
					{
						if(correct[randomQuestion] == 3 )
						{
							loser = false;
							charHealth = (int)(150 * .25);
							
							answeris = true;
							four.setSelected(false);
							panel7.setComponentZOrder(correctans, 1);
							correctans.setVisible(true);
						}
						else
						{
							System.out.print("\nFALSE\n");
							answerisnot = true;
							panel7.setComponentZOrder(wrongans, 1);
							wrongans.setVisible(true);
						}
					}
					panel7.repaint();
					System.out.print("repainted\n");
					panel7.requestFocus();
					//answerisnot = false;
					//answeris = false;
				}
			}
		}
		class CorrectAnswer extends JPanel implements ActionListener
		{
			
			public CorrectAnswer()
			{
					ok2 = new JButton("OK");
					ok2.addActionListener(this);
					ok2.setLocation(65, 205);
					ok2.setSize(70, 25);
					this.add(ok2);
					
			}
			public void paintComponent(Graphics g)
			{
				ok2.setLocation(115, 65);

				if(!answeris)
				{
					ok1.setVisible(false);
					ok2.setVisible(false);
				}
				if(answeris)
				{
					ok1.setVisible(false);
					ok2.setVisible(true);
				}
				if(answeris)
				{
					super.paintComponent(g);
					g.setColor(Color.black);
					g.drawRect(3, 3, 295, 95);
					Font myFont = new Font("Futura Lt", Font.BOLD, 13);
					g.setFont(myFont);
					g.drawString("You answered correctly!", 65, 30);
					g.drawString("Press OK to continue your game.", 40, 50);
				}
			}
			public void actionPerformed(ActionEvent e)
			{
				String command = e.getActionCommand();
				if(answeris)
				{
					if(command.equals("OK"))
					{
						cards.show(c, "Panel 2");
						reset = true;
						loser = false;
						panel2.requestFocus();
						
						answeris = false;
					}
				}
			}
		}
		class WrongAnswer extends JPanel implements ActionListener
		{
			
			public WrongAnswer()
			{
				ok1 = new JButton("RESTART");
				ok1.addActionListener(this);
				ok1.setLocation(105, 65);
				ok1.setSize(70, 25);
				this.add(ok1);

			}
			public void paintComponent(Graphics g)
			{
				ok1.setLocation(115, 65);
				if(!answerisnot)
				{
					ok1.setVisible(false);
					ok2.setVisible(false);
				}
				if(answerisnot)
				{
					ok1.setVisible(true);
					ok2.setVisible(false);
				}

				if(answerisnot)
				{
					super.paintComponent(g);
				
					g.setColor(Color.black);
					g.drawRect(3, 3, 295, 95);
					Font myFont = new Font("Futura Lt", Font.BOLD, 13);
					g.setFont(myFont);
					g.drawString("You answered incorrectly.", 60, 30);
					g.drawString("Press RESTART to start over or QUIT.", 40, 50);
					
					loses++;
				}
			}
			public void actionPerformed(ActionEvent e)
			{
				String command = e.getActionCommand();
				if(answerisnot)
				{
					if(command.equals("RESTART"))
					{
						reset = true;
						wins = 0;
						loses = 0;
						invisicount = 0;
						smallhpcount = 0;
						bighpcount = 0;
						easy = true; hard = medium = false;
						money = 0;
						
						cards.show(c, "Panel 2");
						panel2.requestFocus();
						
						answerisnot = false;
					}
				}
			}
		} 
	
	}
	class DrawPanel9 extends JPanel 
	{
		private BottomPanel lower;
		private CenterPanel center;

		public DrawPanel9()
		{
			pan5 = true;
			this.setLayout(new BorderLayout());
			lower = new BottomPanel();
			this.add(lower, BorderLayout.SOUTH);
			pan5 = false;
			
			center = new CenterPanel(); //adding a panel
			this.add(center, BorderLayout.CENTER);
		}
		class CenterPanel extends JPanel //class for new panel
		{
			private JTextArea text1;
			private JScrollPane scroll;
			
			public CenterPanel()
			{
				this.setLayout(null); //NULL LAYOUT
				setBackground(new Color(234, 237, 176));
				
				text1 = new JTextArea(100, 50);
				text1.setLineWrap(true);
				text1.setWrapStyleWord(true);
				text1.setText("\nHI MR. DONG!\n\n     Java was the first technology related class I've ever taken. At first I doubted that it would be much fun, and only took it so I could take AP Comp Sci in sophomore year. ^^' To be honest, the first couple of days were really tedious and I thought we'd be doing input/output for the rest of the year. But when we began using paintcomponent and JFrames/JPanels I realized how fun coding was when I could integrate art into my codes. It's probably because I've always been really interested in computer graphics and art. I think the highlight of the year was working on the finals project. This was when I got to experiment with everything we'd learned so far with no restrictions, and I really enjoyed seeing how much I could do with just one year's worth of coding education. And my earlier plans of taking AP Comp Sci have been ruined since I'm moving schools, but hopefully I can take it outside of school. Anyhow, thank you very very much for teaching our class how to code. For the past couple weeks, the thing I looked forward to most was literally 4th period so I could work on my finals game some more. I also ended up searching a lot of extra coding things online. And I probably spent half my time on the design/graphics rather than the actual game play coding itself, so sorry about the tedious/boring game. Again, if you've read this far, thank you for a great year of java!\n\nSINCERELY,\nYunji Lee");
				Font myFont = new Font("Futura Lt", Font.PLAIN, 14);
				text1.setFont(myFont);
				text1.setMargin(new Insets(20,20,20,20));
				text1.setEditable(false);
				this.add(text1);
				text1.setSize(520, 380);
				//text1.setBounds(110, 210, 520, 380);
				
				scroll = new JScrollPane(text1);
				scroll.setBounds(80, 210, 570, 380);
				scroll.setBorder(BorderFactory.createEmptyBorder()); //invisible borders
				this.add(scroll);
			}
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.setColor(Color.black);
				//g.drawImage(image[imageNum+10], 80, 200, sizeX[imageNum], sizeY[imageNum], this);
				Graphics2D g2 = (Graphics2D)g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				g2.drawImage(image[41], 70, 5, 570, 200, this);
				
				g.setColor(new Color(54, 50, 88));
				g.fillRect(70, 200, 590, 400);
				g.setColor(Color.white);
				g.fillRect(80, 210, 570, 380);
			}
		}
	}
	class BottomPanel extends JPanel implements ActionListener
	{
		// MENU (JPANEL1)
		JButton options, play, upgrades, instructions;
		JButton mainmenuu;

		public BottomPanel()
		{
			setBackground(new Color(204, 204, 204));
			
			mainmenuu = new JButton("<- MAIN MENU");
			mainmenuu.addActionListener(this);
			this.add(mainmenuu);
			
			play = new JButton("PLAY");
			play.addActionListener(this);
			this.add(play);

			options = new JButton("OPTIONS");
			options.addActionListener(this);
			this.add(options);

			upgrades = new JButton("UPGRADES");
			upgrades.addActionListener(this);
			this.add(upgrades);

			instructions = new JButton("INSTRUCTIONS");
			instructions.addActionListener(this);
			this.add(instructions);
			
		}
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();
			if(command.equals("OPTIONS"))
			{
				cards.show(c, "Panel 3");	
			}
			else if(command.equals("PLAY"))
			{	
				cards.show(c, "Panel 2"); 
				panel2.requestFocus();
			}
			else if(command.equals("UPGRADES"))
			{
				cards.show(c, "Panel 4"); 
			}
			else if(command.equals("<- MAIN MENU"))
			{
				cards.show(c, "Panel 1");
			}
			else if(command.equals("INSTRUCTIONS"))
			{
				cards.show(c, "Panel 5");	
			}
		}
	}
	
	
	public void keyTyped(KeyEvent e)
	{}
	public void keyPressed(KeyEvent e)//Movement
	{
		
		move = e.getKeyChar();
		if(move == 'r')
		{
			reset = true;
		}
		if(victory < 1 && (move == 'w' || move == 'a' || move == 's' || move == 'd' || move == 'i'))
		{
			move = e.getKeyChar();
			switch(move)
			{
				case 'w':
				paclocY--;
				moved = true;
				if(paclocY == -1)
				{
					paclocY = 9;
				}
				go = true;
				break;
				case 'a':
				moved = true;
				if(paclocX == 0)
				{
					paclocX = 10;
				}
				paclocX--;
				go = true;
				break;
				case 'd':
				moved = true;
				if(paclocX == 9)
				{
					paclocX = -1;
				}
				paclocX++;
				go = true;
				break;
				case 's':
				moved = true;
				paclocY++;
				if(paclocY == 10)
				{
					paclocY =0;
				}
				go = true;
				break;
				//UPGRADES;
				case 'i': //invisible
				if(invisicount > 0)
				{
					invisible = true;
					System.out.print("invisible true\n");
				}
				else if(invisicount == 0)
				{
					invisible = false;
				}
				go = false;
				break;
				case 'b': //invisible
				bomb = true;
				go = false;
				break;
				
				
			}
			for (int i = 0; i < 6; i++)//checks for cheese consumption
			{
				if(cheeseX[i] == paclocX && cheeseY[i] == paclocY)
				{
					cheeseX[i] = cheeseY[i] = 100;
					cheeseC++;
				}
			}
			if(cheeseC == 6)//victory condition
			{
				victory = 2;
				wins++;
				panel4.repaint();
				if(wins >= 5) //FIXTHIS
				{
					med.setIcon(new ImageIcon(image[28]));
					med.setRolloverIcon(new ImageIcon(image[31]));
					med = new JButton(new ImageIcon(medStart));
					med.revalidate();
					panel3.revalidate();
					
				}
				if(wins >= 10)
				{
					hardb.setIcon(new ImageIcon(image[29]));
					hardb.setRolloverIcon(new ImageIcon(image[32]));
					hardb = new JButton(new ImageIcon(hStart));
					hardb.revalidate();
					panel3.revalidate();
				}
				if(wins == 10)
				{
					cards.show(c, "Panel 9");
				}
			}
			if(go)
			{
				int shift;
				int num = 6;
				if(easy)
				{
					num = 6;
				}
				else if(medium)
				{
					num = 10;
				}
				else if(hard)
				{
					num = 20;
				}
				for (int i = 0; i < num; i++)//Ghosts movement is completely random
				{
					shift = (int)(Math.random()*8);
					switch(shift)
					{
						case 1:
						if(ghostsX[i] == 9)
						{
							ghostsX[i] = 0;
						}
						if(ghostsY[i] == 0)
						{
							ghostsY[i] = 9;
						}
						ghostsX[i]++;
						ghostsY[i]--;
						break;
						case 2:
						if(ghostsX[i] == 9)
						{
							ghostsX[i] = 0;
						}
						if(ghostsY[i] == 9)
						{
							ghostsY[i] = 0;
						}
						ghostsX[i]++;
						ghostsY[i]++;
						break;
						case 3:
						if(ghostsX[i] == 0)
						{
							ghostsX[i] = 9;
						}
						if(ghostsY[i] == 9)
						{
							ghostsY[i] = 0;
						}
						ghostsX[i]--;
						ghostsY[i]++;
						break;
						case 4:
						if(ghostsX[i] == 0)
						{
							ghostsX[i] = 9;
						}
						if(ghostsY[i] == 0)
						{
							ghostsY[i] = 9;
						}
						ghostsX[i]--;
						ghostsY[i]--;
						break;
						case 5:
						if(ghostsY[i] == 0)
						{
							ghostsY[i] = 9;
						}
						ghostsY[i]--;
						break;
						case 6:
						if(ghostsY[i] == 9)
						{
							ghostsY[i] = 0;
						}
						ghostsY[i]++;
						break;
						case 7:
						if(ghostsX[i] == 0)
						{
							ghostsX[i] = 9;
						}
						ghostsX[i]--;
						break;
						case 8:
						if(ghostsX[i] == 9)
						{
							ghostsX[i] = 0;
						}
						ghostsX[i]++;
						break;
					}
					//win/lose invisi 
					if(ghostsX[i] == paclocX && ghostsY[i] == paclocY && invisible == false)
					{
						battle = true;
						ghostsX[i] = (int)(Math.random()*9)+1;
						ghostsY[i] = (int)(Math.random()*9)+1;
						cards.show(c, "Panel 6");
					}
				}
			}
		}
		med.revalidate();
		hardb.revalidate();
	}
	public void keyReleased(KeyEvent e)
	{}
}

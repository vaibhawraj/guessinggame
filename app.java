import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
public class app extends JFrame
{
	private int f_height;
	private int f_width;
	public JLabel words;
	public JLabel score;
	public app()
	{
		f_height = 443;
		f_width = 446;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Guessing Game");
		setSize(f_width,f_height);
		setVisible(true);
		ImageIcon image = new ImageIcon("game.jpg");
		JLabel imagelabel = new JLabel(image);

		add(imagelabel);
		JLayeredPane layeredPane;
		layeredPane = this.getRootPane().getLayeredPane();
		layeredPane.setLayout(null);
		f_height = layeredPane.getHeight();
		m_panel m = new m_panel();
		words = new JLabel("Words -> 1/10");
		words.setBounds(10,f_height - 40,150,40);
		words.setFont(new Font("Comic San",Font.BOLD,16));
		words.setForeground(Color.blue);
		score = new JLabel("Points -> 0");
		score.setBounds(f_width - 160,f_height - 40,150,40);
		score.setFont(new Font("Comic San",Font.BOLD,16));
		score.setHorizontalAlignment(SwingConstants.RIGHT);
		score.setForeground(Color.blue);
		layeredPane.add(m);
		layeredPane.add(words);
		layeredPane.add(score);
	}
	public static void main(String args[])
	{
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){new app();}
		});
	}
	public class m_panel extends JPanel
	{
		private int height;
		private int width;
		private int x;
		private int y;
		public int point;
		public int index;
		public String wordlist[];
		public String jumble[];
		public JLabel puzzle;
		public JTextField guess;
		public JButton guessButton;
		public int wrong;
		public m_panel()
		{
			point = 0;
			index = 0;
			wrong = 3;
			wordlist = new String[10];
			jumble = new String[10];
			wordlist[0] = "tree"; 		jumble[0] = "etre";
			wordlist[1] = "frog";		jumble[1] = "gofr";
			wordlist[2] = "india";		jumble[2] = "nadii";
			wordlist[3] = "secret";		jumble[3] = "tersec";
			wordlist[4] = "complex";	jumble[4] = "cmxelop";
			wordlist[5] = "facebook";	jumble[5] = "acokbofe";
			wordlist[6] = "american";	jumble[6] = "mariacen";
			wordlist[7] = "worldwar";	jumble[7] = "lowdarwr";
			wordlist[8] = "beautiful";	jumble[8] = "utieafbul";
			wordlist[9] = "complicated";	jumble[9] = "liaccompted";

			width = (int)((double)f_width * 0.60);
			height = (int)((double)f_height * 0.60);
			y = (f_height - height)/2;
			x = (f_width - width)/2;
			setBounds(x,y,width,height);
			setOpaque(false);
			setPreferredSize(new Dimension(100,100));

			setLayout(new GridBagLayout());
			puzzle = new JLabel(jumble[index]);
			puzzle.setOpaque(true);
			puzzle.setBackground(new Color(121,185,225,200));
			puzzle.setForeground(Color.orange);
			puzzle.setPreferredSize(new Dimension(200,30));
			puzzle.setHorizontalAlignment(SwingConstants.CENTER);
			puzzle.setFont(new Font("Comic San",Font.BOLD,26));
			puzzle.setBorder(BorderFactory.createLineBorder(Color.orange));
			guess = new JTextField(10);
			guess.setPreferredSize(new Dimension(100,30));
			guess.setHorizontalAlignment(SwingConstants.CENTER);
			guess.setForeground(new Color(0,155,0));
			guess.setBorder(BorderFactory.createMatteBorder(2,1,1,1,Color.orange));
			guess.setFont(new Font("Comic San",Font.BOLD,16));
			guessButton = new JButton("Guess");
			guessButton.addActionListener(new BListener());
			GridBagConstraints c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 0;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 1;
			c.weighty = 1;
			add(puzzle,c);
			c.gridy=1;
			add(guess,c);
			c.gridy=2;
			add(guessButton,c);
		}
		public class BListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				String guessW;
				guessW = guess.getText();
				if(guessW.equals(wordlist[index]))
				{
					point += 10;
					score.setText("Points -> "+point);
					index++;
					if(index==10)
					{
						JOptionPane.showMessageDialog(new JFrame(), "Congratulation! Your Score is "+point);
						System.exit(1);
					}
					puzzle.setText(jumble[index]);
					guess.setText("");
					guess.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.green));
					guess.requestFocus();
					words.setText("Words -> "+(index+1)+"/10");
					wrong = 3;
				}
				else
				{
					wrong--;
					point -= 5;
					score.setText("Points -> "+point);
					if(wrong == 0)
					{
						guess.setBorder(BorderFactory.createMatteBorder(4-wrong,4-wrong,4-wrong,4-wrong,new Color(((3-wrong)*50)+100,0,0)));
						JOptionPane.showMessageDialog(new JFrame(), "Oops! Game Over. Your Score is "+point);
						System.exit(1);
					}
					guess.setText("");
					guess.setBorder(BorderFactory.createMatteBorder(4-wrong,4-wrong,4-wrong,4-wrong,new Color(((3-wrong)*50)+100,0,0)));
					guess.requestFocus();
				}
			}
		}
	}
}

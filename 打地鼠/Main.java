import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame {

	private JPanel contentPane;
	private Block[] blocks;
	private Thread[] threads;
	public int score; 
	int l = 1000;
	JLabel labelCnt, labelScore;
	cntOneMin realCnt;
	boolean over;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(20, 20));
		contentPane.setLayout(new GridLayout(3, 3, 5, 5));
		labelCnt = new JLabel();
		realCnt = new cntOneMin(labelCnt);
		add("South", labelCnt);
		
		blocks = new Block[9];
		for (int i = 0; i < 9; ++i) {
			blocks[i] = new Block();
			final int index = i;
			blocks[i].addMouseListener(new MouseAdapter() {
				@Override
		    	public void mousePressed(MouseEvent e) {
		    		threads[index].interrupt();;
		    	}
			});
			blocks[i].setHorizontalAlignment(JLabel.CENTER);// 不透明的标签
		    blocks[i].setOpaque(true);
		    contentPane.add(blocks[i]);
		}
		labelScore = new JLabel("0");
		add("North", labelScore);
		over = false;
		threads = new Thread[9];
		for (int i = 0; i < 9; ++i) {
			final int index = i;
			threads[i] = new Thread(()->{
				while(!over && !"0s".equals(labelCnt.getText())) {
					--l;
					double t = Math.random();
					if (t < 0.2)
						blocks[index].setValue(1);
					else if (t > 0.85)
						blocks[index].setValue(-1);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO: handle exception
						int val = blocks[index].getValue();
						if (val == 1)
							score += 1;
						if (val == -1)
							over = true;
						
						blocks[index].setValue(0);
						labelScore.setText(""+score);
//						System.out.print(score);
					}
					blocks[index].setValue(0);
				}
			});
		}
		realCnt.start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 9; ++i)
					threads[i].start();
			}
		}).start();	
		
		add("Center", contentPane);
	}

}

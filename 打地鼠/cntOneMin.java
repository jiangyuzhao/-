import java.util.Timer;

import javax.swing.*;

public class cntOneMin extends Thread{
	Timer timer;
	JLabel textShow;
	public int cnt;
	
	public cntOneMin(JLabel textShow) {
		timer = new Timer();
		cnt = 60;
		this.textShow = textShow;
	}
	
	public void show() {
		this.textShow.setText("" + cnt);
	}
	
	public void run() {
		while (cnt != -1) {
//			textShow.setText(cnt + "s");
			SwingUtilities.invokeLater( 
					new Runnable(){ 
						public void run(){ 
							textShow.setText(cnt + "s");
						}
				} );
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			--cnt;
		}
	}
}

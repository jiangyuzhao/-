import javax.swing.JButton;

public class Block extends JButton{
	private int value;
	public Block() {
		// TODO Auto-generated constructor stub
		this.value = 0;
		setText("");
	}
	public int getValue() {
		return this.value;
	}
	public void setValue(int value) {
		this.value = value;
		
		if (this.value == 0)
			setText("");
		else if (this.value == 1)
			setText("鼠");
		else if (this.value == -1)
			setText("雷");
		else
			setText("啥");
	}
}

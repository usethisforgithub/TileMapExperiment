import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Driver {
	
	public static void main(String[] args) {
		
		
		
		
		// stuff for the dialog box
		JTextField aField = new JTextField(5);
		JTextField bField = new JTextField(5);
		JTextField cField = new JTextField(5);
		JTextField dField = new JTextField(5);

		JPanel myPanel = new JPanel();
		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));

		myPanel.add(new JLabel("Enter x dim:"));
		myPanel.add(aField);

		myPanel.add(Box.createVerticalStrut(15));

		myPanel.add(new JLabel("Enter y dim:"));
		myPanel.add(bField);

		myPanel.add(Box.createVerticalStrut(15));

		int result = JOptionPane.showConfirmDialog(null, myPanel, " Enter dimensions for new tilemap", JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.OK_OPTION) {
			String temp1 = aField.getText();
			String temp2 = bField.getText();

			if (!temp1.equals("") && !temp2.equals("")) {
				int x = Integer.parseInt(temp1);
				int y = Integer.parseInt(temp2);
				ScreenWindow window = new ScreenWindow(x,y);
				new Thread(window).start();
			}
			else {
				System.out.println("One or more fields was left empty. New SCS simulation was not started.");
			}
		}
		
		
		

	}
}
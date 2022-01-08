import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class tictactoe extends JPanel{
	char player = 'X';
	JButton[] buttons = new JButton[9];
	public tictactoe() {
		setLayout(new GridLayout(3,3));
		initButtons();
	}
	public void initButtons() {
		for(int i=0; i<9; i++) {
			buttons[i] = new JButton();
			buttons[i].setText(".");
			buttons[i].setBackground(Color.white);
			buttons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					JButton clicked = (JButton) event.getSource();
					clicked.setEnabled(false);
					clicked.setText(String.valueOf(player));
					if(player == 'X') {
						player = 'O';
						clicked.setBackground(Color.CYAN);
					}
					else if(player == 'O') {
						player = 'X';
						clicked.setBackground(Color.YELLOW);
					}
					checkwin();
				}
			});
			add(buttons[i]);
		}
	}
	public void checkwin() {
		if(haswinner() == true) {
			if(player == 'X') player = 'O';
			else if(player == 'O') player = 'X';
			JOptionPane pane = new JOptionPane();
			int result = JOptionPane.showConfirmDialog(pane, "Game Over! " + player + " wins. Would you like to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) reset();
			else System.exit(0);
		}
		else if(hasdraw() == true){
			JOptionPane pane = new JOptionPane();
			int result = JOptionPane.showConfirmDialog(pane, "Draw! Would you like to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) reset();
			else System.exit(0);
		}
	}
	
	private void reset() {
		for(int i=0; i<9; i++) {
			player = 'X';
			buttons[i].setText(".");
			buttons[i].setBackground(Color.white);
			buttons[i].setEnabled(true);
		}
	}

	public boolean hasdraw() {
		for(int i=0; i<9; i++) {
			if(buttons[i].getText().charAt(0) == '.') {
				return false;
			}
		}
		return true;
	}
	
	public boolean haswinner() {
		for(int i=0; i<9; i+=3) {
			if(buttons[i].getText().charAt(0) != '.' && buttons[i].getText().equals(buttons[i+1].getText()) && buttons[i].getText().equals(buttons[i+2].getText())) {
				return true;
			}
		}
		for(int i=0; i<3; i++) {
			if(buttons[i].getText().charAt(0) != '.' && buttons[i].getText().equals(buttons[i+3].getText()) && buttons[i].getText().equals(buttons[i+6].getText())) {
				return true;
			}
		}
		if((buttons[0].getText().charAt(0) != '.') && (buttons[0].getText().equals(buttons[4].getText())) && (buttons[0].getText().equals(buttons[8].getText()))) {
			return true;
		}
		if((buttons[2].getText().charAt(0) != '.') && (buttons[2].getText().equals(buttons[4].getText())) && (buttons[2].getText().equals(buttons[6].getText()))) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		JFrame window = new JFrame("Little Mouse's Tic Tac Toe");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().add(new tictactoe()); // add the date
		window.setBounds(300, 300, 300, 300); // set the bound
		window.setVisible(true); // show the window
		window.setLocationRelativeTo(null); // center the window
	}
}

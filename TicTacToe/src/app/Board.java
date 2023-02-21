package app;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Board extends JFrame implements Winnable{
	

	private static final long serialVersionUID = 1L;
	
	public final static int NUMBER_OF_SQUARES = 9;
	public final static int ROW_SIZE = 3;
	public final static int COL_SIZE = 3;
	
	
	public static char playerSign = 'x';
	public final static String SYMBOL_OF_CROSS = "X";
	public final static String SYMBOL_OF_SQUARE = "0";
	
	protected static JButton[] buttons;

	public Board() { 
		createBoard();
		setLayout(new GridLayout(ROW_SIZE, COL_SIZE));
		setSize(300, 300);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	protected void createBoard() {
		buttons = new JButton[NUMBER_OF_SQUARES];
		for(int i = 0; i < NUMBER_OF_SQUARES; i++) {
			buttons[i] = new JButton("");
			buttons[i].setBackground(Color.BLACK);
			buttons[i].setForeground(Color.WHITE);
				buttons[i].addActionListener(e -> {
				JButton clickedBtn = (JButton) e.getSource();
				clickedBtn.setText(String.valueOf(playerSign));
	            clickedBtn.setEnabled(false);
	
	            if (playerSign == 'x') {
	            	playerSign = 'o';
	            }else {
	            	playerSign = 'x';
	            }
				showWinner();
				});
			
			add(buttons[i]);
		}
	}
	
	public boolean checkAllRows() {
        int i = 0;
        for (int j = 0; j < ROW_SIZE; j++) {
            if (buttons[i].getText().equals(buttons[i + 1].getText()) && buttons[i].getText().equals(buttons[i + 2].getText())
                    && !buttons[i].getText().equals("")) {
                return true;
            }
            i = i + 3;

        }
        return false;
    }


    public boolean checkAllColumns() {
        int i = 0;
        for (int j = 0; j < COL_SIZE; j++) {

            if (buttons[i].getText().equals(buttons[i + 3].getText()) && buttons[i].getText().equals(buttons[i + 6].getText())
                    && !buttons[i].getText().equals("")) {
                return true;
            }
            i++;
        }
        return false;
    }

    public boolean checkTheDiagonals() {
        if (buttons[0].getText().equals(buttons[4].getText()) && buttons[0].getText().equals(buttons[8].getText())
                && !buttons[0].getText().equals(""))
            return true;
        else
            return buttons[2].getText().equals(buttons[4].getText()) && buttons[2].getText().equals(buttons[6].getText())
                    && !buttons[2].getText().equals("");
    }
	
	@Override
	public boolean checkWinner() {
		return checkAllRows() || checkAllColumns() || checkTheDiagonals();
	}

	@Override
	public boolean checkDraw() {
		boolean gridsFull = true;
        for (int i = 0; i < NUMBER_OF_SQUARES; i++) {
            if (buttons[i].getText().equals("")) {
                gridsFull = false;
            }
        }
        return gridsFull;
	}
	
	public void showWinner() {
		if(checkWinner()){
			
			if (playerSign == 'x') {
				playerSign = 'o';
			}else {
				playerSign = 'x';
			}
			
			JOptionPane jOptionPane = new JOptionPane();
            int dialog = JOptionPane.showConfirmDialog(jOptionPane, "Game Over. " + "The winner is " + playerSign + " ", "Result",
                    JOptionPane.DEFAULT_OPTION);

            if (dialog == JOptionPane.OK_OPTION) {	            	
            	System.exit(0);
            }
		}else if (checkDraw()) {
            JOptionPane jOptionPane = new JOptionPane();
            int dialog = JOptionPane.showConfirmDialog(jOptionPane, "Game Draw", "Result", JOptionPane.DEFAULT_OPTION);

            if (dialog == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        }
	}
}
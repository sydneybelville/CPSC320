import static org.junit.Assert.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import org.junit.Test;

public class UnitTest {

	private Board[] boards = getBoard();
	
	private Board[] getBoard() {
		File file = null;
		JFileChooser fileChooser = new JFileChooser();
		int result = fileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
		}
		try {
			Scanner scan = new Scanner(file);
			int numBoards = scan.nextInt();
			Board[] boardArray = new Board[numBoards];
			// Goes through each board and adds it to the array
			for (int i = 0; i < numBoards; i++) {
				Board boards = new Board();
				for (int row = 0; row < 8; row++) {
					for (int column = 0; column < 8; column++) {
						String characters = scan.next();
						// This adds the pieces to the boards
						switch (characters) {
						case "P":
							boards.setPiece(row, column, new Pawn(PieceColor.Black));
							break;
						case "B":
							boards.setPiece(row, column, new Bishop(PieceColor.Black));
							break;
						case "R":
							boards.setPiece(row, column, new Rook(PieceColor.Black));
							break;
						case "N":
							boards.setPiece(row, column, new Knight(PieceColor.Black));
							break;
						case "Q":
							boards.setPiece(row, column, new Queen(PieceColor.Black));
							break;
						case "K":
							boards.setPiece(row, column, new King(PieceColor.Black));
							break;
						}
					}
				}
				// This adds the created board with the pieces to the array of boards
				boardArray[i] = boards;
			}
			return boardArray;
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	@Test
	public void testBoards() {
		Controller controller = new Controller();
		String result = controller.solveBoard(boards[0]);
		String result2 = controller.solveBoard(boards[1]);
		String result3 = controller.solveBoard(boards[2]);
		String result4 = controller.solveBoard(boards[3]);
		String result5 = controller.solveBoard(boards[4]);
		
		// Test a board that is not solvable 
		verify(result.contains("Alice is stuck!"), "Expected: The board should have returned Alice is stuck! as the steps,  Result: " + result);
		// Test the Bishop piece on the board first
		verify(result2.contains("BRBNP"), "Expected: The board should have returned BRBNP as the steps,  Result: " + result2);
		// Test the Rook piece on the board first
		verify(result3.contains("RQNBR"), "Expected: The board should have returned RQNBR as the steps,  Result: " + result3);	
		// Test the Knight piece on the board first
		verify(result4.contains("NBRQ"), "Expected: The board should have returned NBRQ as the steps,  Result: " + result4);		
		// Test the Queen piece on the board first
		verify(result5.contains("QNRBK"), "Expected: The board should have returned QNRBK as the steps,  Result: " + result5);	
	}
	
	
	
	private void verify (boolean correct, String error) {
		if (!correct) fail(error);
	}
}

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
		// Test the Pawn piece with the ______ board
		
		// Test the Bishop piece with the ______ board
		verify(result.contains("Alice is stuck!"), "Expected: The board should have returned Alice is stuck! as the steps,  Result: " + result);
		// Test the Rook piece with the ______ board
		
		// Test the Knight piece with the ______ board
		
		// Test the Queen and King pieces with the _____ board
		
		// Test a non-working board with the ______ board
		
	}
	
	
	
	private void verify (boolean correct, String error) {
		if (!correct) fail(error);
	}
}

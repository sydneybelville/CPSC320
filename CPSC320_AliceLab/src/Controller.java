import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class Controller {

	private Board[] boards;
	
	public static void main (String args []) {
		Controller controller = new Controller();
		controller.go();
	}
	
	
	public void go() {
		// Need to get the boards, run through all the boards, then solve them
		this.boards = getBoard();
		for (int i = 0; i < boards.length; i++) {
			String capturePath = solveBoard(boards[i]);
			System.out.println("Board " + (i + 1) + ": " + capturePath);
		}
	}
	
	
	public Board[] getBoard() {
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
	
	
	
	public String solveBoard (Board boards) {
		String capturePath = "";
		Piece currentPlayer = null;
		// Make sure to start in the bottom right corner
		int rowForCurrentPiece = 7;
		int columnForCurrentPiece = 0;
		// Goes through the bottom column to find the non-empty space where Alice must start
		for (int i = 0; i < 8; i++) {
			if (boards.getPiece(7, i) != null) {
				 currentPlayer = boards.getPiece(7, i);
				 currentPlayer.changeColor(PieceColor.White);
				 columnForCurrentPiece = i;
				 break;
			}
		}
		capturePath = runThroughBoard("", boards, currentPlayer, columnForCurrentPiece,rowForCurrentPiece, -1, -1);
		if(capturePath!=null) {
			capturePath = capturePath.replaceAll("2", "");
			capturePath = capturePath.replaceAll("1", "");
		}
		else {
			capturePath = "Alice is stuck!";
		}
		return capturePath;
	}
	
	
	
	public String runThroughBoard (String capturePath, Board boards, Piece currentPlayer, int columnForCurrentPiece, int rowForCurrentPiece, int columnForPreviousPiece, int rowForPreviousPiece) {
		// Add the current piece to the path
	    capturePath += boards.getPiece(rowForCurrentPiece, columnForCurrentPiece).getSymbol();

	    // Capture the current piece
	    boards.setPiece(rowForCurrentPiece, columnForCurrentPiece, null);

	    // Check if all pieces have been captured
	    if (boards.countPieces(PieceColor.Black) == 0) {
	        return capturePath;
	    }

	    // Attempt to move and capture the next piece
	    for (int row = 0; row < 8; row++) {
	        for (int column = 0; column < 8; column++) {
	            if (boards.isValidCoords(row, column) && boards.getPiece(row, column) != null) {
	                Piece nextPiece = boards.getPiece(row, column);
	                if (currentPlayer.isValidMove(rowForCurrentPiece, columnForCurrentPiece, row, column)) {
	                    // Move to the next piece and recursively continue
	                    String possibleMove = runThroughBoard(capturePath, boards, nextPiece, column, row, columnForCurrentPiece, rowForCurrentPiece);
	                    if (possibleMove != null) {

	                        return possibleMove; // Return the successful path
	                    }
	                }
	            }
	        }
	    }

	    // Backtrack if no valid moves found from this position
	    boards.setPiece(rowForCurrentPiece, columnForCurrentPiece, currentPlayer);
	    return null;
	}
}

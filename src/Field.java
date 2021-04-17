import java.awt.Color;
import java.awt.Graphics;

public class Field {
	int leftIndent, topIndent, fieldSize, cellSize;
	boolean isComputerField;
	int[][] cells;
	Ship[] ships;

	Field(int topIndent, int leftIndent, int fieldSize, int cellSize, boolean isComputerField) {
		this.topIndent = topIndent;
		this.leftIndent = leftIndent;
		this.fieldSize = fieldSize;
		this.cellSize = cellSize;
		this.isComputerField = isComputerField;
		cells = new int[fieldSize][fieldSize];
		createShips();
	}

	private void createShips() {
		ships = new Ship[10];
		// �������������
		ships[0] = new Ship(1, cells);
		ships[1] = new Ship(1, cells);
		ships[2] = new Ship(1, cells);
		ships[3] = new Ship(1, cells);
		// �������������
		ships[4] = new Ship(2, cells);
		ships[5] = new Ship(2, cells);
		ships[6] = new Ship(2, cells);
		// �������������
		ships[7] = new Ship(3, cells);
		ships[8] = new Ship(3, cells);
		// ����������������
		ships[9] = new Ship(4, cells);

	}

	public void paintField(Graphics g) {

		for (int i = 0; i < fieldSize; i++) {
			for (int j = 0; j < fieldSize; j++) {

				if (/*!false*/!isComputerField) {
					if (cells[i][j] == 1) {
						g.setColor(Color.RED);
						g.fillRect(leftIndent + j * cellSize, topIndent + i * cellSize, cellSize, cellSize);
					} 					
				}
				
				
				if(cells[i][j] == 2) {
					g.setColor(Color.YELLOW);
					g.fillRect(leftIndent + j * cellSize, topIndent + i * cellSize, cellSize, cellSize);
				}
				if(cells[i][j] == 3) {
					g.setColor(Color.MAGENTA);
					g.fillRect(leftIndent + j * cellSize, topIndent + i * cellSize, cellSize, cellSize);
				}
				if (cells[i][j] == -2) {
					g.setColor(Color.BLACK);
					g.fillOval(leftIndent + j * cellSize, topIndent + i * cellSize, cellSize, cellSize);
				}

			}
		}

		for (int i = 0; i < fieldSize + 1; i++) {
			g.setColor(Color.BLACK);
			g.drawLine(leftIndent, topIndent + i * cellSize, leftIndent + fieldSize * cellSize,
					topIndent + i * cellSize); // ��������������
			g.drawLine(leftIndent + i * cellSize, topIndent, leftIndent + i * cellSize,
					topIndent + fieldSize * cellSize); // ������������
		}

	}
	public int shoot(int x, int y) {
		if(cells[y][x] == 1) {
			cells[y][x] = 2;		
			searchKilledShips();		
			return 1; // ������ �� �������
		}else if(cells[y][x] == 2){
			return 2; // ������ � ������� ������ => ���������� �������� � ��� �� �����������
		}else if(cells[y][x] == 3  || cells[y][x] == -2) {
			return 3; // ��������� ������� � ������ ������
		}else {
			cells[y][x] = -2;
			return 0; // ������
		}
	}
	public int countOfDamagedShipCells() {
		int count = 0;
		for(int i = 0; i < fieldSize; i++) {
			for(int j = 0; j < fieldSize; j++) {
				if(cells[i][j] == 2) {
					count++;
				}
			}
		}
		return count;
	}
	
	
	public void searchKilledShips() {
		for(int i = 0; i < 10; i++) {
			ships[i].kill(cells);
		}
	}

	public boolean isAllDeath() {
		int killedShips = 0;
		for (int i = 0; i < ships.length; i++) {
			if(ships[i].isKilled (cells)) {
				killedShips  += 1;
				
			}
		}
		return killedShips ==ships.length;
		
	}
	
	

}

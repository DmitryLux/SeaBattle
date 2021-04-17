import java.util.Random;

public class Ship {
	Random rn;
	int dirX, dirY, x, y, len;

	public Ship(int len, int[][] cells) {
		rn = new Random();

		x = rn.nextInt(10);
		y = rn.nextInt(10);
		this.len = len;

		chooseDirection();
		boolean canSetShip = false;
		while (!canSetShip) {
			x = rn.nextInt(10);
			y = rn.nextInt(10);
			chooseDirection();
			canSetShip = true;
			for (int i = 0; i < len; i++) {
				if (isOutOfBounds(x, y) || !canSurroundWithBombs(x, y, cells)) {
					canSetShip = false;
				}
				x += dirX;
				y += dirY;
			}
		}
		for (int i = 0; i < len; i++) {
			x -= dirX;
			y -= dirY;
			cells[y][x] = 1;
			surroundWithBombs(x, y, cells, -1);
		}
	}

	public void chooseDirection() {
		int dir = rn.nextInt(4);
		switch (dir) {
		case 0: // влево
			dirX = -1;
			dirY = 0;
			break;
		case 1:
			dirX = 0;
			dirY = -1;
			break;
		case 2:
			dirX = 1;
			dirY = 0;
			break;
		case 3:
			dirX = 0;
			dirY = 1;
			break;
		default:
			break;
		}
	}

	public boolean isOutOfBounds(int x, int y) {
		if (x < 0 || x > 9 || y < 0 || y > 9) {
			return true;
		} else {
			return false;
		}
	}

	public boolean canSurroundWithBombs(int x, int y, int[][] cells) {
		boolean canSurroundWithBombs = true;
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (!isOutOfBounds(x + j, y + i)) {
					if (cells[y + i][x + j] == 1) {
						canSurroundWithBombs = false;
					}
				}
			}
		}
		return canSurroundWithBombs;
	}

	public void surroundWithBombs(int x, int y, int[][] cells, int bombType) {
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (!isOutOfBounds(x + j, y + i)) {
					if (cells[y + i][x + j] < 1) { 			
						cells[y + i][x + j] = bombType;		
					}
				}
			}
		}
	}

	public boolean isKilled(int[][] cells) {
		int k = 0;
		int x = this.x;
		int y = this.y;
		for (int i = 0; i < len; i++) {
			if (cells[y][x] == 2 || cells[y][x] == 3) {
				k++;
			}
			y += dirY;
			x += dirX;
		}
		return k == len;

	}

	public void kill(int[][] cells) {
		int x = this.x;
		int y = this.y;
		if (isKilled(cells)) {
			for (int i = 0; i < len; i++) {
				cells[y][x] = 3;
				surroundWithBombs(x, y, cells, -2);
				
				y += dirY;
				x += dirX;
				
			}
		}
	}

}

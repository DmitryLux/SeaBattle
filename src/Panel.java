import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Panel extends JPanel {
	Game game;
	Font font;
	String[] credits = {
			"Над игрой работали:",
			"\n",
			"Великий Всепоглощающий Всеобъемлющий Потрясающий Незабываемый Арсений",
			"\n",
			"EGORKA",
			"\n",
			"Anonimus228",
			"\n",
			"Божией милостию, Мы, Великий Государ Царь"
			,"               и Великий Князь Дмитрий Андреевич всея Руси Самодержавец"
			, "\n",
			"Маленький Тостер Роман Олегович",
			"\n",
			"Неповторимый Король Java Prodackshen", "\n", "\n", "\n", "\n",};
	String paintedCredits = "";
	int letterPos = 0;
	int stringPos = 0;
	Timer tm;
	int blackSquareWidth = 4000;

	Panel() {
		tm = new Timer(1000 / 5, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		setLayout(null);
		game = new Game();
		tm = new Timer(100, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				paintedCredits = paintedCredits + credits[stringPos].charAt(letterPos);
				letterPos = letterPos + 1;
				if (letterPos >= credits[stringPos].length()) {
					stringPos += 1;
					letterPos = 0;
					paintedCredits = "";
					if (stringPos >= credits.length) {
						tm.stop();
						blackSquareWidth = 0;
						repaint();
					}
				}
				repaint();
			}
		});
		repaint();
		font = new Font("Bold Script", 2, 40);
		JButton nGameBtn = new JButton("Начать 3-ю мировую");
		nGameBtn.setBounds(500, 700, 200, 50);
		add(nGameBtn);
		nGameBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				game = new Game();
				repaint();
			}
		});
		JButton exitBtn = new JButton("Выйти на балкон");
		exitBtn.setBounds(1250, 700, 200, 50);
		add(exitBtn);
		exitBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

	}

	public void paint(Graphics g) {
		super.paint(g);
		game.computerField.paintField(g);
		game.playerField.paintField(g);
		if (game.computerWin()) {
			int x = game.computerLeftIndent - game.centerIndent;
			int y = game.topIndent + game.fieldSize * game.cellSize + 50;
			g.setFont(font);
			g.drawString("Ты проиграл!", x, y);
		} else if (game.playerWin()) {
			int x = game.computerLeftIndent - game.centerIndent;
			int y = game.topIndent + game.fieldSize * game.cellSize + 50;
			g.setFont(font);

			g.setColor(Color.black);
			g.fillRect(0, 0, blackSquareWidth, 2000);

			g.setColor(Color.white);
			g.drawString("На этот раз ты победил", x, y);
			if (stringPos == 0)
				tm.start();
			if (stringPos < credits.length) {
				if (stringPos > 0) {
					for (int i = 0; i < stringPos; i++) {
						g.drawString(credits[i], 0, 50 + 40 * i);
					}
				}
				g.drawString(paintedCredits, 0, 50 + 40 * stringPos);
			}else{
				game = new Game();
				stringPos = 0;
				repaint();
				blackSquareWidth = 4000;
			}
		}
		
	  }
    }
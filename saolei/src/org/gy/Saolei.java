package org.gy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Saolei implements ActionListener {
//hello world888
	JFrame frame = new JFrame();
	ImageIcon icon = new ImageIcon("images/banner.png");
	ImageIcon btnIcon = new ImageIcon("images/guess.png");
	ImageIcon leiIcon = new ImageIcon("images/bomb.png");
	ImageIcon failIcon = new ImageIcon("images/fail.png");
	ImageIcon winIcon = new ImageIcon("images/win.png");
	ImageIcon winFlagIcon = new ImageIcon("images/win_flag.png");
	JButton bannerBtn = new JButton(icon);


	// 数据结构
	int ROW = 20;
	int COL = 20;
	int[][] data = new int[ROW][COL];
	JButton[][] btns = new JButton[ROW][COL];
	int LEICOUNT = 20;
	int LEICODE = -1;
	int unopened = ROW * COL;
	int opened = 0;
	int timer=0;
	

	JLabel label1 = new JLabel("待开：" + unopened);
	JLabel label2 = new JLabel("已开：" + opened);
	JLabel label3 = new JLabel("用时：" + timer + "s");
	Timer t = new Timer(1000,this);
	
	public Saolei() {
		frame.setSize(950, 960);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout()); // five area
		setHeader();
		addLei();
		setButtons();
		t.start();
		frame.setVisible(true);

	}

	private void addLei() {
		Random rand = new Random();
		for (int i = 0; i < LEICOUNT;) {
			int r = rand.nextInt(ROW);
			int c = rand.nextInt(COL);
			if (data[r][c] != LEICODE) {
				data[r][c] = LEICODE;
				i++;
			}
		}
//		/*
//		 * [i-1][j-1] [i-1][j] [i-1][j+1] 
//		 * [i]  [j-1] [i]  [j] [i]  [j+1]
//		 * [i+1][j-1] [i+1][j] [i+1][j+1]
//		 */
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				if (data[i][j] == LEICODE) {
					continue;
				}
				int tempCount = 0;
				if (i > 0 && j > 0 && data[i - 1][j - 1] == LEICODE) {
					tempCount++;
				}
				if (i > 0 && data[i - 1][j] == LEICODE) {
					tempCount++;
				}
				if (i > 0 && j < 19 && data[i - 1][j + 1] == LEICODE) {
					tempCount++;
				}
				if (j > 0 && data[i][j - 1] == LEICODE) {
					tempCount++;
				}
				if (j < 19 && data[i][j + 1] == LEICODE) {
					tempCount++;
				}
				if (i < 19 && j > 0 && data[i+1][j - 1] == LEICODE) {
					tempCount++;
				}
				if (i < 19 && data[i + 1][j] == LEICODE) {
					tempCount++;
				}
				if (i < 19 && j < 19 && data[i+1][j + 1] == LEICODE) {
					tempCount++;
				}
				data[i][j]=tempCount;
			}
		}
	}

	private void setButtons() {
		Container container = new Container();
		container.setLayout(new GridLayout(ROW, COL));
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
//				JButton btn = new JButton(data[i][j] + "");
				JButton btn = new JButton(btnIcon);
				btn.setBackground(new Color(244,183,113));
				btn.addActionListener(this);
				container.add(btn);
				btns[i][j] = btn;
			}
		}
		frame.add(container, BorderLayout.CENTER);
	}

	private void setHeader() {
		JPanel panel = new JPanel(new GridBagLayout());

		GridBagConstraints c1 = new GridBagConstraints(0, 0, 3, 1, 1.0, 1.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
		panel.add(bannerBtn, c1);
		bannerBtn.addActionListener(this);

		label1.setOpaque(true);
		label1.setBackground(Color.WHITE);
		label1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		label2.setOpaque(true);
		label2.setBackground(Color.WHITE);
		label2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		label3.setOpaque(true);
		label3.setBackground(Color.WHITE);
		label3.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		bannerBtn.setOpaque(true);
		bannerBtn.setBackground(Color.WHITE);
		bannerBtn.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		GridBagConstraints c2 = new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
		panel.add(label1, c2);
		GridBagConstraints c3 = new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
		panel.add(label2, c3);
		GridBagConstraints c4 = new GridBagConstraints(2, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
		panel.add(label3, c4);

		frame.add(panel, BorderLayout.NORTH);

	}

	public static void main(String[] args) {
		new Saolei();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof Timer) {
			timer++;
			label3.setText("用时：" + timer + "s");
			t.start();
			return;
		}
		
		JButton btn = (JButton) e.getSource();
		if (btn.equals(bannerBtn)) {
			restart();
			return;
		}
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				if (btn.equals(btns[i][j])) {
					if (data[i][j]==LEICODE) {
						lose();
					}
					openCell(i,j);
					checkWin();
					return;
				}
			}
		}
	}

	private void restart() {
		//恢复数据和按钮
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j <COL; j++) {
				data[i][j] = 0;
				btns[i][j].setBackground(new Color(244,183,113));
				btns[i][j].setEnabled(true);
				btns[i][j].setText("");
				btns[i][j].setIcon(btnIcon);
			}
		}
		//状态栏恢复
		unopened = ROW*COL;
		opened = 0;
		timer = 0;
		label1.setText("待开：" + unopened);
		label2.setText("已开：" + opened);
		label3.setText("用时：" + timer + "s");
		//重新开始
		addLei();
		t.start();
		
	}

	private void checkWin() {
		int count =0;
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				if (btns[i][j].isEnabled()) {
					count++;
				}
			}
		}
		if (count==LEICOUNT) {
			t.stop();
			for (int i = 0; i < ROW; i++) {
				for (int j = 0; j < COL; j++) {
					if (btns[i][j].isEnabled()) {
						btns[i][j].setIcon(winFlagIcon);;
					}
				}
			}
			bannerBtn.setIcon(winIcon);
			JOptionPane.showMessageDialog(frame, "你赢了","",JOptionPane.PLAIN_MESSAGE);
		}
		
	}

	private void lose() {
		t.stop();
		bannerBtn.setIcon(failIcon);
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				JButton btn = btns[i][j];
				if (btns[i][j].isEnabled()) {
					if (data[i][j]==LEICODE) {
						btns[i][j].setEnabled(false);
						btns[i][j].setIcon(leiIcon);
						btns[i][j].setDisabledIcon(leiIcon);
					}else {
						btn.setIcon(null);
						btn.setEnabled(false);
						btn.setOpaque(true);
						btn.setText(data[i][j]+"");
					}
				}
			}
		}
		JOptionPane.showMessageDialog(frame, "恭喜你，踩到雷了！","",JOptionPane.PLAIN_MESSAGE);
	}

	private void openCell(int i, int j) {
		JButton btn = btns[i][j];
		if (!btn.isEnabled()) {
			return;
		}
		btn.setIcon(null);
		btn.setEnabled(false);
		btn.setOpaque(true);
		btn.setBackground(Color.GREEN);
		btn.setText(data[i][j]+"");
		addOpenCount();
		
		if (data[i][j] == 0) {
			if (i > 0 && j > 0 && data[i - 1][j - 1] == 0) {
				openCell(i-1, j-1);;
			}
			if (i > 0 && data[i - 1][j] == 0) {
				openCell(i-1, j);;
			}
			if (i > 0 && j < 19 && data[i - 1][j + 1] == 0) {
				openCell(i-1, j+1);
			}
			if (j > 0 && data[i][j - 1] == 0) {
				openCell(i, j-1);
			}
			if (j < 19 && data[i][j + 1] == 0) {
				openCell(i, j+1);
			}
			if (i < 19 && j > 0 && data[i+1][j - 1] == 0) {
				openCell(i+1, j-1);
			}
			if (i < 19 && data[i + 1][j] == 0) {
				openCell(i+1, j);
			}
			if (i < 19 && j < 19 && data[i+1][j + 1] == 0) {
				openCell(i+1, j+1);
			}
		}
		
	}

	private void addOpenCount() {
		opened++;
		unopened--;
		label1.setText("待开："+unopened);
		label2.setText("已开："+opened+"");
	}
}

package main;

import java.awt.EventQueue;

import mainFrame.MainFrame;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MainFrame(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
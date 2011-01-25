package br.com.jera.blitzkrieg;

import java.util.Random;

public class Player {

	private Integer height;

	public Player() {
		this.height = new Random().nextInt(20) + 3;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getHeight() {
		return height;
	}
}

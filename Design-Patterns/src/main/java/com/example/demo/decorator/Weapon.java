package com.example.demo.decorator;

public class Weapon extends CharacterDecorator{

	Character character;
	
	public Weapon(Character character) {
		this.character = character;
	}

	@Override
	public String getLore() {
		// TODO Auto-generated method stub
		return character.getLore() + ", with weapon ";
	}

	@Override
	public double attack() {
		// TODO Auto-generated method stub
		return 10 + character.attack();
	}
	
	
}

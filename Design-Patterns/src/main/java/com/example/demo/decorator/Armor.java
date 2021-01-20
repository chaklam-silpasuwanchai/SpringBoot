package com.example.demo.decorator;

public class Armor extends CharacterDecorator{

	Character character;
	
	public Armor(Character character) {
		this.character = character;
	}

	@Override
	public String getLore() {
		// TODO Auto-generated method stub
		return character.getLore() + ", with armor ";
	}

	@Override
	public double attack() {
		// TODO Auto-generated method stub
		return 2 + character.attack();
	}
	
	
}

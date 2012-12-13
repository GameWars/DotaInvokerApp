package com.example.dota.invoker.trainer;


import java.util.List;

public class Spell {

	private String name;
	private int img;
	private List<String> shortcuts;
	private List<Integer> sounds;
	
	public Spell(String name, int img, List<String> shortcuts) {
		this.name = name;
		this.setImg(img);
		this.shortcuts = shortcuts;
	}
	
	public Spell(String name, int img, List<String> shortcuts, List<Integer> sounds) {
		this.name = name;
		this.setImg(img);
		this.shortcuts = shortcuts;
		this.sounds = sounds;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<String> getShortcuts() {
		return shortcuts;
	}
	public void setShortcuts(List<String> shortcuts) {
		this.shortcuts = shortcuts;
	}
	public int getImg() {
		return img;
	}
	public void setImg(int img) {
		this.img = img;
	}
	public List<Integer> getSounds() {
		return sounds;
	}
	public void setSounds(List<Integer> sounds) {
		this.sounds = sounds;
	}
	
}

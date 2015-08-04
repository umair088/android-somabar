package com.pyntail.somabar.adapters;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


public class Category {

	public ArrayList<Category> children;
	public ArrayList<String> selection;
	
	public String name;
	public String counter;
	
	public Category() {
		children = new ArrayList<Category>();
		selection = new ArrayList<String>();
	}
	
	public Category(String name) {
		this();
		this.name = name;
	}
	
	public Category(String name,String counter) {
		this();
		this.name = name;
		this.counter =counter;
	}
	
	public String toString() {
		return this.name;
	}
	
	public String getCounter() {
		return this.counter;
	}
	
	// generate some random amount of child objects (1..10)
	private void generateChildren() {
		String[] childCategories = {"Vodka" ,"Gin" ,"Tequila","whiskey" ,"Rum" };
		String[] childCounters = {"(30)" ,"(18)" ,"(50)","(12)" ,"(12)" ,"(12)" ,"(12)" ,"(12)" ,"(12)","(12)" ,"(12)"};

		for(int i=0; i < childCategories.length; i++) {
			Category cat = new Category(childCategories[i],childCounters[i]);
			this.children.add(cat);
		}
	}
	
	public static ArrayList<Category> getCategories() {
		String[] parentCategories = {"Base Spiritit" ,"Cocktail Type" ,"Mood","Flavor" ,"Around World" ,"Skinny Drinks" ,"Urban Delights" };
		String[] parentCounters = {"(30)" ,"(18)" ,"(50)","(12)" ,"(12)" ,"(12)" ,"(12)" ,"(12)" ,"(12)","(12)" ,"(12)"};
		ArrayList<Category> categories = new ArrayList<Category>();
		for(int i = 0; i <parentCategories.length  ; i++) {
			Category cat = new Category(parentCategories[i],parentCounters[i]);
			cat.generateChildren();
			categories.add(cat);
		}
		return categories;
	}
	
	public static Category get(String name)
	{
		ArrayList<Category> collection = Category.getCategories();
		for (Iterator<Category> iterator = collection.iterator(); iterator.hasNext();) {
			Category cat = (Category) iterator.next();
			if(cat.name.equals(name)) {
				return cat;
			}
			
		}
		return null;
	}
}

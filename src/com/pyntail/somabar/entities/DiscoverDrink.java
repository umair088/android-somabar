package com.pyntail.somabar.entities;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.pyntail.somabar.entities.request.Ingredient;
import com.pyntail.somabar.entities.request.Instruction;

public class DiscoverDrink {

	@Expose
	private List<Ingredient> Ingredients = new ArrayList<Ingredient>();
	@Expose
	private List<Instruction> Instructions = new ArrayList<Instruction>();
	@Expose
	private int RecipeId;
	@Expose
	private int ThumbsUp;
	@Expose
	private int OriginalRecipeId;
	
	
	/**
	* 
	* @return
	* The Ingredients
	*/
	public List<Ingredient> getIngredients() {
	return Ingredients;
	}

	/**
	* 
	* @param Ingredients
	* The Ingredients
	*/
	public void setIngredients(List<Ingredient> Ingredients) {
	this.Ingredients = Ingredients;
	}

	/**
	* 
	* @return
	* The Instructions
	*/
	public List<Instruction> getInstructions() {
	return Instructions;
	}

	/**
	* 
	* @param Instructions
	* The Instructions
	*/
	public void setInstructions(List<Instruction> Instructions) {
	this.Instructions = Instructions;
	}

	/**
	* 
	* @return
	* The RecipeId
	*/
	public int getRecipeId() {
	return RecipeId;
	}

	/**
	* 
	* @param RecipeId
	* The RecipeId
	*/
	public void setRecipeId(int RecipeId) {
	this.RecipeId = RecipeId;
	}

	/**
	* 
	* @return
	* The ThumbsUp
	*/
	public int getThumbsUp() {
	return ThumbsUp;
	}

	/**
	* 
	* @param ThumbsUp
	* The ThumbsUp
	*/
	public void setThumbsUp(int ThumbsUp) {
	this.ThumbsUp = ThumbsUp;
	}

	/**
	* 
	* @return
	* The OriginalRecipeId
	*/
	public int getOriginalRecipeId() {
	return OriginalRecipeId;
	}

	/**
	* 
	* @param OriginalRecipeId
	* The OriginalRecipeId
	*/
	public void setOriginalRecipeId(int OriginalRecipeId) {
	this.OriginalRecipeId = OriginalRecipeId;
	}

	
}

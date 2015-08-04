package com.pyntail.somabar.entities;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.pyntail.somabar.entities.request.Ingredient;
import com.pyntail.somabar.entities.request.Instruction;

public class FavoriteObject {

	@Expose
	private com.pyntail.somabar.entities.request.User User;
	@Expose
	private int OriginalRecipeId;
	@Expose
	private int RecipeId;
	@Expose
	private String Name;
	@Expose
	private String ImageUrl;
	@Expose
	private String Garnish;
	@Expose
	private String GlassType;
	@Expose
	private String IceType;
	@Expose
	private int MaxQuantity;
	@Expose
	private int ThumbsUp;
	@Expose
	private List<Ingredient> Ingredients = new ArrayList<Ingredient>();
	@Expose
	private List<Instruction> Instructions = new ArrayList<Instruction>();

	/**
	* 
	* @return
	* The User
	*/
	public com.pyntail.somabar.entities.request.User getUser() {
	return User;
	}

	/**
	* 
	* @param User
	* The User
	*/
	public void setUser(com.pyntail.somabar.entities.request.User User) {
	this.User = User;
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
	* The Name
	*/
	public String getName() {
	return Name;
	}

	/**
	* 
	* @param Name
	* The Name
	*/
	public void setName(String Name) {
	this.Name = Name;
	}

	/**
	* 
	* @return
	* The ImageUrl
	*/
	public String getImageUrl() {
	return ImageUrl;
	}

	/**
	* 
	* @param ImageUrl
	* The ImageUrl
	*/
	public void setImageUrl(String ImageUrl) {
	this.ImageUrl = ImageUrl;
	}

	/**
	* 
	* @return
	* The Garnish
	*/
	public String getGarnish() {
	return Garnish;
	}

	/**
	* 
	* @param Garnish
	* The Garnish
	*/
	public void setGarnish(String Garnish) {
	this.Garnish = Garnish;
	}

	/**
	* 
	* @return
	* The GlassType
	*/
	public String getGlassType() {
	return GlassType;
	}

	/**
	* 
	* @param GlassType
	* The GlassType
	*/
	public void setGlassType(String GlassType) {
	this.GlassType = GlassType;
	}

	/**
	* 
	* @return
	* The IceType
	*/
	public String getIceType() {
	return IceType;
	}

	/**
	* 
	* @param IceType
	* The IceType
	*/
	public void setIceType(String IceType) {
	this.IceType = IceType;
	}

	/**
	* 
	* @return
	* The MaxQuantity
	*/
	public int getMaxQuantity() {
	return MaxQuantity;
	}

	/**
	* 
	* @param MaxQuantity
	* The MaxQuantity
	*/
	public void setMaxQuantity(int MaxQuantity) {
	this.MaxQuantity = MaxQuantity;
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
	
	
	
}

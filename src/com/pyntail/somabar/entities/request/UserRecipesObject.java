package com.pyntail.somabar.entities.request;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRecipesObject {

	
	@SerializedName("User")
	private User user;
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
	public User getUser() {
	return user;
	}

	/**
	* 
	* @param User
	* The User
	*/
	public void setUser(User User) {
	this.user = User;
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

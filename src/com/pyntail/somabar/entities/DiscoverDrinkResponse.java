package com.pyntail.somabar.entities;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.pyntail.somabar.entities.request.Ingredient;
import com.pyntail.somabar.entities.request.Instruction;

public class DiscoverDrinkResponse {

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
	private float MaxQuanity;
	@Expose
	private int NoOfLikes;
	@Expose
	private List<String> BaseSpiritTags = new ArrayList<String>();
	@Expose
	private List<String> CocktailTags = new ArrayList<String>();
	@Expose
	private List<Object> FlavorTags = new ArrayList<Object>();
	@Expose
	private List<String> MoodTags = new ArrayList<String>();
	@Expose
	private List<Object> AroundTheWorldTags = new ArrayList<Object>();
	@Expose
	private List<Object> SkinnyDrinkTags = new ArrayList<Object>();
	@Expose
	private List<Ingredient> Ingredients = new ArrayList<Ingredient>();
	@Expose
	private List<Instruction> Instructions = new ArrayList<Instruction>();

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
	* The MaxQuanity
	*/
	public float getMaxQuanity() {
	return MaxQuanity;
	}

	/**
	* 
	* @param MaxQuanity
	* The MaxQuanity
	*/
	public void setMaxQuanity(float MaxQuanity) {
	this.MaxQuanity = MaxQuanity;
	}

	/**
	* 
	* @return
	* The NoOfLikes
	*/
	public int getNoOfLikes() {
	return NoOfLikes;
	}

	/**
	* 
	* @param NoOfLikes
	* The NoOfLikes
	*/
	public void setNoOfLikes(int NoOfLikes) {
	this.NoOfLikes = NoOfLikes;
	}

	/**
	* 
	* @return
	* The BaseSpiritTags
	*/
	public List<String> getBaseSpiritTags() {
	return BaseSpiritTags;
	}

	/**
	* 
	* @param BaseSpiritTags
	* The BaseSpiritTags
	*/
	public void setBaseSpiritTags(List<String> BaseSpiritTags) {
	this.BaseSpiritTags = BaseSpiritTags;
	}

	/**
	* 
	* @return
	* The CocktailTags
	*/
	public List<String> getCocktailTags() {
	return CocktailTags;
	}

	/**
	* 
	* @param CocktailTags
	* The CocktailTags
	*/
	public void setCocktailTags(List<String> CocktailTags) {
	this.CocktailTags = CocktailTags;
	}

	/**
	* 
	* @return
	* The FlavorTags
	*/
	public List<Object> getFlavorTags() {
	return FlavorTags;
	}

	/**
	* 
	* @param FlavorTags
	* The FlavorTags
	*/
	public void setFlavorTags(List<Object> FlavorTags) {
	this.FlavorTags = FlavorTags;
	}

	/**
	* 
	* @return
	* The MoodTags
	*/
	public List<String> getMoodTags() {
	return MoodTags;
	}

	/**
	* 
	* @param MoodTags
	* The MoodTags
	*/
	public void setMoodTags(List<String> MoodTags) {
	this.MoodTags = MoodTags;
	}

	/**
	* 
	* @return
	* The AroundTheWorldTags
	*/
	public List<Object> getAroundTheWorldTags() {
	return AroundTheWorldTags;
	}

	/**
	* 
	* @param AroundTheWorldTags
	* The AroundTheWorldTags
	*/
	public void setAroundTheWorldTags(List<Object> AroundTheWorldTags) {
	this.AroundTheWorldTags = AroundTheWorldTags;
	}

	/**
	* 
	* @return
	* The SkinnyDrinkTags
	*/
	public List<Object> getSkinnyDrinkTags() {
	return SkinnyDrinkTags;
	}

	/**
	* 
	* @param SkinnyDrinkTags
	* The SkinnyDrinkTags
	*/
	public void setSkinnyDrinkTags(List<Object> SkinnyDrinkTags) {
	this.SkinnyDrinkTags = SkinnyDrinkTags;
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

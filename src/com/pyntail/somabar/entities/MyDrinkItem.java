package com.pyntail.somabar.entities;

import java.util.ArrayList;
import java.util.List;

import com.pyntail.somabar.entities.request.Ingredient;

public class MyDrinkItem {


	
	
	/*DISCOVER DRINKS*/
	private String drinkName;
	private int recipeId;
	private int likeCount=0;
	private boolean isLike=false;
	private boolean isFavorite=false;
	private boolean isDeleted=false;
	
	
	/*Ingredients of DRINKS*/

	private List<Ingredient> Ingredients = new ArrayList<Ingredient>();
	
	
	private String txtIngredient;
	private String imgUrl;
	private String txtTitleOfProduct;
	
	private int imgResourceId;
	
	
	

	public int getResourceId() {
		return imgResourceId;
	}

	public void setResourceId(int resourceId) {
		this.imgResourceId = resourceId;
	}

	
	public boolean isLike() {
		return isLike;
	}

	public void setIsLike(boolean isLike) {
		this.isLike = isLike;
	}
	
	
	public boolean isFavorite() {
		return isFavorite;
	}

	public void setIsFavorite(boolean fav) {
		this.isFavorite = fav;
	}
	
	
	
	public boolean isDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean deleted) {
		this.isDeleted = deleted;
	}

	
	public MyDrinkItem()
	{
		
	}
	
	public MyDrinkItem(String txtTitleOfProduct, String txtIngredient) {
		this.txtTitleOfProduct = txtTitleOfProduct;
		this.txtIngredient = txtIngredient;

	}

	public MyDrinkItem(String txtTitleOfProduct, String txtIngredient, String imgUrl) {
		this.txtTitleOfProduct = txtTitleOfProduct;
		this.txtIngredient = txtIngredient;
		this.imgUrl = imgUrl;
	}

	public MyDrinkItem(String txtTitleOfProduct, int imgResourceId,String txtIngredient ) {
		this.txtTitleOfProduct = txtTitleOfProduct;
		this.txtIngredient = txtIngredient;
		this.imgResourceId = imgResourceId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	
	public int getReciepeId() {
		return recipeId;
	}

	public void setReciepeId(int recId) {
		this.recipeId = recId;
	}
	
	
	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	
	
	
	
	public String getDrinkName() {
		return drinkName;
	}

	public void setDrinkName(String name) {
		this.drinkName = name;
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
	
	
	
	public String getTitleOfProduct() {
		return txtTitleOfProduct;
	}

	public String getIngredient() {
		return txtIngredient;
	}

	@Override
	public String toString() {
		return txtTitleOfProduct + ": " + txtIngredient;
	}

	


}

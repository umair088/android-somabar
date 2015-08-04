package com.pyntail.somabar.entities.request;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;

public class Ingredient {
	
	
	@Expose
	private int IngredientId;
	@Expose
	private float Quantity;
	@Expose
	private boolean IsGarnish;
	@Expose
	private String Label;
	@Expose
	private String NfcId;
	@Expose
	private float CorrectionFactor;
	
	
	@Nullable
	private int ingredientIndex;

	
	
	/**
	 * 
	 * @return The IngredientId
	 */
	public int getIngredientIndex() {
		return ingredientIndex;
	}

	/**
	 * 
	 * @param IngredientId
	 *            The IngredientId
	 */
	public void setIngredientIndex(int index) {
		this.ingredientIndex = index;
	}
	
	
	
	
	/**
	 * 
	 * @return The IngredientId
	 */
	public int getIngredientId() {
		return IngredientId;
	}

	/**
	 * 
	 * @param IngredientId
	 *            The IngredientId
	 */
	public void setIngredientId(int IngredientId) {
		this.IngredientId = IngredientId;
	}

	/**
	 * 
	 * @return The Quantity
	 */
	public float getQuantity() {
		return Quantity;
	}

	/**
	 * 
	 * @param Quantity
	 *            The Quantity
	 */
	public void setQuantity(float Quantity) {
		this.Quantity = Quantity;
	}

	/**
	 * 
	 * @return The IsGarnish
	 */
	public boolean isIsGarnish() {
		return IsGarnish;
	}

	/**
	 * 
	 * @param IsGarnish
	 *            The IsGarnish
	 */
	public void setIsGarnish(boolean IsGarnish) {
		this.IsGarnish = IsGarnish;
	}

	/**
	 * 
	 * @return The Label
	 */
	public String getLabel() {
		return Label;
	}

	/**
	 * 
	 * @param Label
	 *            The Label
	 */
	public void setLabel(String Label) {
		this.Label = Label;
	}

	/**
	 * 
	 * @return The NfcId
	 */
	public String getNfcId() {
		return NfcId;
	}

	/**
	 * 
	 * @param NfcId
	 *            The NfcId
	 */
	public void setNfcId(String NfcId) {
		this.NfcId = NfcId;
	}

	/**
	 * 
	 * @return The CorrectionFactor
	 */
	public float getCorrectionFactor() {
		return CorrectionFactor;
	}

	/**
	 * 
	 * @param CorrectionFactor
	 *            The CorrectionFactor
	 */
	public void setCorrectionFactor(float CorrectionFactor) {
		this.CorrectionFactor = CorrectionFactor;
	}

}

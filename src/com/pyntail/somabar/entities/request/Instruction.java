package com.pyntail.somabar.entities.request;

import com.google.gson.annotations.Expose;

public class Instruction {

	@Expose
	private int InstructionId;
	@Expose
	private boolean IsPrepInstruction;
	@Expose
	private int Step=0;
	@Expose
	private String ImageUrl;
	@Expose
	private String Detail;

	/**
	 * 
	 * @return The InstructionId
	 */
	public int getInstructionId() {
		return InstructionId;
	}

	/**
	 * 
	 * @param InstructionId
	 *            The InstructionId
	 */
	public void setInstructionId(int InstructionId) {
		this.InstructionId = InstructionId;
	}

	/**
	 * 
	 * @return The IsPrepInstruction
	 */
	public boolean isIsPrepInstruction() {
		return IsPrepInstruction;
	}

	/**
	 * 
	 * @param IsPrepInstruction
	 *            The IsPrepInstruction
	 */
	public void setIsPrepInstruction(boolean IsPrepInstruction) {
		this.IsPrepInstruction = IsPrepInstruction;
	}

	/**
	 * 
	 * @return The Step
	 */
	public int getStep() {
		return Step;
	}

	/**
	 * 
	 * @param Step
	 *            The Step
	 */
	public void setStep(int Step) {
		this.Step = Step;
	}

	/**
	 * 
	 * @return The ImageUrl
	 */
	public String getImageUrl() {
		return ImageUrl;
	}

	/**
	 * 
	 * @param ImageUrl
	 *            The ImageUrl
	 */
	public void setImageUrl(String ImageUrl) {
		this.ImageUrl = ImageUrl;
	}

	/**
	 * 
	 * @return The Detail
	 */
	public String getDetail() {
		return Detail;
	}

	/**
	 * 
	 * @param Detail
	 *            The Detail
	 */
	public void setDetail(String Detail) {
		this.Detail = Detail;
	}

}

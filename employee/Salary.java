package com.example.employee;

import javax.persistence.*;

@Entity
@Table(name="salary")
public class Salary {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sal_id", nullable=false, unique=true)
	private int id;
	
	@Column(name="sal_base")
	private double baseSal;
	
	@Column(name="sal_travel")
	private double travelPercent;
	
	@Column(name="sal_food")
	private double foodPercent;
	
	@Column(name="sal_insurance")
	private double insurancePercent;

	public Salary() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getBaseSal() {
		return baseSal;
	}

	public void setBaseSal(double baseSal) {
		this.baseSal = baseSal;
	}

	public double getTravelPercent() {
		return travelPercent;
	}

	public void setTravelPercent(double travelPercent) {
		this.travelPercent = travelPercent;
	}

	public double getFoodPercent() {
		return foodPercent;
	}

	public void setFoodPercent(double foodPercent) {
		this.foodPercent = foodPercent;
	}

	public double getInsurancePercent() {
		return insurancePercent;
	}

	public void setInsurancePercent(double insurancePercent) {
		this.insurancePercent = insurancePercent;
	}

	@Override
	public String toString() {
		return "Salary [id=" + id + ", baseSal=" + baseSal + ", travelPercent=" + travelPercent + ", foodPercent="
				+ foodPercent + ", insurancePercent=" + insurancePercent + "]";
	}
	
	
	
}

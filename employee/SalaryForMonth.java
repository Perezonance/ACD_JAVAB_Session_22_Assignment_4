package com.example.employee;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name="sal_for_month")
public class SalaryForMonth {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sfm_id", nullable=false, unique=true)
	private int id;
	
	@Column(name="sfm_date")
	private Date date;
	
	@Column(name="sfm_base_sal")
	private double baseSal;
	
	@Column(name="sfm_p1")
	private double travelPay;
	
	@Column(name="sfm_p2")
	private double foodPay;
	
	@Column(name="sfm_p3")
	private double insurancePay;
	
	@Column(name="sfm_final_sal")
	private double finalSal;
	
	@Column(name="sfm_payment_status")
	private String paymentStatus;
	
	@ManyToOne
	@JoinColumn(name="sfm_employee_id")
	private Employee employee;

	public SalaryForMonth() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SalaryForMonth(Date month, Employee employee, Double baseSalary, Double travel, Double food,
			Double insurance, Double finalSalary, String paymentStatus) {
		super();
		this.date = month;
		this.employee = employee;
		this.baseSal = baseSalary;
		this.travelPay = travel;
		this.foodPay = food;
		this.insurancePay = insurance;
		this.finalSal = finalSalary;
		this.paymentStatus = paymentStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getBaseSal() {
		return baseSal;
	}

	public void setBaseSal(double baseSal) {
		this.baseSal = baseSal;
	}

	public double getTravelPay() {
		return travelPay;
	}

	public void setTravelPay(double travelPay) {
		this.travelPay = travelPay;
	}

	public double getFoodPay() {
		return foodPay;
	}

	public void setFoodPay(double foodPay) {
		this.foodPay = foodPay;
	}

	public double getInsurancePay() {
		return insurancePay;
	}

	public void setInsurancePay(double insurancePay) {
		this.insurancePay = insurancePay;
	}

	public double getFinalSal() {
		return finalSal;
	}

	public void setFinalSal(double finalSal) {
		this.finalSal = finalSal;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "SalaryForMonth [id=" + id + ", date=" + date + ", baseSal=" + baseSal + ", travelPay=" + travelPay
				+ ", foodPay=" + foodPay + ", insurancePay=" + insurancePay + ", finalSal=" + finalSal
				+ ", paymentStatus=" + paymentStatus + ", employee=" + employee + "]";
	}
	
	
	
	
}

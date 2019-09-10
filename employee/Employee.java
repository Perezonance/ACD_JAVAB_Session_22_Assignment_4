package com.example.employee;


import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="emp_id", nullable=false, unique=true)
	private int id;
	
	@Column(name="emp_name")
	private String name;
	
	@Column(name="emp_email")
	private String email;
	
	@Column(name="phone")
	private String phoneNum;
	
	@Column(name="emp_overpay")
	private double overpay;
	
	@Column(name="isActive")
	private String isActive;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Account account;
	
	@ManyToOne
	@JoinColumn(name="sal_id")
	private Salary salary;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	private Set<SalaryForMonth> sfm = new HashSet<SalaryForMonth>();
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Salary getSalary() {
		return salary;
	}

	public void setSalary(Salary salary) {
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Account getAccounts() {
		return account;
	}

	public void setAccounts(Account account) {
		this.account = account;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public double getOverpay() {
		return overpay;
	}

	public void setOverpay(double overpay) {
		this.overpay = overpay;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Set<SalaryForMonth> getSfm() {
		return sfm;
	}

	public void setSfm(Set<SalaryForMonth> sfm) {
		this.sfm = sfm;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", email=" + email + ", phoneNum=" + phoneNum + ", overpay="
				+ overpay + ", isActive=" + isActive + ", account=" + account + ", salary=" + salary + ", sfm=" + sfm
				+ "]";
	}
	
	public SalaryForMonth getSfm(Date date, String status) {
		Double base = overpay + salary.getBaseSal();
		Double travel = base * salary.getTravelPercent() / 100;
		Double food = base * salary.getFoodPercent();
		Double insurance = base * salary.getInsurancePercent();
		return new SalaryForMonth(date, this, base, travel, food, insurance, base + travel + food + insurance, status);
	}
	
	public SalaryForMonth getSfm( Date date, String status, Integer days, Integer daysThisMonth ) {
		Double base = (overpay + salary.getBaseSal()) * Double.valueOf(days) / daysThisMonth ;
		Double travel = base * salary.getTravelPercent();
		Double food = base * salary.getFoodPercent();
		Double insurance = base * salary.getInsurancePercent();
		return new SalaryForMonth(date, this, base, travel, food, insurance, base + travel + food + insurance, status);
	}
	
	
	
}

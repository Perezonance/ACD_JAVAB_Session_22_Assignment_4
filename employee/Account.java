package com.example.employee;

import javax.persistence.*;

@Entity
@Table(name="account")
public class Account {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="account_id", unique=true, nullable=false)
	private int id;
	
	@Column(name="account_number")
	private String accountNum;
	
	@OneToOne(mappedBy = "account")
	private Employee employee;
	
	@Column(name="account_type")
	private String accountType;
	
	@Column(name="account_branch_code")
	private String routingNum;
	
	@Column(name="account_bank_name")
	private String bankName;

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}


	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getRoutingNum() {
		return routingNum;
	}

	public void setRoutingNum(String routingNum) {
		this.routingNum = routingNum;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", accountNum=" + accountNum + ", employee=" + employee + ", accountType="
				+ accountType + ", routingNum=" + routingNum + ", bankName=" + bankName + "]";
	}
	

	
	
	
}

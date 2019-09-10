package com.example.employee;

import org.hibernate.cfg.Configuration;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class EmployeeMain {
	
	private static SessionFactory factory;
	
	public static void main(String[] args) {
		
		factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		
		////////////Salaries////////////
		Salary s1 = new Salary();
		s1.setBaseSal(3000);
		s1.setFoodPercent(0);
		s1.setTravelPercent(0);
		s1.setInsurancePercent(10);
		
		Salary s2 = new Salary();
		s2.setBaseSal(5000);
		s2.setFoodPercent(5);
		s2.setTravelPercent(5);
		s2.setInsurancePercent(10);
		
		Salary s3 = new Salary();
		s3.setBaseSal(7000);
		s3.setFoodPercent(10);
		s3.setTravelPercent(10);
		s3.setInsurancePercent(10);
		
		Salary[] sals = {s1, s2, s3};
		
		////////////Employees////////////
		Employee e1 = new Employee();
		e1.setName("Alex");
		e1.setSalary(s1);
		e1.setPhoneNum("404-789-6747");
		e1.setIsActive("active");
		e1.setOverpay(1500);
		e1.setEmail("alexmperez97@gmail.com");
		
		Employee e2 = new Employee();
		e2.setName("Bilbo Baggins");
		e2.setSalary(s3);
		e2.setPhoneNum("589-412-5565");
		e2.setIsActive("active");
		e2.setOverpay(1000);
		e2.setEmail("BilboB@myspace.com");
		
		Employee e3 = new Employee();
		e3.setName("Gandalf The Grey");
		e3.setSalary(s1);
		e3.setPhoneNum("456-885-5415");
		e3.setIsActive("active");
		e3.setOverpay(250);
		e3.setEmail("gandydgray@hogwarts.com");
		
		Employee e4 = new Employee();
		e4.setName("Aragorn");
		e4.setSalary(s2);
		e4.setPhoneNum("684-788-9898");
		e4.setIsActive("active");
		e4.setOverpay(500);
		e4.setEmail("adog@hotbox.com");
		
		Employee e5 = new Employee();
		e5.setName("Logolas");
		e5.setSalary(s1);
		e5.setPhoneNum("123-456-7890");
		e5.setIsActive("inactive");
		e5.setOverpay(1000);
		e5.setEmail("legelf99@aol.net");
		
		Employee e6 = new Employee();
		e6.setName("Gollum");
		e6.setSalary(s3);
		e6.setPhoneNum("098-765-4321");
		e6.setIsActive("active");
		e6.setOverpay(0);
		e6.setEmail("whatsemail@yahoo.com");
		
		Employee[] emps = {e1, e2, e3, e4, e5, e6};
		
		////////////Accounts////////////
		
		Account a1 = new Account();
		a1.setBankName("Wachovia");
		a1.setAccountNum("W-001001");
		a1.setAccountType("Checking");
		a1.setEmployee(e1);
		a1.setRoutingNum("12434536346");
		
		Account a2 = new Account();
		a2.setBankName("WellsFargo");
		a2.setAccountNum("WF-007005");
		a2.setAccountType("Checking");
		a2.setEmployee(e2);
		a2.setRoutingNum("12434536346");
		
		Account a3 = new Account();
		a3.setBankName("Chase");
		a3.setAccountNum("C-003009");
		a3.setAccountType("Checking");
		a3.setEmployee(e3);
		a3.setRoutingNum("12434536346");
		
		Account a4 = new Account();
		a4.setBankName("WellsFargo");
		a4.setAccountNum("Wf-006022");
		a4.setAccountType("Savings");
		a4.setEmployee(e4);
		a4.setRoutingNum("12434536346");
		
		Account a5 = new Account();
		a5.setBankName("Regions");
		a5.setAccountNum("R-008021");
		a5.setAccountType("Checking");
		a5.setEmployee(e5);
		a5.setRoutingNum("12434536346");
		
		Account a6 = new Account();
		a6.setBankName("Chase");
		a6.setAccountNum("C-005004");
		a6.setAccountType("Savings");
		a6.setEmployee(e6);
		a6.setRoutingNum("12434536346");
		
		/*#1 Insert 5 Records into Employees and 3 Salary Types */
		for (Salary salary : sals) {
			System.out.println(addSalary(session, salary));
		}
		for (Employee employee : emps) {
			System.out.println(addEmployee(session, employee));
		}
		
		System.out.println();
		System.out.println();
		
		/*#2 Calculate the Salary of the month of 09/19 for all employees other than id=2*/
		List<Employee> empList = getEmployees(session);
		for (Employee employee : empList) {
			if(employee.getId() == 2) continue;
			calcSal(employee, Date.valueOf("2019-09-30"), "paid");
			updateEmployee(session, employee);
		}
		
		System.out.println();
		System.out.println();
		
		/* #3 Emp with id=3 awarded bonus of overpay +150*/
		Employee e = getEmployee(session, 3);
		e.setOverpay(e.getOverpay() + 150);
		updateEmployee(session, e);
		
		System.out.println();
		System.out.println();
		
		/* #4 Calc SalaryForMonth of 10-19 for all emps*/
		empList = getEmployees(session);
		for (Employee employee : empList) {
			calcSal(e, Date.valueOf("2019-10-31"), "paid");
			updateEmployee(session, employee);
		}
		
		System.out.println();
		System.out.println();
		
		/* #5*/
		e = getEmployee(session, 1);
		e.setIsActive("Inactive");
		Set<SalaryForMonth> salsFM = e.getSfm();
		SalaryForMonth sfm = null;
		for (SalaryForMonth salaryForMonth : salsFM) {
			if (salaryForMonth.getDate().equals(Date.valueOf("2019-10-31"))){
				salaryForMonth.setPaymentStatus("cancelled");
				break;
			}	
		}
		sfm = e.getSfm(Date.valueOf("2019-10-21"), "paid", 21, 31);
		e.getSfm().add(sfm);
		updateEmployee(session, e);
		
		/* #6 All base pays go up 10%*/
		List<Salary> salList = getSalaries(session);
		for (Salary salary : salList) {
			salary.setBaseSal(salary.getBaseSal() * 1.1);
			updateSalary(session, salary);
		}
		
		System.out.println();
		System.out.println();
		
		/* #7 Calc Salary of Month for 11-2019 for all emps*/
		empList = getEmployees(session);
		for (Employee employee : empList) {
			calcSal(e, Date.valueOf("2019-11-31"), "paid");
			updateEmployee(session, employee);
		}
		
		System.out.println();
		System.out.println();
		
		/* #8  update accounts for Emps w/ IDs 4 & 6*/
		e = getEmployee(session, 4);
		e2 = getEmployee(session, 6);
		
		Account ac1 = e.getAccount();
		Account ac2 = e2.getAccount();
		
		ac1.setAccountNum("6498352168");
		ac1.setAccountType("Savings");
		ac1.setBankName("Simple");
		ac1.setRoutingNum("68468135468");
		
		ac2.setAccountNum("68465843");
		ac2.setAccountType("Savings");
		ac2.setBankName("Green Dot");
		ac2.setRoutingNum("8765138465");
		
		updateEmployee(session, e);
		updateEmployee(session, e2);
		
		System.out.println();
		System.out.println();
		
		/* #9 Calc Salary of Month for 12-2019 for all emps */
		empList = getEmployees(session);
		for (Employee employee : empList) {
			calcSal(e, Date.valueOf("2019-12-31"), "paid");
			updateEmployee(session, employee);
		}
		
		System.out.println();
		System.out.println();
		
		/* 10 Print all Salaries paid out in final quarter */
		List<SalaryForMonth> salsfm = getSalsFM(session, Date.valueOf("2019-10-01"), Date.valueOf("2019-12-31"));
		for (SalaryForMonth salaryForMonth : salsfm) {
			System.out.println(salaryForMonth);
		}
		session.close();
	}
	
	public static int addEmployee(Session session, Employee e) {
		return (int)session.save(e);
	}
	
	public static Employee getEmployee(Session session, int id) {
		return session.get(Employee.class, id);
	}
	public static void updateEmployee(Session session, Integer id, String name) {
		Employee e = (Employee)session.get(Employee.class, id);
		e.setName(name);
		session.update(e);
	}
	public static void updateEmployee(Session session, Employee e) {
		Transaction tx = session.beginTransaction();
		try {
			session.update(e);
			session.flush();
			tx.commit();
		} catch(Exception er) {
			if(tx != null) tx.rollback();
			er.printStackTrace();
		}
	}
	public static void deleteEmployee(Session session, int id) {
		Transaction tx = session.getTransaction();
		
		Employee e = (Employee) session.get(Employee.class, id);
		session.delete(e);
		tx.commit();
	}
	
	public static List<Employee> getEmployees(Session session){
		return session.createQuery("From Employee").list();
	}
	
	public static int addSalary(Session session, Salary s) {
		return (int)session.save(s);
	}
	public static List<Salary> getSalaries(Session session){
		return session.createQuery("From Salary").list();
	}
	public static void updateSalary(Session session, Salary sal) {
		Transaction tx = session.beginTransaction();
		try {
			session.update(sal);
			session.flush();
			tx.commit();
		} catch(Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}
	}
	public static void calcSal(Employee e, Date d, String status) {
		if(e.getIsActive().equalsIgnoreCase("active")) {
			e.getSfm().add(e.getSfm(d, status));
		}
	}
	public static void calcSal(Employee e, Date d, String status, int days, int daysThisMonth) {
		if(e.getIsActive().equalsIgnoreCase("active")) {
			e.getSfm().add(e.getSfm(d, status, days, daysThisMonth));
		}
	}
	public static List<SalaryForMonth> getSalsFM(Session session, Date d1, Date d2){
		return session.createQuery("FROM SalaryForMonth WHERE date BETWEEN '" + d1.toString() + "' AND '" + d2.toString() + "'").list();
	}
}

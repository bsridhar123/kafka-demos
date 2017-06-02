package com.demo.model;

import java.util.Date;
public class Employee{
        private int empId;
        private String empName;
        private Date empStartDate;

        public Employee(int id, String name, Date dt){
                this.empId = id;
                this.empName = name;
                this.empStartDate = dt;
        }

		public int getEmpId() {
			return empId;
		}

		public void setEmpId(int empId) {
			this.empId = empId;
		}

		public String getEmpName() {
			return empName;
		}

		public void setEmpName(String empName) {
			this.empName = empName;
		}

		public Date getEmpStartDate() {
			return empStartDate;
		}

		public void setEmpStartDate(Date empStartDate) {
			this.empStartDate = empStartDate;
		}

		

	

      
}
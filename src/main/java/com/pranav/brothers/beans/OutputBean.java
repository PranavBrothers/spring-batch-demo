package com.pranav.brothers.beans;

import java.util.List;

import lombok.Data;

@Data
public class OutputBean {

	private String companyName;
	private Person person;

	@Data
	public static class Person {
		private String firstName;
		private String lastName;
		private List<Contact> contacts;
		private List<Address> addresses;

		@Data
		public static class Contact {
			private String phone;
			private String phone1;
			private String email;
		}

		@Data
		public static class Address {
			private String address;
			private String city;
			private String country;
			private String state;
			private String zip;
		}
	}

}

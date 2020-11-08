package com.Capgemini.AddressBook;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;

import org.junit.Test;

import exception.AddressBookException;

public class AddressBookTest {

	@Test
	public void givenAddressBookInDB_whenRetrieved_ShouldMatchEmployeeCount() {
		AddressBook addressBook = new AddressBook();
		long entries = addressBook.readAddressBookData().size();
		System.out.println("Number of entries:- " + entries);
		Assert.assertEquals(1, entries);
	}

	@Test
	public void givenContactName_WhenUpdated_ShouldSyncWithDB() throws AddressBookException {
		AddressBook addressBook = new AddressBook();
		addressBook.readAddressBookData();
		addressBook.updateContact("Amit", "9149947504");
		boolean ans = addressBook.checkAddressBookDataInSyncWithDB("Amit");
		Assert.assertTrue(ans);
	}
	
	@Test
	public void givenAddressBookInDB_whenRetrievedForDateRange_ShouldMatchEmployeeCount() {
		AddressBook addressBook = new AddressBook();
		long entries = addressBook.readAddressBookData().size();
		LocalDate startDate = LocalDate.of(2018, 01, 01);
		LocalDate endDate = LocalDate.now();
		List<AddressBookContacts> list = addressBook.getInDateRange(startDate, endDate);
		System.out.println("Number of entries:- " + list.size());
		Assert.assertEquals(4, list.size());
	}
	
	@Test
	public void givenCityAndState_whenRetrieved_ShouldMatchEmployeeCount() {
		AddressBook addressBook = new AddressBook();
		int cityCount = addressBook.getContactsByCity("c");
		int stateCount = addressBook.getContactsByState("s");
		Assert.assertEquals(5, cityCount);
		Assert.assertEquals(5, stateCount);
	}
	
	@Test
	public void givenData_whenAdded_ShouldMatchEmployeeCount() {
		AddressBook addressBook = new AddressBook();
		addressBook.readAddressBookData();
		addressBook.addContactToAddressBook("Amit", "Kumar", "Asansol", "Asansol", "West Bengal", 713326, "9149947504", "amit@gmail.com", "book1", "Business"); 
		boolean result = addressBook.checkAddressBookDataInSyncWithDB("Amit");
		Assert.assertTrue(result);
	}

	
	@Test
	public void givenManyEmployees_WhenAddedToDatabaseWithThreads_ShouldSyncWithDB() {
		AddressBook addressBook = new AddressBook();
		AddressBookContacts[] contacts = {
				new AddressBookContacts("Amit", "Kumar", "Asansol", "Asansol", "West Bengal", 713326, "9149947504", "amit@gmail.com",
						LocalDate.parse("2020-01-05")),
				new AddressBookContacts("Neeraj", "Kumar", "Bawal", "Rewari", "Haryana", 123501, "9413300163", "neeraj@gmail.com",
						LocalDate.parse("2020-01-06")) };
		addressBook.addMultipleAddressContacts(Arrays.asList(contacts));
		long entries = addressBook.readAddressBookData().size();
		Assert.assertEquals(9, entries);
	}
}
}


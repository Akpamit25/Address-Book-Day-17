package com.Capgemini.AddressBook;

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

}
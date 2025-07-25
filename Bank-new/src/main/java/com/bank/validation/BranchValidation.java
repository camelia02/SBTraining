package com.bank.validation;


import com.bank.exceptions.DemoAppException;

public class BranchValidation {
	
	private BranchValidation() {
        throw new UnsupportedOperationException("BankValidation class");
    }
	

	/**
	 * Validates a general item name string.
	 * 
	 * @param itemName The string to validate.
	 * @throws IllegalArgumentException if the item name is null or blank.
	 */
	public static void validateItemName(String itemName) {
		if (itemName == null || itemName.isBlank()) {
			throw new DemoAppException("Branch name cannot be empty or blank.");
		}
	}
	
	
	/**
	 * Validates if a string ID can be converted to a Long.
	 * 
	 * @param id The string ID to validate.
	 * @return The Long representation of the ID.
	 * @throws IllegalArgumentException if the ID string is not a valid number.
	 */
	public static Long parseAndValidateLongId(String id) {
		try {
			return Long.valueOf(id);
		} catch (NumberFormatException e) {
			throw new DemoAppException("Invalid ID format. ID must be a valid number.");
		}
	}
}

package com.bank.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.entity.BranchEntity;
import com.bank.mapper.BranchMapper;
import com.bank.model.BranchDTO;
import com.bank.service.IBranchService;
import com.bank.validation.BranchValidation;
import com.bank.utility_classes.ResponseEntityUtil;

import lombok.AllArgsConstructor;

//Practical 8 - End to End Spring Boot 
//Create a branch entity with the data below
//a. BranchEntity.java

//branchID - Index ID auto generated
//branchName - length 100 - Not Nullable
//branchPostCode - length 30 - Not Nullable
//creationDate - Auto created when insert record - LocalDateTime 

//b. BranchController with get by ID, get all, add, and delete by ID only

//c. BranchDTO, BranchMapper and BranchMapperTest.java 
//Ensure table and column created on DB

//d. BranchService and BranchServiceImpl

//e. BranchRepo

//Additional Requirement
//f1. Exception Handling - When adding record, if the branchName is contain empty space, throw a DemoAppException with meaningful error message. i.e. Branch Name cannot be empty
//Enable package scanning "com.demo.exceptions"
//Ensure swagger output contains the DemoAppException type and error exist in the app.log file

//f2. BranchRepo - Basic Search Function
//Add a DOGET into controller above able to search by branchName
//Add on the method to the controller, service and repo
//Note: Refer to CustomerController.java getCustomersByDescriptionAndCreationDateBetween() as a sample

//f3. BranchRepo - Search Function by date in between
//Add a DOGET into controller above able to between date from and date to
//Add on the method to the controller, service and repo


//g1 - UnitTesting - Create a BranchSearchTest.java for g2 and g3 above.
@AllArgsConstructor
@RestController
@RequestMapping("/api/branches/v1")
public class BranchController {
	//b. BranchController with get by ID, get all, add, and delete by ID only
	
	private final IBranchService branchService;
	
	private final BranchMapper branchMapper;
	
	public static final String BRANCH_WITH_ID = "Branch with id";
	
	@PostMapping
    public ResponseEntity<BranchDTO> create(@RequestBody BranchDTO branchDto) {
		BranchValidation.validateItemName(branchDto.getBranchName());
        var savedEntity = branchService.createBranch(branchMapper.toEntity(branchDto));
        return ResponseEntity.ok(branchMapper.toDto(savedEntity));
    }
	 
	@GetMapping("/{id}")
    public ResponseEntity<BranchDTO> getById(@PathVariable String id) {
		BranchValidation.parseAndValidateLongId(id);
    	
		return ResponseEntity.ok(branchMapper.toDto(branchService.getBranchById(Long.valueOf(id))));
	 }
	 
	@GetMapping
    public ResponseEntity<List<BranchDTO>> getAll() {
        var entities = branchService.getAllBranches();
        return ResponseEntity.ok(branchMapper.toDtoList(entities));
    }
	
	 @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        branchService.deleteBranch(id);
        return ResponseEntity.noContent().build();
    }
	 
	 
	//Additional Requirement
	//f1. Exception Handling - When adding record, if the branchName is contain empty space, throw a DemoAppException with meaningful error message. i.e. Branch Name cannot be empty
	//Enable package scanning "com.demo.exceptions"
	//Ensure swagger output contains the DemoAppException type and error exist in the app.log file

	//f2. BranchRepo - Basic Search Function
	//Add a DOGET into controller above able to search by branchName
	//Add on the method to the controller, service and repo
	//Note: Refer to CustomerController.java getCustomersByDescriptionAndCreationDateBetween() as a sample
	 @GetMapping("/search")
	 public ResponseEntity<List<BranchDTO>> getByBranchName(@RequestParam String name){
		 var branch = branchService.getBranchByName(name);
		 return ResponseEntity.ok(branchMapper.toDtoList(branch));
	 }
	//f3. BranchRepo - Search Function by date in between
	//Add a DOGET into controller above able to between date from and date to
	//Add on the method to the controller, service and repo
	 @GetMapping("/search-by-date")
	 public ResponseEntity<List<BranchDTO>> getBetweenDate(
			 @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate start,
		     @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate end) {
		 var branch = branchService.getBranchBetweenDates(start.atStartOfDay(), end.atTime(LocalTime.MAX));
		 return ResponseEntity.ok(branchMapper.toDtoList(branch));
	 }
	 
	
}
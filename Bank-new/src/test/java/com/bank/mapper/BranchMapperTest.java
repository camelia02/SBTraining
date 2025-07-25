package com.bank.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bank.entity.BranchEntity;
import com.bank.model.BranchDTO;

@SpringBootTest
class BranchMapperTest {
	
	@Autowired
	private BranchMapper branchMapper;
	
	@Test
	void testEntityToDtoAndBack() {
		BranchEntity branch = new BranchEntity();
		branch.setBranchID(1L);
		branch.setBranchName("Wisma Standard Chartered");
		branch.setBranchPostCode("85281");
		
		BranchDTO dto = branchMapper.toDto(branch);
		assertNotNull(dto);
		assertNotNull(dto.getBranchID());
	}
}

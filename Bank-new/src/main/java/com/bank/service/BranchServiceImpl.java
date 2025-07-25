package com.bank.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bank.entity.BranchEntity;
import com.bank.exceptions.DemoAppException;
import com.bank.repo.IBranchRepo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BranchServiceImpl implements IBranchService {
	
	private final IBranchRepo branchRepo;
	
	@Override
	public BranchEntity createBranch(BranchEntity branch) {
		return branchRepo.save(branch);
	}

	@Override
	public BranchEntity getBranchById(Long id) {
		return branchRepo.findById(id).orElseThrow(() -> new DemoAppException("Branch with id " + id + " not found."));
	}

	@Override
	public List<BranchEntity> getAllBranches() {
		return branchRepo.findAll();
	}
	
	@Override
	public List<BranchEntity> getBranchByName(String name) {
	    List<BranchEntity> branches = branchRepo.findByBranchNameContainingIgnoreCase(name);
	    if (branches.isEmpty()) {
	        throw new DemoAppException("No branches found with name containing: " + name);
	    }
	    return branches;
	}

	
	@Override
	public List<BranchEntity> getBranchBetweenDates(LocalDateTime start, LocalDateTime end) {
	    List<BranchEntity> branches = branchRepo.findByCreationDateBetween(start, end);
	    if (branches.isEmpty()) {
	        throw new DemoAppException("No branches found created between the dates: " + start + "and" + end);
	    }
	    return branches;
	}


	@Override
	public void deleteBranch(Long id) {
		branchRepo.deleteById(id);
		
	}
}

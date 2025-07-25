package com.bank.service;

import java.time.LocalDateTime;
import java.util.List;

import com.bank.entity.BranchEntity;

public interface IBranchService {
	BranchEntity createBranch(BranchEntity branch);
    BranchEntity getBranchById(Long id);
    List<BranchEntity> getBranchByName(String name);
    List<BranchEntity> getAllBranches();
    void deleteBranch(Long id);
	List<BranchEntity> getBranchBetweenDates(LocalDateTime start, LocalDateTime end);
}

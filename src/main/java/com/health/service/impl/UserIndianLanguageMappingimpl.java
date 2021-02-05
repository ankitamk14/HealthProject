package com.health.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.repository.UserIndianLanguageMappingRepository;
import com.health.service.UserIndianLanguageMappingService;

@Service
public class UserIndianLanguageMappingimpl implements UserIndianLanguageMappingService{

	@Autowired
	private UserIndianLanguageMappingRepository repo;

	@Override
	public int getNewId() {
		// TODO Auto-generated method stub
		try {
			return repo.getNewId()+1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 1;
		}
	}
	
	
}

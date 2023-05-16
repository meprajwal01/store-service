/**
 * 
 */
package com.tiger.storeservice.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.tiger.storeservice.model.Store;

/**
 * @author psharma408
 *
 */
public interface StoreService {

	List<Store> uploadStoreFile(MultipartFile file);

	Store getStoreById(int id);

	void updateStore(int id, Store store);
	
}

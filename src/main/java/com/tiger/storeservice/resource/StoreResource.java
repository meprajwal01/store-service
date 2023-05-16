package com.tiger.storeservice.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tiger.storeservice.exc.StoreNotFoundException;
import com.tiger.storeservice.model.Store;
import com.tiger.storeservice.service.StoreService;

@RestController
public class StoreResource {

	@Autowired
	private StoreService storeService;

	@PostMapping("/upload")
	public ResponseEntity<List<Store>> uploadStoreFile(@RequestParam("file") MultipartFile file) {

		List<Store> duplicateStore = null;

		if (file.isEmpty())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(duplicateStore);

		duplicateStore = storeService.uploadStoreFile(file);

		return ResponseEntity.ok(duplicateStore);
	}

	@GetMapping("/getStoreById/{id}")
	public ResponseEntity<Store> getStoreById(@PathVariable Integer id) {

		Store store = storeService.getStoreById(id);
		return ResponseEntity.ok(store);
	}

	@PutMapping("/updateStoreById/{id}")
	public ResponseEntity<String> updateStoreById(@PathVariable Integer id, @RequestBody Store store) {

		try {
			storeService.updateStore(id, store);
			return ResponseEntity.ok("Store Updated Successfully");
		} catch (StoreNotFoundException snf) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error occured while updating the store");
		}
	}
}

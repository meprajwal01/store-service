package com.tiger.storeservice.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.tiger.storeservice.exc.StoreNotFoundException;
import com.tiger.storeservice.model.Store;
import com.tiger.storeservice.repo.StoreRepo;

@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	private StoreRepo storeRepo;

	@Override
	public List<Store> uploadStoreFile(MultipartFile file) {

	    List<Store> stores = new ArrayList<Store>();

	    try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
	         CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build()) {
	         
	        String[] nextLine;
	        while ((nextLine = csvReader.readNext()) != null) {
	            // Process each line of the CSV file
	            String record = String.join(",", nextLine);
	            stores.add(mapCsvToEntity(record));
	        }
	    } catch (IOException | CsvValidationException e) {
	        e.printStackTrace();
	    }

	    return saveStore(stores);
	}

	private Store mapCsvToEntity(String csvString) {

		Store store = new Store();

		try {

			String[] values = new CSVParser().parseLine(csvString);

			store.setId(Integer.valueOf(values[0]));
			store.setSku(values[1]);
			store.setProductName(values[2]);
			store.setPrice(Double.valueOf(values[3]));
			store.setDate(LocalDate.now());

		} catch (IOException e) {
			e.printStackTrace();
		}
		return store;
	}

	private List<Store> saveStore(List<Store> stores) {

		List<Store> duplicateStore = new ArrayList<Store>();

		if (!stores.isEmpty()) {
			for (Store store : stores) {
				if (storeRepo.existsById(store.getId())) {
					duplicateStore.add(store);
				} else {
					storeRepo.save(store);
				}
			}
		}

		return duplicateStore;
	}

	@Override
	public Store getStoreById(int id) {

		Optional<Store> oUser = storeRepo.findById(id);

		return oUser.orElseThrow(() -> new StoreNotFoundException("Store Not Found"));

	}

	@Override
	public void updateStore(int id, Store store) throws StoreNotFoundException {

		Store eStore = storeRepo.findById(id)
				.orElseThrow(() -> new StoreNotFoundException("Stote with ID " + id + " not found"));

		eStore.setSku(store.getSku());
		eStore.setProductName(store.getProductName());
		eStore.setPrice(store.getPrice());
		eStore.setDate(store.getDate());

		storeRepo.save(eStore);
	}

}

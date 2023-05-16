/**
 * 
 */
package com.tiger.storeservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiger.storeservice.model.Store;

/**
 * @author psharma408
 *
 */
@Repository
public interface StoreRepo extends JpaRepository<Store, Integer>{

}

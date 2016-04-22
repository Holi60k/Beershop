package hu.hnk.beershop.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import hu.hnk.beershop.exception.EmailNotFound;
import hu.hnk.beershop.exception.NegativeQuantityNumber;
import hu.hnk.beershop.exception.StorageItemQuantityExceeded;
import hu.hnk.beershop.exception.UsernameNotFound;
import hu.hnk.beershop.model.Beer;
import hu.hnk.beershop.model.StorageItem;
import hu.hnk.beershop.model.User;
import hu.hnk.interfaces.StorageDao;
import hu.hnk.interfaces.UserDao;
import hu.hnk.service.StorageServiceImpl;
import hu.hnk.service.UserServiceImpl;

public class StorageServiceTest {

	private StorageServiceImpl storageService;

	private StorageDao storageDao;

	@Before
	public void bootContainer() throws Exception {
		storageService = Mockito.spy(new StorageServiceImpl());
		storageDao = Mockito.mock(StorageDao.class);
		storageService.setStorageDao(storageDao);
	}

	// List<StorageItem> storage, Beer beer, Integer quantity
	@Test(expected = StorageItemQuantityExceeded.class)
	public void testTheckStorageItemQuantityLimitShouldThrowStorageItemQuantityExceeded()
			throws StorageItemQuantityExceeded, NegativeQuantityNumber {
		Beer beer = new Beer();
		beer.setName("Teszt s�r");
		StorageItem item = new StorageItem();
		item.setBeer(beer);
		item.setQuantity(1);
		List<StorageItem> items = new ArrayList<>();
		items.add(item);
		Mockito.doNothing().when(storageDao).saveAllChanges(items);
		storageService.checkStorageItemQuantityLimit(items, beer, 2);
	}

	@Test(expected = NegativeQuantityNumber.class)
	public void testTheckStorageItemQuantityLimitShouldThrowNegativeQuantityNumber()
			throws StorageItemQuantityExceeded, NegativeQuantityNumber {
		Beer beer = new Beer();
		beer.setName("Teszt s�r");
		StorageItem item = new StorageItem();
		item.setBeer(beer);
		item.setQuantity(1);
		List<StorageItem> items = new ArrayList<>();
		items.add(item);
		Mockito.doNothing().when(storageDao).saveAllChanges(items);
		storageService.checkStorageItemQuantityLimit(items, beer, -1);
	}

	@Test(expected = NegativeQuantityNumber.class)
	public void testSaveAllChangesShouldThrowNegativeQuantityNumber() throws NegativeQuantityNumber {
		Beer beer = new Beer();
		beer.setName("Teszt s�r");
		StorageItem item = new StorageItem();
		item.setBeer(beer);
		item.setQuantity(-1);
		List<StorageItem> items = new ArrayList<>();
		items.add(item);
		Mockito.doNothing().when(storageDao).saveAllChanges(items);
		storageService.saveAllChanges(items);
	}

	@Test
	public void testSaveAllChangesShouldThrowNothing() throws NegativeQuantityNumber {
		Beer beer = new Beer();
		beer.setName("Teszt s�r");
		beer.setDiscountAmount(0);
		StorageItem item = new StorageItem();
		item.setBeer(beer);
		item.setQuantity(1);
		List<StorageItem> items = new ArrayList<>();
		items.add(item);
		//Mockito.doNothing().when(storageDao).saveAllChanges(items);
		storageService.saveAllChanges(items);
	}

	@After
	public void destroy() {
		// container.close();
	}

}

package com.example.service;


import com.example.demo.model.Data;
import com.example.demo.model.Item;
import com.example.demo.service.ItemService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class ItemServiceTest {

	//TODO: SpringBoot:Practical 4 - Unit Testing continue
	//Complete the TODO below
	
    private ItemService itemService;

    @BeforeEach
    void setUp() {
    	Data.getDataStore().clear();
        Data.getIdCounter().set(0);
        itemService = new ItemService();
    }

    @Test
    void testCreateItem() {
        Item item = itemService.createItem("Test Value");
        assertNotNull(item);
        assertEquals("Test Value", item.value());
        assertTrue(item.id() > 0);
    }

    @Test
    void testCreateItemWithProvidedId_Success() {
        Optional<Item> optionalItem = itemService.createItemWithProvidedId(100L, "Manual ID Item");
        assertTrue(optionalItem.isPresent());
     // TODO: complete the optionalItem.get().id() is equal to 100L above
        assertTrue(optionalItem.get().id() >= 100L);
    }

    @Test
    void testGetItemById() {
        Item created = itemService.createItem("Lookup");
        Optional<Item> fetched = itemService.getItemById(created.id());
     // TODO: complete the assert result above
        assertTrue(fetched.isPresent());
        assertEquals(created.id(), fetched.get().id());
        assertEquals("Lookup", fetched.get().value());
    }

    @Test
    void testGetAllItems() {
        itemService.createItem("One");
        itemService.createItem("Two");
        List<Item> items = itemService.getAllItems();
        assertEquals(2, items.size());
    }

    @Test
    void testUpdateItem_Success() {
        Item created = itemService.createItem("Before");
        Optional<Item> updated = itemService.updateItem(created.id(), "After");
        assertTrue(updated.isPresent());
        assertEquals("After", updated.get().value());
    }

    @Test
    void testDeleteItem_Success() {
        Item created = itemService.createItem("Delete Me");
        boolean result = itemService.deleteItem(created.id());
        
        // TODO: complete the assert result above
        // assertTrue(itemService.getItemById(created.id()).isEmpty());
        assertTrue(result);
    }

    @Test
    void testDeleteItem_NotFound() {
        boolean result = itemService.deleteItem(12345L);
     // TODO: complete the assert result above
        assertFalse(result);
    }
}

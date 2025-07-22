package com.example.demo.controller;

import com.example.demo.model.Item;
import com.example.demo.service.ItemService;
import com.example.demo.service.ItemServiceAnalysis;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/demo/v2")
public class ItemServiceController {
	
	private final ItemService itemService;
    private final ItemServiceAnalysis itemServiceAnalysis;

    public ItemServiceController(ItemService itemService, ItemServiceAnalysis itemServiceAnalysis) {
        this.itemService = itemService;
        this.itemServiceAnalysis = itemServiceAnalysis;
    }

    // --- CREATE (HTTP POST) ---
    @PostMapping
    public ResponseEntity<String> createItem(@RequestBody String newItemName) {
        if (newItemName == null || newItemName.isBlank()) {
            return new ResponseEntity<>("Item name cannot be empty or blank.", HttpStatus.BAD_REQUEST);
        }

        Item item = itemService.createItem(newItemName);
        return new ResponseEntity<>("Item created successfully with ID: " + item.id() + " and data: " + item.value(),
                HttpStatus.CREATED);
    }

    // --- READ ALL (HTTP GET) ---
    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    // --- READ ONLY DEMO ITEMS (Practical 5) ---
    @GetMapping("/getDemoItems")
    public ResponseEntity<List<Item>> getDemoItems() {
        List<Item> demoItems = itemServiceAnalysis.getAllItemsWithDemo();
        return new ResponseEntity<>(demoItems, HttpStatus.OK);
    }

    // --- READ BY ID (HTTP GET) ---
    @GetMapping("/{id}")
    public ResponseEntity<String> getItemById(@PathVariable Long id) {
        return itemService.getItemById(id)
                .map(item -> new ResponseEntity<>("Found item with ID: " + item.id() + " and data: " + item.value(), HttpStatus.OK))
                .orElse(new ResponseEntity<>("Item with ID: " + id + " not found.", HttpStatus.NOT_FOUND));
    }

    // --- UPDATE (HTTP PUT) ---
    @PutMapping("/{id}")
    public ResponseEntity<String> updateItem(@PathVariable Long id, @RequestBody String updatedName) {
        if (updatedName == null || updatedName.isBlank()) {
            return new ResponseEntity<>("Updated item name cannot be empty or blank.", HttpStatus.BAD_REQUEST);
        }

        Optional<Item> updated = itemService.updateItem(id, updatedName);
        if (updated != null) {
            return new ResponseEntity<>("Item with ID: " + id + " updated successfully to: " + updatedName, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Item with ID: " + id + " not found for update.", HttpStatus.NOT_FOUND);
        }
    }

    // --- DELETE (HTTP DELETE) ---
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        boolean deleted = itemService.deleteItem(id);
        if (deleted) {
            return new ResponseEntity<>("Item with ID: " + id + " deleted successfully.", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Item with ID: " + id + " not found for deletion.", HttpStatus.NOT_FOUND);
        }
    }
}

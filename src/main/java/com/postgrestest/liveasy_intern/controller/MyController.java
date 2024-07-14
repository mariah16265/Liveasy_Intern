package com.postgrestest.liveasy_intern.controller;

import java.util.List;
import java.util.UUID;
import com.postgrestest.liveasy_intern.entities.Load;
import com.postgrestest.liveasy_intern.services.LoadService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class MyController {

    @Autowired
    private LoadService loadService;

    // GET all loads or by shipperId
    @GetMapping("/load")
    public ResponseEntity<List<Load>> getLoads(@RequestParam(required = false) UUID shipperId) {
        List<Load> loads;
        if (shipperId != null) {
            loads = loadService.getLoadsByShipperId(shipperId);
        } else {
            loads = loadService.getLoads();
        }
        return new ResponseEntity<>(loads, HttpStatus.OK);
    }

    // GET load by ID
    @GetMapping("/load/{loadId}")
    public ResponseEntity<Load> getLoad(@PathVariable String loadId) {
        Load load = this.loadService.getLoad(UUID.fromString(loadId));
        if (load != null) {
            return new ResponseEntity<>(load, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // POST a new load
    @PostMapping("/load")
    public ResponseEntity<String> addLoad(@RequestBody Load load) {
        loadService.addLoad(load);
        return new ResponseEntity<>("Load details added successfully", HttpStatus.CREATED);
    }

    // PUT (update) an existing load
    @PutMapping("/load/{loadId}")
    public ResponseEntity<Load> updateLoad(@PathVariable String loadId, @RequestBody Load load) {
        load.setLoadId(UUID.fromString(loadId)); // Ensure the ID is set to the loadId from the URL
        Load updatedLoad = this.loadService.updateLoad(load);
        if (updatedLoad != null) {
            return new ResponseEntity<>(updatedLoad, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE a load
    @DeleteMapping("/load/{loadId}")
    public ResponseEntity<String> deleteLoad(@PathVariable String loadId) {
        try {
            Load load = this.loadService.getLoad(UUID.fromString(loadId));
            if (load != null) {
                this.loadService.deleteLoad(UUID.fromString(loadId));
                return new ResponseEntity<>("Load deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Load not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while deleting the load", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

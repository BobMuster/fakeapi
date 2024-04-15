package com.fakeapi.fakeapi.controller;

import com.fakeapi.fakeapi.service.UnitService;
import com.fakeapi.fakeapi.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
@RequestMapping(value = "/api/json")
public class OcJsonTestController {

    private final String responseJson = "{\"result\": \"someId\"}";

    @Autowired
    private UnitService unitService;

    @GetMapping(value = "/user", produces = "application/json")
    public ResponseEntity<?> getUser(@RequestParam(required = false) String name, @RequestParam("id") String id) throws Exception {
        String user = unitService.getUserByNameAndId(id, name);
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/user/all", produces = "application/json")
    public ResponseEntity<?> getAllUsers() throws Exception {
        String users = unitService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping(value = "/unit/all", produces = "application/json")
    public ResponseEntity<?> getAllUnits() throws Exception {
        String units = unitService.getAllUnit();
        return ResponseEntity.ok(units);
    }

    @GetMapping(value = "/unit/{id}", produces = "application/json")
    public ResponseEntity<?> getUnitById(@PathVariable String unitId) throws Exception {
        String unit = unitService.getUnitById(unitId);
        return ResponseEntity.ok(unit);
    }

    @PostMapping("/unit")
    public ResponseEntity<?> addUnit(@RequestBody String unit) throws Exception {
        System.out.println("Unit has been added: " + unit);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/complex/unit", produces = "application/json")
    public ResponseEntity<String> getComplexUnit(@RequestBody MultiValueMap<String, String> valueMap) throws Exception {
        String id = valueMap.getFirst("id");
        String networkName = valueMap.getFirst("network_name");
        String complexUnit = unitService.getComplexUnitByNetworkNameAndId(id, networkName);
        return ResponseEntity.ok(complexUnit);
    }

    @PostMapping(value = "/complex/unit", produces = "application/json")
    public ResponseEntity<?> addComplexUnit(@RequestBody String unit) throws Exception {
        System.out.println("Complex Unit has been added: " + unit);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/complex/unit/all", produces = "application/json")
    public ResponseEntity<?> getAllComplexUnit() throws Exception {
        String cUnits = unitService.getAllUnitComplex();
        return ResponseEntity.ok(cUnits);
    }


}

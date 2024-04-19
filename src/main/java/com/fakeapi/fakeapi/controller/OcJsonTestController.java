package com.fakeapi.fakeapi.controller;

import com.fakeapi.fakeapi.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/json")
public class OcJsonTestController {

    private final String responseJson = "{\"result\": \"someId\"}";

    @Autowired
    private UnitService unitService;

    @GetMapping(value = "/user", produces = "application/json")
    public ResponseEntity<?> getUser(@RequestParam("id") String id) throws Exception {
        String user = unitService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/user/all", produces = "application/json")
    public ResponseEntity<?> getAllUsers() throws Exception {
        String users = unitService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping(value = "/user")
    public ResponseEntity<?> addUsers(@RequestBody MultiValueMap<String, String> valueMap) throws Exception {
        String id = valueMap.getFirst("id");
        String name = valueMap.getFirst("name");
        String role = valueMap.getFirst("role");
        String department = valueMap.getFirst("department");
        String units = valueMap.getFirst("units");
        String result = String.format("{\n\t\"id\": \"%s\",\n" +
                                        "\t\"name\":\"%s\",\n" +
                                        "\t\"role\":\"%s\",\n" +
                                        "\t\"department\":\"%s\",\n" +
                                        "\t\"units\":\"%s\"\n}", id, name, role, department, units);
        System.out.println(result);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/unit/all", produces = "application/json")
    public ResponseEntity<?> getAllUnits() throws Exception {
        String units = unitService.getAllUnit();
        return ResponseEntity.ok(units);
    }

    @GetMapping(value = "/unit/{unitId}", produces = "application/json")
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
        String unitId = valueMap.getFirst("unitID");
        String complexUnit = unitService.getComplexUnitById(unitId);
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

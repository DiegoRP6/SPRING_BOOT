package com.xyz.openapi.server.Controller;

import com.xyz.openapi.server.model.Department;
import com.xyz.openapi.server.model.Employee;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
 
@RestController
@RequestMapping("/api")
public class OrganizationController {
    private List<Employee> employees = new ArrayList<>();
 
    @GetMapping(value = "/employees")
    public EmployeeList getAllEmployees(@RequestParam(required = false) String deptId) {
        List<Employee> employees = this.employees.stream()
                .filter(emp -> deptId == null ||
                        (deptId != null && emp.getDepartment() != null && emp.getDepartment().getId().equals(deptId)))
                .collect(Collectors.toList());
        return EmployeeList.of(employees);
    }
 
    @GetMapping(value = "/employees/{id}")
    public Employee getEmployee(@PathVariable String id) {
        Optional<Employee> optional = employees.stream()
                .filter(emp -> emp.getId().equals(id))
                .findAny();
        if(optional.isEmpty()) {
            throw new IllegalArgumentException("Employee does not exist for id: "+id);
        }
        return optional.get();
    }
 
    @PostMapping(value = "/employees")
    public String createEmployee(@RequestBody Employee emp){
        emp.setId(UUID.randomUUID().toString());
        employees.add(emp);
        return emp.getId();
    }
 
    @PutMapping(value = "/employees")
    public String updateEmployee(Employee updatedEmp){
        employees.stream()
                .filter(e -> updatedEmp.getId().equals(e.getId()))
                .findFirst()
                .map(emp -> {
                    BeanUtils.copyProperties(updatedEmp, emp);
                    return emp;
                })
                .orElseThrow();
        return updatedEmp.getId();
    }
 
    // Few other APIs for Department resource follows here
}
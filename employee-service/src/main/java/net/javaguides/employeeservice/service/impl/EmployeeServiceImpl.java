package net.javaguides.employeeservice.service.impl;


import lombok.AllArgsConstructor;
import net.javaguides.employeeservice.repository.EmployeeRepository;
import net.javaguides.employeeservice.dto.EmployeeDto;
import net.javaguides.employeeservice.entity.Employee;
import net.javaguides.employeeservice.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        //Dto to Entity
        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode(),
                employeeDto.getOrganizationCode()

        );
        Employee savedDEmployee = employeeRepository.save(employee);
        EmployeeDto savedEmployeeDto = new EmployeeDto(
                savedDEmployee.getId(),
                savedDEmployee.getFirstName(),
                savedDEmployee.getLastName(),
                savedDEmployee.getEmail(),
                savedDEmployee.getDepartmentCode(),
                savedDEmployee.getOrganizationCode()
        );

        return savedEmployeeDto;
    }



}

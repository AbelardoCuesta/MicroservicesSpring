package net.javaguides.employeeservice.service.impl;


import lombok.AllArgsConstructor;
import net.javaguides.employeeservice.exception.ResourceNotFoundException;
import net.javaguides.employeeservice.mapper.AutoEmployeeMapper;
import net.javaguides.employeeservice.repository.EmployeeRepository;
import net.javaguides.employeeservice.dto.EmployeeDto;
import net.javaguides.employeeservice.entity.Employee;
import net.javaguides.employeeservice.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private ModelMapper modelMapper;
    private EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        //Dto to Entity
        Employee employee = AutoEmployeeMapper.MAPPER.mapToEmployee(employeeDto);
        Employee savedDEmployee = employeeRepository.save(employee);

/*        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode(),
                employeeDto.getOrganizationCode()

        );*/

        //Entity to Dto
        EmployeeDto savedEmployeeDto = AutoEmployeeMapper.MAPPER.mapToEmployeeDto(savedDEmployee);

/*        EmployeeDto savedEmployeeDto = new EmployeeDto(
                savedDEmployee.getId(),
                savedDEmployee.getFirstName(),
                savedDEmployee.getLastName(),
                savedDEmployee.getEmail(),
                savedDEmployee.getDepartmentCode(),
                savedDEmployee.getOrganizationCode()
        );*/

        return savedEmployeeDto;
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee=  employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", employeeId)
        );

        EmployeeDto employeeDto = modelMapper.map(employee,EmployeeDto.class);

/*        EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode(),
                employee.getOrganizationCode()
        );*/

        return employeeDto;
    }


}

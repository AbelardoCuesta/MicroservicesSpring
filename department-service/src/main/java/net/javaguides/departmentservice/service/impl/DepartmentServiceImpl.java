package net.javaguides.departmentservice.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.departmentservice.dto.DepartmentDto;
import net.javaguides.departmentservice.entity.Department;
import net.javaguides.departmentservice.exception.ResourceNotFoundException;
import net.javaguides.departmentservice.mapper.AutoDepartmentMapper;
import net.javaguides.departmentservice.repository.DepartmentRepository;
import net.javaguides.departmentservice.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;
    private ModelMapper modelMapper;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        //Dto to Entity
        Department department = modelMapper.map(departmentDto, Department.class);
        Department savedDepartment = departmentRepository.save(department);

/*        Department department = new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentCode()

        );*/


        //Entity to Dto
        DepartmentDto savedDepartmentDto= modelMapper.map(savedDepartment,DepartmentDto.class);
/*
        Department savedDepartment = departmentRepository.save(department);
        DepartmentDto savedDepartmentDto = new DepartmentDto(
                savedDepartment.getId(),
                savedDepartment.getDepartmentName(),
                savedDepartment.getDepartmentDescription(),
                savedDepartment.getDepartmentCode()
        );*/

        return savedDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {
       Department department=  departmentRepository.findByDepartmentCode(code);
       if   (department==null){
           throw new ResourceNotFoundException("Department", "Code department", code);
       }

       DepartmentDto departmentDto = AutoDepartmentMapper.MAPPER.mapToDepartmentDto(department);

/*        DepartmentDto departmentDto = new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode()
        );*/

        return departmentDto;
    }


}

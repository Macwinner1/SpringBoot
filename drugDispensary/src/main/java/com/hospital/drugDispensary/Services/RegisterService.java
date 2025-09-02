package com.hospital.drugDispensary.Services;


import com.hospital.drugDispensary.dto.Request.UserRequest;
import com.hospital.drugDispensary.models.Doctor;
import com.hospital.drugDispensary.models.Pharmacist;
import com.hospital.drugDispensary.repository.DoctorsRepository;
import com.hospital.drugDispensary.repository.PharmacistsRepository;
import com.hospital.drugDispensary.utils.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    @Autowired
    private PharmacistsRepository pharmacistsRepository;
    @Autowired
    private DoctorsRepository doctorsRepository;
    @Autowired
    private UserMapper userMapper;


    public void createUser(UserRequest userRequest) {
        switch (userRequest.getRole().toUpperCase()) {
            case "DOCTOR":
                Doctor doctor = userMapper.toDoctor(userRequest);
                doctor.setRole("DOCTOR");
                doctorsRepository.save(doctor);
                break;
            case "PHARMACIST":
                Pharmacist pharmacist = userMapper.toPharmacist(userRequest);
                pharmacist.setRole("PHARMACIST");
                pharmacistsRepository.save(pharmacist);
                break;
            default:
                throw new IllegalArgumentException("Invalid role" + userRequest.getRole());
        }
    }
}

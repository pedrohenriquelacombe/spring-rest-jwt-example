package com.example.jwt.resource;

import com.example.jwt.model.RoleModel;
import com.example.jwt.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/roles")
public class RoleResource {

    private RoleRepository roleRepository;

    @PostMapping
    public RoleModel save(@RequestBody RoleModel role) {
        return this.roleRepository.save(role);
    }

    @GetMapping
    public List<RoleModel> findAll() {
        return this.roleRepository.findAll();
    }

}

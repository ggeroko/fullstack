package com.example.fullStackbackEnd.controller;
import com.example.fullStackbackEnd.exception.BranchNotFoundException;
import com.example.fullStackbackEnd.exception.UserNotFoundException;
import com.example.fullStackbackEnd.model.Branch;
import com.example.fullStackbackEnd.model.BranchDTO;
import com.example.fullStackbackEnd.model.Product;
import com.example.fullStackbackEnd.repository.BranchRepository;
import com.example.fullStackbackEnd.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class BranchController {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/branch")
    Branch newBranch(@RequestBody Branch newBranch){
        return branchRepository.save(newBranch);
    }
    @GetMapping("/branches")
    List<Branch> getAllBranches(){

        return branchRepository.findAll();
    }

    @GetMapping("/branch/{id}")
    Branch getBranchById(@PathVariable  Long id){
        return branchRepository.findById(id)
                .orElseThrow(()->new BranchNotFoundException(id));
    }

    @PutMapping("/branch/{id}")
    Branch updateBranch(@RequestBody BranchDTO newBranch, @PathVariable Long id){
        Optional<Product> product = productRepository.findById(Long.valueOf(newBranch.getProduct_id()));
        return branchRepository.findById(id)
                .map(branch -> {
                    branch.setId(newBranch.getId());
                    branch.setName(newBranch.getName());
                    branch.setLocation(newBranch.getLocation());
                    return branchRepository.save(branch);
                }).orElseThrow(()->new BranchNotFoundException(id));
    }
    @DeleteMapping("/branch/{id}")
    String deleteBranch(@PathVariable Long id){
        if(!branchRepository.existsById(id)){
            throw new BranchNotFoundException((id));
        }
        branchRepository.deleteById(id);
        return "User with id" +id+"has been deleted successfully";
    }
}
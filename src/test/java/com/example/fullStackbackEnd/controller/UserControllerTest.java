package com.example.fullStackbackEnd.controller;

import com.example.fullStackbackEnd.model.Branch;
import com.example.fullStackbackEnd.model.User;
import com.example.fullStackbackEnd.repository.BranchRepository;
import com.example.fullStackbackEnd.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void createNewUser() throws Exception {
        Branch branch = new Branch("athens","athens");
        branchRepository.save(branch);

        String jsonActual = "{ \"name\": \"asd\", \"username\": \"qwe\", \"email\": \"qwe\", \"branch_id\": \"1\" }";
        mockMvc.perform(post("/user").content(jsonActual).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        assertEquals(1,userRepository.findAll().size());
    }
    @Test
    public void fetchAllUsersAndTestEndpoint() throws Exception {

        Branch branch = new Branch(1,"athens","athens");
        branchRepository.save(branch);

        User user1 = new User(1L,"giwrghs",branch,"giwrghs","asda@asd.com");
        User user2 = new User(2L,"kostakis",branch,"kostakis","asde@asd.com");

        userRepository.save(user1);
        userRepository.save(user2);

        MvcResult mvcResult = this.mockMvc.perform(get("/users")).andExpect(status().isOk()).andReturn();
        String body = mvcResult.getResponse().getContentAsString();
        List<User> users = objectMapper.readValue(body, new TypeReference<List<User>>() {
        });

        assertEquals(2,users.size());
        User responseUser1 = users.get(0);
        assertEquals(user1,responseUser1);
    }
    @BeforeEach
    void clearAllRepositories(){
        branchRepository.deleteAll();
        userRepository.deleteAll();
    }

}
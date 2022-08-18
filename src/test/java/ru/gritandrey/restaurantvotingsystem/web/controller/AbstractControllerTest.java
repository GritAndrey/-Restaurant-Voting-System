package ru.gritandrey.restaurantvotingsystem.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@ActiveProfiles("test")
@RequiredArgsConstructor
public abstract class AbstractControllerTest {

    private final MockMvc mockMvc;

    protected ResultActions perform(MockHttpServletRequestBuilder builder) throws Exception {
        return mockMvc.perform(builder);
    }
}


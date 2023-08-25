package com.gustavo.citiesmicroservice;

import com.gustavo.citiesmicroservice.controller.CityController;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * Esta clase se encarga de realizar pruebas unitarias para el servicio.
 *
 * @author Gustavo Cardenas Alba.
 * @version 24/08/2023/A
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = CityController.class)
@WithMockUser
public class CityControllerTest {

	@Autowired
	private MockMvc mockMvc;

    /*
    * Metodo  que revisa si el servicio esta filtrando de manera correcta.
    */
	@Test
	public void checkIfFilteringIsCorrect() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/suggestions?q=aja");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedResponse = "[{\"name\":\"Ajax\",\"latitude\":43.85012,\"longitude\":-79.03288}]";
		JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);
	}

}
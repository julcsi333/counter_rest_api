package com.example.counter

import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.Matchers.hasItem
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*


@RunWith(SpringRunner::class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = [CounterApplication::class]
)
@AutoConfigureMockMvc
class CounterEndpointTests {

    @Autowired
    private val mvc: MockMvc? = null

    private val objectMapper = ObjectMapper()


    @Test
    fun whenGetCount_thenStatusIsOk() {
        mvc?.perform(
            get("/count")
                .contentType(MediaType.APPLICATION_JSON)
        )
            ?.andExpect(status().isOk)
            ?.andExpect(content().json("""{ "value": 0 }"""))
    }

    @Test
    @DirtiesContext
    fun whenAdd_thenStatusIsOk() {
        mvc?.perform(
            post("/count/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Counter(11)))
                .accept(MediaType.APPLICATION_JSON)
        )?.andExpect(status().isOk)
    }

    @Test
    fun whenAddBadRequest_thenStatusIsBadRequest() {
        mvc?.perform(
            post("/count/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""{ "bad" : "request" }""")
                .accept(MediaType.APPLICATION_JSON)
        )?.andExpect(status().isBadRequest)
    }

    @Test
    fun whenAddEmptyRequest_thenStatusIsBadRequest() {
        mvc?.perform(
            post("/count/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )?.andExpect(status().isBadRequest)
    }

	@Test
	@DirtiesContext
	fun whenSubtract_thenStatusIsOk() {
		mvc?.perform(
			post("/count/subtract")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(Counter(11)))
				.accept(MediaType.APPLICATION_JSON)
		)?.andExpect(status().isOk)
	}

	@Test
	fun whenSubtractBadRequest_thenStatusIsBadRequest() {
		mvc?.perform(
			post("/count/subtract")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""{ "bad" : "request" }""")
				.accept(MediaType.APPLICATION_JSON)
		)?.andExpect(status().isBadRequest)
	}

	@Test
	fun whenSubtractEmptyRequest_thenStatusIsBadRequest() {
		mvc?.perform(
			post("/count/subtract")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
		)?.andExpect(status().isBadRequest)
	}
}

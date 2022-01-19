package com.example.counter

import com.fasterxml.jackson.databind.ObjectMapper
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
class CounterApplicationTests {

    @Autowired
    private val mvc: MockMvc? = null

    private val objectMapper = ObjectMapper()

    @Test
    @DirtiesContext
    fun addIncreases() {
        getCounter(0)
        addCounter(5)
        getCounter(5)
    }

    @Test
    @DirtiesContext
    fun subtractDecreases() {
        getCounter(0)
        subtractCounter(7)
        getCounter(-7)
    }

    @Test
    @DirtiesContext
    fun addSubtractCounter() {
        getCounter(0)
        addCounter(20)
        subtractCounter(10)
        getCounter(10)
    }

    private fun getCounter(expectedValue: Long) {
        mvc?.perform(
            get("/count")
                .contentType(MediaType.APPLICATION_JSON)
        )
            ?.andExpect(status().isOk)
            ?.andExpect(content().json("""{ "value": $expectedValue }"""))
    }

    private fun addCounter(delta: Long) {
        mvc?.perform(
            post("/count/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Counter(delta)))
                .accept(MediaType.APPLICATION_JSON)
        )?.andExpect(status().isOk)
    }

    private fun subtractCounter(delta: Long) {
        mvc?.perform(
            post("/count/subtract")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Counter(delta)))
                .accept(MediaType.APPLICATION_JSON)
        )?.andExpect(status().isOk)
    }

}
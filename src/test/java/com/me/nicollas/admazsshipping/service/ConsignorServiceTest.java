package com.me.nicollas.admazsshipping.service;

import com.me.nicollas.admazsshipping.service.impl.ConsignorServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertFalse;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestPropertySource("/application-test.properties")
public class ConsignorServiceTest {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private ConsignorServiceImpl consignorService;

    @Value("${sql.script.insert.consignor}")
    private String sqlInsertConsignor;

    @Value("${sql.script.insert.address}")
    private String sqlInsertAddress;

    @Value("${sql.script.insert.shipment}")
    private String sqlInsertShipment;

    @Value("${sql.script.insert.item}")
    private String sqlInsertItem;


    @BeforeEach
    public void setupDatabase() {
        jdbc.execute(sqlInsertAddress);
        jdbc.execute(sqlInsertConsignor);
        jdbc.execute(sqlInsertShipment);
        jdbc.execute(sqlInsertItem);
    }

    @Test
    public void isConsignorNullCheck() {
        assertFalse(consignorService.findAllConsignor().isEmpty(), "@BeforeTransaction Theres no consignors on adm_azs_shipping_test_db: return false");
    }


    @AfterEach
    public void tearDownDatabase() {

        jdbc.execute("DELETE FROM item;");
        jdbc.execute("DELETE FROM shipment;");
        jdbc.execute("DELETE FROM consignor;");
        jdbc.execute("DELETE FROM address;");
    }
}

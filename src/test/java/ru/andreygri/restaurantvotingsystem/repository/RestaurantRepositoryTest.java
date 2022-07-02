package ru.andreygri.restaurantvotingsystem.repository;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.andreygri.restaurantvotingsystem.model.Restaurant;

import static org.slf4j.LoggerFactory.getLogger;

@ContextConfiguration({
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB_hsql.sql", config = @SqlConfig(encoding = "UTF-8"))
public class RestaurantRepositoryTest extends TestCase {
    private static final Logger log = getLogger(RestaurantRepositoryTest.class);

    @Autowired
    private RestaurantRepository repository;

    @Test
    public void getAll() {
        repository.findAll().forEach(restaurant -> log.info(restaurant.getName()));
    }

    @Test
    public void create() {
        Restaurant restaurant = repository.save(
                new Restaurant(null, "test_name", "test_address"));
        log.info(restaurant.toString());
    }
}
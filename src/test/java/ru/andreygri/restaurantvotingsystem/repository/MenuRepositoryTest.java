package ru.andreygri.restaurantvotingsystem.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.andreygri.restaurantvotingsystem.model.Menu;

import java.time.LocalDate;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@ContextConfiguration({
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB_hsql.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MenuRepositoryTest {
    @Autowired
    private MenuRepository repository;
    private static final Logger log = getLogger(MenuRepositoryTest.class);
    @Test
    public void getMenusOnDateByRestaurantIdTest() {
        final List<Menu> menus = repository.getMenusOnDateByRestaurantId(LocalDate.now().minusDays(1), LocalDate.now(), 1);
        for (Menu menu : menus) {
            log.info(menu.getDishes().toString());
        }


    }
}
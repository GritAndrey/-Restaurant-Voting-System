package ru.gritandrey.restaurantvotingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.gritandrey.restaurantvotingsystem.model.Restaurant;
import ru.gritandrey.restaurantvotingsystem.repository.RestaurantRepository;
import ru.gritandrey.restaurantvotingsystem.to.RestaurantWithMenuTo;
import ru.gritandrey.restaurantvotingsystem.util.builder.ToBuilderUtil;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static ru.gritandrey.restaurantvotingsystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {

    private final RestaurantRepository repository;

    @Autowired
    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant get(int id) {
        return checkNotFoundWithId(repository.findById(id), id);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }

    public List<Restaurant> getAll() {
        return repository.findAll();
    }

    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "Restaurant must not be null");
        checkNotFoundWithId(repository.save(restaurant), restaurant.id());
    }

    public Restaurant create(Restaurant restaurant) {
        if (!restaurant.isNew()) {
            return null;
        }
        Assert.notNull(restaurant, "Restaurant must not be null");
        return repository.save(restaurant);
    }

    public RestaurantWithMenuTo getWithMenu(int id) {
        final Restaurant restaurant = checkNotFoundWithId(repository.getRestaurantByIdWithMenu(id, LocalDate.now()), id);
        return ToBuilderUtil.buildRestaurantWithMenuTo(restaurant);
    }

    public List<RestaurantWithMenuTo> getAllWithMenus() {
        return repository.findAllWithMenus(LocalDate.now()).stream()
                .map(ToBuilderUtil::buildRestaurantWithMenuTo)
                .collect(toList());
    }
}

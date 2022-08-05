package ru.gritandrey.restaurantvotingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.gritandrey.restaurantvotingsystem.model.Restaurant;
import ru.gritandrey.restaurantvotingsystem.repository.RestaurantRepository;
import ru.gritandrey.restaurantvotingsystem.to.RestaurantTo;
import ru.gritandrey.restaurantvotingsystem.to.RestaurantWithMenuTo;
import ru.gritandrey.restaurantvotingsystem.util.mapper.RestaurantMapper;

import java.time.LocalDate;
import java.util.List;

import static ru.gritandrey.restaurantvotingsystem.util.validation.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant get(int id) {
        return checkNotFoundWithId(restaurantRepository.findById(id), id);
    }

    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    public RestaurantWithMenuTo getWithMenu(int id) {
        final Restaurant restaurant = checkNotFoundWithId(restaurantRepository.getRestaurantByIdWithMenu(id, LocalDate.now()), id);
        return RestaurantMapper.getWithMenuTo(restaurant);
    }

    public List<RestaurantWithMenuTo> getAllWithMenu() {
        return RestaurantMapper.getWithMenuTos(restaurantRepository.findAllWithMenus(LocalDate.now()));
    }

    public Restaurant create(RestaurantTo restaurantTo) {
        Assert.notNull(restaurantTo, "Restaurant must not be null");
        return restaurantRepository.save(RestaurantMapper.getRestaurant(restaurantTo));
    }

    public void update(RestaurantTo restaurantTo) {
        Assert.notNull(restaurantTo, "Restaurant must not be null");
        final var restaurant = RestaurantMapper.getRestaurant(restaurantTo);
        checkNotFoundWithId(save(restaurant), restaurant.id());
    }

    public void delete(int id) {
        checkNotFoundWithId(restaurantRepository.delete(id) != 0, id);
    }

    private Restaurant save(Restaurant restaurant) {
        if (!restaurant.isNew() && get(restaurant.id()) == null) {
            return null;
        }
        return restaurantRepository.save(restaurant);
    }
}

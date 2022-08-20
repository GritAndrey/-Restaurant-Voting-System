package ru.gritandrey.restaurantvotingsystem.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.gritandrey.restaurantvotingsystem.model.Restaurant;
import ru.gritandrey.restaurantvotingsystem.repository.DishRepository;
import ru.gritandrey.restaurantvotingsystem.repository.RestaurantRepository;
import ru.gritandrey.restaurantvotingsystem.to.RestaurantTo;
import ru.gritandrey.restaurantvotingsystem.to.RestaurantWithMenuTo;
import ru.gritandrey.restaurantvotingsystem.util.mapper.RestaurantMapper;

import java.time.LocalDate;
import java.util.List;

import static ru.gritandrey.restaurantvotingsystem.util.validation.ValidationUtil.checkNotFoundWithId;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final DishRepository dishRepository;

    public RestaurantTo get(int id) {
        final var restaurant = checkNotFoundWithId(restaurantRepository.findById(id), id);
        return RestaurantMapper.getTo(restaurant);
    }

    public Page<Restaurant> getAll(Integer page, Integer itemsPerPage) {
        var pageRequest = PageRequest.of(page, itemsPerPage, Sort.by("id"));
        return restaurantRepository.findAllBy(pageRequest);
    }

    @Cacheable("restWithMenu")
    public RestaurantWithMenuTo getWithMenu(int id) {
        final var dishes = dishRepository.findAllByRestaurantId(id);
        if (dishes.isEmpty()) {
            log.warn("There is no menu for the restaurant with id: {}", id);
        }
        final Restaurant restaurant = checkNotFoundWithId(restaurantRepository.getRestaurantByIdWithMenu(id, LocalDate.now()), id);
        return RestaurantMapper.getWithMenuTo(restaurant);
    }

    @Cacheable("restWithMenu")
    public List<RestaurantWithMenuTo> getAllWithMenu() {
        return RestaurantMapper.getWithMenuTos(restaurantRepository.findAllWithMenus(LocalDate.now()));
    }


    @CacheEvict(value = "restWithMenu", allEntries = true)
    public Restaurant create(RestaurantTo restaurantTo) {
        Assert.notNull(restaurantTo, "Restaurant must not be null");
        return restaurantRepository.save(RestaurantMapper.getRestaurant(restaurantTo));
    }

    @CacheEvict(value = "restWithMenu", allEntries = true)
    public void update(RestaurantTo restaurantTo) {
        Assert.notNull(restaurantTo, "Restaurant must not be null");
        final var restaurant = RestaurantMapper.getRestaurant(restaurantTo);
        checkNotFoundWithId(save(restaurant), restaurant.id());
    }

    @CacheEvict(value = "restWithMenu", allEntries = true)
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

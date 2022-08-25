package ru.gritandrey.restaurantvotingsystem.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gritandrey.restaurantvotingsystem.exception.IllegalRequestDataException;
import ru.gritandrey.restaurantvotingsystem.model.Restaurant;
import ru.gritandrey.restaurantvotingsystem.repository.RestaurantRepository;
import ru.gritandrey.restaurantvotingsystem.to.RestaurantTo;
import ru.gritandrey.restaurantvotingsystem.util.RestaurantUtil;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantTo get(int id) {
        return RestaurantUtil.getTo(restaurantRepository.getExisted(id));
    }

    public Page<RestaurantTo> getAll(Integer page, Integer itemsPerPage) {
        var pageRequest = PageRequest.of(page, itemsPerPage, Sort.by("id"));
        return restaurantRepository.findAllBy(pageRequest).map(RestaurantUtil::getTo);
    }

    @Cacheable("restWithMenu")
    public RestaurantTo getWithMenu(int id) {
        final Restaurant restaurant = restaurantRepository.getRestaurantByIdWithMenu(id, LocalDate.now())
                .orElseThrow(() -> new IllegalRequestDataException("No menu for restaurant with id: " + id));
        return RestaurantUtil.getTo(restaurant);
    }

    public Page<RestaurantTo> getAllWithMenu(Integer page, Integer itemsPerPage) {
        var pageRequest = PageRequest.of(page, itemsPerPage, Sort.by("id"));
        return restaurantRepository.findAllWithMenus(pageRequest, LocalDate.now()).map(RestaurantUtil::getTo);
    }


    @CacheEvict(value = "restWithMenu", allEntries = true)
    public Restaurant create(RestaurantTo restaurantTo) {
        return restaurantRepository.save(RestaurantUtil.getRestaurant(restaurantTo));
    }

    @CacheEvict(value = "restWithMenu", key = "#restaurantTo.id")
    public void update(RestaurantTo restaurantTo) {
        final var existed = restaurantRepository.getExisted(restaurantTo.getId());
        existed.setName(restaurantTo.getName());
        existed.setAddress(restaurantTo.getAddress());
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "menus", allEntries = true),
            @CacheEvict(value = "restWithMenu", allEntries = true)
    })
    public void delete(int id) {
        restaurantRepository.deleteExisted(id);
    }
}

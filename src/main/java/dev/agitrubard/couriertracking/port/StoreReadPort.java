package dev.agitrubard.couriertracking.port;

import dev.agitrubard.couriertracking.model.Store;

import java.util.List;

public interface StoreReadPort {

    List<Store> findAll();

}

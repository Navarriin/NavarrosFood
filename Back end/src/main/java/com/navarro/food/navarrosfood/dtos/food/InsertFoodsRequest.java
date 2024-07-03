package com.navarro.food.navarrosfood.dtos.food;

import java.util.List;

public record InsertFoodsRequest(String name, List<Long> ids) {
}

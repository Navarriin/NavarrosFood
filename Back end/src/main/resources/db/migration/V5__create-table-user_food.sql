CREATE TABLE user_food (
    user_id UUID NOT NULL,
    food_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, food_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (food_id) REFERENCES foods(id)
);

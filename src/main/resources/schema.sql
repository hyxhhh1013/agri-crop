DROP TABLE IF EXISTS crop;

CREATE TABLE crop (
    id            BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    crop_name     VARCHAR(50)  NOT NULL,
    variety_name  VARCHAR(50)  NOT NULL,
    crop_type     VARCHAR(20)  NOT NULL,
    suit_area     VARCHAR(100) NULL,
    growth_cycle  INT          NOT NULL,
    yield_level   INT          NOT NULL,
    plant_status  INT          NOT NULL,
    create_time   TIMESTAMP    NULL,
    update_time   TIMESTAMP    NULL,
    deleted       INT          NOT NULL DEFAULT 0
);

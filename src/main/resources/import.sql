-- Option 데이터 추가
INSERT INTO Option (name, price, description) VALUES ('HOT_ONLY', 0, '');
INSERT INTO Option (name, price, description) VALUES ('ICE_ONLY', 0, '');
INSERT INTO Option (name, price, description) VALUES ('TEMP_BOTH', 0, 'HOT, ICE 모두 가능');
INSERT INTO Option (name, price, description) VALUES ('샷 추가', 500, '기본 = 2, 추가부터 + 1');
INSERT INTO Option (name, price, description) VALUES ('디카페인', 1000, '0 = N, 1 = Y');
INSERT INTO Option (name, price, description) VALUES ('사이즈업', 1200, '0 = N, 1 = Y');

-- Item 데이터 추가
INSERT INTO Item (name, price, image_path, menu) VALUES ('에스프레소', 2000, 'dummy_espresso.jpg', 'Coffee');
INSERT INTO Item (name, price, image_path, menu) VALUES ('아메리카노', 3000, 'dummy_americano.jpg', 'Coffee');
INSERT INTO Item (name, price, image_path, menu) VALUES ('카페 라떼', 4000, 'dummy_cafe_latte.jpg', 'Coffee');
INSERT INTO Item (name, price, image_path, menu) VALUES ('바닐라 라떼', 4500, 'dummy_vanilla_latte.jpg', 'Coffee');
INSERT INTO Item (name, price, image_path, menu) VALUES ('카푸치노', 4200, 'dummy_cappuccino.jpg', 'Coffee');
INSERT INTO Item (name, price, image_path, menu) VALUES ('그린티 라떼', 3500, 'dummy_green_tea_latte.jpg', 'NonCoffee');
INSERT INTO Item (name, price, image_path, menu) VALUES ('레몬 에이드', 3500, 'dummy_lemonade.jpg', 'NonCoffee');
INSERT INTO Item (name, price, image_path, menu) VALUES ('망고 에이드', 3500, 'dummy_mango_ade.jpg', 'NonCoffee');
INSERT INTO Item (name, price, image_path, menu) VALUES ('자몽 에이드', 3500, 'dummy_grapefruit_ade.jpg', 'NonCoffee');
INSERT INTO Item (name, price, image_path, menu) VALUES ('레드벨벳케이크', 5000, 'dummy_red_velvet_cake.jpg', 'Dessert');
INSERT INTO Item (name, price, image_path, menu) VALUES ('롤 케이크', 5000, 'dummy_roll_cake.jpg', 'Dessert');
INSERT INTO Item (name, price, image_path, menu) VALUES ('티라미수', 5500, 'dummy_tiramisu.jpg', 'Dessert');
INSERT INTO Item (name, price, image_path, menu) VALUES ('치즈 케이크', 5000, 'dummy_cheese_cake.jpg', 'Dessert');
INSERT INTO Item (name, price, image_path, menu) VALUES ('초코쿠키', 2000, 'dummy_choco_cookie.jpg', 'Dessert');
INSERT INTO Item (name, price, image_path, menu) VALUES ('플레인쿠키', 1800, 'dummy_plain_cookie.jpg', 'Dessert');

-- User 데이터 추가
-- INSERT INTO User (username, password, email, role) VALUES ('kbm1234', 'pw1234', 'rovin1273@gmail.com', 'ADMIN');
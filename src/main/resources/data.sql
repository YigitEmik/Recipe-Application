INSERT INTO role (id, name)
VALUES (1, 'STANDART_USER');

INSERT INTO users (id, email, password, name, surname, is_enabled, phone_number)
VALUES (0, 'test.test@test.com', '$2a$10$PIp9Xg1JYPoMv3sskTr8Te9mBIrgX6rmV1VtErGcgvIY1EVvHs26G', 'test',
        'test',
        true, '1234567890');

INSERT INTO users_roles(user_id, role_id)
VALUES (0, 1);


INSERT INTO recipes (image_url, name, prep_time, cook_time, ingredients, method, creation_date)
VALUES ('https://www.allrecipes.com/thmb/c7D7JmZ7bYoIVXh7fJiPkmx1rOs=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/7401695-59662f0f08fc4e06b9d25322ee6b8538.jpg',
        'Dry Onion Soup Mix', 5, 5,
        '¼ cup dried onion flakes</br> 2 tablespoons low-sodium beef bouillon granules</br> ¼ teaspoon onion powder</br> ¼ teaspoon parsley flakes</br> ⅛ teaspoon celery seed</br> ⅛ teaspoon paprika, ⅛ teaspoon ground black pepper',
        'Stir onion flakes, beef bouillon granules, onion powder, parsley flakes, celery seed, paprika, and black pepper together in a bowl.</br>Use as a substitute for a 1-ounce envelope of dry onion soup mix.',
        '2022-01-01'),
       ('https://www.allrecipes.com/thmb/OTP8xQe2DGqplhr-HZzq5-mOtCg=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/8590428-fea4d1163de24cbdb37d5674de5eb213.jpg',
        'Caramel Frosting', 10, 5,
        '2 tablespoons butter</br>½ cup packed brown sugar</br>3 tablespoons milk, or more as needed</br>1 cup confectioners'' sugar</br>½ teaspoon vanilla extract</br>',
        'Melt butter in a saucepan over medium heat. Stir in brown sugar and 3 tablespoons milk. Boil vigorously for 1 minute.</br>Remove from heat and stir in 1/2 cup confectioners'' sugar. Cool slightly, then beat in vanilla and remaining 1/2 cup confectioners'' sugar. Add more milk if mixture is too thick.',
        '2022-10-01'),
       ('https://images.immediate.co.uk/production/volatile/sites/30/2020/11/Air-fried-chips-c3a5d44.jpg?quality=90&webp=true&resize=600,545',
        'CAir-fried chips', 10, 35,
        '2 tablespoons butter</br>½ cup packed brown sugar</br>3 tablespoons milk, or more as needed</br>1 cup confectioners'' sugar</br>½ teaspoon vanilla extract</br>',
        'To make straight, neat chips, peel the potatoes and trim away all the rounded edges so they become rectangular blocks. Cut the blocks into batons – they should be somewhere between fries and thick chips, as if they''re too thin, they might break; too thick, and they won’t cook through (if you like, save the offcuts to make mash or add to soups). Alternatively, cut the unpeeled potatoes into chips without trimming, if you''re not bothered by neatness. Rinse the chips and pat dry with a clean tea towel.<br>Tip the chips into the bottom of an air fryer (the part with the paddle), add the oil, and toss the chips in the oil so they are evenly coated. Program the fryer to cook for 30 mins using the paddle. After this time, check that the chips are tender and cooked through. If they''re not, cook for a further 5 mins. Season well.',
        '2022-10-10');



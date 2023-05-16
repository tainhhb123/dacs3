-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 16, 2023 at 06:26 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cosmetics`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `product_name` varchar(200) NOT NULL,
  `product_image` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `product_name`, `product_image`) VALUES
(1, 'Skin Care', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRJZw5gdU6tdqXEIbmtTfd35WLU5SpVdbsCMg&usqp=CAU'),
(2, 'Make Up', 'https://cdn.shopify.com/s/files/1/2405/1749/products/07_-_BEST_SELLERS_KIT_480x480.jpg?v=1627897801');

-- --------------------------------------------------------

--
-- Table structure for table `order`
--

CREATE TABLE `order` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `phone` int(11) NOT NULL,
  `email` varchar(200) NOT NULL,
  `address` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `order`
--

INSERT INTO `order` (`id`, `name`, `phone`, `email`, `address`) VALUES
(1, 'tai', 389093655, 'trantantai310803@gmail.com', ''),
(2, 'tan', 389093655, 'taitt.21it@vku.udn.vn', '470 Trần Đại Nghĩa, Hòa Hải, Ngũ Hành Sơn, Đà Nẵng'),
(11, 'Tran Van Nam', 366226518, 'nam@gmail.com', 'Huong Trung, Hoa Huong, Quang Nam');

-- --------------------------------------------------------

--
-- Table structure for table `orderdetail`
--

CREATE TABLE `orderdetail` (
  `id` int(11) NOT NULL,
  `ordercode` int(11) NOT NULL,
  `productorder` int(11) NOT NULL,
  `name` varchar(10000) NOT NULL,
  `price` int(11) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `orderdetail`
--

INSERT INTO `orderdetail` (`id`, `ordercode`, `productorder`, `name`, `price`, `quantity`) VALUES
(22, 4, 15, 'Jane iredale PurePressed Base Mineral Foundation 30g (Various Shades)', 47, 1),
(23, 5, 14, 'EltaMD Foaming Facial Cleanser (7 oz.)', 35, 3),
(24, 5, 13, 'Obagi Medical Obagi-C Fx System - Normal to Dry (5 piece)', 387, 2),
(25, 6, 15, 'Jane iredale PurePressed Base Mineral Foundation 30g (Various Shades)', 47, 1),
(26, 7, 16, 'DHC Lip Cream (0.05 oz.)', 11, 1),
(27, 7, 14, 'EltaMD Foaming Facial Cleanser (7 oz.)', 34, 2),
(28, 8, 16, 'DHC Lip Cream (0.05 oz.)', 11, 1),
(29, 9, 16, 'DHC Lip Cream (0.05 oz.)', 11, 1),
(30, 10, 15, 'Jane iredale PurePressed Base Mineral Foundation 30g (Various Shades)', 47, 1),
(31, 11, 14, 'EltaMD Foaming Facial Cleanser (7 oz.)', 33, 1),
(32, 12, 27, 'SKIN Holy Hydration! ', 14, 1),
(33, 12, 26, 'Pacifica Beauty', 18, 2),
(34, 13, 27, 'SKIN Holy Hydration! ', 14, 2),
(35, 13, 2, 'Simple Smoothing Facial Toner ', 6, 3);

-- --------------------------------------------------------

--
-- Table structure for table `product name`
--

CREATE TABLE `product name` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `price` int(15) NOT NULL,
  `image` varchar(200) NOT NULL,
  `describe` varchar(10000) NOT NULL,
  `idproduct` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `product name`
--

INSERT INTO `product name` (`id`, `name`, `price`, `image`, `describe`, `idproduct`) VALUES
(1, 'Hada Labo Cleansers', 3, 'https://hadalabo.com.vn/wp-content/uploads/2021/08/Cleanser.png', 'Hada Labo Deep Cleansing Moisturizing Cream is a cleanser product in Hada Labo\'s Advanced Nourish moisturizing line, with a creamy foaming texture that gently penetrates deep into pores to remove dirt. & sebum, while combining HA, SHA, Nano HA deep moisturizing system to help provide moisture for the skin to be moist, smooth, and youthful every day.', 1),
(2, 'Simple Smoothing Facial Toner ', 4, 'https://www.simpleskincare.com/sk-eu/content/dam/brands/simple/global_use/1880162-simple-kts-soothing-facial-toner-200ml.jpg.rendition.767.767.jpg', 'Simple Soothing Facial Toner removes any alkaline residue left after cleansing, helping to rebalance the skin\'s natural pH. Suitable for sensitive skin. Have you noticed that the color of our bottle is a bit darker now than it used to be? That\'s because they\'re made from 100% recycled plastic!', 1),
(3, 'Maybelline New York Liquid Foundation', 48, 'https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcQfRsj9owPJsziEnqyUbaXGObcHsZCUHu3HOROLETyYQ1zKxF5u', 'Fit Me! Matte + Poreless from Maybelline New York goes beyond skin tone matching to fit the unique texture issues of normal to oily skin for the ultimate natural skin fit. While some foundations can exaggerate pores and oily skin, only Maybelline\'s pore-minimizing foundation contains their genius blurring micro-powders that erase pores and absorb oil for a naturally matte and poreless-looking finish. Dermatologist and allergy tested. Does not clog pores. The oil control formula makes it the perfect foundation for an ideal matte finish. Now with SPF22.', 2),
(4, 'STUDIO FIX 24-HOUR SMOOTH WEAR CONCEALER', 2, 'https://product.hstatic.net/200000425979/product/61jwwo9vqwl._sl1500__024c21ade6184cad96615ec5ac113d37_1024x1024.jpg', 'With a fairly thick cream, it can cover most imperfections from dark circles to dark spots. The cream is quite easy to spread and blends into the skin color to create a natural look, the color is very suitable for the skin of Asian girls, after using it, there is no yellowing of the skin. With the ability to cover magic with the highest sun protection index today.', 2),
(13, 'Obagi Medical Obagi-C Fx System - Normal to Dry (5 piece)', 385, 'https://static.thcdn.com/images/large/webp//productimg/1600/1600/11288931-7694866330480900.jpg', 'Sunscreens, moisturizers, toners and so forth—finding the right products for your skin type can be quite the effort. Luckily, the Obagi Obagi-C Fx System - Normal to Dry takes the guesswork out for you. Complete with all the daily essentials needed for your skin care regimen\'s start, middle and end, this all-encompassing kit is just the thing to put your skin care woes to rest. Potent vitamin C instantly brightens and revitalizes your skin\'s complexion while botanical extracts work in harmony to protect your skin from future aggressors. Fine lines and wrinkles are reduced for a youthful-looking complexion. Wake up to refreshed, renewed skin, the kind that you ultimately deserve.', 1),
(14, 'EltaMD Foaming Facial Cleanser (7 oz.)', 32, 'https://static.thcdn.com/images/large/webp//productimg/1600/1600/11370307-1475014852189893.jpg', 'EltaMD\'s Foaming Facial Cleanser is an oil-free, pH-balanced formula that gently cleanses without irritating or drying your skin. Utilizing Ultra-Fine Self-Foaming Technology, this foaming cleanser deep cleans your skin easily removing pollutants and impurities. Enriched with bromelain, this pineapple-derived enzyme leaves skin looking and feeling smoother and softer.\r\nKey Ingredients:\r\n\r\n- Ultra-Fine Self-Foaming Technology: ultra-fine foaming bubbles that deep clean skin\r\n- Bromelain: enzymes found in pineapple, helps reduce inflammation and removes dead skin cells leaving skin smoother', 1),
(15, 'Jane iredale PurePressed Base Mineral Foundation 30g (Various Shades)', 46, 'https://static.thcdn.com/images/large/webp//productimg/1600/1600/13633644-6974946419457772.jpg', 'PurePressed Base Mineral Foundation Refill proves that powder can look good, feel good and be good for the skin. \r\nOur best-selling one-step foundation, concealer and sunscreen delivers weightless, buildable coverage and a skin-like, semi-matte finish. Formulated with hydrating sea mineral extracts, potent antioxidants and pure mineral pigments, the nourishing, non-cakey formula builds and blends easily for a filter-like appearance – no drying, no settling. \r\nChoose from 26 shades in a dermatologist tested formula that\'s free from talc and other bulking agents that can clog pores and irritate skin. Pairs with our sleek Refillable Compact for less waste and easy application, at home and on the go. Reef safe, broad spectrum sun protection. Vegan. Always cruelty-free.', 2),
(16, 'DHC Lip Cream (0.05 oz.)', 10, 'https://static.thcdn.com/images/large/webp//productimg/1600/1600/11207544-1114918471847064.jpg', '- DHC Lip Cream calms, comforts and softens with soothing aloe, olive oil and vitamin E. - Designed to neutralize free radicals, defend against feather line creators and collagen deflators, it locks-in moisture to restore and revive the lips.', 2),
(22, '5-kinds kit Manicure Tools Nylon cosmetic brush', 4, 'https://cdn.shopify.com/s/files/1/0595/6691/5779/files/Jan-2023_Mobile_2_1024x1024.jpg?v=1673345852', '- 5 kinds Acrylic Handle Manicure Tools Fiber hair Nail Art Pen\r\n- 49g/set with transparent opp bag.', 2),
(23, 'Honest Beauty 2-in-1 Extreme Length Clean Mascara', 18, 'https://m.media-amazon.com/images/I/51Mfuw9z-qL._AC_UL400_.jpg', 'New look, same great formula; packaging may vary\r\nAward-winning, bestselling double-sided mascara plus lash primer for ultimate length and volume\r\nOphthalmologist Tested\r\nCruelty free\r\nEWG Certified\r\n100% tree-free paper carton\r\nMADE WITH: Jojoba Esters\r\nMADE WITHOUT: Parabens, Paraffins, Synthetic Fragrances, Silicones, Mineral Oil', 2),
(24, 'Neutrogena Hydro Boost Hyaluronic Acid ', 16, 'https://m.media-amazon.com/images/I/71n9F0LtxLL._AC_UL400_.jpg', 'Packaging may vary: 1.7-ounce jar of Neutrogena Hydro Boost hydrating water-gel daily face and neck moisturizer with hyaluronic acid to hydrate dry skin\r\nGel facial moisturizer formula provides hydration to skin, leaving it looking smooth and supple day after day', 1),
(25, 'CeraVe Moisturizing Cream Combo Pack', 19, 'https://m.media-amazon.com/images/I/61PcGo1BiJL._AC_UL400_.jpg', 'MOISTURIZING CREAM & HYDRATING FACE WASH BUNDLE ] Contains 16oz CeraVe Moisturizing Cream with convenient pump top. Combo pack includes a sample size of CeraVe Hydrating Facial Cleanser', 1),
(26, 'Pacifica Beauty', 16, 'https://m.media-amazon.com/images/I/71NwVuj732L._AC_UL400_.jpg', 'GIVE YOUR SKIN A HYDRATION BOOST with this concentrated bioactive serum that moisturizes and relieves dull, dry skin for improved texture and brightness', 1),
(27, 'SKIN Holy Hydration! ', 13, 'https://m.media-amazon.com/images/I/61xLz1gRLML._AC_UL400_.jpg', 'POWERFUL HYDRATION BOOST: For a powerful boost of hydration to the face and luminous skin, this award-winning facial moisturizer does it all. It’s packed with skin-loving ingredients that even out skin tone and replenish lost moisture for a firmed up, bouncy complexion.', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orderdetail`
--
ALTER TABLE `orderdetail`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product name`
--
ALTER TABLE `product name`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `order`
--
ALTER TABLE `order`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `orderdetail`
--
ALTER TABLE `orderdetail`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT for table `product name`
--
ALTER TABLE `product name`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

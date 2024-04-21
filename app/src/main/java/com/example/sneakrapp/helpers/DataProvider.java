package com.example.sneakrapp.helpers;


import android.content.Context;

import com.example.sneakrapp.R;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.sneakrapp.models.Product;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import android.content.Context;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class DataProvider {
    private static SharedPreferences prefs;
    public static void init(Context context) {
        prefs = context.getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
    }

    public static void updateCategoryCount(String category) {
        int currentCount = prefs.getInt(category, 0);
        prefs.edit().putInt(category, currentCount + 1).apply();
        Log.d("DataProvider", "Category " + category + " updated count to " + (currentCount + 1));

    }



    public static String getPreferredCategory() {
        String[] categories = {"Designer", "Newest-Collections", "Active-Wear"};
        String preferredCategory = categories[0];
        int maxCount = 0;

        for (String category : categories) {
            int count = prefs.getInt(category, 0);
            if (count > maxCount) {
                maxCount = count;
                preferredCategory = category;
            }
            Log.d("DataProvider", "Category " + category + " has count: " + count);
        }
        Log.d("DataProvider", "Preferred category: " + preferredCategory);
        return preferredCategory;
    }

    public static void clearPreferences() {
        prefs.edit().clear().commit();
    }

    public static Map<Integer, Map<String, Object>> generateShoeProducts() {
        Map<Integer, Map<String, Object>> products = new LinkedHashMap<>();
        String[][] shoeData = {

                {"Designer", "Travis Scott Air Jordan 4 Retro sneakers", "These Jordan 4s were made in collaboration with rapper, Travis Scott and nicknamed the “Cactus Jack” edition.", "250.00", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/078/714/432/original/365514_01.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/078/714/423/original/365514_08.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/078/714/428/original/365514_04.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/078/714/425/original/365514_06.jpg.jpeg?action=crop&width=950"},
                {"Active-Wear", "Air Zoom Pegasus 40", "A springy ride for every run, the Peg's familiar, just-for-you feel returns to help you accomplish your goals. ", "210.00", "https://www.stirlingsports.co.nz/productimages/medium/1/106097_631612_102968.jpg", "https://www.stirlingsports.co.nz/productimages/productthumb/2/106097_631612_102972.jpg", "https://www.stirlingsports.co.nz/productimages/productthumb/2/106097_631612_102971.jpg", "https://www.stirlingsports.co.nz/productimages/productthumb/2/106097_631612_102970.jpg"},
                {"Newest-Collections", "Zoom Kobe 4 Protro 'Philly'", "The Nike Zoom Kobe 4 Protro 'Philly' revives the 2009 colorway that pays homage to Kobe Bryant's hometown of Philadelphia. ", "237.00", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/099/381/332/original/1250282_01.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/099/381/324/original/1250282_04.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/099/381/328/original/1250282_06.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/099/381/329/original/1250282_08.jpg.jpeg?action=crop&width=950"},
                {"Active-Wear", "Duramo SL Mens", "Race ahead with the Duramo SL shoes from adidas. Adapted for dynamic movement, they feature Lightmotion technology to keep you going forward. ", "99.00", "https://www.stirlingsports.co.nz/productimages/medium/1/105400_627489_102098.jpg", "https://www.stirlingsports.co.nz/productimages/productthumb/2/105400_627489_102101.jpg", "https://www.stirlingsports.co.nz/productimages/productthumb/2/105400_627489_102100.jpg", "https://www.stirlingsports.co.nz/productimages/productthumb/2/105400_627489_102099.jpg"},
                {"Newest-Collections", "Air Jordan 5 Retro SE 'Sail'", "The Air Jordan 5 Retro SE 'Sail' is crafted with off-white suede on the upper, featuring heritage AJ5 details, including lace locks, molded TPU eyelets and breathable quarter panel netting.", "180.00", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/099/611/670/original/1259185_01.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/099/611/673/original/1259185_02.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/099/611/693/original/1259185_04.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/099/611/689/original/1259185_06.jpg.jpeg?action=crop&width=950"},
                {"Active-Wear", "Hoka Womens Clifton 9 Running Shoes", "The ninth iteration of our award-winning franchise, the Clifton 9 is lighter and more cushioned than ever before. Eliminating weight while adding 3mm in stack height.", "279.99", "https://www.rebelsport.co.nz/globalassets/productimages/rebel/8205002/8205002_default_1.jpg/CatalogContentDetails-2129752-800-800-75-0,0", "https://www.rebelsport.co.nz/globalassets/productimages/rebel/8205002/8205002_default_2.jpg/CatalogContentDetails-2129750-800-800-75-0,0", "https://www.rebelsport.co.nz/globalassets/productimages/rebel/8205002/8205002_default_3.jpg/CatalogContentDetails-2129749-800-800-75-0,0", "https://www.rebelsport.co.nz/globalassets/productimages/rebel/8205002/8205002_default_4.jpg/CatalogContentDetails-2129756-800-800-75-0,0"},
                {"Newest-Collections", "Rayssa Leal x Dunk Low SB", "The Rayssa Leal x Nike Dunk Low SB debuts a special-edition colorway designed in collaboration with the Brazilian skateboarder.", "155.00", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/099/227/861/original/1320672_01.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/099/227/850/original/1320672_04.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/099/227/854/original/1320672_06.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/099/227/851/original/1320672_08.jpg.jpeg?action=crop&width=950"},
                {"Designer", "Trophy Room x Air Jordan 1 Retro High OG SP 'Chicago' ", "The Friends & Family edition of the Trophy Room x Air Jordan 1 Retro High OG SP ‘Chicago’ is mostly unchanged from the retail version of the coveted collaboration, save for the addition of royal blue laces printed with the phrase ‘Rumor has it …’", "1300.00", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/080/950/980/original/731816_01.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/080/950/973/original/731816_04.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/080/950/977/original/731816_08.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/080/950/984/original/731816_11.jpg.jpeg?action=crop&width=950"},
                {"Active-Wear", "Air Max 97 'Triple White'", "The Nike Air Max 97 'Triple White' features a white leather and mesh upper with Wolf Grey accents. ", "123.00", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/078/453/950/original/180155_01.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/078/453/951/original/180155_04.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/078/453/964/original/180155_08.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/078/453/956/original/180155_06.jpg.jpeg?action=crop&width=950"},
                {"Newest-Collections", "Kobe 8 Protro 'Court Purple'", "The Nike Kobe 8 Protro 'Court Purple' showcases an all-over print made up of the jersey numbers that Kobe Bryant wore during his 20-year career with the Los Angeles Lakers.", "190.00", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/097/619/130/original/1210580_01.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/097/619/244/original/1210580_12.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/097/619/141/original/1210580_08.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/097/619/136/original/1210580_06.jpg.jpeg?action=crop&width=950"},
                {"Designer", "Air Jordan 9 Retro 'Powder Blue' 2024", "The 2024 edition of the Air Jordan 9 Retro ‘Powder Blue’ brings back an OG colorway in honor of the shoe’s 30th anniversary. A speed lacing system secures the white leather upper, featuring stitched detailing on the side panels and a contrasting black mesh tongue.", "275.00", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/098/992/026/original/1330226_01.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/098/992/030/original/1330226_04.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/098/992/051/original/1330226_08.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/098/992/040/original/1330226_05.jpg.jpeg?action=crop&width=950"},
                {"Active-Wear", "Off-White x Air VaporMax 'Part 2'", "Virgil Abloh teamed up with Nike to release an all-black Air VaporMax in March 2018, a spiritual successor to Abloh's VaporMax design from ‘The Ten' collection. ", "494.00", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/078/454/524/original/288660_01.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/078/454/532/original/288660_04.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/078/454/540/original/288660_09.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/078/454/536/original/288660_08.jpg.jpeg?action=crop&width=950"},
                {"Designer", "Red Ribbon Recon x Air Jordan 1 Retro High", "The Supreme x Louis Vuitton x Red Ribbon Recon x Air Jordan 1 Retro High sneaker boasts an ultra-luxe look in this drop from sneaker customizer Red Ribbon Recon. ", "33000.00", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/078/461/933/original/206830_01.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/078/461/947/original/206830_08.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/078/461/936/original/206830_06.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/078/461/938/original/206830_04.jpg.jpeg?action=crop&width=950"},
                {"Active-Wear", "Nike Zoom Vomero 5 SP", "The Vomero 5 takes early 2000s running to modern heights. A combination of breathable and durable materials stands ready for the rigors of your day, while Zoom Air cushioning delivers a smooth ride.", "270.00", "https://i8.amplience.net/i/jpl/jd_667146_a?qlt=92&w=750&h=531&v=1&fmt=auto", "https://i8.amplience.net/i/jpl/jd_667146_c?qlt=92&w=750&h=531&v=1&fmt=auto", "https://i8.amplience.net/i/jpl/jd_667146_e?qlt=92&w=750&h=531&v=1&fmt=auto", "https://i8.amplience.net/i/jpl/jd_667146_f?qlt=92&w=750&h=531&v=1&fmt=auto"},
                {"Newest-Collections", "JJJJound x 2002R GORE-TEX 'Charcoal'", "The JJJJound x New Balance 2002R GORE-TEX 'Charcoal' is equipped with a gusseted tongue and lined in a waterproof GORE-TEX membrane. ", "237.00", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/098/236/000/original/1360157_01.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/098/236/026/original/1360157_08.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/098/236/020/original/1360157_06.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/098/236/005/original/1360157_04.jpg.jpeg?action=crop&width=950"},
                {"Active-Wear", "Air VaporMax Plus 'Sunset'", "Taking the Air Max Plus upper and the Vapormax full-length Max Air unit, the Air VaporMax Plus 'Sunset’ features a Tonal Orange neoprene upper with a black molded TPU cage and matching laces.", "190.00", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/078/447/342/original/306660_01.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/078/447/344/original/306660_02.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/078/447/349/original/306660_04.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/078/447/350/original/306660_06.jpg.jpeg?action=crop&width=950"},
                {"Newest-Collections", "Dunk Low SP 'Brazil' 2020", "The Nike Dunk Low SP ‘Brazil’ replicates the look of the classic ‘Brazil’ Dunk from 2001, featuring a simple two-tone color scheme that combines a yellow leather base with contrasting green overlays.", "112.00", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/081/743/606/original/616048_01.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/081/743/625/original/616048_06.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/081/743/631/original/616048_08.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/081/743/619/original/616048_04.jpg.jpeg?action=crop&width=950"},
                {"Active-Wear", "Air Max Plus TN 'Wolf Grey'", "The maximalist design of the Nike Air Max Plus TN is tempered by this subtle monochromatic colorway. Released in February 2018, the exterior of the lifestyle silhouette is finished almost entirely in Wolf Grey.", "320.00", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/078/714/740/original/351045_01.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/078/714/739/original/351045_02.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/078/714/733/original/351045_08.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/078/714/732/original/351045_06.jpg.jpeg?action=crop&width=950"},
                {"Newest-Collections", "Air Jordan 6 Retro 'Yellow Ochre'", "The Air Jordan 6 Retro ‘Yellow Ochre’ updates the championship silhouette in color blocking that recalls the OG ‘Carmine’ release. ", "134.00", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/097/208/460/original/1228708_01.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/097/208/297/original/1228708_11.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/097/208/480/original/1228708_08.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/097/208/276/original/1228708_06.jpg.jpeg?action=crop&width=950"},
                {"Active-Wear", "530 'White Natural Indigo'", "The original MR530 combined turn of the millennium aesthetics with the reliability of a high milage running shoe. The reintroduced 530 applies a contemporary, everyday style outlook to this performance-minded design. ", "150.00", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/071/383/827/original/602070_01.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/071/383/838/original/602070_08.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/071/383/835/original/602070_06.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/071/383/833/original/602070_04.jpg.jpeg?action=crop&width=950"},
                {"Newest-Collections", "Dunk Low SB 'Court Purple'", "The Nike Dunk Low SB ‘Court Purple’ delivers a straightforward colorway of the classic silhouette, redolent of the brand’s pink and silver box eras. ", "145.00", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/097/181/404/original/699721_01.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/049/161/603/original/699721_08.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/049/161/600/original/699721_06.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/049/161/591/original/699721_04.jpg.jpeg?action=crop&width=950"},
                {"Designer", "Air Jordan 1 High 'Chicago' 1985", "Conceptualized by Peter Moore, the Air Jordan 1 High 'Chicago' 1985 shoe changed the way people regarded sneakers forever. ", "2500.00", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/081/144/943/original/55279_01.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/081/144/936/original/55279_08.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/081/144/934/original/55279_06.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/081/144/937/original/55279_04.jpg.jpeg?action=crop&width=950"},
                {"Active-Wear", "Air Zoom Vomero 5 'Vast Grey' 2019", "Reintroduced to the public through a December 2018 collaboration with A-COLD-WALL*, the Nike Zoom Vomero 5 ‘Vast Grey’ landed on store shelves as a standalone release the following February.", "160.00", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/079/299/738/original/490661_01.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/079/299/740/original/490661_04.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/079/299/741/original/490661_06.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/079/299/751/original/490661_08.jpg.jpeg?action=crop&width=950"},
                {"Newest-Collections", "Wmns Dunk Low 'Valentine's Day 2024'", "The Nike Women’s Dunk Low ‘Valentine's Day 2024’ features a smooth white leather upper, fortified with suede overlays at the forefoot and heel in a pale red hue. ", "83.00", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/096/982/242/original/1303377_01.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/096/982/247/original/1303377_02.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/096/982/246/original/1303377_04.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/096/982/254/original/1303377_06.jpg.jpeg?action=crop&width=950"},
                {"Designer", "Air Foamposite One 'Eggplant' 2024", "The 2024 edition of the Nike Air Foamposite One ‘Eggplant’ brings back a coveted colorway of the vintage ‘90s hoops shoe.", "190.00", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/097/657/613/original/1293123_01.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/097/657/624/original/1293123_11.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/097/657/631/original/1293123_12.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/097/657/596/original/1293123_08.jpg.jpeg?action=crop&width=950"},
//                {"Active-Wear", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Newest-Collections", "Air Max Plus OG 'Voltage Purple' 2024", "Let your attitude have the edge with flame-like caging that adds heat to the streets while airy mesh keeps you cool. The Nike Air Max Plus gives you a tuned Nike Air experience that offers premium stability and unbelievable cushioning.", "220.00", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/097/304/828/original/1304924_01.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/097/304/832/original/1304924_02.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/097/304/838/original/1304924_04.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/097/304/842/original/1304924_06.jpg.jpeg?action=crop&width=950"},
                {"Designer", "Louis Vuitton x Air Force 1 Low 'Metallic Gold'", "The Louis Vuitton x Air Force 1 Low ‘Metallic Gold’ showcases a gleaming finish in celebration of the iconic silhouette’s 40th anniversary. ", "11890.00", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/094/453/901/original/1005435_01.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/094/453/915/original/1005435_08.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/094/453/910/original/1005435_06.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/094/453/908/original/1005435_04.jpg.jpeg?action=crop&width=950"},
//                {"Active-Wear", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Newest-Collections", "Air Jordan 2 Retro 'Python'", "The Air Jordan 2 Retro ‘Python’ carries a white leather upper with a snakeskin-patterned midfoot overlay outlined in black piping. ", "150.00", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/097/168/745/original/1338187_01.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/097/174/750/original/1338187_02.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/097/168/762/original/1338187_08.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/097/168/757/original/1338187_06.jpg.jpeg?action=crop&width=950"},
                {"Designer", "Air Jordan 4 Retro 'Thunder' 2023'", "The 2023 edition of the Air Jordan 4 Retro ‘Thunder’ brings back the coveted colorway originally released in 2006 (and previously reissued in 2012). ", "342.00", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/086/042/596/original/1124754_01.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/086/042/586/original/1124754_08.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/086/042/590/original/1124754_11.jpg.jpeg?action=crop&width=950", "https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/086/042/591/original/1124754_12.jpg.jpeg?action=crop&width=950"},
//                {"Active-Wear", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
//                {"Newest-Collections", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
//                {"Designer", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
//                {"Active-Wear", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
//                {"Newest-Collections", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
//                {"Designer", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
//                {"Active-Wear", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
//                {"Newest-Collections", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
//                {"Designer", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
//                {"Active-Wear", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
//                {"Newest-Collections", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
//                {"Designer", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
//                {"Active-Wear", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
//                {"Newest-Collections", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
        };

        for (int i = 0; i < shoeData.length; i++) {
            Map<String, Object> shoeProperties = new LinkedHashMap<>();
            shoeProperties.put("category", shoeData[i][0]);
            shoeProperties.put("name", shoeData[i][1]);
            shoeProperties.put("description", shoeData[i][2]);
            shoeProperties.put("price", Double.parseDouble(shoeData[i][3]));
//            shoeProperties.put("imageURL1", shoeData[i][4]); // Add image URL here
//            shoeProperties.put("imageURL2", shoeData[i][4]); // Add image URL here
//            shoeProperties.put("imageURL3", shoeData[i][4]); // Add image URL here
//            shoeProperties.put("imageURL1", shoeData[i][5]); // Add image URL here
            List<String> images = new ArrayList<>();
            for (int j = 4; j < shoeData[i].length; j++) {  // Assuming images start at index 4
                images.add(shoeData[i][j]);
            }
            shoeProperties.put("images", images);

            products.put(i + 1, shoeProperties);
            //shoeProperties.put("imageURL", shoeData[i].length > 7 ? shoeData[i][7] : "https://images.app.goo.gl/SEkXjMBrMandgCGSA");

        }
        return products;
      }

    public static List<Product> getProducts(String category) {
        List<Product> productsList = new LinkedList<>();
        Map<Integer, Map<String, Object>> products = generateShoeProducts();

        if ("Shop-All".equals(category)) {
            // Add all products from all categories
            for (Map.Entry<Integer, Map<String, Object>> entry : products.entrySet()) {
                Map<String, Object> details = entry.getValue();
                String name = (String) details.get("name");
                String description = (String) details.get("description");
                double price = (double) details.get("price");
                String formattedPrice = String.format("$%.2f", price);

                List<String> imageUrls = (List<String>) details.get("images");

                Product product = new Product(name, description, formattedPrice, imageUrls);
                productsList.add(product);
            }
        } else {

            for (Map.Entry<Integer, Map<String, Object>> entry : products.entrySet()) {
                Map<String, Object> details = entry.getValue();
                String productCategory = (String) details.get("category");

                // Debug log to check the category of each product
                //Log.d("DataProvider", "Product Category: " + productCategory);

                if (category.equals(productCategory)) {
                    //int id = entry.getKey();
                    String name = (String) details.get("name");
                    String description = (String) details.get("description");
                    double price = (double) details.get("price");
                    String formattedPrice = String.format("$%.2f", price);

                    //String icon = "firstpic" + id; // Ensure you have a mechanism to resolve this to actual drawable resources
                    //String heart = "heart" + id; // Similarly, ensure this is usable in your UI
                    List<String> imageUrls = (List<String>) details.get("images");


                    String imageURL1 = (String) details.get("imageURL1");
                    String imageURL2 = (String) details.get("imageURL2");
                    String imageURL3 = (String) details.get("imageURL3");
                    String imageURL4 = (String) details.get("imageURL4");

                    Product product = new Product(name, description, formattedPrice, imageUrls);
                    productsList.add(product);
                }
            }
            }

            // Debug log to check the size of the product list
            Log.d("DataProvider", "Product List Size: " + productsList.size());

            // Log the products retrieved for this category
            for (Product product : productsList) {
                Log.d("DataProvider", "Product: " + product.getName());
            }

            return productsList;
        }


}

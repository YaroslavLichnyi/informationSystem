package information.system.Server.Controller;

/*
Meat: Pork, Beaf, Chicken, Crab
Garnishes: Potato, Buckwheat, Pasta, Rice
Salads: Cabbage, Fresh vegetables
Desserts: Cake, Ice Cream, Chocolate
Drinks: Coffee, Juice, Water, Cola, Milk
 */

import information.system.Server.Model.Dish;
import information.system.Server.Model.DishCategory;

public class FillRestaurant {

    final static String dishCategory_1 = "Meat";
    final static String dishCategory_2 = "Garnishes";
    final static String dishCategory_3 = "Salads";
    final static String dishCategory_4 = "Desserts";
    final static String dishCategory_5 = "Drinks";
    final static String dish_1_1 = "Pork";
    final static String dish_1_2 = "Beaf";
    final static String dish_1_3 = "Chicken";
    final static String dish_1_4 = "Crab";
    final static String dish_2_1 = "Potato";
    final static String dish_2_2 = "Buckwheat";
    final static String dish_2_3 = "Pasta";
    final static String dish_2_4 = "Rice";
    final static String dish_3_1 = "Cabbage";
    final static String dish_3_2 = "Fresh vegetables";
    final static String dish_4_1 = "Cake";
    final static String dish_4_2 = "Ice Cream";
    final static String dish_4_3 = "Chocolate";
    final static String dish_5_1 = "Coffee";
    final static String dish_5_2 = "Juice";
    final static String dish_5_3 = "Water";
    final static String dish_5_4 = "Cola";
    final static String dish_5_5 = "Milk";


    public static void main(String[] args) {

        DishCategory dishCategory1 = new DishCategory(dishCategory_1, 1);
        dishCategory1.addDish(new Dish(dish_1_1, 150.00, "Steak"));
        dishCategory1.addDish(new Dish(dish_1_2, 200.00, "Beafsteak"));
        dishCategory1.addDish(new Dish(dish_1_3, 100.00, "Roast chicken"));
        dishCategory1.addDish(new Dish(dish_1_4, 250.00, "Boiled Crab"));
        dishCategory1.setHealthyFood(false);

        DishCategory dishCategory2 = new DishCategory(dishCategory_2, 2);
        dishCategory2.addDish(new Dish(dish_2_1, 35.00, "Baked potato"));
        dishCategory2.addDish(new Dish(dish_2_2, 20.00, "Boiled buckwheat"));
        dishCategory2.addDish(new Dish(dish_2_3, 25.00, "Pasta with cheese"));
        dishCategory2.addDish(new Dish(dish_2_4, 30.00, "Boiled rice"));
        dishCategory2.setHealthyFood(false);

        DishCategory dishCategory3 = new DishCategory(dishCategory_3, 3);
        dishCategory3.addDish(new Dish(dish_3_1, 25.00, "Sliced cabbage"));
        dishCategory3.addDish(new Dish(dish_3_2, 50.00, "Fresh sliced vegetables"));
        dishCategory3.setHealthyFood(false);

        DishCategory dishCategory4 = new DishCategory(dishCategory_4, 4);
        dishCategory4.addDish(new Dish(dish_4_1, 55.00, "Cherry cake"));
        dishCategory4.addDish(new Dish(dish_4_2, 45.00, "Strawberry ice cream"));
        dishCategory4.addDish(new Dish(dish_4_3, 50.00, "Hot chocolate"));
        dishCategory4.setHealthyFood(true);

        DishCategory dishCategory5 = new DishCategory(dishCategory_5, 5);
        dishCategory5.addDish(new Dish(dish_5_1, 60.00, "Pure black coffee"));
        dishCategory5.addDish(new Dish(dish_5_2, 20.00, "Fruit juice"));
        dishCategory5.addDish(new Dish(dish_5_3, 10.00, "Drink water"));
        dishCategory5.addDish(new Dish(dish_5_3, 15.00, "Pure milk"));
        dishCategory5.setHealthyFood(false);

        //TODO
        

    }
}

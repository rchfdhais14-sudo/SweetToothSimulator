import java.util.Scanner;
import java.util.Random;

public class SweetToothSimulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Начальные характеристики персонажа
        int day = 1;
        int money = 500; // Рубли
        int mood = 50;   // от 0 до 100
        double weight = 65.0; // кг
        int sugarLevel = 90;  // мг/дл (норма ~70-140)
        
        // Инвентарь сладостей
        int candies = 2;
        int chocolates = 1;
        int cakes = 0;

        System.out.println("🍫 Добро пожаловать в Симулятор Сладкоежки! 🍰");
        System.out.println("Ваша цель — продержаться 7 дней, балансируя между счастьем и здоровьем.");
        System.out.println("Опасайтесь критического уровня сахара и лишнего веса!\n");

        // Главный игровой цикл (7 дней)
        while (day <= 7) {
            System.out.println("--- ДЕНЬ " + day + " ---");
            System.out.printf("💰 Деньги: %d руб. | 😊 Настроение: %d%% | ⚖️ Вес: %.1f кг | 🩸 Сахар: %d мг/дл\n", 
                    money, mood, weight, sugarLevel);
            System.out.printf("🎒 Инвентарь: Конфеты: %d | Шоколадки: %d | Торты: %d\n", 
                    candies, chocolates, cakes);
            System.out.println("----------------");
            System.out.println("Что вы будете делать?");
            System.out.println("1. Съесть конфету (-1 конфета, +5 сахар, +3 настроение)");
            System.out.println("2. Съесть шоколадку (-1 шоколадка, +15 сахар, +10 настроение, +0.2 кг)");
            System.out.println("3. Съесть кусок торта (-1 торт, +35 сахар, +25 настроение, +0.5 кг)");
            System.out.println("4. Сходить в магазин за сладостями");
            System.out.println("5. Пойти на прогулку / заняться спортом (-20 сахар, -0.4 кг, -5 настроение)");
            System.out.println("6. Лечь спать (перейти на следующий день)");

            System.out.print("Ваш выбор: ");
            int choice = scanner.nextInt();
            System.out.println();

            switch (choice) {
                case 1:
                    if (candies > 0) {
                        candies--;
                        sugarLevel += 5;
                        mood = Math.min(100, mood + 3);
                        System.out.println("🍬 Вы съели вкусную конфетку. На душе стало чуточку теплее.");
                    } else {
                        System.out.println("❌ У вас нет конфет! Сходите в магазин.");
                    }
                    break;
                case 2:
                    if (chocolates > 0) {
                        chocolates--;
                        sugarLevel += 15;
                        mood = Math.min(100, mood + 10);
                        weight += 0.2;
                        System.out.println("🍫 Шоколадный батончик уничтожен. Гормон счастья зашкаливает!");
                    } else {
                        System.out.println("❌ Шоколад закончился!");
                    }
                    break;
                case 3:
                    if (cakes > 0) {
                        cakes--;
                        sugarLevel += 35;
                        mood = Math.min(100, mood + 25);
                        weight += 0.5;
                        System.out.println("🍰 О да! Огромный кусок торта с кремом. Жизнь прекрасна!");
                    } else {
                        System.out.println("❌ Тортов в холодильнике не обнаружено.");
                    }
                    break;
                case 4:
                    // Магазин
                    boolean shopping = true;
                    while (shopping) {
                        System.out.println("🛒 --- КОНДИТЕРСКИЙ МАГАЗИН ---");
                        System.out.println("У вас есть: " + money + " руб.");
                        System.out.println("1. Купить конфету (20 руб.)");
                        System.out.println("2. Купить шоколадку (60 руб.)");
                        System.out.println("3. Купить торт (300 руб.)");
                        System.out.println("4. Выйти из магазина");
                        System.out.print("Купить: ");
                        int buyChoice = scanner.nextInt();
                        
                        if (buyChoice == 1 && money >= 20) {
                            candies++; money -= 20; System.out.println("Куплена конфета!");
                        } else if (buyChoice == 2 && money >= 60) {
                            chocolates++; money -= 60; System.out.println("Куплена шоколадка!");
                        } else if (buyChoice == 3 && money >= 300) {
                            cakes++; money -= 300; System.out.println("Куплен торт!");
                        } else if (buyChoice == 4) {
                            shopping = false;
                        } else {
                            System.out.println("❌ Недостаточно денег или неверный выбор!");
                        }
                        System.out.println();
                    }
                    break;
                case 5:
                    sugarLevel = Math.max(60, sugarLevel - 20);
                    weight = Math.max(45.0, weight - 0.4);
                    mood = Math.max(0, mood - 5);
                    System.out.println("🏃‍♂️ Вы заставили себя выйти на пробежку. Сахар упал, килограммы ушли, но настроение подпортилось.");
                    break;
                case 6:
                    // Конец дня и случайное событие
                    System.out.println("💤 Вы ложитесь спать...");
                    day++;
                    money += 150; // Ежедневный заработок / карманные деньги
                    sugarLevel = Math.max(70, sugarLevel - 15); // Естественное падение сахара за ночь
                    
                    // Случайное событие
                    int event = random.nextInt(3);
                    System.out.println("\n✨ Наступило утро нового дня!");
                    if (event == 0) {
                        System.out.println("🎉 Событие: Бабушка прислала вам посылку со сладостями! (+2 конфеты, +1 шоколадка)");
                        candies += 2;
                        chocolates += 1;
                    } else if (event == 1) {
                        System.out.println("🦷 Событие: Заболел зуб от сладкого! Пришлось отдать деньги стоматологу (-200 руб.)");
                        money = Math.max(0, money - 200);
                    } else {
                        System.out.println("☀️ Погода отличная, день обещает быть хорошим.");
                    }
                    System.out.println();
                    break;
                default:
                    System.out.println("🤔 Непонятное действие, попробуйте еще раз.");
            }

            // Проверка критических условий (Проигрыш)
            if (sugarLevel >= 180) {
                System.out.println("🚨 ИГРА ОКОНЧЕНА! Уровень сахара превысил 180 мг/дл. Вас госпитализировали с гипергликемией!");
                return;
            }
            if (mood <= 0) {
                System.out.println("😢 ИГРА ОКОНЧЕНА! Настроение упало до 0. Вы впали в глубокую депрессию без сахара!");
                return;
            }
            if (weight >= 85.0) {
                System.out.println("⚖️ ИГРА ОКОНЧЕНА! Ваш вес превысил 85 кг. Весы сломались, а джинсы лопнули!");
                return;
            }
        }

        // Победа
        System.out.println("🏆 ПОЗДРАВЛЯЕМ! Вы успешно прожили неделю в режиме сладкоежки и сохранили баланс!");
        System.out.printf("Итоговый счет -> Вес: %.1f кг, Настроение: %d%%, Деньги: %d руб.\n", weight, mood, money);
    }
}

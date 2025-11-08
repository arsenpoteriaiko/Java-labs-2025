import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        int c11 = 12 % 11;
        System.out.printf("c11 = %d \n", c11);

        //Варіант завдання: Визначити клас автомобіль, який складається як мінімум з 5-и полів.

        CarSorter.sortCars();
    }
}

//Клас Car описує модель автомобіля з основними характеристиками.
class Car {
    private String brand;
    private String model;
    private int year;
    private double engineVolume;
    private double price;

    //Конструктор для створення об'єкта автомобіля.
    public Car(String brand, String model, int year, double engineVolume, double price) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.engineVolume = engineVolume;
        this.price = price;
    }

    //геттери
    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public double getEngineVolume() {
        return engineVolume;
    }

    public double getPrice() {
        return price;
    }

    //Перевизначення методу equals() для порівняння автомобілів.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Car car = (Car) obj;
        return year == car.year
                && Double.compare(car.engineVolume, engineVolume) == 0
                && Double.compare(car.price, price) == 0
                && Objects.equals(brand, car.brand)
                && Objects.equals(model, car.model);
    }

    //Повертає рядкове представлення об'єкта автомобіля.
    @Override
    public String toString() {
        return String.format("%-10s %-10s | Рік: %-4d | Двигун: %-4.1f л | Ціна: %.2f $",
                brand, model, year, engineVolume, price);
    }
}

class CarSorter {
    public static void sortCars() {
        try {
            // Створення масиву об'єктів класу Car
            Car[] cars = {
                    new Car("Toyota", "Camry", 2018, 2.5, 24000),
                    new Car("Honda", "Accord", 2020, 1.5, 26000),
                    new Car("BMW", "X5", 2019, 3.0, 52000),
                    new Car("Audi", "A4", 2021, 2.0, 41000),
                    new Car("Ford", "Focus", 2017, 1.6, 18000)
            };

            System.out.println("Початковий масив автомобілів:");
            printArray(cars);

            // Сортування за ціною (зростання) і роком випуску (спадання)
            Arrays.sort(cars, Comparator
                    .comparingDouble(Car::getPrice)
                    .thenComparing(Comparator.comparingInt(Car::getYear).reversed()));

            System.out.println("\nВідсортований масив:");
            printArray(cars);

            // Створення зразка автомобіля для пошуку
            Car target = new Car("Audi", "A4", 2021, 2.0, 41000);

            // Пошук ідентичного автомобіля
            int index = findCar(cars, target);
            if (index != -1) {
                System.out.println("\nЗнайдено ідентичний автомобіль:");
                System.out.println(cars[index]);
            } else {
                System.out.println("\nІдентичний автомобіль не знайдено.");
            }

        } catch (Exception e) {
            System.err.println("Помилка під час виконання програми: " + e.getMessage());
        }
    }

    //Виводить вміст масиву автомобілів у консоль.
    private static void printArray(Car[] cars) {
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    //Здійснює пошук автомобіля, ідентичного заданому, у масиві.
    private static int findCar(Car[] cars, Car target) {
        for (int i = 0; i < cars.length; i++) {
            if (cars[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }
}

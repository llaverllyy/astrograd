package main;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Astrograd {
    private static final Logger logger = Logger.getLogger(Astrograd.class.getName());
    private static final int MIN_ID = 1;
    private static final int MAX_ID = 100000;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n;
            try {
                n = Integer.parseInt(scanner.nextLine());
                if (n < 1 || n > 1000000) {
                    logger.log(Level.SEVERE, "Некорректное количество событий");
                    return;
                }
            } catch (NumberFormatException e) {
                logger.log(Level.SEVERE, "Ожидалось целое число");
                return;
            }

            LinkedList<Integer> queue = new LinkedList<>();
            Set<Integer> inQueue = new HashSet<>();

            logger.log(Level.INFO, "Программа запущена. Количество событий " + n);

            for (int i = 0; i < n; i++) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");

                if (parts.length == 0) {
                    logger.log(Level.WARNING, "Пустая строка ввода");
                    continue;
                }

                try {
                    int eventType = Integer.parseInt(parts[0]);

                    switch (eventType) {
                        case 1:
                            int newId = Integer.parseInt(parts[1]);
                            if (newId < MIN_ID || newId > MAX_ID) {
                                continue;
                            }
                            if (inQueue.contains(newId)) {
                                logger.log(Level.WARNING, "Данное id " + newId + " уже существует");
                                continue;
                            }
                            queue.addLast(newId);
                            inQueue.add(newId);
                            logger.log(Level.INFO, "Добавление нового человека в конец очереди");
                            break;

                        case 2:
                            if (queue.isEmpty()) {
                                logger.log(Level.WARNING, "Очередь пустая");
                                continue;
                            }
                            int removedId = queue.removeFirst();
                            inQueue.remove(removedId);
                            logger.log(Level.INFO, "Человек с id " + removedId + " купил билет и ушел");
                            break;

                        case 3:
                            if (queue.isEmpty()) {
                                logger.log(Level.WARNING, "Очередь пустая");
                                continue;
                            }
                            removedId = queue.removeLast();
                            inQueue.remove(removedId);
                            logger.log(Level.INFO, "Человек с id " + removedId + " ушел");
                            break;

                        case 4:
                            int queryId = Integer.parseInt(parts[1]);
                            if (!inQueue.contains(queryId)) {
                                logger.log(Level.WARNING, "Человека с id " + queryId + " нет в очереди");
                                continue;
                            }
                            int count = 0;
                            for (int person : queue) {
                                if (person == queryId) break;
                                count++;
                            }
                            logger.log(Level.INFO, "Перед человеком с id " + queryId + " стоит " + count + " человек");
                            break;

                        case 5:
                            if (queue.isEmpty()) {
                                logger.log(Level.WARNING, "Очередь пустая");
                                continue;
                            }
                            logger.log(Level.INFO, "Первый в очереди " + queue.getFirst());
                            break;

                        default:
                            logger.log(Level.WARNING, "Неизвестный тип события " + eventType);
                    }
                } catch (NumberFormatException e) {
                    logger.log(Level.WARNING, "Некорректный формат данных " + line);
                } catch (IllegalArgumentException e) {
                    logger.log(Level.WARNING, "Недостаточно данных " + line);
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Критическая ошибка", e);
        }
    }
}
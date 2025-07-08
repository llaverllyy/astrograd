package main;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Astrograd {
    private static final Logger logger = Logger.getLogger(Astrograd.class.getName());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();

        LinkedList<Integer> queue = new LinkedList<>();
        Set<Integer> inQueue = new HashSet<>();

        logger.log(Level.INFO, "Программа запущена");

        for (int i = 0; i < n; i++) {
            String[] parts = scanner.nextLine().split(" ");
            int eventType = Integer.parseInt(parts[0]);

            switch (eventType) {
                case 1:
                    int newId = Integer.parseInt(parts[1]);
                    queue.addLast(newId);
                    inQueue.add(newId);
                    logger.log(Level.INFO, "Добавление нового человека с id " + newId + " в конец очереди");
                    break;

                case 2:
                    if (!queue.isEmpty()) {
                        int removedId = queue.removeFirst();
                        inQueue.remove(removedId);
                        logger.log(Level.INFO, "Человек с id " + removedId + " купил билет и ушел");
                    } else {
                        logger.log(Level.WARNING, "Очередь пустая");
                    }
                    break;

                case 3:
                    if (!queue.isEmpty()) {
                        int removedId = queue.removeLast();
                        inQueue.remove(removedId);
                        logger.log(Level.INFO, "Человек с id " + removedId + " ушел");
                    } else {
                        logger.log(Level.WARNING, "Очередь пустая");
                    }
                    break;

                case 4:
                    int queryId = Integer.parseInt(parts[1]);
                    if (!inQueue.contains(queryId)) {
                        logger.log(Level.WARNING, "Человека с id " + queryId + " нет в очереди");
                    } else {
                        int count = 0;
                        for (int person : queue) {
                            if (person == queryId) break;
                            count++;
                        }
                        logger.log(Level.INFO, "Перед человеком с id " + queryId + " стоит " + count + " человек");
                    }
                    break;
                case 5:
                    if (!queue.isEmpty()) {
                        logger.log(Level.INFO, "Первый в очереди " + queue.getFirst());
                    } else {
                        logger.log(Level.WARNING, "Очередь пустая");
                    }
                    break;

                default:
                    logger.log(Level.SEVERE, "Неизвестный тип события " + eventType);
            }
        }
        scanner.close();
    }
}
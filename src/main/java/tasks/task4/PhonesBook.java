package tasks.task4;

import java.util.*;
import java.util.stream.Collectors;

public class PhonesBook {
    private Map<String, Subscriber> phonesBook;

    public PhonesBook() {
        phonesBook = new HashMap<>();
    }

    public boolean addNumber(String phone, String name, String surname) {
        if (phonesBook.containsKey(phone)) {
            return false;
        }
        Subscriber subscriber = new Subscriber(name, surname);
        phonesBook.put(phone, subscriber);
        return true;
    }

    public boolean deleteNumber(String phone) {
        if (!phonesBook.containsKey(phone))
            return false;
        phonesBook.remove(phone);
        return true;
    }

    public boolean changeSubscriber(String phone, String name, String surname) {
        if (!phonesBook.containsKey(phone))
            return false;
        Subscriber subscriber = phonesBook.get(phone);
        subscriber.setName(name);
        subscriber.setSurname(surname);
        return true;
    }

    public boolean changeNumber(String oldPhone, String newPhone) {
        if (!phonesBook.containsKey(oldPhone))
            return false;
        Subscriber subscriber = phonesBook.get(oldPhone);
        phonesBook.remove(oldPhone);
        phonesBook.put(newPhone, subscriber);
        return true;
    }

    public Subscriber findSubscriber(String phone) {
        return phonesBook.get(phone);
    }

    public PhonesBook strictCoincidence(String name, String surname) {
        Set<Map.Entry<String, Subscriber>> entrySet = phonesBook.entrySet();
        Subscriber subscriber = new Subscriber(name, surname);
        PhonesBook phonesBook = new PhonesBook();
        for (Map.Entry<String, Subscriber> el : entrySet) {
            if (el.getValue().equals(subscriber)) {
                phonesBook.addNumber(el.getKey(),
                        el.getValue().getName(),
                        el.getValue().getSurname());
            }
        }
        return phonesBook;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Phone").append(" | ")
                .append("Name").append(" | ")
                .append("Surname").append("\n");
        for (Map.Entry<String, Subscriber> el : phonesBook.entrySet())
            stringBuilder.append(el.getKey()).append(" | ").append(el.getValue()).append("\n");
        return new String(stringBuilder);
    }
}


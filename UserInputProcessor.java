
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;

public class UserInputProcessor {
    public void processUserInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите Фамилию имя отчество, дату рождения, номер телефона и пол  разделённые пробелом:");
        String userInput = scanner.nextLine();

        try {
            processUserInputFields(userInput);
        } catch (DataFormatException | IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        scanner.close();
    }

    private void processUserInputFields(String userInput) throws DataFormatException, IOException {
        String[] data = userInput.split(" ");
        //Обработка ошибки при неверном количестве вводимых данных
        if (data.length != 6) {
            throw new DataFormatException("Неверное количество вводимых данных.");
        }
        //Исключения формата вводимых данных
        int phoneRegex = 0;
        String lastName = data[0];
        String fullNameRegex = "^[A-Za-zА-Яа-я]+$";
        String dateOfBirthRegex = "^\\d{2}\\.\\d{2}\\.\\d{4}$";
        String genderRegex = "^[mf]$";

        //Проброс исключений с выводом сообщений пользователю об ошибках.

        if (!data[0].matches(fullNameRegex) || !data[1].matches(fullNameRegex) || !data[2].matches(fullNameRegex)) {
            throw new DataFormatException("Недопустимый формат для фамилии, имени или отчества.");
        }

        if (!data[3].matches(dateOfBirthRegex)) {
            throw new DataFormatException("Неверный формат даты рождения. Должен быть формат dd.mm.yyyy.");
        }

        if (phoneRegex < 0 || phoneRegex > 999999999) {
            throw new DataFormatException("Номер телефона должен содержать не более 9 цифр.");
        }

        if (!data[5].matches(genderRegex)) {
            throw new DataFormatException("Недопустимое значение для пола. Введите m для мужчины или f для женщины.");
        }

        System.out.println("Данные введены корректно.");
    
      // Запись данных в файл и обработка проблем с чтением-записью в файл
      try {
        FileWriter writer = new FileWriter(lastName + ".txt", true);
        for (String field : data) {
            writer.write(field + " ");
        }
        writer.write("\n");
        writer.close();
        System.out.println("Данные успешно сохранены в файл: " + lastName);
        
    } catch (IOException e) {
        System.err.println("Произошла ошибка при записи данных в файл: " + e.getMessage());
        e.printStackTrace();
        }
    }
}
    

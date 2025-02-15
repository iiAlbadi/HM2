import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Question {
    private int id;
    private String text;
    private List<Answer> answers;

    public Question(int id, String text) {
        this.id = id;
        this.text = text;
        this.answers = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public void removeAnswer(int answerId) {
        answers.removeIf(answer -> answer.getId() == answerId);
    }
}

class Answer {
    private int id;
    private String text;

    public Answer(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}

public class HW2App {
    private static List<Question> questions = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Add Question\n2. Add Answer to Question\n3. Remove Question\n4. Remove Answer\n5. Display Questions\n6. Exit");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addQuestion();
                    break;
                case "2":
                    addAnswerToQuestion();
                    break;
                case "3":
                    removeQuestion();
                    break;
                case "4":
                    removeAnswer();
                    break;
                case "5":
                    displayQuestions();
                    break;
                case "6":
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 6.");
            }
        }
    }

    private static void addQuestion() {
        System.out.print("Enter question ID (integer): ");
        String idInput = scanner.nextLine();
        if (!idInput.matches("\\d+")) {
            System.out.println("Invalid ID! Must be an integer.");
            return;
        }
        int id = Integer.parseInt(idInput);

        System.out.print("Enter question text: ");
        String text = scanner.nextLine().trim();
        if (text.isEmpty()) {
            System.out.println("Question text cannot be empty.");
            return;
        }

        questions.add(new Question(id, text));
        System.out.println("Question added successfully.");
    }

    private static void addAnswerToQuestion() {
        System.out.print("Enter question ID: ");
        String idInput = scanner.nextLine();
        if (!idInput.matches("\\d+")) {
            System.out.println("Invalid ID! Must be an integer.");
            return;
        }
        int questionId = Integer.parseInt(idInput);

        Question question = findQuestionById(questionId);
        if (question == null) {
            System.out.println("Question not found.");
            return;
        }

        System.out.print("Enter answer ID: ");
        String answerIdInput = scanner.nextLine();
        if (!answerIdInput.matches("\\d+")) {
            System.out.println("Invalid ID! Must be an integer.");
            return;
        }
        int answerId = Integer.parseInt(answerIdInput);

        System.out.print("Enter answer text: ");
        String answerText = scanner.nextLine().trim();
        if (answerText.isEmpty()) {
            System.out.println("Answer text cannot be empty.");
            return;
        }

        question.addAnswer(new Answer(answerId, answerText));
        System.out.println("Answer added successfully.");
    }

    private static void removeQuestion() {
        System.out.print("Enter question ID to remove: ");
        String idInput = scanner.nextLine();
        if (!idInput.matches("\\d+")) {
            System.out.println("Invalid ID! Must be an integer.");
            return;
        }
        int id = Integer.parseInt(idInput);

        questions.removeIf(q -> q.getId() == id);
        System.out.println("Question removed successfully.");
    }

    private static void removeAnswer() {
        System.out.print("Enter question ID: ");
        String questionIdInput = scanner.nextLine();
        if (!questionIdInput.matches("\\d+")) {
            System.out.println("Invalid ID! Must be an integer.");
            return;
        }
        int questionId = Integer.parseInt(questionIdInput);

        Question question = findQuestionById(questionId);
        if (question == null) {
            System.out.println("Question not found.");
            return;
        }

        System.out.print("Enter answer ID to remove: ");
        String answerIdInput = scanner.nextLine();
        if (!answerIdInput.matches("\\d+")) {
            System.out.println("Invalid ID! Must be an integer.");
            return;
        }
        int answerId = Integer.parseInt(answerIdInput);

        question.removeAnswer(answerId);
        System.out.println("Answer removed successfully.");
    }

    private static void displayQuestions() {
        if (questions.isEmpty()) {
            System.out.println("No questions available.");
            return;
        }
        for (Question q : questions) {
            System.out.println("Question " + q.getId() + ": " + q.getText());
            for (Answer a : q.getAnswers()) {
                System.out.println("  - Answer " + a.getId() + ": " + a.getText());
            }
        }
    }

    private static Question findQuestionById(int id) {
        for (Question q : questions) {
            if (q.getId() == id) {
                return q;
            }
        }
        return null;
    }
}

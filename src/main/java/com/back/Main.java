package com.back;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("LocalVariableUsedAndDeclaredInDifferentSwitchBranches")
public class Main {
    public static void main(String[] args) {
        System.out.println("== 명언 앱 ==");
        int id = 0;
        List<Quote> quotes = new ArrayList<>();

        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("명령) ");
            String command = sc.nextLine();

            switch (command) {
                case "등록":
                    ++id;
                    System.out.print("명언: ");
                    String quoteText = sc.nextLine();
                    System.out.print("작가: ");
                    String author = sc.nextLine();
                    quotes.add(new Quote(id, quoteText, author));
                    System.out.println(id + "번 명언이 등록되었습니다.");
                    continue;
                case "목록":
                    System.out.println("번호 / 작가 / 명언");
                    System.out.println("---------------------");
                    for (int i = quotes.size() - 1; i >= 0; --i) {
                        Quote q = quotes.get(i);
                        System.out.println(q.getId() + " / " + q.getAuthor() + " / " + q.getQuote());
                    }
                    continue;
                case "종료":
                    System.out.println("명언 앱을 종료합니다.");
                    return;
                case String cmd when cmd.startsWith("삭제"):
                    String[] parts = command.split("\\?");
                    if (parts.length >= 2 && parts[1].startsWith("id=")) {
                        String idPart = parts[1].substring(3);

                        int deleteId;
                        try {
                            deleteId = Integer.parseInt(idPart);
                        } catch (NumberFormatException var16) {
                            System.out.println("올바른 ID가 아닙니다.");
                            continue;
                        }

                        boolean found = false;

                        for (int i = 0; i < quotes.size(); ++i) {
                            if ((quotes.get(i)).getId() == deleteId) {
                                quotes.remove(i);
                                System.out.println(deleteId + "번 명언이 삭제되었습니다.");
                                found = true;
                                break;
                            }
                        }

                        if (!found) {
                            System.out.println(deleteId + "번 명언은 존재하지 않습니다.");
                        }
                        continue;
                    }

                    System.out.println("올바른 형식이 아닙니다. 예: 삭제?id=1");
                    continue;
                case String cmd when cmd.startsWith("수정"):
                    parts = command.split("\\?");
                    if (parts.length >= 2 && parts[1].startsWith("id=")) {
                        String idPart = parts[1].substring(3);

                        int modifyId;
                        try {
                            modifyId = Integer.parseInt(idPart);
                        } catch (NumberFormatException var15) {
                            System.out.println("올바른 ID가 아닙니다.");
                            continue;
                        }

                        Quote quoteToModify = null;

                        for (Quote q : quotes) {
                            if (q.getId() == modifyId) {
                                quoteToModify = q;
                                break;
                            }
                        }

                        if (quoteToModify == null) {
                            System.out.println(modifyId + "번 명언은 존재하지 않습니다.");
                        } else {
                            System.out.println("명언(기존): " + quoteToModify.getQuote());
                            String newQuoteText = sc.nextLine();
                            System.out.println("작가(기존): " + quoteToModify.getAuthor());
                            String newAuthor = sc.nextLine();
                            quoteToModify.setQuote(newQuoteText);
                            quoteToModify.setAuthor(newAuthor);
                            System.out.println(modifyId + "번 명언이 수정되었습니다.");
                        }
                        continue;
                    }

                    System.out.println("올바른 형식이 아닙니다. 예: 수정?id=1");
                    continue;
                default:
                    System.out.println("올바른 명령어가 아닙니다.");
                    continue;
            }
        }
    }
}

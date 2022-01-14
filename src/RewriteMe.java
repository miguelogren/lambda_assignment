
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/*
Inlämningsuppgit i kursen Funktionell Programmering för JAVA-programmet
För samtliga funktioner i denna fil, byt ut "throw UnSupportedException"-raden
och skriv ett pipeline-uttryck som returnerar det som ska returneras.
Streams MÅSTE användas i samtliga funktioner som lämnas in
För att testa om era funktioner funkar, kör testerna som hör till denna fil
För att bli godkänd på denna uppgift måste minst 7 av funktionerna vara implementerade.
Sigruns bedömning av denna uppgift kommer att gå till så att hon klipper in er version av denna fil
i sitt projekt och kör testerna.
Hennes kontroll om ni har klarat uppgifterna eller inte görs genom att
hon kollar att tillräckligt många av tester går gröna. Pga detta ska ni inte ändra i någon fil
medföljande detta projekt, och inte heller metodsignaturerna i denna fil eller era tester, eftersom
ni då riskerar att påverka rättningen i negativ riktning.
 */

public class RewriteMe {

    public Database database = new Database();
    public List<Question> questions = database.getQuestions();

    //Skriv en funktioner som returnerar hur många frågor det finns i databasen?
    public int getAmountOfQuestionsInDatabase(){
        return (int)questions.stream().count();
        // return questions.stream().mapToInt(q -> 1).sum();
        // return questions.size();

    }

    //Hur många frågor finns i databasen för en viss, given kategori (som ges som inparameter)
    public int getAmountOfQuestionsForACertainCategory(Category category){
        return (int)questions.stream().filter(q -> q.getCategory().equals(category)).count();

    }

    //Skapa en lista innehållandes samtliga frågesträngar i databasen
    public List<String> getListOfAllQuestions(){
        //return questions.stream().map(Question::getQuestionString).toList();
        return questions.stream().map(q -> q.getQuestionString()).toList();

    }

    //Skapa en lista innehållandes samtliga frågesträngar där frågan tillhör en viss kategori
    //Kategorin ges som inparameter
    public List<String> getAllQuestionStringsBelongingACategory(Category category){
        return questions.stream().filter(q -> q.getCategory().equals(category)).map(Question::getQuestionString).toList();
    }

    //Skapa en lista av alla svarsalternativ, där varje svarsalternativ får förekomma
    // en och endast en gång i den lista som du ska returnera
    public List<String> getAllAnswerOptionsDistinct(){
        return questions.stream().flatMap(q -> q.getAllAnswers().stream()).distinct().toList();
    }


    //Finns en viss sträng, given som inparameter, som svarsalternativ till någon fråga i vår databas?
    public boolean isThisAnAnswerOption(String answerCandidate){
        return questions.stream().flatMap(q -> q.getAllAnswers().stream()).filter(a -> a.equals(answerCandidate)).count()!=0;
        //return questions.stream().anyMatch(question -> question.getAllAnswers().contains(answerCandidate));

    }

    //Hur ofta förekommer ett visst svarsalternativ, givet som inparameter, i databasen
    public int getAnswerCandidateFrequncy(String answerCandidate){
        return (int)questions.stream().flatMap(q -> q.getAllAnswers().stream()).filter(a -> a.equals(answerCandidate)).count();

    }

    //Skapa en Map där kategorierna är nycklar och värdena är en lista
    //av de frågesträngar som tillhör varje kategori
    public Map<Category, List<String>> getQuestionGroupedByCategory(){
        //return questions.stream().collect(Collectors.groupingBy(Question::getCategory, Collectors.mapping(Question::getQuestionString, toList())));
        return questions.stream().collect(Collectors.toMap(k -> k.getCategory(), v -> new ArrayList<String>(Collections.singleton(v.getQuestionString())), (n1, n2) -> { List<String> boeg = new ArrayList<String>(); boeg.addAll(n1); boeg.addAll(n2); return boeg; }));
    }

    //names.stream().collect(Collectors.toMap(fff -> fff, n -> n.length())


    //Skapa en funktion som hittar det svarsalternativ som har flest bokstäver, i en kategori, given som inparameter
    // OBS: Du måste använda Reduce!
    public String getLongestLettercountAnwerInAGivenCategory(Category c)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    public static void main(String[] args){
        RewriteMe uppg4 = new RewriteMe();

        System.out.println(uppg4.getQuestionGroupedByCategory());

    }

}
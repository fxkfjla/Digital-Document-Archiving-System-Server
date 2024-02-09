package com.ddas.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ddas.model.domain.File;
import com.ddas.model.domain.Tag;
import com.ddas.model.domain.User;
import com.ddas.model.domain.UserRole;
import com.ddas.repository.FileRepository;
import com.ddas.repository.TagRepository;
import com.ddas.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class DataLoader implements ApplicationRunner
{
    @Transactional
    public void run(ApplicationArguments args)
    {
        User user1 = new User("user@test.com", passwordEncoder.encode("12345678*iI"), UserRole.USER);
        User user2 = new User("user@test2.com", passwordEncoder.encode("12345678*iI"), UserRole.USER);
        userRepository.save(user1);
        userRepository.save(user2);

        List<byte[]> filesData = new ArrayList<byte[]>();
        java.io.File documentsFolder = new java.io.File("documents");
        java.io.File[] files = documentsFolder.listFiles();

        for(java.io.File file : files) {
            filesData.add(readFileData(file.getAbsolutePath()));
        }

        Set<Tag> fileTags = new HashSet<>();
        Tag tag1 = new Tag("ANALIZA");
        Tag tag2 = new Tag("PRZYSZŁOŚĆ");
        fileTags.add(tag1);
        fileTags.add(tag2);
        tagRepository.save(tag1);
        tagRepository.save(tag2);
        File file1 = new File
        (
            user1, 
            "Trendy_globalne_2024", 
            "Kompleksowa analiza przewidywanych trendów gospodarczych i społecznych na świecie w roku 2024.", 
            filesData.get(0), 
            (long) filesData.get(0).length, 
            fileTags 
        );

        fileTags = new HashSet<>();
        tag1 = new Tag("ZDROWIE");
        tag2 = new Tag("STATYSTYKI");
        fileTags.add(tag1);
        fileTags.add(tag2);
        tagRepository.save(tag1);
        tagRepository.save(tag2);
        File file2 = new File
        (
            user1, 
            "Raport_zdrowotny_roczny", 
            "Roczne zestawienie danych i statystyk dotyczących stanu zdrowia populacji, wraz z rekomendacjami dla systemów opieki zdrowotnej.", 
            filesData.get(1), 
            (long) filesData.get(1).length, 
            fileTags 
        );

        fileTags = new HashSet<>();
        tag1 = new Tag("TECHNOLOGIA");
        tag2 = new Tag("INNOWACJE");
        fileTags.add(tag1);
        fileTags.add(tag2);
        tagRepository.save(tag1);
        tagRepository.save(tag2);
        File file3 = new File
        (
            user1, 
            "Przegląd_innowacji_tech", 
            "Zbiór najnowszych osiągnięć i trendów w branży technologicznej, z naciskiem na innowacyjne start-upy i przyszłe technologie.", 
            filesData.get(2), 
            (long) filesData.get(2).length, 
            fileTags 
        );

        fileTags = new HashSet<>();
        tag1 = new Tag("FINANSE");
        tag2 = new Tag("TRENDY");
        fileTags.add(tag1);
        fileTags.add(tag2);
        tagRepository.save(tag1);
        tagRepository.save(tag2);
        File file4 = new File
        (
            user1, 
            "Analiza_rynku_i_kwartał", 
            "Dokładna analiza rynkowa pierwszego kwartału, zawierająca dane o wynikach finansowych i trendach konsumenckich.", 
            filesData.get(3), 
            (long) filesData.get(3).length, 
            fileTags
        );

        fileTags = new HashSet<>();
        tag1 = new Tag("EKOLOGIA");
        tag2 = new Tag("STRATEGIA");
        fileTags.add(tag1);
        fileTags.add(tag2);
        tagRepository.save(tag1);
        tagRepository.save(tag2);
        File file5 = new File
        (
            user1, 
            "Plan_zrównoważonego_rozwoju", 
            "Dokument strategii zrównoważonego rozwoju, podkreślający ekologiczne inicjatywy i długoterminowe cele środowiskowe.", 
            filesData.get(4), 
            (long) filesData.get(4).length, 
            fileTags
        );

        fileTags = new HashSet<>();
        tag1 = new Tag("PRAWO");
        tag2 = new Tag("REGULACJE");
        fileTags.add(tag1);
        fileTags.add(tag2);
        tagRepository.save(tag1);
        tagRepository.save(tag2);
        File file6 = new File
        (
            user1, 
            "Aktualizacja_ramy_prawnej", 
            "Omówienie najnowszych zmian w przepisach prawnych, ze szczególnym uwzględnieniem nowych regulacji i ich wpływu na działalność biznesową.", 
            filesData.get(5), 
            (long) filesData.get(5).length, 
            fileTags
        );

        fileTags = new HashSet<>();
        tag1 = new Tag("PODRÓŻE");
        tag2 = new Tag("BEZPIECZEŃSTWO");
        fileTags.add(tag1);
        fileTags.add(tag2);
        tagRepository.save(tag1);
        tagRepository.save(tag2);
        File file7 = new File
        (
            user1, 
            "Wytyczne_podróży_2024", 
            "Przewodnik po aktualnych wytycznych dotyczących podróżowania, uwzględniający zmieniające się przepisy bezpieczeństwa i zalecenia zdrowotne.", 
            filesData.get(6), 
            (long) filesData.get(6).length, 
            fileTags
        );

        fileTags = new HashSet<>();
        tag1 = new Tag("SZKOLENIE");
        tag2 = new Tag("ROZWÓJ");
        fileTags.add(tag1);
        fileTags.add(tag2);
        tagRepository.save(tag1);
        tagRepository.save(tag2);
        File file8 = new File
        (
            user1, 
            "Podręcznik_szkolenia_pracowników", 
            "Kompleksowy przewodnik po metodach szkolenia i rozwoju pracowników, z naciskiem na budowanie kompetencji i zwiększanie efektywności zespołu.", 
            filesData.get(7), 
            (long) filesData.get(7).length, 
            fileTags
        );

        fileTags = new HashSet<>();
        tag1 = tagRepository.findByName("ANALIZA").get();
        tag2 = new Tag("KLIENT");
        fileTags.add(tag1);
        fileTags.add(tag2);
        tagRepository.save(tag2);
        File file9 = new File
        (
            user1, 
            "Podsumowanie_opinii_klientów", 
            "Analiza opinii i feedbacku klientów, dostarczająca wglądu w potrzeby i oczekiwania klientów oraz sugerująca możliwe obszary poprawy.", 
            filesData.get(8), 
            (long) filesData.get(8).length, 
            fileTags
        );

        fileTags = new HashSet<>();
        tag1 = new Tag("PROJEKT");
        tag2 = tagRepository.findByName("INNOWACJE").get();
        fileTags.add(tag1);
        fileTags.add(tag2);
        tagRepository.save(tag1);
        tagRepository.save(tag2);
        File file10 = new File
        (
            user1, 
            "Propozycja_projektu_omega", 
            "Szczegółowy opis innowacyjnego projektu Omega, prezentujący jego cele, potencjalne korzyści oraz zaplanowane kroki implementacyjne", 
            filesData.get(9), 
            (long) filesData.get(9).length, 
            fileTags
        );

        fileTags = new HashSet<>();
        tag1 = tagRepository.findByName("TECHNOLOGIA").get();
        tag2 = tagRepository.findByName("BEZPIECZEŃSTWO").get();
        fileTags.add(tag1);
        fileTags.add(tag2);
        tagRepository.save(tag1);
        tagRepository.save(tag2);
        File file11 = new File
        (
            user1, 
            "Raport_cyberbezpieczeństwa", 
            "Przegląd aktualnych zagrożeń w cyberprzestrzeni, zawierający analizę recentnych ataków i strategie zapobiegania zagrożeniom cyfrowym.", 
            filesData.get(10), 
            (long) filesData.get(10).length, 
            fileTags
        );

        fileTags = new HashSet<>();
        tag1 = new Tag("ORGANIZACJA");
        tag2 = new Tag("EVENT");
        fileTags.add(tag1);
        fileTags.add(tag2);
        tagRepository.save(tag1);
        tagRepository.save(tag2);
        File file12 = new File
        (
            user1, 
            "Poradnik_planowania_wydarzeń", 
            "Kompletny przewodnik po organizacji i zarządzaniu wydarzeniami, od konferencji po imprezy firmowe, z naciskiem na efektywność i innowacyjność.", 
            filesData.get(11), 
            (long) filesData.get(11).length, 
            fileTags
        );

        fileTags = new HashSet<>();
        tag1 = tagRepository.findByName("FINANSE").get();
        tag2 = tagRepository.findByName("ANALIZA").get();
        fileTags.add(tag1);
        fileTags.add(tag2);
        tagRepository.save(tag1);
        tagRepository.save(tag2);
        File file13 = new File
        (
            user1, 
            "Analiza_ekonomiczna_2024", 
            "Szczegółowa analiza ekonomiczna obecnych trendów rynkowych, wpływu globalnych wydarzeń na gospodarkę i prognozy na przyszły rok.", 
            filesData.get(12), 
            (long) filesData.get(12).length, 
            fileTags
        );

        fileTags = new HashSet<>();
        tag1 = new Tag("MARKETING");
        tag2 = tagRepository.findByName("STRATEGIA").get();
        fileTags.add(tag1);
        fileTags.add(tag2);
        tagRepository.save(tag1);
        tagRepository.save(tag2);
        File file14 = new File
        (
            user2, 
            "Strategia_marketingowa_XYZ", 
            "Zestawienie strategicznych kierunków i działań marketingowych firmy XYZ, mających na celu wzmocnienie pozycji rynkowej i zwiększenie sprzedaży.", 
            filesData.get(13), 
            (long) filesData.get(13).length, 
            fileTags
        );

        fileTags = new HashSet<>();
        tag1 = tagRepository.findByName("FINANSE").get();
        tag2 = new Tag("RAPORT");
        fileTags.add(tag1);
        fileTags.add(tag2);
        tagRepository.save(tag1);
        tagRepository.save(tag2);
        File file15 = new File
        (
            user2, 
            "Roczne_sprawozdanie_finansowe", 
            "Podsumowanie finansowe ostatniego roku, zawierające analizę przychodów, kosztów i zysków firmy, wraz z kluczowymi wskaźnikami finansowymi.", 
            filesData.get(14), 
            (long) filesData.get(14).length, 
            fileTags
        );

        fileTags = new HashSet<>();
        tag1 = new Tag("PRACOWNICY");
        tag2 = tagRepository.findByName("ANALIZA").get();
        fileTags.add(tag1);
        fileTags.add(tag2);
        tagRepository.save(tag1);
        tagRepository.save(tag2);
        File file16 = new File
        (
            user2, 
            "Badanie_satysfakcji_pracowników", 
            "Wyniki badania satysfakcji pracowników, z analizą głównych czynników wpływających na zadowolenie z pracy i środowiska pracy.", 
            filesData.get(15), 
            (long) filesData.get(15).length, 
            fileTags
        );

        fileTags = new HashSet<>();
        tag1 = new Tag("MODA");
        tag2 = tagRepository.findByName("TRENDY").get();
        fileTags.add(tag1);
        fileTags.add(tag2);
        tagRepository.save(tag1);
        tagRepository.save(tag2);
        File file17 = new File
        (
            user2, 
            "Przewodnik_po_trendach_modowych", 
            "Przegląd najnowszych trendów modowych, analiza rynku mody i prognozy zmian na nadchodzący sezon.", 
            filesData.get(16), 
            (long) filesData.get(16).length, 
            fileTags
        );

        fileTags = new HashSet<>();
        tag1 = new Tag("URBANISTYKA");
        tag2 = tagRepository.findByName("STRATEGIA").get();
        fileTags.add(tag1);
        fileTags.add(tag2);
        tagRepository.save(tag1);
        tagRepository.save(tag2);
        File file18 = new File
        (
            user2, 
            "Plan_rozwoju_infrastruktury", 
            "Strategiczny plan rozwoju infrastruktury miejskiej i regionalnej, obejmujący zarówno krótko-, jak i długoterminowe projekty.", 
            filesData.get(17), 
            (long) filesData.get(17).length, 
            fileTags
        );

        fileTags = new HashSet<>();
        tag1 = tagRepository.findByName("EKOLOGIA").get();
        tag2 = tagRepository.findByName("RAPORT").get();
        fileTags.add(tag1);
        fileTags.add(tag2);
        tagRepository.save(tag1);
        tagRepository.save(tag2);
        File file19 = new File
        (
            user2, 
            "Raport_o_stanie_środowiska", 
            "Dokument przedstawiający aktualny stan środowiska naturalnego, wskazujący na kluczowe wyzwania ekologiczne i możliwe ścieżki działań.", 
            filesData.get(18), 
            (long) filesData.get(18).length, 
            fileTags
        );

        fileTags = new HashSet<>();
        tag1 = new Tag("GLOBALIZACJA");
        tag2 = tagRepository.findByName("FINANSE").get();
        fileTags.add(tag1);
        fileTags.add(tag2);
        tagRepository.save(tag1);
        tagRepository.save(tag2);
        File file20 = new File
        (
            user2, 
            "Analiza_rynków_zagranicznych", 
            "Szczegółowa analiza trendów i możliwości na rynkach zagranicznych, ze szczególnym uwzględnieniem nowych rynków i sektorów.", 
            filesData.get(19), 
            (long) filesData.get(19).length, 
            fileTags
        );

        fileTags = new HashSet<>();
        tag1 = new Tag("PRODUKT");
        tag2 = tagRepository.findByName("INNOWACJE").get();
        fileTags.add(tag1);
        fileTags.add(tag2);
        tagRepository.save(tag1);
        tagRepository.save(tag2);
        File file21 = new File
        (
            user2, 
            "Strategia_rozwoju_produktu", 
            "Opis planu rozwoju nowych produktów, uwzględniający badania rynku, innowacje produktowe i strategię wprowadzania na rynek.", 
            filesData.get(20), 
            (long) filesData.get(20).length, 
            fileTags
        );

        fileTags = new HashSet<>();
        tag1 = new Tag("ENERGIA");
        tag2 = tagRepository.findByName("STATYSTYKI").get();
        fileTags.add(tag1);
        fileTags.add(tag2);
        tagRepository.save(tag1);
        tagRepository.save(tag2);
        File file22 = new File
        (
            user2, 
            "Bilans_energetyczny_kraju", 
            "Raport na temat bilansu energetycznego kraju, analiza źródeł energii, ich wykorzystania oraz wpływu na środowisko i gospodarkę.", 
            filesData.get(21), 
            (long) filesData.get(21).length, 
            fileTags
        );

        fileTags = new HashSet<>();
        tag1 = tagRepository.findByName("FINANSE").get();
        tag2 = new Tag("RYZYKO");
        fileTags.add(tag1);
        fileTags.add(tag2);
        tagRepository.save(tag1);
        tagRepository.save(tag2);
        File file23 = new File
        (
            user2, 
            "Ocena_ryzyka_inwestycyjnego", 
            "Analiza ryzyk związanych z inwestycjami, wskazówki dotyczące zarządzania ryzykiem i strategie minimalizowania potencjalnych strat.", 
            filesData.get(22), 
            (long) filesData.get(22).length, 
            fileTags
        );

        fileTags = new HashSet<>();
        tag1 = new Tag("DEMOGRAFIA");
        tag2 = tagRepository.findByName("STATYSTYKI").get();
        fileTags.add(tag1);
        fileTags.add(tag2);
        tagRepository.save(tag1);
        tagRepository.save(tag2);
        File file24 = new File
        (
            user2, 
            "Raport_demograficzny_2024", 
            "Szczegółowy przegląd trendów demograficznych, analiza zmian w strukturze wiekowej populacji i ich potencjalny wpływ na społeczeństwo.", 
            filesData.get(23), 
            (long) filesData.get(23).length, 
            fileTags
        );

        fileTags = new HashSet<>();
        tag1 = new Tag("EDUKACJA");
        tag2 = tagRepository.findByName("PRZYSZŁOŚĆ").get();
        fileTags.add(tag1);
        fileTags.add(tag2);
        tagRepository.save(tag1);
        tagRepository.save(tag2);
        File file25 = new File
        (
            user2, 
            "Strategia_edukacyjna_na_2024", 
            "Plan rozwoju sektora edukacji na nadchodzący rok, z naciskiem na innowacje w nauczaniu i adaptację do zmieniających się potrzeb.", 
            filesData.get(24), 
            (long) filesData.get(24).length, 
            fileTags
        );

        fileTags = new HashSet<>();
        tag1 = new Tag("SZTUCZNA_INTELIGENCJA");
        tag2 = tagRepository.findByName("TECHNOLOGIA").get();
        fileTags.add(tag1);
        fileTags.add(tag2);
        tagRepository.save(tag1);
        tagRepository.save(tag2);
        File file26 = new File
        (
            user2, 
            "Przegląd_rozwoju_AI", 
            "Analiza postępów w dziedzinie sztucznej inteligencji, omówienie najnowszych osiągnięć i prognozy dotyczące przyszłego rozwoju AI.", 
            filesData.get(25), 
            (long) filesData.get(25).length, 
            fileTags
        );

        fileRepository.save(file1);
        fileRepository.save(file2);
        fileRepository.save(file3);
        fileRepository.save(file4);
        fileRepository.save(file5);
        fileRepository.save(file6);
        fileRepository.save(file7);
        fileRepository.save(file8);
        fileRepository.save(file9);
        fileRepository.save(file10);
        fileRepository.save(file11);
        fileRepository.save(file12);
        fileRepository.save(file13);
        fileRepository.save(file14);
        fileRepository.save(file15);
        fileRepository.save(file16);
        fileRepository.save(file17);
        fileRepository.save(file18);
        fileRepository.save(file19);
        fileRepository.save(file20);
        fileRepository.save(file21);
        fileRepository.save(file22);
        fileRepository.save(file23);
        fileRepository.save(file24);
        fileRepository.save(file25);
        fileRepository.save(file26);
    }

     private byte[] readFileData(String filePath)
     {
        try
        {
            Path path = Paths.get(filePath);
            return Files.readAllBytes(path);
        } 
        catch (IOException e)
        {
            throw new RuntimeException("Failed to read file: " + filePath, e);
        }
    }

    private final UserRepository userRepository;
    private final FileRepository fileRepository;
    private final TagRepository tagRepository;
    private final PasswordEncoder passwordEncoder;
}


package pl.km.exercise261;

public class InitialDataProvider {

    public static void provideInitialData(RecipeRepository recipeRepository,
                                          RecipeCategoryRepository recipeCategoryRepository) {
        RecipeCategory category1 = new RecipeCategory("Śniadanie", "Coś pożywnego na dobry początek");
        RecipeCategory category2 = new RecipeCategory("Obiad", "Warto zrobić przerwę :)");
        RecipeCategory category3 = new RecipeCategory("Kolacja", "Ostatni posiłek przed nocną regeneracją");
        RecipeCategory category4 = new RecipeCategory("Dania mięsne", "Strefa mięsożerców");
        RecipeCategory category5 = new RecipeCategory("Ryby i owoce morza", "\"co pływa - nie utonie\"");
        RecipeCategory category6 = new RecipeCategory("Dania vegańskie", "Zielone znaczy jedz ...");
        recipeCategoryRepository.save(category1);
        recipeCategoryRepository.save(category2);
        recipeCategoryRepository.save(category3);
        recipeCategoryRepository.save(category4);
        recipeCategoryRepository.save(category5);
        recipeCategoryRepository.save(category6);

        Ingredient ingredient1 = new Ingredient("jaja", "3 szt.");
        Ingredient ingredient2 = new Ingredient("masło", "ok. 1 łyżeczka");

        Recipe recipe = new Recipe("Jajecznica babci Danusi", 15,
                "Rozgrzać patelnię. Dodać" +
                        " masło i poczekać aż się rozpuści. Dodać jajka (żółtka powinny być w całości). Można odrobinę posolić." +
                        " Ścinać białko - należy od czasu do czasu odrywać ścięte białko od powierzchni patelni " +
                        "(by się nie przypaliło) jednocześnie omijając żółtka. Gdy białko zetnie się całkowicie należy " +
                        "rozbełtać żółtka po całości i od razu zdjąć z patelni na talerz. Otrzymassz w ten sposób pyszną," +
                        " delikatną jajecznicę o kremowej konsystencji :) - SMACZNEGO");
        recipe.addCategory(category1);
        recipe.addCategory(category3);
        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.setLikesCounter(7);
        recipeRepository.save(recipe);

        Ingredient ingredient11 = new Ingredient("boczek surowy", "ok 1 kg");
        Ingredient ingredient12 = new Ingredient("cebula", "ok 1 kg");
        Ingredient ingredient13 = new Ingredient("czosnek", "1 główka");
        Ingredient ingredient14 = new Ingredient("ziele angielksie", "4 szt.");
        Ingredient ingredient15 = new Ingredient("liść laurowy", "4 szt.");
        Ingredient ingredient16 = new Ingredient("papryka słodka w proszku", "2 łyżki");
        Ingredient ingredient17 = new Ingredient("masło", "50g");
        Ingredient ingredient18 = new Ingredient("olej", "2 łyżki");

        Recipe recipe1 = new Recipe("Pan Boczek", 75,
                "Boczek pokroić na plastry ok 1 cm. Cebulę posiekać wg uznania (ja lubię piórka). " +
                        "Czosnek obrać i pokroić wg uznania. Rozgrzać duży rondel, wrzucić masło a następnie cebulę." +
                        "Smażyć mieszająć aż zacznie się lekko przypalać. Dodać sól i pieprz do smaku, liście laurowe," +
                        " zielę angielskie oraz czosnek. W razie potrzeby dodać odrobinę wody. Przykryć i dusić na" +
                        " małym ogniu. Rozgrzać olej na patelni posolić plastry boczku i usmażyć na złoto po obu " +
                        "stronach. Od czasu do czasu kontrolować stan cebuli (powinna się mocno skurczyć i udusić," +
                        " ale nie przypalić. Gdy cebula będzie miękka dodać paprykę słodką wymieszać i rozłożyć na " +
                        "płaskim naczyniu żaroodpornym. Położyć na niej plastry boczku i piec przez ok 20 minut w " +
                        "rozgrzanym piekarniku do ok 180 stopni. Jak lubisz spieczone to nie przykrywaj. Jak wolisz" +
                        " \"na miękko\" to przykryj folią/pokrywką lub zanuż boczek w cebuli, by nie wystawał." +
                        " Generalnie danie obiadowe, ale nic nie stoi na przeszkodzie by zarzucić Pana Boczka na " +
                        "kolację lub śniadanie w zimowy dzień :) - SMACZNEGO. ");
        recipe1.setLikesCounter(10);
        recipe1.addCategory(category2);
        recipe1.addCategory(category4);
        recipe1.addIngredient(ingredient11);
        recipe1.addIngredient(ingredient12);
        recipe1.addIngredient(ingredient13);
        recipe1.addIngredient(ingredient14);
        recipe1.addIngredient(ingredient15);
        recipe1.addIngredient(ingredient16);
        recipe1.addIngredient(ingredient17);
        recipe1.addIngredient(ingredient18);
        recipeRepository.save(recipe1);

        Ingredient ingredient21 = new Ingredient("oliwki", "200 gram");
        Ingredient ingredient22 = new Ingredient("czosnek", "1 ząbek");
        Ingredient ingredient23 = new Ingredient("oliwa z oliwek", "3 łyżki");

        Recipe recipe2 = new Recipe("Pasta z oliwek", 10,
                "Wydrylowane oliwki (zielone lub czarne wg. uznania) odsączyć z zalewy i wrzucić do niewielkiej miski." +
                        "Dodać wyciśnięty czosnek i zmiksować blenderem na gładką pastę dodając oliwę z oliwek. Podawać" +
                        "do pieczywa, grzanek lub jako dodatek do dań");
        recipe2.setLikesCounter(4);
        recipe2.addCategory(category1);
        recipe2.addCategory(category3);
        recipe2.addCategory(category6);
        recipe2.addIngredient(ingredient21);
        recipe2.addIngredient(ingredient22);
        recipe2.addIngredient(ingredient23);
        recipeRepository.save(recipe2);

        Ingredient ingredient31 = new Ingredient("Łosoś", "dzwonek lub dwa");
        Recipe recipe3 = new Recipe("Steki z łososia z grilla", 12,
                "Dwonki z łososia posolić i popieprzyć do smaku. Pamiętaj, by przed grillowaniem " +
                        "wyjąć rybę z lodówki na tyle wcześnie, by łosoś był w temperaturze pokojowej. Grilluj" +
                        " przez 4-5 minut z każdej strony i gotowe :). Można podawać z warzywami z grilla. " +
                        "Smacznego :)");
        recipe3.setLikesCounter(3);
        recipe3.addIngredient(ingredient31);
        recipe3.addCategory(category2);
        recipe3.addCategory(category3);
        recipe3.addCategory(category5);
        recipeRepository.save(recipe3);

        Ingredient ingredient41 = new Ingredient("pieczywo", "");
        Ingredient ingredient42 = new Ingredient("hummus", "");
        Recipe recipe4 = new Recipe("Kanapka z hummusem", 2, "" +
                "Posmaruj pieczywo hummusem i zrobione :)");
        recipe4.setLikesCounter(2);
        recipe4.addIngredient(ingredient41);
        recipe4.addIngredient(ingredient42);
        recipe4.addCategory(category1);
        recipe4.addCategory(category3);
        recipe4.addCategory(category6);
        recipeRepository.save(recipe4);

        Ingredient ingredient51 = new Ingredient("pieczywo", "");
        Ingredient ingredient52 = new Ingredient("masło", "5 g.");
        Ingredient ingredient53 = new Ingredient("bryndza", "20 g.");
        Recipe recipe5 = new Recipe("Kanapka z bryndzą", 4,
                "Pieczywo posmaruj masłem a następnie rozsmaruj na nim bryndzę. Jak lubisz to" +
                        " posyp szczypiorkiem dodaj pomidora itd (choć uważam, że bryndza jest" +
                        " genialna sama w sobie :)).");
        recipe5.addIngredient(ingredient51);
        recipe5.addIngredient(ingredient52);
        recipe5.addIngredient(ingredient53);
        recipe5.setLikesCounter(6);
        recipe5.addCategory(category1);
        recipe5.addCategory(category3);
        recipeRepository.save(recipe5);
    }
}

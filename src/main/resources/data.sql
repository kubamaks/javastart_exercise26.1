INSERT INTO recipe_category (name, description)
VALUES ('Śniadanie', 'Coś pożywnego na dobry początek'),
       ('Obiad', 'Warto zrobić przerwę :)'),
       ('Kolacja', 'Ostatni posiłek przed nocną regeneracją'),
       ('Dania mięsne', 'Strefa mięsożerców'),
       ('Ryby i owoce morza', '"co pływa - nie utonie"'),
       ('Dania vegańskie', 'Zielone znaczy jedz ...');

INSERT INTO recipe (name, preparation_time, likes_counter, description)
VALUES ('Jajecznica babci Danusi', 15, 7,
        'Rozgrzać patelnię. Dodać masło i poczekać aż się rozpuści. Dodać jajka (żółtka powinny być w całości). ' ||
        'Można odrobinę posolić. Ścinać białko - należy od czasu do czasu odrywać ścięte białko od powierzchni patelni ' ||
        '(by się nie przypaliło) jednocześnie omijając żółtka. Gdy białko zetnie się całkowicie należy rozbełtać ' ||
        'żółtka po całości i od razu zdjąć z patelni na talerz. Otrzymassz w ten sposób pyszną, delikatną jajecznicę o ' ||
        'kremowej konsystencji :) - SMACZNEGO'),
       ('Pan Boczek', 75, 10,
        'Boczek pokroić na plastry ok 1 cm. Cebulę posiekać wg uznania (ja lubię piórka). Czosnek obrać i pokroić ' ||
        'wg uznania. Rozgrzać duży rondel, wrzucić masło a następnie cebulę.Smażyć mieszająć aż zacznie się lekko ' ||
        'przypalać. Dodać sól i pieprz do smaku, liście laurowe, zielę angielskie oraz czosnek. W razie potrzeby ' ||
        'dodać odrobinę wody. Przykryć i dusić na małym ogniu. Rozgrzać olej na patelni posolić plastry boczku ' ||
        'i usmażyć na złoto po obu stronach. Od czasu do czasu kontrolować stan cebuli (powinna się mocno skurczyć ' ||
        'i udusić, ale nie przypalić. Gdy cebula będzie miękka dodać paprykę słodką wymieszać i rozłożyć na płaskim ' ||
        'naczyniu żaroodpornym. Położyć na niej plastry boczku i piec przez ok 20 minut w rozgrzanym piekarniku ' ||
        'do ok 180 stopni. Jak lubisz spieczone to nie przykrywaj. Jak wolisz "na miękko" to przykryj folią/pokrywką ' ||
        'lub zanuż boczek w cebuli, by nie wystawał. Generalnie danie obiadowe, ale nic nie stoi na przeszkodzie ' ||
        'by zarzucić Pana Boczka na kolację lub śniadanie w zimowy dzień :) - SMACZNEGO.'),
       ('Pasta z oliwek', 10, 4,
        'Wydrylowane oliwki (zielone lub czarne wg. uznania) odsączyć z zalewy i wrzucić do niewielkiej miski.Dodać ' ||
        'wyciśnięty czosnek i zmiksować blenderem na gładką pastę dodając oliwę z oliwek. Podawaćdo pieczywa,' ||
        ' grzanek lub jako dodatek do dań'),
       ('Steki z łososia z grilla', 12, 3,
        'Dwonki z łososia posolić i popieprzyć do smaku. Pamiętaj, by przed grillowaniem wyjąć rybę z lodówki ' ||
        'na tyle wcześnie, by łosoś był w temperaturze pokojowej. Grilluj przez 4-5 minut z każdej strony i gotowe :). ' ||
        'Można podawać z warzywami z grilla. Smacznego :)'),
       ('Kanapka z hummusem', 2, 2, 'Posmaruj pieczywo hummusem i zrobione :)'),
       ('Kanapka z bryndzą', 4, 6,
        'Pieczywo posmaruj masłem a następnie rozsmaruj na nim bryndzę.' ||
        ' Jak lubisz to posyp szczypiorkiem dodaj pomidora itd (choć uważam, że bryndza jest genialna sama w sobie :)).');

INSERT INTO ingredient (name, quantity, recipe_id)
VALUES ('jaja', '3 szt.', 1),
       ('masło','ok. 1 łyżeczka', 1),
       ('boczek surowy', 'ok 1 kg', 2),
       ('cebula', 'ok 1 kg', 2),
       ('czosnek', '1 główka', 2),
       ('ziele angielskie', '4 szt.', 2),
       ('liść laurowy', '4 szt.', 2),
       ('papryka słodka w proszku', '2 łyżki', 2),
       ('masło', '50g', 2),
       ('olej', '2 łyżki', 2),
       ('oliwki', '200g', 3),
       ('czosnek', '1 ząbek', 3),
       ('oliwa z oliwek', '3 łyżki', 3),
       ('Łosoś', 'dzwonek lub dwa', 4),
       ('pieczywo', '', 5),
       ('hummus', '',5),
       ('pieczywo', '', 6),
       ('masło', '5g', 6),
       ('bryndza', '20g', 6);

INSERT INTO recipe_categories (recipe_id, categories_id)
VALUES (1, 1),
       (1, 3),
       (2, 2),
       (2, 4),
       (3, 1),
       (3, 3),
       (3, 6),
       (4, 2),
       (4, 3),
       (4, 5),
       (5, 1),
       (5, 3),
       (5, 6),
       (6, 1),
       (6, 3);



package ru.tinkoff.qa.dbtests;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.tinkoff.qa.hibernate.BeforeCreator;
import ru.tinkoff.qa.hibernate.HibernateSessionFactoryCreator;
import ru.tinkoff.qa.hibernate.models.Animal;
import ru.tinkoff.qa.hibernate.models.Places;
import ru.tinkoff.qa.hibernate.models.Workman;
import ru.tinkoff.qa.hibernate.models.Zoo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ZooHibernateTests {

    Session session;
    @BeforeAll
    static void init() {
        BeforeCreator.createData();
    }

    @BeforeEach
    public void beforeEach() {
        session = HibernateSessionFactoryCreator.createSessionFactory().openSession();
    }
    /**
     * В таблице public.animal ровно 10 записей
     */
    @Test
    public void countRowAnimal() {
        long count = session.createQuery("SELECT A.id FROM Animal A", Animal.class).stream().count();
        Assertions.assertEquals(10, count, "Check count row in public.animal");
    }

    /**
     * В таблицу public.animal нельзя добавить строку с индексом от 1 до 10 включительно
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    public void insertIndexAnimal(int id) {
        session.createQuery("INSERT INTO Animal (id, name, age, type, sex, place) " +
                "VALUES (" + id + ", \"Name\", 5, 2, 1, null)");
        String expectedName = session.find(Animal.class, id).getName();
        Assertions.assertNotEquals("Name", expectedName, "Check insert animal with id = " + id);
    }

    /**
     * В таблицу public.workman нельзя добавить строку с name = null
     */
    @Test
    public void insertNullToWorkman() {
        Workman workman = new Workman();
        workman.setId(99);
        workman.setName(null);
        workman.setAge(37);
        workman.setPositions(3);
        Assertions.assertThrows(jakarta.persistence.PersistenceException.class,
                () -> session.persist(workman),
                "Check insert workman with name = null");
    }

    /**
     * Если в таблицу public.places добавить еще одну строку, то в ней будет 6 строк
     */
    @Test
    public void insertPlacesCountRow() {
        Places place = new Places();
        place.setId(6);
        place.setRow(3);
        place.setPlaceNum(345);
        place.setName("Загон 6");
        session.beginTransaction();
        session.persist(place);
        session.getTransaction().commit();
        long count = session.createQuery("SELECT P.id FROM Places P", Places.class).stream().count();
        Assertions.assertEquals(6, count, "Check count rows in public.places after add one row");
    }

    /**
     * В таблице public.zoo всего три записи с name 'Центральный', 'Северный', 'Западный'
     */
    @Test
    public void countRowZoo() {
        Set<String> expectedZoo = new HashSet<>();
        expectedZoo.add("Центральный");
        expectedZoo.add("Северный");
        expectedZoo.add("Западный");

        Set<String> actualZoo = new HashSet<>();
        List<Zoo> nameZoo = session.createQuery("FROM Zoo Z", Zoo.class).list();
        for (Zoo zoo : nameZoo) {
            actualZoo.add(zoo.getName());
        }
        Assertions.assertEquals(expectedZoo, actualZoo, "Check count and names zoo");
    }
    @AfterEach
    public void afterEach() {
        session.close();
    }
}

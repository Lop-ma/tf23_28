package ru.tinkoff.qa.hibernate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BeforeCreator {
    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:mem:myDb;DB_CLOSE_DELAY=-1", "sa", "sa");
    }

    public static void createData(){
        try {
            executeUpdate("CREATE TABLE public.places (\n"
                    + "\tid int4 NOT NULL,\n"
                    + "\t\"row\" int2 NULL,\n"
                    + "\tplace_num int4 NULL,\n"
                    + "\t\"name\" varchar NULL\n"
                    + ");");
            executeUpdate("ALTER TABLE public.places ADD CONSTRAINT places_pk PRIMARY KEY (id);");
            executeUpdate("CREATE TABLE public.sex (\n"
                    + "\tid int4 NOT NULL,\n"
                    + "\t\"name\" varchar NULL,\n"
                    + "\tCONSTRAINT sex_pk PRIMARY KEY (id)\n"
                    + ");");
            executeUpdate("CREATE TABLE public.\"types\" (\n"
                    + "\tid int4 NOT NULL,\n"
                    + "\t\"name\" varchar NULL,\n"
                    + "\tCONSTRAINT types_pk PRIMARY KEY (id)\n"
                    + ");");
            executeUpdate("CREATE TABLE public.animal (\n"
                    + "\tid int4 NOT NULL,\n"
                    + "\t\"name\" varchar NULL,\n"
                    + "\tage int4 NULL,\n"
                    + "\t\"type\" int4 NULL,\n"
                    + "\tsex int4 NULL,\n"
                    + "\tplace int4 NULL,\n"
                    + "\tCONSTRAINT animal_pk PRIMARY KEY (id),\n"
                    + "\tCONSTRAINT animal_fk FOREIGN KEY (\"type\") REFERENCES public.\"types\"(id),\n"
                    + "\tCONSTRAINT animal_fk_1 FOREIGN KEY (sex) REFERENCES public.sex(id),\n"
                    + "\tCONSTRAINT animal_fk_2 FOREIGN KEY (place) REFERENCES public.places(id) DEFERRABLE\n"
                    + ");");
            executeUpdate("CREATE TABLE public.positions (\n"
                    + "\tid int4 NOT NULL,\n"
                    + "\t\"name\" varchar NULL,\n"
                    + "\tsalary int4 NULL,\n"
                    + "\tCONSTRAINT positions_pk PRIMARY KEY (id)\n"
                    + ");");
            executeUpdate("CREATE TABLE public.workman (\n"
                    + "\tid int4 NOT NULL,\n"
                    + "\t\"name\" varchar NOT NULL,\n"
                    + "\tage int4 NULL,\n"
                    + "\t\"position\" int4 NULL,\n"
                    + "\tCONSTRAINT workman_pk PRIMARY KEY (id),\n"
                    + "\tCONSTRAINT workman_fk FOREIGN KEY (\"position\") REFERENCES public.positions(id)\n"
                    + ");");
            executeUpdate("CREATE TABLE public.zoo (\n"
                    + "\tid int4 NOT NULL,\n"
                    + "\t\"name\" varchar NULL,\n"
                    + "\tCONSTRAINT zoo_pk PRIMARY KEY (id)\n"
                    + ");");
            executeUpdate("CREATE TABLE public.zoo_animal (\n"
                    + "\tzoo_id int4 NOT NULL,\n"
                    + "\tanimal_id int4 NOT NULL,\n"
                    + "\ttime_apperance timestamp(0) NULL,\n"
                    + "\tworkman int4 NULL,\n"
                    + "\tCONSTRAINT zoo_animal_fk FOREIGN KEY (zoo_id) REFERENCES public.zoo(id),\n"
                    + "\tCONSTRAINT zoo_animal_fk_1 FOREIGN KEY (animal_id) REFERENCES public.animal(id),\n"
                    + "\tCONSTRAINT zoo_animal_fk_2 FOREIGN KEY (workman) REFERENCES public.workman(id)\n"
                    + ");");
            executeUpdate(""
                    + "INSERT INTO public.places (id, \"row\", place_num, \"name\") VALUES(1, 1, 185, '����� 1');\n"
                    + "INSERT INTO public.places (id, \"row\", place_num, \"name\") VALUES(2, 2, 245, '����� 2');\n"
                    + "INSERT INTO public.places (id, \"row\", place_num, \"name\") VALUES(3, 3, 123, '����� 3');\n"
                    + "INSERT INTO public.places (id, \"row\", place_num, \"name\") VALUES(4, 5, 054, '����� 4');\n"
                    + "INSERT INTO public.places (id, \"row\", place_num, \"name\") VALUES(5, 6, 043, '����� 5');");
            executeUpdate("INSERT INTO public.sex (id, \"name\") VALUES(1, '�������');\n"
                    + "INSERT INTO public.sex (id, \"name\") VALUES(2, '�������');");
            executeUpdate(""
                    + "INSERT INTO public.\"types\" (id, \"name\") VALUES(1, '�����');\n"
                    + "INSERT INTO public.\"types\" (id, \"name\") VALUES(2, '������');\n"
                    + "INSERT INTO public.\"types\" (id, \"name\") VALUES(3, '������');\n"
                    + "INSERT INTO public.\"types\" (id, \"name\") VALUES(4, '�����');\n"
                    + "INSERT INTO public.\"types\" (id, \"name\") VALUES(5, '����');");
            executeUpdate(""
                    + "INSERT INTO public.animal (id, \"name\", age, \"type\", sex, place) VALUES(1, '�������', 2, 1, 1, 1);\n"
                    + "INSERT INTO public.animal (id, \"name\", age, \"type\", sex, place) VALUES(2, '������', 4, 2, 1, 1);\n"
                    + "INSERT INTO public.animal (id, \"name\", age, \"type\", sex, place) VALUES(3, '������', 5, 2, 1, 2);\n"
                    + "INSERT INTO public.animal (id, \"name\", age, \"type\", sex, place) VALUES(4, '��', 6, 3, 2, 2);\n"
                    + "INSERT INTO public.animal (id, \"name\", age, \"type\", sex, place) VALUES(5, '�������', 7, 4, 2,3);\n"
                    + "INSERT INTO public.animal (id, \"name\", age, \"type\", sex, place) VALUES(6, '����', 3, 5, 2, 4);\n"
                    + "INSERT INTO public.animal (id, \"name\", age, \"type\", sex, place) VALUES(7, '�����', 5, 3, 1, 5);\n"
                    + "INSERT INTO public.animal (id, \"name\", age, \"type\", sex, place) VALUES(8, '�����', 7, 2, 1, 3);\n"
                    + "INSERT INTO public.animal (id, \"name\", age, \"type\", sex, place) VALUES(9, '������', 4, 1, 1, 2);\n"
                    + "INSERT INTO public.animal (id, \"name\", age, \"type\", sex, place) VALUES(10, '�������', 5, 2, 1, 4);");
            executeUpdate(""
                    + "INSERT INTO public.positions (id, \"name\", salary) VALUES(1, '������� �������', 25000);\n"
                    + "INSERT INTO public.positions (id, \"name\", salary) VALUES(2, '�������', 20000);\n"
                    + "INSERT INTO public.positions (id, \"name\", salary) VALUES(3, '������� �������', 15000);\n"
                    + "INSERT INTO public.positions (id, \"name\", salary) VALUES(4, '��������', 45000);");
            executeUpdate(""
                    + "INSERT INTO public.workman (id, \"name\", age, \"position\") VALUES(1, '����', 23, 1);\n"
                    + "INSERT INTO public.workman (id, \"name\", age, \"position\") VALUES(2, '����', 34, 2);\n"
                    + "INSERT INTO public.workman (id, \"name\", age, \"position\") VALUES(3, '����', 24, 3);\n"
                    + "INSERT INTO public.workman (id, \"name\", age, \"position\") VALUES(4, '���������',22, 4);\n"
                    + "INSERT INTO public.workman (id, \"name\", age, \"position\") VALUES(5, '����', 32, 3);\n"
                    + "INSERT INTO public.workman (id, \"name\", age, \"position\") VALUES(6, '����', 54, 2);");
            executeUpdate(""
                    + "INSERT INTO public.zoo (id, \"name\") VALUES(1, '�����������');\n"
                    + "INSERT INTO public.zoo (id, \"name\") VALUES(2, '��������');\n"
                    + "INSERT INTO public.zoo (id, \"name\") VALUES(3, '��������');");
            executeUpdate(""
                    + "INSERT INTO public.zoo_animal (zoo_id, animal_id, time_apperance, workman) VALUES(1, 1, null, 1);\n"
                    + "INSERT INTO public.zoo_animal (zoo_id, animal_id, time_apperance, workman) VALUES(2, 2, null, 2);\n"
                    + "INSERT INTO public.zoo_animal (zoo_id, animal_id, time_apperance, workman) VALUES(3, 3, null, 3);\n"
                    + "INSERT INTO public.zoo_animal (zoo_id, animal_id, time_apperance, workman) VALUES(3, 4, null, 4);\n"
                    + "INSERT INTO public.zoo_animal (zoo_id, animal_id, time_apperance, workman) VALUES(2, 5, null, 5);\n"
                    + "INSERT INTO public.zoo_animal (zoo_id, animal_id, time_apperance, workman) VALUES(2, 6, null, 6);\n"
                    + "INSERT INTO public.zoo_animal (zoo_id, animal_id, time_apperance, workman) VALUES(3, 7, null, 1);\n"
                    + "INSERT INTO public.zoo_animal (zoo_id, animal_id, time_apperance, workman) VALUES(1, 8, null, 2);\n"
                    + "INSERT INTO public.zoo_animal (zoo_id, animal_id, time_apperance, workman) VALUES(1, 9, null, 3);\n"
                    + "INSERT INTO public.zoo_animal (zoo_id, animal_id, time_apperance, workman) VALUES(2, 10, null, 4);");
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void executeUpdate(String sql) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        connection
                .createStatement()
                .executeUpdate(sql);
        connection.close();
    }
}

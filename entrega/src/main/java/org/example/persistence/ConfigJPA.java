package org.example.persistence;

import jakarta.persistence.*;
// La clase, gestiona el EntityManagerFactory
public class ConfigJPA {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("clientesBD");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void close() {
        emf.close();
    }

}

package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CategorieProduitServiceImplTest {
    @Autowired
    public CategorieProduitServiceImpl categorieProduitServiceImpl;
    @Autowired
    public CategorieProduitRepository categorieProduitRepository;

    @Test
    public void testAddCategorieProduit() {
        CategorieProduit categorieProduit = new CategorieProduit();
        categorieProduit.setCodeCategorie("Test CategorieProduit2");
        categorieProduitRepository.save(categorieProduit);
        CategorieProduit result = categorieProduitServiceImpl.addCategorieProduit(categorieProduit);
        assertEquals("Test CategorieProduit2", result.getCodeCategorie());
    }

}
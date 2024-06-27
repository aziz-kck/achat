package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CategorieProduitServiceImplTest {
    @Autowired
    public CategorieProduitServiceImpl categorieProduitServiceImpl;
    @Autowired
    public CategorieProduitRepository categorieProduitRepository;

    @Mock
    private CategorieProduitRepository categorieProduitRepositoryy;

    @InjectMocks
    private CategorieProduitServiceImpl categorieProduitService;
    @Test
    void testAddCategorieProduit() {
        CategorieProduit categorieProduit = new CategorieProduit();
        categorieProduit.setCodeCategorie("Test CategorieProduit2");
        categorieProduitRepository.save(categorieProduit);
        CategorieProduit result = categorieProduitServiceImpl.addCategorieProduit(categorieProduit);
        assertEquals("Test CategorieProduit2", result.getCodeCategorie());
    }

//    @Test
//    public void testRetrieveAllCategorieProduits() {
//        // Given
//        CategorieProduit categorieProduit1 = new CategorieProduit();
//        categorieProduit1.setCodeCategorie("Test CategorieProduit1");
//        categorieProduitRepositoryy.save(categorieProduit1);
//
//        CategorieProduit categorieProduit2 = new CategorieProduit();
//        categorieProduit2.setCodeCategorie("Test CategorieProduit2");
//        categorieProduitRepositoryy.save(categorieProduit2);
//
//        // When
//        // Using service method to retrieve all categories
//        Iterable<CategorieProduit> allCategories = categorieProduitServiceImpl.retrieveAllCategorieProduits();
//
//        // Then
//        int count = 0;
//        for (CategorieProduit cp : allCategories) {
//            if (cp.getCodeCategorie().equals("Test CategorieProduit1") || cp.getCodeCategorie().equals("Test CategorieProduit2")) {
//                count++;
//            }
//        }
//        assertEquals(2, count);
//    }

//    @Test
//    public void testRetrieveCategorieProduit() {
//        // Given
//        Long categoryId = 1L;
//        CategorieProduit expectedCategorieProduit = new CategorieProduit();
//        expectedCategorieProduit.setId(categoryId);
//        expectedCategorieProduit.setCodeCategorie("Test Code");
//
//        // Mocking behavior of findById
//        when(categorieProduitRepository.findById(categoryId)).thenReturn(Optional.of(expectedCategorieProduit));
//
//        // When
//        CategorieProduit actualCategorieProduit = categorieProduitServiceImpl.retrieveCategorieProduit(categoryId);
//
//        // Then
//        assertEquals(expectedCategorieProduit, actualCategorieProduit);
//    }

    @Test
    void testRetrieveCategorieProduit_NotFound() {
        // Given
        Long categoryId = 2L;

        // Mocking behavior of findById for a non-existent category
        when(categorieProduitRepositoryy.findById(categoryId)).thenReturn(Optional.empty());

        // When
        CategorieProduit actualCategorieProduit = categorieProduitService.retrieveCategorieProduit(categoryId);

        // Then
        assertEquals(null, actualCategorieProduit);

        // Verify that findById was called once with the correct parameter
        verify(categorieProduitRepositoryy, times(1)).findById(categoryId);
    }
    @Test
    void testUpdateCategorieProduit() {
        // Given
        CategorieProduit categorieProduitToUpdate = new CategorieProduit();
        categorieProduitToUpdate.setId(1L);
        categorieProduitToUpdate.setCodeCategorie("Updated Code");
        categorieProduitToUpdate.setLibelleCategorie("Updated Libelle");

        // When
        CategorieProduit updatedCategorieProduit = categorieProduitService.updateCategorieProduit(categorieProduitToUpdate);

        // Then
        verify(categorieProduitRepositoryy).save(categorieProduitToUpdate);
        assertEquals(categorieProduitToUpdate, updatedCategorieProduit);
    }

    @Test
    void testDeleteCategorieProduit() {
        // Given
        Long categoryId = 1L;

        // When
        categorieProduitService.deleteCategorieProduit(categoryId);

        // Then
        verify(categorieProduitRepositoryy).deleteById(categoryId);
    }
}


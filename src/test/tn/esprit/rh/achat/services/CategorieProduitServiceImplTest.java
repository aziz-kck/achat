package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategorieProduitServiceImplTest {

    @Mock
    private CategorieProduitRepository categorieProduitRepository;

    @InjectMocks
    private CategorieProduitServiceImpl categorieProduitService;

    @Test
    void testAddCategorieProduit() {
        CategorieProduit categorieProduit = new CategorieProduit();
        categorieProduit.setCodeCategorie("Test CategorieProduit2");
        when(categorieProduitRepository.save(categorieProduit)).thenReturn(categorieProduit);
        CategorieProduit result = categorieProduitService.addCategorieProduit(categorieProduit);
        assertEquals("Test CategorieProduit2", result.getCodeCategorie());
    }

    @Test
    void testRetrieveCategorieProduit_NotFound() {
        Long categoryId = 2L;
        when(categorieProduitRepository.findById(categoryId)).thenReturn(Optional.empty());
        CategorieProduit actualCategorieProduit = categorieProduitService.retrieveCategorieProduit(categoryId);
        assertEquals(null, actualCategorieProduit);
        verify(categorieProduitRepository, times(1)).findById(categoryId);
    }

    @Test
    void testUpdateCategorieProduit() {
        CategorieProduit categorieProduitToUpdate = new CategorieProduit();
        categorieProduitToUpdate.setId(1L);
        categorieProduitToUpdate.setCodeCategorie("Updated Code");
        categorieProduitToUpdate.setLibelleCategorie("Updated Libelle");
        when(categorieProduitRepository.save(categorieProduitToUpdate)).thenReturn(categorieProduitToUpdate);
        CategorieProduit updatedCategorieProduit = categorieProduitService.updateCategorieProduit(categorieProduitToUpdate);
        verify(categorieProduitRepository).save(categorieProduitToUpdate);
        assertEquals(categorieProduitToUpdate, updatedCategorieProduit);
    }

    @Test
    void testDeleteCategorieProduit() {
        Long categoryId = 1L;
        doNothing().when(categorieProduitRepository).deleteById(categoryId);
        categorieProduitService.deleteCategorieProduit(categoryId);
        verify(categorieProduitRepository).deleteById(categoryId);
    }
}

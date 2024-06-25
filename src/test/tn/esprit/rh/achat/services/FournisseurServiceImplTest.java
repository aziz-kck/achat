package tn.esprit.rh.achat.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.CategorieFournisseur;
import tn.esprit.rh.achat.entities.DetailFournisseur;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.repositories.DetailFournisseurRepository;
import tn.esprit.rh.achat.repositories.FournisseurRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FournisseurServiceImplTest {

    @Mock
    private FournisseurRepository fournisseurRepository;
    @Mock
    private DetailFournisseurRepository detailFournisseurRepository;
    @InjectMocks
    private FournisseurServiceImpl fournisseurService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void testRetrieveAllFournisseurs() {
        // Given
        List<Fournisseur> fournisseurs = new ArrayList<>();
        fournisseurs.add(new Fournisseur(1L, "F001", "Fournisseur 1", CategorieFournisseur.ORDINAIRE));
        fournisseurs.add(new Fournisseur(2L, "F002", "Fournisseur 2", CategorieFournisseur.CONVENTIONNE));
        when(fournisseurRepository.findAll()).thenReturn(fournisseurs);

        // When
        List<Fournisseur> result = fournisseurService.retrieveAllFournisseurs();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(fournisseurRepository, times(1)).findAll();
    }
    @Test
     void testAddFournisseur() {
        // Mock data
        Fournisseur fournisseur = new Fournisseur("CODE1", "Libellé 1", CategorieFournisseur.ORDINAIRE);

        // Mock repository save method
        when(fournisseurRepository.save(any(Fournisseur.class))).thenReturn(fournisseur);

        // Call service method
        Fournisseur result = fournisseurService.addFournisseur(fournisseur);

        // Assertions
        assertNotNull(result);
        assertEquals("CODE1", result.getCode());
        assertNotNull(result.getDetailFournisseur());
        assertNotNull(result.getDetailFournisseur().getDateDebutCollaboration());

        // Verify repository save method call
        verify(fournisseurRepository, times(1)).save(any(Fournisseur.class));
    }
    @Test
     void testUpdateFournisseur() {
        // Mock data
        Fournisseur fournisseur = new Fournisseur(1L, "CODE1", "Libellé 1", CategorieFournisseur.CONVENTIONNE);

        // Mock repository save method
        when(fournisseurRepository.save(any(Fournisseur.class))).thenReturn(fournisseur);

        // Call service method
        Fournisseur result = fournisseurService.updateFournisseur(fournisseur);

        // Assertions
        assertNotNull(result);
        assertEquals("CODE1", result.getCode());

        // Verify repository save method call
        verify(fournisseurRepository, times(1)).save(any(Fournisseur.class));
    }

    @Test
     void testDeleteFournisseur() {
        // Mock data
        Long fournisseurId = 1L;

        // Call service method
        fournisseurService.deleteFournisseur(fournisseurId);

        // Verify repository deleteById method call
        verify(fournisseurRepository, times(1)).deleteById(fournisseurId);
    }
}
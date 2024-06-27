package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.CategorieFournisseur;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.repositories.FournisseurRepository;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
class FournisseurServiceImplTest {

    @Mock
    private FournisseurRepository fournisseurRepository;

    @InjectMocks
    private FournisseurServiceImpl fournisseurService;

    @Mock
    private SecteurActiviteRepository secteurActiviteRepository;




    @Test
    void testRetrieveAllFournisseurs() {
        // Given
        Fournisseur fournisseur1 = new Fournisseur(1L, "code1", "Libelle 1", CategorieFournisseur.ORDINAIRE);
        Fournisseur fournisseur2 = new Fournisseur(2L, "code2", "Libelle 2", CategorieFournisseur.CONVENTIONNE);
        List<Fournisseur> fournisseurs = Arrays.asList(fournisseur1, fournisseur2);

        when(fournisseurRepository.findAll()).thenReturn(fournisseurs);

        // When
        List<Fournisseur> result = fournisseurService.retrieveAllFournisseurs();

        // Then
        assertEquals(2, result.size());
        assertEquals(fournisseur1, result.get(0));
        assertEquals(fournisseur2, result.get(1));
        verify(fournisseurRepository, times(1)).findAll();
    }

    @Test
    void testAddFournisseur() {
        // Given
        Fournisseur fournisseur = new Fournisseur("code1", "Libelle 1", CategorieFournisseur.CONVENTIONNE);

        // When
        Fournisseur savedFournisseur = fournisseurService.addFournisseur(fournisseur);

        // Then
        Assertions.assertNotNull(savedFournisseur);
        Assertions.assertEquals("code1", savedFournisseur.getCode());
        Assertions.assertEquals("Libelle 1", savedFournisseur.getLibelle());
        Assertions.assertNotNull(savedFournisseur.getDetailFournisseur());
        verify(fournisseurRepository, times(1)).save(any(Fournisseur.class));
    }

    @Test
    void testDeleteFournisseur() {
        // Given
        Long fournisseurId = 1L;

        // When
        fournisseurService.deleteFournisseur(fournisseurId);

        // Then
        verify(fournisseurRepository, times(1)).deleteById(fournisseurId);
    }
    @Test
    void testRetrieveFournisseur() {
        // Given
        Long fournisseurId = 1L;
        Fournisseur expectedFournisseur = new Fournisseur(fournisseurId, "code1", "Libelle 1", CategorieFournisseur.ORDINAIRE);

        // Mock repository behavior
        when(fournisseurRepository.findById(fournisseurId)).thenReturn(Optional.of(expectedFournisseur));

        // When
        Fournisseur retrievedFournisseur = fournisseurService.retrieveFournisseur(fournisseurId);

        // Then
        assertEquals(expectedFournisseur, retrievedFournisseur);
        verify(fournisseurRepository, times(1)).findById(fournisseurId);
    }


}

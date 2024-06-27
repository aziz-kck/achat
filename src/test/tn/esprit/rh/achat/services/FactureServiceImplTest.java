//package tn.esprit.rh.achat.services;
//
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import tn.esprit.rh.achat.entities.Facture;
//import tn.esprit.rh.achat.entities.Fournisseur;
//import tn.esprit.rh.achat.repositories.*;
//
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class FactureServiceImplTest {
//
//    @Mock
//    private FactureRepository factureRepository;
//
//    @Mock
//    private FournisseurRepository fournisseurRepository;
//
//    @InjectMocks
//    private FactureServiceImpl factureService;
//
//    @Test
//     void testRetrieveAllFactures() {
//        // Given
//        Facture facture1 = new Facture();
//        facture1.setIdFacture(1L);
//        Facture facture2 = new Facture();
//        facture2.setIdFacture(2L);
//        List<Facture> factures = Arrays.asList(facture1, facture2);
//
//        when(factureRepository.findAll()).thenReturn(factures);
//
//        // When
//        List<Facture> result = factureService.retrieveAllFactures();
//
//        // Then
//        assertEquals(2, result.size());
//        assertEquals(1L, result.get(0).getIdFacture());
//        assertEquals(2L, result.get(1).getIdFacture());
//
//        verify(factureRepository).findAll();
//    }
//
//    @Test
//     void testAddFacture() {
//        // Given
//        Facture factureToAdd = new Facture();
//
//        when(factureRepository.save(any(Facture.class))).thenReturn(factureToAdd);
//
//        // When
//        Facture addedFacture = factureService.addFacture(factureToAdd);
//
//        // Then
//        assertEquals(factureToAdd, addedFacture);
//
//        verify(factureRepository).save(factureToAdd);
//    }
//
//    @Test
//     void testCancelFacture() {
//        // Given
//        Long factureId = 1L;
//        Facture facture = new Facture();
//        facture.setIdFacture(factureId);
//        when(factureRepository.findById(factureId)).thenReturn(Optional.of(facture));
//
//        // When
//        factureService.cancelFacture(factureId);
//
//        // Then
//        assertTrue(facture.isArchivee());
//        verify(factureRepository).save(facture);
//    }
//
//    @Test
//     void testRetrieveFacture() {
//        // Given
//        Long factureId = 1L;
//        Facture facture = new Facture();
//        facture.setIdFacture(factureId);
//        when(factureRepository.findById(factureId)).thenReturn(Optional.of(facture));
//
//        // When
//        Facture retrievedFacture = factureService.retrieveFacture(factureId);
//
//        // Then
//        assertEquals(factureId, retrievedFacture.getIdFacture());
//        verify(factureRepository).findById(factureId);
//    }
//
//    @Test
//    void testGetFacturesByFournisseur() {
//        // Given
//        Long fournisseurId = 1L;
//        Fournisseur fournisseur = new Fournisseur();
//        Facture facture1 = new Facture();
//        facture1.setIdFacture(1L);
//        Facture facture2 = new Facture();
//        facture2.setIdFacture(2L);
//        fournisseur.setFactures(new HashSet<>(Arrays.asList(facture1, facture2)));
//
//        when(fournisseurRepository.findById(fournisseurId)).thenReturn(Optional.of(fournisseur));
//
//        // When
//        List<Facture> factures = factureService.getFacturesByFournisseur(fournisseurId);
//
//        // Then
//        assertEquals(2, factures.size());
//        verify(fournisseurRepository).findById(fournisseurId);
//    }
//}

package tn.esprit.rh.achat.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import tn.esprit.rh.achat.entities.*;
import tn.esprit.rh.achat.repositories.*;

@ExtendWith(MockitoExtension.class)
class FactureServiceImplTest {

    @Mock
    private FactureRepository factureRepository;

    @Mock
    private OperateurRepository operateurRepository;

    @Mock
    private FournisseurRepository fournisseurRepository;

    @Mock
    private ReglementServiceImpl reglementService;

    @InjectMocks
    private FactureServiceImpl factureService;

    private Facture facture;

    @BeforeEach
    void setUp() {
        facture = new Facture();
        facture.setIdFacture(1L);
        facture.setArchivee(false);
    }

    @Test
    void testRetrieveAllFactures() {
        List<Facture> factures = Arrays.asList(facture);
        when(factureRepository.findAll()).thenReturn(factures);

        List<Facture> result = factureService.retrieveAllFactures();
        assertEquals(factures, result);
        verify(factureRepository, times(1)).findAll();
    }

    @Test
    void testAddFacture() {
        when(factureRepository.save(facture)).thenReturn(facture);

        Facture result = factureService.addFacture(facture);
        assertEquals(facture, result);
        verify(factureRepository, times(1)).save(facture);
    }

    @Test
    void testCancelFacture() {
        when(factureRepository.findById(facture.getIdFacture())).thenReturn(Optional.of(facture));

        factureService.cancelFacture(facture.getIdFacture());

        assertTrue(facture.isArchivee());
        verify(factureRepository, times(1)).save(facture);
        verify(factureRepository, times(1)).updateFacture(facture.getIdFacture());
    }

    @Test
    void testRetrieveFacture() {
        when(factureRepository.findById(facture.getIdFacture())).thenReturn(Optional.of(facture));

        Facture result = factureService.retrieveFacture(facture.getIdFacture());
        assertEquals(facture, result);
        verify(factureRepository, times(1)).findById(facture.getIdFacture());
    }

    @Test
    void testGetFacturesByFournisseur() {
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setFactures(Set.of(facture));
        when(fournisseurRepository.findById(fournisseur.getIdFournisseur())).thenReturn(Optional.of(fournisseur));

        List<Facture> result = factureService.getFacturesByFournisseur(fournisseur.getIdFournisseur());
        assertEquals(List.of(facture), result);
        verify(fournisseurRepository, times(1)).findById(fournisseur.getIdFournisseur());
    }

    @Test
    void testAssignOperateurToFacture() {
        Operateur operateur = new Operateur();
        operateur.setFactures(new HashSet<>());
        when(factureRepository.findById(facture.getIdFacture())).thenReturn(Optional.of(facture));
        when(operateurRepository.findById(operateur.getIdOperateur())).thenReturn(Optional.of(operateur));

        factureService.assignOperateurToFacture(operateur.getIdOperateur(), facture.getIdFacture());

        assertTrue(operateur.getFactures().contains(facture));
        verify(operateurRepository, times(1)).save(operateur);
    }

    @Test
    void testPourcentageRecouvrement() {
        Date startDate = new Date();
        Date endDate = new Date();
        when(factureRepository.getTotalFacturesEntreDeuxDates(startDate, endDate)).thenReturn(1000f);
        when(reglementService.getChiffreAffaireEntreDeuxDate(startDate, endDate)).thenReturn(500f);

        float result = factureService.pourcentageRecouvrement(startDate, endDate);
        assertEquals(50f, result);
    }
}

package tn.esprit.rh.achat.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
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
import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.repositories.ReglementRepository;

@ExtendWith(MockitoExtension.class)
class ReglementServiceImplTest {
    @Mock
    private ReglementRepository reglementRepository;

    @InjectMocks
    private ReglementServiceImpl reglementService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void testRetrieveAllReglements() {
        List<Reglement> reglements = List.of(
                new Reglement(1L, 100.0f, 50.0f, true, new Date(), null),
                new Reglement(2L, 200.0f, 100.0f, false, new Date(), null)
        );
        when(reglementRepository.findAll()).thenReturn(reglements);

        List<Reglement> result = reglementService.retrieveAllReglements();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(reglementRepository, times(1)).findAll();
    }

    @Test
     void testAddReglement() {
        Reglement reglement = new Reglement(1L, 100.0f, 50.0f, true, new Date(), null);
        when(reglementRepository.save(any(Reglement.class))).thenReturn(reglement);

        Reglement result = reglementService.addReglement(reglement);

        assertNotNull(result);
        assertEquals(100.0f, result.getMontantPaye());
        verify(reglementRepository, times(1)).save(any(Reglement.class));
    }

    @Test
     void testRetrieveReglement() {
        Reglement reglement = new Reglement(1L, 100.0f, 50.0f, true, new Date(), null);
        when(reglementRepository.findById(1L)).thenReturn(Optional.of(reglement));

        Reglement result = reglementService.retrieveReglement(1L);

        assertNotNull(result);
        assertEquals(1L, result.getIdReglement());
        verify(reglementRepository, times(1)).findById(1L);
    }

    @Test
     void testRetrieveReglementByFacture() {
        List<Reglement> reglements = List.of(
                new Reglement(1L, 100.0f, 50.0f, true, new Date(), null),
                new Reglement(2L, 200.0f, 100.0f, false, new Date(), null)
        );
        when(reglementRepository.retrieveReglementByFacture(1L)).thenReturn(reglements);

        List<Reglement> result = reglementService.retrieveReglementByFacture(1L);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(reglementRepository, times(1)).retrieveReglementByFacture(1L);
    }

    @Test
     void testGetChiffreAffaireEntreDeuxDate() {
        float expectedChiffreAffaire = 5000.0f;
        Date startDate = new Date();
        Date endDate = new Date();
        when(reglementRepository.getChiffreAffaireEntreDeuxDate(startDate, endDate)).thenReturn(expectedChiffreAffaire);

        float result = reglementService.getChiffreAffaireEntreDeuxDate(startDate, endDate);

        assertEquals(expectedChiffreAffaire, result);
        verify(reglementRepository, times(1)).getChiffreAffaireEntreDeuxDate(startDate, endDate);
    }
}